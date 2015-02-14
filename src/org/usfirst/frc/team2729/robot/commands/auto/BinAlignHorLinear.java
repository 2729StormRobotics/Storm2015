package org.usfirst.frc.team2729.robot.commands.auto;

import org.usfirst.frc.team2729.robot.Robot;
import org.usfirst.frc.team2729.robot.subsystems.VisionSystem;

import edu.wpi.first.wpilibj.command.Command;

public class BinAlignHorLinear extends Command{
	public double speed;
	
	public BinAlignHorLinear(double _speed){
		requires(Robot.linearArm);
		speed = _speed;
	}
	
	@Override
	protected void initialize() {}
	@Override
	protected void execute() {
		Robot.linearArm.moveArm(speed);
	}

	@Override
	protected boolean isFinished() {
		if(Robot.linearArm.isPressed()){
			return true;
		} else {
			return false;
		}
	}

	@Override
	protected void end() {
		Robot.linearArm.moveArm(0);
	}
	@Override
	protected void interrupted() {
		Robot.linearArm.moveArm(0);	
	}
}
