package com.bsBilal.jsoup_example;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MainActivity extends AppCompatActivity {
    String[] arrOfWords=new String[50];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new GetWordsFromWebSite().execute();
    }

    private  class GetWordsFromWebSite extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog.setTitle("Bilgilendirme");
            progressDialog.setMessage("Yeni kelimeler alınıyor...");
            progressDialog.setIndeterminate(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            GetWordsandParse();


            return null;
        }

        //get words on website
        private void GetWordsandParse() {
            try{
                String newURL="https://en.wikipedia.org/wiki/Rosika_Schwimmer";
                System.out.println(newURL);
                Document doc  = Jsoup.connect(newURL).get();
                Elements trs = doc.select("div.mw-parser-output");
                for (Element tr : trs) {
                    String strDesc = tr.text();
                    arrOfWords = strDesc.split(" ", 50);
                }


            }catch (Exception e){

                e.printStackTrace();
            }

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            int i=0;
            for(String word: arrOfWords)
                System.out.println(i++ +".- "+word);
            progressDialog.cancel();
        }


    }

}
