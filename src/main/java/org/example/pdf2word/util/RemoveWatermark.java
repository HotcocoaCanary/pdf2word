package org.example.pdf2word.util;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.example.pdf2word.service.Log;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author Canary
 * @version 1.0.0
 * @title RemoveWatermark
 * @description <TODO description class purpose>
 * @creat 2024/9/11 下午5:45
 **/
public class RemoveWatermark {

    public static void removeWordWatermark(String filePath){
        try {
            FileInputStream fis = new FileInputStream(filePath);
            XWPFDocument document = new XWPFDocument(fis);

            for (XWPFParagraph paragraph : document.getParagraphs()) {
                String text = paragraph.getText();
                if (text != null && text.contains("Evaluation Only. Created with Aspose.PDF. Copyright 2002-2024 Aspose Pty Ltd.")) {
                    List<XWPFRun> runs = paragraph.getRuns();
                    for (XWPFRun run : runs) {
                        run.setText("", 0);
                    }
                }
            }

            FileOutputStream fos = new FileOutputStream(filePath);
            document.write(fos);
            fos.close();
            document.close();
            fis.close();
            Log.info("尝试去除水印成功");
            System.out.println();
        } catch (IOException e) {
            Log.error("找不到对应路径的文件："+e.getMessage());
        }

    }

}
