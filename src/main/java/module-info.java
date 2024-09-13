module org.example.fileconversiontools {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.apache.pdfbox;
    requires org.apache.poi.ooxml;
    requires aspose.pdf;

    //测试用
    exports org.example.pdf2word.service;

    opens org.example.pdf2word to javafx.fxml;
    exports org.example.pdf2word;
    exports org.example.pdf2word.controller;
    opens org.example.pdf2word.controller to javafx.fxml;
}