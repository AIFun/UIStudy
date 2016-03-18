package com.geekerfun.study.uistudy.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.geekerfun.study.uistudy.R;
import com.geekerfun.study.uistudy.util.SildingLinearLayout;

public class ImageViewerActivity extends AppCompatActivity {
    int[] images= new int[]{
            R.drawable.default_banner,
            R.drawable.default_wizard_banner,
            R.drawable.splash,
            R.drawable.weather_bg_d
    };
    int currentImg = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_viewer);
        SildingLinearLayout mLayout = (SildingLinearLayout) findViewById(R.id.imageViewRoot);
        mLayout
                .setOnSildingFinishListener(new SildingLinearLayout.OnSildingFinishListener() {

                    @Override
                    public void onSildingFinish() {
                        ImageViewerActivity.this.finish();
                    }
                });

        // touchView要设置到ListView上面
        mLayout.setTouchView(mLayout);
        final ImageView image=new ImageView(this);
        mLayout.addView(image);
        image.setImageResource(images[currentImg]);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image.setImageResource(images[++currentImg%images.length]);

            }
        });

    }
}
