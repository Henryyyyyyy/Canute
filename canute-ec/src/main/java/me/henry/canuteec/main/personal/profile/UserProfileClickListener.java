package me.henry.canuteec.main.personal.profile;

import android.content.DialogInterface;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;

import me.henry.canutecore.delegates.CanuteDelegate;
import me.henry.canutecore.net.RestClient;
import me.henry.canutecore.net.callback.ISuccess;
import me.henry.canutecore.ui.date.DateDialogUtil;
import me.henry.canutecore.util.log.CanuteLogger;
import me.henry.canuteec.R;
import me.henry.canuteec.main.personal.list.ListBean;


public class UserProfileClickListener extends SimpleClickListener {

    private final UserProfileDelegate DELEGATE;

    private String[] mGenders = new String[]{"男", "女", "保密"};

    public UserProfileClickListener(UserProfileDelegate DELEGATE) {
        this.DELEGATE = DELEGATE;
    }

    @Override
    public void onItemClick(final BaseQuickAdapter adapter, final View view, int position) {
        final ListBean bean = (ListBean) baseQuickAdapter.getData().get(position);
        final int id = bean.getId();
        switch (id) {
            case 1:
//                //开始照相机或选择图片
//                CallbackManager.getInstance()
//                        .addCallback(CallbackType.ON_CROP, new IGlobalCallback<Uri>() {
//                            @Override
//                            public void executeCallback(Uri args) {
//                                CanuteLogger.d("ON_CROP", args);
//                                final ImageView avatar = (ImageView) view.findViewById(R.id.img_arrow_avatar);
//                                Glide.with(DELEGATE)
//                                        .load(args)
//                                        .into(avatar);
//
//                                RestClient.builder()
//                                        .url(UploadConfig.UPLOAD_IMG)
//                                        .loader(DELEGATE.getContext())
//                                        .file(args.getPath())
//                                        .success(new ISuccess() {
//                                            @Override
//                                            public void onSuccess(String response) {
//                                                CanuteLogger.d("ON_CROP_UPLOAD", response);
//                                                final String path = JSON.parseObject(response).getJSONObject("result")
//                                                        .getString("path");
//
//                                                //通知服务器更新信息
//                                                RestClient.builder()
//                                                        .url("user_profile.php")
//                                                        .params("avatar", path)
//                                                        .loader(DELEGATE.getContext())
//                                                        .success(new ISuccess() {
//                                                            @Override
//                                                            public void onSuccess(String response) {
//                                                                //获取更新后的用户信息，然后更新本地数据库
//                                                                //没有本地数据的APP，每次打开APP都请求API，获取信息
//                                                            }
//                                                        })
//                                                        .build()
//                                                        .post();
//                                            }
//                                        })
//                                        .build()
//                                        .upload();
//                            }
//                        });
//                DELEGATE.startCameraWithCheck();
                break;
            case 2:
                final CanuteDelegate nameDelegate = bean.getDelegate();
                DELEGATE.getSupportDelegate().start(nameDelegate);
                break;
            case 3:
                getGenderDialog(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final TextView textView = (TextView) view.findViewById(R.id.tv_arrow_value);
                        textView.setText(mGenders[which]);
                        dialog.cancel();
                    }
                });
                break;
            case 4:
                final DateDialogUtil dateDialogUtil = new DateDialogUtil();
                dateDialogUtil.setDateListener(new DateDialogUtil.IDateListener() {
                    @Override
                    public void onDateChange(String date) {
                        final TextView textView = (TextView) view.findViewById(R.id.tv_arrow_value);
                        textView.setText(date);
                    }
                });
                dateDialogUtil.showDialog(DELEGATE.getContext());
                break;
            default:
                break;
        }
    }

    private void getGenderDialog(DialogInterface.OnClickListener listener) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(DELEGATE.getContext());
        builder.setSingleChoiceItems(mGenders, 0, listener);
        builder.show();
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
