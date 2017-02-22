package org.usfirst.frc.team4525.robot.control.auto.blue;

import org.usfirst.frc.team4525.robot.control.Controller;
import org.usfirst.frc.team4525.robot.operate.autosystems.CommandManager;
import org.usfirst.frc.team4525.robot.operate.autosystems.commands.DriveStraight;
import org.usfirst.frc.team4525.robot.operate.autosystems.commands.Rotate;
import org.usfirst.frc.team4525.robot.operate.autosystems.commands.Wait;
import org.usfirst.frc.team4525.robot.util.DashUtil;

public class CrossBaseMiddle implements Controller {

	// DONE AND WORKS

	private CommandManager commands = CommandManager.getInstance();

	@Override
	public void start() {
		// TODO Auto-generated method stub
		try {
			commands.queSequential(new DriveStraight(12, 0.7));
			commands.queSequential(new Rotate(50));
			commands.queSequential(new DriveStraight(80, 0.7));
			commands.queSequential(new Rotate(-50));
			commands.queSequential(new DriveStraight(150, 0.7));

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
