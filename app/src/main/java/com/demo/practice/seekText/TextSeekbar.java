package com.demo.practice.seekText;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;

public class TextSeekbar extends androidx.appcompat.widget.AppCompatSeekBar {

    private Paint textPaint;


    public TextSeekbar(Context context) {
        this(context,null);
    }

    public TextSeekbar(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TextSeekbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        textPaint = new Paint();
        textPaint.setTextAlign(Paint.Align.CENTER);
        int textSize = Utils.dp2px(context, 14);
        textPaint.setTextSize(textSize);
        textPaint.setAntiAlias(true);
        textPaint.setColor(Color.parseColor("#545454"));

    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float top = fontMetrics.top;//为基线到字体上边框的距离,即上图中的top
        Log.e("TAG", "top--> "+top);
        Log.e("TAG", "ascent--> "+fontMetrics.ascent);
        float bottom = fontMetrics.bottom;//为基线到字体下边框的距离,即上图中的bottom
        Log.e("TAG", "bottom--> "+bottom);
        Log.e("TAG", "descent--> "+fontMetrics.descent);
        Log.e("TAG", "top / 2 +bottom / 2--> "+(top / 2 + bottom / 2));
        Log.e("TAG", "getHeight() / 2--> "+getHeight() / 2);
        // 直观写法
        // int baseLineY = (int) (getHeight() / 2 + ((bottom-top)/2 - bottom));//基线中间点的y轴计算公式
        // 去括号换算后写法
        int baseLineY = (int) (getHeight() / 2 - top / 2 - bottom / 2);//基线中间点的y轴计算公式
        Log.e("TAG", "baseLineY--> "+baseLineY);
        canvas.drawText("向右滑动完成", getWidth() / 2, baseLineY, textPaint);
    }

    public static class Utils {

        public static int dp2px(Context ctx, float dip) {
            float density = ctx.getResources().getDisplayMetrics().density;
            return (int) (dip * density);
        }
    }
}
