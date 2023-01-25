// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Train;

public class TimedTrain extends CommandBase {

  private Train driveTrain;

  /** Creates a new TimedTrain. */
  public TimedTrain(Train subsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    driveTrain = subsystem;
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    driveTrain.Drive(0.50, 0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveTrain.Drive(-0.5, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    double[] distance = driveTrain.getSonarValue();
    return distance[0] <= 4;
  }
}
