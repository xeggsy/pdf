package com.playground.pdf.entities;

package com.example.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
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

