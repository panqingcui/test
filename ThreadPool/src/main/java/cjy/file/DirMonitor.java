/*
 * Copyright (c) ANTVISION 2011 All Rights Reserved Licensed under the Apache License, Version 2.0 (the "License"); you
 * may not use this file except in compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and limitations under the
 * License.
 */
package cjy.file;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * <p>功能描述,该部分必须以中文句号结尾。<p>
 * 
 * 创建日期 2014年2月10日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
/**
 * <p> Description:监控某个文件路径下的变化 </p>
 * @author imasmallbird
 * @version $Revision 1.1 $ 2009-10-10 下午01:14:26
 */
public class DirMonitor implements Runnable {
    /**
     * 监控的文件路径
     */
    private String dir;
    /**
     * 扫描间隔时间以秒为单位
     */
    private int period;
    /**
     * 原有文件信息
     */
    private Map<String, String> oldDirFileMap;
    /**
     * 文件修改时间
     */
    private Map<String, Long> fileModifytime;

    /**
     * 初始化相关数据
     */
    public DirMonitor(String dir, int period) {
        this.dir = dir;
        this.period = period;
        this.oldDirFileMap = new HashMap<String, String>();
        this.fileModifytime = new HashMap<String, Long>();
    }

    /**
     * 线程的执行。对于修改文件的情况，则视为删除与增加两个操作。
     */
    public void run() {
        boolean isError = false;
        File file = new File(dir);
        // 初始化开始监控时的文件路径状态
        totalScan(file, oldDirFileMap, fileModifytime);
        // 展示原有路径下的文件信息
        displayNowFile();
        while (!isError) {
            try {
                Thread.sleep(period * 1000);
                // 指定时间间间隔后扫描路径
                Map<String, String> nowDirFileMap = new HashMap<String, String>();
                totalScan(file, nowDirFileMap, fileModifytime);
                // 得到删除的文件及文件夹
                getDeletedFile(nowDirFileMap);
                // 得到新增的文件及文件夹
                getAddedFile(nowDirFileMap);
                // 注意：由于涉及到修改，所以一定要先检测删除的文件，然后再检测新增加的文件
            } catch (InterruptedException e) {
                System.out.println("对指定的文件路径进行监控时产生异常，异常信息为：" + e.getMessage());
                isError = true;
            }
        }
    }

    /**
     * 递归扫描整个路径
     * @param dir
     * @param ndir
     * @param dirFileMap　存放扫描结果
     */
    private void totalScan(File file, Map<String, String> dirFileMap, Map<String, Long> fileTime) {
        String[] fileList = file.list();
        // 判断是否为空目录
        if (null != fileList) {
            for (int i = 0; i < fileList.length; i++) {
                String pname = file.getAbsolutePath() + "\\" + fileList[i];
                File tempFile = new File(pname);
                if (tempFile.isDirectory()) {
                    dirFileMap.put(pname, "文件夹：\t" + pname);
                    fileTime.put(pname, file.lastModified());
                    totalScan(tempFile, dirFileMap, fileTime);
                } else {
                    // 不相同的文件夹下，存放的文件可能名字相同，但是同一路径下的文件肯定不会相同，
                    // 所以采用全路径做为key值
                    dirFileMap.put(pname, "文件：\t" + pname);
                    fileTime.put(pname, file.lastModified());
                }
            }
        }
    }

    /**
     * 修改文件
     */
    public void modifyFile(Map<String, Long> filetime) {}

    /**
     * 得到增加的文件及文件夹,并增加到已有的文件信息中
     */
    private void getAddedFile(Map<String, String> nowDirFileMap) {
        for (Iterator<String> iterator = nowDirFileMap.keySet().iterator(); iterator.hasNext();) {
            String key = iterator.next();
            if (null == oldDirFileMap.get(key)) {
                oldDirFileMap.put(key, nowDirFileMap.get(key));
                System.out.println("新增" + nowDirFileMap.get(key));
            }
        }
    }

    /**
     * 得到删除的文件及文件夹,并删除已经不存在的文件信息
     */
    private void getDeletedFile(Map<String, String> nowDirFileMap) {
        for (Iterator<String> iterator = oldDirFileMap.keySet().iterator(); iterator.hasNext();) {
            String key = iterator.next();
            if (null == nowDirFileMap.get(key)) {
                System.out.println("删除" + oldDirFileMap.get(key));
                iterator.remove();
                oldDirFileMap.remove(key);
            }
        }
    }

    /**
     * 展示原有文件
     */
    private void displayNowFile() {
        System.out.println(dir + "路径原有文件目录如下：\n");
        Set set = oldDirFileMap.keySet();
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(oldDirFileMap.get(iterator.next()));
        }
        System.out.println("========================");
    }

    /**
     * just for test
     * @param args
     */
    public static void main(String[] args) {
        DirMonitor dirMonitor = new DirMonitor("D:\\data", 5);
        dirMonitor.run();
    }
}
