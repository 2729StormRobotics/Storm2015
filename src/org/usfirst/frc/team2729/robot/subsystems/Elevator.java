
package org.usfirst.frc.team2729.robot.subsystems;

import org.usfirst.frc.team2729.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Elevator extends Subsystem {
    
	 private final Talon _elevatorRight  = new Talon(RobotMap.PORT_MOTOR_ELEVATOR_1),
             			 _elevatorLeft = new Talon(RobotMap.PORT_MOTOR_ELEVATOR_2);
	
	private final Encoder _elevatorRightEncoder =  new Encoder(RobotMap.PORT_ENCODER_ELEVATOR_1, RobotMap.PORT_ENCODER_ELEVATOR_2);
	private final Encoder _elevatorLeftEncoder = new Encoder(RobotMap.PORT_ENCODER_ELEVATOR_1, RobotMap.PORT_ENCODER_ELEVATOR_2);
	
	public Elevator() {
        LiveWindow.addSensor("Elevator", "Right Encoder", _elevatorRight);
        LiveWindow.addSensor("Elevator", "Left Encoder", _elevatorLeft);
    }
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
	public double getRightDistance() {
		return _elevatorRightEncoder.get();
	}
	
	public double getLeftDistance() {
		return _elevatorLeftEncoder.get();
	}
	
	public double getRightSpeedEnc() {
		return _elevatorRightEncoder.getRate();
	}
	
	public double getLeftSpeedEnc() {
		return _elevatorLeftEncoder.getRate();
	}
	
	public void clearEncoders(){
		_elevatorLeftEncoder.reset();
	    _elevatorRightEncoder.reset();
	}
	
	
}

