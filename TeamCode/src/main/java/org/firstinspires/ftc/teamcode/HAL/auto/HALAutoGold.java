package org.firstinspires.ftc.teamcode.HAL.auto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.subSystems.Driving.autonomous.encoderLibrary;



public class HALAutoGold extends LinearOpMode {


    encoderLibrary enc;

    @Override
    public void runOpMode() throws InterruptedException {

        enc = new encoderLibrary(hardwareMap, telemetry,this);
        enc.init();

        waitForStart();

        enc.gyroDrive(enc.DRIVE_SPEED_SLOW, 20, 0, false);




    }
}