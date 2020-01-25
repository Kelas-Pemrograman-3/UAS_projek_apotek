package com.noni.apotek;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import server.ConfigUrl;
import session.SessionManager;

public class loginuser extends AppCompatActivity {

    private RequestQueue mRequestQueue;
    private EditText edtuser,edtpassword;

    private Button btnlinkregister,login;

    private ProgressDialog pDialog;

    //  ini untuk session Login
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginuser);

        //Untuk Menghilangkan NavBar di Tampilan
        getSupportActionBar().hide();

        mRequestQueue = Volley.newRequestQueue(this);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        session = new SessionManager(this);

        if (session.isLoggedIn()){
            Intent i = new Intent(loginuser.this,menu.class);
            startActivity(i);
            finish();
        }

        edtuser = (EditText)findViewById(R.id.txtuserlogin);
        edtpassword = (EditText)findViewById(R.id.txtpasslogin);

        login = (Button)findViewById(R.id.btnlogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String struser = edtuser.getText().toString();

                String strpassword = edtpassword.getText().toString();

                if (struser.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Npm Tidak Boleh kosong",
                            Toast.LENGTH_LONG).show();

                }else if (strpassword.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Password Tidak Boleh kosong",
                            Toast.LENGTH_LONG).show();
                }else {
                    login(struser,strpassword);
                }
            }
        });

        btnlinkregister = (Button) findViewById(R.id.btnlinkregister);

        btnlinkregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(loginuser.this,registeruser.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void login(String username, String password){
//        final String URL = "/volley/resource/12";
// Post params to be sent to the server
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("username", username);
        params.put("password", password);

        pDialog.setMessage("Mohon Tunggu...");
        showDialog();

        JsonObjectRequest req = new JsonObjectRequest(ConfigUrl.login, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        hideDialog();
                        try {
                            boolean status = response.getBoolean("error");
                            String msg;
                            if (status== true){
                                msg = response.getString("pesan");
                            }else {
                                session.setLogin(true);
                                msg = response.getString("pesan");
                                Intent i = new Intent(loginuser.this,menu.class);
                                startActivity(i);
                                finish();
                            }
                            Toast.makeText(getApplicationContext(),  msg, Toast.LENGTH_LONG).show();
//                            VolleyLog.v("Response:%n %s", response.toString(4));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                hideDialog();
                VolleyLog.e("Error: ", error.getMessage());
            }
        });

// add the request object to the queue to be executed
//        ApplicationController.getInstance().addToRequestQueue(req);
        mRequestQueue.add(req);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
    @Override
    public void onBackPressed(){
        Intent l = new Intent(loginuser.this, registeruser.class);
        startActivity(l);
        finish();
    }
}
