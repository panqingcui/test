import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
public class SuperJoyful {
    // 声明一个List用于存放前区号码
    private List<Integer> objFrontList = new ArrayList<Integer>();
    // 声明一个List用于存放后区号码
    private List<Integer> objBackList = new ArrayList<Integer>();
    // 声明一个正则表达式对象，用于匹配是否是一位数字,用于输出时验证
    Pattern objP = Pattern.compile("\\d");
    // 所要操作的字符串
    Matcher objM = null;

    // 初始化摇奖号码
    public void init() {
        for (int i = 1; i <= 35; i++) {
            objFrontList.add(i);
        }
        for (int i = 1; i <= 12; i++) {
            objBackList.add(i);
        }
    }

    // 开始摇奖
    public void beginLottery() {
        Random objRandom = new Random();
        int nFrontCount = 35; // 前区号码总数
        int nBackCount = 12; // 后区号码总数
        // 摇奖前先清空LIST，再初始化
        objFrontList.clear();
        // System.out.println(objFrontList);
        objBackList.clear();
        // System.out.println(objBackList);
        this.init();
        /**
         * 产生5个前区号码
         */
        for (int i = 0; i < 5; i++) {
            // System.out.println("nFrontCount:"+nFrontCount);
            // 初始时有35个前区号，随机产生一个索引
            int nIndex = objRandom.nextInt(nFrontCount);
            // 将选出的号码暂时存放在变量中，带正则表达式验证
            int nTemp = objFrontList.get(nIndex);
            String strTemp = new Integer(nTemp).toString();
            // 将获得的号码与正则表达式匹配
            objM = objP.matcher(strTemp);
            boolean flag = objM.matches();
            // 如果是一位数，则在前面补零
            if (flag) {
                System.out.print("0" + strTemp + " ");
            } else {
                System.out.print(strTemp + " ");
            }
            // 删除LIST中该索引处的号码，因为选出一个就不再放回
            objFrontList.remove(nIndex);
            // for(int n = 0; n<objFrontList.size();n++){
            // System.out.println("FrontList:"+objFrontList.get( n ));
            // }
            // 号码总数减少一个
            nFrontCount--;
        }
        System.out.print("+ ");
        /**
         * 产生2个后区号码
         */
        for (int i = 0; i < 2; i++) {
            // System.out.println("nBackCount:"+nBackCount);
            // 初始时有12个后区号，随机产生一个索引
            int nIndex = objRandom.nextInt(nBackCount);
            // 将选出的号码暂时存放在变量中，带正则表达式验证
            int nTemp = objBackList.get(nIndex);
            String strTemp = new Integer(nTemp).toString();
            // 将获得的号码与正则表达式匹配
            objM = objP.matcher(strTemp);
            boolean flag = objM.matches();
            // 如果是一位数，则在前面补零
            if (flag) {
                System.out.print("0" + strTemp + " ");
            } else {
                System.out.print(strTemp + " ");
            }
            // 删除LIST中该索引处的号码，因为选出一个就不再放回
            objBackList.remove(nIndex);
            // for(int n = 0; n<objBackList.size();n++){
            // System.out.println("objBackList:"+objBackList.get( n ));
            // }
            // 号码总数减少一个
            nBackCount--;
        }
        // 产生一注后回车
        System.out.println("");
    }

    // 按要求输出多少注彩票
    public void outPutLottery(int vnCount) {
        for (int i = 0; i < vnCount; i++) {
            this.beginLottery();
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        SuperJoyful objSJ = new SuperJoyful();
        EnterConsole objEC = new EnterConsole();
        // 声明一个正则表达式对象，用于匹配是否是数字
        Pattern objP = Pattern.compile("\\d{1,}");
        // 所要操作的字符串
        Matcher objM = null;
        // 接收控制台输入
        String objTemp = (String) objEC.printConsole();
        // String strTemp = (String)objTemp;
        objM = objP.matcher(objTemp);
        boolean flag = objM.matches();
        int nTemp = 0;
        if (flag) {
            nTemp = new Integer(objTemp);
        } else {
            System.out.println("对不起，只能输入数字！");
        }
        objSJ.outPutLottery(nTemp);
    }
}
