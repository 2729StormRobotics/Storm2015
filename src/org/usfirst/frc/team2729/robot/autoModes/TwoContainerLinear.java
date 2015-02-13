package org.usfirst.frc.team2729.robot.autoModes;

import org.usfirst.frc.team2729.robot.commands.RakeArmRaised;
import org.usfirst.frc.team2729.robot.commands.Shift;
import org.usfirst.frc.team2729.robot.commands.auto.BinAlignDepth;
import org.usfirst.frc.team2729.robot.commands.auto.BinAlignHorLinear;
import org.usfirst.frc.team2729.robot.commands.auto.DriveVector;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TwoContainerLinear extends CommandGroup{
	//TODO: convert encoder ticks (with respect to x) to feet
	public TwoContainerLinear(){
		addSequential(new Shift(true));
		addParallel(new RakeArmRaised(false, 1));
		addSequential(new DriveVector(0, true, 2000, 1));//Roughly align with bin TODO: find 3rd arg
		addSequential(new BinAlignDepth(0.25));
		addSequential(new BinAlignHorLinear(0.5));
		addSequential(new RakeArmRaised(true, 1)); 				//Engage bin
		addSequential(new DriveVector(-90, false, 500, 0.75));
		addSequential(new RakeArmRaised(false, 1)); 			//Release bin
		addSequential(new DriveVector(-90, false, 50, 0.75));   //Move Clear of the bin
		addSequential(new DriveVector(0, false, 2000, 0.75));	//Roughly align with 2nd bin
		addSequential(new BinAlignDepth(0.25));					
		addSequential(new BinAlignHorLinear(0.5));
		addSequential(new RakeArmRaised(true, 1));				//Engage bin
		addSequential(new DriveVector(-90, false, 500, 0.75));	//Move bin clear of the ramp
		addSequential(new RakeArmRaised(false, 1));				//Release bin
		addSequential(new DriveVector(-90, false, 50, 0.75));	//Move clear of bin
		addSequential(new DriveVector(-90, false, 3000, 1)); 	//Drive into the auto zone
		}
}
