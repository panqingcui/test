import java.util.ArrayList;
import java.util.List;

public class TopoLine {
    // 路由下一跳的接点集合
    private List<String> nextHops;
    // 对于路由器代表网络接口，对于subnet代表主机的ip
    private List<String> interfaces;
    // 网关
    private String defaultgw;
    private final TopoRouterLine args = new TopoRouterLine();

    public TopoLine() {
        nextHops = new ArrayList<String>();
        interfaces = new ArrayList<String>();
    }

    public List<String> getInterfaces() {
        return interfaces;
    }

    public List<String> getNextHops() {
        return nextHops;
    }

    public void addInterface(String i) {
        this.interfaces.add(i);
    }

    public void addNextHop(String nextHop) {
        this.nextHops.add(nextHop);
    }

    public String getDefaultgw() {
        return defaultgw;
    }

    public void setDefaultgw(String defaultgw) {
        this.defaultgw = defaultgw;
    }
}
