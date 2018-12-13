package lenovo.example.com.shashe20181119;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lenovo.example.com.shashe20181119.adapter.CarsAdapter;
import lenovo.example.com.shashe20181119.model.CarsBean;
import lenovo.example.com.shashe20181119.mvp.view.AppDelegate;
import lenovo.example.com.shashe20181119.util.HttpHelper;
import lenovo.example.com.shashe20181119.util.HttpListener;

/**
 * author：shashe
 * 日期：2018/11/19
 */
public class MainActivityPresenter extends AppDelegate {
    private RecyclerView mRecyclerView;
    private CheckBox mCheckBox;
    private List<TextView> mTextViews;
    private Context context;
    private CarsAdapter carsAdapter;
    private List<CarsBean.DataBean> dataBean = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {
        carsAdapter = new CarsAdapter(context);
        LinearLayoutManager mlinearLayoutManager = new LinearLayoutManager(context);
        mlinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mlinearLayoutManager);
        mRecyclerView.setAdapter(carsAdapter);
        carsAdapter.setCallBackListener(new CarsAdapter.CallBackListener() {
            @Override
            public void callBack(List<CarsBean.DataBean> list) {
                dataBean = list;
                double allPrice = 0;
                int allNum = 0;
                int num = 0;
                for (int i = 0; i < list.size(); i++) {
                    List<CarsBean.DataBean.ListBean> listBeans = list.get(i).getList();
                    for (int j = 0; j < listBeans.size(); j++) {
                        num++;
                        if (listBeans.get(j).isCheck()) {
                            allNum = allNum + listBeans.get(j).getNum();
                            allPrice = allPrice + (listBeans.get(j).getPrice() * listBeans.get(j).getNum());
                        }
                    }
                }
                if (num == allNum) {//全选
                    mCheckBox.setChecked(true);
                } else {
                    mCheckBox.setChecked(false);
                }
                mTextViews.get(2).setText("合计：" + String.format("%.2f", allPrice));
                mTextViews.get(1).setText("去结算(" + allNum + ")");
            }
        });
        doHttp();
    }

    private void doHttp() {
        Map<String, String> map = new HashMap<>();
        map.put("uid", "71");
        new HttpHelper().get("/product/getCarts", map).result(new HttpListener() {
            @Override
            public void success(String data) {
                CarsBean carsBean = new Gson().fromJson(data, CarsBean.class);
                dataBean = carsBean.getData();
                dataBean.remove(0);
                carsAdapter.setList(dataBean);
            }

            @Override
            public void fail(String error) {

            }
        });
    }

    @Override
    public void initContext(Context context) {
        this.context = context;
    }

    public void initView(RecyclerView mRecyclerView, CheckBox mCheckBox, List<TextView> mTextViews) {
        this.mRecyclerView = mRecyclerView;
        this.mCheckBox = mCheckBox;
        this.mTextViews = mTextViews;
    }

    //编辑
    public void startItem() {
        context.startActivity(new Intent(context,SearchActivity.class));
    }

    //全选反选
    private double allPrice;
    private int allNum;

    public void allCars(boolean b) {
        allPrice = 0.0;
        allNum = 0;
        for (int i = 0; i < dataBean.size(); i++) {
            dataBean.get(i).setCheck(b);
            List<CarsBean.DataBean.ListBean> list = dataBean.get(i).getList();
            for (int j = 0; j < list.size(); j++) {
                allNum++;
                list.get(j).setCheck(b);
                allPrice = allPrice + (list.get(j).getPrice() * list.get(j).getNum());
            }
        }
        carsAdapter.setList(dataBean);
        if (!b) {
            allPrice = 0.0;
            allNum = 0;
        }
        mTextViews.get(2).setText("合计：" + String.format("%.2f", allPrice));
        mTextViews.get(1).setText("去结算(" + allNum + ")");
    }
}
