package org.firstinspires.ftc.teamcode.HAL.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subSystems.Sensing.visionLibrary;

/**
 * Created by Evan McLoughlin on 11/1/2018.
 */

@Autonomous
@Disabled
public class visionTest extends LinearOpMode {

    visionLibrary vis;

    int position;

    @Override
    public void runOpMode() throws InterruptedException {

        vis = new visionLibrary(hardwareMap, telemetry);
        vis.initVuforia();
        vis.initTfod();
        vis.camFlash(true);
        while(!isStarted()) {
            position = vis.objectVision();
        }
        vis.camFlash(false);


    }
}
