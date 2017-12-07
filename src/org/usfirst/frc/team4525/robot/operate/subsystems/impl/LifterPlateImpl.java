package org.usfirst.frc.team4525.robot.operate.subsystems.impl;

import org.usfirst.frc.team4525.robot.operate.subsystems.HomePlate;
import org.usfirst.frc.team4525.robot.util.Piston;

public class LifterPlateImpl implements HomePlate {

	private Piston lifter;

	public void init() {
		lifter = new Piston(3, 2, false);
		// Define the lifter
	}

	public void up() {
		lifter.extend();
		// Raise the lifter
	}

	public void down() {
		lifter.retract();
		// lower the lifter

	}

}
