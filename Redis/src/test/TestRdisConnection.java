package test;

import redis.clients.jedis.Jedis;

/**
 * ��������Redis�Ƿ����ӳɹ�
 * @author admin
 *
 */
public class TestRdisConnection {

	public static void main(String[] args) {
		Jedis d=new Jedis("127.0.0.1",6379);
		System.out.println("�Ƿ����ӳɹ���,ping---->"+d.ping());
		d.set("fulan", "caonima");
		System.out.println(d.get("fulan"));
	}
}
