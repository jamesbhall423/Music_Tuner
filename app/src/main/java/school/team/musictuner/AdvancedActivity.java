package school.team.musictuner;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class AdvancedActivity extends AppCompatActivity implements AdvancedDisplay{

    public static final String ADVANCED_ACTIVITY_TAG = "Tuner AdvancedActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(ADVANCED_ACTIVITY_TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced);
    }
    public void loadMain(View view) {
        AdvancedController.mainDisplay(this);
    }
    public void loadSettings(View view) {
        AdvancedController.settingsDisplay(this);
    }
    public void loadTraining(View view) {
        AdvancedController.trainingDisplay(this);
    }

    @Override
    public void showStatus(String status) {

    }

    @Override
    public void runOnUIThread(Runnable runnable) {

    }

    @Override
    public void displaySections(List<PlayedSection> sections) {

    }
}

