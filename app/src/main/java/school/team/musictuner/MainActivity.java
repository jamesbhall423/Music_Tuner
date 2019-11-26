package school.team.musictuner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.AndroidViewModel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.GenericSignatureFormatError;

/**
 * MainActivity, the initial activity when the app is booted, will display notes
 * as given to it from the MainController. Also includes the navigation to the
 * other activities
 */
public class MainActivity extends AppCompatActivity implements MainDisplay {
    /**
     * Various Variables for the MainActivity
     */
    public static final String MAIN_ACTIVITY_TAG = "Tuner MainActivity";
    private Settings settings;
    private Gson gson;
    private View noteView;
    private TextView letterNoteTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(MAIN_ACTIVITY_TAG, "onCreate");
        super.onCreate(savedInstanceState);
        gson = new Gson();
        setContentView(R.layout.activity_main);
        noteView = findViewById(R.id.Note);
        letterNoteTextView = findViewById(R.id.letterNote);
        SharedPreferences sharedPref = this.getSharedPreferences(Settings.NAME, Context.MODE_PRIVATE);
        settings = gson.fromJson(sharedPref.getString(Settings.NAME, ""), Settings.class);

            AudioDecoderThread sound = new AudioDecoderThread();
            sound.startPlay("/storage/emulated/0/Music/Test.m4a");

    }
    @Override
    protected void onPause() {
        Log.d(MAIN_ACTIVITY_TAG, "Main Pause");
        super.onPause();
    }
    @Override
    protected void onStop() {
        Log.d(MAIN_ACTIVITY_TAG, "Main Stop");
        super.onStop();
    }
    @Override
    protected void onDestroy() {
        Log.d(MAIN_ACTIVITY_TAG, "Main Destroy");
        super.onDestroy();
    }

    /**
     * Calls the MainController to display the Advanced Activity
     * @param view
     */
    public void loadAdvanced(View view) {
        MainController.advancedDisplay(this);
    }

    /**
     * Calls the MainController to display the Settings Activity
     * @param view
     */
    public void loadSettings(View view) {
        MainController.settingsDisplay(this);
    }

    /**
     * Calls the MainController to display the Training Activity
     * @param view
     */
    public void loadTraining(View view) {
        MainController.trainingDisplay(this);
    }

    /**
     * Displays the given pitch and its deviation from the ideal note.
     * Must be called on UI Thread
     */
    @Override
    public void displayNote(Pitch pitch, Note note) {

        Note playedNote= pitch.getNote(TuneSet.STANDARD);

        letterNoteTextView.setText(playedNote.letter());

    }

    @Override
    public void runOnUIThread(Runnable runnable) {

    }
}
