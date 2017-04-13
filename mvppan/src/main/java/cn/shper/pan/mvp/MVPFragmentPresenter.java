package cn.shper.pan.mvp;

import cn.shper.pan.commons.util.Logger;
import cn.shper.pan.mvp.exception.MVPNotBindException;

/**
 * Author: Shper
 * Description: MVP框架 Fragment 的 Presenter基础类，
 * 工程中所有 Fragment 的 Presenter 必须继承此类
 *
 * Version: V0.1 2016/12/28
 */
public abstract class MVPFragmentPresenter<F extends MVPFragment> {

    private F mFragment;

    public MVPFragmentPresenter(F fragment) {
        // 绑定 View
        this.mFragment = fragment;
        Logger.d("attachView: ", null != mFragment ? mFragment.getClass().getName() : "null");
    }

    /**
     * 子类根据 具体业务实现此方法
     */
    protected abstract void onCreateView();

    public void onStart() {
        Logger.d(this.getClass().getName());
    }

    public void onResume() {
        Logger.d(this.getClass().getName());
    }

    public void onPause() {
        Logger.d(this.getClass().getName());
    }

    public void onStop() {
        Logger.d(this.getClass().getName());
    }

    public void onDestroyView() {
        Logger.d(this.getClass().getName());
    }

    /**
     * 解除全部绑定
     */
    protected void onDestroy() {
        Logger.d("detachView: ", null != mFragment ? mFragment.getClass().getName() : "null");
        mFragment = null;
    }

    /**
     * 检测是否 View 已经绑定
     */
    public boolean isFragmentBind() {
        return mFragment != null;
    }

    /**
     * 获取绑定的 View
     */
    public F getFragment() {
        return mFragment;
    }

    /**
     * 检测是否已经 View 绑定，如未绑定抛出 MVPViewNotAttachedException
     */
    public void checkFragmentBind() {
        if (!isFragmentBind()) {
            throw new MVPNotBindException("View Not Attach");
        }
    }

    static {
        Logger.initialization("MVPPan");
        if (BuildConfig.DEBUG) {
            Logger.openDebug();
        }
    }

}
