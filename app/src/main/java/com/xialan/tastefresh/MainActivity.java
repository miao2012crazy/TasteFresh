package com.xialan.tastefresh;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.igexin.sdk.PushManager;
import com.xialan.tastefresh.adapter.LeftItemAdapter;
import com.xialan.tastefresh.applaction.MyApplaction;
import com.xialan.tastefresh.base.BaseFragment;
import com.xialan.tastefresh.base.BaseHttpUtil;
import com.xialan.tastefresh.base.BaseRequestInterTwoParama;
import com.xialan.tastefresh.bean.CommonBean;
import com.xialan.tastefresh.bean.LeftBean;
import com.xialan.tastefresh.bean.UserInfoBean;
import com.xialan.tastefresh.commonutil.AESOperator;
import com.xialan.tastefresh.commonutil.BaseUtils;
import com.xialan.tastefresh.commonutil.EventBusUtils;
import com.xialan.tastefresh.commonutil.ParseUtils;
import com.xialan.tastefresh.commonutil.SharePreUtils;
import com.xialan.tastefresh.commonutil.StringUtil;
import com.xialan.tastefresh.commonutil.UIUtils;
import com.xialan.tastefresh.contranst.EventBusEvent;
import com.xialan.tastefresh.httputil.HttpUrl;
import com.xialan.tastefresh.presenter.LeftPresenter;
import com.xialan.tastefresh.service.CustomIntentService;
import com.xialan.tastefresh.service.CustomPushService;
import com.xialan.tastefresh.view.LeftFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements BaseRequestInterTwoParama {
    @BindView(R.id.main_content_frame)
    FrameLayout mainContentFrame;
    @BindView(R.id.main_content_frame_parent)
    RelativeLayout mainContentFrameParent;
    @BindView(R.id.main_left_drawer_layout)
    RelativeLayout mainLeftDrawerLayout;
    @BindView(R.id.main_right_drawer_layout)
    RelativeLayout mainRightDrawerLayout;
    @BindView(R.id.main_drawer_layout)
    DrawerLayout mainDrawerLayout;

    // 抽屉菜单对象
    private ActionBarDrawerToggle drawerbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        initPushService();
        initEvent();
        initLogin();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetClientSuccess(EventBusEvent event) {
        if (event.getCode()== EventBusUtils.EventCode.A){
            Log.e("miao", "接收到消息" + event.getData());
            String userId = getUserId();
            String user_id = BaseUtils.getJsonData("user_id", userId);
            String client_id = BaseUtils.getJsonData("client_id", event.getData().toString());
            String[] strings = {user_id,client_id};
            getDataFromNet("", HttpUrl.UPLOAD_CLIENT_ID, AESOperator.getRandomString(),strings);
        }
    }

    /**
     * 初始化推送服务
     */
    private void initPushService(){
        PushManager.getInstance().initialize(this.getApplicationContext(), CustomPushService.class);
        PushManager.getInstance().registerPushIntentService(this.getApplicationContext(), CustomIntentService.class);
    }

    /**
     * 初始化用户登录信息
     */
    private void initLogin() {
        String login_data = (String) SharePreUtils.get(this, "user_login", "");
        String login_key = (String) SharePreUtils.get(UIUtils.getContext(), "login_key", "");
        long time = (long) SharePreUtils.get(UIUtils.getContext(), "time",0L );
        if (!StringUtil.isEmpty(login_data) && !StringUtil.isEmpty(login_key) && time!=0) {
            long distanceTime = UIUtils.getDistanceTime(time, UIUtils.getTime());
            if (distanceTime>7){
                UIUtils.showToast("您已经连续7天没有登录,需要重新登录验证!");
                SharePreUtils.remove(this, "user_login");
                SharePreUtils.remove(this, "login_key");
                SharePreUtils.remove(this, "time");
                startActivity(LoginActivity.class);
            }else{
                //验证全部通过 持久化登录 刷新时间戳
                UserInfoBean userInfoBean = ParseUtils.parseDataAES(login_key, login_data, UserInfoBean.class);
                MyApplaction.setUserBean(userInfoBean);
                //刷新时间戳
                SharePreUtils.put(this, "time",UIUtils.getTime());
            }
        } else {
            SharePreUtils.remove(this, "user_login");
            SharePreUtils.remove(this, "login_key");
            SharePreUtils.remove(this, "time");
            startActivity(LoginActivity.class);
        }
    }

    protected void startActivity(Class clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    /**
     * 勿改
     */
    private void initEvent() {
        drawerbar = new ActionBarDrawerToggle(this, mainDrawerLayout, R.string.open, R.string.close) {
            //菜单打开
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            // 菜单关闭
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mainDrawerLayout.addDrawerListener(drawerbar);

        mainDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                //获取屏幕的宽高
                WindowManager manager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
                Display display = manager.getDefaultDisplay();
                if (mainDrawerLayout.isDrawerVisible(mainLeftDrawerLayout)) {
                    mainContentFrame.layout(mainLeftDrawerLayout.getRight(), 0, mainLeftDrawerLayout.getRight() + display.getWidth(), display.getHeight());
                } else {
                    mainContentFrame.layout(mainRightDrawerLayout.getLeft() - display.getWidth(), 0, mainRightDrawerLayout.getLeft(), display.getHeight());
                }
            }

            @Override
            public void onDrawerOpened(View drawerView) {
            }

            @Override
            public void onDrawerClosed(View drawerView) {
            }

            @Override
            public void onDrawerStateChanged(int newState) {
            }
        });

    }

    //左边菜单开关事件
    public void openLeftLayout() {
        if (mainDrawerLayout.isDrawerOpen(mainLeftDrawerLayout)) {
            mainDrawerLayout.closeDrawer(mainLeftDrawerLayout);
        } else {
            mainDrawerLayout.openDrawer(mainLeftDrawerLayout);
        }
    }

    // 右边菜单开关事件
    public void openRightLayout() {
        if (mainDrawerLayout.isDrawerOpen(mainRightDrawerLayout)) {
            mainDrawerLayout.closeDrawer(mainRightDrawerLayout);
        } else {
            mainDrawerLayout.openDrawer(mainRightDrawerLayout);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    /**
     * 上传clientid 到服务器
     * @param tag 标签
     * @param url   路径
     * @param key   请求key
     * @param body  请求体
     */
    @Override
    public void getDataFromNet(String tag, String url, String key, String[] body) {
        BaseHttpUtil.sendHttp(url, key, body,CommonBean.class, new BaseHttpUtil.OnRequestSuccessedListener<CommonBean>() {
            @Override
            public void onSuccessed(CommonBean commonBean) {
                String code = commonBean.getCode();
                boolean checkdata = ParseUtils.checkdata(code);
//                if (checkdata){
//
//                }else{
//                    UIUtils.showToast(ParseUtils.showErrMsg(commonBean.getErr_msg()));
//                }
            }
        });
    }
    protected String getUserId() {
        try {
            String user_id = MyApplaction.getUserBean().getData().getUser_id();
            return user_id;
        } catch (Exception ex) {
            startActivity(LoginActivity.class);
            return "";
        }
    }

    //    @OnClick({R.id.left, R.id.right})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.left:
//                openLeftLayout();
//                break;
//            case R.id.right:
//                openRightLayout();
//                break;
//        }
//    }
}
