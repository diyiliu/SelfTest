import org.apache.commons.net.telnet.TelnetClient;
import org.junit.Test;

import java.io.*;
import java.net.SocketException;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * Description: testDos
 * Author: DIYILIU
 * Update: 2017-08-14 17:15
 */
public class testDos {


    @Test
    public void test() {

        BufferedReader br = null;
        try {
            Process p = Runtime.getRuntime().exec("ipconfig");
            br = new BufferedReader(new InputStreamReader(p.getInputStream(), Charset.forName("GBK")));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Test
    public void test1() {
        try {

            TelnetClient tc = new TelnetClient();
            tc.connect("192.168.1.1", 23);
            InputStream in = tc.getInputStream();
            OutputStream os = tc.getOutputStream();

            System.out.print(readUntil(":", in));
            writeUtil("wgzy+2013", os);
            System.out.print(readUntil(">", in));

            writeUtil("en", os);
            System.out.print(readUntil(":", in));

            writeUtil("tzxw&xutz", os);
            System.out.print(readUntil("#", in));

            writeUtil("conf t", os);
            System.out.print(readUntil("#", in));

            writeUtil("sh ru", os);
            System.out.print(readUntil("<--- More --->", in));

           /* writeUtil(" ", os);
            System.out.print(readUntil("<--- More --->", in));
            writeUtil(" ", os);
            System.out.print(readUntil("<--- More --->", in));
            writeUtil(" ", os);
            System.out.print(readUntil("<--- More --->", in));*/
            /*System.out.print(readUntil("<--- More --->", in));

            writeUtil(" ", os);
            System.out.print(readUntil("<--- More --->", in));

            writeUtil(" ", os);
            System.out.print(readUntil("<--- More --->", in));

            writeUtil(" ", os);
            System.out.print(readUntil("<--- More --->", in));*/

            /*writeUtil("freewificom", os);
            System.out.print(readUntil("root@WiAC:~#", in));


            writeUtil("ls -l / ", os);
            System.out.print(readUntil("root@WiAC:~#", in));

            writeUtil("ip addr ", os);
            System.out.print(readUntil("root@WiAC:~#", in));*/

            /*writeUtil("ip route ", os);
            System.out.print(readUntil("root@WiAC:~#", in));*/

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        TelnetClient tc = new TelnetClient();
        try {
            tc.connect("192.168.1.1", 23);

            InputStream in = tc.getInputStream();
            OutputStream os = tc.getOutputStream();

            ShowThread showThread = new ShowThread(in);
            new Thread(showThread).start();

            while (true) {
                BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
                String str = input.readLine();
                writeUtil(str.trim(), os);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 写入命令方法
     *
     * @param cmd
     * @param os
     */
    public static void writeUtil(String cmd, OutputStream os) {
        try {
            cmd = cmd + "\n";
            os.write(cmd.getBytes());
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 读到指定位置,不在向下读
     *
     * @param endFlag
     * @param in
     * @return
     */
    public static String readUntil(String endFlag, InputStream in) {

        InputStreamReader isr = new InputStreamReader(in);

        char[] charBytes = new char[1024];
        int n;
        boolean flag = false;
        String str = "";
        try {
            while ((n = isr.read(charBytes)) != -1) {
                for (int i = 0; i < n; i++) {
                    char c = charBytes[i];
                    str += c;
                    //当拼接的字符串以指定的字符串结尾时,不在继续读
                    if (str.endsWith(endFlag)) {
                        flag = true;
                        break;
                    }
                    System.out.println(n);
                }
                if (flag) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return str;
    }


    public static String readLine(InputStream inputStream) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        try {
            String str;
            while ((str = reader.readLine()) != null) {

                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}


class ShowThread implements Runnable {

    private InputStream in;

    public ShowThread(InputStream in) {
        this.in = in;
    }

    @Override
    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        try {
            String str;
            while ((str = reader.readLine()) != null) {

                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}