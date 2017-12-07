package org.usfirst.frc.team4525.robot.operate.subsystems.impl;

import org.usfirst.frc.team4525.robot.operate.subsystems.Puncher;
import org.usfirst.frc.team4525.robot.util.Piston;

import edu.wpi.first.wpilibj.Timer;

public class PuncherImpl implements Puncher {

	private Piston puncher;
	private boolean pushing = false;

	public void init() {
		puncher = new Piston(4, 5, false);
		// Declare the puncher
	}

	public void push() {
		// If !pushing, extend and then retract the puncher with a delay of 1 second
		// between the two, inside a new thread
		if (!pushing) {
			pushing = true;
			new Thread(new Runnable() {
				public void run() {
					puncher.extend();
					Timer.delay(1);
					puncher.retract();
					pushing = false;
				}

			}).start();
		}
	}

}
