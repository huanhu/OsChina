package com.lerrycr.oschina.net;

import android.app.Activity;

import com.lerrycr.oschina.listener.OnResponseListener;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by Lerry on 2016/10/30.
 */

public class ApiHttpClient {
    private static OkHttpClient okHttpClient;

    public static void enqueue(final Activity activity, String url, final OnResponseListener listener) {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient();
        }

        final Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(final Request request, final IOException e) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        listener.onFailure(request, e);

                    }
                });
            }

            @Override
            public void onResponse(final Response response) throws IOException {
                final String result = response.body().string();
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            listener.onResponse(result);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                });
            }
        });

    }
}
