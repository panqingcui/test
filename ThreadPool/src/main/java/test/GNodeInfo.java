package test;


/**
 * <p>拓扑图中节点数据bean。<p>
 * 
 * 创建日期 2012-4-25<br>
 * @author liwenjun(liwenjun@antvision.cn)<br>
 * @version $Revision$ $Date: 2012-04-28 17:48:53 +0800 (周六, 28 四月 2012) $
 * @since 3.0.0
 */
public class GNodeInfo {
    /* 唯一标示 */
    private String id;
    /* x轴坐标 */
    private Double x = 0.0;
    /* y轴坐标 */
    private Double y = 0.0;
    // 子网范围
    private String net = "";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    /**
     * @return the net
     */
    public String getNet() {
        return net;
    }

    /**
     * @param net the net to set
     */
    public void setNet(String net) {
        this.net = net;
    }
}
