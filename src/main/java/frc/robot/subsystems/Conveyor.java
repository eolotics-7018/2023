// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.ConveyorConstants.*;

public class Conveyor extends SubsystemBase {
  private final Spark mBelt = new Spark(kPBelt);
  private final Spark mBeltUp = new Spark(kPBeltUp);

  private double motorSpeedProportion = 1;

  public Conveyor() {
    mBelt.setInverted(true);
  }

  public void beltMove(double speed){
    mBelt.set(speed * motorSpeedProportion);
    mBeltUp.set(speed * motorSpeedProportion);
  }

  public void faster() {
    if (motorSpeedProportion < 1) {
      motorSpeedProportion += 0.05;
    }
  }

  public void slower() {
    if (motorSpeedProportion > 0) {
      motorSpeedProportion -= 0.05;
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Output Percentage", motorSpeedProportion);
  }

}
