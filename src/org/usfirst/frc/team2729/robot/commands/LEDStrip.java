package org.usfirst.frc.team2729.robot.commands;

import edu.wpi.first.wpilibj.command.Subsystem;

public class LEDStrip extends Subsystem{
	public static final int _Disabled        =0;
	public static final int _TeleopMode      =1;
	public static final int _AutonomousMode  =2;
	public static final int _MorseMode       =3;
	public static final int _BlackMagic      =4;
	public static final int _DoubleStack     =5;
	public static final int _TwoSpeedStack   =6;
	public static final int _RainbowDance    =7;
	public static final int _RandomMode      =8;
	
	public static final int _blueAlliance=0;
	public static final int _redAlliance=1;
	
	public static final String _socketIP="socket://10.27.29.100:6969"; //because I can
																	  //and also I'm imature
	private static int _curMode=_Disabled;
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
	
}
