package org.usfirst.frc.team2729.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class RollerSafety extends CommandGroup{
	
	public RollerSafety(){
		/*addParallel(new RollerSpin(0));
		addSequential(new RollerClamp(false));*/
		addSequential(new WaitCommand(0.5));
	}
	
}
