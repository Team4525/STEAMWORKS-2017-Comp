package org.usfirst.frc.team4525.robot.operate.subsystems.impl;

import org.usfirst.frc.team4525.robot.operate.subsystems.Clamp;
import org.usfirst.frc.team4525.robot.util.Piston;

public class ClampImpl implements Clamp {

	private Piston clamp;

	private boolean is_clamped = false;

	public void init() {
		clamp = new Piston(0,1, true); // 1,0, true
	}

	public void grip() {
		clamp.extend();
		clamp.countTime();
	}

	// @Override
	public void ungrip() {
		clamp.retract();
		clamp.countTime();
	}

}
