package chengcheng.colormixing;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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

public class ColorAdapter extends BaseAdapter {
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
        final View rowView = mInflater.inflate(R.layout.color_list_item, parent, false);
        final TextView colorSquare = (TextView) rowView.findViewById(R.id.color_square);
        SeekBar colorAdjust = (SeekBar) rowView.findViewById(R.id.color_adjust);
        final TextView alphaPercent = (TextView) rowView.findViewById(R.id.alpha_percent);
        final Button delete = (Button) rowView.findViewById(R.id.delete);

        /** delete current item and update the data **/
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDataSource.remove(position);
                notifyDataSetChanged();
                setBackground();
            }
        });

        ColorList colorlist = mDataSource.get(position);
        final int alpha = colorlist.getAlpha();
        final int red = colorlist.getRed();
        final int green = colorlist.getGreen();
        final int blue = colorlist.getBlue();

        /** regard alpha / 255 as the percent, update rgb **/
        int r_alpha = red * alpha;
        int g_alpha = green * alpha;
        int b_alpha = blue * alpha;

        r_alpha = r_alpha / 255;
        g_alpha = g_alpha / 255;
        b_alpha = b_alpha / 255;

        colorSquare.setBackgroundColor(Color.argb(255, r_alpha, g_alpha, b_alpha)); //update background color of colorSquare
        colorAdjust.setProgress(colorlist.getAlpha());
        int a_per = (int) (alpha / 2.55);
        String a_percent = Integer.toString(a_per) + "%";
        alphaPercent.setText(a_percent);

        colorAdjust.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mDataSource.get(position).setAlpha(progress);
                int percent = mDataSource.get(position).getAlpha();

                /** regard alpha / 255 as the percent, update rgb **/
                int r_p = red * percent;
                int g_p = green * percent;
                int b_p = blue * percent;

                r_p = r_p / 255;
                g_p = g_p / 255;
                b_p = b_p / 255;

                colorSquare.setBackgroundColor(Color.argb(255, r_p, g_p, b_p));
                /** display percent **/
                int a_per = (int) (progress / 2.55);
                String a_percent = Integer.toString(a_per) + "%";
                alphaPercent.setText(a_percent);

                setBackground();
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

    /**
     * calculate lights mixing (addictive mixing)
     **/
    public void setBackground() {
        int r = 0;
        int g = 0;
        int b = 0;
        int size = mDataSource.size();

        for (ColorList c : mDataSource) {
            r += c.getRed() * c.getAlpha();
            g += c.getGreen() * c.getAlpha();
            b += c.getBlue() * c.getAlpha();
        }
        if (size != 0) {
            r = r / 255;
            g = g / 255;
            b = b / 255;
        }
        if (r > 255) r = 255;
        if (g > 255) g = 255;
        if (b > 255) b = 255;

        Button colorDisplay = (Button) ((Activity) mContext).findViewById(R.id.color_display);
        colorDisplay.setBackgroundColor(Color.argb(255, r, g, b));
    }

}
