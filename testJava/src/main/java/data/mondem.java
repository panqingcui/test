/*
 * Copyright (c) 2014-2015 Shandong Antrol Network Technology Co.Ltd. All rights reserved.
 * 版权所有(c) 2014-2015 山东蚁巡网络科技有限公司。保留所有权利。
 */
package data;

/**
 * <p>功能描述,该部分必须以中文句号结尾。<p>
 * 
 * 创建日期 2015年8月25日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 1.0.0
 */
public class mondem {
    public native int SetModemType(int ComNo, int ModemType);

    public native int GetModemType(int ComNo);

    public native int InitModem(int PortNo);

    public native int SendMsg(int PortNo, String strHeader, String strMsg);

    public native String[] ReadMsgEx(int PortNo);

    public native int CloseModem(int PortNo);

    public native int GetPortMax();

    public native int GetStatus(int PortNo);

    public native int GetSndCount(int PortNo);

    public native int GetRecCount(int PortNo);

    public native int ClrSndBuf(int PortNo);

    public native int ClrRecBuf(int PortNo);

    public native int SetReceive(int Type);

    public native int CancelSend(int Count);

    public native int SetDelayTime(int PortNo, int DelayTime);

    public native String[] WapPushCvt(String strTitle, String strUrl);

    public native int SetThreadMode(int Mode);

    // public native int MonInitModem(String strDev,int num);
    // public native int MonSendMsg(int Chno,String strHeader,String strMsg);
    // public native String[] MonGetMsg(int Chno);
    // public native int MonCloseModem();
    static {
        System.loadLibrary("mondem");
    }

    public static void main(String args[]) {}
}
