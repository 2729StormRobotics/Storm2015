package org.usfirst.frc.team2729.robot.autoModes;

import org.usfirst.frc.team2729.robot.commands.ChangeElevPosition;
import org.usfirst.frc.team2729.robot.commands.DriveForward;
import org.usfirst.frc.team2729.robot.commands.ElevatorClamp;
import org.usfirst.frc.team2729.robot.commands.Strafe;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class OneToteOneContainer extends CommandGroup{
	public OneToteOneContainer(){
		addSequential(new DriveForward(.7, 500));
		addSequential(new ElevatorClamp(true));
		addSequential(new ChangeElevPosition(1));
		addSequential(new DriveForward(.4, 100));
		addSequential(new ElevatorClamp(false));
		addSequential(new ChangeElevPosition(-1));
		addSequential(new ElevatorClamp(true));
		addSequential(new Strafe(-1), 3);
	}
}
