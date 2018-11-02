package org.firstinspires.ftc.teamcode.subSystems.Sensing;

import java.util.List;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.vuforia.CameraDevice;

/**
 * Created by Evan McLoughlin on 11/1/2018.
 */

public class visionLibrary {

    private static final String TFOD_MODEL_ASSET = "RoverRuckus.tflite";
    private static final String LABEL_GOLD_MINERAL = "Gold Mineral";
    private static final String LABEL_SILVER_MINERAL = "Silver Mineral";
    private static final String VUFORIA_KEY = "AUbWH2r/////AAABmTqIGJpUgkb9j4jexPb0CKYmpPjDhVyV5bE2RL866jD2AKG/pqN0P8mPlybMo3P0xERT+mK0uW04FHPso8OcJDLER7gW6Rjnv49Yzc7ks3zkCGtwmHx/sqInqUl4i2jlHTFiW8qYnAf/iOJ0O2jO7j8UjOuurbpGT+3iGwRprWQFe7/Wb6k08A1tMIwvDKgU3g+PudWyfefPeo2Oo3PYzIiGu+KlswOR26Jn3jRSGmlin3JrfLkvmV7AmTaFWGwb876eR21A5EP40EIBk8E9nDuJcVB60q9R7nnBvf/qjsSuUwKQWtn9xTBzWSIhEzXetUY5PMxckiugQLWHp7YAkrM7SGNte2JrUk9XslYRqV4z";

    private VuforiaLocalizer vuforia;
    private TFObjectDetector tfod;
    private HardwareMap hwMap;
    private LinearOpMode linOp;
    private Telemetry tele;

    private int position = 1;

    public visionLibrary(HardwareMap hardwareMapRef, Telemetry telemetryRef) {
        tele = telemetryRef;
        hwMap = hardwareMapRef;
    }


    public void initVuforia() {
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();
        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = CameraDirection.BACK;
        vuforia = ClassFactory.getInstance().createVuforia(parameters); //  Instantiate the Vuforia engine
    }

    /**
     * Initialize the Tensor Flow Object Detection engine.
     */
    public void initTfod() {
        int tfodMonitorViewId = hwMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hwMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_GOLD_MINERAL, LABEL_SILVER_MINERAL);
        if (tfod != null) {
            tfod.activate();
        }
    }

    public void camFlash(boolean status){
        CameraDevice.getInstance().setFlashTorchMode(status);
    }

    public int objectVision() {

        if (tfod != null) {
            // getUpdatedRecognitions() will return null if no new information is available since
            // the last time that call was made.
            List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
            if (updatedRecognitions != null) {
                tele.addData("# Object Detected", updatedRecognitions.size());
                if (updatedRecognitions.size() == 3) {
                    int goldMineralX = -1;
                    int silverMineral1X = -1;
                    int silverMineral2X = -1;
                    for (Recognition recognition : updatedRecognitions) {
                        if (recognition.getLabel().equals(LABEL_GOLD_MINERAL)) {
                            goldMineralX = (int) recognition.getLeft();
                        } else if (silverMineral1X == -1) {
                            silverMineral1X = (int) recognition.getLeft();
                        } else {
                            silverMineral2X = (int) recognition.getLeft();
                        }
                    }
                    if (goldMineralX != -1 && silverMineral1X != -1 && silverMineral2X != -1) {
                        if (goldMineralX < silverMineral1X && goldMineralX < silverMineral2X) {
                            tele.addData("Gold Mineral Position", "Left");
                            position = 0;
                        } else if (goldMineralX > silverMineral1X && goldMineralX > silverMineral2X) {
                            tele.addData("Gold Mineral Position", "Right");
                            position = 2;
                        } else {
                            tele.addData("Gold Mineral Position", "Center");
                            position = 1;
                        }
                    }
                }
                tele.update();
            }
        }

//        if (tfod != null) {
//            tfod.shutdown();
//        }

        return position;

    }



}
