package com.example.urban_crew_extended;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
<<<<<<< HEAD

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
=======
>>>>>>> origin/master
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;




public class SignIn_SignUp extends AppCompatActivity {

    Button sign_in;
    Button sign_up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in__sign_up);

        sign_in = (Button)findViewById(R.id.sign_in);
        sign_up = (Button)findViewById(R.id.sign_up_1);

<<<<<<< HEAD
        checkConnection();

=======
>>>>>>> origin/master

        sign_in.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                openSignIn();

            }
        });

        sign_up.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                openSignUp();

            }
        });
    }

    public void openSignIn() {

        Intent intent = new Intent(this,SignIn.class);
        startActivity(intent);
    }

    public void openSignUp() {

        Intent intent = new Intent(this,SignUp.class);
        startActivity(intent);
    }

    public void checkConnection(){

        ConnectivityManager manager = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = manager.getActiveNetworkInfo();

        if (null != activeNetwork){

            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI){

                Toast.makeText(this, "Wifi Enabled", Toast.LENGTH_SHORT).show();
            }

            else  if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE){

                Toast.makeText(this, "Data Network Enabled", Toast.LENGTH_SHORT).show();
            }
        }

        else {

            Toast.makeText(this, "Hmmm...No Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }
}
