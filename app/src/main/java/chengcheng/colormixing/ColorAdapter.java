package chengcheng.colormixing;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by chengchengwang on 8/16/17.
 */

public class ColorAdapter extends BaseAdapter{
    private Context mContext;
    private LayoutInflater mInflater;
    public ArrayList<ColorList> mDataSource;

    public ColorAdapter(Context context, ArrayList<ColorList> items) {
        mContext = context;
        mDataSource = items;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return mDataSource.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View rowView = mInflater.inflate(R.layout.color_list_item, parent, false);

        final TextView colorSquare = (TextView) rowView.findViewById(R.id.color_square);
        SeekBar colorAdjust = (SeekBar) rowView.findViewById(R.id.color_adjust);

        ColorList colorlist = mDataSource.get(position);
        final int alpha = colorlist.getAlpha();
        final int red = colorlist.getRed();
        final int green = colorlist.getGreen();
        final int blue = colorlist.getBlue();
        colorSquare.setBackgroundColor(Color.argb(alpha,red, green, blue));
        colorAdjust.setProgress(colorlist.getAlpha());

        colorAdjust.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mDataSource.get(position).setAlpha(progress);
                colorSquare.setBackgroundColor(Color.argb(mDataSource.get(position).getAlpha(),red, green, blue));

                int r = 0;
                int g = 0;
                int b = 0;
                int size = mDataSource.size();

                for (ColorList c: mDataSource){
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


                Button colorDisplay = (Button) ((Activity)mContext).findViewById(R.id.color_display);
                colorDisplay.setBackgroundColor(Color.argb(255, r, g, b));


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        return rowView;
    }
}
