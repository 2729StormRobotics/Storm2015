package org.usfirst.frc.team2729.robot.commands.auto;

import org.usfirst.frc.team2729.robot.commands.RakeArmRaised;
import org.usfirst.frc.team2729.robot.commands.Shift;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class twoBinRake extends CommandGroup{
	//TODO: convert encoder ticks (with respect to x) to feet
	public twoBinRake(){
		addSequential(new Shift(true));
		addParallel(new RakeArmRaised(false, 1));
		addSequential(new driveVector(0, true, 2000, 1));//Roughly align with bin TODO: find 3rd arg
		addSequential(new binAlignVert(0.25));
		addSequential(new binAlignHor(0.5));
		addSequential(new RakeArmRaised(true, 1)); 				//Engage bin
		addSequential(new driveVector(-90, false, 500, 0.75));
		addSequential(new RakeArmRaised(false, 1)); 			//Release bin
		addSequential(new driveVector(-90, false, 50, 0.75));   //Move Clear of the bin
		addSequential(new driveVector(0, false, 2000, 0.75));	//Roughly align with 2nd bin
		addSequential(new binAlignVert(0.25));					
		addSequential(new binAlignHor(0.5));
		addSequential(new RakeArmRaised(true, 1));				//Engage bin
		addSequential(new driveVector(-90, false, 500, 0.75));	//Move bin clear of the ramp
		addSequential(new RakeArmRaised(false, 1));				//Release bin
		addSequential(new driveVector(-90, false, 50, 0.75));	//Move clear of bin
	}
}
