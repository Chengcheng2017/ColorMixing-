package chengcheng.colormixing;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class PaintingColorMixingActivity extends AppCompatActivity {

    private Button colorDisplay;
    private Button addColor;
    private Button clearAll;
    private ListView colorList;
    private PaintingColorAdapter adapter;
    final ArrayList<ColorList> colorLists = new ArrayList<ColorList>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_painting_color_mixing);
        colorDisplay = (Button) findViewById(R.id.painting_color_display);
        addColor = (Button) findViewById(R.id.add_painting_color);
        clearAll = (Button) findViewById(R.id.clear_all_2);
        colorList = (ListView) findViewById(R.id.painting_color_list);

        colorDisplay.setBackgroundColor(Color.argb(255, 255, 255, 255));

        adapter = new PaintingColorAdapter(this, colorLists);
        colorList.setAdapter(adapter);


        addColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaintingColorMixingActivity.this, AddPaintingColor.class);
                startActivityForResult(intent, RequestCode.ADD_COLOR);
            }
        });

        clearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorLists.clear();
                adapter.notifyDataSetChanged();
                colorDisplay.setBackgroundColor(Color.argb(255, 255, 255, 255));
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RequestCode.ADD_COLOR && resultCode == RESULT_OK) {
            ColorList color = (ColorList) data.getSerializableExtra("PAINTINGCOLOR");
            colorLists.add(color);
            adapter.notifyDataSetChanged();

            int r = 0;
            int g = 0;
            int b = 0;
            int size = adapter.getCount();

            for (ColorList c: adapter.mDataSource2){
                r += c.getRed() * c.getAlpha();
                g += c.getGreen() * c.getAlpha();
                b += c.getBlue() * c.getAlpha();
            }
            if(size != 0) {
                r = size*255 - r / 255;
                g = size*255 - g / 255;
                b = size*255 - b / 255;
            }
            if (r < 0) r = 0;
            if (g < 0) g = 0;
            if (b < 0) b = 0;

            colorDisplay.setBackgroundColor(Color.argb(255, 255 - r, 255 - g, 255 - b));

        }
    }

}

