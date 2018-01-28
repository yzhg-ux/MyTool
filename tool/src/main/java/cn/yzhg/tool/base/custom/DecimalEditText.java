package cn.yzhg.tool.base.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.AttributeSet;
import android.widget.EditText;

import cn.yzhg.tool.R;

/**
 * author yzhg , time 2018/1/28.
 * <p>
 * aphorism:True hero is brave and useless, good scenery always in danger peak.
 * summarize(自定义EditText,控制金额输入)
 */

public class DecimalEditText extends EditText{


    private static final int DEFAULT_DECIMAL_NUMBER = 2;
    /**
     * 保留小数点后多少位
     */
    private int mDecimalNumber = DEFAULT_DECIMAL_NUMBER;


    public DecimalEditText(Context context) {
        this(context,null, R.attr.editTextStyle);
    }

    public DecimalEditText(Context context, AttributeSet attrs) {
        this(context,attrs, R.attr.editTextStyle);
    }

    public DecimalEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DecimalEditText);
        mDecimalNumber = typedArray.getInt(R.styleable.DecimalEditText_decimalNumber,DEFAULT_DECIMAL_NUMBER);
        typedArray.recycle();

        init();
    }

    private void init(){
        setFilters(new InputFilter[]{new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                String lastInputContent = dest.toString();

                if (source.equals(".") && lastInputContent.length() == 0) {
                    return "0.";
                }
                if (lastInputContent.contains(".")) {
                    int index = lastInputContent.indexOf(".");
                    if(dend - index >= mDecimalNumber + 1){
                        return "";
                    }
                }
                return null;
            }
        }});
    }

    public int getDecimalNumber() {
        return mDecimalNumber;
    }

    public void setDecimalNumber(int decimalNumber) {
        mDecimalNumber = decimalNumber;
    }



}
