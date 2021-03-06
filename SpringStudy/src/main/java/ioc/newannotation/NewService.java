package ioc.newannotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 自定义注解 ioc.service
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface NewService {
}
