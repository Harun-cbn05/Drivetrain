// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Timer;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private final WPI_TalonSRX leftFrontMotor = new WPI_TalonSRX(0);
  private final WPI_TalonSRX leftMidMotor = new WPI_TalonSRX(1);
  private final WPI_TalonSRX leftBackMotor = new WPI_TalonSRX(2);
  private final WPI_TalonSRX rightFrontMotor = new WPI_TalonSRX(3);
  private final WPI_TalonSRX rightMidMotor = new WPI_TalonSRX(4);
  private final WPI_TalonSRX rightBackMotor = new WPI_TalonSRX(5);
  
  private final DifferentialDrive roboDrive = new DifferentialDrive(leftFrontMotor, rightFrontMotor);
  private final Joystick driverController = new Joystick(0);
  private final Timer timer = new Timer();

  @Override
  public void robotInit() {
    leftMidMotor.follow(leftFrontMotor);
    leftBackMotor.follow(leftFrontMotor);
    rightMidMotor.follow(rightFrontMotor);
    rightBackMotor.follow(rightFrontMotor);

    rightFrontMotor.setInverted(true);
    rightMidMotor.setInverted(true);
    rightBackMotor.setInverted(true);
  }
  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {
    timer.restart();
  }

  @Override
  public void autonomousPeriodic() {
    if (timer.get() < 2.0) {
      roboDrive.arcadeDrive(0.5, 0.0, false);
    } else {
      roboDrive.stopMotor();
    }
  }

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {
    roboDrive.arcadeDrive(driverController.getRawAxis(1), -driverController.getRawAxis(0));
    if (driverController.getRawButton(1)) {
      leftFrontMotor.set(0);
      leftMidMotor.set(0);
      leftBackMotor.set(0);
      rightFrontMotor.set(0);
      rightMidMotor.set(0);
      rightBackMotor.set(0);
    }
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}
