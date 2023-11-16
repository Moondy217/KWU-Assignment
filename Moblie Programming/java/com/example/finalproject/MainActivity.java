package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private BroadcastReceiver BroadCastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BroadCastReceiver = new BroadCast();


        //임의로 로그인을 성공할 수 있는 아이디와 숫자를 지정
        String personalId, personapass;
        personalId = "a";
        personapass = "b";


        Button login = (Button)findViewById(R.id.logout);
        EditText ID = (EditText) findViewById(R.id.ID);
        EditText Password = (EditText) findViewById(R.id.Password);


        //로그인 버튼 클릭 시 이벤트 처리
        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //사용자가 입력한 id와 password를 저장
                String id, password;
                id = ID.getText().toString();
                password = Password.getText().toString();

                //id, password가 입력되지 않았을 경우 (예외처리)
                if(id.equals("") || password.equals("")) {
                    Toast.makeText(getApplicationContext(),
                            "아이디 또는 비밀번호를 입력해 주세요.", Toast.LENGTH_LONG).show();
                }

                //id, password가 맞은 경우 다음 액티비티 화면으로 넘어간다
                if(id.equals(personalId) && password.equals(personapass)) {
                    Intent intent = new Intent(MainActivity.this,
                            Activity2.class);
                    intent.putExtra("user", personalId);
                    startActivity(intent);

                    Toast.makeText(getApplicationContext(),
                            "로그인 성공", Toast.LENGTH_LONG).show();
                }

                //id, password가 맞지 않은 경우 잘못 입력되었음을 출력 (예외처리)
                else {
                        Toast.makeText(getApplicationContext(),
                                "아이디 또는 비밀번호가 잘못되었습니다.", Toast.LENGTH_LONG).show();
                }




            }
        });
    }

    //전원 연결, 해제 시 발생하는 브로드캐스트
    @Override
    protected void onResume()
    {
        super.onResume();
        //BroadCast에 전송
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_POWER_CONNECTED);
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        registerReceiver(BroadCastReceiver, filter);
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        unregisterReceiver(BroadCastReceiver);
    }

}