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


public class MainActivity extends AppCompatActivity {

    ImageView filmimageView1;
    ImageView filmimageView2;
    ImageView filmimageView3;
    ImageView filmimageView4;
    ImageView filmimageView5;
    ImageView filmimageView6;

    TextView imagetextView1;
    TextView imagetextView2;
    TextView imagetextView3;
    TextView imagetextView4;
    TextView imagetextView5;
    TextView imagetextView6;

    FirebaseStorage firebaseStorage;
    StorageReference storageReference;

    private static final String LOG_TAG = "internet Kontrol¸";
    private Activityfilm1.NetworkChangeReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        filmimageView1 = (ImageView)findViewById(R.id.filmimageView1);
        filmimageView2 = (ImageView)findViewById(R.id.filmimageView2);
        filmimageView3 = (ImageView)findViewById(R.id.filmimageView3);
        filmimageView4 = (ImageView)findViewById(R.id.filmimageView4);
        filmimageView5 = (ImageView)findViewById(R.id.filmimageView5);
        filmimageView6 = (ImageView)findViewById(R.id.filmimageView6);

        imagetextView1 =(TextView)findViewById(R.id.imagetextView1);
        imagetextView2 =(TextView)findViewById(R.id.imagetextView2);
        imagetextView3 =(TextView)findViewById(R.id.imagetextView3);
        imagetextView4 =(TextView)findViewById(R.id.imagetextView4);
        imagetextView5 =(TextView)findViewById(R.id.imagetextView5);
        imagetextView6 =(TextView)findViewById(R.id.imagetextView6);

        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();

        downLoadWithbytes1();
        downLoadWithbytes2();
        downLoadWithbytes3();
        downLoadWithbytes4();
        downLoadWithbytes5();
        downLoadWithbytes6();

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
        private static final String LOG_TAG = "internet Kontrol¸";
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

                            if (!isConnected) {
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
        StorageReference imageRefl = storageReference.child("filmimage1.jpg");

        long MAXBYTES = 1080*1080;

        imageRefl.getBytes(MAXBYTES).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {



                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference reference = database.getReference("filmanasayfa/imagetextView1");

                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        imagetextView1.setText(snapshot.getValue().toString());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });



                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                filmimageView1.setImageBitmap(bitmap);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
        }


    public void downLoadWithbytes2(){
        StorageReference imageRefl2 = storageReference.child("filmimage2.jpg");

        long MAXBYTES = 1080*1080;

        imageRefl2.getBytes(MAXBYTES).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {


                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference reference = database.getReference("filmanasayfa/imagetextView2");

                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        imagetextView2.setText(snapshot.getValue().toString());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                filmimageView2.setImageBitmap(bitmap);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }



    public void downLoadWithbytes3(){
        StorageReference imageRefl3 = storageReference.child("filmimage3.jpg");

        long MAXBYTES = 1080*1080;

        imageRefl3.getBytes(MAXBYTES).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {


                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference reference = database.getReference("filmanasayfa/imagetextView3");

                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        imagetextView3.setText(snapshot.getValue().toString());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                filmimageView3.setImageBitmap(bitmap);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }




    public void downLoadWithbytes4(){
        StorageReference imageRefl4 = storageReference.child("filmimage4.jpg");

        long MAXBYTES = 1080*1080;

        imageRefl4.getBytes(MAXBYTES).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {


                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference reference = database.getReference("filmanasayfa/imagetextView4");

                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        imagetextView4.setText(snapshot.getValue().toString());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                filmimageView4.setImageBitmap(bitmap);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }


    public void downLoadWithbytes5(){
        StorageReference imageRefl5 = storageReference.child("filmimage5.jpg");

        long MAXBYTES = 1080*1080;

        imageRefl5.getBytes(MAXBYTES).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {


                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference reference = database.getReference("filmanasayfa/imagetextView5");

                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        imagetextView5.setText(snapshot.getValue().toString());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                filmimageView5.setImageBitmap(bitmap);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }




    public void downLoadWithbytes6(){
        StorageReference imageRefl6 = storageReference.child("filmimage6.jpg");

        long MAXBYTES = 1080*1080;

        imageRefl6.getBytes(MAXBYTES).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {


                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference reference = database.getReference("filmanasayfa/imagetextView6");

                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        imagetextView6.setText(snapshot.getValue().toString());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                filmimageView6.setImageBitmap(bitmap);
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

    public void film1(View view){
        Intent intent = new Intent(getApplicationContext(), Activityfilm1.class);
        startActivity(intent);
    }

    public void film2(View view){
        Intent intent = new Intent(getApplicationContext(), activityfilm2.class);
        startActivity(intent);
    }

    public void film3(View view){
        Intent intent = new Intent(getApplicationContext(), activityfilm3.class);
        startActivity(intent);
    }

    public void film4(View view){
        Intent intent = new Intent(getApplicationContext(), activityfilm4.class);
        startActivity(intent);
    }

    public void film5(View view){
        Intent intent = new Intent(getApplicationContext(), activityfilm5.class);
        startActivity(intent);
    }

    public void film6(View view){
        Intent intent = new Intent(getApplicationContext(), activityfilm6.class);
        startActivity(intent);
    }

    }
