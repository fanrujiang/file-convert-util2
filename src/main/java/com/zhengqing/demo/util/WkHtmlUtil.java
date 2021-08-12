package com.zhengqing.demo.util;

import cn.hutool.core.io.FileUtil;
import com.zhengqing.demo.config.Constants;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * <p>
 * html 转 图片或pdf 工具类
 * </p>
 *
 * @author zhengqingya
 * @description https://wkhtmltopdf.org
 * html转pdf： wkhtmltopdf https://zhengqing.blog.csdn.net zhengqingya.pdf
 * html转图片： wkhtmltoimage https://zhengqing.blog.csdn.net zhengqingya.png
 * 帮助 wkhtmltopdf -h 或 wkhtmltoimage -h
 * @date 2021/8/11 9:54 下午
 */
@Slf4j
public class WkHtmlUtil {

    /**
     * 工具根目录
     */
    private static final String TOOL_WIN_ROOT_DIRECTORY = "D:/zhengqingya/soft/soft-dev/wkhtmltopdf/bin/";

    public static void main(String[] args) throws Exception {
        String sourceFilePath = "https://zhengqing.blog.csdn.net";
        String targetPngFilePath = Constants.DEFAULT_FOLDER_TMP_GENERATE + "/zhengqingya.png";
        String targetPdfFilePath = Constants.DEFAULT_FOLDER_TMP_GENERATE + "/zhengqingya.pdf";
        // 设置宽高
        String cmdByImage = "--crop-w 150 --crop-h 150 --quality 100";
        byte[] imageBytes = html2ImageBytes(cmdByImage, sourceFilePath, targetPngFilePath);
        byte[] pdfBytes = html2PdfBytes("", sourceFilePath, targetPdfFilePath);
    }

    /**
     * html转图片
     *
     * @param cmd            工具操作指令
     * @param sourceFilePath html源资源
     * @param targetFilePath 生成目标资源
     * @return 图片字节码
     * @author zhengqingya
     * @date 2021/8/12 11:09
     */
    public static byte[] html2ImageBytes(String cmd, String sourceFilePath, String targetFilePath) {
        return baseTool("wkhtmltoimage", cmd, sourceFilePath, targetFilePath);
    }

    /**
     * html转pdf
     *
     * @param cmd            工具操作指令
     * @param sourceFilePath html源资源
     * @param targetFilePath 生成目标资源
     * @return pdf字节码
     * @author zhengqingya
     * @date 2021/8/12 11:09
     */
    public static byte[] html2PdfBytes(String cmd, String sourceFilePath, String targetFilePath) {
        return baseTool("wkhtmltopdf", cmd, sourceFilePath, targetFilePath);
    }

    /**
     * 工具基础操作
     *
     * @param tool           工具
     * @param cmd            工具操作指令
     * @param sourceFilePath html源资源
     * @param targetFilePath 生成目标资源
     * @return 字节码
     * @author zhengqingya
     * @date 2021/8/12 11:08
     */
    @SneakyThrows({Exception.class})
    private static byte[] baseTool(String tool, String cmd, String sourceFilePath, String targetFilePath) {
        // 先创建父目录
        FileUtil.mkParentDirs(targetFilePath);
        String command = String.format("%s %s %s %s", getToolRootPath() + tool, cmd, sourceFilePath, targetFilePath);
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            throw new Exception("工具丢失，请联系系统管理员！");
        }
        // 等待当前命令执行完，再往下执行
        process.waitFor();
        log.info("=============== FINISH: [{}] ===============", command);
        return FileUtil.readBytes(targetFilePath);
    }

    /**
     * 根据不同系统获取工具
     *
     * @return 工具位置
     * @author zhengqingya
     * @date 2021/8/12 11:07
     */
    private static String getToolRootPath() {
        String system = System.getProperty("os.name");
        if (system.contains("Windows")) {
            return TOOL_WIN_ROOT_DIRECTORY;
        } else if (system.contains("Linux") || system.contains("Mac OS X")) {
            return "";
        }
        return "";
    }

}
