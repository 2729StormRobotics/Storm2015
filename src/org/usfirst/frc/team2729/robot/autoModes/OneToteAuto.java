package org.usfirst.frc.team2729.robot.autoModes;

import org.usfirst.frc.team2729.robot.Robot;
import org.usfirst.frc.team2729.robot.commands.*;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
public class OneToteAuto extends CommandGroup {
	public OneToteAuto(){
		//where is it set up?
		//Drive forward with rollers, unclamp, raise elevator, drive back
		addParallel(new Roller(0.5));
		addSequential(new DriveForward(0.5, 200));
		addSequential(new Roller(0));
		addSequential(new Unclamp());
		addSequential(new RollerClamp(false));
		addSequential(new AutoElevator(-1), 2.5);
	}

}
