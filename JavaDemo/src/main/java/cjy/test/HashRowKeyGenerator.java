// /*
// * Copyright (c) 2013-2014 Shandong Antrol Network Technology Co.Ltd. All rights
// reserved. 版权所有(c) 2013-2014
// * 山东蚁巡网络科技有限公司。保留所有权利
// */
// package cjy.test;
//
// import java.util.Random;
//
// import org.apache.hadoop.io.MD5Hash;
//
// import com.google.common.primitives.Bytes;
//
// /**
// * <p>功能描述,该部分必须以中文句号结尾。<p>
// *
// * 创建日期 2014年12月16日<br>
// * @author $Author$<br>
// * @version $Revision$ $Date$
// * @since 3.0.0
// */
// // interface
// public interface RowKeyGenerator {
// byte[] nextId();
// }
//
// // implements
// public class HashRowKeyGenerator implements RowKeyGenerator {
// private long currentId = 1;
// private long currentTime = System.currentTimeMillis();
// private Random random = new Random();
//
// public byte[] nextId() {
// try {
// currentTime += random.nextInt(1000);
// byte[] lowT = Bytes.copy(Bytes.toBytes(currentTime), 4, 4);
// byte[] lowU = Bytes.copy(Bytes.toBytes(currentId), 4, 4);
// return Bytes.add(MD5Hash.getMD5AsHex(Bytes.add(lowU, lowT)).substring(0, 8).getBytes(),
// Bytes.toBytes(currentId));
// } finally {
// currentId++;
// }
// }
// }
