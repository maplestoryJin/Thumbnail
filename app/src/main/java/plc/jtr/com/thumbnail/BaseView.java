package plc.jtr.com.thumbnail;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by chenweiqiang on 2016/7/22.
 */
public abstract class BaseView extends RelativeLayout {
    public BaseView(Context context) {
        super(context);
//        initView(context);
    }

    public BaseView(Context context, AttributeSet attrs) {
        super(context, attrs);
//        initView(context);
    }

    public abstract void initView(Context context);
}
