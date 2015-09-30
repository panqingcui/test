/*
 * Copyright (c) 2014-2015 Shandong Antrol Network Technology Co.Ltd. All rights reserved.
 * 版权所有(c) 2014-2015 山东蚁巡网络科技有限公司。保留所有权利。
 */
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

/**
 * <p>功能描述,该部分必须以中文句号结尾。<p>
 * 
 * 创建日期 2015年5月19日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 1.0.0
 */
public class QRCode {
    @SuppressWarnings({"unchecked", "rawtypes" })
    public static void main(String[] args) throws WriterException, IOException {
        String text = "你好";
        int width = 100;
        int height = 100;
        String format = "png";
        Hashtable hints = new Hashtable();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
        File outputFile = new File("new.png");
        MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);
    }
}
