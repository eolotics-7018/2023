// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxAlternateEncoder;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static com.revrobotics.CANSparkMaxLowLevel.MotorType.*;
import static frc.robot.Constants.EncoderConstants.*;
import frc.robot.Constants;

public class Conveyor extends SubsystemBase {
  private final Spark mBelt = new Spark(Constants.OperatorConstants.kPBelt);
  private final Spark mBeltUp = new Spark(Constants.OperatorConstants.kPBeltUp);

  private final CANSparkMax leftSparkMax = new CANSparkMax(kPEncoderLeft, kBrushed);
  private final CANSparkMax rightSparkMax = new CANSparkMax(kPEncoderRight, kBrushed);
  private final RelativeEncoder leftEncoder; 
  private final RelativeEncoder rightEncoder; 

  private double motorSpeedProportion = 1;

  public Conveyor() {
    mBelt.setInverted(true);

    leftSparkMax.restoreFactoryDefaults();
    rightSparkMax.restoreFactoryDefaults();

    leftEncoder = leftSparkMax.getAlternateEncoder(SparkMaxAlternateEncoder.Type.kQuadrature, 4096);
    rightEncoder = rightSparkMax.getAlternateEncoder(SparkMaxAlternateEncoder.Type.kQuadrature, 4096);
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

  public void stop() {
    mBelt.stopMotor();
  }

  public Conveyor setSpeedProportion(double speed) {
    motorSpeedProportion = speed;
    return this;
  }
  
  public RelativeEncoder getLeftEncoder() {
    return leftEncoder;
  }

  public RelativeEncoder getRightEncoder() {
    return rightEncoder;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Output Percentage", motorSpeedProportion);
    // SmartDashboard.putNumber("Left Position", leftEncoder.getPosition() * leftEncoder.getPositionConversionFactor());
    // SmartDashboard.putNumber("Right Postion", rightEncoder.getPosition() * rightEncoder.getPositionConversionFactor());
    // SmartDashboard.putNumber("Left Encoder", leftEncoder.getVelocity());
    // SmartDashboard.putNumber("Right Encoder", rightEncoder.getVelocity());
  }

}
