package com.github.captain_miao.databinding.tutorial.recycleview;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.github.captain_miao.databinding.tutorial.R;
import com.github.captain_miao.databinding.tutorial.base.BaseActivity;
import com.github.captain_miao.databinding.tutorial.helper.MockRandomData;
import com.github.captain_miao.recyclerviewutils.WrapperRecyclerView;
import com.github.captain_miao.recyclerviewutils.listener.RefreshRecyclerViewListener;

public class RecycleViewActivity extends BaseActivity implements RefreshRecyclerViewListener {
    private static final String TAG = "RecycleViewActivity";

    private WrapperRecyclerView mRefreshRecyclerView;
    @Override
    public void init(Bundle savedInstanceState) {
        setContentView(R.layout.activity_recycle_view);
        mRefreshRecyclerView = (WrapperRecyclerView) findViewById(R.id.refresh_recycler_view);

        mRefreshRecyclerView.setAdapter(new VehicleListAdapter(MockRandomData.getVehicleInfos()));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRefreshRecyclerView.setLayoutManager(linearLayoutManager);
        mRefreshRecyclerView.setRecyclerViewListener(this);
        mRefreshRecyclerView.disableLoadMore();
        mRefreshRecyclerView.setPadding(0, 0, 0, 20);
    }


    @Override
    public void onRefresh() {
        mRefreshRecyclerView.getRecyclerView().getAdapter().notifyDataSetChanged();
        mRefreshRecyclerView.refreshComplete();
    }

    @Override
    public void onLoadMore(int pagination, int pageSize) {

    }
}