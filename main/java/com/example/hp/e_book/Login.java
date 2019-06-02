package com.example.user.finalproject;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class Login extends AppCompatActivity {
    int counter=5;
    Button logIn;
    TextView tv12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText userName = (EditText) findViewById(R.id.username);
        final EditText passWord = (EditText) findViewById(R.id.password);
        final TextView clickme = (TextView)findViewById(R.id.textView20);
        final TextView register = (TextView) findViewById(R.id.clickMe);
        logIn = (Button) findViewById(R.id.btn_login);
        tv12 = findViewById(R.id.textView12);

        tv12.setText("Number of attempts remaining: 5");

//        loginlist = new ArrayList<>();



        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = userName.getText().toString();
                String password = passWord.getText().toString();
                if (TextUtils.isEmpty(username)) {
                    userName.setError("Please enter your username");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    passWord.setError("Please enter your password");
                    return;
                }
                    loginUser(username, password);
            }
        });

        clickme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (Login.this,ForgetPassword.class);
                startActivity(intent);
            }
        });

        // Registration
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });

    }

    private void loginUser(final String username, final String password) {
        class LoginUser extends AsyncTask<Void, Void, String> {

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("username", username);
                hashMap.put("password", password);
                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest("http://studentsumber.com/Easy_Costing/login.php", hashMap);
                return s;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

//                if (s != null) {
//                    try {
//                        JSONObject jsonObj = new JSONObject(s);
//                        JSONArray userArray = jsonObj.getJSONArray("supplier");
//                        loginn = new Loginn[userArray.length()];
//                        for (int i = 0; i < userArray.length(); i++) {
//                            JSONObject c = userArray.getJSONObject(i);
//                            String pid = c.getString("pid");
//                            String sname = c.getString("sname");
//                            String sproduct = c.getString("sproduct");
//                            String tprice = c.getString("tprice");
//                            loginn[i] = new Loginn(pid, sname, sproduct, tprice);
//                            HashMap<String, String> supp = new HashMap<>();
//                            supp.put("pid",pid);
//                            supp.put("sname", sname);
//                            supp.put("sproduct", sproduct);
//                            supp.put("tprice",tprice);
//                            loginlist.add(supp);
//                        }
//                    } catch (final JSONException e) {}
//                    ListAdapter adapter = new customlist_AddP(getBaseContext(), loginlist,
//                            R.layout.activity_customlist__add_p, new String[]
//                            {"pid","sname", "sproduct", "tprice"}, new int[]
//                            {R.id.tvPID,R.id.tvSname,R.id.tvPname, R.id.tvPrices});
//                }

                if (s.equals("success")) {
                    Toast.makeText(Login.this, "Well done! Login successfully!!",
                            Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Login.this, Main2.class);
                    startActivity(intent);
                } else {
                    counter--;
                    tv12.setText("Number of attempts remaining: "+String.valueOf(counter));

                    Toast.makeText(Login.this, "Sorry, Login failed",
                            Toast.LENGTH_LONG).show();
                    if(counter==0){
                        logIn.setEnabled(false);

                    }
                }
            }
        }
        try {
            LoginUser luser = new LoginUser();
            luser.execute();
        } catch (Exception e) {
        };
    }

}
