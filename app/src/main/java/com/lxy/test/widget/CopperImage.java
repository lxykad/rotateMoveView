package com.lxy.test.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.almeros.android.multitouch.RotateGestureDetector;


/**
 * @author lxy
 */
public class CopperImage extends AppCompatImageView {

    private float degree = 0f;

    protected int screenWidth;
    protected int screenHeight;
    protected int lastX;
    protected int lastY;
    private int oriLeft;
    private int oriRight;
    private int oriTop;
    private int oriBottom;
    private int dragDirection;
    private static final int TOP = 0x15;
    private static final int LEFT = 0x16;
    private static final int BOTTOM = 0x17;
    private static final int RIGHT = 0x18;
    private static final int LEFT_TOP = 0x11;
    private static final int RIGHT_TOP = 0x12;
    private static final int LEFT_BOTTOM = 0x13;
    private static final int RIGHT_BOTTOM = 0x14;
    private static final int CENTER = 0x19;
    private int offset = 20;
    protected Paint paint = new Paint();

    RotateGestureDetector rotateGestureDetector;

    public CopperImage(Context context) {
        this(context, null);
    }

    public CopperImage(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CopperImage(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {

        screenHeight = getResources().getDisplayMetrics().heightPixels - 40;
        screenWidth = getResources().getDisplayMetrics().widthPixels;

        setScaleType(ScaleType.MATRIX);

        paint.setColor(Color.RED);
        paint.setStrokeWidth(4.0f);
        paint.setStyle(Paint.Style.STROKE);

        rotateGestureDetector = new RotateGestureDetector(getContext(), mSimpleOnRotateGestureListener);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(offset, offset, getWidth() - offset, getHeight()
                - offset, paint);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        //
        animate()
                .rotation(degree)
                .setDuration(0)
                .start();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        rotateGestureDetector.onTouchEvent(event);

        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                oriLeft = getLeft();
                oriRight = getRight();
                oriTop = getTop();
                oriBottom = getBottom();
                lastY = (int) event.getRawY();
                lastX = (int) event.getRawX();
                dragDirection = getDirection(this, (int) event.getX(),
                        (int) event.getY());

                break;
            case MotionEvent.ACTION_POINTER_UP:
                break;
            case MotionEvent.ACTION_MOVE:

                break;
        }

        // 处理拖动事件
        delDrag(CopperImage.this, event, event.getAction());
        invalidate();

        return super.onTouchEvent(event);

        //  return true;
    }

    protected void delDrag(View v, MotionEvent event, int action) {
        switch (action) {
            case MotionEvent.ACTION_MOVE:
                int dx = (int) event.getRawX() - lastX;
                int dy = (int) event.getRawY() - lastY;
                switch (dragDirection) {
                    case LEFT: // 左边缘
                        left(v, dx);
                        System.out.println("11========left");
                        break;
                    case RIGHT: // 右边缘
                        right(v, dx);
                        break;
                    case BOTTOM: // 下边缘
                        bottom(v, dy);
                        System.out.println("11========bottom");
                        break;
                    case TOP: // 上边缘
                        top(v, dy);
                        break;
                    case CENTER: // 点击中心–>>移动
                        center(v, dx, dy);
                        break;
                    case LEFT_BOTTOM: // 左下
                        left(v, dx);
                        bottom(v, dy);
                        System.out.println("11========左下");
                        break;
                    case LEFT_TOP: // 左上
                        left(v, dx);
                        top(v, dy);
                        break;
                    case RIGHT_BOTTOM: // 右下
                        right(v, dx);
                        bottom(v, dy);
                        break;
                    case RIGHT_TOP: // 右上
                        right(v, dx);
                        top(v, dy);
                        break;
                }
                if (dragDirection != CENTER) {
                    v.layout(oriLeft, oriTop, oriRight, oriBottom);
                }
                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_UP:
                dragDirection = 0;
                break;
        }
    }

    private void center(View v, int dx, int dy) {
        int left = v.getLeft() + dx;
        int top = v.getTop() + dy;
        int right = v.getRight() + dx;
        int bottom = v.getBottom() + dy;
        if (left < -offset) {
            left = -offset;
            right = left + v.getWidth();
        }
        if (right > screenWidth + offset) {
            right = screenWidth + offset;
            left = right - v.getWidth();
        }
        if (top < -offset) {
            top = -offset;
            bottom = top + v.getHeight();
        }
        if (bottom > screenHeight + offset) {
            bottom = screenHeight + offset;
            top = bottom - v.getHeight();
        }
        v.layout(left, top, right, bottom);
    }

    private void top(View v, int dy) {
        oriTop += dy;
        if (oriTop < -offset) {
            oriTop = -offset;
        }
        if (oriBottom - oriTop - 2 * offset < 200) {
            oriTop = oriBottom - 2 * offset - 200;
        }
    }

    private void bottom(View v, int dy) {
        oriBottom += dy;
        if (oriBottom > screenHeight + offset) {
            oriBottom = screenHeight + offset;
        }
        if (oriBottom - oriTop - 2 * offset < 200) {
            oriBottom = 200 + oriTop + 2 * offset;
        }
    }

    private void right(View v, int dx) {
        oriRight += dx;
        if (oriRight > screenWidth + offset) {
            oriRight = screenWidth + offset;
        }
        if (oriRight - oriLeft - 2 * offset < 200) {
            oriRight = oriLeft + 2 * offset + 200;
        }
    }

    private void left(View v, int dx) {
        oriLeft += dx;
        if (oriLeft < -offset) {
            oriLeft = -offset;
        }
        if (oriRight - oriLeft - 2 * offset < 200) {
            oriLeft = oriRight - 2 * offset - 200;
        }
    }

    protected int getDirection(View v, int x, int y) {
        int left = v.getLeft();
        int right = v.getRight();
        int bottom = v.getBottom();
        int top = v.getTop();

        System.out.println("22=======x===" + x);
        System.out.println("22=======y===" + y);

        if (x < 40 && y < 40) {
            return LEFT_TOP;
        }
        if (y < 40 && right - left - x < 40) {
            return RIGHT_TOP;
        }
        if (x < 40 && bottom - top - y < 40) {
            return LEFT_BOTTOM;
        }
        if (right - left - x < 40 && bottom - top - y < 40) {
            return RIGHT_BOTTOM;
        }
        if (x < 40) {
            return LEFT;
        }
        if (y < 40) {
            return TOP;
        }
        if (right - left - x < 40) {
            return RIGHT;
        }
        if (bottom - top - y < 40) {
            return BOTTOM;
        }
        return CENTER;
    }


    public int getCutWidth() {
        return getWidth() - 2 * offset;
    }


    public int getCutHeight() {
        return getHeight() - 2 * offset;
    }

    private RotateGestureDetector.SimpleOnRotateGestureListener mSimpleOnRotateGestureListener = new RotateGestureDetector.SimpleOnRotateGestureListener() {

        @Override
        public boolean onRotate(RotateGestureDetector detector) {
//            mRotationDegrees = -detector.getRotationDegreesDelta()+mRotationDegrees;
//            mRotationDegrees = mRotationDegrees % 360;
//            mView.setRotation(mRotationDegrees);
//            Log.i(TAG, "onRotate: "+detector.getRotationDegreesDelta()+"----"+mRotationDegrees);

            System.out.println("rotate======" + detector.getRotationDegreesDelta());
            return true;
        }
    };

}
