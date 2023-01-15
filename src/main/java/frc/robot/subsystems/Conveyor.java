// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Conveyor extends SubsystemBase {
  /** Creates a new Conveyor. */
  private final Spark mBelt = new Spark(Constants.OperatorConstants.kPBelt);
  private final Spark mNEO = new Spark(Constants.OperatorConstants.kPNeo);
  public Conveyor() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void beltMove(double speed){
    mBelt.set(speed);
    mNEO.set(speed);
  }

}
