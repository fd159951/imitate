package ioc.newannotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

// 自定义注解 resource
@Target({TYPE, FIELD, METHOD})
@Retention(RUNTIME)
public @interface NewResource {
}
