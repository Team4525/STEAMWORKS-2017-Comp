package org.usfirst.frc.team4525.robot.operate.autosystems.commands;

import org.usfirst.frc.team4525.robot.operate.SubsystemsManager;
import org.usfirst.frc.team4525.robot.operate.autosystems.Command;
import org.usfirst.frc.team4525.robot.operate.sensors.Sensor;
import org.usfirst.frc.team4525.robot.operate.sensors.SensorManager;
import org.usfirst.frc.team4525.robot.operate.sensors.impl.Vision;
import org.usfirst.frc.team4525.robot.operate.subsystems.Drive;
import org.usfirst.frc.team4525.robot.operate.subsystems.Visioning;
import org.usfirst.frc.team4525.robot.util.DashUtil;
import org.usfirst.frc.team4525.robot.util.PIDControl;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GearFinder implements Command {

	private SubsystemsManager systems = SubsystemsManager.getInstance();

	private Drive dt = systems.getDriveTrain();
	private Sensor visTarget = SensorManager.getInstance().getVision();
	private Sensor sonic = SensorManager.getInstance().getFrontRange();
	private Sensor gyro = SensorManager.getInstance().getGyro();

	private double visOff = 0;

	private PIDControl pid;
	private PIDControl dist_pid;
	private final float anglePerPixel = 0.140094f; // 45/root(640^2 + 320^2)
	private double angle = 0;

	private boolean hasTarg = false;
	//
	private boolean finished = false;
	private boolean started = false;

	@Override
	public void init() {
		//
		pid = new PIDControl(0.2, 0.01, 0.05); // DO NOT CHANGE THESE
		pid.setOutputRampRate(0.05);
		pid.setOutputLimits(0.6);
		pid.setSetpointRange(10);
		//
		dist_pid = new PIDControl(0.4, 0.2, 0.5);
		dist_pid.setOutputRampRate(0.05);
		dist_pid.setOutputLimits(0.5);
		dist_pid.setSetpointRange(10);
		dist_pid.setSetpoint(5);
		//
		started = true;
	}

	private boolean found_target = false;

	private void findTarget() {
		visOff = visTarget.get();
		if (visOff != 0) {
			angle = Math.abs(visOff - 320) * anglePerPixel;

			if (visOff < 320)
				angle = -angle;
			pid.setSetpoint(gyro.get() + angle);
			found_target = true;
		}
	}

	@Override
	public void execute() {
		if (found_target == false) {
			findTarget();
		} else if (visOff != 0) {
			double dist = sonic.get();
			double power = dist_pid.getOutput(dist);
			double off = 0;
			SmartDashboard.putString("vision", "Active");
			if (dist < 40) {
				off = 0;
			} else {
				off = pid.getOutput(gyro.get());
			}

			if (dist < 15 && dist > 0) {
				finished = true;
			}
			dt.drive(power, off);
		} else {
			dt.drive(0, 0);
		}

	}

	@Override
	public boolean isFinished() {
		// TODO Auto-generated method stub
		return finished;
	}

	@Override
	public void end() {
		dt.stop();
	}

	@Override
	public void stop() {
		finished = true;
	}

	@Override
	public boolean interupted() {
		return false;
	}

	@Override
	public boolean started() {
		// TODO Auto-generated method stub
		return started;
	}

}
