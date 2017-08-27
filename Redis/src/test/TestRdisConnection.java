package test;

import redis.clients.jedis.Jedis;

/**
 * 用来测试Redis是否连接成功
 * @author admin
 *
 */
public class TestRdisConnection {

	public static void main(String[] args) {
		Jedis d=new Jedis("127.0.0.1",6379);
		System.out.println("是否连接成功啊,ping---->"+d.ping());
		d.set("fulan", "caonima");
		System.out.println(d.get("fulan"));
	}
}
