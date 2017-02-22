package org.usfirst.frc.team4525.robot.operate.subsystems.impl;

import org.usfirst.frc.team4525.robot.operate.subsystems.Pneumatics;

import edu.wpi.first.wpilibj.Compressor;

public class PneumaticsImpl implements Pneumatics {

	private Compressor comp;
	
	@Override
	public void init() {
		comp = new Compressor(0);
	}

	@Override
	public void startCompressor() {
		comp.start();
		comp.setClosedLoopControl(true);
	}

	@Override
	public void stopCompressor() {
		comp.stop();
	}
	
	public boolean getCompressorRunning() {
		return comp.getPressureSwitchValue();
	}

}
