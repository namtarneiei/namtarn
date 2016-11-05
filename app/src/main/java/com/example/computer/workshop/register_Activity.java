package com.example.computer.workshop;

import android.os.AsyncTask;
import android.speech.tts.Voice;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class register_Activity extends AppCompatActivity {

    private EditText eddisplay;
    private EditText edReUser;
    private EditText edRePass;
    private Button btReRegis;
    private EditText edReCon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_);

        bindWidget();
        setListener();
        validate();
    }

    private boolean validate() {
        //to validate from
        String username = edReUser.getText().toString();
        String password = edRePass.getText().toString();
        String passwordConfirm = edReCon.getText().toString();
        String displayName = eddisplay.getText().toString();

        if (username.isEmpty())
            return false;

        if (password.isEmpty())
            return false;

        if (passwordConfirm.isEmpty())
            return false;

        if (displayName.isEmpty())
            return false;
        else
            return true;
    }


    private void setListener() {
        Button btLogin = (Button) findViewById(R.id.btReRegis);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    //TODO
                    new Register1(edReUser.getText().toString(),
                            edRePass.getText().toString(),
                            edReCon.getText(), toString(),
                            eddisplay.getText().toString()).execute();
                } else {
                    Toast.makeText(register_Activity.this, "กรุณากรอกข้อมูลให้ครบถ้วน", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void bindWidget() {
        eddisplay = (EditText) findViewById(R.id.eddisplay);
        edReUser = (EditText) findViewById(R.id.edReUser);
        edRePass = (EditText) findViewById(R.id.edRePass);
        edReCon = (EditText) findViewById(R.id.edReCon);
        btReRegis = (Button) findViewById(R.id.btReRegis);
    }

    private class Register1 extends AsyncTask<Void, Void, String> {

        private String username;
        private String password;
        private String passwordCon;
        private String displayName;

        public Register1(String username, String password, Editable text, String passwordCon, String displayName) {
            this.username = username;
            this.password = password;
            this.passwordCon = passwordCon;
            this.displayName = displayName;
        }

        //methods onPreExecute , doInBackground , onPostExecute สามารถต่างคนต่างทำได้เมื่อทำเสร็จจะส่งค่ามา onPostExecute
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... voids) {
            OkHttpClient client = new OkHttpClient();
            Request request;
            Response response;

            RequestBody requestBody = new FormBody.Builder()
                    .add("username", username)
                    .add("password", password)
                    .add("password_con", passwordCon)
                    .add("display_name", displayName)
                    .build();

            request = new Request.Builder()
                    .url("http://kimhun55.com/pollservices/signup.php")
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

        @Override
        protected void onPostExecute(String s) { //ผลรวมการทำงานทั้งหมด
            super.onPostExecute(s);
            Toast.makeText(register_Activity.this, s, Toast.LENGTH_SHORT).show();

            try {
                JSONObject rootObj = new JSONObject(s);
                if (rootObj.has("result")) {
                    JSONObject resultObj = rootObj.getJSONObject("result");
                    if (resultObj.getInt("result") == 1) {
                        Toast.makeText(register_Activity.this, resultObj.getString("result_desc"), Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(register_Activity.this, resultObj.getString("result_desc"), Toast.LENGTH_SHORT).show();
                    }
                }
            } catch (JSONExceptiont ex) {

            }
        }
    }
}
