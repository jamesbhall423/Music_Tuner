package school.team.musictuner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;
import androidx.lifecycle.AndroidViewModel;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
    public static final int MICROPHONE_REQUEST = 1;
    private MainController controller = new MainController();
    /**
     * Various Variables for the MainActivity
     */
    public static final String MAIN_ACTIVITY_TAG = "Tuner MainActivity";
    private Settings settings;
    private Gson gson;
    private View noteView;
    private TextView letterNoteTextView;
    private boolean micPermissionGranted=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(MAIN_ACTIVITY_TAG, "onCreate");
        super.onCreate(savedInstanceState);
        getMicrophonePermission(this,1);
        controller.loadSettings(this);
        controller.setDisplay(this);
        if (ContextCompat.checkSelfPermission(this,Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {
            Log.d(MAIN_ACTIVITY_TAG,"Microphone Permission not granted");
        }
        gson = new Gson();
        setContentView(R.layout.activity_main);
        noteView = findViewById(R.id.Note);
        letterNoteTextView = findViewById(R.id.letterNote);
        SharedPreferences sharedPref = this.getSharedPreferences(Settings.NAME, Context.MODE_PRIVATE);
        settings = gson.fromJson(sharedPref.getString(Settings.NAME, ""), Settings.class);
        if (settings!=null) controller.getConverter().setSettings(settings);
        else settings=controller.getConverter().getSettings();

            /*AudioDecoderThread sound = new AudioDecoderThread();
            sound.startPlay("/storage/emulated/0/Music/Test.m4a");*/

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
       // new Thread(new Runnable() {
       //     @Override
       //     public void run() {
                controller.pause();
      //      }
      //  }).start();
        super.onDestroy();
        System.exit(0);
    }
    @Override
    protected void onStart() {
        Log.d(MAIN_ACTIVITY_TAG, "Main Start");
        //new Thread(new Runnable() {
        //    @Override
        //    public void run() {
                if (micPermissionGranted) controller.start();
                else getMicrophonePermission(MainActivity.this,MICROPHONE_REQUEST);
       //     }
       // }).start();
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
    public static void getMicrophonePermission(AppCompatActivity activity, int request) {
// Here, activity is the current activity
        if (ContextCompat.checkSelfPermission(activity,
                Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                    Manifest.permission.RECORD_AUDIO)) {
                Toast.makeText(activity, "The app needs audio input in order to process sound.", Toast.LENGTH_LONG).show();
                ActivityCompat.requestPermissions(activity,
                        new String[]{Manifest.permission.RECORD_AUDIO}, request);
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(activity,
                        new String[]{Manifest.permission.RECORD_AUDIO}, request);

            }
        } else {
            activity.onRequestPermissionsResult(request,new String[]{Manifest.permission.RECORD_AUDIO},new int[]{PackageManager.PERMISSION_GRANTED});
        }
    }
    @Override
    public void onRequestPermissionsResult(int request,String[] permissions, int[] results) {
        switch (request) {
            case MainActivity.MICROPHONE_REQUEST:
                if (permissions.length>0) {
                    if (results[0]==PackageManager.PERMISSION_GRANTED) {
                        micPermissionGranted=true;
                        controller.start();
                    } else MainActivity.getMicrophonePermission(this,MainActivity.MICROPHONE_REQUEST);
                }
                break;
        }

    }

}
