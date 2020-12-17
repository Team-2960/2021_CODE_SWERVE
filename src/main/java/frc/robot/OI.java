import frc.robot.SubSystems.Climb;
import frc.robot.SubSystems.Drive;
import frc.robot.SubSystems.Hood;
import frc.robot.SubSystems.Index;
import frc.robot.SubSystems.Intake;
import frc.robot.SubSystems.Shooter;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
public class OI extends SubsystemBase {
    private Climb climb;
    private Intake intake;
    private Drive drive;
    private Shooter shooter;
    public Hood hood;
    private Index index;
    public OI(){
        //camera = Camera.get_Instance();
        drive = Drive.get_Instance();
        climb = Climb.get_Instance();
        intake = Intake.get_Instance();
        shooter = Shooter.get_Instance();
        index = Index.get_Instance();
        Joystick joy1 = new Joystick(Constants.joy1);


    }
    private void manualDrive(){
        //TODO ADD CONTROLS
        //TODO ADD SCALAR
        drive.setSwerve(joy1.getRawAxis(1/*TODO CHANGE TO VERT LEFT*/), joy1.getRawAxis(5/*TODO CHANGE TO HORIZ LEFT*/), joy1.getRawAxis(4/*TODO CHANGE TO HORIZ RIGHT*/));
    }
    public void periodic(){
        manualDrive();
    }
}