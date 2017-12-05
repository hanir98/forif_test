package com.example.hallf.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.provider.ContactsContract;
import android.renderscript.ScriptGroup;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText input;
    String status;
    Drawable photo;
    Adapter adapter;
    String[] x ={"61","53","77"};
    String[] y ={"127","38","86"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(new Intent(this, splash.class ));
        adapter = new Adapter();
        ListView listView = (ListView) findViewById(R.id.listview) ;
        listView.setAdapter(adapter);
        final TextView tx = (TextView) findViewById(R.id.textcity);
        final TextView tx1 = (TextView) findViewById(R.id.textwheater);
        input = (EditText) findViewById(R.id.input);



        for (int i = 0; i<x.length; i++){



        }



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView adapterView, View view, int i, long id) {
                listData list = (listData) adapter.getItem(i);
                final String a =list.getStatus();
                tx1.setText(a);
                final String b=list.getCity();
                tx.setText(b);

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("확인")
                        .setMessage("넘어갈?")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener(){

                            public void onClick(DialogInterface dlg, int sumthin){

                                Intent intent = new Intent(MainActivity.this ,add.class);
                                intent.putExtra("imformation1", a );
                                intent.putExtra("imformation2", b );
                                startActivity(intent);

                            }

                        })
                        .setNegativeButton("no", new DialogInterface.OnClickListener(){

                            public void onClick(DialogInterface dlg, int sumthin){



                            }

                        }).show();


            }

        });
    }



    public void findd(View v){

        new Thread(){
            public void run(){
                String clientid =  "eNZEgR1LVRybwbYzxNKV";
                String secret = "buprsN1B9q";
                try {
                    String result = input.getText().toString();
                    String text = URLEncoder.encode(result, "UTF-8");
                    String apiURL = "https://openapi.naver.com/v1/search/encyc?query="+ text; // json 결과
                    //String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // xml 결과
                    URL url = new URL(apiURL);
                    HttpURLConnection con = (HttpURLConnection)url.openConnection();
                    con.setRequestMethod("GET");
                    con.setRequestProperty("X-Naver-Client-Id", clientid);
                    con.setRequestProperty("X-Naver-Client-Secret", secret);//헤더
                    int responseCode = con.getResponseCode();
                    BufferedReader br;
                    if(responseCode==200) { // 정상 호출
                        br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    } else {  // 에러 발생
                        br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                    }
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = br.readLine()) != null) {
                        response.append(inputLine);
                    }
                    br.close();
                    String resul = response.toString();
                    System.out.println(resul);
                } catch (Exception e) {
                System.out.println(e);

            }
            }
        }.start();



    }

    public void find(View v){

        final StringBuffer buffer = new StringBuffer();
        new Thread() {
            public void run() {


                try {
                    URL url = new URL("http://newsky2.kma.go.kr/service/SecndSrtpdFrcstInfoService2/ForecastSpaceData?ServiceKey=zUpcN59PDHlR9%2Fn6RNYP9G6yS9xUjB%2BMiQ0BUS4foeCsstLf4XJU0TdI104RdxYiPM74fLhNEQxNkjWV%2FMRpdg%3D%3D&base_date=20171128&base_time=0230&nx=1&ny=1&_type=json");
                    URLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    InputStream contentStream = urlConnection.getInputStream();
                    BufferedReader bufreader = new BufferedReader(new InputStreamReader(contentStream, "UTF-8"));
                    String line = null;
                    while ((line = bufreader.readLine()) != null) {
                        buffer.append(line);
                    }
                    bufreader.close();
                    System.out.println(buffer.toString());


                } catch (
                        java.io.IOException e)

                {
                    e.printStackTrace();
                }

            }
        }.start();

        try {

            JSONObject jsonObject = new JSONObject(buffer.toString());
            JSONObject body = jsonObject.getJSONObject("body");
            System.out.println(body.toString());
            JSONObject items = body.getJSONObject("items");
            JSONArray item = items.getJSONArray("item");
            JSONObject iteminfo = item.getJSONObject(5);
            String fcstValue = iteminfo.getString("fcstValue");
            System.out.println(fcstValue);

            if (fcstValue == "1"){
                    status = "맑음";
                    photo = ContextCompat.getDrawable(this, R.drawable.phto1);


            }else if (fcstValue =="2"){
                    status = "구름조금";
                     photo = ContextCompat.getDrawable(this, R.drawable.poto);

            }else if (fcstValue =="3"){
                status = "구름많음";
                photo = ContextCompat.getDrawable(this, R.drawable.phto1);

            }else{
                status = "흐림";
                photo = ContextCompat.getDrawable(this, R.drawable.poto);


            }




        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}
