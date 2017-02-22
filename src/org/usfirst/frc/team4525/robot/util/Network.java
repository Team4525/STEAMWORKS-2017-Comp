package org.usfirst.frc.team4525.robot.util;

import java.util.Hashtable;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class Network {

	// Network library integrations for FRC visions: 
	
	private static NetworkTable table = NetworkTable.getTable("vision");
	
	public static void put(String k, double v) {
		table.putString(k, Double.toString(v));
	}
	
	public static double get(String key) {
		Hashtable<String, Double> ht = new Hashtable<String, Double>();
		
		double val = 0;
		if(ht.get(key) != null) {
			val = ht.get(key);
		}
		return val;
	}
}
