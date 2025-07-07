// Copyright 2025 FRC Team Neutrino

package frc.robot.factories;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.subsystems.*;

public class CoralFactory {

    private final Coral m_subsystem;

    public CoralFactory(Coral subsystem) {
        m_subsystem = subsystem;
    }

    public Command doNothing(){
        return m_subsystem.doNothing();
    }
}
