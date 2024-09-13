package org.example.pdf2word.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import org.example.pdf2word.service.PDF2WordService;

import java.io.File;

public class PDF2WordController {

    private PDF2WordService PDF2WordService;
    // 成员变量用于存储选择的路径
    private String selectedPath;

    @FXML
    private TextArea log = new TextArea();

    @FXML
    private ToggleButton batchButton,soleButton;

    @FXML
    public Button selectButton;

    @FXML
    public Button submitButton;

    public void logger(String message) {
        Platform.runLater(() -> log.appendText(message + "\n"));
    }

    public String getLog() {
        return log.getText();
    }

    @FXML
    private void initialize() {
        PDF2WordService = new PDF2WordService();

        // ToggleGroup 实例
        ToggleGroup sourceGroup = new ToggleGroup();
        batchButton.setToggleGroup(sourceGroup);
        soleButton.setToggleGroup(sourceGroup);

        // Set default selection
        batchButton.setSelected(true);

        // Initialize the select button text
        selectButton.setText("选择文件夹");

        // Handle toggle button action
        sourceGroup.selectedToggleProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == batchButton) {
                selectButton.setText("选择文件夹");
            } else {
                selectButton.setText("选择文件");
            }
        });

    }

    @FXML
    private void handleSelectButtonAction() {
        if (batchButton.isSelected()) {
            // 处理批处理文件夹选择
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("选择PDF文件夹");
            File selectedDirectory = directoryChooser.showDialog(selectButton.getScene().getWindow());
            if (selectedDirectory != null) {
                selectedPath = selectedDirectory.getAbsolutePath();
                log.appendText("选择的文件夹: " + selectedPath + "\n");
            }
        } else {
            // 处理单个文件选择
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("选择PDF文件");
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
            File selectedFile = fileChooser.showOpenDialog(selectButton.getScene().getWindow());
            if (selectedFile != null) {
                selectedPath = selectedFile.getAbsolutePath();
                log.appendText("选择的文件: " + selectedPath + "\n");
            }
        }
    }

    @FXML
    private void handleSubmitButtonAction() {
        if (selectedPath == null || selectedPath.isEmpty()) {
            log.appendText("请先选择文件或文件夹。\n");
            return;
        }

        if (batchButton.isSelected()) {
            log.appendText("开始批处理转换...\n");
            PDF2WordService.batchFile(selectedPath);
            log.appendText("批处理转换完成!\n");
        } else {
            // 实现单一文件转换逻辑
            log.appendText("开始单一文件转换...\n");
            PDF2WordService.soleFile(selectedPath);
            // ...单一文件转换逻辑...
            log.appendText("单一文件转换完成!\n");
        }
    }

}
