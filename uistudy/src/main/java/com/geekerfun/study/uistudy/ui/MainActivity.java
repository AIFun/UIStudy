package com.geekerfun.study.uistudy.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.geekerfun.study.uistudy.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button exitBtn= (Button) findViewById(R.id.button_exit);
        exitBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
        Button emptyBtn= (Button) findViewById(R.id.button_empty);
        emptyBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public  void onClick(View v)
            {
                Intent emptyIntent=new Intent(MainActivity.this,EmptyActivity.class);
                //Activity emptyActivity=new EmptyActivity();
                MainActivity.this.startActivity(emptyIntent);
            }
        });
        Button codeViewBtn= (Button) findViewById(R.id.button_codeView);
        codeViewBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public  void onClick(View v)
            {
                Intent codeViewIntent=new Intent(MainActivity.this,CodeViewActivity.class);
                //Activity emptyActivity=new EmptyActivity();
                MainActivity.this.startActivity(codeViewIntent);
            }
        });
        Button imageViewBtn= (Button) findViewById(R.id.button_imageView);
        imageViewBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public  void onClick(View v)
            {
                Intent imageViewIntent=new Intent(MainActivity.this,ImageViewerActivity.class);
                //Activity emptyActivity=new EmptyActivity();
                MainActivity.this.startActivity(imageViewIntent);
            }
        });
        Button customViewBtn= (Button) findViewById(R.id.button_customView);
        customViewBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public  void onClick(View v)
            {
                Intent customViewIntent=new Intent(MainActivity.this,CustomViewActivity.class);
                //Activity emptyActivity=new EmptyActivity();
                MainActivity.this.startActivity(customViewIntent);
            }
        });

        findViewById(R.id.button_imageLoad).setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent imageLoadIntent = new Intent(MainActivity.this, ImageLoaderActivity.class);
                //Activity emptyActivity=new EmptyActivity();
                MainActivity.this.startActivity(imageLoadIntent);
            }
        });
    }

}
