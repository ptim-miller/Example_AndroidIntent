package edu.myschool.java.search;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        final EditText textURL = (EditText)findViewById(R.id.textURL);
        final WebView webview = (WebView)findViewById(R.id.webview);


        if(getIntent().hasExtra("edu.myschool.java.newssearch")){
            webview.setWebViewClient(new InternalAppBrowser());
            WebSettings webSettings = webview.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webSettings.setDomStorageEnabled(true);
            webSettings.setLoadsImagesAutomatically(true);
            webSettings.setLoadWithOverviewMode(true);
            webSettings.setUseWideViewPort(true);
            webSettings.setBuiltInZoomControls(true);

            String urlSent = getIntent().getExtras().getString("edu.myschool.java.newssearch");
            textURL.setText(urlSent);
            webview.loadUrl(urlSent);
        }

        Button btnGo = (Button)findViewById(R.id.btnGo);
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String contents = textURL.getText().toString();
                if(contents.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please enter an address" ,Toast.LENGTH_LONG).show();
                    return;
                }

                StringBuilder url = new StringBuilder(contents.toLowerCase());
                if(!url.toString().startsWith("https://") && (!url.toString().startsWith("http://")) ){
                    url.insert(0, "https://");
                    textURL.setText(url.toString());
                }
                try {
                    webview.loadUrl(url.toString());
                    InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),"Something went wrong!" ,Toast.LENGTH_LONG).show();
                }


            }
        });

        Button btnBack = (Button)findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(webview.canGoBack()) {
                    webview.goBack();
                }
            }
        });

        Button btnForward = (Button)findViewById(R.id.btnForward);
        btnForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(webview.canGoForward()) {
                    webview.goForward();
                }
            }
        });

    }

    private class InternalAppBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

}
