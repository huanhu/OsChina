package com.lerrycr.oschina.activity;

import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lerrycr.oschina.Constants;
import com.lerrycr.oschina.R;
import com.lerrycr.oschina.base.BaseActivity;
import com.lerrycr.oschina.utils.PreferenceUtils;
import com.lerrycr.oschina.utils.UiUtils;

import butterknife.Bind;
import butterknife.OnClick;

public class SaveIpActivity extends BaseActivity {


    @Bind(R.id.et_ip_address)
    EditText mEtIpAddress;
    @Bind(R.id.et_socket_address)
    EditText mEtSocketAddress;
    @Bind(R.id.btn_save_ip)
    Button mBtnSaveIp;
    @Bind(R.id.btn_clear_ip)
    Button mBtnClearIp;

    @Override
    protected void initView() {
        initActionBar();
    }

    /**
     * 初始化actionbar
     */
    private void initActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        //回显数据
        retrieveIpInfos();
    }

    /**
     * 回显保存的数据
     */
    private void retrieveIpInfos() {
        String ipInfos = PreferenceUtils.getString(this, Constants.IP_ADDRESS, "");
        if (ipInfos.length() > 0) {
            //重新显示到数据上去
            String[] ipAndSocket = ipInfos.split("#");
            String ip = ipAndSocket[0];
            String socket = ipAndSocket[1];
            //设置到屏幕上去
            mEtIpAddress.setText(ip);
            mEtSocketAddress.setText(socket);
        }
    }

    @Override
    protected Object getLayoutIdOrView() {
        return R.layout.activity_save_ip;
    }

    @OnClick({R.id.btn_save_ip, R.id.btn_clear_ip})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_save_ip:
                saveIpInfos();
                break;
            case R.id.btn_clear_ip:
                clearIpAddress();
                break;
        }
    }

    /**
     * 清空数据
     */
    private void clearIpAddress() {
        boolean isClear = PreferenceUtils.remove(this, Constants.IP_ADDRESS);
        if (isClear) {
            UiUtils.showToast("数据清除成功");
            mEtIpAddress.setText("");
            mEtSocketAddress.setText("");
        } else {
            UiUtils.showToast("数据清除失败");
        }
    }

    /**
     * 保存ipInfos
     */
    private void saveIpInfos() {
        String ipAddress = mEtIpAddress.getText().toString().trim();
        String socket = mEtSocketAddress.getText().toString().trim();
        if (TextUtils.isEmpty(ipAddress)) {
            UiUtils.showToast("ip地址不能为空");
            return;
        }

        if (TextUtils.isEmpty(socket)) {
            UiUtils.showToast("端口号不能为空");
            return;
        }

        //两个都有数字
        boolean isSuccess = PreferenceUtils.putString(this, Constants.IP_ADDRESS, ipAddress + "#" + socket);
        if (isSuccess) {
            UiUtils.showToast("保存成功");
            finish();
        } else {
            UiUtils.showToast("保存失败,请重试");
        }
    }


}
