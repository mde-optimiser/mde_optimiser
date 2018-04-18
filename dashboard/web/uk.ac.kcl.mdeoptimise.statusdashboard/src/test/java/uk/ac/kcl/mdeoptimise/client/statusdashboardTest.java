package uk.ac.kcl.mdeoptimise.client;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

import uk.ac.kcl.mdeoptimise.client.services.MDEOService;
import uk.ac.kcl.mdeoptimise.client.services.MDEOServiceAsync;

/**
 * GWT JUnit tests must extend GWTTestCase.
 */
public class statusdashboardTest extends GWTTestCase {

  /**
   * Must refer to a valid module that sources this class.
   */
  public String getModuleName() {
    return "uk.ac.kcl.mdeoptimise.statusdashboardJUnit";
  }

  /**
   * This test will send a request to the server using the getWorkerIds method in
   * MDEOService and verify the response.
   */
  public void testMdeoService() {
    // Create the service that we will test.
    MDEOServiceAsync mdeoService = GWT.create(MDEOService.class);
    ServiceDefTarget target = (ServiceDefTarget) mdeoService;
    target.setServiceEntryPoint(GWT.getModuleBaseURL() + "statusdashboard/mdeo");

    // Since RPC calls are asynchronous, we will need to wait for a response
    // after this test method returns. This line tells the test runner to wait
    // up to 10 seconds before timing out.
    delayTestFinish(10000);

    // Send a request to the server.
    mdeoService.getWorkerIds(new AsyncCallback<List<String>>() {
      public void onFailure(Throwable caught) {
        // The request resulted in an unexpected error.
        fail("Request failure: " + caught.getMessage());
      }

      public void onSuccess(List<String> result) {
        // Verify that the response is correct.
        assertTrue(!result.isEmpty());

        // Now that we have received a response, we need to tell the test runner
        // that the test is complete. You must call finishTest() after an
        // asynchronous test finishes successfully, or the test will time out.
        finishTest();
      }
    });
  }


}
