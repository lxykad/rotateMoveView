package com.lxy.test.ui;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.Utils;
import com.bumptech.glide.Glide;
import com.google.android.flexbox.FlexboxLayout;
import com.lxy.test.R;
import com.lxy.test.entity.FlexBean;

/**
 * @author lxy
 */
public class FlexActivity extends AppCompatActivity {

    private FlexboxLayout flexboxLayout;
    private int screenWidth;
    private static final int COUNT = 4; // 每行图片的个数
    private int imgWidth;// 每个图片的宽度
    private int imgHeight;
    private int distance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flex);

        screenWidth = ScreenUtils.getScreenWidth();
        int totalWidth = screenWidth - ConvertUtils.dp2px((COUNT + 1) * 10);
        imgWidth = (int) (totalWidth * 1f / COUNT);
        imgHeight = imgWidth;

        distance = ConvertUtils.dp2px(10);

        flexboxLayout = findViewById(R.id.flex_layout);
        FlexBean bean = new FlexBean();


        for (int i = 0; i < 10; i++) {
            Button view = new Button(this);
            view.setTag(i);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int tag = (int) view.getTag();
                    Toast.makeText(view.getContext(), tag + "", Toast.LENGTH_SHORT).show();
                }
            });
            ImageView imageView = (ImageView) LayoutInflater.from(this)
                    .inflate(R.layout.item_img_layout, null);

            FlexboxLayout.LayoutParams params = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            params.height = imgHeight;
            params.width = imgWidth;
            params.leftMargin = distance;
            params.topMargin = distance;


            imageView.setLayoutParams(params);

            Glide.with(this)
                    .load(bean.imgUrl)
                    .into(imageView);

            final int finalI = i;
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Toast.makeText(view.getContext(), finalI + "", Toast.LENGTH_SHORT).show();
                }
            });

            flexboxLayout.addView(imageView);

        }
    }
}
