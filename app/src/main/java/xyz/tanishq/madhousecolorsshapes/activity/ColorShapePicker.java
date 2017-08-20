package xyz.tanishq.madhousecolorsshapes.activity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import xyz.tanishq.madhousecolorsshapes.R;
import xyz.tanishq.madhousecolorsshapes.adapters.ColorGridAdapter;
import xyz.tanishq.madhousecolorsshapes.adapters.ShapeGridAdapter;

public class ColorShapePicker extends AppCompatActivity {

    private GridView shapeGrid;
    private GridView colorGrid;
    private TextView mainText;
    private ImageView mainImage;
    private boolean isColored = false;
    private boolean isShaped = false;
    private int selectedColor;
    private int selectedShape;
    private static final String TAG = "ColorShapePicker";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_shape_picker);

        shapeGrid = (GridView) findViewById(R.id.shapegrid);
        colorGrid = (GridView) findViewById(R.id.colorgrid);
        mainText = (TextView) findViewById(R.id.mainText);
        mainImage = (ImageView) findViewById(R.id.mainImage);
        shapeGrid.setAdapter(new ShapeGridAdapter(getApplicationContext()));
        shapeGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (isShaped && selectedShape == position) {
                    isShaped = false;
                    mainText.setText("");
                } else {
                    isShaped = true;
                    selectedShape = position;
                }
                setColor();
            }
        });
        final int[] colors = this.getResources().getIntArray(R.array.gridColors);
        Log.d(TAG, "onCreate: " + colors.length);
        colorGrid.setAdapter(new ColorGridAdapter(getApplicationContext(), colors));
        colorGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (isColored && selectedColor == position) {
                    isColored = false;
                    mainText.setText("");
                } else {
                    isColored = true;
                    selectedColor = position;
                }
                setColor();
            }
        });
    }

    void setColor() {
        final int[] colors = this.getResources().getIntArray(R.array.gridColors);
        final String[] colorNames = this.getResources().getStringArray(R.array.colorNames);
        final String[] shapes = this.getResources().getStringArray(R.array.shapeNames);
        if (isColored) {
            if (isShaped) {
                mainText.setText(colorNames[selectedColor] + " " + shapes[selectedShape]);
                mainText.setTextColor(colors[selectedColor]);
                drawShape(selectedShape, colors[selectedColor]);
            } else {
                mainImage.setImageBitmap(null);
                mainText.setText(colorNames[selectedColor]);
                mainText.setTextColor(colors[selectedColor]);
            }
        } else {
            if (isShaped) {
                mainText.setText(shapes[selectedShape]);
                mainText.setTextColor(Color.BLACK);
                drawShape(selectedShape, Color.WHITE);
            } else {
                mainText.setText("");
                mainImage.setImageBitmap(null);
            }
        }
    }

    void drawShape(int position, int color) {

        int width = 320;
        int height = 320;
        Bitmap bitmap = Bitmap.createBitmap(height, width, Bitmap.Config.ARGB_8888);
        bitmap = bitmap.copy(bitmap.getConfig(), true);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);

        if (isColored) {
            paint.setColor(color);
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
        } else {
            paint.setColor(Color.BLACK);
            paint.setStyle(Paint.Style.STROKE);
        }

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
        } else if (position == 3) {
            canvas.drawRect(0, height, width, 50, paint);
        } else if (position == 4) {
            Path path = new Path();
            final int size = Math.min(width, height);
            final int centerX = (width / 2);
            final int centerY = (height / 2);
            final float section = (float) (2.0 * Math.PI / 5);
            int radius = size / 2;
            path.reset();
            path.moveTo((centerX + radius * (float) Math.cos(0)), (centerY + radius
                    * (float) Math.sin(0)));

            for (int i = 1; i < 6; i++) {
                path.lineTo((centerX + radius * (float) Math.cos(section * i)),
                        (centerY + radius * (float) Math.sin(section * i)));
            }
            canvas.drawPath(path, paint);
        } else if (position == 5) {
            Path path = new Path();
            final int size = Math.min(width, height);
            final int centerX = (width / 2);
            final int centerY = (height / 2);
            final float section = (float) (2.0 * Math.PI / 6);
            int radius = size / 2;
            path.reset();
            path.moveTo((centerX + radius * (float) Math.cos(0)), (centerY + radius
                    * (float) Math.sin(0)));

            for (int i = 1; i < 7; i++) {
                path.lineTo((centerX + radius * (float) Math.cos(section * i)),
                        (centerY + radius * (float) Math.sin(section * i)));
            }
            canvas.drawPath(path, paint);
        }
        mainImage.setImageBitmap(bitmap);
    }
}
