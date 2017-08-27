package test01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class TestAPI {

	public static void main(String[] args) {
		Jedis jedis=new Jedis("127.0.0.1",6379);
		
//		jedis.set("k1", "v1");
//		jedis.set("k2", "v2");
//		jedis.set("k3", "v3");
//		
//		System.out.println(jedis.get("k1"));
//	
//		Set<String> set = jedis.keys("*");
//		System.out.println(set);
//		
		//进行String类型的api演示。
//		redisString(jedis);
		//进行List类型的api演示
//		redisList(jedis);
		//进行Set类型的api演示  (Set还有三种逻辑运算)
//		readSet(jedis);
		//进行Hash类型的api演示
//		readHash(jedis);
		//进行zset类型的api演示
		readZset(jedis);
		
	}
	public static void redisString(Jedis jedis){

		
//		jedis.set("fulan1", "fulanduolu");
//		jedis.set("fulan2", "fulanduoluya");
//		System.out.println(jedis.get("fulan1")+jedis.keys("*").size());
//		
//		//删除
//		System.out.println(jedis.del("k1"));
//		
//		System.out.println(jedis.strlen("fulan1"));
//		
//		jedis.set("v1", "2");
//		for(int i=0;i<10;i++){
//			jedis.incr("v1");
//		}
//		System.out.println(jedis.get("v1"));
//		jedis.incrBy("v1", 10);
//		System.out.println(jedis.get("v1"));
//		
//		System.out.println(jedis.getrange("v1", 0, 30));
		//在设置值的时候直接设置过期时间
//		jedis.setex("v2", 2, "100");
		System.out.println(jedis.get("v2"));
		
		jedis.mset("a1","1","a2","2","a3","3");
		System.out.println(jedis.mget("a1","a2","a3"));
		
		
		
	}

	public static void redisList(Jedis jedis){
		
		String[] arr=new String[]{"v1","v2"};
		jedis.lpush("myList", arr);
		
		List<String> list=jedis.lrange("myList", 0, -1);
		System.out.println(list);
	}

	public static void readSet(Jedis jedis){
		jedis.sadd("orders", "jd001");
		jedis.sadd("orders", "jd002");
		jedis.sadd("orders", "jd003");
		jedis.sadd("orders", "jd004");
		//避免重复
		jedis.sadd("orders", "jd004");
		Set<String> set=jedis.smembers("orders");
		System.out.println(set);
		jedis.srem("orders", "jd003");
		System.out.println(jedis.smembers("orders").size());
		
		//获取集合中的个数 
		System.out.println(jedis.scard("orders"));
	}
	
	public static void readHash(Jedis jedis){
		jedis.hset("hash1", "userName", "fulan");
		System.out.println(jedis.hget("hash1", "userName"));
		Map<String,String> map=new HashMap<String,String>();
		map.put("telephone", "110");
		map.put("address", "幻想乡");
		map.put("email", "321312@qq.com");
		jedis.hmset("hash2", map);
		List<String> list=jedis.hmget("hash2", "telephone","address","email");
		System.out.println(list);
	}

	public static void readZset(Jedis jedis){
		jedis.zadd("zset01", 60d,"v1");
		jedis.zadd("zset01", 60, "v2");
		jedis.zadd("zset01", 70, "v3");
		jedis.zadd("zset01", 30, "v4");
		jedis.zrem("zset01", "v2");
		System.out.println(jedis.zrangeWithScores("zset01", 0, -1));
		System.out.println(jedis.zrangeByScore("zset01", 40, 80));
		
	}
}
