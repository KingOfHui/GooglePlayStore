package com.google.googleplaystore.fragment;

import android.os.SystemClock;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.google.googleplaystore.base.BaseFragment;
import com.google.googleplaystore.base.ItemAdapter;
import com.google.googleplaystore.base.LoadingPagerController;
import com.google.googleplaystore.bean.HomeBean;
import com.google.googleplaystore.factory.ListViewFactory;
import com.google.googleplaystore.protocol.AppProtocol;

import java.util.List;

/**
 * Created by DH on 2017/7/8.
 */

public class AppFragment extends BaseFragment {

    private AppProtocol mProtocol;
    private List<HomeBean.ListBean> mDatas;

    @Override
    protected LoadingPagerController.LoadedResult initData() {
        mProtocol = new AppProtocol();
        try {
            mDatas = mProtocol.loadData(0);
            return checkResult(mDatas);
        } catch (Exception e) {
            e.printStackTrace();
            return LoadingPagerController.LoadedResult.ERROR;
        }
    }

    @Override
    public View initSuccessView() {
        ListView listView = ListViewFactory.createListView();
        listView.setAdapter(new AppAdapter(mDatas,listView));
        return listView;
    }
    class AppAdapter extends ItemAdapter{

        public AppAdapter(List<HomeBean.ListBean> datas, AbsListView absListView) {
            super(datas, absListView);
        }

        @Override
        public List onLoadMore() throws Exception {
            SystemClock.sleep(1000);
            List<HomeBean.ListBean> listBeens = mProtocol.loadData(mDatas.size());
            return listBeens;
        }
    }
}
