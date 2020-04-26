package com.atliwen.domain.validator;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 自定义验证注解示例  todo   当现有验证注解满足不了验证情况的时候使用，不需要请删除
 * @author 李文
 * @date 2017-07-26 15:19
 **/
public class StringValidatorImpl implements ConstraintValidator<StringValidator, String>
{
    private String[] gender;
    @Override
    public void initialize(StringValidator v) {
        this.gender = v.gender();
    }
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
      return   !StringUtils.isEmpty(s) &&("女".equals(s) || "男".equals(s));
    }

}
