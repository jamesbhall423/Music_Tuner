package school.team.musictuner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

public class TestSoundMicActivity extends AppCompatActivity {

    private static final String TAG = "Tuner TestSound";
    private boolean micPermissionGranted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_sound_mic);
        MainActivity.getMicrophonePermission(this,MainActivity.MICROPHONE_REQUEST);
    }
    @Override
    public void onRequestPermissionsResult(int request,String[] permissions, int[] results) {
        switch (request) {
            case MainActivity.MICROPHONE_REQUEST:
                if (permissions.length>0) {
                    if (results[0]== PackageManager.PERMISSION_GRANTED) {
                        micPermissionGranted=true;
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                TestSoundMicActivity.this.runTest();
                            }
                        }).start();
                    } else MainActivity.getMicrophonePermission(this,MainActivity.MICROPHONE_REQUEST);
                }
                break;
        }

    }
    private void runTest() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Sound sound = new Sound(0.05);
        double[] out = new double[sound.length()];
        for (int i = 0; i < out.length; i++) out[i]=sound.audioRecord.get(i);

        double waveBelow = fourierTransform(out,sound.samplesPerSecond()/470.0);
        double waveEquals = fourierTransform(out,sound.samplesPerSecond()/440.0);
        double waveAbove = fourierTransform(out, sound.samplesPerSecond()/410.0);
        Log.d(TAG,"samples per Second "+sound.samplesPerSecond());
        Log.d(TAG, "First sample "+sound.getDataAt(0));
        Log.d(TAG,"Second Sample"+sound.getDataAt(100));
        Log.d(TAG, "Third Sample"+sound.getDataAt(200));
        Log.d(TAG, "First sample "+sound.getDataAt(3000));
        Log.d(TAG,"Second Sample"+sound.getDataAt(3100));
        Log.d(TAG, "Third Sample"+sound.getDataAt(3200));
        Log.d(TAG, "First sample "+sound.getDataAt(6000));
        Log.d(TAG,"Second Sample"+sound.getDataAt(6100));
        Log.d(TAG, "Third Sample"+sound.getDataAt(6200));
        Log.d(TAG,"Wave Below: "+waveBelow);
        Log.d(TAG,"Wave Equals: "+waveEquals);
        Log.d(TAG,"Wave Above: "+waveAbove);
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
