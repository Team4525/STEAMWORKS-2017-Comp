package org.usfirst.frc.team4525.robot.operate.sensors;

public interface Sensor {
	
	public void init();
	public double get();
	public void calibrate();
	public void reset();
	
}
