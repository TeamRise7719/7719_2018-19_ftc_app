package org.firstinspires.ftc.teamcode.HAL.teleOp;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.subSystems.Driving.teleOp.mecanumDrivetrain;
@Disabled
@TeleOp(name = "HAL TeleOp", group = "HAL")
public class HALTeleOp2 extends OpMode {

    private mecanumDrivetrain robot;
    DcMotor arm1, arm2;
    DcMotor intRotL, intRotR;
    CRServo intR, intL;
    CRServo hook;
    Servo lift1, lift2, lift3, lift4;



    Telemetry tele;

    @Override
    public void init() {


        robot = new mecanumDrivetrain(hardwareMap, telemetry);
        robot.runUsingEncoders();
        arm1 = hardwareMap.dcMotor.get("arm1");
        arm2 = hardwareMap.dcMotor.get("arm2");
        arm1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        arm2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intRotL = hardwareMap.dcMotor.get("intRotL");
        intRotR = hardwareMap.dcMotor.get("intRotR");
        intRotL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intRotR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intL = hardwareMap.crservo.get("intL");
        intR = hardwareMap.crservo.get("intR");
        hook = hardwareMap.crservo.get("hook");
        lift1 = hardwareMap.servo.get("lift1");
        lift2 = hardwareMap.servo.get("lift2");
        lift3 = hardwareMap.servo.get("lift3");
        lift4 = hardwareMap.servo.get("lift4");

        //boolean changed = false;

    }

    @Override
    public void loop() {


        //----------------------------------------------=+(Drivetrain)+=----------------------------------------------\\
        robot.drive(gamepad1, telemetry);

        if (gamepad1.x) {
            robot.resetHeading();
        }

        //----------------------------------------------=+(Drivetrain)+=----------------------------------------------\\


        //--------------------------------------------=+(Intake/Hanging)+=--------------------------------------------\\

        //Intake\\
        intL.setPower(gamepad2.left_stick_y);
        intR.setPower(-gamepad2.left_stick_y);

        //X-Rail Extension\\
        arm1.setPower(-gamepad2.right_stick_y);
        arm2.setPower(-gamepad2.right_stick_y);

        //Intake Rotation\\
        if (gamepad2.left_bumper) {
            intRotR.setPower(1);
            intRotL.setPower(1);
        } else {
            intRotR.setPower(0);
            intRotL.setPower(0);
        }
        if (gamepad2.right_bumper){
            intRotR.setPower(-1);
            intRotL.setPower(-1);
        } else {
            intRotR.setPower(0);
            intRotL.setPower(0);
        }

        //Toggle Hook//








    }

}