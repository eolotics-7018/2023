// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Conveyor;

public class ConveyorRotations extends CommandBase {

  private Conveyor s_conveyor;
  public static final double kP = 0.5, kI = 5, kD = 0.01, iLimit = 1;
  private double setpoint, errorSum, lastTimeStamp, lastError;
  private RelativeEncoder encoder;

  /** Creates a new ConveyorRotations. */
  public ConveyorRotations(Conveyor subsystem, double setpoint) {
    // Use addRequirements() here to declare subsystem dependencies.
    s_conveyor = subsystem;
    this.setpoint = setpoint;
    this.encoder = s_conveyor.getLeftEncoder();
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    errorSum = 0;
    lastTimeStamp = Timer.getFPGATimestamp();
    lastError = 0;
    encoder.setPosition(0);
    SmartDashboard.putString("Completado", "no");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double position = encoder.getPosition();
    
    // Cálculos
    double error = setpoint - position;
    double dt = Timer.getFPGATimestamp() - lastTimeStamp;
    
    if (Math.abs(error) < iLimit) {
      errorSum += error * dt;
    }

    double errorRate = (error - lastError) / dt;

    double outputSpeed = kP * error + kI * errorSum + kD * errorRate;
    s_conveyor.beltMove(outputSpeed);
    
    lastTimeStamp = Timer.getFPGATimestamp();
    lastError = error;

    SmartDashboard.putNumber("Error", error);
    SmartDashboard.putNumber("outputSpeed", outputSpeed);
    SmartDashboard.putNumber("position", position);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    s_conveyor.beltMove(0);
    SmartDashboard.putString("Completado", "si");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return lastError < 5;
  }
}
