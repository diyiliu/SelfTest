import com.diyiliu.model.Dog;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Description: TestStream
 * Author: DIYILIU
 * Update: 2017-10-26 16:17
 */
public class TestStream {

    private static List<Dog> dogs = new ArrayList(){
        {
            this.add(new Dog("java", 10));
            this.add(new Dog("c++", 10));
            this.add(new Dog("python", 12));
            this.add(new Dog("groovy", 11));
            this.add(new Dog("java", 14));
            this.add(new Dog("groovy", 12));
        }
    };

    @Test
    public void test1(){

        Dog dog = dogs.stream().filter(d -> d.getName().contentEquals("java")).findFirst().get();
        System.out.println(dog);
    }

    @Test
    public void test2(){

        List list = dogs.stream().filter(dog -> dog.getName().contentEquals("groovy")).collect(Collectors.toList());
        System.out.println(list);
    }


    /**
     * 分组
     */
    @Test
    public void test3(){

        Map<String, List<Dog>> map1 = dogs.stream().collect(Collectors.groupingBy(Dog::getName));
        System.out.println(map1);

        Map<Integer, List<Dog>> map2 = dogs.stream().collect(Collectors.groupingBy(Dog::getAge));
        System.out.println(map2);
    }

    /**
     * 提取
     */
    @Test
    public void test4(){

        Set<String> set = dogs.stream().map(Dog::getName).collect(Collectors.toSet());
        System.out.println(set);

        List<String> l = dogs.stream().map(Dog::getName).distinct().collect(Collectors.toList());
        System.out.println(l);
    }

    /**
     * 判断
     */
    @Test
    public void test5(){

        dogs.stream().filter(dog -> dog.getAge() == 10).forEach(dog -> dog.setAge(20));
        System.out.println(dogs);
    }
}
