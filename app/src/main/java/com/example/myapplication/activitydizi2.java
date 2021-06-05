package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class activitydizi2 extends AppCompatActivity {

    ImageView diziimageView1;
    TextView dizi1textView;

    FirebaseStorage firebaseStorage;
    StorageReference storageReference;

    private static final String LOG_TAG = "Otomatik internet Kontrol¸";
    private Activityfilm1.NetworkChangeReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activitydizi2);

        diziimageView1 =(ImageView)findViewById(R.id.film1image);
        dizi1textView =(TextView)findViewById(R.id.film1text);

        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();

        downLoadWithbytes1();

        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        receiver = new Activityfilm1.NetworkChangeReceiver();
        registerReceiver(receiver, filter);
    }

    @SuppressLint("LongLogTag")
    @Override
    protected void onDestroy() {
        Log.v(LOG_TAG, "onDestory");
        super.onDestroy();

        unregisterReceiver(receiver);

    }
    public static class NetworkChangeReceiver extends BroadcastReceiver {
        private static final String LOG_TAG = "Otomatik internet Kontrol¸";
        static boolean isConnected = false;

        @Override
        public void onReceive(final Context context, final Intent intent) {

            isNetworkAvailable(context);

        }


        @SuppressLint("LongLogTag")
        private boolean isNetworkAvailable(Context context) {
            ConnectivityManager connectivity = (ConnectivityManager)
                    context.getSystemService(Context.CONNECTIVITY_SERVICE);

            if (connectivity != null) {
                NetworkInfo[] info = connectivity.getAllNetworkInfo();
                if (info != null) {
                    for (int i = 0; i < info.length; i++) {
                        if (info[i].getState() == NetworkInfo.State.CONNECTED) {

                            if(!isConnected){
                                isConnected = true;
                            }
                            return true;
                        }
                    }
                }
            }
            isConnected = false;
            Toast.makeText(context, "İnternet Yok", Toast.LENGTH_LONG).show();
            Log.v(LOG_TAG, "İnternet Yok!");
            return false;
        }
    }


    public void downLoadWithbytes1(){
        StorageReference imageRefl = storageReference.child("diziimage2.jpg");

        long MAXBYTES = 1080*1080;

        imageRefl.getBytes(MAXBYTES).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {



                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference reference = database.getReference("dizisayfa/dizi2text");

                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        dizi1textView.setText(snapshot.getValue().toString());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });



                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                diziimageView1.setImageBitmap(bitmap);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }


    public void menu(View view){
        Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
        startActivity(intent);
    }

    public void menukapa(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}