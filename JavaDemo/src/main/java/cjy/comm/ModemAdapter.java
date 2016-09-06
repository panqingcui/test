// package cjy.comm;
//
// import java.util.ArrayList;
//
// import javax.comm.SerialPort;
// import javax.comm.SerialPortEvent;
// import javax.comm.SerialPortEventListener;
//
// /**
// * @author sinboy
// *
// * 无线MODEM适配器。 用来从MODEM发送短消息，以及从MODEM接收短消息
// */
// public class ModemAdapter extends Thread implements SerialPortEventListener {
// private static ModemAdapter modem;
// // 发送是否已成功完成
// private boolean sendOKFlag;
// private int errCount;
// // 发送模式是否是PDU方式
// private boolean isPduMode;
// private String smContent;
// private ArrayList<SubmitPack> sendBuffer;
// // 要打开使用的串口
// private SerialPort sPort;
// static Logger logger = Logger.getLogger(ModemAdapter.class);
//
// private ModemAdapter() {
// DOMConfigurator.configure(MyFinal.LOG4J_CONF_FILE);
// isPduMode = false;
// errCount = 0;
// logger.debug("Add a test data");
// sendBuffer = new ArrayList<SubmitPack>();
// SubmitPack msg = new SubmitPack();
// ArrayList<String> destList = new ArrayList<String>();
// destList.add("136××××××××");
// msg.setSm("你好,张新波");
// msg.setDestAddr(destList);
// add(msg);
// start();
// }
//
// public static ModemAdapter getInstance() {
// if (modem == null)
// modem = new ModemAdapter();
// return modem;
// }
//
// // 得到计算机的串口
// private SerialPort getSerialPort(String com) {
// SerialPort sPort = null;
// CommPortIdentifier portID;
// String owner = new String("modemn");
// int keeptime = 5000;
// Enumeration portList;
// portList = CommPortIdentifier.getPortIdentifiers();
// String driverName = "com.sun.comm.Win32Driver";
// CommDriver driver = null;
// try {
// System.loadLibrary("win32com");
// logger.debug("Win32Com Library Loaded");
// driver = (CommDriver) Class.forName(driverName).newInstance();
// driver.initialize();
// logger.debug("Win32Driver Initialized");
// } catch (InstantiationException e1) {
// logger.error("1:" + e1.getMessage());
// e1.printStackTrace();
// } catch (IllegalAccessException e1) {
// logger.error("2:" + e1.getMessage());
// e1.printStackTrace();
// } catch (ClassNotFoundException e1) {
// logger.error(e1.getMessage());
// e1.printStackTrace();
// }
// // 如果有多个端口
// while (portList.hasMoreElements()) {
// portID = (CommPortIdentifier) portList.nextElement();
// if (portID.getName().equals(com))
// try {
// sPort = (SerialPort) portID.open(owner, keeptime);
// break;
// }// 打开一个串口
// catch (PortInUseException e) {
// logger.fatal(e.getMessage());
// System.exit(1);
// }
// }// while
// if (sPort != null) {
// logger.debug("serial name is :" + sPort.getName());
// try {
// // 设置串口的参数
// sPort.setSerialPortParams(9600,// 波特率
// SerialPort.DATABITS_8,// 数据位数
// SerialPort.STOPBITS_1, // 停止位
// SerialPort.PARITY_NONE);// 奇偶位
// } catch (UnsupportedCommOperationException e) {
// e.printStackTrace();
// logger.error(e.getMessage());
// }
// }
// return sPort;
// }
//
// private boolean init() {
// boolean result = false;
// Config conf = new Config();
// String comName = conf.comName();
// sPort = getSerialPort(comName);
// String msg = null;
// if (sPort != null) {
// listenSerialPort(sPort);
// // 用配置参数初始化MODEM
// msg = conf.initParam();
// if (msg != null) {
// if (conf.modemMode() != null && conf.modemMode().equals("0"))
// isPduMode = true;
// if (isPduMode)
// msg = "at+cmgf=0;" + msg;
// else
// msg = "at+cmgf=1;" + msg;
// sendMsg(msg, sPort);
// sendOKFlag = true;
// }
// }
// for (int i = 0; i < 100; i++) {
// try {
// Thread.sleep(1000);
// if (sendOKFlag) {
// logger.debug("初始化MODEM成功！");
// return true;
// }
// } catch (InterruptedException e) {
// e.printStackTrace();
// }
// }
// return result;
// }
//
// // 把短消息通过数据猫发送出去
// private void sendMsg(String msg, SerialPort sPort) {
// PrintWriter pw;
// if (msg != null && sPort != null)
// try {
// pw = new PrintWriter(sPort.getOutputStream());
// pw.println(msg);
// pw.flush();
// pw.close();
// logger.debug("msg has been send from Modemn:");
// logger.debug(msg);
// } catch (IOException e) {
// logger.error(e.getMessage());
// e.printStackTrace();
// }
// }
//
// // 把短消息通过数据猫发送出去
// private void sendMsg(byte[] msg, SerialPort sPort) {
// DataOutputStream pw;
// if (msg != null && sPort != null)
// try {
// pw = new DataOutputStream(sPort.getOutputStream());
// pw.write(msg);
// pw.flush();
// pw.close();
// logger.debug("msg has been send from Modemn:");
// } catch (IOException e) {
// logger.error(e.getMessage());
// e.printStackTrace();
// }
// }
//
// private void listenSerialPort(SerialPort sPort) {
// if (sPort != null)
// try {
// sPort.addEventListener(this);
// sPort.notifyOnDataAvailable(true);
// } catch (TooManyListenersException e) {
// logger.error(e.getMessage());
// e.printStackTrace();
// }
// }
//
// public void run() {
// int waitCount = 0;
// String cmd1 = null;
// SubmitPack msg;
// String dest;
// String content;
// if (init()) {
// while (true) {
// try {
// sleep(10);
// } catch (InterruptedException e) {
// e.printStackTrace();
// }
// msg = getOneMsg();
// if (msg != null) {
// ArrayList<String> destList = msg.getDestAddr();
// for (int i = 0; destList != null && i < destList.size(); i++) {
// dest = (String) destList.get(i);
// content = msg.getSm();
// if (content != null) {
// while (true) {
// if (sendOKFlag == true) {
// if (isPduMode) {
// Config conf = new Config();
// PduPack pack = new PduPack();
// pack.setAddr(dest);
// pack.setMsgContent(content);
// pack.setSmsc(conf.smsc());
// String coded = pack.getCodedResult();
// if (coded != null
// && coded.length() > 18)
// cmd1 = "AT+CMGS="
// + (coded.length() - 18) / 2
// + "/r";
// smContent = coded
// + (char) Integer.parseInt("1A",
// 16);
// // cmd1+=smContent;
// sendMsg(cmd1.getBytes(), sPort);
// cmd1 = null;
// } else
// cmd1 = "AT+CMGS=/"+86"
// + dest
// + "/"/r"
// + content
// + (char) Integer.parseInt("1a",
// 16) + "z";
// if (cmd1 != null) {
// logger.debug("Cmd:" + cmd1);
// sendMsg(cmd1, sPort);
// sendOKFlag = false;
// logger.debug("isSendOK=false");
// }
// break;
// } else
// try {
// sleep(100);
// if (waitCount > 300) {
// sendOKFlag = true;
// waitCount = 0;
// } else
// waitCount++;
// } catch (InterruptedException e) {
// }
// }
// }
// }
// }
// }// while
// } else {
// logger.fatal("无法成功初始化MODEM，请检查设备");
// System.exit(0);
// }
// }
//
// // 处理侦听到的串口事件
// public synchronized void serialEvent(SerialPortEvent ev) {
// DataInputStream in;
// int c = 0;
// StringBuffer sb = null;
// // 如果有串口事件发生
// if (ev.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
// try {
// in = new DataInputStream(sPort.getInputStream());
// sb = new StringBuffer();
// while ((c = in.read()) != -1) {
// sb.append((char) c);
// System.out.println(sb);
// if (handleRecData(sb)) {
// logger.debug("从Modem接收到的数据" + sb);
// sb = new StringBuffer();
// }
// }
// }// try
// catch (IOException e) {
// logger.error(e.getMessage());
// e.printStackTrace();
// }
// }
// }
//
// /**
// * 判断接收到的数据是否最后是以"OK"结束的
// *
// * @param data
// * @return
// */
// private boolean isRecOK(String data) {
// final String OK_FLAG = "OK";
// int index1 = 0;
// if (data != null) {
// index1 = data.indexOf(OK_FLAG);
// if (index1 >= 0 && index1 + 4 <= data.length()) {
// String t = data.substring(index1 + 2);
// byte[] b = t.getBytes();
// if (b.length >= 2) {
// if (b[0] == 0x0D && b[1] == 0x0A)
// return true;
// }
// }
// }
// return false;
// }
//
// /**
// * 发送短消息是否成功. <p> 判断依据: 收到回应的消息中有+CMGS:<space><number>,紧接着是两个换行回车(0x0D,0x0A,0x0D,0x0A), 然后是OK,最后是一个回车换行(0x0D,0x0A)
// *
// * @param data
// * @return
// */
// private boolean isSendOK(String data) {
// final String FLAG = "+CMGS:";
// int index = -1;
// int index2 = -1;
// if (data != null) {
// index = data.indexOf(FLAG);
// if (index > 0) {
// index += 6;
// if (index < data.length()) {
// String temp = data.substring(index);
// index = 0;
// byte[] b = temp.getBytes();
// for (int i = 0; i < b.length; i++) {
// if (b[i] == 0x0D) {
// index2 = i;
// break;
// }
// }
// if (index2 < temp.length() && index2 > index + 1) {
// String t1 = temp.substring(index + 1, index2);
// try {
// int seqid = Integer.parseInt(t1);
// logger.debug("seqID:" + seqid);
// if (index2 + 8 == temp.length()) {
// // 两个回车换行符
// if (b[index2] == 0x0D && b[++index2] == 0x0A && b[++index2] == 0x0D
// && b[++index2] == 0x0A) {
// if (b[++index2] == 0x4F && b[++index2] == 0x4B) {// OK
// if (b[++index2] == 0x0D && b[++index2] == 0x0A) {// 一个回车换行
// return true;
// }
// }
// }
// }
// } catch (NumberFormatException e) {
// e.printStackTrace();
// return false;
// }
// }
// }
// }
// }
// return false;
// }
//
// /**
// * 判断接收到的字符串最后是否是以"ERROR"结束的
// *
// * @param data
// * @return
// */
// private boolean isRecError(String data) {
// final String FLAG = "ERROR";
// int index1 = 0;
// if (data != null) {
// index1 = data.indexOf(FLAG);
// if (index1 >= 0 && index1 + 7 <= data.length()) {
// String t = data.substring(index1 + 5);
// byte[] b = t.getBytes();
// if (b.length >= 2) {
// if (b[0] == 0x0D && b[1] == 0x0A)
// return true;
// }
// }
// }
// return false;
// }
//
// /**
// * 是否接收到手机发来的完整数据,上传的数据是以"+CMT:"开头
// *
// * @param data
// * @return
// */
// private boolean isRecData(String data) {
// final String BEGIN_FLAG = "+CMT:";
// int index0 = -1;
// int index1 = -1;
// int index2 = -1;
// if (data != null) {
// index0 = data.indexOf(BEGIN_FLAG);
// if (index0 >= 0 && index0 < data.length()) {
// // data=data.substring(index0);
// // index1 = data.indexOf("/r/n");
// // if (index1 > index0 && index1 + 2 < data.length()) {
// // String str=data.substring(index1+2);
// // index2=str.indexOf("/r/n");
// // if(index2>0 && index2<str.length()){
// //
// // return true;
// // }
// // }
// return true;
// }
// }
// return false;
// }
//
// private boolean handleRecData(StringBuffer sb) {
// String data = null;
// if (sb != null) {
// data = sb.toString();
// if (isRecOK(data)) {
// sendOKFlag = true;
// return true;
// } else if (isRecError(data)) {
// errCount++;
// if (errCount > 3) {
// sendOKFlag = true;
// errCount = 0;
// }
// return true;
// } else if (sb.indexOf(">") != -1 && smContent != null) {
// sendMsg(smContent.getBytes(), sPort);
// smContent = null;
// } else {
// int index0 = data.lastIndexOf("+CMT:");
// if (index0 >= 0 && index0 < data.length()) {
// data = data.substring(index0);
// int index1 = data.indexOf("/r/n");
// if (index1 != -1 && index1 + 2 < data.length()) {
// data = data.substring(index1 + 2);
// int index2 = data.indexOf("/r/n");
// if (index2 > 1 && index2 < data.length()) {
// data = data.substring(0, index2);
// if (data != null && data.length() > 0) {
// PduPack pack = new PduPack(data);
// String srcAddr = pack.getAddr();
// String content = pack.getMsgContent();
// String destAddr = "012345";
// if (srcAddr != null && content != null) {
// logger.debug("srcAddr:" + srcAddr);
// logger.debug("content:" + content);
// Config conf = new Config();
// destAddr = conf.cmppSSN();
// DeliverPack deliver = new DeliverPack(srcAddr, destAddr, content);
// ServerAdapter server = ServerAdapter.getInstance();
// server.addDeliverPack(deliver);
// return true;
// }
// }
// }
// }
// }
// }
// }
// return false;
// }
//
// private SubmitPack getOneMsg() {
// SubmitPack result = null;
// if (sendBuffer != null && sendBuffer.size() > 0)
// result = (SubmitPack) sendBuffer.remove(0);
// return result;
// }
//
// public void add(SubmitPack msg) {
// sendBuffer.add(msg);
// }
//
// @Override
// public void serialEvent(SerialPortEvent arg0) {
// // TODO Auto-generated method stub
// }
// }
