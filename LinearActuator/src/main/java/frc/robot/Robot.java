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

  private Joystick xbox;

  Spark LinearActuator = new Spark(3);
  Spark ShootMotor = new Spark(4);

  

  @Override
  public void robotInit() 
  {
    xbox = new Joystick(0);
    LinearActuator = new Spark(3);
    ShootMotor = new Spark(4);
    
    
  }

  
  @Override
  public void teleopPeriodic() 
  {
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
    
  }

}
