package com.geekerfun.study.uistudy.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.geekerfun.study.uistudy.R;
import com.geekerfun.study.uistudy.util.DrawView;
import com.geekerfun.study.uistudy.util.SildingLinearLayout;

public class CustomViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);

        SildingLinearLayout mLayout = (SildingLinearLayout) findViewById(R.id.customview);
        mLayout
                .setOnSildingFinishListener(new SildingLinearLayout.OnSildingFinishListener() {

                    @Override
                    public void onSildingFinish() {
                        CustomViewActivity.this.finish();
                    }
                });
        DrawView drawview = (DrawView) findViewById(R.id.drawview);
        // touchView要设置到ListView上面
        mLayout.setTouchView(drawview);
    }
}
