package org.usfirst.frc.team4525.robot.operate.sensors;

import org.usfirst.frc.team4525.robot.operate.sensors.impl.*;
import org.usfirst.frc.team4525.robot.util.DashUtil;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SensorManager {

	private static SensorManager instance = new SensorManager();

	public static SensorManager getInstance() {
		return instance;
	}

	// Sensors:
	private Sensor gyro;
	private Sensor encoder;
	private Sensor sonic_left;
	// private Sensor sonic_middle;
	private Sensor voltage;
	//	private Sensor gear_switch;
	private Sensor vision;

	// Getters

	public Sensor getVision() {
		return vision;
	}
	
	public Sensor getVoltage() {
		return voltage;
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

	// This switch has been problematic.
	/*
	public Sensor getGearOrientation() {
		return gear_switch;
	} 
	*/ 

	// public Sensor getDistanceFromMiddle() {
	// return sonic_middle;
	// }

	private SensorManager() {
		DashUtil.getInstance().log("Initiating Sensors");
		gyro = new Gyro();
		encoder = new DriveEncoders();
		sonic_left = new Proximity();
		//gear_switch = new GearSwitch();
		vision = new Vision();
		voltage = new Voltage();
		// sonic_middle = new MiddleSonic();
		//
		gyro.init();
		encoder.init();
		sonic_left.init();
		voltage.init();
		// sonic_middle.init();
		vision.init();
	//	gear_switch.init();
		//
		DashUtil.getInstance().log("Sensors online.");
		//
	}

}
