package com.geekerfun.study.uistudy.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.geekerfun.study.uistudy.R;
import com.geekerfun.study.uistudy.util.SildingRelativeLayout;

public class EmptyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);
        SildingRelativeLayout mSildingRelativeLayout = (SildingRelativeLayout) findViewById(R.id.sildingFinishLayout);
        mSildingRelativeLayout
                .setOnSildingFinishListener(new SildingRelativeLayout.OnSildingFinishListener() {

                    @Override
                    public void onSildingFinish() {
                        EmptyActivity.this.finish();
                    }
                });

        // touchView要设置到ListView上面
        mSildingRelativeLayout.setTouchView(mSildingRelativeLayout);
    }
}
