package org.usfirst.frc.team4525.robot.operate.sensors.impl;

import org.usfirst.frc.team4525.robot.operate.sensors.Sensor;

import edu.wpi.first.wpilibj.Ultrasonic;

public class Proximity implements Sensor {

	private Ultrasonic sonic;

	public void init() {
		sonic = new Ultrasonic(6, 5);
		sonic.setEnabled(true);
		sonic.setAutomaticMode(true);
		//Declare and enable the ultrasonic sensor, and set it to automatic mode
	}

	public double get() {
		if ((sonic.getRangeInches() - 3) < 0) {
			return 0;
		} else {
			return sonic.getRangeInches() - 3;
		}
		//Returns the number of inces between the front of the robot and the closest object.  
	}

	public void calibrate() {
		//From the interface
	}

	public void reset() {
		//From the interface
	}

}
