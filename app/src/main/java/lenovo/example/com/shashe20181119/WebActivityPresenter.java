package lenovo.example.com.shashe20181119;

import android.content.Context;
import android.webkit.WebView;

import lenovo.example.com.shashe20181119.mvp.view.AppDelegate;

/**
 * author：shashe
 * 日期：2018/11/20
 */
public class WebActivityPresenter extends AppDelegate{
    private WebView webView;
    private Context context;

    @Override
    public int getLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    public void initData() {
        webView.loadUrl("https://wapbaike.baidu.com/item/%E8%85%BE%E8%AE%AF/112204");
    }

    @Override
    public void initContext(Context context) {
        this.context=context;
    }

    public void initView(WebView webView) {
        this.webView=webView;
    }
}
