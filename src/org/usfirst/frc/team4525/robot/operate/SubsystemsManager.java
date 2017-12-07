package org.usfirst.frc.team4525.robot.operate;

import org.usfirst.frc.team4525.robot.operate.subsystems.Clamp;
import org.usfirst.frc.team4525.robot.operate.subsystems.Climber;
import org.usfirst.frc.team4525.robot.operate.subsystems.Drive;
import org.usfirst.frc.team4525.robot.operate.subsystems.HomePlate;
import org.usfirst.frc.team4525.robot.operate.subsystems.Pneumatics;
import org.usfirst.frc.team4525.robot.operate.subsystems.Puncher;
import org.usfirst.frc.team4525.robot.operate.subsystems.Visioning;
import org.usfirst.frc.team4525.robot.operate.subsystems.impl.ClampImpl;
import org.usfirst.frc.team4525.robot.operate.subsystems.impl.ClimberImpl;
import org.usfirst.frc.team4525.robot.operate.subsystems.impl.DriveImpl;
import org.usfirst.frc.team4525.robot.operate.subsystems.impl.LifterPlateImpl;
import org.usfirst.frc.team4525.robot.operate.subsystems.impl.PneumaticsImpl;
import org.usfirst.frc.team4525.robot.operate.subsystems.impl.PuncherImpl;
import org.usfirst.frc.team4525.robot.operate.subsystems.impl.VisioningImpl;
import org.usfirst.frc.team4525.robot.util.DashUtil;

public class SubsystemsManager {

	private static SubsystemsManager instance = new SubsystemsManager();

	public static SubsystemsManager getInstance() {
		return instance;
	}

	// Declare the subsystems
	private SubSystem drive_train = new DriveImpl();
	private SubSystem climber = new ClimberImpl();
	private SubSystem lift_plate = new LifterPlateImpl();
	private SubSystem clamp = new ClampImpl();
	private SubSystem puncher = new PuncherImpl();
	private SubSystem pneumatics = new PneumaticsImpl();
	private SubSystem vision;

	// Declare methods for subsystems
	public Visioning getVisionUtil() {
		return (Visioning) vision;
	}

	public Pneumatics getPneumatics() {
		return (Pneumatics) pneumatics;
	}

	public Drive getDriveTrain() {
		return (Drive) drive_train;
	}

	public Climber getClimber() {
		return (Climber) climber;
	}

	public HomePlate getLiftPlate() {
		return (HomePlate) lift_plate;
	}

	public Clamp getClamp() {
		return (Clamp) clamp;
	}

	public Puncher getGearPuncher() {
		return (Puncher) puncher;
	}

	private SubsystemsManager() {
		DashUtil.getInstance().log("Initiating Subsystems.");

		// Initialize subsystems
		drive_train.init();
		climber.init();
		lift_plate.init();
		clamp.init();
		puncher.init();
		pneumatics.init();
		//Declare and initialize vision
		vision = new VisioningImpl((Drive) drive_train);
		vision.init();

		DashUtil.getInstance().log("Subsystems Ready!");
	}

}
