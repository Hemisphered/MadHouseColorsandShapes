package xyz.tanishq.madhousecolorsshapes.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import xyz.tanishq.madhousecolorsshapes.R;

/**
 * Created by Hemant on 8/19/2017.
 */

public class ShapeGridAdapter extends BaseAdapter {
    private Context context;

    public ShapeGridAdapter(Context context) {
        this.context = context;
    }


    @Override
    public int getCount() {
        return 6;
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
        View gridViewShape;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            gridViewShape = new View(context);
            gridViewShape = inflater.inflate(R.layout.shapes, parent, false);
            ImageView shapeImage = (ImageView) gridViewShape.findViewById(R.id.shapeImage);
            int width = 320;
            int height = 320;
            Bitmap bitmap = Bitmap.createBitmap(height, width, Bitmap.Config.ARGB_8888);
            bitmap = bitmap.copy(bitmap.getConfig(), true);
            Canvas canvas = new Canvas(bitmap);
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setColor(Color.BLACK);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(4.5f);
            if (position == 0) {
                canvas.drawCircle(width / 2, height / 2, (width / 2) - 5, paint);
            } else if (position == 1) {
                canvas.drawRect(0, height - 10, width, 100, paint);
            } else if (position == 2) {
                int x = 150;
                int y = 150;
                int halfWidth = (height - 50) / 2;
                Path path = new Path();
                path.moveTo(x, y - halfWidth); // Top
                path.lineTo(x - halfWidth, y + halfWidth); // Bottom left
                path.lineTo(x + halfWidth, y + halfWidth); // Bottom right
                path.lineTo(x, y - halfWidth); // Back to Top
                path.close();

                canvas.drawPath(path, paint);
            }else if(position == 3){
                canvas.drawRect(0,height,width,50,paint);
            }else if(position == 4){
                Path path = new Path();
                final int size = Math.min(width, height);
                final int centerX =  (width / 2);
                final int centerY =  (height / 2);
                final float section = (float) (2.0 * Math.PI / 5);
                int radius = size / 2;
                path.reset();
                path.moveTo((centerX + radius * (float) Math.cos(0)), (centerY + radius
                        *(float) Math.sin(0)));

                for (int i = 1; i < 6; i++) {
                    path.lineTo((centerX + radius *(float) Math.cos(section * i)),
                            (centerY + radius * (float) Math.sin(section * i)));
                }
                canvas.drawPath(path,paint);
            }else if(position==5){
                Path path = new Path();
                final int size = Math.min(width, height);
                final int centerX =  (width / 2);
                final int centerY =  (height / 2);
                final float section = (float) (2.0 * Math.PI / 6);
                int radius = size / 2;
                path.reset();
                path.moveTo((centerX + radius * (float) Math.cos(0)), (centerY + radius
                        *(float) Math.sin(0)));

                for (int i = 1; i < 7; i++) {
                    path.lineTo((centerX + radius *(float) Math.cos(section * i)),
                            (centerY + radius * (float) Math.sin(section * i)));
                }
                canvas.drawPath(path,paint);
            }
            shapeImage.setImageBitmap(bitmap);
        } else {
            gridViewShape = convertView;
        }
        return gridViewShape;

    }
}
