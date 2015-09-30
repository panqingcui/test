/*
 * Copyright (c) ANTVISION 2011 All Rights Reserved Licensed under the Apache
 * License, Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law
 * or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package cjy.com.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

/**
 * <p>测试文件读取。<p>
 * 
 * 创建日期 2012-8-20<br>
 * @author 陈军营(chenjunying@antvision.cn) <br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class TestFile {
    private static final String NMAP_XML_PATH = System.getProperty("loong.home") + "/data/nmap-result.xml";
    private static final String NMAP_LOCK_PATH = System.getProperty("loong.home") + "/data/nmap-result.xml.lock";
    private static final String NMAP_PATH = System.getProperty("loong.home") + "/data";
    private static final String AGENT_CONFIG_FILE = System.getProperty("loong.home")
            + "/conf/cn.antvision.soldier.agent.properties";
    private static final String NMAP_SCAN_FILE_DEL = "nmap.scan.file.del";

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("NMAP_XML_PATH=" + NMAP_XML_PATH);
        System.out.println("NMAP_LOCK_PATH=" + NMAP_LOCK_PATH);
        // 文件是否存在判断
        File file = new File(NMAP_XML_PATH);
        if (!file.exists()) {
            System.out.println(NMAP_XML_PATH + " is not exist");
        }
        // 检查lock文件，确保扫描的过程中不读取扫描文件
        File fileLock = new File(NMAP_LOCK_PATH);
        if (fileLock.exists()) {
            System.out.println("nmap is scaning");
        }
        // 获取当前时间
        Calendar cal = Calendar.getInstance();
        long timeInMillisCurrent = cal.getTimeInMillis();
        System.out.println("timeInMillisCurrent=" + timeInMillisCurrent);
        // 获取NMAP_PATH下所有以xml为后缀的文件
        List<File> fileList = getFileList(NMAP_PATH, "nmap-result", "xml");
        for (File fileXml : fileList) {
            System.out.println("fileXml name is " + fileXml.getPath());
        }
        // 读取配置文件
        String isDel = null;
        Properties properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream(AGENT_CONFIG_FILE);
            properties.load(fileInputStream);
            isDel = properties.getProperty(NMAP_SCAN_FILE_DEL);
            System.out.println("isDel=" + isDel);
            fileInputStream.close();
            fileInputStream = null;
        } catch (FileNotFoundException e) {
            System.out.println(AGENT_CONFIG_FILE + " is not find!");
        } catch (IOException e) {
            System.out.println("read file " + AGENT_CONFIG_FILE + " error!");
        }
    }

    /**
     * 返回目录filePath下的所有以filePrefix为前缀，filePostfix为后缀的文件
     * @param filePostfix
     * @return
     */
    public static List<File> getFileList(String filePath, String filePrefix, String filePostfix) {
        List<File> fileList = new ArrayList<File>();
        File path = new File(filePath);
        File[] files = path.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                String fileName = file.getName();
                if (fileName.startsWith(filePrefix)) {
                    if (fileName.endsWith(filePostfix)) {
                        fileList.add(file);
                    }
                }
            }
        }
        return fileList;
    }
}
