package com.example.myguider.firebase;

import android.content.Context;
import android.util.Log;

import com.example.myguider.Firebase_database;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;

import static androidx.constraintlayout.widget.StateSet.TAG;

public class FirebaseMethod {
    FirebaseAuth auth=FirebaseAuth.getInstance();
    String udi=auth.getCurrentUser().getUid();
    private Context context;
    public Firebase_modle getGuideAccountDetails(DataSnapshot snapshot){
        Log.d(TAG,"retriving data from firebase database");
        Firebase_database fib=new Firebase_database();
        guid_acc_setting gas=new guid_acc_setting();
        for (DataSnapshot ds: snapshot.getChildren()){
            if (ds.getKey().equals("guideaccsetting")){
                try {

                    gas.setDisplayname(
                            ds.child(udi)
                                    .getValue(guid_acc_setting.class)
                                    .getDisplayname()
                    );
                    gas.setDesription(
                            ds.child(udi)
                                    .getValue(guid_acc_setting.class)
                                    .getDesription()
                    );
                    gas.setPropic(
                            ds.child(udi)
                                    .getValue(guid_acc_setting.class)
                                    .getPropic()
                    );
                    gas.setRating(
                            ds.child(udi)
                                    .getValue(guid_acc_setting.class)
                                    .getRating()
                    );
                    gas.setPosts(
                            ds.child(udi)
                                    .getValue(guid_acc_setting.class)
                                    .getPosts()
                    );
                    gas.setCountry(
                            ds.child(udi)
                                    .getValue(guid_acc_setting.class)
                                    .getCountry()
                    );
                    gas.setUsername(
                            ds.child(udi)
                                    .getValue(guid_acc_setting.class)
                                    .getUsername()
                    );

                }catch (NullPointerException e){
                    Log.e(TAG,e.getMessage());
                }

                if (ds.getKey().equals("guide")){
                    fib.setUsern(
                            ds.child(udi)
                                    .getValue(Firebase_database.class)
                                    .getUsern()
                    );
                    fib.setEmail(
                            ds.child(udi)
                                    .getValue(Firebase_database.class)
                                    .getEmail()
                    );
                    fib.setMobile(
                            ds.child(udi)
                                    .getValue(Firebase_database.class)
                                    .getMobile()
                    );
                    fib.setPassword(
                            ds.child(udi)
                                    .getValue(Firebase_database.class)
                                    .getPassword()
                    );
                    fib.setImgUri(
                            ds.child(udi)
                                    .getValue(Firebase_database.class)
                                    .getImgUrl()
                    );
                    fib.setCountry(
                            ds.child(udi)
                                    .getValue(Firebase_database.class)
                                    .getCountry()
                    );
                    fib.setDesription(
                            ds.child(udi)
                                    .getValue(Firebase_database.class)
                                    .getDesription()
                    );

                }

            }
        }
        return  new Firebase_modle(fib,gas);
    }
}
