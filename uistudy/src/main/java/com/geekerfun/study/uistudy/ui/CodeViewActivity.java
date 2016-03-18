package com.geekerfun.study.uistudy.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.geekerfun.study.uistudy.R;
import com.geekerfun.study.uistudy.util.SildingRelativeLayout;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by ebupt on 16/2/3.
 */
public class CodeViewActivity extends Activity {
    @Override
    public  void  onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.d("codeview", this.toString());
        XmlPullParser parser = this.getResources().getXml(R.layout.sildingrelativelayout);
        AttributeSet attributes = Xml.asAttributeSet(parser);
        int type;
        try{
            while ((type = parser.next()) != XmlPullParser.START_TAG &&
                    type != XmlPullParser.END_DOCUMENT) {
                // Empty
            }

            if (type != XmlPullParser.START_TAG) {
                Log.e("", "the xml file is error!\n");
            }
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Log.d("", "" + parser.getAttributeCount());
        SildingRelativeLayout sfLayout=new SildingRelativeLayout(this,attributes);

        super.setContentView(sfLayout);
        final TextView show = new TextView(this);
        Button bn=new Button(this);
        bn.setText(R.string.ok);
        final SildingRelativeLayout.LayoutParams bnlp=new SildingRelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        bnlp.setMargins(0, 26, 0, 0);
        bn.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        sfLayout.addView(show);
        sfLayout.addView(bn);
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show.setText("hello , Android , " + new java.util.Date());
                Log.d("codeview", this.toString());
                v.setLayoutParams(bnlp);
            }
        });
        sfLayout.setOnSildingFinishListener(new SildingRelativeLayout.OnSildingFinishListener() {

            @Override
            public void onSildingFinish() {
                CodeViewActivity.this.finish();
            }
        });

        // touchView要设置到ListView上面
        sfLayout.setTouchView(sfLayout);
    }
}
