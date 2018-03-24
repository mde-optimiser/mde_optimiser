package uk.ac.kcl.mdeoptimise.dashboard.api.hashing;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;

public class Hashing {
	
	public static String generateExperimentId(String macAddress, String moptId, String startTime) {
		HashFunction hf = com.google.common.hash.Hashing.md5();
		HashCode hc = hf.newHasher()
		       .putString(macAddress, Charsets.UTF_8)
		       .putString(moptId, Charsets.UTF_8)
		       .putString(startTime, Charsets.UTF_8)
		       .hash();
		
		return hc.toString();
	}

	public static String generateMoptId(String model, String metamodel) {
		HashFunction hf = com.google.common.hash.Hashing.md5();
		HashCode hc = hf.newHasher()
		       .putString(model, Charsets.UTF_8)
		       .putString(metamodel, Charsets.UTF_8)
		       .hash();
		
		return hc.toString();
	}
}
