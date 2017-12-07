package org.usfirst.frc.team4525.robot.util;

import org.usfirst.frc.team4525.robot.control.Controller;
import org.usfirst.frc.team4525.robot.control.auto.DoNothing;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoChooser {
	
	private static SendableChooser autoModes;
	private static DriverStation ds = DriverStation.getInstance();
	
	
	private enum AutoMode {
		Nothing, BaseLine,BaseLineMiddle,
		DropGearLeft,DropGearMiddle,DropGearRight,
		DropGearBaseLeft,DropGearBaseMid,DropGearBaseRight;
	}

	public AutoChooser() {
		// Autonomous modes:
		
		autoModes = new SendableChooser();
		autoModes.addDefault("Nothing", AutoMode.Nothing);
		autoModes.addObject("Cross Base Line Side", AutoMode.BaseLine);
		autoModes.addObject("Cross Base Line Middle", AutoMode.BaseLineMiddle);
		//
		autoModes.addObject("Drop Gear Left Side", AutoMode.DropGearLeft);
		autoModes.addObject("Drop Gear Middle", AutoMode.DropGearMiddle);
		autoModes.addObject("Drop Gear Right Side", AutoMode.DropGearRight);
		//
		autoModes.addObject("Drop Gear Left Cross Bassline", AutoMode.DropGearBaseLeft);
		autoModes.addObject("Drop Gear Middle Cross Baseline", AutoMode.DropGearBaseMid);
		autoModes.addObject("Drop Gear Right Cross Baseline", AutoMode.DropGearBaseRight);
		
		SmartDashboard.putData("Autochooser", autoModes);
	};
	
	public static Controller getSelectedAuto() {
		AutoMode mode = (AutoMode) autoModes.getSelected();
		DashUtil.getInstance().log("Running auto for playerstation "+DriverStation.getInstance().toString());
		
		//Run the selected autonomous
		if(ds.getAlliance() == DriverStation.Alliance.Blue) {
			switch(mode) {
			case BaseLine :
				return new org.usfirst.frc.team4525.robot.control.auto.blue.CrossBaseSide();
			case BaseLineMiddle :
				return new org.usfirst.frc.team4525.robot.control.auto.blue.CrossBaseMiddle();
			case DropGearLeft :
				return new org.usfirst.frc.team4525.robot.control.auto.blue.DropGearLeft();
			case DropGearMiddle :
				return new org.usfirst.frc.team4525.robot.control.auto.blue.DropGearMiddle();
			case DropGearRight :
				return new org.usfirst.frc.team4525.robot.control.auto.blue.DropGearRight();
			case DropGearBaseLeft :
				return new org.usfirst.frc.team4525.robot.control.auto.blue.DropGearCrossBaseLeft();
			case DropGearBaseMid :
				return new org.usfirst.frc.team4525.robot.control.auto.blue.DropGearCrossBaseMiddle();
			case DropGearBaseRight :
				return new org.usfirst.frc.team4525.robot.control.auto.blue.DropGearCrossBaseRight();
			default : // nothing
				return new DoNothing();
			
			}
		} else {
			switch(mode) {
			case BaseLine :
				return new org.usfirst.frc.team4525.robot.control.auto.red.CrossBaseSide();
			case BaseLineMiddle :
				return new org.usfirst.frc.team4525.robot.control.auto.red.CrossBaseMiddle();
			case DropGearLeft :
				return new org.usfirst.frc.team4525.robot.control.auto.red.DropGearLeft();
			case DropGearMiddle :
				return new org.usfirst.frc.team4525.robot.control.auto.red.DropGearMiddle();
			case DropGearRight :
				return new org.usfirst.frc.team4525.robot.control.auto.red.DropGearRight();
			case DropGearBaseLeft :
				return new org.usfirst.frc.team4525.robot.control.auto.red.DropGearCrossBaseLeft();
			case DropGearBaseMid :
				return new org.usfirst.frc.team4525.robot.control.auto.red.DropGearCrossBaseMiddle();
			case DropGearBaseRight :
				return new org.usfirst.frc.team4525.robot.control.auto.red.DropGearCrossBaseRight();
			default :
				return new DoNothing();
			}
		}
	}
	
}