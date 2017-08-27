package test01;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Redis���ӳع�����(����ģʽ)
 * 
 * ���ȹ��췽��˽�л����������ṩ���췽��
 * ���������һ����Ҫ�Ķ���Ϊnull
 * ����ṩһ��public�ķ��������ṩ�����������ģʽ�����ģʽ��  ����ģʽ���̰߳�ȫ�ģ�
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
					//���ӳص�����
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
