package plc.jtr.com.thumbnail;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by chenweiqiang on 2016/7/28.
 */
public class CircleView extends View {
    private int mCircleXY;
    private int length;
    private float mRadius;
    private RectF mArcRectF;
    private Paint mCiclePaint;
    private Paint mArcPaint;
    private float mSweepAngle = 300;
    private String mShowText = "Hello World";
    private Paint mTextPaint;
    private float mShowTextSize;
    private int startAngle;

    public CircleView(Context context) {
        super(context);
        init();
    }


    private void init() {
        length = getResources().getDisplayMetrics().widthPixels;
        mTextPaint = new Paint();
        mTextPaint.setColor(Color.WHITE);
        mShowTextSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics());
        mTextPaint.setTextSize(mShowTextSize);
        mCiclePaint = new Paint();
        mCiclePaint.setColor(Color.RED);
        mArcPaint = new Paint();
        mArcPaint.setColor(Color.GREEN);
        mArcPaint.setStyle(Paint.Style.STROKE);
        mArcPaint.setStrokeWidth(40);
        mCircleXY = length / 2;
        mRadius = (float) (length * 0.5 / 2);
        mArcRectF = new RectF(length * 0.1f, length * 0.1f, length * 0.9f, length * 0.9f);
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (startAngle < 360) {
            startAngle += 20;
        } else {
            startAngle = 0;
        }
        canvas.drawCircle(mCircleXY, mCircleXY, mRadius, mCiclePaint);
        canvas.drawArc(mArcRectF, startAngle, mSweepAngle, false, mArcPaint);
        canvas.drawText(mShowText, 0, mShowText.length(), mCircleXY - mTextPaint.measureText(mShowText) / 2, mCircleXY + (mShowTextSize / 4), mTextPaint);
        postInvalidateDelayed(50);
    }

}
