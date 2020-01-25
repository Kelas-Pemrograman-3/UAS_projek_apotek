package com.noni.apotek;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import adapter.adapterobat;
import adapter.adapterpesanan;
import model.obatmodel;
import model.pesananmodel;
import server.ConfigUrl;

public class lihatkeranjang extends AppCompatActivity {

    ProgressDialog pDialog;

    adapterpesanan adapter;
    ListView list;

    ArrayList<pesananmodel> newsList = new ArrayList<pesananmodel>();

    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pesanan);getSupportActionBar().hide();

        mRequestQueue = Volley.newRequestQueue(this);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        list = (ListView) findViewById(R.id.ldaftarpesanan);
        newsList.clear();
        adapter = new adapterpesanan(lihatkeranjang.this, newsList, mRequestQueue, pDialog);
        list.setAdapter(adapter);
        getAllData();

    }
    private void getAllData() {
        // Pass second argument as "null" for GET requests
        pDialog.setMessage("Loading");
        showDialog();
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, ConfigUrl.getallpesanan, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        hideDialog();
                        try {
                            boolean status = response.getBoolean("error");
                            if (status == false) {
                                String data = response.getString("data");
                                JSONArray jsonArray = new JSONArray(data);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    pesananmodel pesanan = new pesananmodel();
                                    pesanan.set_id(jsonObject.getString("_id"));
                                    pesanan.setNamaobat(jsonObject.getString("namaobat"));
                                    pesanan.setJumlahobat(jsonObject.getString("jumlahobat"));
                                    pesanan.setHargaobat(jsonObject.getString("hargaobat"));
                                    pesanan.setTotalobat(jsonObject.getString("totalobat"));


                                    newsList.add(pesanan);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                hideDialog();
            }
        });

        /* Add your Requests to the RequestQueue to execute */
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
        Intent l = new Intent(lihatkeranjang.this, menu.class);
        startActivity(l);
        finish();
    }
}
