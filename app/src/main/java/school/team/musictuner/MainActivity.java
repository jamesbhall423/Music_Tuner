package school.team.musictuner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;

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
        SharedPreferences sharedPreferences = this.getSharedPreferences()
        settings = gson.fromJson();
    }
}

//Forest the Confused comment1