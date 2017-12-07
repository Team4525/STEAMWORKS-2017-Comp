package org.usfirst.frc.team4525.robot.operate.subsystems.impl;

import org.usfirst.frc.team4525.robot.operate.subsystems.Clamp;
import org.usfirst.frc.team4525.robot.util.Piston;

public class ClampImpl implements Clamp {

	private Piston clamp;

	public void init() {
		clamp = new Piston(0,1, true);
		//Define the clamp
	}

	public void grip() {
		clamp.extend();
		//Close the clamp
	}

	public void ungrip() {
		clamp.retract();
		//Open the clamp
	}

}
