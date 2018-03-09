import com.diyiliu.jna.Win32Util;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.BaseTSD;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Description: TestJNA
 * Author: DIYILIU
 * Update: 2018-03-09 09:22
 */
public class TestJNA {

    @Test
    public void test() {
        final User32 user32 = User32.INSTANCE;
        user32.EnumWindows(new WinUser.WNDENUMPROC() {
            int count = 0;

            @Override
            public boolean callback(WinDef.HWND hWnd, Pointer data) {
                char[] windowText = new char[512];
                user32.GetWindowText(hWnd, windowText, 512);
                String wText = new String(windowText);

                if (wText.isEmpty()) {
                    return true;
                }

                System.out.println("Found window with text " + hWnd + ", total " + ++count
                        + " Text: " + wText);
                return true;
            }
        }, null);
    }

    @Test
    public void test1() {
        WinDef.HWND hwnd = User32.INSTANCE.FindWindow
                (null, "TIM"); // 第一个参数是Windows窗体的窗体类，第二个参数是窗体的标题。不熟悉windows编程的需要先找一些Windows窗体数据结构的知识来看看，还有windows消息循环处理，其他的东西不用看太多。
        if (hwnd == null) {
            System.out.println("TIM is not running");
        } else {
            User32.INSTANCE.ShowWindow(hwnd, 9);        // SW_RESTORE
            User32.INSTANCE.SetForegroundWindow(hwnd);   // bring to front

            //User32.INSTANCE.GetForegroundWindow() //获取现在前台窗口
            WinDef.RECT qqwin_rect = new WinDef.RECT();
            User32.INSTANCE.GetWindowRect(hwnd, qqwin_rect);
            int qqwin_width = qqwin_rect.right - qqwin_rect.left;
            int qqwin_height = qqwin_rect.bottom - qqwin_rect.top;

            User32.INSTANCE.MoveWindow(hwnd, 700, 100, qqwin_width, qqwin_height, true);
            for (int i = 700; i > 100; i -= 10) {
                User32.INSTANCE.MoveWindow(hwnd, i, 100, qqwin_width, qqwin_height, true);   // bring to front
                try {
                    Thread.sleep(80);
                } catch (Exception e) {
                }
            }
            //User32.INSTANCE.PostMessage(hwnd, WinUser.WM_CLOSE, null, null);  // can be WM_QUIT in some occasio
        }

    }

    @Test
    public void test2() {
        WinDef.HWND hwnd = User32.INSTANCE.FindWindow(null, "TIM"); // 第一个参数是Windows窗体的窗体类，第二个参数是窗体的标题。不熟悉windows编程的需要先找一些Windows窗体数据结构的知识来看看，还有windows消息循环处理，其他的东西不用看太多。
        if (hwnd == null) {
            System.out.println("TIM is not running");
        } else {
            User32.INSTANCE.ShowWindow(hwnd, 9);        // SW_RESTORE
            User32.INSTANCE.SetForegroundWindow(hwnd);   // bring to front

            String username = "572772828";
            for (Character c : username.toCharArray())
                sendChar(c);
        }
    }

    static WinUser.INPUT input = new WinUser.INPUT();

    static void sendChar(char ch) {

        input.type = new WinDef.DWORD(WinUser.INPUT.INPUT_KEYBOARD);
        input.input.setType("ki"); // Because setting INPUT_INPUT_KEYBOARD is not enough: https://groups.google.com/d/msg/jna-users/NDBGwC1VZbU/cjYCQ1CjBwAJ
        input.input.ki.wScan = new WinDef.WORD(0);
        input.input.ki.time = new WinDef.DWORD(0);
        input.input.ki.dwExtraInfo = new BaseTSD.ULONG_PTR(0);
        // Press
        input.input.ki.wVk = new WinDef.WORD(Character.toUpperCase(ch)); // 0x41
        input.input.ki.dwFlags = new WinDef.DWORD(0);  // keydown

        User32.INSTANCE.SendInput(new WinDef.DWORD(1), (WinUser.INPUT[]) input.toArray(1), input.size());

        // Release
        input.input.ki.wVk = new WinDef.WORD(Character.toUpperCase(ch)); // 0x41
        input.input.ki.dwFlags = new WinDef.DWORD(2);  // keyup

        User32.INSTANCE.SendInput(new WinDef.DWORD(1), (WinUser.INPUT[]) input.toArray(1), input.size());
    }


//    ==========================================================

    @Test
    public void testAlipayPasswordInput() {
        String password = "your password";
        WinDef.HWND alipayEdit = findHandle("Chrome_RenderWidgetHostHWND", "Edit"); //Chrome浏览器，使用Spy++可以抓取句柄的参数
        assertThat("获取支付宝密码控件失败。", alipayEdit, notNullValue());
        boolean isSuccess = Win32Util.simulateCharInput(alipayEdit, password);
        assertThat("输入支付宝密码[" + password + "]失败。", isSuccess, is(true));
    }

    private WinDef.HWND findHandle(String browserClassName, String alieditClassName) {
        WinDef.HWND browser = Win32Util.findHandleByClassName(browserClassName, 10, TimeUnit.SECONDS);
        return Win32Util.findHandleByClassName(browser, alieditClassName, 10, TimeUnit.SECONDS);
    }
}
