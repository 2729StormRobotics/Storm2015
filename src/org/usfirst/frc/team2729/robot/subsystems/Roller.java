package org.usfirst.frc.team2729.robot.subsystems;

import org.usfirst.frc.team2729.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Roller extends Subsystem {
	
	private final DoubleSolenoid _rollerArm = new DoubleSolenoid(RobotMap.PORT_SOLENOID_ROLLER_IN, RobotMap.PORT_SOLENOID_ROLLER_OUT);
	private final Talon _roller = new Talon(RobotMap.PORT_MOTOR_ROLLER);
	private boolean _clamped, _spinning;
	
	@Override
	protected void initDefaultCommand() {
		
	}

	public Roller() {
		LiveWindow.addActuator("Roller", "RollerArm", _rollerArm);
		LiveWindow.addActuator("Roller", "Roller", _roller);
        
	    _rollerArm.set(DoubleSolenoid.Value.kForward);
	    _clamped = false;
	}
	
	/**
	 * Clamps roller arms
	 */
	public void clamp() {
		_rollerArm.set(DoubleSolenoid.Value.kReverse);
		_clamped = true;
	}
	
	/**
	 * Unclamps roller arms
	 */
	public void unclamp() {
		_rollerArm.set(DoubleSolenoid.Value.kForward);
		_clamped = false;
	}
	
	/**
	 * Runs the roller motors; Power must be a value between -1.0 and 1.0
	 */
	public void setRollerPower(double power) {
		_roller.set(power);
	}
	
	/**
	 * Spins rollers in
	 */
	public void spinIn() {
		_roller.set(0.8);
		_spinning = true;
	}
	
	/**
	 * Spins rollers out
	 */
	public void spinOut() {
		_roller.set(-0.8);
		_spinning = false;
	}
	
	public double getRollerSpeed() {
		return _roller.getSpeed();
	}

	public boolean isClamped(){
		return !_clamped;
	}
	
	public boolean isSpinning(){
		return !_spinning;
	}
}