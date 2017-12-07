package org.usfirst.frc.team4525.robot.control.teleop;

import org.usfirst.frc.team4525.robot.control.Controller;
import org.usfirst.frc.team4525.robot.operate.SubsystemsManager;
import org.usfirst.frc.team4525.robot.operate.sensors.Sensor;
import org.usfirst.frc.team4525.robot.operate.sensors.SensorManager;
import org.usfirst.frc.team4525.robot.operate.subsystems.Drive;
import org.usfirst.frc.team4525.robot.operate.subsystems.Visioning;
import org.usfirst.frc.team4525.robot.util.DashUtil;
import org.usfirst.frc.team4525.robot.util.XboxController;
import org.usfirst.frc.team4525.robot.util.XboxController.Axis;
import org.usfirst.frc.team4525.robot.util.XboxController.Button;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Driver implements Controller {

	private boolean active = false;

	private XboxController xbox = new XboxController(0);

	public void start() {
		// Thread which runs the driver loop
		active = true;
		//
		new Thread(new Runnable() {
			public void run() {
				// Set all definitions
				DashUtil.getInstance().log("Starting Driver Mode.");

				SubsystemsManager systems = SubsystemsManager.getInstance();
				Drive drive_train = systems.getDriveTrain();
				Visioning visionTargeting = SubsystemsManager.getInstance().getVisionUtil();
				Sensor sonic_left = SensorManager.getInstance().getFrontRange();

				drive_train.setDeadZone(0.15);
				drive_train.setDriveStrait(true);

				int driveInvert = 1;

				// set buttons
				boolean invertPressed = false;
				boolean vision_target = false;

				// Driver loop:
				while (active) {

					SmartDashboard.putString("Distance from front of robot:", Double.toString(sonic_left.get()));

					// For inverting the values that are fed to the drive train
					if (Math.abs(xbox.getAxis(Axis.LeftY)) < 0.2) {
						if (xbox.getButton(Button.BumperR) && !invertPressed) {
							driveInvert = driveInvert * -1;
							invertPressed = true;
						} else if (!xbox.getButton(Button.BumperR)) {
							invertPressed = false;
						}
					}

					// Put the power to the drive_train
					if (xbox.getAxis(Axis.TriggerL) > 0.5) {
						if (!vision_target) {
							drive_train.drive(0, 0);
							DashUtil.getInstance().log("Attempting to go to target");
							visionTargeting.goToTarget();
							vision_target = true;
						}
						//Stop vision
					} else if (vision_target && xbox.getAxis(Axis.TriggerL) < 0.5) {
						visionTargeting.stop();
						vision_target = false;
					} else {
						drive_train.drive(xbox.getAxis(Axis.LeftY) * driveInvert, xbox.getAxis(Axis.RightX));
					}
				}

				DashUtil.getInstance().log("Driver mode stopped.");
			}
		}).start();

	}

	public void stop() {
		active = false;
	}

}
