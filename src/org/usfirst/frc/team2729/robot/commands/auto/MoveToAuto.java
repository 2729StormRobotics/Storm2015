package org.usfirst.frc.team2729.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MoveToAuto extends CommandGroup {

    public MoveToAuto() {
    	addSequential(new DriveVector(0,false,-100,1));
    }
}
