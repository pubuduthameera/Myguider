package com.example.myguider;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myguider.firebase.guid_acc_setting;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


import de.hdodenhof.circleimageview.CircleImageView;



public class guidpropic extends AppCompatActivity {
    private FirebaseAuth myAuth;
    private FirebaseStorage storage;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    String des;
    String imageurl;
    Uri imguri;
    CircleImageView circleImageView;
    ProgressDialog dialog;
    FloatingActionButton FAB;
    private FirebaseDatabase database;
    DatabaseReference reference;
    DatabaseReference dr;
    StorageReference storageReference;
///ADD progressbar
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_guidpropic );

        //firebase part
        myAuth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference( "guide" );
        dr = database.getReference( "guideaccsetting" );
        storageReference = storage.getReference();

        FAB = findViewById( R.id.floatingActionButton );
        Button cabtn = findViewById( R.id.create_accbtn );
        circleImageView = findViewById( R.id.profile_image );
        EditText description = findViewById( R.id.gdescription );

        dialog = new ProgressDialog( this );
        dialog.setMessage( "uploading img..." );
        dialog.setCancelable( false );

        FAB.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageview();
            }
        } );


        cabtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imguri != null) {
                    StorageReference storageReference = storage.getReference().child( "profiles/" + imguri.getLastPathSegment() );
                    storageReference.putFile( imguri ).addOnSuccessListener( new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            storageReference.getDownloadUrl().addOnSuccessListener( new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    myAuth.createUserWithEmailAndPassword( newguid.email, newguid.password ).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if (task.isSuccessful()) {
                                                des = description.getText().toString().trim();
                                                imageurl = uri.toString();
                                                Firebase_database firebase_database = new Firebase_database( newguid.usern, newguid.email, newguid.password, newguid.country, newguid.mobile, des, imageurl );
                                                reference.child( Objects.requireNonNull( myAuth.getUid() ) ).setValue( firebase_database );
                                                guid_acc_setting gas = new guid_acc_setting( newguid.usern, newguid.usern, newguid.country, des, imageurl, 0, 0 );
                                                dr.child( myAuth.getUid() ).setValue( gas );

                                                Toast.makeText( guidpropic.this, "successful", Toast.LENGTH_SHORT ).show();
                                                myAuth.signOut();
                                                startActivity( new Intent( getApplicationContext(), guidlogin.class ) );
                                            } else {
                                                Toast.makeText( guidpropic.this, "fail", Toast.LENGTH_SHORT ).show();
                                            }
                                        }
                                    } );
                                }
                            } );
                        }
                    } );
                }
            }
        } );
    }

    private void imageview() {

            Intent intent=new Intent();
            intent.setAction(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(intent,1);

    }
    private void cloudstore(){
        Map<String, String> user = new HashMap<>();
        imageurl=imguri.toString();
        user.put("name ", newguid.usern );
        user.put("image", imguri.toString());


// Add a new document with a generated ID
        db.collection("new").add(user).addOnCompleteListener(task -> Toast.makeText(getApplicationContext(),"inserted",Toast.LENGTH_LONG).show());

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1 && resultCode==RESULT_OK && data != null && data.getData()!=null){

                Uri uri=data.getData(); //file path
                circleImageView.setImageURI(imguri);
                StorageReference storageReference=storage.getReference().child("profiles/");
                storageReference.putFile(uri).addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                        storageReference.getDownloadUrl().addOnSuccessListener(uri1 -> {
                            Snackbar.make(findViewById(android.R.id.content),"image uploaded",Snackbar.LENGTH_LONG).show();
                            String filepath= uri1.toString();
                            HashMap<String,Object> objectHashMap=new HashMap<>();
                            objectHashMap.put("image",filepath);

                        });
                    }
                });
                circleImageView.setImageURI(data.getData());
                imguri =data.getData();

        }
    }

}
