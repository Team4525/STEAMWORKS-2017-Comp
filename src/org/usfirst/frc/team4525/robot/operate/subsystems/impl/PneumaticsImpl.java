package org.usfirst.frc.team4525.robot.operate.subsystems.impl;

import org.usfirst.frc.team4525.robot.operate.subsystems.Pneumatics;

import edu.wpi.first.wpilibj.Compressor;

public class PneumaticsImpl implements Pneumatics {

	private Compressor comp;
	
	
	public void init() {
		comp = new Compressor(0);
		//Define the compressor
	}

	
	public void startCompressor() {
		comp.start();
		comp.setClosedLoopControl(true);
		//Start the compressor
	}

	
	public void stopCompressor() {
		comp.stop();
		//Stop the compressor
	}
	
	public boolean getCompressorRunning() {
		return comp.getPressureSwitchValue();
		//Gets and returns the current pressure
	}

}
