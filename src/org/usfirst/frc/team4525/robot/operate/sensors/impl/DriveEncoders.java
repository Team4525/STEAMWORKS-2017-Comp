package org.usfirst.frc.team4525.robot.operate.sensors.impl;

import org.usfirst.frc.team4525.robot.operate.sensors.Sensor;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;

public class DriveEncoders implements Sensor {

	private Encoder encode;

	public void init() {
		encode = new Encoder(0, 1, false, EncodingType.k4X);
		encode.setDistancePerPulse(0.057); // DO NOT CHANGE THIS VALUE
	}

	@Override
	public double get() {
		return encode.getDistance();
	}

	@Override
	public void calibrate() {
		// nothing here
	}

	@Override
	public void reset() {
		encode.reset();
	}

}
