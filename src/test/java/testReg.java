import org.junit.Test;

import java.util.regex.Pattern;

/**
 * Description: testReg
 * Author: DIYILIU
 * Update: 2017-08-16 15:10
 */
public class testReg {


    @Test
    public void test(){

       String str = "static (inside,outside) tcp 218.3.247.227 8082 192.168.1.52 8082 netmask 255.255.255.255 \n";

        String pattern = "^static \\(inside,outside\\) [tcp|udp][\\s\\S]*?";

        System.out.println(Pattern.matches(pattern, str));
    }


    @Test
    public void test1(){
        String str = "static (inside,outside) tcp 218.3.247.227 3206 192.168.1.32 6606 netmask 255.255.255.255";

        String[] array = str.split(" ");

        for (int i = 0; i < array.length; i++){

            System.out.println(i + ":" + array[i]);
        }
    }
}
