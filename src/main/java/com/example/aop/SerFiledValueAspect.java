package com.example.aop;

import com.example.utils.BeanUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

/**
 * Created on 2020/2/19.
 *
 * @author Wang Qin
 */
@Component
// 定义切面点
@Aspect //把当前类标志为一个切面,共容器使用
public class SerFiledValueAspect {

    @Autowired
    private BeanUtil beanUtil;

    @Around("@annotation(com.example.annotation.NeedSetFiledValue)")
    public Object doSetFiledValue(ProceedingJoinPoint point) throws Throwable {

        Object ret = point.proceed(); //执行完毕的结果集
        // 对ret进行处理(这时候的customer是带有注解的) --> 获取注解 --反射获取method --> invoke -- 设置值
        this.beanUtil.setFiledValueForCollection((Collection) ret);
        return ret;
    }

    @Around("@annotation(com.example.annotation.NeedSetFiledValueSingle)")
    public Object doSetFiledValueSingle(ProceedingJoinPoint pjp) throws Throwable{
        Object proceed = pjp.proceed();
        Optional.ofNullable(proceed).ifPresent(u->{
            try {
                this.beanUtil.setFieldValueForSingle(u);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        return proceed;
    }

}
