package com.example.administrator.myrecycleviw;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import butterknife.Bind;

public class MainActivity extends BaseActivity<MainView, MainPresenter> implements MainView,SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.RequestLoadMoreListener {

    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    @Bind(R.id.rv_content)
    RecyclerView rvContent;
    private ArticleListAdapter mAdapter;

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        super.initView();
        rvContent.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ArticleListAdapter(this, null);
        rvContent.setAdapter(mAdapter);
        swipeRefresh.setOnRefreshListener(this);
        mAdapter.setOnLoadMoreListener(this, rvContent);
        mPresenter.requestData();
        showRefreshView(true);



    }


    @Override
    public void getSuccess(List<ArticleBean> data) {
        mAdapter.setNewData(data);
        showRefreshView(false);
    }

    @Override
    public void getMoreSuccess(List<ArticleBean> data) {
        if (data.size() != 0) {
            mAdapter.addData(data);
            mAdapter.loadMoreComplete();
        } else {
            mAdapter.loadMoreEnd();
        }
    }

    @Override
    public void getError(String msg) {
        showRefreshView(false);
    }

    @Override
    public void onRefresh() {
        mPresenter.requestData();
    }

    @Override
    public void onLoadMoreRequested() {
        mPresenter.requestMoreData();
    }



    public void showRefreshView(final Boolean refresh) {
        swipeRefresh.post(new Runnable() {
            @Override
            public void run() {
                swipeRefresh.setRefreshing(refresh);
            }
        });
    }
}
