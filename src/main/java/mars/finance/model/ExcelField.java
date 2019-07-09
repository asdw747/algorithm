package mars.finance.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public  @interface ExcelField {
    int column() default 0;

    String name() default "";

    /**
     * 仅对Date类型的字段生效，true-精确到日期，false-精确到秒
     */
    boolean dateOnly() default false;
}
