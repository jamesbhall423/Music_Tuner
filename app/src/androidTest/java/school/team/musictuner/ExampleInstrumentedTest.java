package school.team.musictuner;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.io.File;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;


/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    public static final String TAG = "Tuner ExampleInstrumented";
    public static final String AUDIO_FILE = "/data/data/school.team.musictuner.test/audioTest.wav";
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("school.team.musictuner", appContext.getPackageName());
        assertEquals(4, 2+2);
    }
    @Test
    public void soundFileTest() {
        /*Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        // Here, context is the current activity
        if (ContextCompat.checkSelfPermission(context,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(context.,
                    Manifest.permission.READ_CONTACTS)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(context,
                        new String[]{Manifest.permission.READ_CONTACTS},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
        }*/
        if (ContextCompat.checkSelfPermission(InstrumentationRegistry.getInstrumentation().getTargetContext(), Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG,"Microphone Permission not granted");
        }
        //Requires audioTest.wav be added to android device.
        //This is done via View->Tools Windows->device explorer.
        //Other people may place the file in a different location.
        Sound sound = null;
        assertTrue("directory data does not exist",new File("data/").exists());
        assertTrue("directory data/data does not exist",new File("data/data").exists());
        assertTrue("directory does not exist",new File("data/data/school.team.musictuner.test/").exists());
        assertTrue("Audio file does not exist",new File(AUDIO_FILE).exists());
        try {
            sound = new Sound(AUDIO_FILE);
        } catch (IOException e) {
            throw new AssertionError(e);
        }
        assertTrue(sound.samplesPerSecond()+"",sound.samplesPerSecond()==(44.1*1000));
        assertTrue(sound.length()/sound.samplesPerSecond()+"",(sound.length()/sound.samplesPerSecond())>2.9&&(sound.length()/sound.samplesPerSecond())<3.1);
        double[] out = new double[sound.length()];
        for (int i = 0; i < out.length; i++) out[i]=sound.getDataAt(i);

        double waveBelow = fourierTransform(out,42.1);
        double waveEquals = fourierTransform(out,44.1);
        double waveAbove = fourierTransform(out, 46.1);
        assertFalse(waveBelow+"",waveBelow>5);
        assertTrue(waveEquals+"",waveEquals>10);
        assertFalse(waveAbove+"",waveAbove>5);
    }
    @Test
    public void soundMicTest() {

        if (ContextCompat.checkSelfPermission(InstrumentationRegistry.getInstrumentation().getTargetContext(), Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG,"Microphone Permission not granted");
        }
        //Requires audioTest.wav be added to android device.
        //This is done via View->Tools Windows->device explorer.
        //Other people may place the file in a different location.

        MediaPlayer mp = new MediaPlayer();

        try {
            mp.setDataSource(AUDIO_FILE);
            mp.prepare();
            mp.start();
        } catch (Exception e) {
            throw new AssertionError(e);
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Sound sound = new Sound(1.0);
        double[] out = new double[sound.length()];
        for (int i = 0; i < out.length; i++) out[i]=sound.audioRecord.get(i);

        double waveBelow = fourierTransform(out,sound.samplesPerSecond()/1050.0);
        double waveEquals = fourierTransform(out,sound.samplesPerSecond()/1000.0);
        double waveAbove = fourierTransform(out, sound.samplesPerSecond()/950.0);
        Log.d("Author Mic","samples per Second "+sound.samplesPerSecond());
        Log.d("Author Mic", "First sample "+sound.getDataAt(0));
        Log.d("Author Mic","Second Sample"+sound.getDataAt(100));
        Log.d("Author Mic", "Third Sample"+sound.getDataAt(200));
        Log.d("Author Mic", "First sample "+sound.getDataAt(3000));
        Log.d("Author Mic","Second Sample"+sound.getDataAt(3100));
        Log.d("Author Mic", "Third Sample"+sound.getDataAt(3200));
        Log.d("Author Mic", "First sample "+sound.getDataAt(6000));
        Log.d("Author Mic","Second Sample"+sound.getDataAt(6100));
        Log.d("Author Mic", "Third Sample"+sound.getDataAt(6200));
        Log.d("Author Mic","Wave Below: "+waveBelow);
        Log.d("Author Mic","Wave Equals: "+waveEquals);
        Log.d("Author Mic","Wave Above: "+waveAbove);
        assertFalse(waveBelow+"",waveBelow>100);
        assertTrue(waveEquals+"",waveEquals>500);
        assertFalse(waveAbove+"",waveAbove>100);
    }
    public static double fourierTransform(double[] signal, double sampleWavelength) {
        //If the signal is a simple harmonic, it will inevitably be a combination of the sin and cos trigonametric functions.
        double phaseSin = 0.0; //One possible phase for the frequency
        double phaseCos = 0.0; //The inverse phase for the frequency
        for (int i = 0; i < signal.length; i++) {
            double angleTraveled = 2*Math.PI*i/sampleWavelength;
            phaseSin += Math.sin(angleTraveled)*signal[i]; //sum correlation with sin phase
            phaseCos += Math.cos(angleTraveled)*signal[i]; //sum correlation with cos phase
        }
        phaseSin/=signal.length; //average correlation with the sin function
        phaseCos/=signal.length; //average correlation with the cos function
        return 2*Math.sqrt(phaseSin*phaseSin+phaseCos*phaseCos); //Perfect match gives value 1/2 the amplitude, hence *2
    }

}
