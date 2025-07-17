// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkBase.*;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.ClosedLoopSlot;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLimitSwitch;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.ClosedLoopConfig.FeedbackSensor;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkFlexConfig;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.ElevatorConstants.*;
import static frc.robot.Constants.CANRateConstants.*;

public class Elevator extends SubsystemBase {
  private SparkFlex m_motor = new SparkFlex(MOTOR_ID, MotorType.kBrushless);
  private SparkFlex m_follower = new SparkFlex(FOLLOWER_ID, MotorType.kBrushless);
  private RelativeEncoder m_encoder = m_motor.getEncoder();
  private SparkLimitSwitch m_lowLimit = m_motor.getReverseLimitSwitch();
  private SparkClosedLoopController m_pid = m_motor.getClosedLoopController();
  private SparkFlexConfig m_config = new SparkFlexConfig();
  private SparkFlexConfig m_followerConfig = new SparkFlexConfig();

  private double m_targetHeight = DEFAULT_HEIGHT;
  private double m_FFStage1 = STAGE_1_FF;
  private double m_FFStage2 = STAGE_2_FF;

  public Elevator() {
    m_config
        .inverted(true)
        .idleMode(IdleMode.kBrake);
    m_config.encoder
        .positionConversionFactor((1))
        .velocityConversionFactor(1);
    m_config.closedLoop
        .feedbackSensor(FeedbackSensor.kPrimaryEncoder)
        .pid(P_VAL, I_VAL, D_VAL, ClosedLoopSlot.kSlot0);
    m_config.signals.absoluteEncoderPositionAlwaysOn(false)
        .absoluteEncoderVelocityAlwaysOn(false)
        .analogPositionAlwaysOn(false)
        .analogVelocityAlwaysOn(false)
        .analogVoltageAlwaysOn(false)
        .appliedOutputPeriodMs(FASTEST_5MS)
        .busVoltagePeriodMs(FASTEST_5MS)
        .externalOrAltEncoderPositionAlwaysOn(false)
        .externalOrAltEncoderVelocityAlwaysOn(false)
        .limitsPeriodMs(FASTEST_5MS)
        .motorTemperaturePeriodMs(FASTEST_5MS)
        .outputCurrentPeriodMs(FASTEST_5MS)
        .primaryEncoderPositionPeriodMs(FAST_10MS)
        .primaryEncoderVelocityPeriodMs(FAST_10MS);
    m_followerConfig.signals.absoluteEncoderPositionAlwaysOn(false)
        .absoluteEncoderVelocityAlwaysOn(false)
        .analogPositionAlwaysOn(false)
        .analogVelocityAlwaysOn(false)
        .analogVoltageAlwaysOn(false)
        .appliedOutputPeriodMs(FASTEST_5MS)
        .busVoltagePeriodMs(FASTEST_5MS)
        .externalOrAltEncoderPositionAlwaysOn(false)
        .externalOrAltEncoderVelocityAlwaysOn(false)
        .limitsPeriodMs(FASTEST_5MS)
        .motorTemperaturePeriodMs(FASTEST_5MS)
        .outputCurrentPeriodMs(FASTEST_5MS)
        .primaryEncoderPositionPeriodMs(FAST_10MS)
        .primaryEncoderVelocityPeriodMs(FAST_10MS);
    m_config.smartCurrentLimit(CURRENT_LIMIT);
    m_motor.configure(m_config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    m_followerConfig.follow(MOTOR_ID, true);
    m_followerConfig.idleMode(IdleMode.kBrake);
    m_followerConfig.smartCurrentLimit(CURRENT_LIMIT);
    m_follower.configure(m_followerConfig, ResetMode.kResetSafeParameters,
        PersistMode.kPersistParameters);
  }

  private void adjustElevator(double target) {
    m_pid.setReference(target, ControlType.kPosition, ClosedLoopSlot.kSlot0, feedForwardCalculation());
  }

  private double feedForwardCalculation() {
    double feedForward = m_FFStage1;
    if (m_encoder.getPosition() >= STAGE_ONE_HEIGHT) {
      feedForward = m_FFStage2;
    }
    // Complete this method that calculates the elevator's feedforward (a voltage
    // needed to counteract the force of gravity on the elevator). We need two
    // values because the elevator will weigh more once it picks up its next stage.
    // (remember the demo?)
    return feedForward;
  }

  private void resetEncoder(double position) {
    m_encoder.setPosition(position);
  }

  public double getBusVoltage() {
    return m_motor.getBusVoltage();
  }

  public double getOutputCurrent() {
    return m_motor.getOutputCurrent();
  }

  public int getFaultRawBits() {
    return m_motor.getStickyFaults().rawBits;
  }

  public boolean getGateDriver() {
    return m_motor.getStickyFaults().gateDriver;
  }

  public double getVelocity() {
    return m_encoder.getVelocity();
  }

  public double getHeight() {
    return m_encoder.getPosition();
  }

  public double getTargetHeight() {
    return m_targetHeight;
  }

  public boolean isAtBottom() {
    return m_lowLimit.isPressed();
  }

  public void changeFF2(double newFF) {
    m_FFStage2 = newFF;
  }

  public void changeMaxMotion(double mv, double ma, double ae) {
    m_config.closedLoop.maxMotion.maxVelocity(mv).maxAcceleration(ma).allowedClosedLoopError(ae);
    m_motor.configure(m_config, ResetMode.kResetSafeParameters,
        PersistMode.kPersistParameters);
  }

  public void setTargetHeight(double height) {
    m_targetHeight = height;
  }

  @Override
  public void periodic() {
    adjustElevator(m_targetHeight);
    if (isAtBottom()) {
      resetEncoder(DEFAULT_HEIGHT);
    }
  }

  @Override
  public void simulationPeriodic() {

  }

  public Command elevatorDefaultCommand() {
    return run(() -> {
      // Finish the default command here
    });
  }

  public Command moveElevatorCommand(double height) {
    return run(() -> {
      m_targetHeight = height;
    });
  }
}