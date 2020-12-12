package com.li.aspect;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import com.alibaba.fastjson.JSON;
import com.li.model.WebLog;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;

/**
 * @author : LiWenBo
 * @program : coinexchange
 * @description : 日志切面
 * @date : 2020-12-12 17:25:25
 */
@Aspect
@Component
@Order(1)
@Slf4j
public class WebLogAspect {
    @Pointcut("execution( * com.li.controller.*.*(..))")
    public void webLog() {
    }

    @Around(value = "webLog()")
    public Object recordWebLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object result = null;
        StopWatch stopWatch = new StopWatch(); // 创建计时器
        stopWatch.start(); //  开始计时器
        result = proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs()); // 不需要我们自己处理这个异常
        stopWatch.stop(); // 记时结束

        // 获取请求的上下文
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        // 获取登录的用户
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 获取方法
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = methodSignature.getMethod();
        // 获取方法上的ApiOperation注解
        ApiOperation annotation = method.getAnnotation(ApiOperation.class);
        // 获取目标对象的类型名称
        String className = proceedingJoinPoint.getTarget().getClass().getName();
        // 获取请求的url 地址
        String requestUrl = request.getRequestURL().toString();
        WebLog webLog = new WebLog();
        /*
        WebLog.builder()
                .basePath(StrUtil.removeSuffix(requestUrl, URLUtil.url(requestUrl).getPath()))
                .description(annotation == null ? "no desc" : annotation.value())
                .ip(request.getRemoteAddr())
                .parameter(getMethodParameter(method, proceedingJoinPoint.getArgs()))
                .method(className + "." + method.getName())
                .result(request == null ? "" : JSON.toJSONString(request))
                .recodeTime(System.currentTimeMillis())
                .spendTime(stopWatch.getTotalTimeMillis())
                .uri(request.getRequestURI())
                .url(request.getRequestURL().toString())
                .username(authentication == null ? "anonymous" : authentication.getPrincipal().toString())
                .build();
        * */
        webLog.setUri(request.getRequestURI());
        webLog.setUrl(request.getRequestURL().toString());
        webLog.setBasePath(StrUtil.removeSuffix(requestUrl, URLUtil.url(requestUrl).getPath()));
        webLog.setUsername(authentication == null ? "anonymous" : authentication.getPrincipal().toString());
        webLog.setIp(request.getRemoteAddr());
        webLog.setDescription(annotation == null ? "no desc" : annotation.value());
        webLog.setMethod(className + "." + method.getName());
        webLog.setParameter(getMethodParameter(method, proceedingJoinPoint.getArgs()));
        webLog.setParameter(getMethodParameter(method, proceedingJoinPoint.getArgs()));
        webLog.setResult(result == null ? "" : result);
        log.info(JSON.toJSONString(webLog, true));
        return result;
    }

    /**
     * {
     * "":value,
     * "":"value"
     * }
     *
     * @param method
     * @param args
     * @return
     */
    private Object getMethodParameter(Method method, Object[] args) {
        LocalVariableTableParameterNameDiscoverer localVariableTableParameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();
        String[] parameterNames = localVariableTableParameterNameDiscoverer.getParameterNames(method);
        HashMap<String, Object> methodParameters = new HashMap<>();
        Parameter[] parameters = method.getParameters();
        if (args != null) {
            for (int i = 0; i < parameterNames.length; i++) {
                if (args[i] instanceof ServletRequest || args[i] instanceof ServletResponse || args[i] instanceof MultipartFile) {
                    //ServletRequest不能序列化，从入参里排除，否则报异常：java.lang.IllegalStateException: It is illegal to call this method if the current request is not in asynchronous mode (i.e. isAsyncStarted() returns false)
                    //ServletResponse不能序列化 从入参里排除，否则报异常：java.lang.IllegalStateException: getOutputStream() has already been called for this response
                    continue;
                }
                methodParameters.put(parameterNames[i], args[i] == null ? "" : JSON.toJSONString(args[i]));
            }
        }
        return methodParameters;
    }
}
