package plc.jtr.com.thumbnail;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class GridViewItemLayout extends RelativeLayout {

	public GridViewItemLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		setMeasuredDimension(getDefaultSize(0, widthMeasureSpec),
				getDefaultSize(0, widthMeasureSpec));

		// Children are just made to fill our space.
		int childWidthSize = getMeasuredWidth();
		// 高度和宽度一样
		heightMeasureSpec = widthMeasureSpec = MeasureSpec.makeMeasureSpec(
				childWidthSize, MeasureSpec.EXACTLY);
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
}