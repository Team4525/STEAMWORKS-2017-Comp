package org.usfirst.frc.team4525.robot.operate.subsystems.impl;

import org.usfirst.frc.team4525.robot.operate.sensors.Sensor;
import org.usfirst.frc.team4525.robot.operate.sensors.SensorManager;
import org.usfirst.frc.team4525.robot.operate.subsystems.Drive;
import org.usfirst.frc.team4525.robot.util.PIDControl;

import edu.wpi.first.wpilibj.VictorSP;

public class DriveImpl implements Drive {

	// definitions:
	private VictorSP left[];
	private VictorSP right[];

	//
	private double deadzone = 0;

	// Drive Strait
	private Sensor gyro;
	private boolean gyroReset = false;
	private double offset = 0;

	//
	private boolean drive_strait = false;
	// PID loop
	private PIDControl driveStrait;

	// Drive strait
	private final double strait_correct_time = 1200;
	private double current_correct_time = 0;
	private double correct_time = 0;

	// Keep heading:

	public void init() {
		left = new VictorSP[] { new VictorSP(0), new VictorSP(1) };
		right = new VictorSP[] { new VictorSP(2), new VictorSP(3) };
		// Define right and left drive sides as arrays of victors
		gyro = SensorManager.getInstance().getGyro();

		// Set the PID loop for the drive strait logic

		driveStrait = new PIDControl(0.0025, 0.0000001, 0.0005);
		driveStrait.setOutputLimits(0.4);
		driveStrait.setOutputRampRate(0.05);

	}

	// Reset the gyro and set the current heading to its value
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

		// Ensure that the power level doesn't exceed are deadzone to cut motor whine
		if (Math.abs(power) < deadzone)
			power = 0;

		// Whether we are driving strait or not
		if (drive_strait && (Math.abs(off) <= deadzone && Math.abs(power) > 0)) {
			if (!gyroReset) {
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
			} else {
				double slip = gyro.get();
				off = driveStrait.getOutput(slip);
			}
		} else { // Lets the driver determine the offset.
			off = off * 0.7;
			gyroReset = false;
			current_correct_time = 0;
		}

		if (off > 0 && Math.abs(power) > 0.5) {
			off = off * 0.4;
		}

		power = power * 0.8;

		if (off > 0) {// We want to go left
			if (power < 0) {
				l = off - power; // set the left drive motors
				r = Math.max(off, power); // Maximize off on the right drive
											// motors
			} else {// We're going backwards
				l = Math.max(off, -power);
				r = off + power;
			}
		} else {// We want to go right
			if (power >= 0) {
				l = off + power;// Set the right drive motors
				r = -Math.max(-off, power);// Maximize offset on the left drive motors
			} else {// We're going backwards
				l = off - power;
				r = -Math.max(-off, -power);
			}
		}
		setLeft(l);
		setRight(r);
		// Set the right and left motors to the variables l and r
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
		}
	}

	private void setRight(double pow) {
		for (VictorSP motor : right) {
			motor.set(pow);
		}
	}

}