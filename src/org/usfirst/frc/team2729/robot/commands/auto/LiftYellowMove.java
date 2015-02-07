package org.usfirst.frc.team2729.robot.commands.auto;

import org.usfirst.frc.team2729.robot.commands.LowerArmsToReleasePos;
import org.usfirst.frc.team2729.robot.commands.Stack;
import org.usfirst.frc.team2729.robot.commands.Unclamp;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LiftYellowMove extends CommandGroup {

	public LiftYellowMove() {
		addSequential(new DriveVector(0, false, 20, 1));
		addSequential(new Stack());
		addSequential(new MoveToAuto());
		addSequential(new LowerArmsToReleasePos());
		addSequential(new Unclamp());
	}

}
