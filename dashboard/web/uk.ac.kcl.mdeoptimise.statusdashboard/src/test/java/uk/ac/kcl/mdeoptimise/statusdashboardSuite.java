package uk.ac.kcl.mdeoptimise;

import uk.ac.kcl.mdeoptimise.client.statusdashboardTest;
import com.google.gwt.junit.tools.GWTTestSuite;
import junit.framework.Test;
import junit.framework.TestSuite;

public class statusdashboardSuite extends GWTTestSuite {
  public static Test suite() {
    TestSuite suite = new TestSuite("Tests for statusdashboard");
    suite.addTestSuite(statusdashboardTest.class);
    return suite;
  }
}
