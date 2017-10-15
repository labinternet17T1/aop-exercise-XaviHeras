package cat.tecnocampus.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(LoggerAdvice.class);

    @Pointcut("execution(* cat.tecnocampus.controller.ClassroomController.*(..))")
    public void pointcutForAllMethods() {
    }

    @Before("pointcutForAllMethods()")
    public void beforePointcutForAllMethods() {
        logger.info("Working with a classroom");
    }

    @Pointcut("execution(* cat.tecnocampus.controller.ClassroomController.find*(..))")
    public void pointcutForMethodsWithFind() {
    }

    @After("pointcutForMethodsWithFind()")
    public void afterPointcutForMethodsWithFind() {
        logger.info("Finding classrooms");
    }

    @Pointcut("execution(* cat.tecnocampus.controller.ClassroomController.insertBatch(..))")
    public void pointcutForInsertBatch() {
    }

    @Around("pointcutForInsertBatch()")
    public void aroundPointcutForInsertBatch(ProceedingJoinPoint pjp) {
        logger.info("before multiple insert");

        try {
            pjp.proceed();
            logger.info("after multiple insert");
        } catch (Throwable throwable) {
            logger.error("could not insert one or more classrooms");
        }
    }

}
