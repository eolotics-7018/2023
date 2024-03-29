// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class DrivetrainConstants {
    public final static int kPMotorLeftFront = 0; 
    public final static int kPMotorRightFront = 1; 
    public final static int kPMotorLeftRear = 2; 
    public final static int kPMotorRightRear = 3; 
  }
  
  public final static class ConveyorConstants {
    public final static int kPBelt = 1;
    public final static int kPBeltUp = 0;
    public final static double[] kHighGrid = {0.9, 0.4};
    public final static double[] kMiddleGrid = {0.8, 0.3};
    public final static double[] kDownGrid = {0.5, 0};
  }
  
  public final class Wing {
    public final static int kPiston = 2;
  }
}
