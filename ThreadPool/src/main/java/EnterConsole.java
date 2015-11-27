import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * Copyright (c) 2014-2015 Shandong Antrol Network Technology Co.Ltd. All rights reserved.
 * 版权所有(c) 2014-2015 山东蚁巡网络科技有限公司。保留所有权利。
 */
/**
 * <p>功能描述,该部分必须以中文句号结尾。<p>
 * 
 * 创建日期 2015年11月21日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 1.0.0
 */
public class EnterConsole {
    public Object printConsole() {
        System.out.print("请输入你要随机选取多少注，确认回车即可：");
        BufferedReader objBR = new BufferedReader(new InputStreamReader(System.in));
        String strValue = null;
        try {
            strValue = (String) objBR.readLine();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return strValue;
    }
}
