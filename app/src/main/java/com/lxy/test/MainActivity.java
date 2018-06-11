package com.lxy.test;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.almeros.android.multitouch.MoveGestureDetector;
import com.almeros.android.multitouch.RotateGestureDetector;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.Utils;
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
        int screenHeight = ScreenUtils.getScreenHeight() * 2;
        int screenWidth = ScreenUtils.getScreenWidth();

        int rest = screenHeight - screenWidth;

        LinearLayout layout = findViewById(R.id.view_layout);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.width = screenHeight;
        layoutParams.height = screenHeight;


        LinearLayout.LayoutParams zoomParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        zoomParams.width = screenHeight;
        layoutParams.height = screenHeight;

        layoutParams.leftMargin = -rest / 4;
        zoomParams.leftMargin = -rest / 4;

        layout.setLayoutParams(layoutParams);
        zoomView.setLayoutParams(zoomParams);

        zoomView.animate()
                .rotation(0)
                .scaleY(1f)
                .scaleX(1f)
                .setDuration(0)
                .start();

        zoomView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                System.out.println("top====l==="+zoomView.getLeft());
                System.out.println("top====r==="+zoomView.getRight());
                System.out.println("top====w==="+zoomView.getWidth());
                System.out.println("top====h==="+zoomView.getHeight());
                System.out.println("top====sw==="+ScreenUtils.getScreenWidth());



                zoomView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // rotateGestureDetector.onTouchEvent(event);
        //  moveGestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    public void btClick(View view) {
        Toast.makeText(view.getContext(), "bt", Toast.LENGTH_SHORT).show();
    }
}
