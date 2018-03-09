import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.diyiliu.model.Animal;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: TestJson
 * Author: DIYILIU
 * Update: 2017-12-08 11:34
 */
public class TestJson<T> {


    private List<T> list = new ArrayList<>();

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Test
    public void test(){

        Animal animal = new Animal();
        animal.setName("点点");

        TestJson<Animal> tj = new TestJson();
        tj.list.add(animal);

        String str = JSON.toJSONString(tj);
        System.out.println(str);

        TestJson t = JSON.parseObject(str, new TypeReference<TestJson<Animal>>(){});
        System.out.println(t.getList().size());
    }
}
