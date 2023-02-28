// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.Autos;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Drivetrain;
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
  private final CommandXboxController mStick = new CommandXboxController(0);
  // private final CommandJoystick mStickDrivetrain = new CommandJoystick(1);
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final Drivetrain s_drivetrain = new Drivetrain();
  private final Conveyor s_conveyor = new Conveyor();
  private final Wing s_wing = new Wing();
  
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    s_conveyor.setDefaultCommand(new RunCommand(()->s_conveyor.beltMove(0.0), s_conveyor));
    s_wing.setDefaultCommand(new RunCommand(()->s_wing.pistonMove(0.0), s_wing));
    s_drivetrain.setDefaultCommand(new RunCommand(()-> s_drivetrain.Drive(0, 0), s_drivetrain));
    // s_drivetrain.setDefaultCommand(new RunCommand(()->s_drivetrain.Drive(mStick.getLeftY(), mStick.getRightX()), s_drivetrain));
    
    // Configure the trigger bindings
    configureBindings();
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

    mStick.rightTrigger().whileTrue(new RunCommand(()-> s_conveyor.beltMove(mStick.getRightTriggerAxis()), s_conveyor));
    mStick.leftTrigger().whileTrue(new RunCommand(()-> s_conveyor.beltMove(-mStick.getLeftTriggerAxis()), s_conveyor));
    mStick.a().onTrue(new RunCommand(()->s_wing.pistonMove(1.0), s_wing).withTimeout(3));
    mStick.b().onTrue(new RunCommand(()->s_wing.pistonMove(-1.0), s_wing).withTimeout(3));
    mStick.y().onTrue(new InstantCommand(() -> s_drivetrain.desacelerar(), s_drivetrain));
    
    mStick.povRight().onTrue(new InstantCommand(()-> s_conveyor.faster(), s_conveyor));
    mStick.povLeft().onTrue(new InstantCommand(()-> s_conveyor.slower(), s_conveyor));
    mStick.start().and(mStick.button(7)).toggleOnTrue(new RunCommand(()->s_drivetrain.Drive(mStick.getLeftY(), mStick.getRightX()), s_drivetrain));
    // mStick.leftStick().onTrue(new InstantCommand(()-> s_drivetrain.invert(), s_drivetrain));
  }
  
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return Autos.exampleAuto(m_exampleSubsystem);

    // return Commands.sequence(
    //   new PIDDrivetrain(s_drivetrain, 4, 8)

      // new RunCommand(()-> s_drivetrain.Drive(0.4, 0), s_drivetrain).until(()-> {return s_drivetrain.isCloserThan(3, 8);}),
      // new InstantCommand(()->s_drivetrain.Drive(0, 0), s_drivetrain)

      // // new TimedDrivetrain(s_drivetrain), 
      // new RunCommand(()-> s_conveyor.setSpeedProportion(0.75).beltMove(1), s_conveyor).withTimeout(3)
      // new PrintCommand("execution").repeatedly().until(()-> s_drivetrain.isCloserThan(2, 2))
    // );
  }
}
