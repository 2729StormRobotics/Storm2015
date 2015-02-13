package org.usfirst.frc.team2729.robot.commands.auto;

import org.usfirst.frc.team2729.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TurnToAngle extends Command{

	private double target = 0;
	private double speed  = 0;
	
	public TurnToAngle(double _target, double _speed){
		requires(Robot.driveTrain);
		target = _target;
		speed  = _speed;
	}

	protected void initialize() {
	}

	@Override
	protected void execute() {
		//turns left or right depending on target location
		if (target > 0) { 
			Robot.driveTrain.gradientDrive(0, 0, speed);
		} else {
			Robot.driveTrain.gradientDrive(0, 0, -speed);
		}

	}

	@Override
	protected boolean isFinished() {
		return Math.abs(Robot.driveTrain._gyro.getGyroAngle() - target) < 2.0; //finished if within 2 degrees
	}

	@Override
	protected void end() {
		Robot.driveTrain._gyro.clearGyro();
		Robot.driveTrain.halt();
	}

	@Override
	protected void interrupted() {
		Robot.driveTrain._gyro.clearGyro();
		Robot.driveTrain.halt();
	}
}
