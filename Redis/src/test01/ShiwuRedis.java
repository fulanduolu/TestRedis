package test01;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * RedisµÄÊÂÎñ
 * @author admin
 *
 */
public class ShiwuRedis {

	public static void main(String[] args) {
		Jedis jedis=new Jedis("127.0.0.1",6379);
		Transaction t=jedis.multi();
		t.set("v1", "k1");
		t.set("v2", "k2");
		t.discard();
	}
	
}
