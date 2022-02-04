package io.skyhive.veneer.common.handlers;

import io.skyhive.veneer.common.annotation.SkyhiveRestTransactional;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * The type Skyhive rest transactional handler.
 *
 * @author krishna
 * @created 17 /11/21
 * @project skyhive -veeneer
 */
@Aspect
@Component
@Slf4j
public class SkyhiveRestTransactionalHandler {

    /**
     * Handle transaction object.
     *
     * @param joinPoint the join point
     * @return the object
     * @throws Throwable the throwable
     */
    @Around("@annotation(io.skyhive.veneer.common.annotation.SkyhiveRestTransactional)")
    public Object handleTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        Object[] joinPointArgs = joinPoint.getArgs();
        Object thisObject = joinPoint.getTarget();

        SkyhiveRestTransactional skyhiveAnnotation = method.getAnnotation(SkyhiveRestTransactional.class);
        try {
            return joinPoint.proceed();
        } catch (Throwable ex) {
            log.error("Exception ", ex);
            getRollbackMethod(thisObject, skyhiveAnnotation.serviceObject());
            throw ex;
        }
    }

    private String getMethodName(Field field) throws NoSuchMethodException {
        return Arrays.stream(field.getDeclaringClass().getMethods())
                .filter(method -> method.isAnnotationPresent(DeleteMapping.class))
                .findFirst().orElseThrow(() -> new NoSuchMethodException()).getName();
    }

    private Method getMethodByName(Class cls, String name) throws NoSuchMethodException {
        return Arrays.stream(cls.getMethods()).filter(method -> method.getName().equals(name))
                .findFirst().orElseThrow(() -> new NoSuchMethodException());
    }

    private void getRollbackMethod(Object currentObject, String fieldName) {
        try {
            Field field = currentObject.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            Object fieldObject = field.get(currentObject);
            String methodName = getMethodName(field);
            Method method = getMethodByName(fieldObject.getClass(), methodName);
            // TODO: Need to get the parameter to
            method.invoke(fieldObject, "testing");
        } catch (NoSuchFieldException | IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
            // Ignore this exception and continue
            ex.printStackTrace();
        }
    }
}
