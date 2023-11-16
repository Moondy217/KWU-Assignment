package com.example.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;

public class Activity3 extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout3);

        setTitle("영화 정보 수정 페이지입니다.");

        Button ok = (Button) findViewById(R.id.ok);
        EditText memo = (EditText) findViewById(R.id.memo); //영화 메모 및 한줄평
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Activity3.this, Activity2.class);
                intent.putExtra("usermemo", memo.getText().toString());

                setResult(RESULT_OK, intent);
                finish();

            }
        });

        Button select = (Button) findViewById(R.id.select);


        //갤러리에 있는 이미지 선택 버튼
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
            }
        }); //select 버튼 이벤트 종료


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    imageView.setImageURI(uri);
                }
                break;

        }
    }
}