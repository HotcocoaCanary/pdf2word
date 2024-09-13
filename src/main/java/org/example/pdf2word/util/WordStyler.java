package org.example.pdf2word.util;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.example.pdf2word.service.Log;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

/**
 * @Description: 应用样式到 Word 文档
 * @Author: Canary
 * @Date: 2024/9/9 下午3:58
 */
public class WordStyler {

    public static void applyStylesToWord(String wordPath, Map<String, Map<String, Boolean>> styles) {
        try {
            FileInputStream fis = new FileInputStream(wordPath);
            XWPFDocument document = new XWPFDocument(fis);

            for (XWPFParagraph paragraph : document.getParagraphs()) {
                for (XWPFRun run : paragraph.getRuns()) {
                    String text = run.getText(0);

                    // 根据提取的 PDF 样式，修改 Word 文档中的 run
                    if (text != null && styles.containsKey(text.trim())) {
                        Map<String, Boolean> textStyles = styles.get(text.trim());
                        run.setBold(textStyles.getOrDefault("bold", false));
                        run.setItalic(textStyles.getOrDefault("italic", false));

                        // 使用 getFontSizeAsDouble() 获取字号
                        Double fontSize = run.getFontSizeAsDouble();

                        // 针对 12.5 和 21.5 大小的字号进行特殊处理
                        if (fontSize != null && (fontSize == 12.5 || fontSize == 21.5)) {
                            // 为段落设置 0.5 行的段前间距 (按行计算)
                            paragraph.setSpacingBeforeLines(50); // 50 表示 0.5 行
                            // 向上移动 0.5 行，通过设置文本位置
                            run.setTextPosition(5); // 向上移动 0.5 行（正值为上，负值为下）
                        }
                    }
                }
            }
            // 保存修改后的 Word 文档
            FileOutputStream fos = new FileOutputStream(wordPath);
            document.write(fos);
            fos.close();
            document.close();

            Log.info("提取PDF样式到word成功");

        } catch (IOException e) {
            Log.error("找不到对应路径的文件："+e.getMessage());
        }


    }
}
