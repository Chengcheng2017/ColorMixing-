package chengcheng.colormixing;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by chengchengwang on 8/16/17.
 */

public class ColorAdapter extends BaseAdapter{
    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<ColorList> mDataSource;

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
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = mInflater.inflate(R.layout.color_list_item, parent, false);

        TextView colorSquare = (TextView) rowView.findViewById(R.id.color_square);
        SeekBar colorAdjust = (SeekBar) rowView.findViewById(R.id.color_adjust);

        ColorList colorlist = mDataSource.get(position);
        int alpha = colorlist.getAlpha();
        int red = colorlist.getRed();
        int green = colorlist.getGreen();
        int blue = colorlist.getBlue();
        colorSquare.setBackgroundColor(Color.argb(alpha,red, green, blue));
        colorAdjust.setProgress(255);

        return rowView;
    }
}
