package com.adobe.travelagency.aop;

import com.adobe.travelagency.entity.Logging;
import com.adobe.travelagency.repository.LoggingRepository;
import lombok.AllArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
@AllArgsConstructor
public class LoggerAspect {

    private final LoggingRepository loggingRepository;

    @Pointcut("execution(* com.adobe.travelagency.service.HolidayOfferService.*(..))")
    private void loggingHolidayOfferServicePointCut() {}

    @Around("loggingHolidayOfferServicePointCut()")
    public Object logOnExecution(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();

        Object result = null;
        Logging log = Logging.builder().localDateTime(LocalDateTime.now())
                .action(className + " ====> " + methodName)
                .build();

        try {
            result = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            log.setAction(log.getAction() + " failed with reason " + throwable.getMessage());

            saveLog(log);

            throw throwable;
        }

        saveLog(log);

        return result;
    }

    public void saveLog(Logging logging) {
        loggingRepository.save(logging);
    }
}
