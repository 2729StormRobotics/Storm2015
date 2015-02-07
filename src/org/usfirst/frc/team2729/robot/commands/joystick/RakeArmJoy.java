package org.usfirst.frc.team2729.robot.commands.joystick;

import org.usfirst.frc.team2729.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class RakeArmJoy extends Command{

	public RakeArmJoy(){
		requires(Robot.rakeArm);
	}
	
	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		//double rake = Robot.oi.getRake();
		//Robot.rakeArm.moveArm(rake);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
	
	@Override
	protected void end() {}
	
	@Override
	protected void interrupted() {}

//require dat rakearm
}
