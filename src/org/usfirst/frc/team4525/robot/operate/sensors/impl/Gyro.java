package org.usfirst.frc.team4525.robot.operate.sensors.impl;

import org.usfirst.frc.team4525.robot.operate.sensors.Sensor;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;

public class Gyro implements Sensor {

	private ADXRS450_Gyro gyro;

	public void init() {
		gyro = new ADXRS450_Gyro();
		calibrate();
		reset();
	}
	
	public double get() {
		return gyro.getAngle();
	}
	
	public void calibrate() {
		gyro.calibrate();
	}
	
	public void reset() {
		gyro.reset();
	}
	
}
