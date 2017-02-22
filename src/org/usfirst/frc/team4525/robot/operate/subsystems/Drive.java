package org.usfirst.frc.team4525.robot.operate.subsystems;

import org.usfirst.frc.team4525.robot.operate.SubSystem;

public interface Drive extends SubSystem {

	public void setDeadZone(double dz);
	public void setDriveStrait(boolean v);
	public void setStraitTarget(double tgt);
	public void drive(double power, double off);
	public void tank(double lspeed, double rspeed);
	public void stop();
	
	
}
