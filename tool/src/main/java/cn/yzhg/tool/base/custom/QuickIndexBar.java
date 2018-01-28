package cn.yzhg.tool.base.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import cn.yzhg.tool.R;
import cn.yzhg.tool.tool.common.UiTool;

/**
 * author yzhg , time 2018/1/28.
 * <p>
 * aphorism:True hero is brave and useless, good scenery always in danger peak.
 * summarize(Please describe it in one sentence.)
 */

public class QuickIndexBar extends View {


    private String[] indexArr = {"A", "B", "C", "D", "E", "F", "G", "H",
            "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
            "V", "W", "X", "Y", "Z"};

    Handler handler = new Handler();

    /**
     * 定义两个颜色,白色和深灰色
     */
    private int COLOR_DEFAULT = UiTool.getColor(R.color.color_f63854);
    private int COLOR_PRESSED = UiTool.getColor(R.color.color_da1733);
    private Paint paint;
    private float gridHeight;  //每个格子的高度

    public QuickIndexBar(Context context) {
        this(context,null);
    }

    public QuickIndexBar(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public QuickIndexBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    /**
     * 此方法用于初始化ViewDragHelper
     */
    private void initView() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG); //设置抗锯齿
        paint.setColor(UiTool.getColor(R.color.color_f63854));
        //获取dimens中设置的字体大小
        paint.setTextSize(getResources().getDimensionPixelSize(R.dimen.sp_16));
        paint.setTextAlign(Paint.Align.CENTER);  //设置文字被绘制的起点为底边的中心
    }

    /**
     * 此方法在onMeasure方法之后执行,获取格子的宽高
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        //获得每个格子的高度
        gridHeight = (getMeasuredHeight() * 1f)  / indexArr.length;

    }

    /**
     * 绘制
     * @param canvas  画画板
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < indexArr.length; i++) {
            //x的宽度是,格子的一半
            float x = getMeasuredWidth() / 2;
            //y的高度 本格子的高度的一半(gridHeight / 2) + 本字体高度的一半 + 上几个个格子的高度(i * gridHeight)

            float y = (gridHeight / 2) + getTextHeight(indexArr[i]) / 2 + (i * gridHeight);
            //第一次手指按下时,绘制View,执行到invalidate();  又会绘制一次
            paint.setColor(i == currentIndex ? COLOR_PRESSED : COLOR_DEFAULT);
            canvas.drawText(indexArr[i],x,y,paint);
        }

    }

    private int currentIndex = -1;
    /**
     * 处理滑动事件
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                //计算出按下之处对应的格子
                int indexGrid = (int) (event.getY() / gridHeight);
                if(indexGrid != currentIndex){  //如果当前按下的字母是同一个
                    if(indexGrid >= 0 && indexGrid < indexArr.length){
                        String word = indexArr[indexGrid];
                        if(listener != null){
                            listener.OnWordListener(word);
                        }
                    }
                }
                currentIndex = indexGrid;

                break;
            case MotionEvent.ACTION_UP:
                currentIndex = -1;
                break;
        }
        invalidate();  //刷新onDraw
        return true;
    }

    //获取字体的高度
    private float getTextHeight(String s) {
        Rect bounds = new Rect();  //当前bounds还没有值
        paint.getTextBounds(s,0,s.length(),bounds);  //当此方法走完之后,bounds就有值
        return bounds.height();
    }
    private OnWordChangeListener listener;

    public void setOnWordChangeListener(OnWordChangeListener listener){
        this.listener = listener;
    }

    /**
     * 定义接口的回调
     */
    public interface OnWordChangeListener{
        void OnWordListener(String word);
    }
}
