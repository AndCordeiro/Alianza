package com.example.alianza.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.alianza.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(3000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{

                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    if(user != null){

                        Intent intent = new Intent(SplashScreenActivity.this,MainActivity.class);
                        startActivity(intent);

                    }else{
                        Intent intent = new Intent(SplashScreenActivity.this,LoginActivity.class);
                        startActivity(intent);

                    }




                }
            }
        };
        timerThread.start();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }
}
