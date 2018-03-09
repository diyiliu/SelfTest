import com.diyiliu.model.Animal;
import com.diyiliu.model.Dog;
import org.junit.Test;

/**
 * Description: testExtend
 * Author: DIYILIU
 * Update: 2017-08-09 09:16
 */
public class testExtend {

    @Test
    public void test(){

        Animal animal = new Animal();
        animal.setName("动物2");

        Dog dog = new Dog();
        System.out.println(dog.getName());
    }

}
