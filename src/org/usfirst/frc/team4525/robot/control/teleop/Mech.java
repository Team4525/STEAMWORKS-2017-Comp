package org.usfirst.frc.team4525.robot.control.teleop;

import org.usfirst.frc.team4525.robot.control.Controller;
import org.usfirst.frc.team4525.robot.operate.SubsystemsManager;
import org.usfirst.frc.team4525.robot.operate.subsystems.Clamp;
import org.usfirst.frc.team4525.robot.operate.subsystems.Climber;
import org.usfirst.frc.team4525.robot.operate.subsystems.HomePlate;
import org.usfirst.frc.team4525.robot.operate.subsystems.Pneumatics;
import org.usfirst.frc.team4525.robot.operate.subsystems.Puncher;
import org.usfirst.frc.team4525.robot.util.DashUtil;
import org.usfirst.frc.team4525.robot.util.XboxController;
import org.usfirst.frc.team4525.robot.util.XboxController.Axis;
import org.usfirst.frc.team4525.robot.util.XboxController.Button;

public class Mech implements Controller {
	private boolean active = false;

	private XboxController xbox = new XboxController(1);

	public void start() {
		// Thread which runs the driver loop
		active = true;
		//
		new Thread(new Runnable() {
			public void run() {
				// Set all definitions
				DashUtil.getInstance().log("Starting Mech Mode.");

				SubsystemsManager systems = SubsystemsManager.getInstance();
				//
				Climber climb = systems.getClimber();
				//
				HomePlate lifter = systems.getLiftPlate();
				Clamp clamp = systems.getClamp();
				Puncher push = systems.getGearPuncher();
				Pneumatics pneumat = systems.getPneumatics();

				// buttons
				boolean climb_motor = false;
				boolean push_gear = false;

				boolean canLift = false;
				boolean isLifted = false;

				boolean a = false;
				boolean x = false;
				boolean b = false;

				// Driver loop:
				while (active) {

					a = xbox.getButton(Button.A);
					x = xbox.getButton(Button.X);
					b = xbox.getButton(Button.B);

					if (x && !isLifted) {
						lifter.up();
						clamp.ungrip();
						//
						canLift = true;
						isLifted = true;
					} else if (b && isLifted) {
						lifter.down();
						clamp.grip();
						//
						canLift = true;
						isLifted = false;
					} else if (!a) {
						canLift = false;
					}
					// Push the gear
					if (xbox.getAxis(Axis.TriggerR) > 0.7 && !push_gear) {
						clamp.ungrip();
						push.push();
						push_gear = true;
					} else if (xbox.getAxis(Axis.TriggerR) < 0.7 && push_gear) {
						push_gear = false;
					}

					// Climb motor on/off
					if (xbox.getPOV() == 0 && !climb_motor) {
						climb_motor = true;
						climb.on();
						pneumat.stopCompressor();
					} else if (climb_motor == true && xbox.getPOV() != 0) {
						climb.off();
						climb_motor = false;
						pneumat.startCompressor();
					}

				}

				DashUtil.getInstance().log("Mech mode stopped.");
			}
		}).start();

	}

	public void stop() {
		active = false;
	}

}
