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
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * MainActivity, the initial activity when the app is booted, will display notes
 * as given to it from the MainController. Also includes the navigation to the
 * other activities
 */
public class MainActivity extends AppCompatActivity implements MainDisplay {
    private MainController controller = new MainController();
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
        new Thread(new Runnable() {
            @Override
            public void run() {
                controller.pause();
            }
        }).start();
        super.onDestroy();
    }
    @Override
    protected void onStart() {
        Log.d(MAIN_ACTIVITY_TAG, "Main Start");
        new Thread(new Runnable() {
            @Override
            public void run() {
                controller.start();
            }
        }).start();
        super.onStart();
    }

    /**
     * Calls the MainController to display the Advanced Activity
     * @param view
     */
    public void loadAdvanced(View view) {
        controller.advancedDisplay(this);
    }

    /**
     * Calls the controller to display the Settings Activity
     * @param view
     */
    public void loadSettings(View view) {
        controller.settingsDisplay(this);
    }

    /**
     * Calls the MainController to display the Training Activity
     * @param view
     */
    public void loadTraining(View view) {
        controller.trainingDisplay(this);
    }

    /**
     * Displays the given pitch and its deviation from the ideal note.
     * Must be called on UI Thread
     */
    @Override
    public void displayNote(Pitch pitch, Note note) {
        NumberFormat formatter = new DecimalFormat("#0.0");
        double diff = (pitch.getFrequency()-note.frequency)*100/note.frequency;

        letterNoteTextView.setText(note.toString()+" Val="+formatter.format(pitch.getFrequency())+"hz. Ideal="+formatter.format(note.frequency)+"hz. Diff="+formatter.format(diff)+"%");


    }

    @Override
    public void runOnUIThread(Runnable runnable) {

    }
}
