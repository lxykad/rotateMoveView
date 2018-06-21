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

import com.bumptech.glide.Glide;
import com.google.android.flexbox.FlexboxLayout;
import com.lxy.test.R;
import com.lxy.test.entity.FlexBean;

/**
 * @author lxy
 */
public class FlexActivity extends AppCompatActivity {

    private FlexboxLayout flexboxLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flex);

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

            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            params.height = 240;
            params.width = 240;


            imageView.setLayoutParams(params);

            Glide.with(this)
                    .load(bean.imgUrl)
                    .into(imageView);

            flexboxLayout.addView(imageView);

        }
    }
}
