package me.henry.canuteec.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import me.henry.canuteec.database.DataBaseManager;
import me.henry.canuteec.database.UserProfile;


/**
 * Created by zj on 2017/8/21.
 * me.henry.canuteec.sign
 */

public class SignHandler {
    public static void onSignUp(String response){
        final JSONObject profileJson= JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");
        final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);
        DataBaseManager.getInstance().getDao().insert(profile);
    }
}
