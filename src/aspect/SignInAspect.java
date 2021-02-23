package aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class SignInAspect {
	
	//要註冊pointcut的方法
//	@Pointcut("execution(void members.MembersService.signIn(..))")
//	private void signIn() {}
	
//	@Before("signIn()")
//	public void beforeLogAdvice(JoinPoint joinpoint) {
//		
//	}
	
	
	//test
//	@Pointcut("execution(* com.members.model.MembersService.test())")
//	public void test() {}
//	
//	@Before("test()")
//	public void beforeLogAdvice(JoinPoint joinpoint) {
//		System.out.println("before:");
//		System.out.println(joinpoint);
//	}
	
}
