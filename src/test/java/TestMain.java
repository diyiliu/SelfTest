import org.junit.Test;

/**
 * Description: TestMain
 * Author: DIYILIU
 * Update: 2017-10-11 17:07
 */
public class TestMain {


    @Test
    public void test(){

        int pos = 0x01;

        for (int i = 0 ; i < 10; i++){

            System.out.println(pos << i);
        }

    }
}
