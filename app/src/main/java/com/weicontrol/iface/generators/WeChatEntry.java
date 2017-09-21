package com.weicontrol.iface.generators;


import me.henry.annotationssss.annotations.EntryGenerator;
import me.henry.canutecore.wechat.template.WXEntryTemplate;

/**
 * Created by 傅令杰 on 2017/4/22
 */

@SuppressWarnings("unused")
@EntryGenerator(
        packageName = "com.weicontrol.iface",
        entryTemplate = WXEntryTemplate.class
)
public interface WeChatEntry {
}
