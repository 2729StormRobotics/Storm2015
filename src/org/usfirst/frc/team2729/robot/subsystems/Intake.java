package org.usfirst.frc.team2729.robot.subsystems;

import java.util.Timer;
import java.util.TimerTask;

import org.usfirst.frc.team2729.robot.RobotMap;
import org.usfirst.frc.team2729.robot.commands.Elevator;
import org.usfirst.frc.team2729.robot.util.StringPot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Intake extends Subsystem{

	private final Solenoid _arm = new Solenoid(RobotMap.PORT_SOLENOID_CLAMP);
	private final Talon _elevator = new Talon(RobotMap.PORT_MOTOR_ELEVATOR);
	private final Encoder _elevatorEncoder =  new Encoder(RobotMap.PORT_ENCODER_ELEVATOR_1, RobotMap.PORT_ENCODER_ELEVATOR_2);
	private boolean _isHighGear  = false;
	private DoubleSolenoid _shifter = new DoubleSolenoid(RobotMap.PORT_SOLENOID_SHIFT_ELEVATOR_HIGH, RobotMap.PORT_SOLENOID_SHIFT_ELEVATOR_LOW);
	private final StringPot _pot = new StringPot(RobotMap.PORT_ELEVATOR_STRINGPOT, 1); //TODO: Find max safe value
	
	private double elevatorSetSpeed = 0;
	
	private double stringPotSP, iGain = .005, dT = 10; 
	private final double HGMax = 700, LGMax = 500; //TODO: Determine real values for these
	
	public Intake(){
		LiveWindow.addActuator("Intake", "Arm", _arm);
		LiveWindow.addActuator("Intake", "Elevator", _elevator);
		LiveWindow.addSensor("Intake", "Elevator Encoder", _elevatorEncoder);
        
		stringPotSP = _pot.get(); //sets initial setpoint to the initial height of the elevator
		
		Timer _timer = new Timer();
		_timer.schedule(new TimerTask() {
			public void run() {
				if(getElevatorSpeed() == 0){
					_elevator.set(_elevator.get() + ((stringPotSP - (getElevatorSpeed()/(isHighgear() ? HGMax : LGMax)))*iGain));
				} else {
					_elevator.set(elevatorSetSpeed);
					stringPotSP = _pot.get();
				}
			}
		}, (long)dT, (long)dT);
		
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
		setDefaultCommand(new Elevator());
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
		elevatorSetSpeed = power;
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
        return elevatorSetSpeed;
    }

    public void setHighGear(boolean enabled) {
		_isHighGear = enabled;
		_shifter.set(enabled ? DoubleSolenoid.Value.kReverse :
					 DoubleSolenoid.Value.kForward);
	}
	public boolean isHighgear() {
		return _isHighGear;
	}
}
