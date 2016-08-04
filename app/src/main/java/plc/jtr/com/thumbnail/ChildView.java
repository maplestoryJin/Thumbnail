package plc.jtr.com.thumbnail;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Shader;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;
import android.widget.TextView;

import java.util.concurrent.Semaphore;

/**
 * Created by chenweiqiang on 2016/7/22.
 */
public class ChildView extends TextView {
    private Scroller mScroller;
    private int mViewWidth;
    private TextPaint mPaint;
    private LinearGradient mLinearGradient;
    private Matrix mGraientMatrix;
    private int mTranslate;
    private int lastX;
    private int lastY;
    private Semaphore semaphore = new Semaphore(1);

    public ChildView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        mScroller = new Scroller(context);
    }

    public ChildView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }

    private int measureHeight(int heightMeasureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(heightMeasureSpec);
        int specSize = MeasureSpec.getSize(heightMeasureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = 200;
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }

        return result;
    }



    private int measureWidth(int widthMeasureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = 200;
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }

        return result;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mGraientMatrix != null) {
            mTranslate += mViewWidth / 5;
            if (mTranslate > 2 * mViewWidth) {
                mTranslate = -mViewWidth;
            }
            mGraientMatrix.setTranslate(mTranslate, 0);
            mLinearGradient.setLocalMatrix(mGraientMatrix);
            postInvalidateDelayed(100);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (mViewWidth == 0) {
            mViewWidth = getMeasuredWidth();
            if (mViewWidth > 0) {
                mPaint = getPaint();
                mLinearGradient = new LinearGradient(0 ,0, mViewWidth, 0, new int[]{Color.BLUE, Color.WHITE,Color.BLUE}, null, Shader.TileMode.CLAMP);
                mPaint.setShader(mLinearGradient);
                mGraientMatrix = new Matrix();
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = x - lastX;
                int offsetY = y - lastY;
                // 第一种方式
//                layout(getLeft() + offsetX, getTop() + offsetY, getRight() + offsetX, getBottom() + offsetY);
                // 第二种方式
//                offsetLeftAndRight(offsetX);
//                offsetTopAndBottom(offsetY);
                // 第三种方式
//                ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
//                layoutParams.topMargin = getTop() + offsetY;
//                layoutParams.leftMargin = getLeft() + offsetX;
//                setLayoutParams(layoutParams);
                // 第四种方式
//                ((View)getParent()).scrollBy(-offsetX, -offsetY);
                break;
            case MotionEvent.ACTION_UP:
                // 第五种方式
//                View viewGroup = (View) getParent();
//                mScroller.startScroll(viewGroup.getScrollX(),
//                        viewGroup.getScrollY(),
//                        -viewGroup.getScrollX(),
//                        -viewGroup.getScrollY());
                break;
        }
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            ((View)getParent()).scrollTo(mScroller.getCurrX(),
                    mScroller.getCurrY());
        }
    }
}
