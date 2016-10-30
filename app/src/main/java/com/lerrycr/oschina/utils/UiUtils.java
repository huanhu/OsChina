package com.lerrycr.oschina.utils;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lerrycr.oschina.MyApp;

import java.util.Random;

/**
 * Created by dzl on 2016/10/13.
 */

public class UiUtils {

    /**
     * 在屏幕的中间显示一个Toast
     *
     * @param text
     */
    public static void showToast(CharSequence text) {
        Toast toast = Toast.makeText(MyApp.getContext(), text, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0); // 设置让toast在屏幕居中显示
        toast.show();
    }


    /**
     * 把dp单的值转换为px单位的值
     *
     * @param dp
     * @return
     */
    public static int dp2px(int dp) {
        float density = MyApp.getContext().getResources().getDisplayMetrics().density;  // 获取手机屏幕的密度
        int px = (int) (dp * density);
        return px;
    }

    /**
     * 创建随机颜色
     */
    public static int createRandomColor() {
        Random random = new Random();
        int red = 50 + random.nextInt(150);     // 50 ~ 199
        int green = 50 + random.nextInt(150);   // 50 ~ 199
        int blue = 50 + random.nextInt(150);    // 50 ~ 199
        int color = Color.rgb(red, green, blue);
        return color;
    }

    /**
     * 创建带随机颜色选择器的TextView
     */
    public static TextView createRandomColorSelectorTextView() {
        final TextView textView = new TextView(MyApp.getContext());
        textView.setTextColor(Color.WHITE);
        textView.setGravity(Gravity.CENTER);
        int padding = dp2px(6);
        textView.setPadding(padding, padding, padding, padding);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast(textView.getText());
            }
        });

        // 设置TextView背景
        textView.setBackgroundDrawable(createRandomColorSelector());
        return textView;
    }

    /**
     * 创建带随机颜色的选择器
     */
    private static Drawable createRandomColorSelector() {
        StateListDrawable stateListDrawable = new StateListDrawable();  // 创建一个Selector类型的Drawable
        int[] pressState = {android.R.attr.state_pressed, android.R.attr.state_enabled};    // 代码中的按下状态必须加上enable
        Drawable pressDrawable = createRandomColorDrawable();
        stateListDrawable.addState(pressState, pressDrawable);  // 设置按下状态要显示的Drawable

        int[] normalState = {};
        Drawable normalDrawable = createRandomColorDrawable();
        stateListDrawable.addState(normalState, normalDrawable);// 设置正常状态要显示的Drawable

        return stateListDrawable;
    }

    /**
     * 创建带随机颜色的圆角矩形的Drawable
     */
    private static Drawable createRandomColorDrawable() {
        GradientDrawable gradientDrawable = new GradientDrawable(); // 创建一个图形Drawable
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);// 形状为矩形
        gradientDrawable.setCornerRadius(dp2px(6));           // 圆角
        gradientDrawable.setColor(createRandomColor());       // 颜色
        return gradientDrawable;
    }
}
