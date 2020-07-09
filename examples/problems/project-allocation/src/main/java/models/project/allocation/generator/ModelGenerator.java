package models.project.allocation.generator;

import com.github.javafaker.Faker;
import com.google.common.io.Resources;
import com.google.inject.Guice;
import com.google.inject.Injector;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import models.project.allocation.ecore.StudentProjectMarkerAllocation.Academic;
import models.project.allocation.ecore.StudentProjectMarkerAllocation.Project;
import models.project.allocation.ecore.StudentProjectMarkerAllocation.Student;
import models.project.allocation.ecore.StudentProjectMarkerAllocation.StudentProjectMarkerAllocationFactory;
import models.project.allocation.ecore.StudentProjectMarkerAllocation.StudentProjectMarkerAllocationPackage;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseBool;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.constraint.StrRegEx;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvMapReader;
import org.supercsv.io.ICsvMapReader;
import org.supercsv.prefs.CsvPreference;

public class ModelGenerator {

  static String CSV_FILENAME = "allocations.csv";
  // This will hide true values from the real csv file above with fake values
  static boolean GENERATE_FAKES = true;
  static Injector injector = Guice.createInjector();

  ResourceSet resourceSet;
  Resource resource;
  List<Academic> academics = new ArrayList<Academic>();
  List<Student> students = new ArrayList<Student>();
  List<Project> projects = new ArrayList<Project>();
  Faker faker;

  // Register the metamodel
  public static void main(String args[]) {

    var app = injector.getInstance(ModelGenerator.class);

    try {
      app.run();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void run() throws Exception {

    // Do EMF stuff
    this.resourceSet = new ResourceSetImpl();
    // Register the appropriate resource factory to handle all file extensions.
    //
    resourceSet
        .getResourceFactoryRegistry()
        .getExtensionToFactoryMap()
        .put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());

    // Register the package to ensure it is available during loading.
    //
    resourceSet
        .getPackageRegistry()
        .put(
            StudentProjectMarkerAllocationPackage.eNS_URI,
            StudentProjectMarkerAllocationPackage.eINSTANCE);

    this.faker = new Faker();

    this.resource =
        resourceSet.createResource(URI.createURI("src/main/resources/ug_pg_allocation.xmi"));

    // Load and serialise
    serialiseModels(readModelsFromCSV());
  }

  public void serialiseModels(List<Map<String, Object>> csvRows) throws IOException {

    csvRows.forEach(
        row -> {
          var currentAcademic = getAcademic(row);
          var currentStudent = getStudent(row);
          var currentProject = getProject(row);

          currentAcademic.getProjects().add(currentProject);
          currentProject.setStudent(currentStudent);

          // Each project has the supervisor as the first marker
          // currentProject.setFirstMarker(currentProject.getSupervisor());
        });

    // Generate fake IDs
    if (GENERATE_FAKES) {
      this.academics.forEach(a -> a.setNumber(faker.number().digits(10)));
      this.students.forEach(s -> s.setNumber(faker.number().digits(10)));
    }

    var markerAllocationModel =
        StudentProjectMarkerAllocationFactory.eINSTANCE.createMarkerAllocationModel();

    markerAllocationModel.getAcademics().addAll(academics);
    markerAllocationModel.getStudents().addAll(students);
    markerAllocationModel.getProjects().addAll(projects);

    resource.getContents().add(markerAllocationModel);

    resource.save(Collections.EMPTY_MAP);
  }

  public Project getProject(Map<String, Object> row) {

    var projectTitle = row.get("Project Title").toString();

    Project project;

    var currentProject =
        this.projects.stream().filter(p -> p.getTitle().equals(projectTitle)).findFirst();

    if (!currentProject.isPresent()) {

      project = StudentProjectMarkerAllocationFactory.eINSTANCE.createProject();

      if (GENERATE_FAKES) {
        project.setTitle(faker.app().name());
      } else {
        project.setTitle(projectTitle);
      }

      this.projects.add(project);

    } else {
      return currentProject.get();
    }

    return project;
  }

  public Student getStudent(Map<String, Object> row) {

    var studentNumber = (String) row.get("Student Number");

    Student student;

    var currentStudent =
        this.students.stream().filter(s -> s.getNumber().equals(studentNumber)).findFirst();

    if (!currentStudent.isPresent()) {

      student = StudentProjectMarkerAllocationFactory.eINSTANCE.createStudent();

      if (GENERATE_FAKES) {

        var firstName = this.faker.name().firstName();
        var lastName = this.faker.name().lastName();
        student.setName(String.format("%s %s", firstName, lastName));
        // This will be faked separately, later
        student.setNumber(studentNumber);
        student.setEmail(String.format("%s.%s@mde-optimiser.fake", firstName, lastName));
      } else {
        student.setName((String) row.get("Student Name"));
        student.setNumber(studentNumber);
        student.setEmail((String) row.get("Student Email"));
      }

      this.students.add(student);
    } else {
      return currentStudent.get();
    }

    return student;
  }

  public Academic getAcademic(Map<String, Object> row) {

    var academicNumber = (String) row.get("Supervisor Username");

    Academic academic;

    var currentAcademic =
        this.academics.stream().filter(a -> a.getNumber().equals(academicNumber)).findFirst();

    if (!currentAcademic.isPresent()) {

      academic = StudentProjectMarkerAllocationFactory.eINSTANCE.createAcademic();

      if (GENERATE_FAKES) {

        var firstName = this.faker.name().firstName();
        var lastName = this.faker.name().lastName();

        academic.setName(String.format("%s %s", firstName, lastName));
        // This will be faked separately, later
        academic.setNumber(academicNumber);
        academic.setEmail(String.format("%s.%s@mde-optimiser.fake", firstName, lastName));
      } else {
        academic.setName((String) row.get("Supervisor Name"));
        academic.setEmail((String) row.get("Supervisor Email"));
        academic.setNumber((String) academicNumber);
      }

      this.academics.add(academic);

    } else {
      return currentAcademic.get();
    }

    return academic;
  }

  public CellProcessor[] getProcessors() {

    var emailRegex = "[a-z0-9\\._-']+@[a-z0-9\\.]+";
    StrRegEx.registerMessage(emailRegex, "must be a valid email address");

    // TODO Add the rest of the validation if needed?
    var processors =
        new CellProcessor[] {
          new NotNull(), // Student Number
          new NotNull(), // Student Name
          new NotNull(), // Student Email - would be nice to use the validator above, but no mas
          new NotNull(), // Project Title
          new NotNull(), // Stage
          new Optional(new ParseBool()), // Resit
          new NotNull(), // Supervisor Name
          new NotNull(), // Supervisor Username
          new NotNull(), // Supervisor Email
          new NotNull() // Project Status
        };

    return processors;
  }

  // Load models from CSV
  public List<Map<String, Object>> readModelsFromCSV() throws Exception {

    ICsvMapReader mapReader = null;

    var rows = new ArrayList<Map<String, Object>>();

    try {

      var resource = Resources.getResource(CSV_FILENAME);

      mapReader =
          new CsvMapReader(new FileReader(resource.getPath()), CsvPreference.STANDARD_PREFERENCE);
      var header = mapReader.getHeader(true);
      var processors = getProcessors();

      Map<String, Object> projectMap;

      while ((projectMap = mapReader.read(header, processors)) != null) {

        rows.add(projectMap);
      }
    } finally {
      if (mapReader != null) {
        mapReader.close();
      }
    }

    return rows;
  }
}
