package corp.asbp.platform.cmqs.pool;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import corp.asbp.platform.cmqs.util.CmqsProperties;
import corp.asbp.platform.cmqs.pool.condition.OnlineThreadPoolCondition;
/**
 * @author Narendra Padala
 *
 */
@Component
@Conditional(OnlineThreadPoolCondition.class)
public class OnlineThreadPool {
	private static final Logger LOGGER = LoggerFactory.getLogger(OnlineThreadPool.class);
	
	@Autowired
	private CmqsProperties properties;
	
	private ThreadPoolExecutor pool;

    @PostConstruct
    public void initialize() {
    	LOGGER.info("Initializing online thread pool...");
        pool = new ThreadPoolExecutor(properties.getThreadPoolCoreSize(), 
        							  properties.getThreadPoolMaxSize(), 
        							  30*60,
        							  TimeUnit.SECONDS, 
        							  new LinkedBlockingDeque<Runnable>());
        pool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
    }

    public ThreadPoolExecutor getThreadPoolExecutor() {
        return pool;
    }

    public int getThreadCount() {
        return pool.getActiveCount();
    }

    @PreDestroy
    public void cleanUp() {
        pool.shutdown();
    }
}
