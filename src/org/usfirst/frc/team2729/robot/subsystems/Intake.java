package org.usfirst.frc.team2729.robot.subsystems;

import org.usfirst.frc.team2729.robot.RobotMap;
import org.usfirst.frc.team2729.robot.commands.Elevator;
import org.usfirst.frc.team2729.robot.util.StringPot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Intake extends Subsystem{

	private final DoubleSolenoid _arm = new DoubleSolenoid(RobotMap.PORT_SOLENOID_CLAMP_IN, RobotMap.PORT_SOLENOID_CLAMP_OUT);
	private final Talon _elevator = new Talon(RobotMap.PORT_MOTOR_ELEVATOR),
						_spin	  = new Talon(RobotMap.PORT_MOTOR_SPIN);
	private final Encoder _elevatorEncoder =  new Encoder(RobotMap.PORT_ENCODER_ELEVATOR_1, RobotMap.PORT_ENCODER_ELEVATOR_2);
	private boolean _extended;
	private StringPot _stringPot = new StringPot(RobotMap.PORT_STRINGPOT);
	
	//false is bottom
	private final DigitalInput _switch = new DigitalInput(RobotMap.PORT_LIMIT_SWITCH_ELEVATOR);

	
	public Intake(){
		LiveWindow.addActuator("Intake", "Arm", _arm);
		LiveWindow.addActuator("Intake", "Elevator", _elevator);
		LiveWindow.addSensor("Intake", "Elevator Encoder", _elevatorEncoder);
        
	    _arm.set(DoubleSolenoid.Value.kForward);
	    _extended = true;
	}
	
	public void spin(double power){
		_spin.set(power);
	}
	
	public double getElevHeight(){
		return _stringPot.get();
	}
	
	public boolean isBottom(){
		return !_switch.get();
	}
	
	public Talon get_elevator() {
		return _elevator;
	}

	public boolean isExtended() {
		return _extended;
	}
	
	public Encoder getElevatorEncoder() {
		return _elevatorEncoder;
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		setDefaultCommand(new Elevator());
	}

	/**
	Clamps moving arm into stationary arm
	*/
	public void clamp() {
		_arm.set(DoubleSolenoid.Value.kReverse);
		_extended = false;
	}
	
	/**
	Unclamps moving arm from stationary arm
	*/
	public void unclamp() {
		_arm.set(DoubleSolenoid.Value.kForward);
		_extended = true;
    }
	
	/**
	Runs the elevator motors; Power must be a value between -1.0 and 1.0
	*/
	public void setElevator(double power){
		if((Math.abs(power) == power && !_switch.get())){
			_elevator.set(0);
		}else{
			_elevator.set(-power);
		}
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
