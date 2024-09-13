package org.example.pdf2word.util;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 提取 PDF 中的样式和格式信息
 * @Author: Canary
 * @Date: 2024/9/9 下午3:58
 */

public class PdfStyleExtractor extends PDFTextStripper {

    private final Map<String, Map<String, Boolean>> textStyles = new HashMap<>();

    public PdfStyleExtractor() throws IOException {
        super.setSortByPosition(true);
    }

    @Override
    protected void processTextPosition(TextPosition text) {
        String textStr = text.getUnicode();
        // 创建一个存储样式信息的 map
        Map<String, Boolean> styles = new HashMap<>();
        // 检测字体是否加粗、斜体等
        String fontName = text.getFont().getName().toLowerCase();
        styles.put("bold", fontName.contains("bold"));
        styles.put("italic", fontName.contains("italic"));
        // 保存样式信息
        textStyles.put(textStr, styles);
        super.processTextPosition(text);
    }

    public static Map<String, Map<String, Boolean>> extractStyles(String pdfPath) throws IOException {
        PDDocument document = PDDocument.load(new java.io.File(pdfPath));
        PdfStyleExtractor stripper = new PdfStyleExtractor();
        stripper.getText(document);
        document.close();
        return stripper.getTextStyles();
    }

    private Map<String, Map<String, Boolean>> getTextStyles() {
        return textStyles;
    }
}
