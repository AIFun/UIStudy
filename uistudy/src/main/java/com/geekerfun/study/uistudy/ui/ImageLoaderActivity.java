package com.geekerfun.study.uistudy.ui;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.geekerfun.study.uistudy.R;
import com.geekerfun.study.uistudy.util.SildingLinearLayout;
import com.geekerfun.study.uistudy.util.SildingRelativeLayout;
import com.yancy.imageselector.ImageConfig;
import com.yancy.imageselector.ImageSelector;
import com.yancy.imageselector.ImageSelectorActivity;

import org.apache.poi.util.BitFieldFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.validation.Schema;

public class ImageLoaderActivity extends AppCompatActivity {
    public class GlideLoader implements com.yancy.imageselector.ImageLoader {

        @Override
        public void displayImage(Context context, String path, ImageView imageView) {
            Glide.with(context)
                    .load(path)
                    .placeholder(com.yancy.imageselector.R.mipmap.imageselector_photo)
                    .centerCrop()
                    .into(imageView);
        }

    }
    private static final int SELECT_PIC = 0;
    private static final int SELECT_PIC_KITKAT = 3;
    private static final int CAMERA_REQUEST_CODE = 1;
    private static final int RESULT_REQUEST_CODE = 2;
    private static  int state_select_pic = 0;

    int moveflg=0;
    float downx,downy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ImageConfig imageConfig
                = new ImageConfig.Builder(new GlideLoader())
                .steepToolBarColor(ContextCompat.getColor(this,R.color.blue))
                .titleBgColor(ContextCompat.getColor(this, R.color.blue))
                .titleSubmitTextColor(ContextCompat.getColor(this, R.color.white))
                .titleTextColor(ContextCompat.getColor(this, R.color.white))
                        // (截图默认配置：关闭    比例 1：1    输出分辨率  500*500)
                .crop(2048, 1080, 2048, 1080)
                        // 开启单选   （默认为多选）
                .singleSelect()
                        // 开启拍照功能 （默认关闭）
                .showCamera()
                        // 拍照后存放的图片路径（默认 /temp/picture） （会自动创建）
                .filePath("/ImageSelector/Pictures")
                .build();
        setContentView(R.layout.activity_image_loader);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        /*AssetManager assets = getAssets();
        InputStream imagefile=null;
        try
        {
            imagefile=assets.open("test1.jpg");

        }catch (IOException e)
        {
            e.printStackTrace();
        }
        BitmapDrawable bd=(BitmapDrawable) imageView.getDrawable();
        if(bd!=null && !bd.getBitmap().isRecycled())
        {
            bd.getBitmap().recycle();
        }
        imageView.setImageBitmap(BitmapFactory.decodeStream(imagefile));
        try
        {
            imagefile.close();
        }catch (IOException e){
            e.printStackTrace();
        }*/

        Glide.with(this)
                .load(Uri.parse("file:///android_asset/test1.jpg"))
                .into(imageView);
        SildingLinearLayout mSildingLinearLayout = (SildingLinearLayout) findViewById(R.id.imageLoadRoot);
        mSildingLinearLayout
                .setOnSildingFinishListener(new SildingLinearLayout.OnSildingFinishListener() {

                    @Override
                    public void onSildingFinish() {
                        ImageLoaderActivity.this.finish();
                    }
                });

        // touchView要设置到ListView上面
        mSildingLinearLayout.setTouchView(mSildingLinearLayout);
        imageView.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);//ACTION_OPEN_DOCUMENT
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/jpeg");
                state_select_pic = 1;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                    startActivityForResult(intent, SELECT_PIC_KITKAT);
                } else {
                    startActivityForResult(intent, SELECT_PIC);
                }*/
                if (moveflg==0) ImageSelector.open(ImageLoaderActivity.this, imageConfig);
            }
        });
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == event.ACTION_DOWN) {
                    downx = event.getX();
                    downy = event.getY();
                    moveflg=0;
                }
                if (event.getAction() == event.ACTION_UP) {
                    Log.d("ImagePathList",""+Math.sqrt(Math.pow(downx-event.getX(),2)+Math.pow(downy-event.getY(),2)));
                    if (Math.sqrt(Math.pow(downx-event.getX(),2)+Math.pow(downy-event.getY(),2))>100)
                        moveflg = 1;
                }
                return false;
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        /*Log.d("PHOTO", "in result " + requestCode + " " + resultCode);
        Uri uri = null;
        state_select_pic = 0;
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK)
            switch (requestCode) {
                case SELECT_PIC_KITKAT:// 当取到值的时候才上传path路径下的图片到服务器
                    Log.d("PHOTO", "in SELECT_PIC_KITKAT ");
                     uri = data.getData(); // 得到Uri
                    Log.d("PHOTO", "in SELECT_PIC_KITKAT "+ uri.toString());
                    ImageView imageView = (ImageView) findViewById(R.id.imageView);
                    //imageView.setImageURI(uri);
                    String selectedImagePath = getPath(uri);
                    Log.d("PHOTO", "in path "+ selectedImagePath);
                    BitmapDrawable bd=(BitmapDrawable) imageView.getDrawable();
                    if(bd!=null && !bd.getBitmap().isRecycled())
                    {
                        bd.getBitmap().recycle();
                    }
                    imageView.setImageBitmap(BitmapFactory
                            .decodeFile(selectedImagePath));

                    break;
                case SELECT_PIC:// 选择图片消息
                    Log.d("PHOTO", "in SELECT_PIC ");
                     uri = data.getData(); // 得到Uri
                    Log.d("PHOTO", "in SELECT_PIC "+ uri.toString());
                    break;
            }
            */

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ImageSelector.IMAGE_REQUEST_CODE && resultCode == RESULT_OK && data != null) {

            // Get Image Path List
            List<String> pathList = data.getStringArrayListExtra(ImageSelectorActivity.EXTRA_RESULT);
            ImageView imageView = (ImageView) findViewById(R.id.imageView);
            for (String path : pathList) {
                Log.i("ImagePathList", path);
                Glide.with(this)
                        .load(path)
                        .into(imageView);
            }
        }

        }

    /**
     * change uri to path string
     * @param uri
     * @return
     */

    public String getPath(Uri uri)
    {
        String[] projection = {MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.d("PHOTO", "in result " + keyCode + " " + state_select_pic);
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if (state_select_pic == 1 )  {
                state_select_pic = 0;
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
