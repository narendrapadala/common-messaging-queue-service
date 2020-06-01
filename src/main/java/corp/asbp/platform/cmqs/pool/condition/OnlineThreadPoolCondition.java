package corp.asbp.platform.cmqs.pool.condition;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import corp.asbp.platform.cmqs.util.CommonSpringConditionUtil;
/**
 * @author Narendra Padala
 *
 */
public class OnlineThreadPoolCondition implements Condition {
	
	private static Logger LOGGER = LoggerFactory.getLogger(OnlineThreadPoolCondition.class);
	
	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		LOGGER.info("creating online thread pool condition...");
		return CommonSpringConditionUtil.isConditionSatisfied(context, "common.thread.pool.enabled", true) ? true : false;
	}

}
