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
        Intent intentLoad = new Intent(this, MainActivity.class);
        startActivity(intentLoad);
    }
    public void loadSettings() {
        Intent intentLoad = new Intent(this, SettingsActivity.class);
        startActivity(intentLoad);
    }
    public void loadTraining() {
        Intent intentLoad = new Intent(this, TrainingActivity.class);
        startActivity(intentLoad);
    }
}

