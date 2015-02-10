package org.usfirst.frc.team2729.robot.commands.auto;

import org.usfirst.frc.team2729.robot.Robot;
import org.usfirst.frc.team2729.robot.subsystems.VisionSystem;

import edu.wpi.first.wpilibj.command.Command;

public class ToteAlignHor extends Command{
	public double speed;
	
	public ToteAlignHor(double _speed){
		requires(Robot.driveTrain);
		speed = _speed;
	}
	@Override
	protected void initialize() {}

	@Override
	protected void execute() {
		Robot.driveTrain.gradientDrive(VisionSystem.getToteAngle() <= 0 ? speed : -speed, 0, 0);
		//Is the robot on the left or right of tote?
		//moves left/right accordingly
	}

	@Override
	protected boolean isFinished() {
		if(VisionSystem.getToteFound()){
			if(Math.abs(VisionSystem.TOTE_TARGET_ANGLE - VisionSystem.getToteAngle())< 1){ //if it's aligned, you done
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
