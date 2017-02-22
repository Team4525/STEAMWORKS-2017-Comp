package org.usfirst.frc.team4525.robot.operate.sensors.impl;

import org.usfirst.frc.team4525.robot.operate.sensors.Sensor;

import edu.wpi.first.wpilibj.Ultrasonic;

public class MiddleSonic implements Sensor {

	private Ultrasonic ultra;

	public void init() {
		ultra = new Ultrasonic(7, 8);
		ultra.setEnabled(true);
		ultra.setAutomaticMode(true);
	}

	public double get() {
		if ((ultra.getRangeInches() - 6.75) < 0) {
			return 0;
		} else {
			return ultra.getRangeInches() - 6.75;
		}
	}

	public void calibrate() {

	}

	public void reset() {

	}

}
