package org.usfirst.frc.team4525.robot.operate.autosystems.commands;

import org.usfirst.frc.team4525.robot.operate.SubsystemsManager;
import org.usfirst.frc.team4525.robot.operate.autosystems.Command;
import org.usfirst.frc.team4525.robot.operate.sensors.Sensor;
import org.usfirst.frc.team4525.robot.operate.sensors.SensorManager;
import org.usfirst.frc.team4525.robot.operate.subsystems.Drive;
import org.usfirst.frc.team4525.robot.util.PIDControl;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveDistanceToWall implements Command {

	private SubsystemsManager systems = SubsystemsManager.getInstance();
	private SensorManager sensors = SensorManager.getInstance();
	private PIDControl pid;

	private Drive drive_train = systems.getDriveTrain();
	private Sensor gyro = sensors.getGyro();
	private Sensor encode = sensors.getDriveEncoder();
	private Sensor sonic = sensors.getFrontRange();
	// private Sensor sonic =
	// SensorManager.getInstance().getDistanceFromMiddle();

	private boolean finished = false;
	private boolean stop = false;
	private boolean start = false;

	private double distance;
	private double headingTarget;

	private final double wait_time_before_start = 100;
	private double wait_time = 0;

	private PIDControl driveStrait;

	// IT WORKS - <3 eric

	public DriveDistanceToWall(double dist, double max_output) {
		distance = dist * -1;
		//
		pid = new PIDControl(0.4, 0.2, 0.5);
		pid.setOutputRampRate(0.05);
		pid.setOutputLimits(max_output);
		pid.setSetpointRange(10);
		//
		driveStrait = new PIDControl(0.0025, 0.0000001, 0.0005);// new
													// PIDControl(0.015,
		// 0.00001, 0.05); // 0.01,
		// 0, 0.05

		// 0.0025, 0.0000001, 0.0005
		driveStrait.setOutputLimits(0.4);
		driveStrait.setOutputRampRate(0.05);

		/*
		 * if (sonicOn == true) { isSonic = true; } else { isSonic = false; }
		 */
	}

	public void init() {
		encode.reset();
		//
		distance = encode.get() + distance;
		pid.setSetpoint(distance);
		drive_train.setDeadZone(0);
		drive_train.setDriveStrait(false);
		//
		headingTarget = gyro.get();
		driveStrait.setSetpoint(headingTarget);
		start = true;
	}

	public void execute() {

		double output = Math.abs(encode.get());
		if (output == 0)
			output = 1;
		SmartDashboard.putString("distance driven:", Double.toString(output));
		SmartDashboard.putString("encoder actual:", Double.toString(encode.get()));

		if (Math.abs(distance) > output && (sonic.get() > 14)) {
			double power = Math.abs(pid.getOutput(output));
			double offset = driveStrait.getOutput(gyro.get());

			if (distance < 0)
				power = power * -1;

			SmartDashboard.putString("power for drive:", Double.toString(power));

			drive_train.drive(power, offset);

		} else {
			finished = true;
		}

		// if (sonic.get() > 11) {
		// drive_train.drive(power, offset);
		// } else {
		// drive_train.stop();
		// finished = true;
		// }

		/*
		 * if (sonic.get() <= 11 && isSonic == true) { drive_train.stop();
		 * finished = true; } else { drive_train.drive(power, offset); }
		 */

	}

	public boolean isFinished() {
		return finished;
	}

	public void end() {
		drive_train.stop();
	}

	public void stop() {
		stop = true;
	}

	@Override
	public boolean interupted() {
		return stop;
	}

	@Override
	public boolean started() {
		return start;
	}
}
