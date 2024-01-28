package ru.egpt.core.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
@Slf4j
public class MeasureTimeAdvice {

  private final static Logger logger = LoggerFactory.getLogger(MeasureTimeAdvice.class);

  @Around("execution(public * *(..)) && @annotation(yourAnnotation)")
  public Object measureTime(ProceedingJoinPoint point, MeasureTime yourAnnotation) throws Throwable {
    StopWatch stopWatch = new StopWatch();
    stopWatch.start();
    Object object = point.proceed();
    stopWatch.stop();
    logger.info("Потрачено " + stopWatch.getTotalTimeSeconds() + " сек. на " + yourAnnotation.value());
    return object;
  }
}