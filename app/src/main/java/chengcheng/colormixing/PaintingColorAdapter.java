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

import java.util.ArrayList;

/**
 * Created by chengchengwang on 8/30/17.
 */

public class PaintingColorAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    public ArrayList<ColorList> mDataSource2;

    public PaintingColorAdapter(Context context, ArrayList<ColorList> items) {
        mContext = context;
        mDataSource2 = items;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return mDataSource2.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataSource2.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final View rowView = mInflater.inflate(R.layout.color_list_item, parent, false);
        final TextView colorSquare = (TextView) rowView.findViewById(R.id.color_square);
        SeekBar colorAdjust = (SeekBar) rowView.findViewById(R.id.color_adjust);
        final TextView alphaPercent = (TextView) rowView.findViewById(R.id.alpha_percent);
        Button delete = (Button) rowView.findViewById(R.id.delete);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDataSource2.remove(position);
                notifyDataSetChanged();
            }
        });

        ColorList colorlist = mDataSource2.get(position);
        final int red = colorlist.getRed();
        final int green = colorlist.getGreen();
        final int blue = colorlist.getBlue();
        colorSquare.setBackgroundColor(Color.argb(255,red, green, blue));
        colorAdjust.setProgress(colorlist.getAlpha());

        colorAdjust.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mDataSource2.get(position).setAlpha(progress);
                int percent = mDataSource2.get(position).getAlpha();
                int r_p = red * percent;
                int g_p = green * percent;
                int b_p = blue * percent;

                r_p = r_p / 255 + 255 - percent;
                g_p = g_p / 255 + 255 - percent;
                b_p = b_p / 255 + 255 - percent;

                colorSquare.setBackgroundColor(Color.argb( 255, r_p, g_p, b_p));
                int a_per = (int) (progress/2.55);
                String a_percent = Integer.toString(a_per) + "%";
                alphaPercent.setText(a_percent);

                int r = 0;
                int g = 0;
                int b = 0;
                int size = mDataSource2.size();

                for (ColorList c: mDataSource2){
                    r += ( 255 - c.getRed()) * c.getAlpha();
                    g += ( 255 - c.getGreen()) * c.getAlpha();
                    b += ( 255 - c.getBlue()) * c.getAlpha();
                }
                if(size != 0) {
                    r = 255 - r / 255;
                    g = 255 - g / 255;
                    b = 255 - b / 255;
                }
                if (r < 0) r = 0;
                if (g < 0) g = 0;
                if (b < 0) b = 0;

                Button colorDisplay = (Button) ((Activity)mContext).findViewById(R.id.painting_color_display);
                colorDisplay.setBackgroundColor(Color.argb(255, r, g, b));

                Log.v("cc", Integer.toString(r));
                Log.v("cc1", Integer.toString(g));
                Log.v("cc2", Integer.toString(b));
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
