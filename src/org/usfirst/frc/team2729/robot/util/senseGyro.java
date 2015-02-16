package org.usfirst.frc.team2729.robot.util;

import edu.wpi.first.wpilibj.Gyro;

public class senseGyro {

	private final Gyro _gyro;
	private double _gyroOffset = 0;

	public senseGyro(double offset, int channel) {
		_gyro = new Gyro(channel);
		_gyroOffset = offset;
	}

	public void resetGyro() {
		//resets and clears the gyro duh
		_gyro.reset();
		clearGyro();
	}

	public void clearGyro() {
		_gyroOffset = _gyro.getAngle();
	}

	public double getGyroAngle() {
		return _gyro.getAngle() - _gyroOffset;
	}
}
