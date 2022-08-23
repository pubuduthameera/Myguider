package com.example.myguider;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.core.Tag;

import static androidx.constraintlayout.widget.StateSet.TAG;
import static com.example.myguider.newguid.email;
import static com.example.myguider.newguid.password;

public class guidlogin extends AppCompatActivity {
private FirebaseAuth myauth;
EditText email,password;
CheckBox cb;
String Email,pass;
public static String name;
public static Uri photo;
    private FirebaseAuth.AuthStateListener myauthlistner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guidlogin);
        Button userbtn=findViewById(R.id.userbtn);

        cb=findViewById(R.id.gcheckbox);
        TextView gregister= findViewById(R.id.gregister);
        email=findViewById(R.id.gemail);
        password=findViewById(R.id.gpassword);

        myauth=FirebaseAuth.getInstance();
//        FirebaseUser user=myauth.getCurrentUser();
        userbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent user=new Intent(guidlogin.this,userlogin.class);
                startActivity(user);
            }
        });
        gregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newguid=new Intent(guidlogin.this, newguid.class);
                startActivity(newguid);
            }
        });

        setupfirbaseauth();
        initi();

    }
    public void initi() {
        Button signin=findViewById(R.id.gsignin);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"attemting to log");

                Email = email.getText().toString();
                pass = password.getText().toString();

                myauth.signInWithEmailAndPassword(Email, pass)
                        .addOnCompleteListener(guidlogin.this ,new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Log.d(TAG,"Succsesfully in"+task.isSuccessful());
                                startActivity(new Intent(getApplicationContext(),guide_inter.class));
                                if (!task.isSuccessful()){
                                   Log.w(TAG,"fsiled",task.getException());

                                }
                            }
                       });
            }
        });
    }

    private void setupfirbaseauth() {
        Log.d(TAG,"setup firebaseauth");
        myauthlistner=new FirebaseAuth.AuthStateListener(){

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user=firebaseAuth.getCurrentUser();

                if (user != null){
                    Log.d(TAG,"signed in"+user.getUid());
                }else {
                    Log.d(TAG,"signed out");
                }
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        myauth.addAuthStateListener(myauthlistner);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (myauthlistner != null){
            myauth.removeAuthStateListener(myauthlistner);
        }
    }

}
