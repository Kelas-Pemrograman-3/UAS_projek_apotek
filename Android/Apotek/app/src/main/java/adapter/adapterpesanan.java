package adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.noni.apotek.R;

import java.util.ArrayList;
import java.util.List;

import model.obatmodel;
import model.pesananmodel;

public class adapterpesanan extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<pesananmodel> item;

    private RequestQueue mRequestQueue;
//    ProgressDialog pDialog;

    public adapterpesanan(Activity activity, List<pesananmodel> item, RequestQueue mRequestQueue, ProgressDialog pDialog) {
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
            convertView = inflater.inflate(R.layout.daftarpesanan, null);


        TextView tnamapesanan = (TextView) convertView.findViewById(R.id.namapesanan);
        TextView tjumlahpesanan = (TextView) convertView.findViewById(R.id.jumlahpesanan);
        TextView thargapesanan = (TextView) convertView.findViewById(R.id.hargapesanan);
        TextView ttotalpesanan = (TextView) convertView.findViewById(R.id.totalpesanan);

        tnamapesanan.setText(item.get(position).getNamaobat());
        tjumlahpesanan.setText(item.get(position).getJumlahobat());
        thargapesanan.setText(item.get(position).getHargaobat());
        ttotalpesanan.setText(item.get(position).getTotalobat());

        return convertView;
    }
}
