package com.weigh.verification.aspect;

import com.auth0.jwt.interfaces.Claim;
import com.weigh.verification.annotation.PassAuth;
import com.weigh.verification.annotation.PassToken;
import com.weigh.verification.entity.Result;
import com.weigh.verification.entity.TokenUserEntity;
import com.weigh.verification.service.RbacAuthService;
import com.weigh.verification.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * @author xuyang
 */
@Slf4j
@Aspect
@Component
public class TokenAspect {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    RbacAuthService rbacAuthService;

    /**
     * 定义切入点，切入点为com.example.demo.aop.AopController中的所有函数
     * 通过@Pointcut注解声明频繁使用的切点表达式
     */
    @Pointcut("execution( * com.weigh.verification.controller..*.*(..)) || " +
            "execution(public * com.weigh.verification.controller..*.*(..))"
    )
    public void brokerAspect() {
    }

//    /**
//     * @description  在连接点执行之前执行的通知
//     */
//    @Before("BrokerAspect()")
//    public void doBeforeGame(){
//        System.out.println("在连接点执行之前执行的通知！");
//    }
//
//    /**
//     * @description  在连接点执行之后执行的通知（返回通知和异常通知的异常）
//     */
//    @After("BrokerAspect()")
//    public void doAfterGame(){
//        System.out.println("在连接点执行之后执行的通知（返回通知和异常通知的异常）！");
//    }
//
//    /**
//     * @description  在连接点执行之后执行的通知（返回通知）
//     */
//    @AfterReturning("BrokerAspect()")
//    public void doAfterReturningGame(){
//        System.out.println("在连接点执行之后执行的通知（返回通知）");
//    }
//
//    /**
//     * @description  在连接点执行之后执行的通知（异常通知）
//     */
//    @AfterThrowing("BrokerAspect()")
//    public void doAfterThrowingGame(){
//        System.out.println("在连接点执行之后执行的通知（异常通知）");
//    }

    /**
     * @description 使用环绕通知
     */
    @Around("brokerAspect()")
    public Object doAroundGameData(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            //进入controller层前
            beforePoint(joinPoint);
            //放行
            Object result = joinPoint.proceed();
            //返回数据前
            afterPoint(joinPoint, result);

            return result;
        } catch (RuntimeException e) {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            assert attributes != null;
            HttpServletResponse response = attributes.getResponse();
            assert response != null;

            Result errorResult = new Result();


            if (e.getMessage() != null) {
                response.setStatus(Integer.parseInt(e.getMessage()));
                errorResult.setCode(Integer.parseInt(e.getMessage()));
            } else {
                response.setStatus(400);
                errorResult.setCode(400);
            }

            errorResult.setMsg("系统异常");
            return errorResult;
        }
    }

    private void beforePoint(ProceedingJoinPoint joinPoint) throws Exception {
        // 得到要进入的是哪个controller方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        //检查是否有PassToken注释，有则跳过认证，所以在controller层加了@PassToken注解，这里就不校验
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return;
            }
        }

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();

        // 从 http 请求头中取出 token
        String token = request.getHeader("authorization");

        // 执行认证
        if (token == null) {
            throw new RuntimeException("401");
        }

        // 解析token并获取token中的用户信息
        Map<String, Claim> claims = jwtUtil.verity(token);


        //检查是否有PassAuth注释，有则跳过认证，所以在controller层加了@PassAuth注解，这里就不校验
        boolean isAdmin = false;
        if(claims.get("email") != null){
            isAdmin = "administrator".equals(claims.get("email").asString()) ? true : false;
        }

        if (!isAdmin) {
            PassAuth passAuth = method.getAnnotation(PassAuth.class);

            boolean isPassAuth = false;
            boolean isPassAuthRequired = false;
            if(passAuth != null){
                isPassAuth = true;

                isPassAuthRequired = passAuth.required();
            }

            if (!isPassAuth || !isPassAuthRequired) {
                String className = joinPoint.getTarget().getClass().getName().replace("com.weigh.verification.controller.", "");
                String methodName = signature.getName();
                String authKey = className + ":" + methodName;
                System.out.println(authKey);

                // 判断用户授权，判断用户是否拥有这个接口的权限
                Result res = rbacAuthService.getApiAuth(claims.get("userId").asInt());

                List<String> apiAuth = (List<String>) res.getData();
                System.out.println(apiAuth);
                if (!apiAuth.contains(authKey)) {
                    throw new RuntimeException("403");
                }

                // 根据用户数据权限获取数据获取范围，返回取数据的所有用户id


            }
        }


        // 得到这个方法控制器的所有形参
        Object[] args = joinPoint.getArgs();

        for (Object argItem : args) {
            //如果这个控制器方法中有用户这个形参，说明这个控制器需要用户的信息，那么我就把我这里解析出来的userId 赛进这个形参中，那样在控制器那边就能得到我赛的userId了
            if (argItem instanceof TokenUserEntity) {
                TokenUserEntity paramVO = (TokenUserEntity) argItem;
                // System.out.println(paramVO);
                Claim userId = claims.get("userId");
                Claim email = claims.get("email");
//                Claim role = claims.get("role");

                paramVO.setUserId(userId == null ? 0 : userId.asInt());
                paramVO.setEmail(email == null ? "" : email.asString());
//                paramVO.setRole(role.asString());
            }
        }
    }

    private void afterPoint(ProceedingJoinPoint joinPoint, Object result) throws Exception {
        //可以存储日志
        // log.info(result.toString());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        log.info(request.getServletPath());
        // System.out.println(result);
    }
}
