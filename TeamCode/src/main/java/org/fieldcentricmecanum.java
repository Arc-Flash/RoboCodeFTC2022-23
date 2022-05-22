package org.firstinspires.ftc.teamcode.drive.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;



@TeleOp(name = "Field Relative")
public class FieldRelativeDrivetrain extends LinearOpMode {

    private DcMotor leftFront;
    private DcMotor leftBack;
    private DcMotor rightFront;
    private DcMotor rightBack;
    private BNO055IMU imu;

    double speedModifier = 0.4; //How fast motors go
    double robotAngle = 0; //For Field Relative Part
    double angleZeroValue = -3.1415 / 2.0;  // change this as needed, experiment to get start angle right
    

    @Override
    public void runOpMode(){
        //@TODO Check hardware mappings and name motors this
        leftFront = hardwareMap.get(DcMotor.class,"leftFront");
        leftBack = hardwareMap.get(DcMotor.class,"leftBack");
        rightFront = hardwareMap.get(DcMotor.class,"rightFront");
        rightBack = hardwareMap.get(DcMotor.class,"rightBack");
        imu = hardwareMap.get(BNO055IMU.class, "imu");

        //since this is mecanum code... 
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        while(opModeIsActive()){
            drivetrain();
        }


    }

    public void drivetrain() {
        //pressing x will recalibrate field relative(Press this)
        if (gamepad1.x) {
            angleZeroValue = this.getRawExternalHeading();

        }
        robotAngle = this.getRawExternalHeading() - angleZeroValue; //angle of robot

        double speed = Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y); //get speed
        double LeftStickAngle = Math.atan2(gamepad1.left_stick_y, -gamepad1.left_stick_x) - Math.PI / 4; //get angle
        double rightX = -gamepad1.right_stick_x; //rotation
        rightX *= 0.8; //optionally reduce rotation value for better turning
        //linear the angle by the angle of the robot to make it field relative
        double leftFrontPower = speed * Math.cos(LeftStickAngle - robotAngle) + rightX;
        double rightFrontPower = speed * Math.sin(LeftStickAngle - robotAngle) - rightX;
        double leftBackPower = speed * Math.sin(LeftStickAngle - robotAngle) + rightX;
        double rightBackPower = speed * Math.cos(LeftStickAngle - robotAngle) - rightX;


        speedModifier = .8 + (.8 * gamepad1.right_trigger) - (.4 * gamepad1.left_trigger);
        //Added this to give robot a speed boost

        //setting powers correctly
        leftFront.setPower(leftFrontPower * speedModifier);
        rightFront.setPower(rightFrontPower * speedModifier);
        leftBack.setPower(leftBackPower * speedModifier);
        rightBack.setPower(rightBackPower * speedModifier);

    }
    public double getRawExternalHeading() {
        //gives us the robot angle
        return imu.getAngularOrientation().firstAngle;
    }



}