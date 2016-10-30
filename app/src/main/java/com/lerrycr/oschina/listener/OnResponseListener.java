package com.lerrycr.oschina.listener;

import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by Lerry on 2016/10/30.
 */
public interface OnResponseListener {
    void onFailure(Request request, IOException e);

    void onResponse(String response) throws IOException;
}
