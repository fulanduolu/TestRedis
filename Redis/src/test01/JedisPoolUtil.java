package test01;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Redis连接池工具类(单例模式)
 * 
 * 首先构造方法私有化，不对外提供构造方法
 * 其次再声明一个需要的对象为null
 * 最后，提供一个public的方法，来提供这个对象（懒汉模式与饿汉模式。  饿汉模式是线程安全的）
 * @author admin
 *
 */
public class JedisPoolUtil {
	
	private static volatile JedisPool jedisPool=null;
	
	private JedisPoolUtil(){}
	
	public static JedisPool getJedisPoolInstance(){
		if(null ==jedisPool){
			synchronized (JedisPoolUtil.class) {
				if(null == jedisPool){
					//连接池的配置
					JedisPoolConfig config=new JedisPoolConfig();
					config.setMaxActive(1000);
					config.setMaxIdle(30);
					config.setMaxWait(100*1000);
					config.setTestOnBorrow(true);
					jedisPool = new JedisPool(config,"127.0.0.1",6379);
				}
			}
		}
		
		return jedisPool;
	}

	public static void release(JedisPool jedisPool,Jedis jedis){
		if(null!=jedis){
			jedisPool.returnResourceObject(jedis);
		}
	}
}
