package chengcheng.colormixing;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class AddColor extends AppCompatActivity {

    Button button, display;
    SeekBar sb_red, sb_green, sb_blue;
    TextView red_c, green_c, blue_c;



    int red, green, blue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_color);

        red = 255;
        green = 255;
        blue = 255;

        button = (Button) findViewById(R.id.add_button);
        display = (Button) findViewById(R.id.color_dis);
        sb_red = (SeekBar) findViewById(R.id.sb_red);
        sb_green = (SeekBar) findViewById(R.id.sb_green);
        sb_blue = (SeekBar) findViewById(R.id.sb_blue);
        red_c = (TextView) findViewById(R.id.red_count);
        green_c = (TextView) findViewById(R.id.green_count);
        blue_c = (TextView) findViewById(R.id.blue_count);

        sb_red.setMax(red);
        sb_red.setProgress(red);

        sb_green.setMax(green);
        sb_green.setProgress(green);

        sb_blue.setMax(blue);
        sb_blue.setProgress(blue);

        display.setBackgroundColor(Color.argb(255, red, green, blue));

        sb_red.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                red = progress;
                String text = Integer.toString(progress);
                red_c.setText(text);
                display.setBackgroundColor(Color.argb(255, red, green, blue));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sb_green.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                green = progress;
                String text = Integer.toString(progress);
                green_c.setText(text);
                display.setBackgroundColor(Color.argb(255, red, green, blue));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sb_blue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                blue = progress;
                String text = Integer.toString(progress);
                blue_c.setText(text);
                display.setBackgroundColor(Color.argb(255, red, green, blue));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorList color = new ColorList(255, red, green, blue);
                Intent data = new Intent();
                data.putExtra("COLOR",color);
                setResult(RESULT_OK, data);
                finish();
            }
        });

    }
}
