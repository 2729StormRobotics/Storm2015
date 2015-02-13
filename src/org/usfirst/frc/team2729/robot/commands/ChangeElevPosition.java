package org.usfirst.frc.team2729.robot.commands;

import org.usfirst.frc.team2729.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ChangeElevPosition extends Command{

	private int _change;
	
	public ChangeElevPosition(int change){
		_change = change;
	}
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		Robot.intake.updateIndex();
		Robot.intake.increment(_change);
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		Robot.intake.setElevatorPower(0.80 * (Math.abs(_change)/_change));
	}

	@Override
	protected boolean isFinished() {
		return (Robot.intake.getPoint() == Robot.intake.getElevHeight() - 0.009 || Robot.intake.getPoint() == Robot.intake.getElevHeight() + 0.009);
	}

	@Override
	protected void end() {
		Robot.intake.setElevatorPower(0);
	}

	@Override
	protected void interrupted() {
		end();
		
	}

}
