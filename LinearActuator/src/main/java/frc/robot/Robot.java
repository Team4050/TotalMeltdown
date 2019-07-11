/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Joystick;


public class Robot extends TimedRobot {
	//Drive Motor Variable init
	private Spark FrontLeft;
	private Spark FrontRight;
	private Spark RearLeft;
	private Spark RearRight;
	//Joystick/Xbox Variable init
	private Joystick xbox;
	private Joystick leftStick;
	private Joustick rightStick;
	//LinearActuator Varible init
	Spark LinearActuator = new Spark(3);
	Spark ShootMotor = new Spark(4);
	//Side Groups
	private SpeedControllerGroup left;
	private SpeedControllerGroup right;
	//RobotDrive
	private DifferentialDrive robotDrive;
	@Override
	public void robotInit() 
	{
		//assigning Data Values
			//Joysticks
		leftStick = new Joystick(0);
		rightStick = new Joystick(1);
		xbox = new Joystick(2);
			//Drive Motors
		FrontLeft = new Spark(0);
		RearLeft = new Spark(1);
		FrontRight = new Spark(2);
		RearRight = new Spark(3);
			//Actuator Motors
		LinearActuator = new Spark(4);
		ShootMotor = new Spark(5);
		
		//Invert Motors
		FrontLeft.set(-1);
		RearLeft.set(-1);
		//Asign Speed Groups
		right = new SpeedControllerGroup(FrontLeft,RearLeft);
		left = new SpeedControllerGroup(FrontRight,RearRight);
		//Asign The Drive system
		robotDrive = new DifferentialDrive(left,right);
		
  }

  
  @Override
  public void teleopPeriodic() 
  {
		//Linear Actuator
    boolean buttonValue;
    int direction = xbox.getPOV(0);
    buttonValue = xbox.getRawButton(5);

    if (buttonValue == true)
    {
      ShootMotor.set(0.7);
    } else if (buttonValue == false)
      {
        ShootMotor.set(0);
      }

    if (direction == 180)
    {
      LinearActuator.set(1);
    } else if(direction == 0)
    {
      LinearActuator.set(-1);
    } else 
    {
      LinearActuator.set(0);
		}
		//Drive Code
		robotDrive.tankDrive(leftStick.getY(), rightStick.getY());
    
  }

}
