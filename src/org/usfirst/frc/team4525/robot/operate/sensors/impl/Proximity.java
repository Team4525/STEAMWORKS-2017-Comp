package org.usfirst.frc.team4525.robot.operate.sensors.impl;

import org.usfirst.frc.team4525.robot.operate.sensors.Sensor;

import edu.wpi.first.wpilibj.Ultrasonic;

public class Proximity implements Sensor {

	private Ultrasonic sonic;

	public void init() {
		sonic = new Ultrasonic(6, 5);
		sonic.setEnabled(true);
		sonic.setAutomaticMode(true);
	}

	public double get() {
		if ((sonic.getRangeInches() - 3) < 0) {
			return 0;
		} else {
			return sonic.getRangeInches() - 3;
		}
	}

	public void calibrate() {
		// nothing to calibrate
	}

	public void reset() {

	}

}
