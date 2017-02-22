package org.usfirst.frc.team4525.robot.operate.subsystems.impl;

import org.usfirst.frc.team4525.robot.operate.subsystems.Climber;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;

public class ClimberImpl implements Climber {


	private Relay motor;
	
	public void init() {
		motor = new Relay(0);
	}

	@Override
	public void on() {
		motor.set(Value.kForward);
	}

	@Override
	public void off() {
		motor.stopMotor();
	}

}
