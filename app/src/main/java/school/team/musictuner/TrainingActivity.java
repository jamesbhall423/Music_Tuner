package school.team.musictuner;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class TrainingActivity extends AppCompatActivity {

    public static final String TRAINING_ACTIVITY_TAG = "Tuner TrainingActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TRAINING_ACTIVITY_TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);
    }
    public void loadAdvanced(View view) {
        Intent intentLoad = new Intent(this, AdvancedActivity.class);
        startActivity(intentLoad);
    }
    public void loadSettings(View view) {
        Intent intentLoad = new Intent(this, SettingsActivity.class);
        startActivity(intentLoad);
    }
    public void loadMain(View view) {
        Intent intentLoad = new Intent(this, MainActivity.class);
        startActivity(intentLoad);
    }
}
