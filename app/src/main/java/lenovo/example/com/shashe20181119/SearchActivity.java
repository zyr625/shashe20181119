package lenovo.example.com.shashe20181119;



import android.widget.SearchView;

import butterknife.BindView;
import lenovo.example.com.shashe20181119.mvp.presenter.BaseActivityPresenter;

public class SearchActivity extends BaseActivityPresenter<SearchActivityPresenter> {

    @BindView(R.id.search)
    SearchView searchView;
    @BindView(R.id.self)
    SelfView selfView;

    @Override
    public Class<SearchActivityPresenter> getClassDelegate() {
        return SearchActivityPresenter.class;
    }

    @Override
    public void initView() {
        super.initView();
        delegate.initView(searchView, selfView);
    }
}
