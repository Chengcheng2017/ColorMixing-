package chengcheng.colormixing;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button colorDisplay;
    private Button addColor;
    private Button clearAll;
    private ListView colorList;
    private ColorAdapter adapter;
    final ArrayList<ColorList> colorLists = new ArrayList<ColorList>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        colorDisplay = (Button) findViewById(R.id.color_display);
        addColor = (Button) findViewById(R.id.add_color);
        clearAll = (Button) findViewById(R.id.clear_all);
        colorList = (ListView) findViewById(R.id.color_list);

        colorDisplay.setBackgroundColor(Color.argb(255, 0, 0, 0));



        adapter = new ColorAdapter(this, colorLists);
        colorList.setAdapter(adapter);


        addColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddColor.class);
                startActivityForResult(intent, RequestCode.ADD_COLOR);
            }
        });

        clearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorLists.clear();
                adapter.notifyDataSetChanged();
                colorDisplay.setBackgroundColor(Color.argb(255, 0, 0, 0));
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RequestCode.ADD_COLOR && resultCode == RESULT_OK) {
            ColorList color = (ColorList) data.getSerializableExtra("COLOR");
            colorLists.add(color);
            adapter.notifyDataSetChanged();

            int r = 0;
            int g = 0;
            int b = 0;
            int size = adapter.getCount();

            for (ColorList c: adapter.mDataSource){
                r += c.getRed() * c.getAlpha();
                g += c.getGreen() * c.getAlpha();
                b += c.getBlue() * c.getAlpha();
            }
            if(size != 0) {
                r = r / 255;
                g = g / 255;
                b = b / 255;
            }

            if (r > 255) r = 255;
            if (g > 255) g = 255;
            if (b > 255) b = 255;

            colorDisplay.setBackgroundColor(Color.argb(255, r, g, b));

        }
    }
}
