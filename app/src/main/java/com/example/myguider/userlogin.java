package com.example.myguider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class userlogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView uregister=findViewById(R.id.uregister);
        Button guidebtn=findViewById(R.id.guidebtn);

        guidebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent guid=new Intent(userlogin.this,guidlogin.class);
                startActivity(guid);
            }
        });
        uregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent uregister=new Intent(userlogin.this, usernew.class);
                startActivity(uregister);
            }
        });
    }
}
