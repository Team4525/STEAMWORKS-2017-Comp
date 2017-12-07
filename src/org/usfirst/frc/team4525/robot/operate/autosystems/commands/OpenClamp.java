package org.usfirst.frc.team4525.robot.operate.autosystems.commands;

import org.usfirst.frc.team4525.robot.operate.SubsystemsManager;
import org.usfirst.frc.team4525.robot.operate.autosystems.Command;
import org.usfirst.frc.team4525.robot.operate.subsystems.*;

public class OpenClamp implements Command {
	//Declare objects
	private Clamp clamp;
	private boolean finished = false;
	private boolean started = false;
	
	public void init() {//Initialize the clamp
		clamp = SubsystemsManager.getInstance().getClamp();
		started = true;
	}

	@Override
	public void execute() {//Open the clamp
		clamp.ungrip();
		finished = true;
	}

	@Override
	public boolean isFinished() {
		return finished;
	}

	public void end() {
	}

	@Override
	public void stop() {
	}

	@Override
	public boolean interupted() {
		return false;
	}

	@Override
	public boolean started() {
		return started;
	}
}
