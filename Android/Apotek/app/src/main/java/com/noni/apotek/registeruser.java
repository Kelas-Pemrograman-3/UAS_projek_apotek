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

public class registeruser extends AppCompatActivity {

    private RequestQueue mRequestQueue;

    private EditText edtuser,edtnama,edtpassword,edtprodi;
    private Button btnregister,btnlinklogin;

    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeruser);
        getSupportActionBar().hide();

        mRequestQueue = Volley.newRequestQueue(this);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        edtuser      = (EditText) findViewById(R.id.txtuserregis);
        edtnama     = (EditText) findViewById(R.id.txtnamaregis);
        edtpassword = (EditText) findViewById(R.id.txtpassregis);

        btnregister = (Button) findViewById(R.id.btnregis);

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String struser = edtuser.getText().toString();
                String strnama = edtnama.getText().toString();
                String strpassword = edtpassword.getText().toString();

                if (struser.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Username Tidak Boleh kosong",
                            Toast.LENGTH_LONG).show();
                }else if (strnama.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Nama Tidak Boleh kosong",
                            Toast.LENGTH_LONG).show();
                }else if (strpassword.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Password Tidak Boleh kosong",
                            Toast.LENGTH_LONG).show();
                }else {
                    registrasi(struser, strnama,strpassword);

//                    Intent a = new Intent(RegistrasiActivity.this, LoginActivity.class);
//                    startActivity(a);
//                    finish();
                }

            }
        });

        btnlinklogin = (Button) findViewById(R.id.btnlinklogin);

        btnlinklogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(registeruser.this,loginuser.class);
                startActivity(i);
                finish();
            }
        });
    }
    private void registrasi(String username, String nama, String password){
//        final String URL = "/volley/resource/12";
// Post params to be sent to the server
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("username", username);
        params.put("nama", nama);
        params.put("password", password);

        pDialog.setMessage("Mohon Tunggu...");
        showDialog();

        JsonObjectRequest req = new JsonObjectRequest(ConfigUrl.register, new JSONObject(params),
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
                                msg = response.getString("pesan");

                                Intent i = new Intent(registeruser.this,loginuser.class);
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
    public void onBackPressed(){
        Intent l = new Intent(registeruser.this, loginuser.class);
        startActivity(l);
        finish();
}
}
