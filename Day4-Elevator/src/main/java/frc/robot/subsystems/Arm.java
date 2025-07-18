// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.spark.ClosedLoopSlot;
import com.revrobotics.spark.SparkBase.*;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.ClosedLoopConfig.FeedbackSensor;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkFlexConfig;
import com.revrobotics.AbsoluteEncoder;
import static frc.robot.Constants.ArmConstants.*;

/**
 * Class that represents the arm subsystem on the robot.
 */
public class Arm extends SubsystemBase {
    private SparkFlex m_motor = new SparkFlex(MOTOR_ID, MotorType.kBrushless);
    private SparkFlexConfig m_motorConfig = new SparkFlexConfig();
    private AbsoluteEncoder m_encoder;
    private SparkClosedLoopController m_pid;
    private double m_targetAngle = STARTING_POSITION;
    private double m_FFConstant = FFCONSTANT;

    public Arm() {
        initializeMotorControllers();
    }

    /**
     * Returns the actual angle of the arm in degrees with 180 as vertical pointing
     * up and 90 pointing forward
     */
    public double getAngle() {
        return m_encoder.getPosition();
    }

    /**
     * Returns the target angle of the arm in degrees with 180 as vertical pointing
     * up and 90 pointing forward
     */
    public double getTargetAngle() {
        return m_targetAngle;
    }

    private boolean nearTargetAngle() {
        return Math.abs(getAngle() - m_targetAngle) <= GAIN_THRESHOLD;
    }

    /**
     * Sets up motor controllers to necessary configurations. Includes maxMotion,
     * current limits, and converstion factors.
     */
    private void initializeMotorControllers() {
        m_encoder = m_motor.getAbsoluteEncoder();
        m_pid = m_motor.getClosedLoopController();
        m_motorConfig.idleMode(IdleMode.kBrake);

        m_motorConfig.absoluteEncoder
                .positionConversionFactor(360)
                .velocityConversionFactor(1);

        m_motorConfig.signals.absoluteEncoderPositionPeriodMs(5);

        m_motorConfig.smartCurrentLimit(CURRENT_LIMIT);

        m_motorConfig.closedLoop
                .feedbackSensor(FeedbackSensor.kAbsoluteEncoder)
                .pid(kp, ki, kd, ClosedLoopSlot.kSlot0)
                .pid(kp1, ki1, kd1, ClosedLoopSlot.kSlot1)
                .pid(kp2, ki2, kd2, ClosedLoopSlot.kSlot2);
        m_pid = m_motor.getClosedLoopController();

        m_motorConfig.closedLoop.maxMotion
                .maxVelocity(MAX_VELOCITY)
                .maxAcceleration(MAX_ACCELERATION)
                .allowedClosedLoopError(ALLOWED_ERROR);

        m_motor.configure(m_motorConfig,
                ResetMode.kResetSafeParameters,
                PersistMode.kPersistParameters);
    }

    public boolean willNotHitSwerve() {
        return getAngle() <= 270 && getAngle() >= 90;
    }

    public boolean isAtIntake() {
        return Math.abs(getAngle() - CORAL_STATION_POSITION) <= INTAKE_ANGLE_TOLERANCE;
    }

    private void adjustArm(double targetAngle) {
        if (nearTargetAngle()) {
            m_pid.setReference(targetAngle, ControlType.kPosition, ClosedLoopSlot.kSlot1, feedForwardCalculation());
        } else {
            m_pid.setReference(targetAngle, ControlType.kPosition, ClosedLoopSlot.kSlot0,
                    feedForwardCalculation());
        }
    }

    public void setTarget(double target) {
        m_targetAngle = target;
    }

    /**
     * Determines the necessary volts needed for the Feedforward. Used to pass into
     * closed loop controller
     * 
     * @return volts
     */
    private double feedForwardCalculation() {
        double feedForward = m_FFConstant;
        double currentAngle = (getAngle() - 90) * (Math.PI / 180);
        double volts = feedForward * Math.cos(currentAngle);
        return volts;
    }

    /**
     * Changes the PID settings of the closed loop controller
     * 
     * @param p Proportional
     * @param i Integral
     * @param d Derivative
     */
    public void changePID(double p, double i, double d) {
        m_motorConfig.closedLoop.pid(p, i, d);
        m_motor.configure(m_motorConfig, ResetMode.kResetSafeParameters,
                PersistMode.kPersistParameters);
    }

    public void changeFF(double newFF) {
        m_FFConstant = newFF;
    }

    public void changeMaxMotion(double mv, double ma, double ae) {
        m_motorConfig.closedLoop.maxMotion.maxVelocity(mv).maxAcceleration(ma).allowedClosedLoopError(ae);
        m_motor.configure(m_motorConfig,
                ResetMode.kResetSafeParameters,
                PersistMode.kPersistParameters);
    }

    @Override
    public void periodic() {
        adjustArm(m_targetAngle);
    }

    /**
     * Gives a instance of the arm default command. Rotates the arm to the default
     * position
     * 
     * @return The rotate wrist command
     */
    public Command armDefaultCommand() {
        return run(() -> {
            m_targetAngle = L3_POSITION;
        });

    }

    /**
     * Gives a instance of the arm rotate command. Rotates the arm to the given
     * angle
     * 
     * @param targetAngle
     * @return The arm rotate command
     */
    public Command armRotateCommand(double targetAngle) {
        return run(() -> m_targetAngle = targetAngle);
    }
}