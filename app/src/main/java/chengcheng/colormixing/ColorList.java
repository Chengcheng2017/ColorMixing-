package chengcheng.colormixing;

import android.graphics.Color;
import android.widget.TextView;

import java.io.Serializable;

/**
 * Created by chengchengwang on 8/16/17.
 */

 public class ColorList implements Serializable{
    public int alpha;
    public int red;
    public  int green;
    public  int blue;

    public ColorList(int alpha, int red, int green, int blue) {
        this.alpha = alpha;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public int getAlpha() {
        return alpha;
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }
}
