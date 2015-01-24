package org.usfirst.frc.team2729.robot.commands;

import org.usfirst.frc.team2729.robot.Robot;
import org.usfirst.frc.team2729.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class turnToAngle extends Command{

	private double target = 0;
	private double speed = 0;
	
	public turnToAngle(double _target, double _speed){
		requires(Robot._gyro);
		requires(Robot.driveTrain);
	}
	protected void initialize() {}

	@Override
	protected void execute() {
		if(target > 0){
			Robot.driveTrain.gradientDrive(0,0,speed);
		} else {
			Robot.driveTrain.gradientDrive(0,0,-speed);
		}
		
	}

	@Override
	protected boolean isFinished() {
		return Math.abs(Robot._gyro.getGyroAngle() - target) < 2.0;
	}

	@Override
	protected void end(){
		Robot._gyro.clearGyro();
		Robot.driveTrain.halt();
	}

	@Override
	protected void interrupted() {
		Robot._gyro.clearGyro();
		Robot.driveTrain.halt();
	}
}
