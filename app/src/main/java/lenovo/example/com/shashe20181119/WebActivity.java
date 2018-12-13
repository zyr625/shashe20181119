package lenovo.example.com.shashe20181119;

import android.webkit.WebView;

import butterknife.BindView;
import lenovo.example.com.shashe20181119.mvp.presenter.BaseActivityPresenter;

public class WebActivity extends BaseActivityPresenter<WebActivityPresenter> {

    @BindView(R.id.webview)
    WebView webView;
    @Override
    public Class<WebActivityPresenter> getClassDelegate() {
        return WebActivityPresenter.class;
    }

    @Override
    public void initView() {
        super.initView();
        delegate.initView(webView);
    }
}
