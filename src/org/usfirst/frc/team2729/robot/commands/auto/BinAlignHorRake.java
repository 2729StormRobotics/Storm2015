package org.usfirst.frc.team2729.robot.commands.auto;

import org.usfirst.frc.team2729.robot.Robot;
import org.usfirst.frc.team2729.robot.subsystems.VisionSystem;

import edu.wpi.first.wpilibj.command.Command;

public class BinAlignHorRake extends Command{
	public double speed;
	
	public BinAlignHorRake(double _speed){
		requires(Robot.driveTrain);
		speed = _speed;
	}
	
	@Override
	protected void initialize() {}
	@Override
	protected void execute() {
		Robot.driveTrain.gradientDrive(speed, 0, 0);
	}

	@Override
	protected boolean isFinished() {
		if(Robot.rakeArm.isPressed()){
			return true;
		} else {
			return false;
		}
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
