package com.playground.pdf.components;

import com.playground.pdf.entities.Style;

public interface Component {
    void calculate();
    Style getStyle();
    void setStyle(Style style);
    void render(Style style);
}
