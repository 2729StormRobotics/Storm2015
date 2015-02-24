package org.usfirst.frc.team2729.robot.subsystems;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

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
	
	public static boolean _connection = false;
	
	public static final String _socketIP="socket://10.27.29.100:1024";  
					//The IP of the Arduino, on same network as RoboRio	 
	private static int _curMode=_Disabled;
    static SocketAddress arduinoAddress = new InetSocketAddress("10.27.29.100",1024);
    static OutputStream _writeOut;
	public LEDStrip(){}
	protected void initDefaultCommand() {}

	//public void setMode(final int mode){
	//	new Thread(){
			public void setMode(final int mode) { 
				_curMode = mode; //make the new mode we are using what we sent it to
				//if we are doing anything other than showing off
				//and because you can't use a switch for DriverStation.getInstance()
				if(DriverStation.getInstance().isAutonomous()){ 
					_curMode = _Autonomous; 		
				}else
				if(DriverStation.getInstance().isOperatorControl()){
					_curMode = _Teleop;
				}
				else if(DriverStation.getInstance().isTest()){
					_curMode = _BlackMagic;
				}else{
					_curMode = _Disabled;
				}
				if(!_connection){
					connect();
				}
				if(_curMode == _Teleop){
					try{
					DriverStation.Alliance alliance = DriverStation.getInstance().getAlliance();
					if(alliance == DriverStation.Alliance.Blue) 
						_writeOut.write(_blueAlliance);
					else if(alliance == DriverStation.Alliance.Red) 
						_writeOut.write(_redAlliance);
					else if(alliance == DriverStation.Alliance.Invalid) 
						_writeOut.write(_invalidAlliance);
					else 
						_writeOut.write(_whatAlliance);
					}catch(IOException e ){
						System.out.println("Failure to change Alliance: AKA You're not Italy.");
						_connection=false;
						connect();
					}
					
				}
			}
			public static void connect(){
				try(
						Socket arduino= new Socket("10.27.29.100",1024);
					) {
					while(!_connection){
						_writeOut = arduino.getOutputStream();
						arduino.bind(arduinoAddress);
						if(arduino.isConnected()) _connection=true;						
					}
						
						/*Socket connect = new Socket("10.27.29.100", 1024);
						OutputStream out = connect.getOutputStream();
						out.write(_curMode);
						if(_curMode == _Teleop){
							out.write((int) (SmartDashboard.getNumber("String Pot")*1000));
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
						_connection = true;*/
					}catch(IOException e){
						//if we're here we didn't connect
						System.out.println("Not Connected");
						_connection = false;
					}
			}
			public static void stringPotLEDs(double stringPot){
				try{
					_writeOut.write((int)(stringPot*1000));
				}catch(IOException e){
					System.out.println("Failure to send String Pot Data to Arduino.");
					_connection=false;
					connect();
				}
				
			}
			public static void armsClamped(boolean clamped){
				try{
					_writeOut.write((!clamped ? 1: 0));
				}catch(IOException e){
					System.out.println("Failed to tell the Arduino if the arm is clamped. "
							+ "Yell at it loud enough and it might hear.");
					_connection=false;
					connect();
				}
			}
	//	}.start();
	//}
}
