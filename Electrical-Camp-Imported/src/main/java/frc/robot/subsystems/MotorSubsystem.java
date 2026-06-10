// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import static frc.robot.Constants.MotorConstants.*;

public class MotorSubsystem extends SubsystemBase {
  /** Creates a new MotorSubsystem. */
  private SparkMax m_Neo550 = new SparkMax(NEO550_ID, MotorType.kBrushless);
  private SparkMaxConfig m_neo550Config = new SparkMaxConfig();
  private SparkMax m_Neo = new SparkMax(NEO_ID, MotorType.kBrushless);
  private SparkMaxConfig m_neoConfig = new SparkMaxConfig();

  public MotorSubsystem() {
    configureNeo550();
  }

  private void configureNeo550() {
    m_neo550Config.smartCurrentLimit(NEO_CURRENT_LIMIT);
    m_neo550Config.inverted(false);
    m_neo550Config.idleMode(IdleMode.kCoast);

    m_Neo550.configure(m_neo550Config, ResetMode.kResetSafeParameters,
        PersistMode.kPersistParameters);
  }

  private void configureNeo() {
    m_neoConfig.smartCurrentLimit(NEO_CURRENT_LIMIT);
    m_neoConfig.inverted(false);
    m_neoConfig.idleMode(IdleMode.kCoast);

    m_Neo550.configure(m_neoConfig, ResetMode.kResetSafeParameters,
        PersistMode.kPersistParameters);
  }

  public Command runNeo550() {
    return run(() -> {
      m_Neo550.set(.05);
    });
  }

  public Command runNeo() {
    return run(() -> {
      m_Neo.set(.05);
    });
  }

  @Override
  public void periodic() {
  }
}
