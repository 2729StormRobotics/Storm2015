package org.usfirst.frc.team2729.robot.subsystems;

import org.usfirst.frc.team2729.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Arms extends Subsystem{

	private final Solenoid _arm = new Solenoid(RobotMap.PORT_SOLENOID_LATCH);
	
	public Arms(){
		LiveWindow.addActuator("Arms", "Arm", _arm);
	    _arm.set(false);
	}
		
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
	public void clamp() {
        if(!_arm.get())
            _arm.set(true);
    }
	
	public void unclamp() {
        if(_arm.get())
            _arm.set(false);
    }
}
