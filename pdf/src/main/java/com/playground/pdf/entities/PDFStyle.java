package com.playground.pdf.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class PDFStyle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fontFamily;
    private int fontSize;
    private boolean bold;
    private boolean italic;
    private boolean underline;
    private String textColor;
    private String backgroundColor;
    private int x;
    private int y;
    private int marginTop;
    private int marginRight;
    private int marginBottom;
    private int marginLeft;
    private int paddingTop;
    private int paddingRight;
    private int paddingBottom;
    private int paddingLeft;
    private String alignment;
}

