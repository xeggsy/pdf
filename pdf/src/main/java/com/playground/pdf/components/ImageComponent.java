package com.playground.pdf.components;

import com.playground.pdf.entities.PDFStyle;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ImageComponent implements Component {
    private String imagePath;
    private PDFStyle style = new PDFStyle();

    @Override
    public void render(PDFStyle style) {
        System.out.println("Rendering image from path: " + imagePath);
        // Styling logic using PDFStyle
    }

    @Override
    public void calculate() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calculate'");
    }
}
