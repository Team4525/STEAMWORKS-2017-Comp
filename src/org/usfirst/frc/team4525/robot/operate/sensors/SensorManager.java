package org.usfirst.frc.team4525.robot.operate.sensors;

import org.usfirst.frc.team4525.robot.operate.sensors.impl.DriveEncoders;
import org.usfirst.frc.team4525.robot.operate.sensors.impl.Gyro;
import org.usfirst.frc.team4525.robot.operate.sensors.impl.Proximity;
import org.usfirst.frc.team4525.robot.operate.sensors.impl.Vision;
import org.usfirst.frc.team4525.robot.util.DashUtil;

public class SensorManager {

	private static SensorManager instance = new SensorManager();

	public static SensorManager getInstance() {
		return instance;
	}

	// Sensors:
	private Sensor gyro;
	private Sensor encoder;
	private Sensor sonic_left;
	private Sensor vision;

	// Getters

	public Sensor getVision() {
		return vision;
	}

	public Sensor getGyro() {
		return gyro;
	}

	public Sensor getDriveEncoder() {
		return encoder;
	}

	public Sensor getFrontRange() {
		return sonic_left;
	}

	private SensorManager() {
		DashUtil.getInstance().log("Initiating Sensors");
		gyro = new Gyro();
		encoder = new DriveEncoders();
		sonic_left = new Proximity();
		vision = new Vision();
		//
		gyro.init();
		encoder.init();
		sonic_left.init();
		vision.init();
		//
		DashUtil.getInstance().log("Sensors online.");
		//
	}

}
