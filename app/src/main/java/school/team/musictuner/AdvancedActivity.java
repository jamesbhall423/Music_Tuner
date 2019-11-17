package school.team.musictuner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class AdvancedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
}

