package chengcheng.colormixing;

import android.graphics.Color;
import android.widget.TextView;

/**
 * Created by chengchengwang on 8/16/17.
 */

public class ColorList {
    public int alpha;
    public int color;

    public ColorList(int alpha, int color) {
        this.alpha = alpha;
        this.color = color;
    }

    public int getAlpha() {
        return alpha;
    }

    public int getColor() {
        return color;
    }
}
