package com.gethin.validator.validator.util;

import org.hibernate.validator.HibernateValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: gethin
 * @email: denggx3@chinaunicom.cn
 * @Date: 2019/5/14 9:53
 * @description:
 */
public class ValidationUtil {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 开启快速结束模式 failFast (true)
     */
    private static Validator validator = Validation.byProvider(HibernateValidator.class).configure().failFast(false).buildValidatorFactory().getValidator();


    /**
     * 校验注解对象是否满足校验注解，不满足则抛出异常
     *
     * @param o
     * @throws Exception
     */
    public static void validate(Object o) throws Exception {
        StringBuilder sb = null;
        try {
            List<String> results = validateBean(o);
            if (results != null && results.size() > 0) {
                sb = new StringBuilder();
                for (String result : results) {
                    sb.append(result + ",");
                }
            }
            if (sb != null) {
                throw new IllegalArgumentException(sb.substring(0, sb.length() - 1));
            }
        } catch (Exception e) {
            throw e;
        }
    }


    public static <E> List<String> validateBean(E bean) throws IllegalAccessException {
        List<String> result = new ArrayList<String>();
        Class clazz = bean.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (isBaseType(field)) {
                continue;
            } else if (isListOrArrayType(field)) {
                field.setAccessible(true);
                List list = (List) field.get(bean);
                if (list != null && list.size() > 0) {
                    for (Object o : list) {
                        result.addAll(validateBean(o));
                    }
                }
            } else {
                field.setAccessible(true);
                Object o = field.get(bean);
                if (o != null) {
                    result.addAll(validateBean(field.get(bean)));
                }
            }
        }
        Set<ConstraintViolation<E>> violationSet = validator.validate(bean);
        boolean hasError = violationSet != null && violationSet.size() > 0;
        if (hasError) {
            for (ConstraintViolation<E> violation : violationSet) {
                if (violation.getMessage() == null || "".equals(violation.getMessage())) {
                    result.add(violation.getPropertyPath() + violation.getMessage());
                } else {
                    result.add(violation.getMessage());
                }
            }
        }
        return result;
    }


//    /**
//     * 校验bean的某一个属性
//     *
//     * @param obj          bean
//     * @param propertyName 属性名称
//     * @return ValidResult
//     */
//    public static <T> ValidResult validateProperty(T obj, String propertyName) {
//        ValidResult result = new ValidResult();
//        Set<ConstraintViolation<T>> violationSet = validator.validateProperty(obj, propertyName);
//        boolean hasError = violationSet != null && violationSet.size() > 0;
//        result.setHasErrors(hasError);
//        if (hasError) {
//            for (ConstraintViolation<T> violation : violationSet) {
//                result.addError(propertyName, violation.getMessage());
//            }
//        }
//        return result;
//    }

    private static boolean isBaseType(Field field) {
        boolean noProcess = field.getType().isPrimitive() || field.getType() == String.class || field.getType() == Double.class || field.getType() == Integer.class ||
                field.getType() == Long.class || field.getType() == Float.class || field.getType() == Date.class || field.getType() == Short.class;
        return noProcess;
    }

    private static boolean isListOrArrayType(Field field) {
        return List.class.isAssignableFrom(field.getType()) || field.getClass().isArray();
    }

    public static void main(String args[]) {
        System.out.println(new ArrayList<String>().getClass().isAssignableFrom(List.class));
        System.out.println(List.class.isAssignableFrom(List.class));
    }

}
