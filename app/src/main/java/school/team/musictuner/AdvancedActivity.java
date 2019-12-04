package school.team.musictuner;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

/**
 * This activity represents the Note-Time function.
 * It takes sound input and turns it into PlayedSections and displays it.
 *
 * see PlayedSection
 */
public class AdvancedActivity extends AppCompatActivity implements AdvancedDisplay{
    private AdvancedController controller = new AdvancedController(new Converter());
    private boolean micPermissionGranted=false;
    /**
     * The LogCat tag
     */
    public static final String ADVANCED_ACTIVITY_TAG = "Tuner AdvancedActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(ADVANCED_ACTIVITY_TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced);
    }
    @Override
    protected void onDestroy() {
        Log.d(ADVANCED_ACTIVITY_TAG, "Advanced Destroy");
        new Thread(new Runnable() {
            @Override
            public void run() {
                controller.destroy();
            }
        }).start();
        super.onDestroy();
    }
    public void loadMain(View view) {
        controller.mainDisplay(this);
    }
    public void loadSettings(View view) {
        controller.settingsDisplay(this);
    }
    public void loadTraining(View view) {
        controller.trainingDisplay(this);
    }

    /**
     * Display the controllers current status:
     *  for instance, whether it is listening for input, reading a file, or processing data.
     */
    @Override
    public void showStatus(String status) {

    }


    /**
     * Displays the given sections (each note) to the user.
     */
    @Override
    public void displaySections(List<PlayedSection> sections) {

    }
    @Override
    public void onRequestPermissionsResult(int request,String[] permissions, int[] results) {
        switch (request) {
            case MainActivity.MICROPHONE_REQUEST:
                if (permissions.length>0) {
                    if (results[0]== PackageManager.PERMISSION_GRANTED) {
                        micPermissionGranted=true;
                    } else MainActivity.getMicrophonePermission(this,MainActivity.MICROPHONE_REQUEST);
                }
                break;
        }

    }
}

