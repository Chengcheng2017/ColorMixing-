package chengcheng.colormixing;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class AddPaintingColor extends AppCompatActivity {

    private Button button, display;
    private SeekBar sb_cyan, sb_magenta, sb_yellow;
    private TextView cyan_c, magenta_c, yellow_c;

    private int cyan, magenta, yellow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_painting_color);

        button = (Button) findViewById(R.id.add_button_p);
        display = (Button) findViewById(R.id.painting_color_dis);
        sb_cyan = (SeekBar) findViewById(R.id.sb_cyan);
        sb_magenta = (SeekBar) findViewById(R.id.sb_magenta);
        sb_yellow = (SeekBar) findViewById(R.id.sb_yellow);
        cyan_c = (TextView) findViewById(R.id.cyan_count);
        magenta_c = (TextView) findViewById(R.id.magenta_count);
        yellow_c = (TextView) findViewById(R.id.yellow_count);

        display.setBackgroundColor(Color.argb(255, 255, 255, 255));

        sb_cyan.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                cyan = progress;
                int a = (int) (progress/2.55);
                String text = Integer.toString(a);
                cyan_c.setText(text);
                display.setBackgroundColor(Color.argb(255, 255-cyan, 255-magenta, 255-yellow));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sb_magenta.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                magenta = progress;
                int a = (int) (progress/2.55);
                String text = Integer.toString(a);
                magenta_c.setText(text);
                display.setBackgroundColor(Color.argb(255, 255-cyan, 255-magenta, 255-yellow));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sb_yellow.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                yellow = progress;
                int a =  (int)(progress/2.55);
                String text = Integer.toString(a);
                yellow_c.setText(text);
                display.setBackgroundColor(Color.argb(255,255-cyan, 255-magenta, 255-yellow));
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
                ColorList color = new ColorList(255, 255-cyan, 255-magenta, 255-yellow);
                Intent data = new Intent();
                data.putExtra("PAINTINGCOLOR",color);
                setResult(RESULT_OK, data);
                finish();
            }
        });

    }
}

