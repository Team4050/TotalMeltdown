/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;


public class Robot extends TimedRobot {
	/* TODO: Why are some of these variables "private" and some aren't private or public? */
	/* TODO: Adding spacing between variable groups (group up all the "Spark"s for example) makes it easier to read */
	//Dashboard
	SendableChooser<Integer> FireSpeedSelect = new SendableChooser<>();

	//Drive Motor Variable init
	private Spark FrontLeft;
	private Spark FrontRight;
	private Spark RearLeft;
	private Spark RearRight;
	//Joystick/Xbox Variable init
	private Joystick xbox;
	//Limit Switches
	DigitalInput LoaderLimit;
	DigitalInput PneuLimit;

	//Solenoid
	DoubleSolenoid FireSolenoid = new DoubleSolenoid(1, 2);
	
	//ultrasonic sensor
	AnalogInput ultraSensor;

	//LinearActuator Varible init
	Spark LinearActuator;
	Spark FireMotor;
	Spark LoadMotor;
	//Side Groups
	private SpeedControllerGroup leftSC;
	private SpeedControllerGroup rightSC;
	//RobotDrive
	private DifferentialDrive robotDrive;
	@Override /* TODO: You might want to add a space between the variables above and this method below */
	public void robotInit() 
	{
		/* TODO: Some weird spacing here? */

		FireSpeedSelect.setDefaultOption("0", 0);
		FireSpeedSelect.addOption("10%", 1);
		FireSpeedSelect.addOption("20%", 2);
		FireSpeedSelect.addOption("30%", 3);
		FireSpeedSelect.addOption("40%", 4);
		FireSpeedSelect.addOption("50%", 5);
		FireSpeedSelect.addOption("60%", 6);
		FireSpeedSelect.addOption("70%", 7);
		FireSpeedSelect.addOption("80%", 8);
		FireSpeedSelect.addOption("90%", 9);
		FireSpeedSelect.addOption("100%", 10);

		SmartDashboard.putData("Fire Speed", FireSpeedSelect);

		//assigning Data Values
			//Joysticks
			
		LoaderLimit = new DigitalInput(0);
		PneuLimit = new DigitalInput(1);

		//ultrasonic
		ultraSensor = new AnalogInput(0);

	/* TODO: Unnecessary space here? Also the tabbing above is kinda odd */

		
    /* TODO: This line needs to be tabbed in one */
    CameraServer.getInstance().startAutomaticCapture();
		xbox = new Joystick(0);
			//Drive Motors		/* TODO: Why tab in the comments? */
		FrontLeft = new Spark(0);
		RearLeft = new Spark(1);
		FrontRight = new Spark(2);
		RearRight = new Spark(3);
			//Actuator Motors
		LinearActuator = new Spark(4);
		FireMotor = new Spark(5);
		LoadMotor = new Spark(6);
		
		//Invert Motors
		FrontLeft.set(-1);
		RearLeft.set(-1);
		//Asign Speed Groups
		leftSC = new SpeedControllerGroup(FrontLeft, RearLeft);
		rightSC = new SpeedControllerGroup(FrontRight, RearRight);
		//Asign The Drive system
		robotDrive = new DifferentialDrive(leftSC, rightSC);
		
  }
 /* TODO: There is some mweird spacing around here */
  
  @Override
  public void teleopPeriodic() 
  {	/* TODO: More weird spacing? */

		//Variables

		
		double ultravolt = ultraSensor.getVoltage() / 0.009766;
		boolean FireMotorReady = false;
		int direction = xbox.getPOV(0);
		boolean abutton = xbox.getRawButton(1);
		double rtrigger = xbox.getRawAxis(3);
		double FireSpeed = 0;
		boolean rbumper = xbox.getRawButton(6);
		//Actuator Code			/* TODO: Some really weird tabbing here */
			//Linear Actuator
				if (direction == 180)
    		{ 
			/* TODO: I am not a fan of the format of these curly braces since they're hard to read, but if you're going to use them, make sure they're indented properly */
     	 		LinearActuator.set(1);
    		} else if(direction == 0)
    		{
      		LinearActuator.set(-1);
    		} else 
    		{
      		LinearActuator.set(0);
				}
			
			//Loading Motor
				
			SmartDashboard.putNumber("UltraVolts", ultravolt);

			if (LoaderLimit.get() == true)
			{
				SmartDashboard.putBoolean("Frisbee Ready", true);
			} else if (LoaderLimit.get() == false)
			{
				SmartDashboard.putBoolean("Frisbee Ready", false);
			}
			
				if (abutton == true)
				{
					if (LoaderLimit.get() == true)
					{
						LoadMotor.set(0.75);
					}else if (LoaderLimit.get() == false)
					{
						LoadMotor.set(0);
					}

				} else 
				{
					LoadMotor.set(0);
				}

				/* TODO: Try using a Switch statement here, it will clean up this code a lot */
				if (FireSpeedSelect.getSelected() == 0)
				{
					FireSpeed = 0;
				} else if (FireSpeedSelect.getSelected() == 1)
				{
					FireSpeed = 0.1;
				} else if (FireSpeedSelect.getSelected() == 2)
				{
					FireSpeed = 0.2;
				}else if (FireSpeedSelect.getSelected() == 3)
				{
					FireSpeed = 0.3;
				} else if (FireSpeedSelect.getSelected() == 4)
				{
					FireSpeed = 0.4;
				} else if (FireSpeedSelect.getSelected() == 5)
				{
					FireSpeed = 0.5;
				}else if (FireSpeedSelect.getSelected() == 6)
				{
					FireSpeed = 0.6;
				}
				else if (FireSpeedSelect.getSelected() == 7)
				{
					FireSpeed = 0.7;
				} else if (FireSpeedSelect.getSelected() == 8)
				{
					FireSpeed = 0.8;
				} else if (FireSpeedSelect.getSelected() == 9)
				{
					FireSpeed = 0.9;
				}else if (FireSpeedSelect.getSelected() == 10)
				{
					FireSpeed = 1;
				}


			//Fire Motor
				if (rtrigger >= 0.5)
				{
					FireMotor.set(FireSpeed);
					FireMotorReady = true;
				/* TODO: Weird tabbing */
				xbox.setRumble(RumbleType.kRightRumble, 1);
				xbox.setRumble(RumbleType.kLeftRumble, 1);
				}else
				{
					FireMotor.set(0);
					FireMotorReady = false;

					xbox.setRumble(RumbleType.kRightRumble, 0);
					xbox.setRumble(RumbleType.kLeftRumble, 0);
				}

			//Pneumatics			/* TODO: Try to combine these If statements. Example: if (rbumper && PneuLimit.get() && FireMotorReady == true) {Do things} else {Do other things} */
				if (rbumper == true)
				{
					if (PneuLimit.get() == true)
					{
						if (FireMotorReady == true)
						{
							FireSolenoid.set(DoubleSolenoid.Value.kForward);
						}
						else
						{
							FireSolenoid.set(DoubleSolenoid.Value.kReverse);
						}
					}
					else
					{
						FireSolenoid.set(DoubleSolenoid.Value.kReverse);
					}
				} else
				{
					FireSolenoid.set(DoubleSolenoid.Value.kReverse);
				}

			//misc  /* TODO: Same thing here, might be able to simplify these if statements */
				if (FireMotorReady == true)
				{
					if (PneuLimit.get() == true)
					{
						SmartDashboard.putBoolean("Ready To Fire", true);
					}
					else if (PneuLimit.get() == false)
					{
						SmartDashboard.putBoolean("Ready To Fire", false);
					}
				} else if (FireMotorReady == false)	
				{
					SmartDashboard.putBoolean("Ready To Fire", false);
				}

		//Drive Code  /* TODO: Not quite tabbed correctly? */
    robotDrive.tankDrive(-xbox.getRawAxis(1), -xbox.getRawAxis(5));
    
	}	
}