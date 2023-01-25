// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Train extends SubsystemBase {
  /** Creates a new Train. */
  private final WPI_VictorSPX MLF = new WPI_VictorSPX(Constants.OperatorConstants.kPMLF);
  private final WPI_VictorSPX MLR = new WPI_VictorSPX(Constants.OperatorConstants.kPMLR);
  private final WPI_VictorSPX MRF = new WPI_VictorSPX(Constants.OperatorConstants.kPMRF);
  private final WPI_VictorSPX MRR = new WPI_VictorSPX(Constants.OperatorConstants.kPMRR);
  private final MotorControllerGroup mLeft = new  MotorControllerGroup(MLF, MLR) ;
  private final MotorControllerGroup mRight = new MotorControllerGroup(MRF, MRR);
  public DifferentialDrive mDrive = new DifferentialDrive(mLeft, mRight);

  private final AnalogInput ultrasonic = new AnalogInput(3);

  public Train() {}

  public void Drive(double ySpeed, double xSpeed){
    mDrive.arcadeDrive(xSpeed, ySpeed);
  }

  public void moverUno(double speed) {
    MRR.set(ControlMode.PercentOutput, speed);
  }

  public double[] getSonarValue() {
    double rawValue = ultrasonic.getValue();
    double voltage_scale_factor = 5/RobotController.getVoltage5V();
    double currentDistanceInches = rawValue * voltage_scale_factor * 0.0492;
    double feet = currentDistanceInches/12, inch = currentDistanceInches % 12;
    return new double[]{feet, inch};
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    double[] distance = getSonarValue();
    SmartDashboard.putNumber("Feet", Math.round(distance[0]));
    SmartDashboard.putNumber("Inch", Math.round(distance[1]));
  }
}
