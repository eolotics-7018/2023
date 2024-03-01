// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.DrivetrainConstants.*;

public class Drivetrain extends SubsystemBase {
  private final WPI_VictorSPX mLeftFront = new WPI_VictorSPX(kPMotorLeftFront);
  private final WPI_VictorSPX mLeftRear = new WPI_VictorSPX(kPMotorLeftRear);
  private final WPI_VictorSPX mRightFront = new WPI_VictorSPX(kPMotorRightFront);
  private final WPI_VictorSPX mRightRear = new WPI_VictorSPX(kPMotorRightRear);

  private final MotorControllerGroup mLeft = new  MotorControllerGroup(mLeftFront, mLeftRear);
  private final MotorControllerGroup mRight = new MotorControllerGroup(mRightFront, mRightRear);

  private DifferentialDrive mainDrive = new DifferentialDrive(mLeft, mRight);

  public Drivetrain() {}
  
  public void Drive(double ySpeed, double xSpeed){
    mainDrive.arcadeDrive(xSpeed, ySpeed);
  }

  @Override
  public void periodic() {}
}