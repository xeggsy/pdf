package com.playground.pdf.components;

import com.playground.pdf.entities.Style;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TableComponent implements Component {
    private String[][] data;
    private Style style = new Style();

    @Override
    public void render(Style style) {
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
