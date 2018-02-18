package com.xialan.tastefresh.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.xialan.tastefresh.R;
import com.xialan.tastefresh.adapter.GridSpacingItemDecoration;
import com.xialan.tastefresh.adapter.UpLoadPicAdapter;
import com.xialan.tastefresh.base.BaseActivity;
import com.xialan.tastefresh.base.BasePresenter;
import com.xialan.tastefresh.bean.UpLoadPicBean;
import com.xialan.tastefresh.commonutil.StringUtil;
import com.xialan.tastefresh.commonutil.UIUtils;
import com.xialan.tastefresh.contract.CommitReportExperienceContract;
import com.xialan.tastefresh.presenter.CommitReportExperiencePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/1/22.
 */

public class CommitReportExperienceActivity extends BaseActivity implements CommitReportExperienceContract.View {

    @BindView(R.id.rl_left)
    RelativeLayout rlLeft;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_right)
    RelativeLayout rlRight;
    @BindView(R.id.iv_product_img_report)
    ImageView ivProductImgReport;
    @BindView(R.id.et_progress_text)
    EditText etProgressText;
    @BindView(R.id.recycler_upload_pic)
    RecyclerView recyclerUploadPic;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.tv_product_name)
    TextView tvProductName;
    @BindView(R.id.tv_process_num)
    TextView tvProcessNum;
    private CommitReportExperiencePresenter commitReportExperiencePresenter;
    private String report_id;
    private List<UpLoadPicBean> mList;
    private UpLoadPicAdapter upLoadPicAdapter;
    private List<LocalMedia> selectList = null;

    @Override
    protected int setlayoutID() {

        return R.layout.activity_commit_report_ex;
    }

    @Override
    protected void initData() {
        rlRight.setVisibility(View.GONE);
        tvTitle.setText("试用心得");
        report_id = getIntent().getStringExtra("report_id");
        initRecyclerView();
    }

    /**
     * 初始化recyclerview布局
     */
    private void initRecyclerView() {
        GridLayoutManager layoutManage = new GridLayoutManager(this, 4);
        recyclerUploadPic.setLayoutManager(layoutManage);
        recyclerUploadPic.addItemDecoration(new GridSpacingItemDecoration(4, 20, false));
        mList = new ArrayList<>();
        UpLoadPicBean e0 = new UpLoadPicBean("", "1");
        mList.add(e0);
        upLoadPicAdapter = new UpLoadPicAdapter(mList);
        recyclerUploadPic.setAdapter(upLoadPicAdapter);
        upLoadPicAdapter.notifyDataSetChanged();
        recyclerUploadPic.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
                UpLoadPicBean upLoadPicBean = (UpLoadPicBean) baseQuickAdapter.getData().get(position);
                switch (upLoadPicBean.getTag()) {
                    case "1":
                        if (selectList != null && selectList.size() == 3) {
                            //已有十张图片
                            UIUtils.showToast("最多支持10张图片!可以先移除已选图片");
                            return;
                        }
                        //选择照片
                        PictureSelector.create(CommitReportExperienceActivity.this)
                                .openGallery(PictureMimeType.ofImage())
                                .selectionMode(PictureConfig.MULTIPLE)
                                .maxSelectNum(10)
                                .enableCrop(true)// 是否裁剪 true or false
                                .compress(true)// 是否压缩 true or false
                                .withAspectRatio(3, 4)// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                                .hideBottomControls(true)// 是否显示uCrop工具栏，默认不显示 true or false
                                .forResult(PictureConfig.CHOOSE_REQUEST);
                        break;
                    default:
                        break;
                }
            }
        });
        recyclerUploadPic.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
                UpLoadPicBean upLoadPicBean = mList.get(position);
                switch (upLoadPicBean.getTag()) {
                    case "3":
                        // 预览图片
                        PictureSelector.create(CommitReportExperienceActivity.this).externalPicturePreview(position, selectList);
                        break;
                }
            }

            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                super.onItemChildClick(adapter, view, position);
                switch (view.getId()) {
                    case R.id.iv_delete:
                        //移除图片或视频
                        mList.remove(position);
                        upLoadPicAdapter.notifyDataSetChanged();
                        break;
                }
            }
        });
    }

    @Override
    protected BasePresenter createPresenter() {
        commitReportExperiencePresenter = new CommitReportExperiencePresenter(this);
        return commitReportExperiencePresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.rl_left, R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_left:
                finish();
                break;
            case R.id.btn_next:
                String s = etProgressText.getText().toString();
                boolean empty = StringUtil.isEmpty(s);
                if (empty) {
                    UIUtils.showToast("还没有填写试用心得!");
                    return;
                }
                commitReportExperiencePresenter.upLodeReport(getUserId(),report_id,s,mList);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    selectList = PictureSelector.obtainMultipleResult(data);
                    for (LocalMedia item : selectList) {
                        try {
                            String compressPath = item.getCompressPath();
                            UpLoadPicBean e = new UpLoadPicBean(compressPath, "3");
                            mList.add(e);
                        } catch (Exception ex) {

                        }
                    }
                    upLoadPicAdapter.notifyDataSetChanged();
                    break;
            }
        }
    }

    @Override
    public void getDataFailed(String err_msg) {

    }

    @Override
    public void upLodeSuccessed() {
        UIUtils.showToast("提交成功!");
        Intent intent = new Intent(CommitReportExperienceActivity.this, CommitReportListActvity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
