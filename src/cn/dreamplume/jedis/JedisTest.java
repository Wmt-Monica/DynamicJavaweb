package cn.dreamplume.jedis;

import redis.clients.jedis.Jedis;

/**
 * @Classname JedisTest
 * @Description TODO
 * @Date 2021/3/11 14:45
 * @Created by 翊
 */
public class JedisTest {
    public static void main(String[] args) {
        // 使用虚拟机中安装的redis数据库的虚拟机的IP号：192.168.94.129和redis使用的端口号6379
        // 注意：需要将虚拟机中的 ./redis-server redis.conf 打开redis服务器端口
        Jedis jedis = new Jedis("192.168.94.129",6379);
        String name = jedis.get("name");
        System.out.println(name);
    }
}
