package xyz.tanishq.madhousecolorsshapes.activity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import xyz.tanishq.madhousecolorsshapes.R;

public class ColorShapePicker extends AppCompatActivity {


    private TextView mainText;
    private ImageView mainImage;
    private boolean isColored = false;
    private boolean isShaped = false;
    private int selectedColor;
    private int selectedShape;
    private static final String TAG = "ColorShapePicker";
    private int[] colorSounds = {
            R.raw.black,
            R.raw.red,
            R.raw.orange,
            R.raw.yellow,
            R.raw.green,
            R.raw.blue
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_shape_picker);

        mainText = (TextView) findViewById(R.id.outputText);
        mainImage = (ImageView) findViewById(R.id.outputImage);
        final LinearLayout circle = (LinearLayout) findViewById(R.id.circle);
        LinearLayout rectangle = (LinearLayout) findViewById(R.id.rectangle);
        LinearLayout triangle = (LinearLayout) findViewById(R.id.triangle);
        LinearLayout square = (LinearLayout) findViewById(R.id.square);
        LinearLayout pentagon = (LinearLayout) findViewById(R.id.pentagon);
        LinearLayout hexagon = (LinearLayout) findViewById(R.id.hexagon);
        LinearLayout black = (LinearLayout) findViewById(R.id.black);
        LinearLayout red = (LinearLayout) findViewById(R.id.red);
        LinearLayout orange = (LinearLayout) findViewById(R.id.orange);
        LinearLayout yellow = (LinearLayout) findViewById(R.id.yellow);
        LinearLayout green = (LinearLayout) findViewById(R.id.green);
        LinearLayout blue = (LinearLayout) findViewById(R.id.blue);
        circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShaped && selectedShape == 0) {
                    isShaped = false;
                } else {
                    isShaped = true;
                    selectedShape = 0;
                }
                setColor();
            }
        });
        rectangle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShaped && selectedShape == 1) {
                    isShaped = false;
                } else {
                    isShaped = true;
                    selectedShape = 1;
                }
                setColor();
            }
        });
        triangle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShaped && selectedShape == 2) {
                    isShaped = false;
                } else {
                    isShaped = true;
                    selectedShape = 2;
                }
                setColor();
            }
        });
        square.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShaped && selectedShape == 3) {
                    isShaped = false;
                } else {
                    isShaped = true;
                    selectedShape = 3;
                }
                setColor();
            }
        });
        pentagon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShaped && selectedShape == 4) {
                    isShaped = false;
                } else {
                    isShaped = true;
                    selectedShape = 4;
                }
                setColor();
            }
        });
        hexagon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShaped && selectedShape == 5) {
                    isShaped = false;
                } else {
                    isShaped = true;
                    selectedShape = 5;
                }
                setColor();
            }
        });
        black.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isColored && selectedColor == 0) {
                    isColored = false;
                } else {
                    isColored = true;
                    selectedColor = 0;
                }
                setColor();
            }
        });
        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isColored && selectedColor == 1) {
                    isColored = false;
                } else {
                    isColored = true;
                    selectedColor = 1;
                }
                setColor();
            }
        });
        orange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isColored && selectedColor == 2) {
                    isColored = false;
                } else {
                    isColored = true;
                    selectedColor = 2;
                }
                setColor();
            }
        });
        yellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isColored && selectedColor == 3) {
                    isColored = false;
                } else {
                    isColored = true;
                    selectedColor = 3;
                }
                setColor();
            }
        });
        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isColored && selectedColor == 4) {
                    isColored = false;
                } else {
                    isColored = true;
                    selectedColor = 4;
                }
                setColor();
            }
        });
        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isColored && selectedColor == 5) {
                    isColored = false;
                } else {
                    isColored = true;
                    selectedColor = 5;
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
            MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), colorSounds[selectedColor]);
            mediaPlayer.start();
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
