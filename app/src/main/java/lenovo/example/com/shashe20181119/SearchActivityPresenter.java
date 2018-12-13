package lenovo.example.com.shashe20181119;

import android.content.Context;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

import lenovo.example.com.shashe20181119.mvp.view.AppDelegate;

/**
 * author：shashe
 * 日期：2018/11/19
 */
public class SearchActivityPresenter extends AppDelegate {
    private SearchView searchView;
    private SelfView selfView;
    private Context context;
    private List<String> list = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    public void initData() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if (s.length() > 0) {
                    searchView.setIconified(true);
                }
                list.add(0, s);
                selfView.setListData(list);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                return false;
            }
        });
    }

    @Override
    public void initContext(Context context) {
        this.context = context;
    }

    public void initView(SearchView searchView, SelfView selfView) {
        this.searchView = searchView;
        this.selfView = selfView;
    }
}
