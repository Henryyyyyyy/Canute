package me.henry.canuteec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.OnClick;
import me.henry.canutecore.delegates.CanuteDelegate;
import me.henry.canutecore.util.log.CanuteLogger;
import me.henry.canutecore.wechat.CanuteWechat;
import me.henry.canutecore.wechat.callback.IWeChatSignInCallback;
import me.henry.canuteec.R;
import me.henry.canuteec.R2;

/**
 * Created by zj on 2017/8/21.
 * me.henry.canuteec.sign
 */

public class SignUpDelegate extends CanuteDelegate{
    @BindView(R2.id.edit_sign_up_name)
    TextInputEditText mName = null;
    @BindView(R2.id.edit_sign_up_email)
    TextInputEditText mEmail = null;
    @BindView(R2.id.edit_sign_up_phone)
    TextInputEditText mPhone = null;
    @BindView(R2.id.edit_sign_up_password)
    TextInputEditText mPassword = null;
    @BindView(R2.id.edit_sign_up_re_password)
    TextInputEditText mRePassword = null;
    private ISignListener mISignListener = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
                if (activity instanceof ISignListener) {
            mISignListener = (ISignListener) activity;
        }
    }


    @OnClick(R2.id.btn_sign_up)
    void onClickSignUp() {
        String response="{\n" +
                "  \"code\": 0,\n" +
                "  \"message\": \"OK\",\n" +
                "  \"data\": {\n" +
                "    \"userId\": 1,\n" +
                "    \"name\": \"猿猿\",\n" +
                "    \"avatar\": \"http://wx.qlogo.cn/mmopen/guWqj0vybsIHxY2BIqqI3iaSHcbWZXiaSQysU0JKwmqjqMw8Uhia6AribBBynqnr9qxVOTkaUMnAnzqvXYjEDctsoXxzeQ2ibqWt0/0\",\n" +
                "    \"gender\": \"男\",\n" +
                "    \"address\": \"西安\"\n" +
                "  }\n" +
                "}";
                              CanuteLogger.json("USER_PROFILE", response);
                            SignHandler.onSignUp(response,mISignListener);
  //      if (checkForm()) {
//            RestClient.builder()
//                    .url("http://192.168.56.1:8080/RestDataServer/api/user_profile.php")
//                    .params("name", mName.getText().toString())
//                    .params("email", mEmail.getText().toString())
//                    .params("phone", mPhone.getText().toString())
//                    .params("password", mPassword.getText().toString())
//                    .success(new ISuccess() {
//                        @Override
//                        public void onSuccess(String response) {
//                            LatteLogger.json("USER_PROFILE", response);
//                            SignHandler.onSignUp(response, mISignListener);
//                        }
//                    })
//                    .build()
//                    .post();


      //  }
    }
    private boolean checkForm() {
        final String name = mName.getText().toString();
        final String email = mEmail.getText().toString();
        final String phone = mPhone.getText().toString();
        final String password = mPassword.getText().toString();
        final String rePassword = mRePassword.getText().toString();

        boolean isPass = true;

        if (name.isEmpty()) {
            mName.setError("请输入姓名");
            isPass = false;
        } else {
            mName.setError(null);
        }

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            mEmail.setError(null);


        }

        if (phone.isEmpty() || phone.length() != 11) {
            mPhone.setError("手机号码错误");
            isPass = false;
        } else {
            mPhone.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("请填写至少6位数密码");
            isPass = false;
        } else {
            mPassword.setError(null);
        }

        if (rePassword.isEmpty() || rePassword.length() < 6 || !(rePassword.equals(password))) {
            mRePassword.setError("密码验证错误");
            isPass = false;
        } else {
            mRePassword.setError(null);
        }

        return isPass;
    }
    @OnClick(R2.id.icon_sign_in_wechat)
    void onClickWeChat() {
        Log.e("hahah","wechat222");
        CanuteWechat
                .getInstance()
                .onSignSuccess(new IWeChatSignInCallback() {
                    @Override
                    public void onSignInSuccess(String userInfo) {
//                        Toast.makeText(getContext(), userInfo, Toast.LENGTH_LONG).show();
                        CanuteLogger.e("fuck",userInfo);
                        Log.e("fuckme","userInfo ="+userInfo);
                    }
                })
                .signIn();
    }
    @Override
    public Object setLayout() {
        return R.layout.delegate_signup;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
