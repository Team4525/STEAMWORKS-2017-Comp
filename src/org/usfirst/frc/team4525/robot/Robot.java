package org.usfirst.frc.team4525.robot;

import org.usfirst.frc.team4525.robot.control.Controller;
import org.usfirst.frc.team4525.robot.control.teleop.Driver;
import org.usfirst.frc.team4525.robot.control.teleop.Mech;
import org.usfirst.frc.team4525.robot.operate.SubsystemsManager;
import org.usfirst.frc.team4525.robot.operate.autosystems.CommandManager;
import org.usfirst.frc.team4525.robot.operate.sensors.SensorManager;
import org.usfirst.frc.team4525.robot.util.AutoChooser;
import org.usfirst.frc.team4525.robot.util.DashUtil;

import edu.wpi.first.wpilibj.SampleRobot;

public class Robot extends SampleRobot {

	private Controller drive, mech;
	private Controller auto;
	private DashUtil du;

	private SensorManager sensors;
	private SubsystemsManager subsystems;
	private CommandManager commands;
	private AutoChooser autos;

	public Robot() {
		du = DashUtil.getInstance();
		du.log("Loading Steamworks2k17...");
		//
		sensors = SensorManager.getInstance();
		subsystems = SubsystemsManager.getInstance();
		commands = CommandManager.getInstance();
		//
		autos = new AutoChooser();
		//
		drive = new Driver();
		mech = new Mech();
		//
	}

	public void robotInit() {

	}

	public void autonomous() {
		du.log("Starting autonomous mode");

		auto = (Controller) autos.getSelectedAuto();
		du.log("Running " + auto.toString());
		commands.start();
		//
		if (auto != null) {
			auto.start();
		}
		//
		/*
		 * while(isEnabled()) { Network.put("boop", x); x++; Timer.delay(1); }
		 */
	}

	public void operatorControl() {
		DashUtil.getInstance().clear();
		DashUtil.getInstance().log("Starting operator mode.");
		SubsystemsManager.getInstance().getPneumatics().startCompressor();
		drive.start();
		mech.start();

	}

	public void test() {

	}

	protected void disabled() {
		// DashUtil.getInstance().clear();
		drive.stop();
		SubsystemsManager.getInstance().getPneumatics().stopCompressor();
		//
		if (auto != null)
			auto.stop();
		commands.stop();
		//
	}
}
