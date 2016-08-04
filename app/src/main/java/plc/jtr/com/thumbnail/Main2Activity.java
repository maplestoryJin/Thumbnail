package plc.jtr.com.thumbnail;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    private View viewById;
    private View viewById1;
    private View viewById2;
    private View viewById3;
    private View viewById4;
    private boolean mFlag = true;
    private View click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        click = findViewById(R.id.click_1);
        viewById = findViewById(R.id.image_ic1);
        viewById1 = findViewById(R.id.image_ic2);
        viewById2 = findViewById(R.id.image_ic3);
        viewById3 = findViewById(R.id.image_ic4);
        viewById4 = findViewById(R.id.image_ic5);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvTimer(v);
            }
        });
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFlag) {
                    startAnim();
                } else {
                    closeAnim();
                }
            }
        });
//        TextView textView = (TextView) findViewById(R.id.text);
//        if (textView != null) {
//            textView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(Settings.ACTION_SETTINGS);
//                    startActivity(intent);
//                }
//            });
//        }
//
//        try {
//            int[] a = new int[-1];
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        Log.d("jtr", "xxxxxxxxxxxxxxxxxxx");
    }

    private void tvTimer(final View v) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 100);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                ((TextView)v).setText(String.format("$%s", animation.getAnimatedValue()));
            }
        });
        valueAnimator.setDuration(3000);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.start();
    }

    private void closeAnim() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(viewById, "alpha", 0.5F, 1F);
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(viewById1, "translationY", 0F);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(viewById2, "translationX", 0F);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(viewById3, "translationY", 0F);
        ObjectAnimator animator4 = ObjectAnimator.ofFloat(viewById4, "translationX", 0F);
        AnimatorSet animationSet = new AnimatorSet();
        animationSet.setDuration(500);
        animationSet.setInterpolator(new BounceInterpolator());
        animationSet.playTogether(animator, animator1, animator2, animator3, animator4);
        animationSet.start();
        mFlag = true;

    }

    private void startAnim() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(viewById, "alpha", 1F, 0.5F);
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(viewById1, "translationY", 200F);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(viewById2, "translationX", 200F);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(viewById3, "translationY", -200F);
        ObjectAnimator animator4 = ObjectAnimator.ofFloat(viewById4, "translationX", -200F);
        AnimatorSet animationSet = new AnimatorSet();
        animationSet.setDuration(500);
        animationSet.setInterpolator(new BounceInterpolator());
        animationSet.playTogether(animator, animator1, animator2, animator3, animator4);
        animationSet.start();
        mFlag = false;

    }
}
