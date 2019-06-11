/**
 * Created by alxbrd on 2/7/18.
 */

import com.github.javafaker.Faker;
import org.dynemf.EObjectWrapper;
import org.dynemf.EPackageWrapper;
import org.dynemf.ResourceSetWrapper;
import org.eclipse.emf.ecore.EObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.dynemf.ResourceSetWrapper.rset;

public class NRPModelGenerator {

    private List<EObjectWrapper> softwareArtifactObjects = new ArrayList<EObjectWrapper>();
    private List<EObjectWrapper> requirementRealisationObjects = new ArrayList<EObjectWrapper>();
    private List<EObjectWrapper> valuationObjects = new ArrayList<EObjectWrapper>();

    public static void main(String args[]) throws IOException {

        NRPModelGenerator modelGenerator = new NRPModelGenerator();
        modelGenerator.generateModel();
    }

    public void generateModel() throws IOException {
        ResourceSetWrapper rset = rset().register("src/main/resources/nextReleaseProblem.ecore");

        EPackageWrapper metamodel = rset.ePackage("http://model.nrp");

        EObjectWrapper<EObject> modelRoot = metamodel.create("NRP");
        Faker faker = new Faker();

        int maximumCustomers = 25000;
        int customers = 3;//getRandom(maximumCustomers);
        int maximumCustomerImportance = 5;

        int maximumRequirements = 50;
        int requirements = 5;//getRandom(maximumRequirements) + customers;

        int maximumValuations = 3;
        int valuations = getRandom(maximumValuations);

        int maximumRequirementRealisations = 3;
        int requirementRealisations = getRandom(maximumRequirementRealisations);

        int maximumSoftwareArtifacts =100;
        int softwareArtifacts = 10; //getRandom(maximumSoftwareArtifacts);

        int maximumSoftwareArtifactsDependencies = 3;

        //Generate customers
        List<EObjectWrapper> customerObjects = new ArrayList<EObjectWrapper>();
        for(int i = 0;  i < customers; i++) {

            EObjectWrapper customer = metamodel.create("Customer");
            customer.set("name", faker.name().fullName());
            customer.set("importance", (double) getRandom(maximumCustomerImportance));

            modelRoot.add("customers", customer);

            customerObjects.add(customer);
        }

        //Generate requirements
        for(int i = 0; i < requirements; i ++){
            EObjectWrapper requirement = metamodel.create("Requirement");

            requirement.set("name", String.format("Specification #%s", faker.code().ean8()));

            modelRoot.add("requirements", requirement);

            //Add Valuations
            //TODO randomise
            for(int j = 0; j < getRandom(valuations); j++){
                EObjectWrapper valuation = metamodel.create("Valuation");

                valuation.set("value", (double) getRandom(10));

                requirement.add("valuations", valuation);

                int  maximumValuationCustomers = getRandom(maximumValuations);

                for(int k = 0; k < maximumValuationCustomers; k++){

                    EObjectWrapper customer = customerObjects.get(getRandom(0, customerObjects.size()-1));
                        customer.add("assigns", valuation);
                }

                valuationObjects.add(valuation);
            }

            //Add Realisations
            //TODO randomise
            for(int j = 0; j < getRandom(requirementRealisations); j++){
                EObjectWrapper realisation = metamodel.create("RequirementRealisation");

                realisation.set("percentage", round(new Random().nextDouble(), 2));

                requirement.add("realisations", realisation);

                //Add at least one software artifact to each realization
                EObjectWrapper softwareArtifact = getSoftwareArtifact(metamodel, faker);
                realisation.add("dependsOn", softwareArtifact);

                softwareArtifactObjects.add(softwareArtifact);

                requirementRealisationObjects.add(realisation);
            }
        }

        //Generate SoftwareArtifacts
        for(int i = 0; i < softwareArtifacts; i++) {
            EObjectWrapper softwareArtifact = getSoftwareArtifact(metamodel, faker);

            //Build dependency tree on other artifacts for this artifact
            //TODO add multiple realisations randomly for a software artifact
            EObjectWrapper saRequirementRealisation = requirementRealisationObjects
                    .get(getRandom(0, requirementRealisationObjects.size() - 1));

            saRequirementRealisation.add("dependsOn", softwareArtifact);

            //Generate dependencies bool
            if (new Random().nextDouble() > 0.7) {

                //How many dependencies
                int dependencies = getRandom(maximumSoftwareArtifactsDependencies);

                //Generate them
                //TODO Make this recursive to make it more interesting
                for (int j = 0; j < dependencies; j++) {

                    EObjectWrapper dependencyArtifact = getSoftwareArtifact(metamodel, faker);
                    softwareArtifact.add("requires", dependencyArtifact);

                    //Add dependency on the same req realization as original sa
                    saRequirementRealisation.add("dependsOn", dependencyArtifact);

                    //Add another random realisation dependency for this artifact
                    EObjectWrapper dependencyReqRealisation = requirementRealisationObjects
                            .get(getRandom(0, requirementRealisationObjects.size() - 1));

                    dependencyReqRealisation.add("dependsOn", dependencyArtifact);

                    //modelRoot.add("availableArtifacts", dependencyArtifact);
                    softwareArtifactObjects.add(dependencyArtifact);
                }
            }

            softwareArtifactObjects.add(softwareArtifact);
        }

        for(EObjectWrapper softwareArtifact : softwareArtifactObjects){
            modelRoot.add("availableArtifacts", softwareArtifact);
        }

        //Finally add a solution

        EObjectWrapper solution = metamodel.create("Solution");

        modelRoot.add("solutions", solution);

        rset.create(String.format("gen/nrp/nrp-model-%s-cus-%s-req-%s-sa.xmi", customers,
                requirements, softwareArtifactObjects.size())).add(modelRoot).save();
        //rset.create(String.format("gen/nrp/nrp-model.xmi", customers, requirements)).add(modelRoot).save();
    }

    private EObjectWrapper getSoftwareArtifact(EPackageWrapper metamodel, Faker faker){

        EObjectWrapper softwareArtifact = metamodel.create("SoftwareArtifact");

        softwareArtifact.set("name", String.format("Software artifact %s version %s", faker.app().name(), faker.app().version()));

        EObjectWrapper cost = metamodel.create("Cost");
        cost.set("amount", (double) getRandom(50, 100));

        softwareArtifact.add("costs", cost);
        return softwareArtifact;
    }

    private int getRandom(int random) {
        return new Random().nextInt(random) + 1;
    }

    private int getRandom(int start, int end){
        return new Random().nextInt((end - start) + 1) + start;
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}









