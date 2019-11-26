package school.team.musictuner;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Brings up the activity to help the user tune their instruments
 */
public class TrainingActivity extends AppCompatActivity {

    public static final String TRAINING_ACTIVITY_TAG = "Tuner TrainingActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TRAINING_ACTIVITY_TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);
    }

    /*
    * This will load the AdvancedActivity.
    */
    public void loadAdvanced(View view) {
        Intent intentLoad = new Intent(this, AdvancedActivity.class);
        startActivity(intentLoad);
    }

    //Checking any changes of the settings by the user
    public void loadSettings(View view) {
        Intent intentLoad = new Intent(this, SettingsActivity.class);
        startActivity(intentLoad);
    }

    //Loading the main view
    public void loadMain(View view) {
        Intent intentLoad = new Intent(this, MainActivity.class);
        startActivity(intentLoad);
    }
}
