package chengcheng.colormixing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class Homepage extends AppCompatActivity {
    private Button lightMixing;
    private Button colorMixing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        lightMixing = (Button) findViewById(R.id.light_mixing_button);
        colorMixing = (Button) findViewById(R.id.color_mixing_button);

        /** go to light mixing page **/
        lightMixing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLight = new Intent(Homepage.this, MainActivity.class);
                startActivity(intentLight);
            }
        });

        /** go to color mixing page **/
        colorMixing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLight = new Intent(Homepage.this, PaintingColorMixingActivity.class);
                startActivity(intentLight);
            }
        });


    }
}
