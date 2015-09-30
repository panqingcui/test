package cjy.com.velocity;

import java.io.StringWriter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import cjy.com.map.TestMap;

public class TestVelocity {
    // 日志
    private static final Log logger = LogFactory.getLog(TestMap.class);

    public static void main(String[] args) {
        VelocityEngine ve = new VelocityEngine();
        // Properties properties = new Properties();
        // properties.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, "D://ywj//testJava");
        ve.init();
        Template template = ve.getTemplate("report.vm", "utf-8");
        VelocityContext context = new VelocityContext();
        context.put("dealedAlarm", 30);
        // 设置输出
        StringWriter w = new StringWriter();
        template.merge(context, w);
        logger.debug("ddd" + w.toString());
    }
}
