package org.usfirst.frc.team4525.robot.util;

import edu.wpi.first.wpilibj.DriverStation;

public class XboxController {

	public static DriverStation ds = DriverStation.getInstance();

	private final int port;
	private final double axisDeadZone = 0.1;

	public enum Button {
		A(0), B(1), X(2), Y(3), BumperL(4), BumperR(5), Back(6), Start(7), LStickPush(8), RStickPush(9);
		private final int inputValue;

		Button(int inputValue) {
			this.inputValue = inputValue;
		}

		public int get() {
			return inputValue;
		}
	}

	public enum Axis {
		LeftX(0), LeftY(1), TriggerL(2), TriggerR(3), RightX(4), RightY(5), dPadX(6), dPadY(7);
		private final int inputValue;

		Axis(int inputValue) {
			this.inputValue = inputValue;
		}

		public int get() {
			return inputValue;
		}
	}

	// Constructor
	public XboxController(int port) {
		this.port = port;
		ds = DriverStation.getInstance();
	}

	public boolean getButton(Button button) {
		return ((0x1 << button.get()) & ds.getStickButtons(port)) != 0;
	}

	public double getAxis(Axis axis) {
		double raw = ds.getStickAxis(port, axis.get());
		// If axis is not within our deadzones:
		if (Math.abs(raw) < axisDeadZone)
			return 0;

		return raw;
	}

	public double getPOV() {
		double raw = ds.getStickPOV(port, 0);
		return raw;
	}
}
