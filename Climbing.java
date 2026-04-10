package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climbing extends SubsystemBase {
    public final TalonFX m_climb = new TalonFX(8);

    public Climbing() {
        final TalonFXConfiguration kClimbConfigs = new TalonFXConfiguration();
    }
}
