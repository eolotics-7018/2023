// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import static frc.robot.Constants.Wing.*;

public class Wing extends SubsystemBase {
  private Spark mPiston = new Spark(kPiston);
  
  public Wing() {}

  public void pistonMove(double speed){
    mPiston.set(speed);
  }

  @Override
  public void periodic() {}
  
}
