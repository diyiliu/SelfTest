import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * Description: TestRedis
 * Author: DIYILIU
 * Update: 2017-10-25 13:48
 */
public class TestRedis {

    @Test
    public void test(){

        // 连接本地的 Redis 服务
        Jedis jedis = new Jedis("192.168.1.156", 6379);
        System.out.println("连接 Redis 服务成功！");
        // 查看服务是否运行
        System.out.println("服务 正在运行: " + jedis.ping());

    }
}
