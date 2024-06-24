package com.example.egzaminui;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class DrawView extends View {

    // Variables to hold colors and sizes of circles :
    private int color1, color2, color3;
    private int size1, size2, size3;
    private int[] colors, sizes; // Arrays to hold multiple circle colors and sizes for the TestActivity

    private int rotDegree = 0; // Rotation degree for canvas

    // Getters for individual circle colors and sizes :
    public int getColor1() {
        return color1;
    }
    public int getColor2() {
        return color2;
    }
    public int getColor3() {
        return color3;
    }
    public int getSize1() {
        return size1;
    }
    public int getSize2() {
        return size2;
    }
    public int getSize3() {
        return size3;
    }

    public int getRotDegree() {
        return rotDegree;
    }

    // Setter for rotation degree and invalidate to trigger redraw
    public void setRotDegree(int rotDegree) {
        this.rotDegree = rotDegree;
        invalidate(); // Redraw the view with new rotation degree
    }

    // Constructors for DrawView class :
    public DrawView(Context context) {
        super(context);
    }
    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public DrawView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    // Method to set individual circle parameters
    public void setCircleParams(int c1, int c2, int c3, int s1, int s2, int s3) {
        this.color1 = c1;
        this.color2 = c2;
        this.color3 = c3;
        this.size1 = s1;
        this.size2 = s2;
        this.size3 = s3;
        invalidate(); // Trigger onDraw to redraw with new parameters
    }

    // Override method for drawing on the canvas
    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas); // Call superclass method first

        // Save the canvas state
        canvas.save();

        // Rotate the canvas based on rotDegree
        canvas.rotate(rotDegree);

        // Initialize Paint objects for each circle
        Paint paint1 = new Paint();
        Paint paint2 = new Paint();
        Paint paint3 = new Paint();

        // Set colors for each Paint object
        paint1.setColor(color1);
        paint2.setColor(color2);
        paint3.setColor(color3);

        // Set style to fill for each Paint object
        paint1.setStyle(Paint.Style.FILL);
        paint2.setStyle(Paint.Style.FILL);
        paint3.setStyle(Paint.Style.FILL);

        // Draw rectangles (representing circles) on the canvas with respective sizes and colors
        canvas.drawRect(200, 200, 800, 200+size1, paint1);
        canvas.drawRect(200, 200+size1, 800, 200+size1+size2, paint2);
        canvas.drawRect(200, 200+size1+size2, 800, 200+size1+size2+size3, paint3);

        // Restore the canvas to its original state
        //canvas.restore();
    }



    public void setCircleParams(int[] colors, int[] sizes) {
        this.colors = colors;
        this.sizes = sizes;
        invalidate(); // Trigger onDraw to redraw with new parameters
    }

// Uncomment vvv for TestActivity to work

//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//
//        if (colors == null || sizes == null || colors.length != sizes.length) {
//            return; // Exit if colors or sizes arrays are not properly initialized
//        }
//
//        Paint paint = new Paint();
//
//        canvas.save();
//        canvas.rotate(rotDegree);
//
//        int top = 200; // Initial top position for the first circle
//        for (int i = 0; i < colors.length; i++) {
//            paint.setColor(colors[i]);
//            paint.setStyle(Paint.Style.FILL);
//
//            int circleSize = sizes[i];
//            int left = (getWidth() - circleSize) / 2; // Center horizontally
//
//            canvas.drawCircle(left + circleSize / 2, top + circleSize / 2, circleSize / 2, paint);
//
//            // Adjust top position for the next circle
//            top += circleSize;
//        }
//
//        canvas.restore();
//    }



// - - - For Drawing Activity

//    private static final int NONE = 0;
//    private static final int TRIANGLE = 1;
//    private static final int CIRCLE = 2;
//    private static final int RECTANGLE = 3;
//
//    public boolean fillFlag = false;
//
//    private int figure;
//
//    public DrawView(Context context) {
//        super(context);
//    }
//
//    public DrawView(Context context, AttributeSet attributeSet) {
//        super(context, attributeSet);
//    }
//
//    public DrawView(Context context, AttributeSet attributeSet,
//                    int defStyleAttr) {
//        super(context, attributeSet, defStyleAttr);
//    }
//
//    public int getFigure() {
//        return figure;
//    }
//    public void setFigure(int figure) {
//        this.figure = figure;
//    }
//    public boolean getFillFlag() {
//        return fillFlag;
//    }
//
//    public void setFillFlag(boolean fillFlag) {
//        this.fillFlag = fillFlag;
//    }
//
//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        int width = getWidth();
//        int heigth = getHeight();
//        Paint paint;
//
//        switch (figure) {
//            case TRIANGLE: {
//                paint = new Paint();
//                paint.setColor(Color.GREEN);
//
//                if (fillFlag) { paint.setStyle(Paint.Style.FILL_AND_STROKE); }
//                else { paint.setStyle(Paint.Style.STROKE); }
//                paint.setStrokeWidth(10f);
//
//                Point point1_draw = new Point(width/2, 0);
//                Point point2_draw = new Point(0, heigth);
//                Point point3_draw = new Point(width, heigth);
//
//                Path path = new Path();
//                path.moveTo(point1_draw.x, point1_draw.y);
//                path.lineTo(point2_draw.x, point2_draw.y);
//                path.lineTo(point3_draw.x, point3_draw.y);
//                path.lineTo(point1_draw.x, point1_draw.y);
//                path.close();
//                canvas.drawPath(path, paint);
//                break;
//            }
//            case CIRCLE: {
//                paint = new Paint();
//                paint.setColor(Color.BLUE);
//
//                if (fillFlag) { paint.setStyle(Paint.Style.FILL_AND_STROKE); }
//                else { paint.setStyle(Paint.Style.STROKE); }
//                paint.setStrokeWidth(10f);
//
//                canvas.drawCircle(width/2, heigth/2, width/2, paint);
//                break;
//            }
//            case RECTANGLE: {
//                paint = new Paint();
//                paint.setColor(Color.BLACK);
//
//                if (fillFlag) { paint.setStyle(Paint.Style.FILL_AND_STROKE); }
//                else { paint.setStyle(Paint.Style.STROKE); }
//                paint.setStrokeWidth(10f);
//
//                canvas.drawRect(0, 0, width, heigth, paint);
//                break;
//            }
//            case NONE:{break;}
//            default: break;
//        }
//    }

}