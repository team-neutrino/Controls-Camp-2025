// Copyright 2025 FRC Team Neutrino

package frc.robot.factories;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.subsystems.*;

public class LedFactory {

    private final LedSubsystem m_subsystem;

    public LedFactory(LedSubsystem subsystem) {
        m_subsystem = subsystem;
    }

    public Command defaultCommand() {
        return off();
    }

    public Command off() {
        return m_subsystem.colorCommand(0, 0, 0);
    }

    public Command white() {
        return m_subsystem.colorCommand(255, 255, 255);
    }

    public Command orange() {
        return m_subsystem.colorCommand(255, 165, 0);
    }

    public Command toggleWhiteAndOrange() {
        return m_subsystem.doNothing();
    }

    public Command rainbow() {
        return m_subsystem.doNothing();
    }
}
