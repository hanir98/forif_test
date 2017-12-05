package com.example.hallf.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by hallf on 2017-11-21.
 */

public class add extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);

        Intent intent = getIntent();
        String imformation1 = intent.getExtras().getString("imformation1");
        String imformation2 = intent.getExtras().getString("imformation2");

        TextView tx = (TextView)findViewById(R.id.tx);
        TextView tx1 = (TextView)findViewById(R.id.tx1);

        tx.setText(imformation1);
        tx1.setText(imformation2);

    }



}
