package com.example.administrator.myrecycleviw;

import java.util.List;

/**
 * Created by Administrator on 2018/5/30.
 */

public interface MainView {

    public void getSuccess(List<ArticleBean> data);
    public void getMoreSuccess(List<ArticleBean> data);
    public void getError(String msg);

}
