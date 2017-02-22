package org.usfirst.frc.team4525.robot.operate.sensors.impl;

import org.usfirst.frc.team4525.robot.operate.sensors.Sensor;

import edu.wpi.first.wpilibj.DigitalInput;

public class GearSwitch implements Sensor {

	private DigitalInput gear_switch;

	public void init() {
		gear_switch = new DigitalInput(4);
	}

	public double get() {
		if (gear_switch.get() == false) {
			return 4525;
		} else {
			return 0000;
		}
	}

	public void calibrate() {

	}

	public void reset() {

	}

}
