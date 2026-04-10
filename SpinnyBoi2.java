package frc.robot.subsystems;

// import com.revrobotics.spark.SparkMax;
// import com.revrobotics.spark.SparkLowLevel.MotorType;
// import com.revrobotics.spark.config.SparkMaxConfig;
// import com.thethriftybot.devices.ThriftyNova;
// import com.thethriftybot.devices.ThriftyNova.CurrentType;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.MotorAlignmentValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SpinnyBoi2 extends SubsystemBase {
    // public final ThriftyNova  m_feed = new ThriftyNova (5);
    public final TalonFX feeder = new TalonFX(5);
    // public final SparkMax  m_intake_shooter = new SparkMax(6, MotorType.kBrushed);
    public final TalonFX shooter_right = new TalonFX(6);
    public final TalonFX shooter_left = new TalonFX(7);

    private final VoltageOut voltageOut = new VoltageOut(6.0);


    public SpinnyBoi2() {
        /* 

        m_feed.setMaxCurrent(CurrentType.STATOR, 60)
               .setBrakeMode(true);

        // SparkMaxConfig has no constructor taking an ID — use default and configure if needed
        
        final SparkMaxConfig kFeedConfigs = new SparkMaxConfig();
        kFeedConfigs.smartCurrentLimit(80);
        */

        TalonFXConfiguration shooterConfig = new TalonFXConfiguration();
        shooterConfig.Voltage.PeakForwardVoltage = 12;
        shooterConfig.Voltage.PeakReverseVoltage = -12;
        shooterConfig.CurrentLimits.StatorCurrentLimit = 60.0;
        shooterConfig.CurrentLimits.StatorCurrentLimitEnable = true;
        shooterConfig.CurrentLimits.SupplyCurrentLimit = 60.0;
        shooterConfig.CurrentLimits.SupplyCurrentLimitEnable = true;
        shooterConfig.MotorOutput.NeutralMode = NeutralModeValue.Brake;

        TalonFXConfiguration feederConfig = new TalonFXConfiguration();
        feederConfig.Voltage.PeakForwardVoltage = 12;
        feederConfig.Voltage.PeakReverseVoltage = -12;
        feederConfig.CurrentLimits.StatorCurrentLimit = 60.0;
        feederConfig.CurrentLimits.StatorCurrentLimitEnable = true;
        feederConfig.CurrentLimits.SupplyCurrentLimit = 60.0;
        feederConfig.CurrentLimits.SupplyCurrentLimitEnable = true;
        feederConfig.MotorOutput.NeutralMode = NeutralModeValue.Brake;


        shooter_left.setControl(new Follower(shooter_right.getDeviceID(), MotorAlignmentValue.Opposed));
        shooter_right.getConfigurator().apply(shooterConfig);
        shooter_left.getConfigurator().apply(shooterConfig);
        feeder.getConfigurator().apply(feederConfig);

    }

    public void setShooterVoltage(double volts) {
        shooter_right.setControl(voltageOut.withOutput(volts));
    }

    public void setFeederVoltage(double volts) {
        feeder.setControl(voltageOut.withOutput(volts));
    }
}
