import org.dynemf.EObjectWrapper;
import org.dynemf.ResourceSetWrapper;
import org.dynemf.ResourceWrapper;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import java.io.*;
import java.util.HashMap;

import static org.dynemf.ResourceSetWrapper.rset;

public class SolutionsQualityEvaluator {


    private HashMap<Integer, Integer> hashIndex;
    private HashMap<Integer, Integer> stakeholdersIndex;

    public static void main(String[] args) throws IOException {

        SolutionsQualityEvaluator evaluator = new SolutionsQualityEvaluator();

//        if(args.length == 1){
//          System.out.println("Evaluating solutions quality for path: " + args[0]);
//            evaluator.evaluate(args[0]);
            evaluator.evaluate("./gen/scrum-planning/");
//        } else {
//            System.out.println("Expecting a valid path containing MDEO solutions:");
//        }
    }

    public void evaluate(String solutionsPath) throws IOException {

        File[] solutionModelPaths = getSolutionsModelFiles(solutionsPath);

        for(File solutionModelPath : solutionModelPaths){
            EObjectWrapper<EObject> solution = loadSolutionModel(solutionModelPath);

            saveSolutionEvaluation(solution, solutionModelPath);
        }
    }

    private void saveSolutionEvaluation(EObjectWrapper solution, File solutionModelPath) throws IOException {

        File evaluationOutput = new File(solutionModelPath.getAbsolutePath() + ".evaluation.txt");

        PrintWriter sw = new PrintWriter(new FileOutputStream(evaluationOutput, false));
        evaluateSolutionSprints(solution, sw);
        sw.flush();
    }

    private void evaluateSolutionSprints(EObjectWrapper solution, PrintWriter out) {

        EList<EObject> sprints = getFeatureList((EObject) solution.result(), "sprints");

        out.println();
        out.println(String.format("Found %s sprints", sprints.size()));

        evaluateBacklogSize(solution, out);
        evaluateStakeholders(solution, out);

        for(int i = 0; i < sprints.size(); i++){
            evaluateSprint(sprints.get(i), i, out);
            out.println();
            out.println("=============================================");
            out.println();
        }

    }

    private void evaluateStakeholders(EObjectWrapper solution, PrintWriter out) {

        EList<EObject> stakeholders = getFeatureList((EObject) solution.result(), "stakeholders");

        out.println(String.format("Found %s stakeholders", stakeholders.size()));

        this.stakeholdersIndex = new HashMap<Integer, Integer>();

        for(int i = 1; i <= stakeholders.size(); i++ ){
            stakeholdersIndex.put(stakeholders.get(i-1).hashCode(), i);
        }

        out.println();
        for(int i = 0; i < stakeholders.size(); i++){

            EList<EObject> stakeholderItems = getFeatureList(stakeholders.get(i), "workitem");

            int effort = 0;
            int importance = 0;
            for(EObject item : stakeholderItems){
                effort += getFeatureInt(item, "Effort");
                importance += getFeatureInt(item, "Importance");
            }

            out.println(String.format("\tStakeholder-%s stats:", i));
            out.println(String.format("\t\tItems: %s", stakeholderItems.size()));
            out.println(String.format("\t\tTotal Effort: %s", effort));
            out.println(String.format("\t\tTotal Importance: %s", importance));
            out.println();
        }

    }

    private void evaluateBacklogSize(EObjectWrapper solution, PrintWriter out) {

        EObject backlog = getFeatureObject((EObject) solution.result(), "backlog");
        EList<EObject> workItems = getFeatureList(backlog, "workitems");

        EList<EObject> sprints = getFeatureList((EObject) solution.result(), "sprints");



        this.hashIndex = new HashMap<Integer, Integer>();

        for(int i = 1; i <= workItems.size(); i++ ){
            hashIndex.put(workItems.get(i-1).hashCode(), i);
        }


        int backlogEffort = 0;

        for(EObject workItem : workItems){
            backlogEffort += getFeatureInt(workItem, "Effort");
        }

        out.println(String.format("Backlog items: %s", workItems.size()));
        out.println(String.format("Backlog effort points size: %s", backlogEffort));

        if(sprints.size() > 0) {
            out.println(String.format("Proposed team velocity: %d points per sprint", backlogEffort / sprints.size()));
        }

        out.println();
        out.println();
    }

    private void evaluateSprint(EObject sprint, int index, PrintWriter out) {


        out.println(String.format("Sprint %s stats", index));
        out.println();
        out.println("---------------------------------------------");
        out.println();
        EList<EObject> workItems = getFeatureList(sprint, "committedItem");

        out.println(String.format("Committed items total: %s", workItems.size()));

        int sprintEffort = 0;

        for(EObject workItem : workItems){
            sprintEffort += getFeatureInt(workItem, "Effort");
        }

        out.println(String.format("Committed sprint effort: %s", sprintEffort));

        out.println();

        for(int i = 0; i < workItems.size(); i++) {
            int effort = getFeatureInt(workItems.get(i), "Effort");
            int importance = getFeatureInt(workItems.get(i), "Importance");
            String outputFormat = "Committed Work Item %s, Effort %s, Importance %s, Stakeholder %s";
            out.println(String.format(outputFormat, hashIndex.get(workItems.get(i).hashCode()),
                    effort, importance, stakeholdersIndex.get(getFeatureObject(workItems.get(i), "stakeholder").hashCode())));
        }

    }

    private EObjectWrapper<EObject> loadSolutionModel(File solutionPath) {

        // Register the metamodel
        ResourceSetWrapper rset = rset().register("src/main/resources/planning.ecore");

        // Load an existing model that conforms to the previously loaded metamodel
        ResourceWrapper resourceWrapper = rset.open(solutionPath.getAbsolutePath());

        return (EObjectWrapper<EObject>) resourceWrapper.root();
    }

    private File[] getSolutionsModelFiles(String solutionsPath){

        File solutionsOutputPath = new File(solutionsPath);

        //Check the path
        if(solutionsOutputPath.isDirectory() && solutionsOutputPath.exists()){
            System.out.println(String.format("Invalid solutions path given: %s", solutionsPath));
        }

        //Check that it contains at least one xmi file
        return solutionsOutputPath.listFiles(new FilenameFilter(){
            public boolean accept(File dir, String name) {
                return name.endsWith(".xmi");
            }
        });

    }

    /**
     * Helper function getting the value of the named feature (if it exists) for the given EObject.
     */
    private Object getFeature (EObject o, String feature) {

        if(o == null){
            System.out.println("Null object given");
        }

        return o.eGet(o.eClass().getEStructuralFeature(feature));
    }

    private EList<EObject> getFeatureList(EObject object, String feature) {
        return (EList<EObject>) getFeature(object, feature);
    }

    private EObject getFeatureObject(EObject object, String feature) {
        return (EObject) getFeature(object, feature);
    }

    private int getFeatureInt(EObject object, String feature) {
        return (Integer) getFeature(object, feature);
    }
}