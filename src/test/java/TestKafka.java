import kafka.admin.AdminUtils;
import kafka.server.ConfigType;
import kafka.utils.ZkUtils;
import org.apache.kafka.common.security.JaasUtils;
import org.junit.Test;
import scala.collection.Seq;
import scala.collection.convert.Wrappers;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Description: TestKafka
 * Author: DIYILIU
 * Update: 2018-01-03 14:11
 */
public class TestKafka {

    @Test
    public void test() {
        ZkUtils zkUtils = ZkUtils.apply("192.168.1.153:2181", 30000, 30000, JaasUtils.isZkSecurityEnabled());
        // 获取topic ‘test‘的topic属性属性
        Properties props = AdminUtils.fetchEntityConfig(zkUtils, ConfigType.Topic(), "test");
        // 查询topic-level属性
        Iterator it = props.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Object key = entry.getKey();
            Object value = entry.getValue();
            System.out.println(key + " = " + value);
        }

        zkUtils.close();
    }

    @Test
    public void test1() {
        ZkUtils zkUtils = ZkUtils.apply("192.168.1.153:2181", 30000, 30000, JaasUtils.isZkSecurityEnabled());

        Seq<String> topics = zkUtils.getAllTopics();

        System.out.println(topics.length());

        Wrappers.JListWrapper listWrapper = (Wrappers.JListWrapper) topics;

        List<String> list = listWrapper.underlying();
        for (String t: list){

            System.out.println(t);
        }

        zkUtils.close();
    }
}
