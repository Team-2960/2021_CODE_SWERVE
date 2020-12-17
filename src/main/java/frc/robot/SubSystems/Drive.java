package frc.robot.SubSystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
//MOTORS
import com.revrobotics.CANDigitalInput;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
//UTIL
import frc.robot.Util.Swerve;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.controller.PIDController;
//MATHU
import java.math.*;
import java.util.ArrayList;


public class Drive extends SubsystemBase {
  //TODO ADD TRAJECTORY https://docs.wpilib.org/en/stable/docs/software/examples-tutorials/trajectory-tutorial/index.html
    private static Drive drive;
    private static double frontLeftSwerveSpeed;
    private static double frontLeftSwerveAngle;
    private static double frontRightSwerveSpeed;
    private static double frontRightSwerveAngle;
    private static double backLeftSwerveSpeed;
    private static double backLeftSwerveAngle;
    private static double backRightSwerveSpeed;
    private static double backRightSwerveAngle;
    Swerve frontLeft;
    Swerve frontRight;
    Swerve backRight;
    Swerve backLeft;
    public static Drive get_Instance(){
    
        if(drive == null){
          drive = new Drive();
        } 
        return drive;
      }
      public Drive(){
         frontLeft = new Swerve(Constants.motorIdDriveFrontLeft, Constants.motorIdAngleFrontLeft, Constants.encoderIdFrontLeft);
         frontRight = new Swerve(Constants.motorIdDriveFrontRight, Constants.motorIdAngleFrontRight, Constants.encoderIdFrontRight);
         backRight = new Swerve(Constants.motorIdDriveBackRight, Constants.motorIdAngleBackRight, Constants.encoderIdBackRight);
         backLeft = new Swerve(Constants.motorIdDriveBackLeft, Constants.motorIdAngleBackLeft, Constants.encoderIdBackLeft);
      }
      public void homeSwerve(){
        frontLeft.setSpeed(0, frontLeft.anglePIDCalcABS(Constants.flHome));
        frontRight.setSpeed(0, frontRight.anglePIDCalcABS(Constants.frHome));
        backLeft.setSpeed(0, backLeft.anglePIDCalcABS(Constants.blHome));
        backRight.setSpeed(0, backRight.anglePIDCalcABS(Constants.brHome));
        frontLeft.resetEncoder();
        frontRight.resetEncoder();
        backLeft.resetEncoder();
        backRight.resetEncoder();
      }
      public void setSwerve(double angleVectorX, double angleVectorY, double rotationVectorX){
        double rotationVectorY = rotationVectorX;
        double A = angleVectorX - rotationVectorX;//THE PLUS AND MINUS MAY BE FLIPPED
        double B = angleVectorX + rotationVectorX;
        double C = angleVectorY - rotationVectorY;
        double D = angleVectorY + rotationVectorY;

        frontLeftSwerveSpeed = Math.sqrt(Math.pow(A,2.0) + Math.pow(C,2.0));
        frontLeftSwerveAngle = Math.atan2(A,C)*180/Math.PI;
        frontRightSwerveSpeed =  Math.sqrt(Math.pow(A,2.0) + Math.pow(D,2.0));
        frontRightSwerveAngle = Math.atan2(A,D)*180/Math.PI;
        backLeftSwerveSpeed =  Math.sqrt(Math.pow(B,2.0) + Math.pow(C,2.0));
        backLeftSwerveAngle = Math.atan2(B,C)*180/Math.PI;
        backRightSwerveSpeed =  Math.sqrt(Math.pow(B,2.0) + Math.pow(D,2.0));
        backRightSwerveAngle = Math.atan2(B,D)*180/Math.PI;
        //SET ALL OF THE NUMBERS FOR THE SWERVE VARS
      }
      //TODO SET SVWERVE TO VECTOR AND NOT INDIVIDUAL PARTS OF VECTOR CALL SET SWERVE 
      public void periodic(){
        frontLeft.setSpeed(frontLeftSwerveSpeed, frontLeft.anglePIDCalc(frontLeftSwerveAngle));
        frontRight.setSpeed(frontRightSwerveSpeed, frontRight.anglePIDCalc(frontRightSwerveAngle));
        backLeft.setSpeed(backLeftSwerveSpeed, backLeft.anglePIDCalc(backLeftSwerveAngle));
        backRight.setSpeed(backRightSwerveSpeed, backRight.anglePIDCalc(backRightSwerveAngle));

      }


}