package lenovo.example.com.shashe20181119;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnClick;
import lenovo.example.com.shashe20181119.mvp.presenter.BaseActivityPresenter;

public class MainActivity extends BaseActivityPresenter<MainActivityPresenter> {

    @BindView(R.id.recycler)
    RecyclerView mRecyclerView;
    @BindView(R.id.checkbox)
    CheckBox mCheckBox;
    @BindViews({R.id.desc, R.id.sum_price_txt, R.id.all_price})
    List<TextView> mTextViews;

    @Override
    public Class<MainActivityPresenter> getClassDelegate() {
        return MainActivityPresenter.class;
    }

    @Override
    public void initView() {
        super.initView();
        delegate.initView(mRecyclerView, mCheckBox, mTextViews);
    }

    private boolean isClick = true;

    @OnClick({R.id.desc, R.id.layout_all})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.desc:
                delegate.startItem();
                break;
            case R.id.layout_all:
                if (isClick){
                    delegate.allCars(true);
                    mCheckBox.setChecked(true);
                    isClick=false;
                }else {
                    delegate.allCars(false);
                    mCheckBox.setChecked(false);
                    isClick=true;
                }
                break;
        }
    }
}
