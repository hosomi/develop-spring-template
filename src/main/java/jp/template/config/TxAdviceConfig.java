package jp.template.config;

import java.util.Collections;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.MatchAlwaysTransactionAttributeSource;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

/**
 * トランザクション管理設定。
 * <pre>
 * ・アノテーション指定なしで Exception が発生した場合、ロールバックする仕様。
 * 
 * </pre>
 * 
 * @author hosomi.
 */
@Aspect
@Configuration
public class TxAdviceConfig {

	@Autowired
	private PlatformTransactionManager transactionManager;

	@Bean
	public TransactionInterceptor txAdvice() {

		MatchAlwaysTransactionAttributeSource source = new MatchAlwaysTransactionAttributeSource();
		RuleBasedTransactionAttribute transactionAttribute = new RuleBasedTransactionAttribute();

		transactionAttribute.setName("*"); // 適用範囲（クラス名）
		transactionAttribute.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class))); // ロールバックするルールを一律　Exception が発生した場合にする。
		transactionAttribute.setTimeout(30);
		source.setTransactionAttribute(transactionAttribute);

		TransactionInterceptor txAdvice = new TransactionInterceptor(transactionManager, source);

		return txAdvice;
	}

	@Bean
	public Advisor txAdviceAdvisor() {

		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		pointcut.setExpression("execution(* jp.template..*.*(..))"); // トランザクション管理したい対象クラスを指定（ワイルドカード使用可能）
		return new DefaultPointcutAdvisor(pointcut, txAdvice());
	}
}
