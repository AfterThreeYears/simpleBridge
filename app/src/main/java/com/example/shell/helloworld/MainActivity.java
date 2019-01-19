package com.example.shell.helloworld;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init() {
        //final String url = "file:///android_asset/index.html";
      final String url = "https://www.baidu.com";
        webView = findViewById(R.id.web_view);
        webView.loadUrl(url);

//        webView.setWebViewClient(new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//                Log.d("shouldOverrideUrlLoading", "shouldOverrideUrlLoading: " + request.toString());
//                view.loadUrl(request.toString());
//                return true;
//            }
//        });


        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

//        webView.setWebViewClient(new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//                Uri u = Uri.parse(request.getUrl().toString());
//                if (u.getScheme().equals("zaihu") && u.getAuthority().equals("invoke")) {
//                    // 查询到func: open， cbId: xxx, data: xxx,执行完毕以后
//                    String func = u.getQueryParameter("func");
//                    String cbId = u.getQueryParameter("cbId");
//                    String data = u.getQueryParameter("data");
//                    switch (func) {
//                        case "showToast":
//                            showToast(cbId, data);
//                            break;
//                        case "openNativePage":
//                            openNativePage(cbId, data);
//                            break;
//                        default:
//                            return false;
//                    }
//                }
//                return true;
//            }
//        });
    }

    public void showToast(String cbId, String data) {
        Toast.makeText(MainActivity.this, data, Toast.LENGTH_LONG).show();
        if (cbId != null) {
            execJS(cbId, data);
        }
    }

    public void openNativePage(String cbId, String data) {
        Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
        MainActivity.this.startActivity(intent);
        if (cbId != null) {
            execJS(cbId, data);
        }
    }

    public void execJS(String cbId, String msg) {
        webView.loadUrl("javascript:JSBridge._handlerFromZaihu({cbId:'"+cbId+"',msg:"+ msg+"})");
    }
}
