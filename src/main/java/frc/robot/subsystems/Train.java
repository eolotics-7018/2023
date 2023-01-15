// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
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
  public Train() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void Drive(double ySpeed, double xSpeed){
    mDrive.arcadeDrive(ySpeed, xSpeed);
  }

  public void moverUno(double speed) {
    MRR.set(ControlMode.PercentOutput, speed);
  }
}
