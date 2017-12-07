package org.usfirst.frc.team4525.robot.operate.subsystems.impl;

import org.usfirst.frc.team4525.robot.operate.subsystems.Climber;

import edu.wpi.first.wpilibj.Victor;

public class ClimberImpl implements Climber {


	private Victor motor;
	
	public void init() {
		motor = new Victor(0);
		//Define the climber motor
	}

	@Override
	public void on() {
		motor.set(0.95);
		//Turn on the motor, in the forward direction
	}

	@Override
	public void off() {
		motor.stopMotor();
		//Stop the motor
	}

}
