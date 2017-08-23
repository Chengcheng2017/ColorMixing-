package chengcheng.colormixing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView colorDisplay;
    private Button addColor;
    private ListView colorList;
    final ArrayList<ColorList> colorLists = new ArrayList<ColorList>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        colorDisplay = (TextView) findViewById(R.id.color_display);
        addColor = (Button) findViewById(R.id.add_color);
        colorList = (ListView) findViewById(R.id.color_list);

        ColorAdapter adapter = new ColorAdapter(this, colorLists);
        colorList.setAdapter(adapter);

        addColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddColor.class);
                startActivityForResult(intent, RequestCode.ADD_COLOR);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RequestCode.ADD_COLOR && resultCode == RESULT_OK) {

        }
    }
}
