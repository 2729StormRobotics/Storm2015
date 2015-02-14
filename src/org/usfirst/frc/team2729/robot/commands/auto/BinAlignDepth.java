package org.usfirst.frc.team2729.robot.commands.auto;

import org.usfirst.frc.team2729.robot.Robot;
import org.usfirst.frc.team2729.robot.subsystems.VisionSystem;

import edu.wpi.first.wpilibj.command.Command;

public class BinAlignDepth extends Command{
	public double speed;
	
	public BinAlignDepth(double _speed){
		requires(Robot.driveTrain);
		speed = _speed;
	}
	
	@Override
	protected void initialize() {}
	@Override
	protected void execute() {
		//what side of the bin is the robot on? 
		//moves left or right accordingly
		Robot.driveTrain.gradientDrive(0, VisionSystem.getBinAngle() <= 0 ? speed : -speed, 0);
	}

	@Override
	protected boolean isFinished() {
		if(VisionSystem.getBinFound()){ 
			if(Math.abs(VisionSystem.BIN_TARGET_ANGLE - VisionSystem.getBinAngle())< 1){ //if the angle is good, you done
				return true;
			} else {
				return false;
			}
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