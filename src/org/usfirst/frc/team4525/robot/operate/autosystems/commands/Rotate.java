package org.usfirst.frc.team4525.robot.operate.autosystems.commands;

import org.usfirst.frc.team4525.robot.operate.SubsystemsManager;
import org.usfirst.frc.team4525.robot.operate.autosystems.Command;
import org.usfirst.frc.team4525.robot.operate.sensors.Sensor;
import org.usfirst.frc.team4525.robot.operate.sensors.SensorManager;
import org.usfirst.frc.team4525.robot.operate.subsystems.Drive;
import org.usfirst.frc.team4525.robot.util.PIDControl;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Rotate implements Command {

	private SubsystemsManager systems = SubsystemsManager.getInstance();
	private SensorManager sensors = SensorManager.getInstance();
	private PIDControl pid;

	private Drive drive_train = systems.getDriveTrain();
	private Sensor gyro = sensors.getGyro();

	private boolean finished = false;
	private boolean stopped = false;
	private boolean start = false;

	// Command specific
	private double target_point = 0;

	// range cycles
	private double range_ramp = 100;
	private double range_count = 0;
	private double range = 10;
	private double deg = 0;

	public Rotate(double degrees) {
		deg = degrees;
		pid = new PIDControl(0.08, 0.002, 0.05); // DO NOT CHANGE THESE
		pid.setOutputRampRate(0.05);
		pid.setOutputLimits(0.6);
		pid.setSetpointRange(10);
	}

	public void init() {
		target_point = gyro.get() + deg;
		drive_train.setDeadZone(0);
		pid.setSetpoint(target_point);
		SmartDashboard.putString("Target point", Double.toString(target_point));
		start = true;
	}

	public void execute() {
		double gv = gyro.get();
		if (inRange(gv) == false) {
			double rot_power = pid.getOutput(gv);

			SmartDashboard.putString("Target Progress", Double.toString(gv));
			//
			drive_train.drive(0, rot_power);
		} else {
			finished = true;
		}
	}

	public boolean isFinished() {
		return finished;
	}

	public void end() {
		drive_train.stop();
	}

	public void stop() {
		stopped = true;
	}

	public boolean interupted() {
		return stopped;
	}

	public boolean started() {
		return start;
	}

	private boolean inRange(double gval) {
		if (gval > target_point - range && gval < target_point + range) {
			range_count++;
			return range_count >= range_ramp;
		}
		range_count = 0;

		return false;
	}

}