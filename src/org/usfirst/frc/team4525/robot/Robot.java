package org.usfirst.frc.team4525.robot;

import org.usfirst.frc.team4525.robot.control.Controller;
import org.usfirst.frc.team4525.robot.control.teleop.Driver;
import org.usfirst.frc.team4525.robot.control.teleop.Mech;
import org.usfirst.frc.team4525.robot.operate.SubsystemsManager;
import org.usfirst.frc.team4525.robot.operate.autosystems.CommandManager;
import org.usfirst.frc.team4525.robot.util.DashUtil;

import edu.wpi.first.wpilibj.SampleRobot;

public class Robot extends SampleRobot {

	// Initialize controllers, dashboard, commandmanager, and auto-chooser, DashUtil
	private Controller drive, mech;
	private Controller auto;
	private DashUtil du;
	private CommandManager commands;

	public Robot() {
		// Declare DashUtil
		du = DashUtil.getInstance();
		du.log("Loading Steamworks2k17...");
		// Declare commands
		commands = CommandManager.getInstance();
		// Declare Driver and Mechanism controllers
		drive = new Driver();
		mech = new Mech();
		//
	}

	public void robotInit() {

	}

	public void autonomous() {
		du.log("Starting autonomous mode");
		du.log("Running " + auto.toString());
		//If auto is running, start autonomous and commands
		if (auto != null) {
			auto.start();
			commands.start();
		}
	}

	public void operatorControl() {
		//Clear the DashUtil from auto, start the compressor, and start the drive train and mechanisms
		DashUtil.getInstance().clear();
		DashUtil.getInstance().log("Starting operator mode.");
		SubsystemsManager.getInstance().getPneumatics().startCompressor();
		drive.start();
		mech.start();

	}

	public void test() {

	}

	protected void disabled() {
		// Stop the DriveTrain and Compressor
		drive.stop();
		SubsystemsManager.getInstance().getPneumatics().stopCompressor();
		// If the autonomous is still running, stop it
		if (auto != null)
			auto.stop();
		// Stop any running commands
		commands.stop();
		//
	}
}
