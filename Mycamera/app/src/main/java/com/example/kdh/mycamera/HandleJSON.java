package com.example.kdh.mycamera;

/**
 * Created by kdh on 2015-03-08.
 */
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by kdh on 2015-02-14.
 */
public class HandleJSON {
    private String cbaSeq = "cbaSeq";
    private String cbaTyp = "cbaTyp";
    private String cbaAlikeWord = "cbaAlikeWord";
    private String cbaR1="cbaR1";
    private String cbaR2="cbaR2";
    private String cbaR3="cbaR3";
    private String cbaR4="cbaR4";
    private String cbaG1="cbaG1";
    private String cbaG2="cbaG2";
    private String cbaG3="cbaG3";
    private String cbaG4="cbaG4";
    private String cbaB1="cbaB1";
    private String cbaB2="cbaB2";
    private String cbaB3="cbaB3";
    private String cbaB4="cbaB4";
    private String urlString = null;
    ArrayList<Color> arrList = new ArrayList<Color>();

    public volatile boolean parsingComplete = true;

    public HandleJSON(String url) {
        this.urlString = url;
    }
    public String getSeq(){
        return cbaSeq;
    }
    public String getTyp(){return cbaTyp;}
    public String getAlikeWord(){return cbaAlikeWord;}
    public String getR1(){return cbaR1;}
    public String getR2(){return cbaR2;}
    public String getR3(){return cbaR3;}
    public String getR4(){return cbaR4;}
    public String getG1(){return cbaG1;}
    public String getG2(){return cbaG2;}
    public String getG3(){return cbaG3;}
    public String getG4(){return cbaG4;}
    public String getB1(){return cbaB1;}
    public String getB2(){return cbaB2;}
    public String getB3(){return cbaB3;}
    public String getB4(){return cbaB4;}
    /*public String getPhone() {
        return phone;
    }*/



    public void readAndParseJSON(String in) {
        try {
            Log.i("PJM","리드파셔시작");
            JSONArray reader = new JSONArray(in);
            Log.i("PJM","리드랭스");
            Log.i("PJM",reader.length()+"");
            for (int i=0; i<reader.length(); i++) {
                JSONObject obj = (JSONObject)reader.get(i);
                int cbaSeq = obj.getInt(getSeq());
                int cbaTyp = obj.getInt(getTyp());
                String cbaAlikeWord = obj.getString(getAlikeWord());
                int cbaR1 = 0;
                int cbaR2 = 0;
                int cbaR3 = 0;
                int cbaR4 = 0;
                int cbaG1 = 0;
                int cbaG2 = 0;
                int cbaG3 = 0;
                int cbaG4 = 0;
                int cbaB1 = 0;
                int cbaB2 = 0;
                int cbaB3 = 0;
                int cbaB4 = 0;

                Color ins =null;
                if(cbaTyp==1){
                    cbaR1 = obj.getInt(getR1());
                    cbaG1 = obj.getInt(getG1());
                    cbaB1 = obj.getInt(getB1());
                    ins=new Color(cbaSeq,cbaTyp,cbaAlikeWord,new RGB(cbaR1,cbaG1,cbaB1));
                }
                else if(cbaTyp==2){
                    cbaR1 = obj.getInt(getR1());
                    cbaR2 = obj.getInt(getR2());
                    cbaG1 = obj.getInt(getG1());
                    cbaG2 = obj.getInt(getG2());
                    cbaB1 = obj.getInt(getB1());
                    cbaB2 = obj.getInt(getB2());
                    ins=new Color(cbaSeq,cbaTyp,cbaAlikeWord,new RGB(cbaR1,cbaG1,cbaB1),new RGB(cbaR2,cbaG2,cbaB2));
                }
                else if(cbaTyp==3){
                    cbaR1 = obj.getInt(getR1());
                    cbaR2 = obj.getInt(getR2());
                    cbaR3 = obj.getInt(getR3());
                    cbaG1 = obj.getInt(getG1());
                    cbaG2 = obj.getInt(getG2());
                    cbaG3 = obj.getInt(getG3());
                    cbaB1 = obj.getInt(getB1());
                    cbaB2 = obj.getInt(getB2());
                    cbaB3 = obj.getInt(getB3());
                    ins=new Color(cbaSeq,cbaTyp,cbaAlikeWord,new RGB(cbaR1,cbaG1,cbaB1),new RGB(cbaR2,cbaG2,cbaB2),new RGB(cbaR3,cbaG3,cbaB3));
                }
                else if(cbaTyp==4){
                    cbaR1 = obj.getInt(getR1());
                    cbaR2 = obj.getInt(getR2());
                    cbaR3 = obj.getInt(getR3());
                    cbaR4 = obj.getInt(getR4());
                    cbaG1 = obj.getInt(getG1());
                    cbaG2 = obj.getInt(getG2());
                    cbaG3 = obj.getInt(getG3());
                    cbaG4 = obj.getInt(getG4());
                    cbaB1 = obj.getInt(getB1());
                    cbaB2 = obj.getInt(getB2());
                    cbaB3 = obj.getInt(getB3());
                    cbaB4 = obj.getInt(getB4());

                    ins=new Color(cbaSeq,cbaTyp,cbaAlikeWord,new RGB(cbaR1,cbaG1,cbaB1),new RGB(cbaR2,cbaG2,cbaB2),new RGB(cbaR3,cbaG3,cbaB3),new RGB(cbaR4,cbaG4,cbaB4));
                }


                arrList.add(ins);
            }
            Log.i("PJM","포문끝");
            parsingComplete = false;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public ArrayList<Color> fetchJSON() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    URL url = new URL(urlString);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setReadTimeout(10000 /* milliseconds */);
                    conn.setConnectTimeout(15000 /* milliseconds */);
                    conn.setRequestMethod("GET");
                    conn.setDoInput(true);
                    // Starts the query
                    conn.connect();
                    InputStream stream = conn.getInputStream();

                    String data = convertStreamToString(stream);

                    readAndParseJSON(data);
                    stream.close();
                    Log.i("PJM","리드파서끝");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }//쓰레드 기다리기

        return arrList;
    }

    static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}

