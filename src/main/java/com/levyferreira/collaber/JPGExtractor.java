package com.levyferreira.collaber;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class JPGExtractor {

    protected static void extractJPG(File input, String outputPath)  {

        try {
            PDDocument document = PDDocument.load(new File(input.getAbsolutePath()));
            PDFRenderer pdfRenderer = new PDFRenderer(document);
            int pageCounter = 0;
            for (PDPage page : document.getPages())
            {
                // note that the page number parameter is zero based
                BufferedImage bim = pdfRenderer.renderImageWithDPI(pageCounter, 100, ImageType.RGB);

                // suffix in filename will be used as the file format
                ImageIOUtil.writeImage(bim, outputPath + (pageCounter++) + ".jpg", 100);
            }
            document.close();
            System.out.println("Conversão Completa!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
