package me.henry.canute.generators;


import com.henry.annotationssss.annotations.PayEntryGenerator;

import me.henry.canutecore.wechat.template.WXPayEntryTemplate;

/**
 * Created by 傅令杰 on 2017/4/22
 */
@SuppressWarnings("unused")
@PayEntryGenerator(
        packageName = "me.henry.canute",
        payEntryTemplate = WXPayEntryTemplate.class
)
//这里其实写什么都行，主要用来生成build下面的文件，
public interface WeChatPayEntry {
}
