package com.zhengqing.demo.html2pdf;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import com.aspose.words.Document;
import com.aspose.words.SaveFormat;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * Html 转 Pdf 工具类
 * </p>
 *
 * @author : zhengqing
 * @description :
 * @date : 2020/11/24 11:23
 */
@Slf4j
public class Html2PdfUtil {

    /**
     * 将`html`字节码转换成`pdf`字节码
     *
     * @param htmlBytes:
     *            html字节码
     * @return: 生成的`pdf`字节码
     * @author : zhengqing
     * @date : 2020/11/24 11:26
     */
    @SneakyThrows(Exception.class)
    public static byte[] html2Pdf(byte[] htmlBytes) {
        Document document = new Document(new ByteArrayInputStream(htmlBytes));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        document.save(outputStream, SaveFormat.PDF);
        // 返回生成的`pdf`字节码
        return outputStream.toByteArray();
    }

    /**
     * 将`html`内容生成`pdf`文件
     *
     * @param html:
     *            html内容
     * @param pdfFilePath:
     *            需转换的`pdf`文件路径
     * @return: 生成的`pdf`文件路径
     * @author : zhengqing
     * @date : 2020/11/24 11:26
     */
    @SneakyThrows(Exception.class)
    public static void html2Pdf(String html, String pdfFilePath) {
        Document document = new Document(new ByteArrayInputStream(html.getBytes()));
        document.save(pdfFilePath, SaveFormat.PDF);
    }

}
