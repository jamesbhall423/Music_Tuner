package school.team.musictuner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.GenericSignatureFormatError;

//James Hall comment1
//Josh Spendlove Comment 2 + 1
//Zaq Nelson Comment 3
//Another Comment
//James Hall comment merge
//James comment X
public class MainActivity extends AppCompatActivity {

    private Settings settings;
    private Gson gson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gson = new Gson();
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPref = this.getSharedPreferences(Settings.NAME, Context.MODE_PRIVATE);
        settings = gson.fromJson(sharedPref.getString(Settings.NAME, ""), Settings.class);

            AudioDecoderThread sound = new AudioDecoderThread();
            sound.startPlay("/storage/emulated/0/Music/Test.m4a");

    }
    @Override
    protected void onPause() {
        Log.d("Author", "Main Pause");
        super.onPause();
    }
    @Override
    protected void onStop() {
        Log.d("Author", "Main Stop");
        super.onStop();
    }
    @Override
    protected void onDestroy() {
        Log.d("Author", "Main Destroy");
        super.onDestroy();
    }
    public void loadAdvanced() {
        Intent intentLoad = new Intent(this, AdvancedActivity.class);
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

//Forest the Confused comment1