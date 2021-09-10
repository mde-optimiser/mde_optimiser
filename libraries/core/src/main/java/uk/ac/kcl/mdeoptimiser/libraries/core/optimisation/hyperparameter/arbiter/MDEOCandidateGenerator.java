package uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter.arbiter;

import org.deeplearning4j.arbiter.optimize.api.Candidate;
import org.deeplearning4j.arbiter.optimize.api.CandidateGenerator;
import org.deeplearning4j.arbiter.optimize.api.OptimizationResult;
import org.deeplearning4j.arbiter.optimize.api.ParameterSpace;

/** Generates more candidates using the implemented strategy */
public class MDEOCandidateGenerator<MoeaFrameworkAlgorithmConfiguration>
    implements CandidateGenerator {

  @Override
  public boolean hasMoreCandidates() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public Candidate getCandidate() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void reportResults(OptimizationResult result) {
    // TODO Auto-generated method stub

  }

  @Override
  public ParameterSpace<?> getParameterSpace() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void setRngSeed(long rngSeed) {
    // TODO Auto-generated method stub

  }

  @Override
  public Class<?> getCandidateType() {
    // TODO Auto-generated method stub
    return null;
  }
}
