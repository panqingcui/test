package cjy.comm;

import org.smslib.AGateway;
import org.smslib.GatewayException;
import org.smslib.IOutboundMessageNotification;
import org.smslib.Message.MessageEncodings;
import org.smslib.OutboundMessage;
import org.smslib.Service;
import org.smslib.modem.SerialModemGateway;

public class SendMessage1 {
    public class OutboundNotification implements IOutboundMessageNotification {
        public void process(String gatewayId, OutboundMessage msg) {
            System.out.println("Outbound handler called from Gateway: " + gatewayId);
            System.out.println(msg);
        }

        @Override
        public void process(AGateway arg0, OutboundMessage arg1) {
            // TODO Auto-generated method stub
        }
    }

    @SuppressWarnings("deprecation")
    public void sendSMS(String mobilePhones, String content) {
        Service srv;
        OutboundMessage msg;
        OutboundNotification outboundNotification = new OutboundNotification();
        srv = Service.getInstance();
        SerialModemGateway gateway = new SerialModemGateway("modem.com3", "COM3", 57600, "", ""); // 设置端口与波特率
        gateway.setInbound(true);
        gateway.setOutbound(true);
        gateway.setSimPin("0000");
        try {
            srv.addGateway(gateway);
        } catch (GatewayException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        System.out.println("初始化成功，准备开启服务");
        try {
            srv.startService();
            System.out.println("服务启动成功");
            String[] phones = mobilePhones.split(",");
            for (int i = 0; i < phones.length; i++) {
                msg = new OutboundMessage(phones[i], content);
                msg.setEncoding(MessageEncodings.ENCUCS2); // 中文
                srv.sendMessage(msg);
            }
            srv.stopService();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SendMessage1 sendMessage = new SendMessage1();
        sendMessage.sendSMS("10010", "您要发送的内容！");
    }
}
