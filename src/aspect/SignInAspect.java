package aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import com.members.model.MembersVO;

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
//	@Pointcut("execution(* com.members.model.MembersService.test(..))")
//	public void test() {}
//	
//	@Before("test()")
//	public void beforeLogAdvice(JoinPoint joinpoint) {
//		System.out.println("before:");
//		System.out.println(joinpoint);
//		System.out.println(joinpoint.getClass().getName());//用來取得joinpoint實際上是什麼類別
//		MembersVO memVO = (MembersVO)joinpoint.getArgs()[0];//用來取得傳入test(membersVO)的物件
//		System.out.println(memVO);
//		memVO.setMemAcc("321");//對物件內容進行修改
//		System.out.println(memVO.getMemAcc());
//		System.out.println(joinpoint.getTarget().getClass().getName());//用來取得被切入的方法所在的類別
//	}
	
}
