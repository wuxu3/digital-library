package com.baizhi.common.aspect;


import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

@Configuration
@Aspect
public class MyAspect {

    @Autowired
    private Jedis jedis;

    @Around("@annotation(com.baizhi.common.annotation.Read)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        //目标对象的全限定名
        String clazzName = proceedingJoinPoint.getTarget().toString().split("@")[0];

        //方法名
        String methodName = proceedingJoinPoint.getSignature().getName().toString();

        //获取目标对象方法参数的集合
        Object[] args = proceedingJoinPoint.getArgs();

        Object proceed = null;

        for (Object arg : args) {

            methodName += arg;

        }

        MethodSignature signatrue = (MethodSignature) proceedingJoinPoint.getSignature();

        if (jedis.hexists(clazzName, methodName)) {

            String json = jedis.hget(clazzName, methodName);

            proceed = JSONObject.parseObject(json, signatrue.getMethod().getGenericReturnType());

        } else {

            proceed = proceedingJoinPoint.proceed();

            String json = JSONObject.toJSONStringWithDateFormat(proceed, "yyyy-MM-dd");

            jedis.hset(clazzName, methodName, json);

            proceed = JSONObject.parseObject(json, signatrue.getMethod().getGenericReturnType());

            //System.out.println(proceed);
        }

        return proceed;


    }


    @After("@annotation(com.baizhi.common.annotation.Write)")
    public void afterAdvice(JoinPoint joinpoint) {

        String clazzName = joinpoint.getTarget().toString().split("@")[0];

        jedis.del(clazzName);

    }


}
