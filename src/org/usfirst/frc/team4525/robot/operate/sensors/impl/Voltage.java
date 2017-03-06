package org.usfirst.frc.team4525.robot.operate.sensors.impl;

import org.usfirst.frc.team4525.robot.operate.sensors.Sensor;

import edu.wpi.first.wpilibj.DriverStation;

public class Voltage implements Sensor {

	private DriverStation ds;
	
	public void init() {
		ds = DriverStation.getInstance();
	}

	// return ds voltage
	public double get() {
		return ds.getBatteryVoltage();
	}
	
	public void calibrate() {
		// no logic here either
	}

	public void reset() {
		// no logic here
	}

}
