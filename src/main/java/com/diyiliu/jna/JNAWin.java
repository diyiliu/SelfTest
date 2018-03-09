package com.diyiliu.jna;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;

/**
 * Description: JNAWin
 * Author: DIYILIU
 * Update: 2018-03-09 10:53
 */
public class JNAWin {
    public static void main(String[] args){
        String windowName = "TIM";
        WinDef.HWND hwnd = User32.INSTANCE.FindWindow(null,windowName);
        if (hwnd==null)System.out.println("Miss!");
        else {
            System.out.println("Hit!");
            boolean showed = User32.INSTANCE.ShowWindow(hwnd, WinUser.SW_RESTORE );
            System.out.println(windowName+(showed?"窗口之前可见.":"窗口之前不可见."));
        }
    }
}
