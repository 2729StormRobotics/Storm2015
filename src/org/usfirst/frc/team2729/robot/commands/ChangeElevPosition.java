package org.usfirst.frc.team2729.robot.commands;

import org.usfirst.frc.team2729.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ChangeElevPosition extends Command{

	private int _change;
	private boolean _up;
	private long _startTime, _endTime;
	
	
	public ChangeElevPosition(int change){
		_change = change;
		_up = _change > 0;
		_startTime = System.currentTimeMillis();
	}
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		Robot.intake.defIndexes();
		Robot.intake.increment(_change);
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		Robot.intake.setElevatorPower(0.80 * (_change > 0 ? 1 : -1));
	}

	@Override
	protected boolean isFinished() {
		_endTime = System.currentTimeMillis();
		/**
		 * This ends it if it goes too long
		 */
		/*if(_endTime - _startTime == 60000){
			SmartDashboard.putBoolean("Too long", true);
			return true;
		}*/
		if(_up){
			return Robot.intake.getElevHeight() >= Robot.intake.getPoint();
		}else{
			return Robot.intake.getElevHeight() <= Robot.intake.getPoint();
		}
		
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
