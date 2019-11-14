package school.team.musictuner;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void storeSettings(Settings settings){
        SharedPreferences mPrefs = getSharedPreferences(Settings.NAME,MODE_PRIVATE);
        Gson gson = new Gson();
        String json = gson.toJson(settings);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.putString("NAME", json);
        prefsEditor.commit();
    }
}
