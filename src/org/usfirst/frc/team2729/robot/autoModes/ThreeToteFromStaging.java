package org.usfirst.frc.team2729.robot.autoModes;

import org.usfirst.frc.team2729.robot.commands.DriveForward;
import org.usfirst.frc.team2729.robot.commands.ElevatorClamp;
import org.usfirst.frc.team2729.robot.commands.Turn;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ThreeToteFromStaging extends CommandGroup{

	public ThreeToteFromStaging(){
		addSequential(new ThreeToteFromStagingStep());
		addSequential(new DriveForward(1.0, 500)); //todo determine distance between totes
		addSequential(new ThreeToteFromStagingStep());
		addSequential(new DriveForward(1.0, 500)); //todo determine distance between totes
		addSequential(new ThreeToteFromStagingStep());
		
		addSequential(new Turn(-250));
		addSequential(new DriveForward(-1.0, 1000)); //todo determine distance to auto zone
		addSequential(new ElevatorClamp(false));
		addSequential(new DriveForward(-1.0, 500));	
	}
}
