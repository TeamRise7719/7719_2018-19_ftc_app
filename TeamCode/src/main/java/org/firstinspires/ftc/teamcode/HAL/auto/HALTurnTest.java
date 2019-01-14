package org.firstinspires.ftc.teamcode.HAL.auto;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subSystems.Driving.autonomous.encoderLibrary;
import org.firstinspires.ftc.teamcode.subSystems.Sensing.visionLibrary;
@Autonomous

public class HALTurnTest extends LinearOpMode{
    encoderLibrary enc;

    ElapsedTime etime = new ElapsedTime();

    public void waitFor(int time){
        time = time/1000;
        etime.reset();
        while ((etime.time() < time)&&(opModeIsActive())) {
            idle();
        }
    }


    @Override
    public void runOpMode() throws InterruptedException {

        enc = new encoderLibrary(hardwareMap, telemetry,this);
        enc.init();

        waitForStart();

        enc.gyroTurn(0.7, 90);



    }
}
