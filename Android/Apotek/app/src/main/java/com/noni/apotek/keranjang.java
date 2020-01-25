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

public class keranjang extends AppCompatActivity {

    private RequestQueue mRequestQueue;

    private EditText edtnama,edtjumlah,edtharga,edttotal;
    private Button pesan;

    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keranjang);
        getSupportActionBar().hide();

        mRequestQueue = Volley.newRequestQueue(this);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        edtnama      = (EditText) findViewById(R.id.txtnamaobatd);
        edtjumlah     = (EditText) findViewById(R.id.txtjumlahobatd);
        edtharga    = (EditText) findViewById(R.id.txthargaobatd);
        edttotal = (EditText) findViewById(R.id.txttotalhargad);

        pesan = (Button) findViewById(R.id.btnpesanan);
        pesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String setnama = edtnama.getText().toString();
                String strjumlah = edtjumlah.getText().toString();
                String strharga = edtharga.getText().toString();
                String strtotal = edttotal.getText().toString();

                if (setnama.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Nama Tidak Boleh kosong",
                            Toast.LENGTH_LONG).show();
                }else if (strjumlah.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Jumlah Tidak Boleh kosong",
                            Toast.LENGTH_LONG).show();
                }else if (strharga.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Harga Studi Tidak Boleh kosong",
                            Toast.LENGTH_LONG).show();
                }else if (strtotal.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Total Harga Tidak Boleh kosong",
                            Toast.LENGTH_LONG).show();
                }else {
                    pesananmetod(setnama, strjumlah, strharga,strtotal);
                }

            }
        });
    }
    private void pesananmetod(String namaobat, String jumlahobat, String hargaobat, String totalobat){
//        final String URL = "/volley/resource/12";
// Post params to be sent to the server
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("namaobat", namaobat);
        params.put("jumlahobat", jumlahobat);
        params.put("hargaobat", hargaobat);
        params.put("totalobat", totalobat);

        pDialog.setMessage("Mohon Tunggu...");
        showDialog();

        JsonObjectRequest req = new JsonObjectRequest(ConfigUrl.postpesanan, new JSONObject(params),
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

                                Intent i = new Intent(keranjang.this,keranjang.class);
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
        Intent l = new Intent(keranjang.this, menu.class);
        startActivity(l);
        finish();
    }
}
