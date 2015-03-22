package org.usfirst.frc.team2729.robot.autoModes;

import org.usfirst.frc.team2729.robot.Robot;
import org.usfirst.frc.team2729.robot.commands.DriveForward;
import org.usfirst.frc.team2729.robot.commands.LinearPiston;
import org.usfirst.frc.team2729.robot.commands.ResetHall;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class SecretProject extends CommandGroup{

	/**
	 * It's a secret to everybody
	 */
	public SecretProject(){
		addSequential(new OneContainerPiece());
		addSequential(new LinearPiston(false));
		addSequential(new WaitCommand(0.75));
		addParallel(_moveReset());
		addSequential(new DriveForward(-0.8, 655));
		addSequential(new TwoContainerAuto());
	}
	
	private Command _moveReset(){
		CommandGroup _moveReset = new CommandGroup();
		_moveReset.addSequential(new Command(){
			@Override
			protected void initialize() {}
			@Override
			protected void execute() {Robot.linearArm.moveArm(-1);}
			@Override
			protected boolean isFinished() {return Robot.linearArm.getRawHallCount() >= 47;}
			@Override
			protected void end() {Robot.linearArm.moveArm(0);}
			@Override
			protected void interrupted() {}
			
		}, 1.4);
		_moveReset.addSequential(new ResetHall());
		return _moveReset;
	}
	
}
