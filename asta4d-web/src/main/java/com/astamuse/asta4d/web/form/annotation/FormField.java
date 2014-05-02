package com.astamuse.asta4d.web.form.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.astamuse.asta4d.util.annotation.ConvertableAnnotation;
import com.astamuse.asta4d.web.form.FormFieldValueRenderer;
import com.astamuse.asta4d.web.form.annotation.convert.FormFieldAnnotationConvertor;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
@ConvertableAnnotation(FormFieldAnnotationConvertor.class)
public @interface FormField {
    String name() default "";

    Class<? extends FormFieldValueRenderer> fieldValueRenderer();
}
