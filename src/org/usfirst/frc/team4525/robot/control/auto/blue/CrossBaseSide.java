package org.usfirst.frc.team4525.robot.control.auto.blue;

import org.usfirst.frc.team4525.robot.control.Controller;
import org.usfirst.frc.team4525.robot.operate.autosystems.CommandManager;
import org.usfirst.frc.team4525.robot.operate.autosystems.commands.DriveStraight;
import org.usfirst.frc.team4525.robot.util.DashUtil;

/* DONE AND WORKING */

public class CrossBaseSide implements Controller {

	private CommandManager commands = CommandManager.getInstance();

	@Override
	public void start() {
		// TODO Auto-generated method stub
		try {
			commands.queSequential(new DriveStraight(250, 0.7));

		} catch (NullPointerException e) {
			DashUtil.getInstance().log("Error with automode, command was null :(");
		}

	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		commands.stop();
	}

}
