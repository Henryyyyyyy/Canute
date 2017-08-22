package com.henry.annotationssss.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 2017/8/22.
 */
@Target(ElementType.TYPE)//表示在类上面使用的
@Retention(RetentionPolicy.SOURCE)//生成源码的时候使用的,对性能没有影响
public @interface EntryGenerator {
    String packageName();//这些就是在注解上面要添加的参数
    Class<?>entryTemplate();
}
