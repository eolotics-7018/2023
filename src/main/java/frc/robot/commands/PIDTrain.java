// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Train;

public class PIDTrain extends CommandBase {

  private Train driveTrain;
  public static double kP = 0.004, kI = 0.05, kD = 0.01, iLimit = 0.5;
  private double setpoint, errorSum, lastTimeStamp, lastError, feets, inches;

  /** Creates a new TimedTrain. */
  public PIDTrain(Train subsystem, int feets, int inches) {
    // Use addRequirements() here to declare subsystem dependencies.
    driveTrain = subsystem;
    this.feets = feets;
    this.inches = inches;
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    setpoint = (feets * 12) + inches;
    errorSum = 0;
    lastTimeStamp = Timer.getFPGATimestamp();
    lastError = 0;
    SmartDashboard.putString("Completado", "no");
    driveTrain.mDrive.setMaxOutput(0.5);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double distance = driveTrain.getSonarValue();

    // Cálculos
    double error = distance - setpoint;
    // double dt = Timer.getFPGATimestamp() - lastTimeStamp;

    // if (Math.abs(error) < iLimit) {
      // errorSum += error * dt;
    // }

    // double errorRate = (error - lastError) / dt;

    // double outputSpeed = kP * error + kI * errorSum + kD * errorRate;
    double outputSpeed = kP * error;
    driveTrain.Drive(outputSpeed, 0);

    lastTimeStamp = Timer.getFPGATimestamp();
    lastError = error;

    // SmartDashboard.putNumber("Error", error);
    SmartDashboard.putNumber("outputSpeed", outputSpeed);
    SmartDashboard.putNumber("Setpoint", setpoint);
    SmartDashboard.putNumber("Distancia", distance);
    // SmartDashboard.putNumberArray("grafico", new double[]{outputSpeed,setpoint,distance});
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveTrain.Drive(0, 0);
    SmartDashboard.putString("Completado", "si");
    SmartDashboard.clearPersistent("Distancia");
    SmartDashboard.clearPersistent("outputSpeed");
    driveTrain.mDrive.setMaxOutput(1);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return lastError == 0;
  }
}
