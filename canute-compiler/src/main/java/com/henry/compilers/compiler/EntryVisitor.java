package com.henry.compilers.compiler;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;

import javax.annotation.processing.Filer;
import javax.imageio.spi.ServiceRegistry;
import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.SimpleAnnotationValueVisitor7;

/**
 * Created by Administrator on 2017/8/22.
 * 8.23早上需要记得的东西：filer怎么得来的，visitor要设在哪？
 */

public final class EntryVisitor extends SimpleAnnotationValueVisitor7<Void,Void>{
    private TypeMirror mTypeMirror;//要循环找出来的类型
    private Filer mFilter;//需要遍历的东西
    private String mPackageName;//包名
public void setVisitor(Filer filer){
this.mFilter=filer;
}
//找包名
    @Override
    public Void visitString(String s, Void p) {
            mPackageName=s;
        return p;
    }
//找类名
    @Override
    public Void visitType(TypeMirror t, Void p) {
        mTypeMirror=t;
        return p;
    }
    //生成java代码
    public void generateJavaCode(){
        //生成一个类
        final TypeSpec targetActivity=
                TypeSpec.classBuilder("WXEntryActivity")//类名
                .addModifiers(Modifier.PUBLIC)//类属性
                .addModifiers(Modifier.FINAL)//类属性
                .superclass(TypeName.get(mTypeMirror))//重点！！！！在注解中获取父类，这样我们就可以在父类做其他设置了
                .build();
        //然后生成类文件，在build文件夹下面的
        final JavaFile javaFile=JavaFile.builder(mPackageName+".wxapi",targetActivity)
                .addFileComment("微信入口")//注释
                .build();
        try {
            javaFile.writeTo(mFilter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
