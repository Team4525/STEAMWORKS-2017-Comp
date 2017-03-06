package org.usfirst.frc.team4525.robot.operate.subsystems.impl;

import org.usfirst.frc.team4525.robot.operate.sensors.Sensor;
import org.usfirst.frc.team4525.robot.operate.sensors.SensorManager;
import org.usfirst.frc.team4525.robot.operate.subsystems.*;
import org.usfirst.frc.team4525.robot.util.PIDControl;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveImpl implements Drive {

	// definitions:
	private VictorSP left[];
	private VictorSP right[];

	//
	private double deadzone = 0;

	// 
	private Sensor voltage;
	
	// Drive Strait
	private Sensor gyro;
	private boolean gyroReset = false;
	private double offset = 0;

	//
	private boolean drive_strait = false;
	//
	private PIDControl driveStrait;

	//
	private final double strait_correct_time = 1200; // in ns
	private double current_correct_time = 0;
	private double correct_time = 0;

	// Keep heading:

	public void init() {
		left = new VictorSP[] { new VictorSP(0), new VictorSP(1) };
		right = new VictorSP[] { new VictorSP(2), new VictorSP(3) };
		//
		gyro = SensorManager.getInstance().getGyro();
		
		//
		voltage = SensorManager.getInstance().getVoltage();

		// Set the PID loop for the drive strait logic

		driveStrait = new PIDControl(0.0025, 0.0000001, 0.0005); // 0.01, 0,
																	// 0.05
		driveStrait.setOutputLimits(0.4);
		driveStrait.setOutputRampRate(0.05);

	}

	public void setStraitTarget(double tgt) {
		gyroReset = true;
		driveStrait.setSetpoint(tgt);
	}

	// Tells the drivetrain whether it is in drive strait mode or just letting
	// the driver correct it
	public void setDriveStrait(boolean v) {
		drive_strait = v;
	}

	// Sets the minimum output value otherwise the motors are 0
	public void setDeadZone(double dz) {
		deadzone = dz;
	}

	// Arcade drive
	public void drive(double power, double off) {
		double l = 0;
		double r = 0;

		if (Math.abs(power) < deadzone)
			power = 0;

		// button stupid thing
		// end of button thing

		// Whether we are driving strait or not
		if (drive_strait == true && (Math.abs(off) <= deadzone && Math.abs(power) > 0)) {
			if (gyroReset == false) {
				if (current_correct_time == 0) {
					current_correct_time = Math.abs(power) * strait_correct_time;
					correct_time = 0;
				} else if (correct_time >= current_correct_time) {
					offset = gyro.get();
					driveStrait.setSetpoint(offset);
					gyroReset = true;
					current_correct_time = 0;
				} else {
					correct_time++;
				}
			} else { // Looks like we determine the offset value! We're going
						// strait.
				double slip = gyro.get();
				off = driveStrait.getOutput(slip);
			}
		} else { // Let's the driver determine the offset.
			off = off * 0.7;
			gyroReset = false;
			current_correct_time = 0;
		}

		if (off > 0 && Math.abs(power) > 0.5) {
			off = off * 0.4; // take 70% of 70%
		}
		
		if(off != 0 || voltage.get() < 7)  
			power = power * 0.8;
		/*
		 * SmartDashboard.putString("Encoder:", Double.toString(encoder.get()));
		 * SmartDashboard.putString("DriveStrait Correction:",
		 * Double.toString(off)); SmartDashboard.putString("Gyro Angle:",
		 * Double.toString(gyro.get()));
		 * SmartDashboard.putString("Gyro Difference",
		 * Double.toString(gyro.get() - offset));
		 * SmartDashboard.putString("Where You Should be",
		 * Double.toString(offset));
		 * SmartDashboard.putString("Distance from front",
		 * Double.toString(sideSonic.get()));
		 */
		// SmartDashboard.putString("POWER", Double.toString(power));

		if (off > 0) {
			if (power < 0) { // We want to go left
				l = off - power; // powerset the left drive motors
				r = Math.max(off, power); // Maximize off on the right drive
											// motors
			} else {
				l = Math.max(off, -power);
				r = off + power; // could overflow 1
			}
		} else {
			if (power >= 0) {
				l = -Math.max(-off, power);
				r = off + power;
			} else {
				l = off - power;
				r = -Math.max(-off, -power);
			}
		}
		setLeft(l);
		setRight(r);
	}

	// If we want to tank drive for some reason instead
	public void tank(double lspeed, double rspeed) {
		if (Math.abs(lspeed) < deadzone)
			lspeed = 0;
		if (Math.abs(rspeed) < deadzone)
			rspeed = 0;
		//
		setLeft(lspeed);
		setRight(rspeed);
	}

	// Stops the drive train
	public void stop() {
		setLeft(0);
		setRight(0);
		gyroReset = false;
	}

	// ----- Sets power to the motors ------ \\

	private void setLeft(double pow) {
		for (VictorSP motor : left) {
			motor.set(pow);
			// SmartDashboard.putString("left power", Double.toString(pow));
		}
	}

	private void setRight(double pow) {
		for (VictorSP motor : right) {
			motor.set(pow);
			// SmartDashboard.putString("right power", Double.toString(pow));
		}
	}

}