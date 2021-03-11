package cn.dreamplume.jedis.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Classname JedisUtil
 * @Description TODO
 * @Date 2021/3/11 15:17
 * @Created by 翊
 * Jedis 连接池对象提供 Jedis 对象的工具类
 */
public class JedisPoolUtil {

    // 创建 Jedis 连接池对象
    public JedisPool pool;

    public JedisPoolUtil() {
        // 获取 JedisUtil 配置文件的输入流对象
        InputStream input = JedisPoolUtil.class.getClassLoader().getResourceAsStream("cn/dreamplume/jedis/JedisUtil.properties");
        // 创建获取配置文件数据的 Properties 对象
        Properties pro = new Properties();
        // 将 JedisUtil 配置文件的输入流对象传入，用于 Properties 对象进行加载
        try {
            pro.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 获取Jedis池对象的获取配置文件对象 JedisPoolConfig 对象
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMinIdle(Integer.parseInt(pro.getProperty("setMinIdle")));  // 设置最小限制闲置个数
        poolConfig.setMaxIdle(Integer.parseInt(pro.getProperty("setMaxIdle")));  // 设置最大限制闲置个数
        poolConfig.setMaxTotal(Integer.parseInt(pro.getProperty("setMaxTotal")));  // 设置连接池中最多连接数

        // 创建连接池对象
        pool = new JedisPool(poolConfig, pro.getProperty("url"), Integer.parseInt(pro.getProperty("port")));
    }

    // 创建 getJedis() 方法来获取 JedisPool 连接池对象的 Jedis 资源的方法
    public Jedis getJedis() {
        return pool.getResource();
    }

}
