package frc.robot.subsystems;

import com.thethriftybot.devices.ThriftyNova;
import com.thethriftybot.devices.ThriftyNova.CurrentType;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Drivek;
import static frc.robot.Constants.Drivek.kLeftFollowerCANID;
import static frc.robot.Constants.Drivek.kLeftLeaderCANID;
import static frc.robot.Constants.Drivek.kRightFollowerCANID;
import static frc.robot.Constants.Drivek.kRightLeaderCANID;

public class Drive extends SubsystemBase {
    private final ThriftyNova m_leftLeader = new ThriftyNova(kLeftLeaderCANID);
    private final ThriftyNova m_leftFollower = new ThriftyNova(kLeftFollowerCANID);

    private final ThriftyNova m_rightLeader = new ThriftyNova(kRightLeaderCANID);
    private final ThriftyNova m_rightFollower = new ThriftyNova(kRightFollowerCANID);



    // Followers are configured to follow their leaders on the CAN bus, so
    // we only pass the leaders into DifferentialDrive below.



    public Drive() {
        // Apply team default configuration (current limits, brake mode, inversion)
        Drivek.kdriveConfigurator(m_rightLeader, m_leftLeader);

    // Configure followers to follow their respective leaders by device ID.
    // ThriftyNova.follow(int leaderDeviceID) is used here (assumption based
    // on earlier commented code in repository).
        m_leftFollower.follow(kLeftLeaderCANID);
        m_rightFollower.follow(kRightLeaderCANID);

        // Apply per-device limits as an extra safety measure.
        m_leftLeader.setMaxCurrent(CurrentType.STATOR, 60);
        m_rightLeader.setMaxCurrent(CurrentType.STATOR, 60);
        m_leftFollower.setMaxCurrent(CurrentType.STATOR, 60);
        m_rightFollower.setMaxCurrent(CurrentType.STATOR, 60);
    }

    private final DifferentialDrive m_diffDrive =
        new DifferentialDrive(m_leftLeader, m_rightLeader);


        public void TankDrive(double leftMotorSpeed, double rightMotorSpeed) {
            m_diffDrive.tankDrive(leftMotorSpeed, rightMotorSpeed);
        }
    
        public void ArcDrive(double moveSpeed, double turnSpeed) {
        m_diffDrive.arcadeDrive(moveSpeed, turnSpeed);
        }

        

    @Override
    public void periodic() {
        // Feed the DifferentialDrive to satisfy MotorSafety and avoid
        // "Output not updated often enough" errors. This ensures the
        // watchdog is reset each scheduler cycle even if higher-level
        // code doesn't call drive frequently.
        m_diffDrive.feed();
    }
}
