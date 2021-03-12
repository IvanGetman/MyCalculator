package mycalculator.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View.OnClickListener click = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Click", Toast.LENGTH_SHORT).show();
            }
        };

        int[] buttonID = {
                R.id.button_1,
                R.id.button_2,
                R.id.button_3,
                R.id.button_4,
                R.id.button_5,
                R.id.button_6,
                R.id.button_7,
                R.id.button_8,
                R.id.button_9,
                R.id.button_10,
                R.id.button_11,
                R.id.button_12,
                R.id.button_13,
                R.id.button_14,
                R.id.button_15,
                R.id.button_16,
                R.id.button_17,
                R.id.button_18,
        };
        for (int i = 0; i < buttonID.length; i++) {
            findViewById(buttonID[i]).setOnClickListener(click);
        }
    }
}