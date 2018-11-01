package org.firstinspires.ftc.teamcode.HAL.auto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subSystems.Driving.autonomous.encoderLibrary;
import org.firstinspires.ftc.teamcode.subSystems.Sensing.visionLibrary;



public class HALAutoGold extends LinearOpMode {


    encoderLibrary enc;
    visionLibrary vis;
    int position;

    @Override
    public void runOpMode() throws InterruptedException {

        enc = new encoderLibrary(hardwareMap, telemetry,this);
        enc.init();

        position = vis.objectVision();

        //1. Drop down from the latch

        //2. Strafe to the left

        //3. Come forward

        //4. Sample gold mineral

        //5. Drive clear of sample field

        //6. Drive to depot

        //7. Drop off team marker

        //8. Score in crater


    }
}