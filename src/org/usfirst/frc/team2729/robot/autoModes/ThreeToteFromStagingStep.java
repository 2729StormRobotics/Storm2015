package org.usfirst.frc.team2729.robot.autoModes;

import org.usfirst.frc.team2729.robot.commands.ChangeElevPosition;
import org.usfirst.frc.team2729.robot.commands.DriveForward;
import org.usfirst.frc.team2729.robot.commands.ElevatorClamp;
import org.usfirst.frc.team2729.robot.commands.RollerClamp;
import org.usfirst.frc.team2729.robot.commands.RollerSpinAuto;
import org.usfirst.frc.team2729.robot.commands.Turn;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.command.WaitForChildren;

public class ThreeToteFromStagingStep extends CommandGroup{

	private Command _moveUp(){
		CommandGroup _moveUpTwice = new CommandGroup();
		_moveUpTwice.addSequential(new ChangeElevPosition(1));
		_moveUpTwice.addSequential(new ChangeElevPosition(1));
		return _moveUpTwice;
	}
	
	public ThreeToteFromStagingStep(){
		addParallel(new RollerClamp(true));
		addSequential(new Turn(50));
		
		addParallel(new RollerSpinAuto(1.0));
		addSequential(new DriveForward(1.0, 200)); //todo determine distance to tote
		
		addSequential(new WaitCommand(0.5));
		addSequential(new RollerSpinAuto(0));
		addSequential(new RollerClamp(false));
		
		/*for(int i = 3; i>totes; i--){
			addSequential(new ChangeElevPosition(-1));
		}*/
		
		addSequential(new ChangeElevPosition(-1));
		addSequential(new ElevatorClamp(false));
		addSequential(new ChangeElevPosition(-1));
		
		addSequential(new ElevatorClamp(true));
		addParallel(_moveUp());
		addSequential(new DriveForward(-1.0, 200));
		/*for(int i = 2; i>totes; i--){
			addSequential(new ChangeElevPosition(1));
		}*/
		addSequential(new Turn(-50));
	}
}
