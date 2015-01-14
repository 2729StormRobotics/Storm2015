package org.usfirst.frc.team2729.robot.subsystems;

import org.usfirst.frc.team2729.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Intake extends Subsystem{

	private final Solenoid _arm = new Solenoid(RobotMap.PORT_SOLENOID_CLAMP);
	
	private final Talon _elevatorRight  = new Talon(RobotMap.PORT_MOTOR_ELEVATOR_1),
 						_elevatorLeft = new Talon(RobotMap.PORT_MOTOR_ELEVATOR_2);

	private final Encoder _elevatorRightEncoder =  new Encoder(RobotMap.PORT_ENCODER_ELEVATOR_1, RobotMap.PORT_ENCODER_ELEVATOR_2);
	private final Encoder _elevatorLeftEncoder = new Encoder(RobotMap.PORT_ENCODER_ELEVATOR_1, RobotMap.PORT_ENCODER_ELEVATOR_2);
	
	public Intake(){
		LiveWindow.addActuator("Intake", "Arm", _arm);
		LiveWindow.addActuator("Intake", "Elevator Left", _arm);
		LiveWindow.addActuator("Intake", "Elevator Right", _arm);
		LiveWindow.addSensor("Intake", "Elevator Right Encoder", _elevatorRight);
        LiveWindow.addSensor("Intake", "Elevator Left Encoder", _elevatorLeft);
        
	    _arm.set(false);
	}
		
	public Solenoid get_arm() {
		return _arm;
	}

	public Talon get_elevatorRight() {
		return _elevatorRight;
	}

	public Talon get_elevatorLeft() {
		return _elevatorLeft;
	}

	public Encoder get_elevatorRightEncoder() {
		return _elevatorRightEncoder;
	}

	public Encoder get_elevatorLeftEncoder() {
		return _elevatorLeftEncoder;
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

	/**
	Clamps moving arm into stationary arm
	*/
	public void clamp() {
        if(!_arm.get())
            _arm.set(true);
    }
	
	/**
	Unclamps moving arm from stationary arm
	*/
	public void unclamp() {
        if(_arm.get())
            _arm.set(false);
    }
	
	/**
	Runs the elevator motors; Power must be a value between -1.0 and 1.0
	*/
	public void runElevator(double power){
		_elevatorRight.set(power);
		_elevatorLeft.set(power);
    }
	
	public double getRightElevatorDistance() {
		return _elevatorRightEncoder.get();
	}
	
	public double getLeftElevatorDistance() {
		return _elevatorLeftEncoder.get();
	}
	
	public double getRightElevatorRate() {
		return _elevatorRightEncoder.getRate();
	}
	
	public double getLeftElevatorRate() {
		return _elevatorLeftEncoder.getRate();
	}
	
	public void clearEncoders(){
		_elevatorLeftEncoder.reset();
	    _elevatorRightEncoder.reset();
	}
	
	public double getLeftElevatorSpeed(){
        return -_elevatorLeft.get();
    }
    
    public double getRightElevatorSpeed(){
        return _elevatorRight.get();
    }
}
