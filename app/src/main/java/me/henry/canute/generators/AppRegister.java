package me.henry.canute.generators;


import com.henry.annotationssss.annotations.AppRegisterGenerator;

import me.henry.canutecore.wechat.template.AppRigisterTemplate;

/**
 * Created by 傅令杰 on 2017/4/22
 */
@SuppressWarnings("unused")
@AppRegisterGenerator(
        packageName = "me.henry.canute",
        registerTemplate = AppRigisterTemplate.class
)
public interface AppRegister {
}
