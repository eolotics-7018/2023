// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

// import frc.robot.commands.Autos;
import frc.robot.commands.ConveyorRotations;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Train;
import frc.robot.subsystems.Wing;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
//Recuerdo de Juan Jimenez Pineda
//Pertenecí 4 años a la competencia 
//Todos me la pelaban menos Edy
//Recuerden mi código y recuerdenme a mi 


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final CommandXboxController mStick = new CommandXboxController(Constants.OperatorConstants.KPControl);
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final Train s_DriveTrain = new Train();
  private final Conveyor s_Conveyor = new Conveyor();
  private final Wing s_Wing = new Wing();
  
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
    
    // s_DriveTrain.setDefaultCommand(new RunCommand(()->s_DriveTrain.Drive(mStick.getLeftY(), mStick.getRightX()), s_DriveTrain));
    s_Conveyor.setDefaultCommand(new RunCommand(()->s_Conveyor.beltMove(0.0), s_Conveyor));
    s_Wing.setDefaultCommand(new RunCommand(()->s_Wing.pistonMove(0.0), s_Wing));
    s_DriveTrain.setDefaultCommand(new RunCommand(()-> s_DriveTrain.Drive(mStick.getLeftY(), mStick.getRightX()), s_DriveTrain));
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    new Trigger(m_exampleSubsystem::exampleCondition)
        .onTrue(new ExampleCommand(m_exampleSubsystem));

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.

    mStick.rightTrigger().whileTrue(new RunCommand(()-> s_Conveyor.beltMove(mStick.getRightTriggerAxis()), s_Conveyor));
    mStick.leftTrigger().whileTrue(new RunCommand(()-> s_Conveyor.beltMove(-mStick.getLeftTriggerAxis()), s_Conveyor));
    mStick.a().whileTrue(new RunCommand(()->s_Wing.pistonMove(1.0), s_Wing));
    mStick.b().whileTrue(new RunCommand(()->s_Wing.pistonMove(-1.0), s_Wing));
    mStick.povRight().onTrue(new InstantCommand(()-> s_Conveyor.faster(), s_Conveyor));
    mStick.povLeft().onTrue(new InstantCommand(()-> s_Conveyor.slower(), s_Conveyor));
  }
  
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    // return Autos.exampleAuto(m_exampleSubsystem);
    return new ConveyorRotations(s_Conveyor, 500);
  }
}
