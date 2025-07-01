// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.*;
import edu.wpi.first.wpilibj2.command.button.*;

public class RobotContainer {

  private final FakeSubsystem m_fake_subsystem = new FakeSubsystem();
  private final CommandXboxController XBOX_CONTROLLER_0 = new CommandXboxController(0);

  public RobotContainer() {
    XBOX_CONTROLLER_0.a().whileTrue(m_fake_subsystem.countUpCommand());
  }

  public Command getAutonomousCommand() {
    return new InstantCommand();
  }
}
