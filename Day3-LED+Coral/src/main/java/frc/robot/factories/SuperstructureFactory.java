// Copyright 2025 FRC Team Neutrino

package frc.robot.factories;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.RobotContainer;
import frc.robot.subsystems.*;

public class SuperstructureFactory {
    Coral coralSub = new Coral();
    CoralFactory coralFactory = new CoralFactory(coralSub);

    public Command changeLEDWhenMotorSpin() {
        Command nothingCommand = coralFactory.doNothing();
        return nothingCommand;
    }
}
