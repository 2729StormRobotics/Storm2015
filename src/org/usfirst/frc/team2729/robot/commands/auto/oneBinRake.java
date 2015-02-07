package org.usfirst.frc.team2729.robot.commands.auto;

import org.usfirst.frc.team2729.robot.commands.RakeArmRaised;
import org.usfirst.frc.team2729.robot.commands.Shift;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class oneBinRake extends CommandGroup{
	//TODO: convert encoder ticks (with respect to x) to feet
	public oneBinRake(){
		addSequential(new Shift(true));
		addParallel(new RakeArmRaised(false, 1));
		addSequential(new driveVector(0, true, 2000, 1));//TODO: find 3rd arg
		addSequential(new binAlignVert(0.25));
		addSequential(new binAlignHor(0.5));
		addSequential(new RakeArmRaised(true, 1));
		addSequential(new driveVector(-90, false, 500, 0.75));
		addSequential(new turnToAngle(-90, .9));
		addSequential(new RakeArmRaised(false, 1));
	}
}
