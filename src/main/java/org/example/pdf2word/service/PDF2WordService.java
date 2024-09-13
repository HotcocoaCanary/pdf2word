package org.example.pdf2word.service;

import org.example.pdf2word.util.PdfStyleExtractor;
import org.example.pdf2word.util.PdfToWordConverter;
import org.example.pdf2word.util.RemoveWatermark;
import org.example.pdf2word.util.WordStyler;

import java.io.IOException;
import java.nio.file.*;
import java.util.Map;

/**
 * @Description: 文件转化
 * @Author: Canary
 * @Date: 2024/9/9 下午4:01
 */

public class PDF2WordService {

    public void batchFile(String directoryPath) {
        Log.info("开始识别传入的文件夹中的PDF文件");
        String wordDirectoryPath = directoryPath+"/output/";
        Path dir = Paths.get(directoryPath);
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, path -> path.toString().endsWith(".pdf"))) {
            for (Path file : stream) {
                String wordPath = wordDirectoryPath + file.getFileName().toString().substring(0, file.getFileName().toString().lastIndexOf(".pdf")) + ".docx";;
                String pdfPath = file.toAbsolutePath().toString();
                pdfToWord(pdfPath, wordPath);
            }
        } catch (IOException | DirectoryIteratorException e) {
            Log.error("传入的文件格式不合法");
        }
    }

    public void soleFile(String filePath) {
        try {
            Log.info("开始识别传入的文件格式");
            String wordPath = filePath.substring(0, filePath.lastIndexOf(".pdf")) + ".docx";
            pdfToWord(filePath, wordPath);
        } catch (Exception e) {
            Log.error("传入的文件格式不合法");
        }
    }

    private void pdfToWord(String pdfPath, String wordPath) {
        // 构造Word文件路径，使用同路径同名，但扩展名为.docx
        try {
            Log.info("开始将PDF:"+pdfPath+"转化为Word，保存在:"+wordPath);
            // 1. PDF 转 Word
            PdfToWordConverter.convertPdfToWord(pdfPath, wordPath);
            // 2. 提取 PDF 中的样式和格式信息
            Map<String, Map<String, Boolean>> styles = PdfStyleExtractor.extractStyles(pdfPath);
            // 3. 应用样式到 Word 文档
            WordStyler.applyStylesToWord(wordPath, styles);
            // 4. 去除水印
            RemoveWatermark.removeWordWatermark(wordPath);
            Log.info("转换成功");
        } catch (Exception e) {
            Log.error("转换错误:"+e.getMessage());
        }
    }

}
