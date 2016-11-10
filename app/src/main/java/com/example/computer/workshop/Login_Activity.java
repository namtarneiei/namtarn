package com.example.computer.workshop;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Login_Activity extends AppCompatActivity {


    private Button btLog;
    private Button btRegis;
    private EditText edUser;
    private EditText edPass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);

        setListener();
        validate();

        edUser = (EditText) findViewById(R.id.edUser);
        edPass = (EditText) findViewById(R.id.edPass);
    }

    private boolean validate() {
        String user = edUser.getText().toString();
        String pass = edPass.getText().toString();

        if (user.isEmpty())
            return false;

        if (pass.isEmpty())
            return false;

        return true;
    }

    private void setListener() {
        btLog = (Button) findViewById(R.id.btLog);
        btRegis = (Button) findViewById(R.id.btRegis);
        edUser = (EditText) findViewById(R.id.edUser);
        edPass = (EditText) findViewById(R.id.edPass);

        btLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    new Login_Activity.login1(edUser.getText().toString(),
                            edPass.getText().toString()).execute();
                } else {
                    Toast.makeText(Login_Activity.this, "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();

                }
            }
        });
        btRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login_Activity.this, register_Activity.class);
                startActivity(i);
            }
        });

    }

    private class login1 extends AsyncTask<Void, Void, String> {
        private String edUser;
        private String edPass;

        public login1(String username, String password) {
            this.edUser = username;
            this.edPass = password;
        }


        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... voids) {
            OkHttpClient client = new OkHttpClient();
            Request request;
            Response response;

            RequestBody requestBody = new FormBody.Builder()
                    .add("username", edUser)
                    .add("password", edPass)
                    .build();
            request = new Request.Builder()
                    .url("http://kimhun55.com/pollservices/login.php")
                    .post(requestBody)
                    .build();
            try {
                response = client.newCall(request).execute(); //ทำการรอขอกับ server
                if (response.isSuccessful()) {
                    return response.body().string();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String s) { //ผลรวมการทำงานทั้งหมด
            super.onPostExecute(s);
            Toast.makeText(Login_Activity.this, s, Toast.LENGTH_SHORT).show();

            try {
                JSONObject rootObj = new JSONObject(s);
                if (rootObj.has("result")) {
                    JSONObject resultObj = rootObj.getJSONObject("result");
                    if (resultObj.getInt("result") == 1) {
                        Intent i = new Intent(Login_Activity.this, new_list_Activity.class);
                        startActivity(i);

                    } else {
                        Toast.makeText(Login_Activity.this, resultObj.getString("result_desc"), Toast.LENGTH_SHORT).show();
                    }
                }


            } catch (JSONException ex) {

            }
        }


    }
}


