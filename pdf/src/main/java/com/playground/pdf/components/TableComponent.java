package com.playground.pdf.components;

import com.playground.pdf.entities.PDFStyle;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TableComponent implements Component {
    private String[][] data;
    private PDFStyle style = new PDFStyle();

    @Override
    public void render(PDFStyle style) {
        System.out.println("Rendering table with data:");
        for (String[] row : data) {
            for (String cell : row) {
                System.out.print(cell + "\t");
            }
            System.out.println();
        }
    }

    @Override
    public void calculate() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calculate'");
    }
}
