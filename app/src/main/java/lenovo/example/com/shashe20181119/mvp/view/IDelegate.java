package lenovo.example.com.shashe20181119.mvp.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * author：shashe
 * 日期：2018/11/19
 */
public interface IDelegate {
    void initData();

    View getRootView();

    void create(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle);

    void initContext(Context context);
}
