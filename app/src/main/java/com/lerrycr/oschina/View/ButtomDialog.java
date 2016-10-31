package com.lerrycr.oschina.View;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.lerrycr.oschina.R;
import com.lerrycr.oschina.activity.dialogactivity.DialogAlbumActivity;
import com.lerrycr.oschina.activity.dialogactivity.DialogPhotoActivity;
import com.lerrycr.oschina.activity.dialogactivity.DialogTextActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Lerry on 2016/10/28.
 */

public class ButtomDialog extends Dialog {
    @Bind(R.id.btn_dialog_text)
    Button mBtnDialogText;
    @Bind(R.id.btn_dialog_album)
    Button mBtnDialogAlbum;
    @Bind(R.id.btn_dialog_photo)
    Button mBtnDialogPhoto;
    @Bind(R.id.rl_dialog_exit)
    RelativeLayout mRlDialogExit;

    public ButtomDialog(Context context) {
        // 通过构造方法 传进去一个自定义的样式
        super(context, R.style.CenterDialogStyle);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.buttom_dialog);
        ButterKnife.bind(this);

        // 更改显示位置
        Window window = getWindow();
        int width = getWindow().getWindowManager().getDefaultDisplay().getWidth();
        // 获取布局参数
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = width;
        // 调整位置在最下方 并且左右居中
        params.gravity = Gravity.BOTTOM;
        // 更新布局参数
        window.setAttributes(params);
    }

    @OnClick({R.id.btn_dialog_text, R.id.btn_dialog_album, R.id.btn_dialog_photo, R.id.rl_dialog_exit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_dialog_text:
                //打开textactivity
                startDialogTextActivity();
                break;
            case R.id.btn_dialog_album:
                //打开albumactivity
                startDialogAlbumActivity();
                break;
            case R.id.btn_dialog_photo:
                //打开photoactivity
                startDialogPhotoActivity();
                break;
            case R.id.rl_dialog_exit:
                break;
        }
        dismiss();
    }

    private void startDialogPhotoActivity() {
        Intent intent = new Intent(getContext(), DialogPhotoActivity.class);
        getContext().startActivity(intent);
    }

    private void startDialogAlbumActivity() {
        Intent intent = new Intent(getContext(), DialogAlbumActivity.class);
        getContext().startActivity(intent);
    }

    private void startDialogTextActivity() {
        Intent intent = new Intent(getContext(), DialogTextActivity.class);
        getContext().startActivity(intent);
    }
}
