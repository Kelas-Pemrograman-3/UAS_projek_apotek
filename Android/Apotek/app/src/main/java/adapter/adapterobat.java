package adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.noni.apotek.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import model.obatmodel;
import server.ConfigUrl;

public class adapterobat extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<obatmodel> item;

    private RequestQueue mRequestQueue;
//    ProgressDialog pDialog;

    public adapterobat(Activity activity, List<obatmodel> item, RequestQueue mRequestQueue, ProgressDialog pDialog) {
        this.activity = activity;
        this.item = item;
        this.mRequestQueue = mRequestQueue;
//        this.pDialog = pDialog;
    }

    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public Object getItem(int position) {
        return item.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.daftarobat, null);


        TextView namaobat = (TextView) convertView.findViewById(R.id.edtnamaobat);
        TextView stockobat = (TextView) convertView.findViewById(R.id.edtstock);
        TextView hargaobat = (TextView) convertView.findViewById(R.id.edthargaobat);

        namaobat.setText(item.get(position).getNama_Obat());
        stockobat.setText(item.get(position).getStok());
        hargaobat.setText(item.get(position).getHarga());

        return convertView;
    }

//    private void showDialog() {
//        if (!pDialog.isShowing())
//            pDialog.show();
//    }
//
//    private void hideDialog() {
//        if (pDialog.isShowing())
//            pDialog.dismiss();
//    }
}
