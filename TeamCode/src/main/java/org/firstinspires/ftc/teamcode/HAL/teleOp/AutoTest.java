package org.firstinspires.ftc.teamcode.HAL.teleOp;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subSystems.Driving.autonomous.encoderLibrary;
import org.firstinspires.ftc.teamcode.subSystems.Driving.teleOp.mecanumDrivetrain;

@Autonomous
@Disabled
public class AutoTest extends LinearOpMode {

    encoderLibrary enc;
    private mecanumDrivetrain robot;
    Servo lift1,lift2,lift3,lift4;
    DcMotor shoulderL;
    DcMotor shoulderR;
    DcMotor armL, armR;
    CRServo wristR, wristL, intR, intL,hook;



    public void waitFor(int time) {

        }



        public void runOpMode() {
            robot = new mecanumDrivetrain(hardwareMap, telemetry);
            robot.runUsingEncoders();
            intR = hardwareMap.crservo.get("intR");
            intL = hardwareMap.crservo.get("intL");
            shoulderL = hardwareMap.dcMotor.get("shoulderL");
            shoulderR = hardwareMap.dcMotor.get("shoulderR");
            hook = hardwareMap.crservo.get("hook");





            enc.gyroHold(0.3, 47,2);
            waitFor(250);
            armR.setPower(0.5);
            armL.setPower(-0.5);
            waitFor(250);
            armR.setPower(-0.5);
            armL.setPower(0.5);
            waitFor(250);
            enc.gyroHold(0.3, -47,2);



        }







        }




