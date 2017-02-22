package org.usfirst.frc.team4525.robot.control.auto.red;

import org.usfirst.frc.team4525.robot.control.Controller;
import org.usfirst.frc.team4525.robot.operate.autosystems.CommandManager;
import org.usfirst.frc.team4525.robot.operate.autosystems.commands.DriveDistanceToWall;
import org.usfirst.frc.team4525.robot.operate.autosystems.commands.DriveStraight;
import org.usfirst.frc.team4525.robot.operate.autosystems.commands.OpenClamp;
import org.usfirst.frc.team4525.robot.operate.autosystems.commands.PushGear;
import org.usfirst.frc.team4525.robot.operate.autosystems.commands.Rotate;
import org.usfirst.frc.team4525.robot.operate.autosystems.commands.Wait;
import org.usfirst.frc.team4525.robot.util.DashUtil;

public class DropGearMiddle implements Controller {

	// DONE AND WORKING

	private CommandManager commands = CommandManager.getInstance();

	@Override
	public void start() {
		// TODO Auto-generated method stub
		commands.start();
		try {
			commands.queSequential(new DriveDistanceToWall(88, 0.45));
			// drop gear
			commands.queSequential(new Wait(0.5));
			commands.queSequential(new OpenClamp());
			commands.queSequential(new Wait(1));
			commands.queSequential(new PushGear());
			commands.queSequential(new Wait(1));
			//
			commands.queSequential(new DriveStraight(-48, 0.5));
			commands.queSequential(new Rotate(-45));
		} catch (NullPointerException e) {
			DashUtil.getInstance().log("Error with automode, command was null :(");
		}

	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub

	}

}
