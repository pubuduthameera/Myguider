package com.example.myguider;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.myguider.firebase.FirebaseMethod;
import com.example.myguider.firebase.guid_acc_setting;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

import static androidx.constraintlayout.widget.StateSet.TAG;

public class guide_inter extends AppCompatActivity {
    private FirebaseAuth.AuthStateListener myauthlistner;
    private FirebaseAuth myauth=FirebaseAuth.getInstance();
    guid_acc_setting gac=new guid_acc_setting();
    ImageView imageView;
    TextView tv;
    FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    DatabaseReference databaseReference=firebaseDatabase.getReference();
    FirebaseMethod firebaseMethod=new FirebaseMethod();
    DatabaseReference dr=databaseReference.child("guide");
//    DatabaseReference dr=databaseReference.child("guide");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_inter);
         tv=findViewById(R.id.un);

         //        tv.setText(newguid.usern);
        imageView=findViewById(R.id.profile_image);

        setupfirbaseauth();
    }

private void setupfirbaseauth() {
    Log.d(TAG,"setup firebaseauth");
    myauthlistner=new FirebaseAuth.AuthStateListener(){

        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            FirebaseUser user=firebaseAuth.getCurrentUser();

            if (user != null){
                Log.d(TAG,"signed in"+user.getUid());

               dr.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Map<String, String> map = (Map<String, String>) snapshot.getValue();
                        String img=map.get("imgUrl");
                        String name=map.get("usern");
                        Firebase_database gac=new Firebase_database();
                        String link=snapshot.getKey();
                        Log.d(TAG,"url eka"+img);
                        Log.d(TAG,"name eka"+name);
                        tv.setText(gac.getUsern());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
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
