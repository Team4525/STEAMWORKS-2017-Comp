package org.usfirst.frc.team4525.robot.operate.autosystems;

public interface Command {
	
	public void init();
	
	public void execute();
	public boolean isFinished();
	public void end();
	//
	public void stop();
	//
	public boolean interupted();
	public boolean started();
}
