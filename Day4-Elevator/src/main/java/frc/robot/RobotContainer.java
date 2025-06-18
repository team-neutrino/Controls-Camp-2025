// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.factories.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.*;
import edu.wpi.first.wpilibj2.command.button.*;

public class RobotContainer {

  private final LedSubsystem m_led_subsystem = new LedSubsystem();
  private final LedFactory m_led_factory = new LedFactory(m_led_subsystem);
  private final CommandXboxController XBOX_CONTROLLER_0 = new CommandXboxController(0);

  public RobotContainer() {
    m_led_subsystem.setDefaultCommand(m_led_factory.defaultCommand());
    XBOX_CONTROLLER_0.a().whileTrue(m_led_factory.white());
    XBOX_CONTROLLER_0.b().whileTrue(m_led_factory.orange());
    XBOX_CONTROLLER_0.x().whileTrue(m_led_factory.toggleWhiteAndOrange());
    XBOX_CONTROLLER_0.y().whileTrue(m_led_factory.rainbow());
  }

  public Command getAutonomousCommand() {
    return new InstantCommand();
  }
}
