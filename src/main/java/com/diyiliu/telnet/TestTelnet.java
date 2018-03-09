package com.diyiliu.telnet;

import org.apache.commons.net.telnet.TelnetClient;

import java.io.*;
import java.util.Queue;

/**
 * Description: TestTelnet
 * Author: DIYILIU
 * Update: 2017-08-16 09:42
 */
public class TestTelnet {


    public static void main(String[] args) {
        try {
            TelnetClient tc = new TelnetClient();
            tc.connect("192.168.1.1", 23);
            InputStream in = tc.getInputStream();
            OutputStream os = tc.getOutputStream();

            DisplayThread display = new DisplayThread(in);
            new Thread(display).start();

            writeUtil("wgzy+2013", os);
            writeUtil("en", os);
            writeUtil("tzxw&xutz", os);
            writeUtil("conf t", os);
            writeUtil("sh ru", os);

            Queue queue = display.getContent();
            String content = "";
            int i = 0;
            while (true) {
                if (!queue.isEmpty()) {
                    content = (String) queue.poll();
                    System.out.println("[" + ++i + "]" + content);
                } else {
                    writeUtil(" ", os);
                    Thread.sleep(1000);
                }

                if (content.contains(": end")){
                    System.out.println("结束！！！");
                    display.close();
                    tc.disconnect();

                    break;
                }
            }
        } catch (Exception e) {
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

}
