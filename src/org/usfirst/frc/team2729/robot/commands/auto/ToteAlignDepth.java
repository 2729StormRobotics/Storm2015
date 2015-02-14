package org.usfirst.frc.team2729.robot.commands.auto;

import org.usfirst.frc.team2729.robot.Robot;
import org.usfirst.frc.team2729.robot.subsystems.VisionSystem;

import edu.wpi.first.wpilibj.command.Command;

public class ToteAlignDepth extends Command{
	public double speed;
	public ToteAlignDepth(double _speed){
		requires(Robot.driveTrain);
		speed = _speed;
	}
	@Override
	protected void initialize() {}

	@Override
	protected void execute() {
		Robot.driveTrain.gradientDrive(0, VisionSystem.getToteAngle() <= 0 ? speed : -speed, 0);
		
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.driveTrain.halt();
		
	}

	@Override
	protected void interrupted() {
		Robot.driveTrain.halt();
		
	}

}
