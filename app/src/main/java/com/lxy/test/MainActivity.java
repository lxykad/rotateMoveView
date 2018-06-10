package com.lxy.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

import com.almeros.android.multitouch.MoveGestureDetector;
import com.almeros.android.multitouch.RotateGestureDetector;
import com.lxy.test.widget.CustomZoomView;
import com.lxy.test.widget.RotateView;

/**
 * @author lxy
 */
public class MainActivity extends AppCompatActivity {

    private boolean canMove;
    private boolean canRotate;
    private CustomZoomView zoomView;

    private RotateGestureDetector rotateGestureDetector;
    private RotateGestureDetector.SimpleOnRotateGestureListener simpleOnRotateGestureListener = new RotateGestureDetector.SimpleOnRotateGestureListener() {
        @Override
        public boolean onRotate(RotateGestureDetector detector) {
            canRotate = true;
            canMove = false;

            mRotationDegrees = -detector.getRotationDegreesDelta() + mRotationDegrees;
            mRotationDegrees = mRotationDegrees % 360;
            zoomView.setRotation(mRotationDegrees);

            return true;
        }

        @Override
        public void onRotateEnd(RotateGestureDetector detector) {
            canRotate = false;
            canMove = true;
            super.onRotateEnd(detector);
        }
    };
    private RotateView rotateView;
    private float mRotationDegrees = 0f;

    // move
    private float mOffsetX = 0;
    private float mOffsetY = 0;
    private float mLastX = 0;
    private float mLastY = 0;

    private MoveGestureDetector moveGestureDetector;
    private MoveGestureDetector.SimpleOnMoveGestureListener moveGestureListener = new MoveGestureDetector.SimpleOnMoveGestureListener() {
        @Override
        public boolean onMove(MoveGestureDetector detector) {
            canMove = true;
            canRotate = false;
            mOffsetX = detector.getFocusDelta().x + mLastX;
            mOffsetY = detector.getFocusDelta().y + mLastY;
            zoomView.setTranslationX(mOffsetX);
            zoomView.setTranslationY(mOffsetY);

            System.out.println("move====" + mOffsetX);
            return super.onMove(detector);
        }

        @Override
        public void onMoveEnd(MoveGestureDetector detector) {
            canMove = false;
            canRotate = true;
            mLastX = mOffsetX;
            mLastY = mOffsetY;
            super.onMoveEnd(detector);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        zoomView = findViewById(R.id.zoom_view);
        // CopperImage image = findViewById(R.id.copper);
        rotateView = findViewById(R.id.rotateView);
        rotateGestureDetector = new RotateGestureDetector(this, simpleOnRotateGestureListener);

        moveGestureDetector = new MoveGestureDetector(this, moveGestureListener);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
       // rotateGestureDetector.onTouchEvent(event);
      //  moveGestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
}
