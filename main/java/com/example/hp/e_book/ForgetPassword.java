package com.example.user.finalproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class ForgetPassword extends AppCompatActivity {
    Button forget,login;
//    Button find;
    TextView eMail, newP;
    String email, newpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        final Toolbar toolbar = findViewById(R.id.toolbarFYP);
        toolbar.setTitle("Forget Password?");
        setSupportActionBar(toolbar);

//        find = findViewById(R.id.btnFind);
        forget = (Button)findViewById(R.id.button2);
        eMail = (TextView)findViewById(R.id.editText);
        newP = (TextView)findViewById(R.id.editText11);
        login = findViewById(R.id.buttonlogin);

        /*find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email == "email"){
                    emailtrue();
                }
                else{
                    emailfalse();
                }
            }
        });
*/
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(ForgetPassword.this,getAcc.class);
                startActivity(intent);*/

                email = eMail.getText().toString();
                newpassword = newP.getText().toString();
                if (TextUtils.isEmpty(email)) {
                    eMail.setError("Please enter your email");
                    return;
                }
                if (TextUtils.isEmpty(newpassword)) {
                    newP.setError("Please enter your new password");
                    return;
                }resetAcc();



            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = eMail.getText().toString();
                newpassword = newP.getText().toString();
                if (TextUtils.isEmpty(email)) {
                    eMail.setError("Please enter your email");
                    return;
                }
                if (TextUtils.isEmpty(newpassword)) {
                    newP.setError("Please enter your new password");
                    return;
                }loginAccNewP(email,newpassword);
            }
        });
    }

    private void loginAccNewP(final String email, final String newpassword) {

        class LoginUserN extends AsyncTask<Void, Void, String> {

            @Override
            protected String doInBackground(Void... params) {
                final HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("email", email);
                hashMap.put("newpassword", newpassword);

                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest("http://studentsumber.com/Easy_Costing/loginNew.php", hashMap);
                return s;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                if (s.equals("success")) {
                    Toast.makeText(ForgetPassword.this, "Login with new password successfully!!",
                            Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(ForgetPassword.this, Main2.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(ForgetPassword.this, "Sorry, Login failed",
                            Toast.LENGTH_LONG).show();
                }
            }
        }
        try {
            LoginUserN nuser = new LoginUserN();
            nuser.execute();
        } catch (Exception e) {
        };
    }

    private void resetPass(final String email, final String newpassword) {

        class LoginUser extends AsyncTask<Void, Void, String> {

            @Override
            protected String doInBackground(Void... params) {
                final HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("email", email);
                    hashMap.put("newpassword", newpassword);
//                 hashMap.put("email",email)==hashMap.get("email")

                /*find.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (email == "email"){
                            emailtrue();
                        }
                        else{
                            emailfalse();
                        }
                    }
                });*/

                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest("http://studentsumber.com/Easy_Costing/updatePassword.php", hashMap);
                return s;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                if (s.equals("success")) {
                    Toast.makeText(ForgetPassword.this, "Login with your new password quickly!!",
                            Toast.LENGTH_LONG).show();

//                    Intent intent = new Intent(ForgetPassword.this, Login.class);
//                    startActivity(intent);
                } else {
                    Toast.makeText(ForgetPassword.this, "Sorry, Update failed",
                            Toast.LENGTH_LONG).show();
                }
            }
        }
        try {
            LoginUser luser = new LoginUser();
            luser.execute();
        } catch (Exception e) {
        };
    }

    public void resetAcc() {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this,
                    android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle("Reset Password")
                .setMessage("Are you sure you want to reset your password?")
                .setPositiveButton(android.R.string.yes, new
                        DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                resetPass(email, newpassword);
                                Toast.makeText(ForgetPassword.this,"Password Reset Successfully!",Toast.LENGTH_SHORT).show();
//                                Intent intent = new Intent(ForgetPassword.this,Login.class);
//                                startActivity(intent);


                            }
                        })
                .setNegativeButton(android.R.string.no, new
                        DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(ForgetPassword.this,"Password reset fail!",Toast.LENGTH_SHORT).show();

                            }
                        })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public void emailtrue() {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this,
                    android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle("This is your account!")
                .setMessage("The email you had entered is true! Please reset your password!")
                .setPositiveButton(android.R.string.ok, new
                        DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(ForgetPassword.this,"Please reset your password!",Toast.LENGTH_SHORT).show();
//                                Intent intent = new Intent(ForgetPassword.this,Login.class);
//                                startActivity(intent);
                            }
                        })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public void emailfalse() {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this,
                    android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle("This is not your account!")
                .setMessage("Sorry! The email you had entered is not registered! Please try again thank you!")
                .setPositiveButton(android.R.string.ok, new
                        DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(ForgetPassword.this,"Back to login page",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ForgetPassword.this,Login.class);
                                startActivity(intent);
                            }
                        })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logout1,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.idback1:
                Back();
                return true;
            default:
                Toast.makeText(this,"No item selected", Toast.LENGTH_SHORT).show();


        }
        return super.onOptionsItemSelected(item);


    }
    public  void Back(){
        Toast.makeText(this," Back to Last Screen ", Toast.LENGTH_SHORT).show();
        this.onBackPressed();

    }
}
