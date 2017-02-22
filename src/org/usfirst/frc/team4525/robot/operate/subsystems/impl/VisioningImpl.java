package org.usfirst.frc.team4525.robot.operate.subsystems.impl;

import org.usfirst.frc.team4525.robot.operate.SubSystem;
import org.usfirst.frc.team4525.robot.operate.SubsystemsManager;
import org.usfirst.frc.team4525.robot.operate.sensors.Sensor;
import org.usfirst.frc.team4525.robot.operate.sensors.SensorManager;
import org.usfirst.frc.team4525.robot.operate.subsystems.Drive;
import org.usfirst.frc.team4525.robot.operate.subsystems.Visioning;
import org.usfirst.frc.team4525.robot.util.DashUtil;
import org.usfirst.frc.team4525.robot.util.PIDControl;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.ITable;

public class VisioningImpl implements Visioning {

	private Sensor sonic = SensorManager.getInstance().getFrontRange();
	private Sensor vision = SensorManager.getInstance().getVision();
	private Sensor gyro = SensorManager.getInstance().getGyro();

	private Drive dt;
	private double rotation_angle = 0;

	public VisioningImpl(Drive drive_train) {
		dt = drive_train;
	}

	// private Drive dt;

	private double dist = 0;
	private final double imgCenterX = 320;

	private double[] cont = {};
	private final double pi = Math.PI;

	private boolean run = false;
	private PIDControl pid;
	private PIDControl dist_pid;
	private final float anglePerPixel = 0.140094f; // 45/root(640^2 + 320^2)

	public void init() {
		pid = new PIDControl(0.01, 0, 0.05); // DO NOT CHANGE THESE
		pid.setOutputRampRate(0.05);
		pid.setOutputLimits(0.4);
		pid.setSetpointRange(10);

		dist_pid = new PIDControl(0.4, 0.2, 0.5);
		dist_pid.setOutputRampRate(0.05);
		dist_pid.setOutputLimits(0.5);
		dist_pid.setSetpointRange(10);
	}

	public void goToTarget() {
		if (run == false) {
			run = true;
			new Thread(new Runnable() {
				public void run() {
					dist_pid.setSetpoint(5);

					//
					//
					DashUtil.getInstance().log("Correcting Vision");

					double dist = 0;
					double power = 0;
					double off = 0;

					boolean centered = false;

					double visOff = 0;
					while (run == true && visOff == 0) {
						visOff = vision.get();
					}

					double angle = Math.abs(visOff - 320) * anglePerPixel;

					if (visOff < 320)
						angle = -angle;
					pid.setSetpoint(gyro.get() + angle);

					DashUtil.getInstance().log("Going for target!");

					while (run == true) {

						dist = sonic.get();
						power = dist_pid.getOutput(dist);
						if (visOff != 0) {
							SmartDashboard.putString("vision", "Active");
							if (dist < 40) {
								off = 0;
							} else {
								off = pid.getOutput(gyro.get());
							}

							if (dist < 8 && dist > 0) {
								DashUtil.getInstance().log("Range too close stopping vision!");
								break;
							}
							dt.drive(power, off);
						} else {
							SmartDashboard.putString("vision", "No Target");
							dt.drive(power, 0);
						}
						SmartDashboard.putString("Vision Distance", Double.toString(dist));
						SmartDashboard.putString("Vision Drive Power", Double.toString(power));
						SmartDashboard.putString("Vision Correct:", Double.toString(off));
					}
					//

					//
					dt.drive(0, 0);
					DashUtil.getInstance().log("Not Correcting Vision");
					SmartDashboard.putString("vision", "Inactive");
				}
			}).start();
		}
	}

	public void stop() {
		run = false;
	}

}
