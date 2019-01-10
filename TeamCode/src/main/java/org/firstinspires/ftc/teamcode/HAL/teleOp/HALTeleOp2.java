package org.firstinspires.ftc.teamcode.HAL.teleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.subSystems.Driving.teleOp.mecanumDrivetrain;

@TeleOp(name = "HAL TeleOp2", group = "HAL")
public class HALTeleOp2 extends OpMode {

    private mecanumDrivetrain robot;
    CRServo marker, crater;
    DcMotor plow;

    Telemetry tele;

    @Override
    public void init() {


        robot = new mecanumDrivetrain(hardwareMap, telemetry);
        robot.runUsingEncoders();
        marker = hardwareMap.crservo.get("marker");
        crater = hardwareMap.crservo.get("crater");
        plow = hardwareMap.dcMotor.get("plow");
        plow.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    @Override
    public void loop() {
//        marker.setPower(-1);


        //----------------------------------------------=+(Drivetrain)+=----------------------------------------------\\
        robot.drive(gamepad1, telemetry);

        if (gamepad1.x) {
            robot.resetHeading();
        }

        //----------------------------------------------=+(Drivetrain)+=----------------------------------------------\\

        if (gamepad1.left_bumper) {
            robot.winchMotor.setPower(-1);
        } else if (gamepad1.right_bumper) {
            robot.winchMotor.setPower(1);
        } else {
            robot.winchMotor.setPower(0);
        }


//
//  if(gamepad1.a) {
//  send out intake
//    }
//
//
// if(gamepad1.b) {
// take out intake
//   }
//
//
// if(gamepad1.) {
//  increase vertical value on outtake
//   }
//
// if(gamepad1.righttrigger) {
//  decrease vertical value on outtake
//   }

    }

}