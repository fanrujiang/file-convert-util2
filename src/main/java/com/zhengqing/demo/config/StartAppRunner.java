package com.zhengqing.demo.config;

import com.aspose.words.License;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStream;

/**
 * <p>
 * 服务初始化之后，执行方法
 * </p>
 *
 * @author zhengqing
 * @description
 * @date 2020/5/22 19:29
 */
@Slf4j
@Component
public class StartAppRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        log.info("《服务初始化执行处理》 start...");
        try {
            log.info("实现`aspose-words`授权 -> 去掉头部水印");
            /*
              实现匹配文件授权 -> 去掉头部水印 `Evaluation Only. Created with Aspose.Words. Copyright 2003-2018 Aspose Pty Ltd.` |
                                          `Evaluation Only. Created with Aspose.Cells for Java. Copyright 2003 - 2020 Aspose Pty Ltd.`
             */
            InputStream is = new ClassPathResource("license.xml").getInputStream();
            License license = new License();
            license.setLicense(is);
        } catch (Exception e) {
            log.error("《`aspose-words`授权》 失败： {}", e.getMessage());
        }
        log.info("《服务初始化执行处理》 end...");
    }

}
