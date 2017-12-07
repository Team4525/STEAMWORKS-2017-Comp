package org.usfirst.frc.team4525.robot.operate.sensors.impl;

import org.usfirst.frc.team4525.robot.operate.sensors.Sensor;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.ITable;

public class Vision implements Sensor {

	private NetworkTable tbl;
	private ITable conts;
	// Create network table

	private double[] cont = {};
	// Creates contour array

	private final double camera_offset_x = 0;
	// Offset from center

	@Override
	public void init() {
		tbl = NetworkTable.getTable("GRIP");
		conts = tbl.getSubTable("Contours");
		// Get the network table and contours from outside and assign tbl and conts to
		// them, respectively.

	}

	@Override
	public double get() {//Actually locate the target.  
		double[] contours = conts.getNumberArray("centerX", cont);
		//Redefine contours in terms of "centerX"
		
		double targetCenter = 0;

		SmartDashboard.putString("Contours:", Integer.toString(contours.length));

		if (contours.length >= 2) {
			double shapeCenter = Math.abs(contours[0] + contours[1]) / 2;
			targetCenter = shapeCenter + camera_offset_x;
		} else if (contours.length == 1) {
			targetCenter = contours[0] + camera_offset_x;
		}
		SmartDashboard.putString("Camera Offset", Double.toString(targetCenter));
		return targetCenter;
	}

	@Override
	public void calibrate() {
		//From the interface
	}

	@Override
	public void reset() {
		//From the interface
	}

}
