package org.usfirst.frc.team4525.robot.operate.sensors.impl;

import org.usfirst.frc.team4525.robot.operate.sensors.Sensor;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;

public class Gyro implements Sensor {

	private ADXRS450_Gyro gyro;

	public void init() {
		gyro = new ADXRS450_Gyro();
		calibrate();
		reset();
		//Declare, calibrate, and reset the gyro.  
	}
	
	public double get() {
		return gyro.getAngle();
		//Get and return the current gyro angle
	}
	
	public void calibrate() {
		gyro.calibrate();
		//Calibrate the gyro
	}
	
	public void reset() {
		gyro.reset();
		//Reset the gyro
	}
	
}
