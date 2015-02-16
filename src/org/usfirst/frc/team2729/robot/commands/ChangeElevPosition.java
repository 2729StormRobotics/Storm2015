package org.usfirst.frc.team2729.robot.commands;

import org.usfirst.frc.team2729.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ChangeElevPosition extends Command{

	private int _change, _loop;
	private boolean _up;
	private long _startTime, _endTime;
	private double _err, _speed;
	private final double _maxDis = 0.229;
	
	public ChangeElevPosition(int change){
		_change = change;
		_up = _change > 0;
		_startTime = System.currentTimeMillis();
	}
	
	@Override
	protected void initialize() {
		_loop = 3;
		// TODO Auto-generated method stub
		if(Robot.intake.defIndexes()){
			Robot.intake.increment(_change);
		}else{
			end();
		}
	}
	
	@Override
	protected void execute() {
		double speed;
		SmartDashboard.putNumber("Loop count", _loop);
		speed = 0.80;
		_err = Math.abs(Robot.intake.getElevHeight() - Robot.intake.getPoint());
		Robot.intake.setElevatorPower(speed * (_up ? -1 : 1) * Math.min(Math.max(_err/_maxDis * 2, 0.2), 1));
//		Robot.intake.setElevatorPower(0.65 * (_up ? -1 : 1));
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
		System.out.println(Robot.intake.getPoint());
		if(_up){
			if(Robot.intake.getElevHeight() >= Robot.intake.getPoint()){
				_up = false;
				_loop--;
			}
				
		}else{
			if(Robot.intake.getElevHeight() <= Robot.intake.getPoint()){
				_up = true;
				_loop --;
			}
		}
		return _loop <= 0;
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
