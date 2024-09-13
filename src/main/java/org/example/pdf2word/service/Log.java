package org.example.pdf2word.service;

import org.example.pdf2word.controller.PDF2WordController;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Canary
 * @version 1.0.0
 * @title Log
 * @description <TODO description class purpose>
 * @creat 2024/9/12 下午8:52
 **/
public class Log {

    private static final PDF2WordController controller=new PDF2WordController();

    private static String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }

    public static void info(String message) {
        controller.logger(getCurrentDateTime()+"[info]"+message);
    }

    public static void error(String message) {
        controller.logger(getCurrentDateTime()+"[error]"+message);
    }

    public static void save(String filePath) {
        String log = controller.getLog();
        // 替换为你的文件路径
        Path path = Paths.get(filePath);
        try {
            // 创建父目录（如果不存在）
            Files.createDirectories(path.getParent());
            // 写入文件
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
                writer.write(log);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
