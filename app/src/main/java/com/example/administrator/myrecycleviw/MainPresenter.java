package com.example.administrator.myrecycleviw;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.List;

/**
 * Created by Administrator on 2018/5/30.
 */

public class MainPresenter extends BasePresenter<MainView> {
    private int pagerNum;

    public void requestData() {
        pagerNum = 0;
        OkGo.<String>get("http://www.wanandroid.com/article/list/0/json")//
                .tag(this)//
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson=new Gson();
                        DemoBean bean=  gson.fromJson(response.body(),DemoBean.class);
                        List<ArticleBean> list=bean.getData().getDatas();

                        getView().getSuccess(list);
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);

                    }
                });
    }

    public void requestMoreData() {
        pagerNum = pagerNum + 1;
        OkGo.<String>get("http://www.wanandroid.com/article/list/" + pagerNum + "/json")//
                .tag(this)//
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        Gson gson=new Gson();
                        DemoBean bean=  gson.fromJson(response.body(),DemoBean.class);
                        List<ArticleBean> list=bean.getData().getDatas();
                        getView().getMoreSuccess(list);
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);

                    }
                });
    }

}
