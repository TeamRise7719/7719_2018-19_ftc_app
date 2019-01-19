package org.firstinspires.ftc.teamcode.HAL.teleOp;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subSystems.Driving.teleOp.mecanumDrivetrain;
@Disabled
@TeleOp(name = "HAL TeleOpV2", group = "HAL")
public class HALTeleOpV2 extends OpMode {

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
        marker.setPower(-1);


        //----------------------------------------------=+(Drivetrain)+=----------------------------------------------\\
        robot.drive(gamepad1, telemetry);

        if (gamepad1.x) {
            robot.resetHeading();
        }

        //----------------------------------------------=+(Drivetrain)+=----------------------------------------------\\



    }

}