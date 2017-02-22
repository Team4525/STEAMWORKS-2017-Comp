package org.usfirst.frc.team4525.robot.operate.sensors.impl;

import org.usfirst.frc.team4525.robot.operate.sensors.Sensor;
import org.usfirst.frc.team4525.robot.util.PIDControl;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.ITable;

public class Vision implements Sensor {

	private NetworkTable tbl;
	private ITable conts;	
	
	private double[] cont = {};
	
	private final double image_center_x = 320;
	private final double camera_offset_x = 0;

	
	@Override
	public void init() {
		tbl = NetworkTable.getTable("GRIP");
		conts = tbl.getSubTable("Contours");

	}

	@Override
	public double get() {
		double[] contours = conts.getNumberArray("centerX",cont);
		double targetCenter = 0;
		
		SmartDashboard.putString("Contours:", Integer.toString(contours.length));
		
		if(contours.length >= 2) {
			double diff = Math.abs(contours[0] - contours[1]);
		//	if(diff <= 200) {
				double shapeCenter = Math.abs(contours[0] + contours[1])/2;
				targetCenter = shapeCenter+camera_offset_x;
		//	} else {
		//		return 0;
		//	}
		} else if(contours.length == 1){
			targetCenter = contours[0]+camera_offset_x;
		}
		SmartDashboard.putString("Camera Offset", Double.toString(targetCenter));
		return targetCenter;
	}

	@Override
	public void calibrate() {
		// TODO Auto-generated method stub

	}

	@Override
	public void reset() {

	}

}
