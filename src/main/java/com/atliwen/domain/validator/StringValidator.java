package com.atliwen.domain.validator;

import javax.validation.Constraint;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * 自定义验证注解示例  todo   当现有验证注解满足不了验证情况的时候使用，不需要请删除
 * @author atliwen
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {StringValidatorImpl.class})
public @interface StringValidator
{
    String message() default "性别不等于 男 或 女 ";   // 约束注解验证时的输出消息
    Class[] groups() default { };  // 约束注解在验证时所属的组别
    Class[] payload() default { }; // 约束注解的有效负载
    String[] gender() default { "男","女" };
}
