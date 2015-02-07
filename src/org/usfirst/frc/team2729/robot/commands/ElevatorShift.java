package org.usfirst.frc.team2729.robot.commands;

import org.usfirst.frc.team2729.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ElevatorShift extends Command {
    private final boolean _high;
    
    public ElevatorShift(boolean high) {
    	requires(Robot.driveTrain);
        _high = high;
    }
    
    protected void initialize() {
        Robot.intake.setHighGear(_high);
    }

    protected void execute() {
    	
    }
    
    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    	
    }
    
    protected void interrupted() {
    	
    }
}
