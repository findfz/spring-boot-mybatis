package com.blog.config;
/**
 * Created by xiongZhan on 2018/4/17.
 */

import com.alibaba.fastjson.JSON;
import com.blog.util.UidUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.jboss.logging.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Author：xiongzhan
 * Description：日志切面
 * Date：2018-04-17 15:40
 **/
@Aspect
@Component
@Slf4j
public class WebLogAspect {

    ThreadLocal<Long> startTime = new ThreadLocal<>();

    ThreadLocal<String> reqId = new ThreadLocal<>();

    //如果你需要拦截指定package指定规则名称的方法，可以使用表达式execution(...)
    @Pointcut("execution(public * com.blog.*.controller.*Controller.*(..))")
    public void webLog(){}

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        reqId.set(UidUtil.uuid());
        startTime.set(System.currentTimeMillis());

        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        MDC.put("userId",123456);

        // 记录下请求内容,使用{}占位符。避免字符串连接操作，减少String对象（不可变）带来的内存开销
        log.info("reqId : {}, URL : {}", reqId.get().substring(0,6), request.getRequestURL().toString());
        log.info("reqId : {}, HTTP_METHOD : {}", reqId.get().substring(0,6), request.getMethod());
        log.info("reqId : {}, IP : {}", reqId.get().substring(0,6), request.getRemoteAddr());
        log.info("reqId : {}, CLASS_METHOD : {}", reqId.get(), joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("reqId : {}, ARGS : {}", reqId.get(), Arrays.toString(joinPoint.getArgs()));

    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容  
        log.info("reqId : {}, RESPONSE : {}", reqId.get(), JSON.toJSONString(ret));
        log.info("reqId : {}, SPEND TIME : {}", reqId.get(),(System.currentTimeMillis() - startTime.get()));
    }

}
