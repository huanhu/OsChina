package com.lerrycr.oschina.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;

import com.lerrycr.oschina.R;
import com.lerrycr.oschina.base.BaseActivity;
import com.lerrycr.oschina.bean.Blog;
import com.lerrycr.oschina.bean.BlogDetail;
import com.lerrycr.oschina.bean.News;
import com.lerrycr.oschina.bean.NewsDetail;
import com.lerrycr.oschina.fragment.NewsDetailFragment.BlogFragment;
import com.lerrycr.oschina.fragment.NewsDetailFragment.HotFragment;
import com.lerrycr.oschina.fragment.NewsDetailFragment.MessageFragment;
import com.lerrycr.oschina.fragment.NewsDetailFragment.RecommendFragment;
import com.lerrycr.oschina.listener.OnResponseListener;
import com.lerrycr.oschina.net.ApiClientHelper;
import com.lerrycr.oschina.utils.Logger;
import com.lerrycr.oschina.utils.StringUtils;
import com.lerrycr.oschina.utils.ThemeSwitchUtils;
import com.lerrycr.oschina.utils.UIHelper;
import com.lerrycr.oschina.utils.XmlUtils;
import com.squareup.okhttp.Request;

import java.io.IOException;

import butterknife.Bind;
import butterknife.OnClick;

public class ItemDetialActivity extends BaseActivity implements OnResponseListener {

    @Bind(R.id.webview)
    WebView mWebview;
    @Bind(R.id.ib_detail_keyboard)
    ImageButton mIbDetailKeyboard;
    @Bind(R.id.ib_detail_review)
    ImageButton mIbDetailReview;
    @Bind(R.id.ib_detail_write)
    ImageButton mIbDetailWrite;
    @Bind(R.id.ib_detail_favor)
    ImageButton mIbDetailFavor;
    @Bind(R.id.ib_detail_report)
    ImageButton mIbDetailReport;

    private int mId;

    private String mAction;

    @Override
    protected void initView() {
        Intent intent = getIntent();
        String action = intent.getAction();
        setAction(action);
        initActionBar();
        if (action == null) {
            return;
        }
        int id = 0;
        switch (action) {
            case "com.oschina.message.detial":
                id = intent.getIntExtra(MessageFragment.MESSAGE_DETIAL_ID, -1);

                break;
            case "com.oschina.hots.detial":
                id = intent.getIntExtra(HotFragment.HOTS_DETIAL_ID, -1);
                break;

            case "com.oschina.blog.detial":
                id = intent.getIntExtra(BlogFragment.BLOGS_DETIAL_ID, -1);
                break;
            case "com.oschina.recommend.detial":
                id = intent.getIntExtra(RecommendFragment.RECOMMEND_DETIAL_ID, -1);
                break;
        }
        //保存id
        setId(id);

    }

    /**
     * 初始化actionbar
     */
    private void initActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        switch (mAction) {
            case "com.oschina.message.detial":
            case "com.oschina.hots.detial":
                actionBar.setTitle("咨询详情");
                break;
            case "com.oschina.blog.detial":
            case "com.oschina.recommend.detial":
                actionBar.setTitle("博客详情");
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.refresh, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        int index = getId();
        if (mAction != null) {
            switch (mAction) {
                case "com.oschina.message.detial":
                case "com.oschina.hots.detial":
                    ApiClientHelper.getNewsAndHotsDetial(this, index, this);
                    break;
                case "com.oschina.blog.detial":
                case "com.oschina.recommend.detial":
                    ApiClientHelper.getBlogAndRecommendDetial(this, index, this);
                    break;
            }
        }

    }

    @Override
    public void onFailure(Request request, IOException e) {

    }

    @Override
    public void onResponse(String response) throws IOException {
        setDatas(response);
    }

    /**
     * 设置数据给webview
     *
     * @param response
     */

    private void setDatas(String response) {
        Logger.i(this, response);
        if (mAction != null) {
            switch (mAction) {
                case "com.oschina.message.detial":
                case "com.oschina.hots.detial":
                    NewsDetail newsDetail = XmlUtils.toBean(NewsDetail.class, response.getBytes());
                    News news = newsDetail.getNews();
                    mWebview.loadDataWithBaseURL("", getWebViewBodyForNews(news), "text/html", "utf-8", "");
                    break;
                case "com.oschina.blog.detial":
                case "com.oschina.recommend.detial":
                    BlogDetail blogDetail = XmlUtils.toBean(BlogDetail.class, response.getBytes());
                    Blog blog = blogDetail.getBlog();
                    mWebview.loadDataWithBaseURL("", getWebViewBodyForBlog(blog), "text/html", "utf-8", "");
                    break;
            }
        }


    }


    @Override
    protected Object getLayoutIdOrView() {
        return R.layout.activity_item_detial;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public void setAction(String action) {
        mAction = action;
    }

    protected String getWebViewBodyForNews(News detail) {
        StringBuffer body = new StringBuffer();
        body.append(UIHelper.WEB_STYLE).append(UIHelper.WEB_LOAD_IMAGES);
        body.append(ThemeSwitchUtils.getWebViewBodyString());
        // 添加title
        body.append(String.format("<div class='title'>%s</div>", detail.getTitle()));
        // 添加作者和时间
        String time = detail.getPubDate();
        String author = String.format("<a class='author' href='http://my.oschina.net/u/%s'>%s</a>", detail.getAuthorId(), detail.getAuthor());
        body.append(String.format("<div class='authortime'>%s&nbsp;&nbsp;&nbsp;&nbsp;%s</div>", author, time));
        // 添加图片点击放大支持
        body.append(UIHelper.setHtmlCotentSupportImagePreview(detail.getBody()));


        // 更多关于***软件的信息
        String softwareName = detail.getSoftwareName();
        String softwareLink = detail.getSoftwareLink();
        if (!StringUtils.isEmpty(softwareName)
                && !StringUtils.isEmpty(softwareLink))
            body.append(String
                    .format("<div class='oschina_software' style='margin-top:8px;font-weight:bold'>更多关于:&nbsp;<a href='%s'>%s</a>&nbsp;的详细信息</div>",
                            softwareLink, softwareName));

        // 相关新闻
        if (detail != null && detail.getRelatives() != null
                && detail.getRelatives().size() > 0) {
            String strRelative = "";
            for (News.Relative relative : detail.getRelatives()) {
                strRelative += String.format(
                        "<li><a href='%s' style='text-decoration:none'>%s</a></li>",
                        relative.url, relative.title);
            }
            body.append("<p/><div style=\"height:1px;width:100%;background:#DADADA;margin-bottom:10px;\"/>"
                    + String.format("<br/> <b>相关资讯</b><ul class='about'>%s</ul>",
                    strRelative));
        }
        body.append("<br/>");
        // 封尾
        body.append("</div></body>");
        return body.toString();
    }

    protected String getWebViewBodyForBlog(Blog detail) {
        StringBuffer body = new StringBuffer();
        body.append(UIHelper.WEB_STYLE).append(UIHelper.WEB_LOAD_IMAGES);
        body.append(ThemeSwitchUtils.getWebViewBodyString());
        // 添加title
        body.append(String.format("<div class='title'>%s</div>", detail.getTitle()));
        // 添加作者和时间
        String time = detail.getPubDate();
        String author = String.format("<a class='author' href='http://my.oschina.net/u/%s'>%s</a>", detail.getAuthorId(), detail.getAuthor());
        body.append(String.format("<div class='authortime'>%s&nbsp;&nbsp;&nbsp;&nbsp;%s</div>", author, time));
        // 添加图片点击放大支持
        body.append(UIHelper.setHtmlCotentSupportImagePreview(detail.getBody()));
        // 封尾
        body.append("</div></body>");
        return body.toString();
    }

    @OnClick({R.id.ib_detail_keyboard, R.id.ib_detail_review, R.id.ib_detail_write, R.id.ib_detail_favor, R.id.ib_detail_report})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_detail_keyboard:
                break;
            case R.id.ib_detail_review:
                break;
            case R.id.ib_detail_write:
                break;
            case R.id.ib_detail_favor:
                break;
            case R.id.ib_detail_report:
                break;
        }
    }
}
