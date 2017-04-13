package cn.shper.pan.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.shper.pan.commons.util.Logger;

/**
 * Author: Shper
 * Description: MVP框架 Fragment 基础类,工程中所有 Fragment 必须继承此类
 * Version: V0.1 2016/12/28
 */
public abstract class MVPFragment<P extends MVPFragmentPresenter> extends Fragment {

    private P mPresenter;
    private View rootView;
    private Unbinder mButterKnifeBinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Logger.d(this.getClass().getName());
        if (rootView == null) {
            // 分解 onCreateView 使其更符合 单一职能原则
            rootView = inflater.inflate(getLayoutId(), null);
            // 初始化view
            mButterKnifeBinder = ButterKnife.bind(this, rootView);
            // 初始化变量
            initVariables(rootView, container, savedInstanceState);
            // 初始化监听
            initListeners();
            // 初始化P层
            mPresenter = initPresenter();
            if (null != mPresenter) {
                Logger.i("Presenter: " + mPresenter.getClass().getName());
                mPresenter.onCreateView();
            }
        }

        return rootView;
    }

    protected abstract int getLayoutId();

    protected abstract void initVariables(View rootView, ViewGroup container, @Nullable Bundle savedInstanceState);

    protected abstract void initListeners();

    protected abstract P initPresenter();

    @Override
    public void onStart() {
        Logger.d(this.getClass().getName());
        super.onStart();

        if (null != mPresenter) {
            Logger.i("Presenter: " + mPresenter.getClass().getName());
            mPresenter.onStart();
        }
    }

    @Override
    public void onResume() {
        Logger.d(this.getClass().getName());
        super.onResume();

        if (null != mPresenter) {
            Logger.i("Presenter: " + mPresenter.getClass().getName());
            mPresenter.onResume();
        }
    }

    @Override
    public void onPause() {
        Logger.d(this.getClass().getName());
        super.onPause();

        if (null != mPresenter) {
            Logger.i("Presenter: " + mPresenter.getClass().getName());
            mPresenter.onPause();
        }
    }

    @Override
    public void onStop() {
        Logger.d(this.getClass().getName());
        super.onStop();

        if (null != mPresenter) {
            Logger.i("Presenter: " + mPresenter.getClass().getName());
            mPresenter.onStop();
        }
    }

    @Override
    public void onDestroyView() {
        Logger.d(this.getClass().getName());
        super.onDestroyView();

        if (null != mPresenter) {
            Logger.i("Presenter: " + mPresenter.getClass().getName());
            mPresenter.onDestroyView();
        }
    }

    @Override
    public void onDestroy() {
        Logger.d(this.getClass().getName());
        if (null != mPresenter) {
            Logger.d("MVPFragment.onDestroyView: ", mPresenter.getClass().getName());
            mPresenter.onDestroy();
            mPresenter = null;
        }
        mButterKnifeBinder.unbind();
        rootView = null;

        super.onDestroy();
    }

    public P getPresenter() {
        Logger.d("Presenter: ", null != mPresenter ? mPresenter.getClass().getName() : "null");
        return mPresenter;
    }

    static {
        Logger.initialization("MVPPan");
        if (BuildConfig.DEBUG) {
            Logger.openDebug();
        }
    }

}