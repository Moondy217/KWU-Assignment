package com.example.finalproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NonNls;
import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class Activity2 extends AppCompatActivity {
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout2);

        Intent intent = getIntent();
        String User = intent.getStringExtra("user");

        //(로그인 한) id(유저) 님의 영화 페이지입니다. 출력
        setTitle(User + " 님의 영화 페이지입니다.");


        list = (ListView) findViewById(R.id.listView);
        final ArrayList<String> data = new ArrayList<String>();
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_single_choice, data);
        list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        list.setAdapter(adapter);

        Button add = (Button) findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog Dialog = new Dialog(Activity2.this);
                Dialog.setContentView(R.layout.dialog);
                Dialog.setTitle("제목 입력");

                EditText dialogtitle = (EditText) Dialog.findViewById(R.id.dialogtitle);
                Button dialogok = (Button) Dialog.findViewById(R.id.dialogok);

                dialogok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String title;
                        title = dialogtitle.getText().toString();
                        data.add(title);
                        adapter.notifyDataSetChanged();
                        Dialog.dismiss();
                    }
                });
                Dialog.show();
            }
        }); //add 버튼 종료

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(getApplicationContext(),
                        data.get(position), Toast.LENGTH_LONG).show();

                Button modify = (Button) findViewById(R.id.modify);
                modify.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        int count, checked;
                        count = adapter.getCount();

                        if (count > 0) {
                            // 현재 선택된 아이템의 position 획득.
                            checked = list.getCheckedItemPosition();
                            if (checked > -1 && checked < count) {
                                // 아이템 수정
                                Intent intent = new Intent(Activity2.this, Activity3.class);
                                startActivityForResult(intent, 1);

                            }
                        } //if문 종료
                    }
                }); //modify 종료


                Button delete = (Button) findViewById(R.id.delete);
                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int count, checked ;
                        count = adapter.getCount() ;

                        if (count > 0) {
                            // 현재 선택된 아이템의 position 획득.
                            checked = list.getCheckedItemPosition();
                            if (checked > -1 && checked < count) {
                                // 아이템 삭제
                                data.remove(checked) ;

                                // listview 선택 초기화.
                                list.clearChoices();
                                adapter.notifyDataSetChanged();
                            }
                        }
                    }
                }); //아이템 삭제 버튼 종료


               Button btnview = (Button) findViewById(R.id.view);
                btnview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = getIntent();
                        String memo = intent.getStringExtra("usermemo");

                        TextView textView = (TextView) findViewById(R.id.textView3);

                        textView.setText(memo);
                        final Dialog Dialog = new Dialog(Activity2.this);
                        Dialog.setContentView(R.layout.movieveiw);

                        Dialog.setTitle("제목 입력");
                        Dialog.show();
                    }
                });

            }
        }); //리스트 아이템 클릭 시 아이템 출력 + 수정 + 삭제 버튼 처리
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode != Activity.RESULT_OK) {
                return;
            }

        }
    }

    //옵션 메뉴에 로그아웃 클릭 시 로그아웃되고 로그인 화면으로
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}




