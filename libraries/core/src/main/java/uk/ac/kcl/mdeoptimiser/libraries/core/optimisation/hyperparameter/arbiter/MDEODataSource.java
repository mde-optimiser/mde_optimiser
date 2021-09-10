package uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter.arbiter;

import java.util.Properties;
import org.deeplearning4j.arbiter.optimize.api.data.DataSource;

public class MDEODataSource implements DataSource {

  @Override
  public void configure(Properties properties) {}

  @Override
  public Object trainData() {
    return null;
  }

  @Override
  public Object testData() {
    return null;
  }

  @Override
  public Class<?> getDataType() {
    return null;
  }
}
