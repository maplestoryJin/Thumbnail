package plc.jtr.com.thumbnail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class Main4Activity extends AppCompatActivity {

    private LinearLayout mHiddenView;
    private float mDensity;
    private int mHiddenViewMeasuredHeight;
    private int height;
    private TextView textView;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private Spinner mSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main4);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mSpinner = (Spinner) findViewById(R.id.spinner);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    recyclerView.setLayoutManager(new LinearLayoutManager(Main4Activity.this));
                } else if (position == 1) {

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//        View viewById = findViewById(R.id.image_tint);
//
//        ViewOutlineProvider viewOutlineProvider = new ViewOutlineProvider() {
//            @Override
//            public void getOutline(View view, Outline outline) {
//                outline.setRoundRect(0, 0,
//                        view.getWidth(),
//                        view.getHeight(), 30);
//            }
//        };

//        viewById.setOutlineProvider(viewOutlineProvider);

//        Palette.generateAsync(bitmap, new Palette.PaletteAsyncListener() {
//            @Override
//            public void onGenerated(Palette palette) {
//                // 获取对应色调
//                Palette.Swatch vibrant =
//                        palette.getMutedSwatch();
//                // 将颜色设置给相应的组件
//                getActionBar().setBackgroundDrawable(new ColorDrawable(vibrant.getRgb()));
//                Window window = getWindow();
//                window.setStatusBarColor(vibrant.getRgb());
//            }
//        });
//        mHiddenView = (LinearLayout) findViewById(R.id.hidden_view);
//        textView = (TextView) findViewById(R.id.tv_hidden);
//        long startTime = SystemClock.currentThreadTimeMillis();
//        Log.d("jtr", reserve("hello"));
//        Log.d("jtr", SystemClock.currentThreadTimeMillis() - startTime + "ms");
//        mHiddenView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                height = mHiddenView.getHeight();
//                mHiddenView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
//            }
//        });
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Main4Activity.this, Main2Activity.class);
//                startActivity(intent);
//            }
//        });
//    }
//
//
//    public void llClick(View v) {
//        if (mHiddenView.getVisibility() == View.INVISIBLE) {
//            animateOpen(mHiddenView);
//        } else {
//            animateClose(mHiddenView);
//        }
//    }
//
//    private void animateClose(final View mHiddenView) {
//        ValueAnimator valueAnimator = createDropAnimator(mHiddenView, height, 0);
//        valueAnimator.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                mHiddenView.setVisibility(View.GONE);
//            }
//        });
//        valueAnimator.start();
//    }
//
//    private void animateOpen(final View mHiddenView) {
//        mHiddenView.setVisibility(View.VISIBLE);
//        ValueAnimator valueAnimator = createDropAnimator(mHiddenView, 0, height);
//        valueAnimator.start();
//    }
//
//    private ValueAnimator createDropAnimator(final View mHiddenView, int start, int end) {
//        ValueAnimator valueAnimator = ValueAnimator.ofInt(start, end);
//        valueAnimator.setInterpolator(new DecelerateInterpolator());
//        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                int value = (int) animation.getAnimatedValue();
//                ViewGroup.LayoutParams layoutParams = mHiddenView.getLayoutParams();
//                layoutParams.height = value;
//                mHiddenView.setLayoutParams(layoutParams);
//            }
//        });
//        return valueAnimator;
//    }
//
//    public String reserve(String s) {
////        String newStr = "";
////        for (int i = s.length() - 1; i >= 0; i--) {
////            newStr += s.charAt(i);
////        }
//        char[] chars = s.toCharArray();
//        for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
//            char temp = chars[j];
//            chars[j] = chars[i];
//            chars[i] = temp;
//        }
//        return new String(chars);
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        Debug.stopMethodTracing();
    }
}

