package org.usfirst.frc.team2729.robot.commands.joystick;
import org.usfirst.frc.team2729.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class LinearArmJoy extends Command {
	
	public LinearArmJoy(){
		requires(Robot.linearArm);
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		double Linear = Robot.oi.getRake();
		Robot.linearArm.moveArm(Linear);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {	
	}	
}
