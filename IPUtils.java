package com.wisely;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

/**
 * @author zhangyc
 * 2017-09-11
 */
public class IPUtils {
	
	/**
	 * 获得本地iplist
	 * @return
	 * @throws SocketException
	 */
	public static Set<String> getLocalIpList() throws SocketException{
		Set<String> set = new HashSet<String>();
		Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
		while(networkInterfaces.hasMoreElements()){
			NetworkInterface ni=(NetworkInterface)networkInterfaces.nextElement();
			Enumeration<InetAddress> ia = ni.getInetAddresses();
			while(ia.hasMoreElements()){
				InetAddress	ip=	(InetAddress)ia.nextElement();
				if(!ip.isLoopbackAddress()&&ip.getHostAddress().indexOf(":")==-1){
					set.add(ip.getHostAddress());
				}
			}
		}
		return set;
		
	}
	
	/**
	 * 获得hosts文件下的ip
	 * @return
	 * @throws UnknownHostException
	 */
	public static String host() throws UnknownHostException{
		InetAddress byName = InetAddress.getByName("HostName");
		return byName.getHostAddress();
		
	}
	public static void main(String[] args) throws SocketException {
		Set<String> localIpList = getLocalIpList();
		for (String string : localIpList) {
			System.out.println(string);
		}
	}
}
