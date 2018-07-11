package com.cloud.web.common.data;

import com.cloud.web.common.data.pojo.LicenceObj;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.Map;

@Component
@Aspect
public class LicenceAccessAspect {
    private static final Logger logger = Logger.getLogger(LicenceAccessAspect.class);

    @Resource
    private LicenceHandlerService licenceService;

    private ThreadLocal<LicenceObj> lcObj = new ThreadLocal<LicenceObj>();

    @Pointcut(value = "@annotation(com.cloud.web.common.data.LicenceAccess)")
    public void access() {
        logger.info("start");
    }

    @Before("access()")
    public void deBefore(JoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Map params = request.getParameterMap();
        String token = request.getParameter("token");
        LicenceObj obj = licenceService.loadLicence(token,true);
        if(obj == null){
            logger.warn(token + "无权访问" + request.getRequestURI());
            throw new Exception(token + "无效");
        }
        lcObj.set(obj);
    }

    @Around("@annotation(accessCode)")
    public Object around(ProceedingJoinPoint pjp, LicenceAccess accessCode) throws Exception {
        try {
            Object rs = pjp.proceed();
            licenceService.accessLicence(lcObj.get());
            return rs;
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new Exception(throwable);
        }
    }
}
