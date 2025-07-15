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
  private SparkClosedLoopController m_pid = m_motor.getClosedLoopController();
  private SparkFlexConfig m_config = new SparkFlexConfig();
  private SparkFlexConfig m_followerConfig = new SparkFlexConfig();

  private RelativeEncoder m_encoder; // Define m_encoder as the built-in relative encoder that
  // belongs to the leader motor (m_motor)
  private SparkLimitSwitch m_lowLimit; // Define m_lowLimit as the limit switch connected to the leader motor. It
  // should be a reverse limit switch.

  // Define a new member variable that represents the target height
  private double m_targetHeight = DEFAULT_HEIGHT;

  public Elevator() {
    initializeMotorControllers();
  }

  private void initializeMotorControllers() {
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
    // Use your SparkClosedLoopController, m_pid, to move the elevator by setting a
    // target. This can be done using a method for the SparkClosedLoopController
    // class (check out the linked documentation!). Set the control type to
    // 'position' and the slot to '0'
  }

  private double feedForwardCalculation() {
    double feedForward = 0;
    // Complete this method that returns the elevator's feedforward (a voltage
    // needed to counteract the force of gravity on the elevator). We need two
    // values because the elevator will weigh more once it picks up its next stage.
    // (remember the Day 3 demo?)

    return feedForward;
    // Once you have completed this, go ahead and make it the 4th parameter of your
    // call to the 'setReference' method in 'adjustElevator.' This will add your
    // feedforward to your elevator control!
  }

  private void resetEncoder(double position) {
    // Complete this method so that it sets the encoder's position to the position
    // given as a parameter!
  }

  public boolean isAtBottom() {
    // Complete this method so that it returns true when the limit switch at the
    // bottom of the elevator is pressed and false otherwise
    return false;
  }

  public void setTargetHeight() {
    // Change this method so that it takes in a parameter 'height' (in inches) and
    // sets the target to that height.
  }

  // Below, write the three getter methods from slide ___.

  public double getVelocity() {
    return m_encoder.getVelocity();
  }

  public double getHeight() {
    return m_encoder.getPosition();
  }

  public double getTargetHeight() {
    return m_targetHeight;
  }

  @Override
  public void periodic() {
    // Using the methods you have written, complete periodic so that the elevator is
    // always trying to get to its target. If it touches the limit switch at the
    // bottom, we want to reset the height here, too.
  }

  public Command elevatorDefaultCommand() {
    return run(() -> {
      // Finish the default command here so that the elevator is all the way down by
      // default
    });
  }

  public Command moveElevatorCommand(double height) {
    return run(() -> {
      // Complete this command to make the elevator move to a given height.
      // Hint: The name of this command is slightly misleading. You already 'adjust'
      // the elevator in periodic. What would you need to change to make the elevator
      // go somewhere else?
    });
  }
}