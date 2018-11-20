package com.placement.pesu.pesuplacement;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import android.annotation.TargetApi;
public class FormLoaderWebView extends Activity {

    private WebView mWebview ;
    public String company_name;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        company_name=getIntent().getStringExtra("company_name");
        super.onCreate(savedInstanceState);

        mWebview  = new WebView(this);

        mWebview.getSettings().setJavaScriptEnabled(true); // enable javascript

        final Activity activity = this;

        mWebview.setWebViewClient(new WebViewClient() {
            @SuppressWarnings("deprecation")
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(activity, description, Toast.LENGTH_SHORT).show();
            }
            @TargetApi(android.os.Build.VERSION_CODES.M)
            @Override
            public void onReceivedError(WebView view, WebResourceRequest req, WebResourceError rerr) {
                // Redirect to deprecated method, so you can use it in all SDK versions
                //onReceivedError(view, rerr.getErrorCode(), rerr.getDescription().toString(), req.getUrl().toString());
                finish();
            }
        });


                /*
        String url = "https://sramako.github.io/formclient.html?company="+company_name;
        mWebview.loadUrl(url);
        */

        mWebview.loadUrl("https://sramako.github.io/formclient.html?company="+company_name+"&usn="+LoginActivity.USN);
        setContentView(mWebview );


    }




}
