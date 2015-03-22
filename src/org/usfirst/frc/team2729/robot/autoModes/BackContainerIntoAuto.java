package org.usfirst.frc.team2729.robot.autoModes;

import org.usfirst.frc.team2729.robot.commands.ChangeElevPosition;
import org.usfirst.frc.team2729.robot.commands.Clamp;
import org.usfirst.frc.team2729.robot.commands.DriveForward;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BackContainerIntoAuto extends CommandGroup{

	public BackContainerIntoAuto(){
		addSequential(new DriveForward(0.8, 100));
		addSequential(new Clamp());
		addSequential(new ChangeElevPosition(1));
		addSequential(new DriveForward(-0.8, 600));
	}
	
}
