# JSoup Example
In this project, we took words by parsing HTML on a page on Wikipedia through JSoup in Android Studio. Bu projede Android Studio'da JSOUP ile internetteki bir sayfadan(Wikipedia) HTML'i parçalayarak kelimeleri aldık.

### Libraries

Add These Libraries
```
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
```
### Dependency

Add This Dependency
```
implementation 'org.jsoup:jsoup:1.12.1'
```

### AndroidManifest.xml

Add This Manifest Line
```
 <uses-permission android:name="android.permission.INTERNET" />
```
 


### This is our target text.

![image](https://user-images.githubusercontent.com/9121424/71427424-829f9580-26c9-11ea-98fa-a8c372b7674a.png)

### That is our div.

![image](https://user-images.githubusercontent.com/9121424/71427455-dad69780-26c9-11ea-9170-684ca0f00ae6.png)


### Then we changed this area 

![image](https://user-images.githubusercontent.com/9121424/71427473-107b8080-26ca-11ea-873e-1dee7dd7ba5d.png)

After had got data splitted by 'space'
```
 arrOfWords = strDesc.split(" ", 50);
```

### Finally traversing array and show words.
![image](https://user-images.githubusercontent.com/9121424/71427536-d65eae80-26ca-11ea-874e-9dfb63e3d4f5.png)




### Codes

```
    private  class GetWordsFromWebSite extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
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
        }
    }
```



