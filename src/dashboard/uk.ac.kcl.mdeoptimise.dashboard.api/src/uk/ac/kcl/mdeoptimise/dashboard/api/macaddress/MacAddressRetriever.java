package uk.ac.kcl.mdeoptimise.dashboard.api.macaddress;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

public class MacAddressRetriever {
	private String macAddress;
	
	public String getMacAddress() throws SocketException, UnknownHostException {
		if (macAddress != null) return macAddress;
		
		byte[] mac = NetworkInterface.getByInetAddress(InetAddress.getLocalHost()).getHardwareAddress();

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < mac.length; i++) {
			sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
		}
		
		return sb.toString();
	}
}
