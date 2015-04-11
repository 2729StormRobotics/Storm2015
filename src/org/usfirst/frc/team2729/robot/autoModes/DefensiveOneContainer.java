package org.usfirst.frc.team2729.robot.autoModes;

import org.usfirst.frc.team2729.robot.commands.BinAlignHorLinear;
import org.usfirst.frc.team2729.robot.commands.ExtendUntilPressed;
import org.usfirst.frc.team2729.robot.commands.LinearPiston;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class DefensiveOneContainer extends CommandGroup{
	
	public DefensiveOneContainer(){
		addParallel(new LinearPiston(false));
		addSequential(new BinAlignHorLinear(0.9, 22), 1.5);
		addSequential(new BinAlignHorLinear(0.5, 28), 2.5);
		addSequential(new WaitCommand(0.3));
		addSequential(new LinearPiston(true));
	}
}
