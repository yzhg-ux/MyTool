package cn.yzhg.tool.base.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import cn.yzhg.tool.R;

/**
 * author yzhg , time 2018/1/28.
 * <p>
 * aphorism:True hero is brave and useless, good scenery always in danger peak.
 * summarize(Please describe it in one sentence.)
 */

public class RefreshListView extends ListView implements AbsListView.OnScrollListener {

    private View initFootView;
    private int measuredHeightFoot;
    private boolean isScrollToBottom;
    private boolean isLoadingMore = false;

    /**
     * 定义三种状态
     */
    private final int DOWN_PULL = 0;
    private final int RELEASE_REFRESH = 1;
    private final int REFRESHING = 2;

    private int currentState = DOWN_PULL;
    private int downY = -1;
    // private int firstVisiblePosition;

    public RefreshListView(Context context) {
        super(context);

        initFootView();
        setOnScrollListener(this);
    }

    public RefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initFootView();
        setOnScrollListener(this);
    }


    public RefreshListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initFootView();
        setOnScrollListener(this);
    }

    private void initFootView() {
        initFootView = View.inflate(getContext(), R.layout.init_foot_view, null);
        initFootView.measure(0, 0);
        measuredHeightFoot = initFootView.getMeasuredHeight();
        initFootView.setPadding(0, -measuredHeightFoot, 0, 0);
        this.addFooterView(initFootView);
    }

    public void OnViewSucceed() {
        if (isLoadingMore) {
            initFootView.setPadding(0, -measuredHeightFoot, 0, 0);
            isLoadingMore = false;
        }
    }

    /**
     * 滚动时状态的改变
     *
     * @param view
     * @param scrollState
     */
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if ((scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE	// 当前是停滞或者是快速滑动时
                || scrollState == AbsListView.OnScrollListener.SCROLL_STATE_FLING)
                && getLastVisiblePosition() == (getCount() -1)
                && !isLoadingMore) {

            isLoadingMore = true;
            initFootView.setPadding(0, 0, 0, 0);
            this.setSelection(this.getCount());

            if (listener != null) {
                listener.onLoadingMore();
            }
        }
    }

    /**
     * 滚动时调用的方法
     *
     * @param view
     * @param firstVisibleItem 屏幕显示在顶部的item的position
     * @param visibleItemCount 屏幕显示了多少条目的总数
     * @param totalItemCount   ListView总条目
     */
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int
            totalItemCount) {
    }


    private onRefreshListener listener;

    /**
     * 实现接口回调
     */
    public void setOnRefreshListener(onRefreshListener listener) {
        this.listener = listener;
    }


    //创建接口回调
    public interface onRefreshListener {
        void onLoadingMore();
    }
}



