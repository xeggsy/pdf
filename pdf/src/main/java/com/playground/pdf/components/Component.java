package com.playground.pdf.components;

import com.playground.pdf.entities.PDFStyle;

public interface Component {
    void calculate();
    PDFStyle getStyle();
    void setStyle(PDFStyle style);
    void render(PDFStyle style);
}
