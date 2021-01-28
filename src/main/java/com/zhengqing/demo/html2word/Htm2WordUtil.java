package com.zhengqing.demo.html2word;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.aspose.words.Document;
import com.aspose.words.DocumentBuilder;
import com.aspose.words.SaveFormat;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * Html 转 Word 工具类
 * </p>
 *
 * @author : zhengqing
 * @description :
 * @date : 2020/11/23 16:00
 */
@Slf4j
public class Htm2WordUtil {

    /**
     * `html` 转 `word` 【 注：本地图片不支持显示！！！ 需转换成在线图片 】
     *
     * @param htmlBytes:
     *            html字节码
     * @return: word文件路径
     * @author : zhengqing
     * @date : 2020/11/24 11:52
     */
    @SneakyThrows(Exception.class)
    public static byte[] htmlBytes2WordBytes(byte[] htmlBytes) {
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);
        builder.insertHtml(new String(htmlBytes));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        doc.save(outputStream, SaveFormat.DOCX);
        return outputStream.toByteArray();
    }

    /**
     * `html` 转 `word` 【 注：本地图片不支持显示！！！ 需转换成在线图片 】
     *
     * @param htmlBytes:
     *            html字节码
     * @param wordFilePath:
     *            待生成的word文件路径
     * @return: word文件数据
     * @author : zhengqing
     * @date : 2020/11/24 11:52
     */
    @SneakyThrows(Exception.class)
    public static File htmlBytes2WordFile(byte[] htmlBytes, String wordFilePath) {
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);
        builder.insertHtml(new String(htmlBytes));
        doc.save(wordFilePath, SaveFormat.DOCX);
        return new File(wordFilePath);
    }

    /**
     * `html` 转 `word` 【注`doc`生成的html中的图片路径中中文是被转义处理过的，再生成word时图片便看不了，需单独做处理，`docx`无此问题】 【 注：此方式会丢失一定格式 】
     *
     * @param html:
     *            html内容
     * @param fileRootPath:
     *            文件根位置
     * @param wordFileName:
     *            待生成的word文件名
     * @return: word文件路径
     * @author : zhengqing
     * @date : 2020/11/23 16:04
     */
    @SneakyThrows(Exception.class)
    public static String html2Word(String html, String fileRootPath, String wordFileName) {
        final String wordFilePath = fileRootPath + "/" + wordFileName;
        byte htmlBytes[] = html.getBytes();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(htmlBytes);
        POIFSFileSystem poifs = new POIFSFileSystem();
        DirectoryEntry directory = poifs.getRoot();
        DocumentEntry documentEntry = directory.createDocument("WordDocument", inputStream);
        FileOutputStream outputStream = new FileOutputStream(wordFilePath);
        poifs.writeFilesystem(outputStream);
        inputStream.close();
        outputStream.close();
        return wordFilePath;
    }

}
