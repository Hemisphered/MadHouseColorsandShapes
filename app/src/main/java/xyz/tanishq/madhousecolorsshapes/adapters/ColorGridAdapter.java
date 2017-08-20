package xyz.tanishq.madhousecolorsshapes.adapters;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * Created by Hemant on 8/19/2017.
 */

public class ColorGridAdapter extends BaseAdapter {
    private static final String TAG = "ColorGridAdapter";
    private final int[] colors;
    private Context context;

    public ColorGridAdapter(Context context, int[] colors) {
        this.context = context;
        this.colors = colors;
    }


    @Override
    public int getCount() {
        Log.d(TAG, "getCount: " + colors.length);
        return colors.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = new ImageView(context);
            convertView.setMinimumHeight(320);
            convertView.setMinimumWidth(32);

        }
        ((ImageView)convertView).setImageDrawable(new ColorDrawable(colors[position]));
        return convertView;
    }

}
