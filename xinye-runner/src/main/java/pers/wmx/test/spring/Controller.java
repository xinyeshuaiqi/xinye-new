package pers.wmx.test.spring;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
// 该注解可以作用于类,接口等
@Target(ElementType.TYPE)
// 该注解在运行时保留
@Retention(RetentionPolicy.RUNTIME)
public @interface Controller {
}
