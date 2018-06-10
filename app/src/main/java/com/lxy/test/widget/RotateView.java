package com.lxy.test.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.almeros.android.multitouch.RotateGestureDetector;

/**
 * @author lxy
 */
public class RotateView extends View {
    private float mRotationDegrees = 0f;

    private RotateGestureDetector rotateGestureDetector;
    private RotateGestureDetector.SimpleOnRotateGestureListener simpleOnRotateGestureListener = new RotateGestureDetector.SimpleOnRotateGestureListener() {
        @Override
        public boolean onRotate(RotateGestureDetector detector) {
            Toast.makeText(getContext(), "rot", Toast.LENGTH_LONG).show();
            System.out.println("rot======" + detector.getRotationDegreesDelta());
            mRotationDegrees = -detector.getRotationDegreesDelta() + mRotationDegrees;
            mRotationDegrees = mRotationDegrees % 360;
            animate()
                    .rotation(mRotationDegrees)
                    .setDuration(0)
                    .start();

            return true;
        }
    };

    public RotateView(Context context) {
        this(context, null);
    }

    public RotateView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RotateView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        rotateGestureDetector = new RotateGestureDetector(getContext(), simpleOnRotateGestureListener);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.parseColor("#30ff0000"));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        rotateGestureDetector.onTouchEvent(event);
        System.out.println("touch=======");
        return super.onTouchEvent(event);
    }


}
