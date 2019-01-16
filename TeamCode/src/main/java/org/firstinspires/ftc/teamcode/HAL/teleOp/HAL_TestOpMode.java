package org.firstinspires.ftc.teamcode.HAL.teleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.subSystems.Driving.teleOp.mecanumDrivetrain;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "TESTING", group = "HAL")
public class HAL_TestOpMode extends OpMode {

    public DcMotor a1, a2, s1, s2;
    public Servo l1, l2, l3, l4;
    public CRServo vex1, vex2;

    public mecanumDrivetrain robot;


    HardwareMap hwMap;

    @Override
    public void init() {

        robot = new mecanumDrivetrain(hardwareMap, telemetry);
        robot.runUsingEncoders();
        a1 = hardwareMap.dcMotor.get("a1");
        a2 = hardwareMap.dcMotor.get("a2");
        s1 = hardwareMap.dcMotor.get("s1");
        s2 = hardwareMap.dcMotor.get("s2");
        l1 = hardwareMap.servo.get("1");
        l2 = hardwareMap.servo.get("2");
        l3 = hardwareMap.servo.get("3");
        l4 = hardwareMap.servo.get("4");
        vex1 = hardwareMap.crservo.get("v1");
        vex2 = hardwareMap.crservo.get("v2");

        a1.setDirection(DcMotorSimple.Direction.FORWARD);
        a2.setDirection(DcMotorSimple.Direction.REVERSE);
        a1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        a2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        s1.setDirection(DcMotorSimple.Direction.FORWARD);
        s2.setDirection(DcMotorSimple.Direction.REVERSE);
        s1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        s2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        vex1.setDirection(DcMotorSimple.Direction.FORWARD);
        vex2.setDirection(DcMotorSimple.Direction.REVERSE);



    }

    @Override
    public void loop() {

        a1.setPower(gamepad2.left_stick_y);
        a2.setPower(gamepad2.left_stick_y);
        s1.setPower(gamepad2.right_stick_y);
        s2.setPower(gamepad2.right_stick_y);
        vex1.setPower(gamepad2.right_trigger - gamepad2.left_trigger);
        vex2.setPower(gamepad2.right_trigger - gamepad2.left_trigger);

        if (gamepad2.a){
            l1.setPosition(0.3);
            l2.setPosition(0.3);
            l3.setPosition(0.3);
            l4.setPosition(0.3);
        } else if (gamepad2.b){
            l1.setPosition(0.75);
            l2.setPosition(0.75);
            l3.setPosition(0.75);
            l4.setPosition(0.75);
        }

        robot.drive(gamepad1, telemetry);



    }

}