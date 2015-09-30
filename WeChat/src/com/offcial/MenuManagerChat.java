package com.offcial;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.util.AccessToken;

/**
 * 菜单管理器类builder
 * 
 * 
 */
public class MenuManagerChat {
    // private static Logger log = LoggerFactory.getLogger(MenuManager.class);
    private static Log log = LogFactory.getLog(Official.class);
    public static String menu_delete_url = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";

    public static void main(String[] args) {
        // 第三方用户唯一凭证
        String appId = "wxac985f1a80ee1803";
        // 第三方用户唯一凭证密钥
        String appSecret = "db13d5cd514c567d9e39d22c76826bfe";
        // 调用接口获取access_token
        AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);
        if (null != at) {
            // // 调用接口创建菜单
            int result = WeixinUtil.createMenu(getMenu(), at.getToken());
            // 判断菜单创建结果
            if (0 == result)
                log.info("菜单创建成功！");
            else
                log.info("菜单创建失败，错误码：" + result);
            // deleteMenu(at.getToken());
        }
    }

    private static void deleteMenu(String token) {
        menu_delete_url = menu_delete_url.replace("ACCESS_TOKEN", token);
        JSONObject json = WeixinUtil.httpRequest(menu_delete_url, "GET", null);
        System.out.println(json);
    }

    /**
     * 组装菜单数据
     * 
     * @return
     */
    private static Menu getMenu() {
        CommonButton btn11 = new CommonButton();
        btn11.setName("告警信息查询");
        btn11.setType("click");
        btn11.setKey("11");
        ViewButton btn21 = new ViewButton();
        btn21.setName("蚁巡官网");
        btn21.setType("view");
        btn21.setUrl("http://www.antrol.com/");
        ViewButton btn31 = new ViewButton();
        btn31.setName("蚁巡官网");
        btn31.setType("view");
        btn31.setUrl("http://www.antrol.com/");
        ComplexButton mainBtn1 = new ComplexButton();
        mainBtn1.setName("蚁巡助手");
        mainBtn1.setSub_button(new Button[] {btn11 });
        ComplexButton mainBtn2 = new ComplexButton();
        mainBtn2.setName("生活助手");
        mainBtn2.setSub_button(new Button[] {btn21 });
        ComplexButton mainBtn3 = new ComplexButton();
        mainBtn3.setName("更多");
        mainBtn3.setSub_button(new Button[] {btn31 });
        /**
         * 这是公众号xiaoqrobot目前的菜单结构，每个一级菜单都有二级菜单项<br>
         * 
         * 在某个一级菜单下没有二级菜单的情况，menu该如何定义呢？<br> 比如，第三个一级菜单项不是“更多体验”，而直接是“幽默笑话”，那么menu应该这样定义：<br> menu.setButton(new
         * Button[] { mainBtn1, mainBtn2, btn33 });
         */
        Menu menu = new Menu();
        menu.setButton(new Button[] {mainBtn1, mainBtn2, mainBtn3 });
        return menu;
    }
}
