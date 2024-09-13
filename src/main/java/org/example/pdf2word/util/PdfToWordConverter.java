package org.example.pdf2word.util;

import com.aspose.pdf.Document;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.example.pdf2word.service.Log;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * @Description: PDF 转 Word
 * @Author: Canary
 * @Date: 2024/9/9 下午3:58
 */
public class PdfToWordConverter {


    public static void convertPdfToWord(String pdfPath, String wordPath) {
        // Load PDF document
        try(Document pdfDocument = new Document(pdfPath)){
            // Save PDF as Word document
            pdfDocument.save(wordPath, com.aspose.pdf.SaveFormat.DocX);
            // 替换 Word 文档中的无效字符，如“ꢀ”替换为空格
            replaceInvalidCharacters(wordPath);
            Log.info("提取PDF文字到word成功");
        } catch (Exception e) {
            Log.error("提取PDF文字错误，请检查传入的文件路径："+e.getMessage());
        }

    }

    // 手动替换转换后的 Word 文档中的“ꢀ”为正确的空格
    private static void replaceInvalidCharacters(String wordPath) {
        try (FileInputStream fis = new FileInputStream(wordPath);
             XWPFDocument document = new XWPFDocument(fis)) {
            for (XWPFParagraph paragraph : document.getParagraphs()) {
                for (XWPFRun run : paragraph.getRuns()) {
                    String text = run.getText(0);
                    if (text != null) {
                        // 将“ꢀ”替换为空格
                        text = text.replace("ꢀ", " ");
                        run.setText(text, 0);
                    }
                }
            }
            // 保存替换后的文档
            FileOutputStream fos = new FileOutputStream(wordPath);
            document.write(fos);
            fos.close();
            Log.info("替换非法字符成功");
            System.out.println();
        } catch (IOException e) {
            Log.error("替换非法字符错误："+e.getMessage());
        }
    }
}
