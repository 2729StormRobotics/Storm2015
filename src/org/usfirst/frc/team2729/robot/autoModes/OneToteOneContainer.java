package org.usfirst.frc.team2729.robot.autoModes;

import org.usfirst.frc.team2729.robot.commands.ChangeElevPosition;
import org.usfirst.frc.team2729.robot.commands.DriveForward;
import org.usfirst.frc.team2729.robot.commands.ElevatorClamp;
import org.usfirst.frc.team2729.robot.commands.Strafe;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class OneToteOneContainer extends CommandGroup{
	public OneToteOneContainer(){
		addSequential(new ElevatorClamp(true));
		addSequential(new WaitCommand(0.5));
		addSequential(new ChangeElevPosition(1));
		addSequential(new ChangeElevPosition(1));
		addSequential(new WaitCommand(0.25));
		addSequential(new DriveForward(.7, 400));
		addSequential(new Strafe(-1), 3);
	}
}
