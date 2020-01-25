package com.noni.apotek;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import session.SessionManager;

public class menu extends AppCompatActivity {

    private SessionManager session;

    ImageView menu1,menu2,menu3,keluar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getSupportActionBar().hide();

        session = new SessionManager(this);
        menu1 = findViewById(R.id.txtmenu1);
        menu2 = findViewById(R.id.txtmenu2);
        menu3 = findViewById(R.id.txtlihatkeranjang);
        keluar = findViewById(R.id.txtkeluar);

        menu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent q = new Intent(menu.this, obat.class);
                startActivity(q);
                finish();
            }
        });

        menu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent q = new Intent(menu.this, lihatkeranjang.class);
                startActivity(q);
                finish();
            }
        });

        menu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent q = new Intent(menu.this, keranjang.class);
                startActivity(q);
                finish();
            }
        });

        keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.setLogin(false);
                session.setSkip(false);
                session.setSessid(0);
                Intent q = new Intent(menu.this, loginuser.class);
                startActivity(q);
                finish();
            }
        });

    }
    @Override
    public void onBackPressed(){
        Intent l = new Intent(menu.this, loginuser.class);
        startActivity(l);
        finish();
    }
}
