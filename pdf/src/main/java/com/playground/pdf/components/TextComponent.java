package com.playground.pdf.components;

import com.playground.pdf.entities.Style;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TextComponent implements Component {
    private String text;
    private Style style = new Style();

    @Override
    public void render(Style style) {
        System.out.println("Rendering text: " + text);
        // Styling logic using PDFStyle
    }

    @Override
    public void calculate() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calculate'");
    }
}
