package com.example.myguider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class newguid extends AppCompatActivity {

    private EditText userName,Email,Password,MobileNo,Country;
    private FirebaseAuth myAuth;
    public static String usern,email,password,country,mobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myAuth= FirebaseAuth.getInstance();
        setContentView(R.layout.activity_newguid);
        Button gback1=findViewById(R.id.gback1);
        Button next=findViewById(R.id.gnext);
        userName=findViewById(R.id.ngname);
        Email=findViewById(R.id.ngemail);
        Password=findViewById(R.id.ngpassword);
        MobileNo=findViewById(R.id.ngmobile);
        Country=findViewById(R.id.ngcountry);

        if (myAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),guide_inter.class));
            finish();
        }

        gback1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goback=new Intent(newguid.this,guidlogin.class);
                startActivity(goback);
            }
        });

        next.setOnClickListener(v -> {
             usern=userName.getText().toString().trim();
             email=Email.getText().toString().trim();
             password=Password.getText().toString().trim();
             country=Country.getText().toString().trim();
             mobile=MobileNo.getText().toString().trim();

            if (TextUtils.isEmpty(usern)){
                userName.setError("user is required");
                return;
            }
            else if (TextUtils.isEmpty(email)){
                Email.setError("email is required");
                return;
            }
            else if (TextUtils.isEmpty(password)){
                Password.setError("password is required");
                return;
            }
            else if (TextUtils.isEmpty(country)){
                Country.setError("country is required");
                return;
            }
           else if (TextUtils.isEmpty(usern)){
                userName.setError("user is required");
                return;
            }
           else if (TextUtils.isEmpty(mobile)){
                MobileNo.setError("MNo is required");
                return;
            }
           else if (password.length()<6){
                Password.setError("password too short");
                return;
            }else {
                Intent gonext = new Intent(newguid.this, guidpropic.class);
                startActivity(gonext);
            }
        });

    }
}
