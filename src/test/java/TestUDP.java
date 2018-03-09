import org.junit.Test;
import sun.misc.Unsafe;

import java.net.*;

/**
 * Description: TestUDP
 * Author: DIYILIU
 * Update: 2017-08-01 16:47
 */
public class TestUDP {


    @Test
    public void test() throws Exception {
        DatagramSocket client = new DatagramSocket();

        String sendStr = "Hello! I'm Client";
        byte[] sendBuf;
        sendBuf = sendStr.getBytes();

        InetAddress addr = InetAddress.getByName("192.168.1.154");
        int port = 8989;

        DatagramPacket sendPacket
                = new DatagramPacket(sendBuf, sendBuf.length, addr, port);

        client.send(sendPacket);

        byte[] recvBuf = new byte[100];
        DatagramPacket recvPacket
                = new DatagramPacket(recvBuf, recvBuf.length);
        client.receive(recvPacket);
        String recvStr = new String(recvPacket.getData(), 0, recvPacket.getLength());
        System.out.println("收到:" + recvStr);

        client.close();
    }


    @Test
    public void test1() throws Exception {

        //终端[15951778296]
        String content = "000E03B6CCD1F8040101020193814B0D0A";

        //content = "002403B6CCD1F80401010211EE830234509205D21ACA003200000000200E1108010A243AED0D0A";

        // 13705176744 0x87
        //content =  "003C0330E466A804010202003187020B2C9F06FD47EC004400000004200E1108090F37360001000400019196000200010F000300025DD700040001008D0D0A";
        // 80
        // content = "001A0330E466A8040102020F6C80020B2C9F06FD47EC004400000004200E11080A490D0A";

        //18795892484  0x80
        // content = "00240460528F0404010203000080020B351306FD3C56000000000000008E1108090F37181D0D0A";
        content = "0012036F49008004010203000F85020B350906FD3DB20000002B000000AF11090F0E2220D10D0A";

        byte[] bytes = hexStringToBytes(content);

        SocketAddress address = new InetSocketAddress("58.213.118.25", 8988);
        DatagramSocket socket = new DatagramSocket();

        DatagramPacket packet = new DatagramPacket(bytes, bytes.length, address);
        socket.send(packet);
        System.out.println("发送成功!");
        socket.close();
    }


    @Test
    public void test2() throws Exception{

        // 终端13705176744
        String content = "001A0330E466A804010202052380020B2C9F06FD47EC00440000000022061108121A0D0A";
        byte[] bytes = hexStringToBytes(content);

        SocketAddress address = new InetSocketAddress("192.168.1.154", 8988);
        DatagramSocket socket = new DatagramSocket();

        DatagramPacket packet = new DatagramPacket(bytes, bytes.length, address);
        socket.send(packet);
        System.out.println("发送成功!");
        socket.close();
    }

    public static byte[] hexStringToBytes(String hex) {

        char[] charArray = hex.toCharArray();

        if (charArray.length % 2 != 0) {
            // 无法转义
            return null;
        }

        int length = charArray.length / 2;
        byte[] bytes = new byte[length];

        for (int i = 0; i < length; i++) {

            String b = new String(new char[]{charArray[i * 2], charArray[i * 2 + 1]});
            bytes[i] = (byte) Integer.parseInt(b, 16);
        }

        return bytes;
    }
}
