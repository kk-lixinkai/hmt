package com.mybestcoding.hmt.aspect;

import com.alibaba.fastjson.JSON;
import com.mybestcoding.hmt.annotation.LogOperation;
import com.mybestcoding.hmt.model.ExecLogWithBLOBs;
import com.mybestcoding.hmt.model.OperLogWithBLOBs;
import com.mybestcoding.hmt.model.User;
import com.mybestcoding.hmt.service.ExecLogService;
import com.mybestcoding.hmt.service.OperLogService;
import com.mybestcoding.hmt.service.UserService;
import com.mybestcoding.hmt.util.CurrentUser;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: lixinkai
 * @description: 日志切面
 * @date: 2021/3/18 17:09
 * @GitHub: https://github.com/kk-lixinkai
 * @Gitee: https://gitee.com/bestbug
 * @version: 1.0
 */
@Slf4j
@Aspect
@Component
public class LogAspect {

    private String version = "1997";

    @Autowired
    private OperLogService operLogService;

    @Autowired
    private ExecLogService execLogService;


    @Autowired
    private CurrentUser currentUser;

    @Autowired
    private UserService userService;


    /**
     * 设置操作日志切入点 记录操作日志 在注解的位置切入代码
     */
    @Pointcut("@annotation(com.mybestcoding.hmt.annotation.LogOperation)")
    public void operLogPointCut() {
    }

    /**
     * 设置操作异常切入点记录异常日志 扫描所有controller包下操作
     */
    @Pointcut("execution(* com.mybestcoding.hmt.controller..*.*(..))")
    public void operExceptionLogPointCut() {
    }

    /**
     * 正常返回通知，拦截用户操作日志，连接点正常执行完成后执行，如果连接点抛出异常，则不执行
     *
     * @param joinPoint 切入点
     * @param keys      返回结果
     */
    @AfterReturning(value = "operLogPointCut()", returning = "keys")
    public void saveOperLog(JoinPoint joinPoint, Object keys) {
        // 获取 RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        // 从获取RequestAttribute中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);

        OperLogWithBLOBs operLogWithBLOBs = new OperLogWithBLOBs();

        try {

            // 从切面织入点出通过反射机制获取织入点处的方法
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            // 获取切入点所在的方法
            Method method = signature.getMethod();
            // 获取注解属性
            LogOperation logOper = method.getAnnotation(LogOperation.class);
            if (logOper != null) {
                String module = logOper.module();
                String type = logOper.type();
                String desc = logOper.desc();
                // 操作模块
                operLogWithBLOBs.setModule(module);
                // 操作类型
                operLogWithBLOBs.setType(type);
                // 操作描述
                operLogWithBLOBs.setDescription(desc);
            }
            // 获取请求的类名
            String className = joinPoint.getTarget().getClass().getName();
            // 获取请求的方法名
            String methodName = method.getName();
            methodName = className + ":" + methodName;
            // 设置请求方法
            operLogWithBLOBs.setMethod(methodName);

            // 请求的参数
            Map<String, String> rtnMap = convertMap(request.getParameterMap());
            // 将参数所在的数组转换成json
            String params = JSON.toJSONString(rtnMap);

            // 请求参数
            operLogWithBLOBs.setRequParam(params);
            // 返回结果
            operLogWithBLOBs.setRespParam(JSON.toJSONString(keys));
            // 请求用户ID
            //TODO:
            User user = userService.getByUserName(currentUser.getCurrentUser().getUsername());
            operLogWithBLOBs.setUserId(String.valueOf(user.getId()));
            //请求用户名
            operLogWithBLOBs.setUserName(currentUser.getCurrentUser().getUsername());
            //TODO:
            //请求IP
            operLogWithBLOBs.setIp(getRemoteHost(request));
            // 请求 URI
            operLogWithBLOBs.setUri(request.getRequestURI());
            // 创建时间
            operLogWithBLOBs.setCreateTime(new Date());
            // 操作版本
            operLogWithBLOBs.setVersion(version);
            operLogService.addLog(operLogWithBLOBs);
            log.info("打印操作日志:{}", operLogWithBLOBs.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 异常返回通知，用于拦截异常日志信息 连接点抛出异常后执行
     *
     * @param joinPoint 切入点
     * @param e         异常信息
     */
    @AfterThrowing(pointcut = "operExceptionLogPointCut()", throwing = "e")
    public void saveExceptionLog(JoinPoint joinPoint, Throwable e) {
        // 获取RequestAttribute
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        // 从RequestAttribute中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);

        ExecLogWithBLOBs execLogWithBLOBs = new ExecLogWithBLOBs();
        try {
            // 从切面织入点出通过反射机制获取织入点处的方法
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            // 获取切入点所在的方法
            Method method = signature.getMethod();
            // 获取请求的类型
            String className = joinPoint.getTarget().getClass().getName();
            // 获取请求方法名
            String methodName = method.getName();
            methodName = className + ":" + methodName;
            // 请求的参数
            Map<String, String> rtnMap = convertMap(request.getParameterMap());
            // 将参数所在的数组转换成json
            String params = JSON.toJSONString(rtnMap);
            // 请求参数
            execLogWithBLOBs.setRequParam(params);
            // 请求方法名
            execLogWithBLOBs.setMethod(methodName);
            // 异常名称
            execLogWithBLOBs.setName(e.getClass().getName());
            // 异常信息
            execLogWithBLOBs.setMethod(stackTrackToString(e.getClass().getName(), e.getMessage(), e.getStackTrace()));
            // 操作员ID
            //TODO:
            User user = userService.getByUserName(currentUser.getCurrentUser().getUsername());
            execLogWithBLOBs.setUserId(String.valueOf(user.getId()));
            //操作员名称
            //TODO:
            execLogWithBLOBs.setUserName(currentUser.getCurrentUser().getUsername());
            //操作URI
            execLogWithBLOBs.setUri(request.getRequestURI());
            // 操作员IP
            execLogWithBLOBs.setIp(getRemoteHost(request));
            // 操作版本号
            execLogWithBLOBs.setVersion(version);
            // 异常发生时间
            execLogWithBLOBs.setCreateTime(new Date());
            // 保存到数据库中
            execLogService.addLog(execLogWithBLOBs);
            log.info("异常日志:{}", execLogWithBLOBs.toString());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /**
     * 转换request：请求参数
     *
     * @param paramMap request获取的参数数组
     * @return
     */
    public Map<String, String> convertMap(Map<String, String[]> paramMap) {
        Map<String, String> rtnMap = new HashMap<>();
        for (String key : paramMap.keySet()) {
            rtnMap.put(key, paramMap.get(key)[0]);
        }
        return rtnMap;
    }

    /**
     * 转换异常信息为字符串
     *
     * @param exceptionName    异常名称
     * @param exceptionMessage 异常信息
     * @param elements         堆栈信息
     * @return
     */
    public String stackTrackToString(String exceptionName, String exceptionMessage, StackTraceElement[] elements) {
        StringBuffer strbuff = new StringBuffer();
        for (StackTraceElement stet : elements) {
            strbuff.append(stet + "\n");
        }
        String message = exceptionName + ":" + exceptionMessage + "\n\t" + strbuff.toString();
        return message;
    }

    /**
     * 获取客户端IP地址
     *
     * @param request
     * @return
     */
    public String getRemoteHost(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
    }
}
