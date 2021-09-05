package com.levyferreira.collaber;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class ApplicationController extends Application {
    @FXML
    TextField tfPath;
    @FXML
    Button btRun;
    private Stage stage;



    @FXML
    private File selectFile(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecione o Arquivo");
        File file = fileChooser.showOpenDialog(stage);
        if(file !=null){
            tfPath.setText(file.getAbsolutePath());
        }
        return file;
    }

    public void processs(ActionEvent actionEvent) {
        if (!tfPath.getText().isBlank() ){
            File f = new File(tfPath.getText());
            if (f.isFile()){
                String dirname = f.getName().replaceAll("[^a-zA-Z0-9]","");
                String outputdir = f.getParent() +"/"+dirname+"/";

                new File(outputdir).mkdirs();

                try {
                    JPGExtractor.extractJPG(f,outputdir);
                    Zipper.ZipDirectory(outputdir,f.getParent()+"/"+dirname+".zip");
                    FileUtils.deleteDirectory(new File(outputdir));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }else {
            System.out.println("arquivo vazio ou inválido");
        }

    }



    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
    }
}