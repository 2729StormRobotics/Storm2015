package org.usfirst.frc.team2729.robot.subsystems;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Subsystem;

public class LEDStrip extends Subsystem{
	//Modes
	public static final byte _Disabled        = 0;
	public static final byte _Teleop          = 1;
	public static final byte _Autonomous      = 2;
	public static final byte _MorseMode       = 3;
	public static final byte _BlackMagic      = 4;
	public static final byte _DoubleStack     = 5;
	public static final byte _TwoSpeedStack   = 6;
	public static final byte _RainbowDance    = 7;
	public static final byte _RandomMode      = 8;
	
	public static final byte _blueAlliance    = 0;
	public static final byte _redAlliance     = 1;
	public static final byte _invalidAlliance = 2;
	public static final byte _whatAlliance    = 3;
	
	public static boolean _connection = true;
	
	public static final String _socketIP="socket://10.27.29.100:6969";  
					//The IP of the Arduino, on same network as RoboRio	 
	private static byte _curMode=_Disabled;
	protected void initDefaultCommand() {}
	
	public LEDStrip(){}
	
	public void setMode(final int mode){
		new Thread(){
			int newMode;
			public void modeChange(){ 
				newMode = mode; //make the new mode we are using what we sent it to
				//if we are doing anything other than showing off
				//and because you can't use a switch for DriverStation.getInstance()
				if(DriverStation.getInstance().isAutonomous()){ 
					newMode = _Autonomous; 		
				}else
				if(DriverStation.getInstance().isOperatorControl()){
					newMode = _Teleop;
				}
				else if(DriverStation.getInstance().isTest()){
					newMode = _BlackMagic;
				}else{
					newMode = _Disabled;
				}
				if(_connection){
					try{
						Socket connect = new Socket("10.27.29.100", 6969);
						OutputStream out = connect.getOutputStream();
						out.write(newMode);
						if(newMode == _Teleop){
							DriverStation.Alliance side = DriverStation.getInstance().getAlliance();
							if(side == DriverStation.Alliance.Blue){
								out.write(_blueAlliance);
							}else if(side == DriverStation.Alliance.Red){
								out.write(_redAlliance);
							}else if(side == DriverStation.Alliance.Invalid){
								out.write(_invalidAlliance);
							}else out.write(_whatAlliance);
						}//for the pretty bouncing LEDs during teleop ^^^
						//if we got here we are done
						out.close();
						connect.close();
					}catch(IOException e){
						//if we're here we didn't connect
						System.out.println("Not Connected");
						_connection = false;
					}
				}
			}
		}.start();
	}
}
