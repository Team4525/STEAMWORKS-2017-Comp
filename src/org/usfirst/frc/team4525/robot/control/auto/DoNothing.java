package org.usfirst.frc.team4525.robot.control.auto;

import org.usfirst.frc.team4525.robot.control.Controller;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DoNothing implements Controller {

	public void start() {
		SmartDashboard.putString("DOING", " NOTHING");
	}

	@Override
	public void stop() {

	}

}