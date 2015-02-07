package org.usfirst.frc.team2729.robot.autoModes;

import org.usfirst.frc.team2729.robot.commands.LowerArmsToReleasePos;
import org.usfirst.frc.team2729.robot.commands.Stack;
import org.usfirst.frc.team2729.robot.commands.Unclamp;
import org.usfirst.frc.team2729.robot.commands.auto.DriveVector;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class OneToteAuto extends CommandGroup {

	public OneToteAuto() {
		addSequential(new DriveVector(0, false, 20, 1));
		addSequential(new Stack());
		addSequential(new MoveToAuto());
		addSequential(new LowerArmsToReleasePos());
		addSequential(new Unclamp());
	}

}
