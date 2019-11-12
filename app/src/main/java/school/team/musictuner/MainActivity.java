package school.team.musictuner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

//James Hall comment1
//Josh Spendlove Comment 2 + 1
//Zaq Nelson Comment 3
//Another Comment
//James Hall comment merge
//James comment X
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
}

//Forest the Confused comment1