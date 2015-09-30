import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class getServiceList {
    /**
     * @param args
     * @throws ClassNotFoundException
     */
    public static void main(String[] args) {
        // 此处输入配置文件路径及名称，
        // 配置文件中按下面取得顺序编写各文件路径及名称
        Scanner sc = new Scanner(System.in);
        String routeList = sc.next();
        // jar文件路径及名称
        String jarFileName = readFile(routeList, 0);
        // 参与比较的源文件
        String sourceFileName = readFile(routeList, 1);
        // 反射jar后结果文件
        String targetFileName = readFile(routeList, 2);
        // 比较结果存放文件
        String compareFileName = readFile(routeList, 3);
        // 清除文件
        deleteFile(targetFileName);
        deleteFile(compareFileName);
        // 创建文件
        createFile(sourceFileName);
        createFile(targetFileName);
        createFile(compareFileName);
        // 取得class中method,并写入targetFileName文件中.
        reflexJar(jarFileName, targetFileName);
        // 生产比较结果
        compareFile(sourceFileName, targetFileName, compareFileName);
    }

    static String readFile(String textFile, int num) {
        String fileName = "";
        BufferedReader reader = null;// 定义BufferedReader
        try {
            reader = new BufferedReader(new FileReader(textFile));
            int i = 0;
            while ((fileName = reader.readLine()) != null) {
                if (i == num) {
                    break;
                }
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileName;
    }

    static void deleteFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("删除文件失败！" + fileName + "文件不存在.");
        } else {
            file.delete();
        }
    }

    static void createFile(String fileName) {
        try {
            File file = new File(fileName);
            if (file.exists()) {
                if (fileName.indexOf("source.txt") == -1) {
                    System.out.println("创建文件失败！" + fileName + "文件已存在.");
                }
            } else {
                file.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void reflexJar(String fileName, String targetFileName) {
        String className = "";
        try {
            JarFile file = new JarFile(fileName);
            Enumeration<JarEntry> entrys = file.entries();
            while (entrys.hasMoreElements()) {
                JarEntry jar = entrys.nextElement();
                className = jar.getName();
                if (className.indexOf(".class") != -1) {
                    // 将路径中 / 换为 .
                    className = className.replace("/", ".");
                    // 从className中截去.class
                    className = className.substring(0, className.indexOf(".class"));
                    int prefixNum = className.indexOf("nsrgl.");
                    if (prefixNum == -1) {
                        continue;
                    }
                    String prefixName = className.substring(prefixNum);
                    reflexMethod(className, prefixName, targetFileName);
                    // System.out.println(prefixName);
                }
            }
            file.close();
        } catch (Exception e) {
            System.out.println(className);
            e.printStackTrace();
        }
    }

    static void reflexMethod(String className, String prefixName, String targetFileName) {
        try {
            Class cls = Class.forName(className);
            Method m[] = cls.getDeclaredMethods();
            // System.out.println(prefixName);
            for (int i = 0; i < m.length; i++) {
                if (m[i].toString().indexOf("Response") != -1) {
                    String funcName = m[i].toString();
                    funcName = funcName.substring(funcName.indexOf(prefixName), funcName.indexOf("("));
                    appendMethod(targetFileName, funcName);
                    // System.out.println(funcName);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 方法追加文件：使用FileWriter
     * 
     * @param fileName
     * @param content
     */
    static void appendMethod(String fileName, String content) {
        try {
            // 打开一个写文件器，构造函数中的第2个参量true表示以追加情势写文件
            FileWriter writer = new FileWriter(fileName, true);
            writer.write(content + "\r\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void compareFile(String sourceFileName, String targetFileName, String compareFileName) {
        BufferedReader reader = null;// 定义BufferedReader
        try {
            reader = new BufferedReader(new FileReader(targetFileName));
            // 按行读取文件并加入到content中。
            // 当readLine方法返回null时表示文件读取完毕。
            String line;
            BufferedReader newReader;
            while ((line = reader.readLine()) != null) {
                newReader = new BufferedReader(new FileReader(sourceFileName));
                String readline;
                int i = 0;
                while ((readline = newReader.readLine()) != null) {
                    if (line.equals(readline)) {
                        i++;
                        break;
                    }
                }
                if (i == 0) {
                    appendMethod(compareFileName, line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 最后要在finally中将reader对象关闭
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
