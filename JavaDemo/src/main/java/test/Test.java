package test;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Date;

public class Test {
    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(date.toString());
        // GMap gmap = new GMap();
        // gmap.setId("1");
        // GNode node = new GNode();
        // node.setId("1");
        // node.setNet("");
        // node.setX(0.0);
        // node.setY(0.0);
        // List<GNode> nodelist = new ArrayList<GNode>();
        // nodelist.add(node);
        // gmap.setGNodeList(nodelist);
        // GMapInfo g = new GMapInfo(gmap.getId(), gmap.getAutoLayout(), gmap.getGNodeList());
        // try {
        // Copy(gmap, g);
        // List<GNodeInfo> list = g.getgNodeList();
        // for (GNodeInfo gn : list) {
        // System.out.println(gn.getId());
        // }
        // } catch (Exception e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
        // System.out.println(g.getgNodeList().get(0));
    }

    public static void Copy(Object source, Object dest) throws Exception {
        // 获取属性
        BeanInfo sourceBean = Introspector.getBeanInfo(source.getClass(), java.lang.Object.class);
        PropertyDescriptor[] sourceProperty = sourceBean.getPropertyDescriptors();
        BeanInfo destBean = Introspector.getBeanInfo(dest.getClass(), java.lang.Object.class);
        PropertyDescriptor[] destProperty = destBean.getPropertyDescriptors();
        try {
            for (int i = 0; i < sourceProperty.length; i++) {
                for (int j = 0; j < destProperty.length; j++) {
                    if (sourceProperty[i].getName().equals(destProperty[j].getName())) {
                        // 调用source的getter方法和dest的setter方法
                        destProperty[j].getWriteMethod().invoke(dest, sourceProperty[i].getReadMethod().invoke(source));
                        break;
                    }
                }
            }
        } catch (Exception e) {
            throw new Exception("属性复制失败:" + e.getMessage());
        }
    }
}
