package com.example.user.finalproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class getAcc extends AppCompatActivity {
    Button resetP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_acc);

        final Toolbar toolbar = findViewById(R.id.toolbarAcc);
        toolbar.setTitle("Isn't Your Account?");
        setSupportActionBar(toolbar);

        resetP = (Button)findViewById(R.id.buttonReset);

        resetP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAcc();
//                Intent intent = new Intent(getAcc.this, Login.class);
//                startActivity(intent);
            }
        });
    }

    public void getAcc() {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this,
                    android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle("Password Reset Successfully")
                .setMessage("Password Reset Successfully! Login to your account quickly!")
                .setPositiveButton(android.R.string.yes, new
                        DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getAcc.this,"Go to Login Screen",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getAcc.this,Login.class);
                                startActivity(intent);
                            }
                        })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logout,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.idLogout:
                Logout();
                return true;
            case R.id.idback:
                Back();
                return true;
            case R.id.idMainPage:
                MainPage();
                return true;
            default:
                Toast.makeText(this,"No item selected", Toast.LENGTH_SHORT).show();


        }
        return super.onOptionsItemSelected(item);


    }

    public  void Logout(){
        Toast.makeText(this,"Good Job! Logout successfully", Toast.LENGTH_SHORT).show();
        this.startActivity(new Intent(getApplicationContext(),Login.class));
        finish();
    }


    public  void Back(){
        Toast.makeText(this," Back to Last Screen ", Toast.LENGTH_SHORT).show();
        this.onBackPressed();

    }

    public  void MainPage(){
        Toast.makeText(this," Back to Main Screen ", Toast.LENGTH_SHORT).show();
        this.startActivity(new Intent(getApplicationContext(),System.class));

    }
}
