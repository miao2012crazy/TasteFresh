package com.xialan.tastefresh.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.nostra13.universalimageloader.core.ImageLoader;
import com.willy.ratingbar.BaseRatingBar;
import com.willy.ratingbar.ScaleRatingBar;
import com.xialan.tastefresh.R;
import com.xialan.tastefresh.adapter.GridSpacingItemDecoration;
import com.xialan.tastefresh.adapter.UpLoadPicAdapter;
import com.xialan.tastefresh.base.BaseActivity;
import com.xialan.tastefresh.base.BasePresenter;
import com.xialan.tastefresh.bean.UpLoadPicBean;
import com.xialan.tastefresh.commonutil.ParseUtils;
import com.xialan.tastefresh.commonutil.StringUtil;
import com.xialan.tastefresh.commonutil.UIUtils;
import com.xialan.tastefresh.contract.CommitReportContract;
import com.xialan.tastefresh.presenter.CommitReportPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/1/21.
 */

public class CommitReportActivity extends BaseActivity implements CommitReportContract.View {

    @BindView(R.id.rl_left)
    RelativeLayout rlLeft;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_right)
    RelativeLayout rlRight;
    @BindView(R.id.iv_product_img_report)
    ImageView ivProductImgReport;
    @BindView(R.id.simpleRatingBar)
    ScaleRatingBar simpleRatingBar;
    @BindView(R.id.et_progress_text)
    EditText etProgressText;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.recycler_upload_pic)
    RecyclerView recyclerUploadPic;
    @BindView(R.id.tv_score)
    TextView tvScore;
    @BindView(R.id.tv_product_name)
    TextView tvProductName;
    @BindView(R.id.tv_process_num)
    TextView tvProcessNum;
    @BindView(R.id.tv_tag)
    TextView tvTag;
    private CommitReportPresenter commitReportPresenter;
    private List<UpLoadPicBean> mList;
    private UpLoadPicAdapter upLoadPicAdapter;
    private List<LocalMedia> selectList = null;
    private List<LocalMedia> selectList1 = null;
    private String order_id;

    @Override
    protected int setlayoutID() {
        return R.layout.activity_commit_report;
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        order_id = intent.getStringExtra("order_id");
        String img_url = intent.getStringExtra("img_url");
        String deposit = intent.getStringExtra("deposit");
        String product_name = intent.getStringExtra("product_name");
        ImageLoader.getInstance().displayImage(img_url, ivProductImgReport);
        tvProductName.setText(product_name);
        rlRight.setVisibility(View.GONE);
        tvTitle.setText("试用过程");

        initRecyclerView();
        simpleRatingBar.setOnRatingChangeListener(new BaseRatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChange(BaseRatingBar baseRatingBar, float v) {
                tvScore.setText(v + "");
            }
        });
        etProgressText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tvProcessNum.setText(s.length() + "/500");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        simpleRatingBar.setRating(5.0f);
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
        UpLoadPicBean e1 = new UpLoadPicBean("", "2");
        mList.add(e0);
        mList.add(e1);
        upLoadPicAdapter = new UpLoadPicAdapter(mList);
        recyclerUploadPic.setAdapter(upLoadPicAdapter);
        upLoadPicAdapter.notifyDataSetChanged();
        recyclerUploadPic.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
                UpLoadPicBean upLoadPicBean = (UpLoadPicBean) baseQuickAdapter.getData().get(position);
                switch (upLoadPicBean.getTag()) {
                    case "1":
                        if (selectList != null && selectList.size() == 10) {
                            //已有十张图片
                            UIUtils.showToast("最多支持10张图片!可以先移除已选图片");
                            return;
                        }

                        //选择照片
                        PictureSelector.create(CommitReportActivity.this)
                                .openGallery(PictureMimeType.ofImage())
                                .selectionMode(PictureConfig.MULTIPLE)
                                .maxSelectNum(10)
                                .enableCrop(true)// 是否裁剪 true or false
                                .compress(true)// 是否压缩 true or false
                                .withAspectRatio(3, 4)// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                                .hideBottomControls(true)// 是否显示uCrop工具栏，默认不显示 true or false
                                .forResult(PictureConfig.CHOOSE_REQUEST);
                        break;
                    case "2":

                        if (selectList1 != null && selectList1.size() != 0) {
                            //已有视频
                            UIUtils.showToast("最多支持一个视频!可以先移除已选视频");
                            return;
                        }

                        //选择视频
                        PictureSelector.create(CommitReportActivity.this)
                                .openGallery(PictureMimeType.ofVideo())
                                .selectionMode(PictureConfig.SINGLE)
                                .maxSelectNum(1)
                                .compress(true)// 是否压缩 true or false
                                .previewVideo(true)
                                .enablePreviewAudio(true)
                                .hideBottomControls(true)// 是否显示uCrop工具栏，默认不显示 true or false
                                .forResult(PictureConfig.TYPE_VIDEO);
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
                        PictureSelector.create(CommitReportActivity.this).externalPicturePreview(position, selectList);
                        break;
                    case "4":
                        // 预览视频
                        PictureSelector.create(CommitReportActivity.this).externalPictureVideo(upLoadPicBean.getUrl());
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
        commitReportPresenter = new CommitReportPresenter(this);
        return commitReportPresenter;
    }


    @OnClick({R.id.rl_left, R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_left:
                finish();
                break;
            case R.id.btn_next:


                //上传数据 post 请求体
                Map<String, String> stringStringMap = new HashMap<>();
                stringStringMap.put("user_id", getUserId());
                stringStringMap.put("order_id", order_id);
                String value = etProgressText.getText().toString();
                if (StringUtil.isEmpty(value)) {
                    UIUtils.showToast("尚未填写试用过程!");
                    return;
                }
                stringStringMap.put("process", value);
                String score = tvScore.getText().toString();
                stringStringMap.put("core", score);
                if (selectList1 == null || selectList1.size() == 0 || selectList == null || selectList.size() == 0) {
                    UIUtils.showToast("至少需要上传一张图片和一个视频!");
                    return;
                }
                for (int i = 0; i < selectList.size(); i++) {
                    stringStringMap.put("process", value);
                }

                commitReportPresenter.upLodeReport(getUserId(),order_id,value,score,mList);
                break;
        }
    }

    @Override
    public void getDataFailed(String err_msg) {
        UIUtils.showToast(ParseUtils.showErrMsg(err_msg));
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

                case PictureConfig.TYPE_VIDEO:
                    // 视频选择结果回调
                    selectList1 = PictureSelector.obtainMultipleResult(data);
                    for (LocalMedia item : selectList1) {
                        try {
                            String path = item.getPath();
                            String cutPath = item.getCutPath();
                            String compressPath = item.getCompressPath();
                            UpLoadPicBean e = new UpLoadPicBean(path, "4");
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
    public void upLodeSuccessed(String report_id) {
        bundle.clear();
        bundle.putString("report_id",report_id);
        startActivityWithParamas(CommitReportExperienceActivity.class,bundle);
    }
}
