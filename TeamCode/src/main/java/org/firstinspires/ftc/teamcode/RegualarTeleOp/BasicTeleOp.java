package org.firstinspires.ftc.teamcode.RegualarTeleOp;

import com.outoftheboxrobotics.photoncore.PhotonCore;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import java.util.jar.Attributes;

@TeleOp(name = "BasicTeleOp")
public class BasicTeleOp extends OpMode {

    private DcMotor frontLeft = null;
    private DcMotor frontRight = null;
    private DcMotor backLeft = null;
    private DcMotor backRight = null;

    @Override
    public void init() {
        PhotonCore.enable();

        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");

        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public void loop()
    {
        backRight.setPower(-gamepad1.right_stick_y);
        backLeft.setPower(-gamepad1.left_stick_y);
        frontRight.setPower(-gamepad1.right_stick_y);
        frontLeft.setPower(-gamepad1.left_stick_y);
    }
}
