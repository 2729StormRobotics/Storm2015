package org.usfirst.frc.team2729.robot.subsystems;

import org.usfirst.frc.team2729.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Intake extends Subsystem{

	private final Solenoid _arm = new Solenoid(RobotMap.PORT_SOLENOID_CLAMP);
	private final Talon _elevator = new Talon(RobotMap.PORT_MOTOR_ELEVATOR);
	private final Encoder _elevatorEncoder =  new Encoder(RobotMap.PORT_ENCODER_ELEVATOR_1, RobotMap.PORT_ENCODER_ELEVATOR_2);

	
	public Intake(){
		LiveWindow.addActuator("Intake", "Arm", _arm);
		LiveWindow.addActuator("Intake", "Elevator", _elevator);
		LiveWindow.addSensor("Intake", "Elevator Encoder", _elevatorEncoder);
        
	    _arm.set(false);
	}
		
	public Solenoid get_arm() {
		return _arm;
	}

	public Talon get_elevator() {
		return _elevator;
	}

	public Encoder get_elevatorEncoder() {
		return _elevatorEncoder;
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
	public void setElevator(double power){
		_elevator.set(power);
    }
	
	public double getElevatorDistance() {
		return _elevatorEncoder.get();
	}
	
	public double getElevatorRate() {
		return _elevatorEncoder.getRate();
	}
	
	public void clearEncoders(){
		_elevatorEncoder.reset();
	}
    
    public double getElevatorSpeed(){
        return _elevator.get();
    }
}
