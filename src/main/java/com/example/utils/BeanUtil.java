package com.example.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.annotation.NeedSetValue;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;

/**
 * Created on 2020/2/19.
 *
 * @author Wang Qin
 */
@Component
public class BeanUtil implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (this.applicationContext == null) {
            this.applicationContext = applicationContext;
        }
    }

    public  void setFieldValueForSingle(Object object) throws Exception{
        // 获取要修改的对象
        Class<?> clazz = object.getClass();
        // 我们是要补充字段数据,获取目标字段
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            //获取字段上的指定注解
            NeedSetValue annotation = declaredField.getAnnotation(NeedSetValue.class);
            if(annotation==null){
                continue;
            }
            //获取到含有指定注解的字段之后,字段暴露
            declaredField.setAccessible(true);
            //执行注解上的方法,获取需要的数据,UserService.findById
            //获取类 -- 从Spring中获取数据
            Object bean = this.applicationContext.getBean(annotation.beanClass());
            //获取方法 -- 参数是入参类型
            Method method = bean.getClass().getMethod(annotation.method(),
                    clazz.getDeclaredField(annotation.param()).getType());
            //获取参数字段的值
            Field paramField = clazz.getDeclaredField(annotation.param());
            //字段不进行访问检查
            paramField.setAccessible(true);
            //目标字段
            Field targetField = null;
            //构建缓存key
            String keyPreFix = annotation.beanClass() +"_"+ annotation.method()+"_"+annotation.targetFiled()+":";
            //字段属性
            Object paramValue = paramField.get(object);
            if(paramValue == null){
                continue;
            }
            Object targetValue = null;
            Object targetFieldValue = null;
            String key = keyPreFix + paramValue.toString();
            if (redisTemplate.hasKey(key)) {
                String jsonStr = (String) redisTemplate.opsForValue().get(key);
                targetValue = JSONArray.parseObject(jsonStr,annotation.targetClass());
            }else{
                targetValue = method.invoke(bean, paramValue);
                String jsonStr = JSONObject.toJSONString(targetValue);
                //获取到值之后进行缓存
                redisTemplate.opsForValue().set(key, jsonStr);
            }
            if (targetValue != null) {
                if(targetField == null){
                    targetField = targetValue.getClass().getDeclaredField(annotation.targetFiled());
                    targetField.setAccessible(true);
                }
                targetFieldValue = targetField.get(targetValue);
            }
            declaredField.set(object, targetFieldValue);
        }
    }

    public void setFiledValueForCollection(Collection col) throws Exception {

        Class<?> clazz = col.iterator().next().getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field needField : fields) {
            NeedSetValue sv = needField.getAnnotation(NeedSetValue.class);
            if(sv == null){
                continue;
            }
            needField.setAccessible(true);
            Object bean = this.applicationContext.getBean(sv.beanClass());

            // method
            Method method = sv.beanClass().getMethod(sv.method(), clazz.getDeclaredField(sv.param()).getType());

            Field paramField = clazz.getDeclaredField(sv.param());
            paramField.setAccessible(true);

            Field targetField = null;
            String keyPreFix = sv.beanClass() + "_" + sv.method() + "_" + sv.targetFiled() + ":";
            for(Object object: col){
                Object paramValue = paramField.get(object);
                if(paramValue == null){
                    continue;
                }
                Object targetValue = null;
                Object targetFieldValue = null;
                String key = keyPreFix + paramValue.toString();
                if (redisTemplate.hasKey(key)) {
                    String jsonStr = (String) redisTemplate.opsForValue().get(key);
                    targetValue = JSONArray.parseObject(jsonStr,sv.targetClass());
                }else{
                    targetValue = method.invoke(bean, paramValue);
                    String jsonStr = JSONObject.toJSONString(targetValue);
                    //获取到值之后进行缓存
                    redisTemplate.opsForValue().set(key, jsonStr);
                }
                if(targetValue!=null){
                    if(targetField==null){
                        targetField = targetValue.getClass().getDeclaredField(sv.targetFiled());
                        targetField.setAccessible(true);
                    }
                    targetFieldValue = targetField.get(targetValue);
                }
                needField.set(object, targetFieldValue);
            }

        }
        

    }


}
