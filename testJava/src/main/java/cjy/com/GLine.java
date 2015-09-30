package cjy.com;

/**
 * <p>拓扑图中边数据bean。<p>
 * 
 * 创建日期 2012-4-25<br>
 * @author liwenjun(liwenjun@antvision.cn)<br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class GLine implements java.io.Serializable {
    //
    private static final long serialVersionUID = 1L;
    // 开始节点id
    private String nodeFrom;
    // 结束节点id
    private String nodeTo;

    public String getNodeFrom() {
        return nodeFrom;
    }

    public void setNodeFrom(String nodeFrom) {
        this.nodeFrom = nodeFrom;
    }

    public String getNodeTo() {
        return nodeTo;
    }

    public void setNodeTo(String nodeTo) {
        this.nodeTo = nodeTo;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        GLine other = (GLine) obj;
        if (nodeFrom.equals(other.getNodeFrom()) && nodeTo.equals(other.getNodeTo())) {
            return true;
        }
        return false;
    }
}
