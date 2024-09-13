package org.example.pdf2word;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.pdf2word.service.Log;

import java.io.IOException;

public class PDF2WordApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(PDF2WordApplication.class.getResource("view/index.fxml"));
        Scene scene = new Scene(loader.load(), 800, 550);
        stage.setTitle("文件转换工具");
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(event -> {
            String filePath = "./log/log.txt";
            Log.save(filePath);
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}