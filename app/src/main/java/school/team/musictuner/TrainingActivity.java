package school.team.musictuner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class TrainingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);
    }
    public void loadAdvanced(View view) {
        Intent intentLoad = new Intent(this, AdvancedActivity.class);
        startActivity(intentLoad);
    }
    public void loadSettings(View view) {
        Intent intentLoad = new Intent(this, SettingsActivity.class);
        startActivity(intentLoad);
    }
    public void loadMain(View view) {
        Intent intentLoad = new Intent(this, MainActivity.class);
        startActivity(intentLoad);
    }
}
