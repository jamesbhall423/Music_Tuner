package school.team.musictuner;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;

/**
 * Provides the user with an interface to adjust app settings.
 */
public class SettingsActivity extends AppCompatActivity {
    private Settings settings;


    public static final String SETTINGS_ACTIVITY_TAG = "Tuner SettingsActivity";
    public static final String SETTINGS = "Settings";

    /**
     * Sets up the SettingsActivity and all necessary variables.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(SETTINGS_ACTIVITY_TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Intent intent = getIntent();
        //settings = (Settings) intent.getSerializableExtra(SETTINGS);
        settings = MainController.settings;
        ((TextView) findViewById(R.id.thresholdInput)).setText(Double.valueOf(settings.getSensitivity()).toString());
    }
    @Override
    protected void onStop() {
        Log.d(SETTINGS_ACTIVITY_TAG, "onStop");

        super.onStop();
    }

    @Override
    protected void onPause() {
        Log.d(SETTINGS_ACTIVITY_TAG, "onPause");
        TextView thresholdInput = (TextView) findViewById(R.id.thresholdInput);
        try {
            double threshold = Double.parseDouble(thresholdInput.getText().toString());
            settings.setSensitivity(threshold);
            storeSettings(settings);
        } catch (RuntimeException e) {
            Log.e(SETTINGS_ACTIVITY_TAG,e.getMessage());
        }
        super.onPause();
    }


    /**
     * Stores the user's adjusted settings in the user's shared preferences.
     * @param settings the settings to be stored.
     */
    public void storeSettings(Settings settings){
        SharedPreferences mPrefs = getSharedPreferences(Settings.NAME,MODE_PRIVATE);
        Gson gson = new Gson();
        String json = gson.toJson(settings);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.putString("NAME", json);
        prefsEditor.commit();
    }

    /**
     * Loads the MainActivity.
     * @param view
     */
    public void loadMain(View view) {
        Intent intentLoad = new Intent(this, MainActivity.class);
        startActivity(intentLoad);
    }
}
