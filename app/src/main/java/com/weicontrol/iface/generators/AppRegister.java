package com.weicontrol.iface.generators;


import me.henry.annotationssss.annotations.AppRegisterGenerator;
import me.henry.canutecore.wechat.template.AppRigisterTemplate;

/**
 * Created by 傅令杰 on 2017/4/22
 */
@SuppressWarnings("unused")
@AppRegisterGenerator(
        packageName = "com.weicontrol.iface",
        registerTemplate = AppRigisterTemplate.class
)
public interface AppRegister {
}
