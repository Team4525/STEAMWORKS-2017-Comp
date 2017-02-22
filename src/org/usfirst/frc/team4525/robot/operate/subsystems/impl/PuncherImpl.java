package org.usfirst.frc.team4525.robot.operate.subsystems.impl;

import org.usfirst.frc.team4525.robot.operate.subsystems.Puncher;
import org.usfirst.frc.team4525.robot.util.Piston;

import edu.wpi.first.wpilibj.Timer;

public class PuncherImpl implements Puncher {

	private Piston puncher;
	private boolean pushing = false;

	public void init() {
		puncher = new Piston(4, 5, false); // 4,5, false
	}

	public void push() {
		if (pushing == false) {
			pushing = true;
			new Thread(new Runnable() {
				public void run() {
					puncher.extend();
					Timer.delay(1);
					puncher.retract();
					// pokeystaby.countTime();
					pushing = false;
				}

			}).start();
		}
	}

}
