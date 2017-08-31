package com.henry.compilers.compiler;

import com.google.auto.service.AutoService;
import com.henry.annotationssss.annotations.AppRegisterGenerator;
import com.henry.annotationssss.annotations.EntryGenerator;
import com.henry.annotationssss.annotations.PayEntryGenerator;

import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.AnnotationValueVisitor;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

/**
 * Created by Administrator on 2017/8/22.
 * 扫描全局的一个Processor,就是要获得那些注解内容
 */
@AutoService(Processor.class)
public class CanuteProcessor extends AbstractProcessor {


    /**
     * 1.先要定义一下，这个processor需要到哪些注解
     */
    private Set<Class<? extends Annotation>> getSupportAnnotations() {
        final Set<Class<? extends Annotation>> annotations = new LinkedHashSet<>();
        annotations.add(EntryGenerator.class);
        annotations.add(PayEntryGenerator.class);
        annotations.add(AppRegisterGenerator.class);
        return annotations;
    }

    /**
     * 2.把注解标注的类名找出来
     * ps：要了解一下工作原理,如果某个注释标注了多个类，是怎么执行的？这个方法什么时候被调用
     *
     * @return
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        final Set<Class<? extends Annotation>> supportAnnotations = getSupportAnnotations();
        final Set<String> types = new LinkedHashSet<>();
        for (Class<? extends Annotation> annotation : supportAnnotations) {
            types.add(annotation.getCanonicalName());
        }
        return types;
    }

    /**
     * 这里的主要作用就是
     * 在环境里扫描注释标注的元素，然后把内容交给visitor处理
     *
     * @param env
     * @param annotation
     * @param visitor
     */
    private void scan(RoundEnvironment env, Class<? extends Annotation> annotation, AnnotationValueVisitor visitor) {
        //在代码环境中获得标有注释的元素，不一定是类，可能是interface,方法，属性
        for (Element typeElement : env.getElementsAnnotatedWith(annotation)) {
            //从元素中获得AnnotationMirrors（AnnotationMirror就是每个注解元素的个体信息集，因为你会在注解的时候赋值）
            final List<? extends AnnotationMirror> annotationMirrors = typeElement.getAnnotationMirrors();
            //下面的就是获得信息集里面的东西，然后给visitor进行自己的操作

            for (AnnotationMirror annotationMirror : annotationMirrors) {

                final Map<? extends ExecutableElement, ? extends AnnotationValue> elementValue = annotationMirror.getElementValues();

                for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry : elementValue.entrySet()) {

                    entry.getValue().accept(visitor, null);
                }

            }

        }
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        //scan
        generateEntryCode(roundEnv);
        generatePayEntryCode(roundEnv);
        generateAppRegisterCode(roundEnv);
        return true;
    }

    private void generateEntryCode(RoundEnvironment env) {
        final EntryVisitor entryVisitor = new EntryVisitor(processingEnv.getFiler());
        scan(env, EntryGenerator.class, entryVisitor);
    }

    private void generatePayEntryCode(RoundEnvironment env) {
        final PayEntryVisitor payEntryVisitor = new PayEntryVisitor(processingEnv.getFiler());
        scan(env, PayEntryGenerator.class, payEntryVisitor);
    }

    private void generateAppRegisterCode(RoundEnvironment env) {
        final AppRegisterVisitor appRegisterVisitor = new AppRegisterVisitor(processingEnv.getFiler());
        scan(env, AppRegisterGenerator.class, appRegisterVisitor);
    }
}
