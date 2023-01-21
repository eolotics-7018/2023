// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Conveyor extends SubsystemBase {
  /** Creates a new Conveyor. */
  private final CANSparkMax mBelt = new CANSparkMax(Constants.OperatorConstants.kPBelt, MotorType.kBrushless);
  private final CANSparkMax mBeltUp = new CANSparkMax(Constants.OperatorConstants.kPBeltUp, MotorType.kBrushless);

  private boolean beltUpOn = true;

  private RelativeEncoder encoder;
  private double motorSpeedProportion = 1;

  public Conveyor() {
    mBelt.restoreFactoryDefaults();
    mBeltUp.restoreFactoryDefaults();

    encoder = mBelt.getEncoder();
  }

  public void beltMove(double speed){
    mBelt.set(speed * motorSpeedProportion);
    if (beltUpOn) {
      mBeltUp.set(speed * motorSpeedProportion);
    }
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

  public void toggleBeltUp() {
    beltUpOn = !beltUpOn;
  }

  public RelativeEncoder getEncoder() {
    return encoder;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Encoder position: ", encoder.getPosition());
    SmartDashboard.putNumber("Encoder velocity: ", encoder.getVelocity());
    SmartDashboard.putNumber("Output Percentage", motorSpeedProportion);
  }

}
