package com.playground.pdf.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class StyleDTO {

    @NotNull(message = "Font family cannot be null")
    @Size(min = 3, max = 50, message = "Font family must be between 3 and 50 characters")
    private String fontFamily;

    @Min(value = 5, message = "Font size must be at least 5")
    @Max(value = 50, message = "Font size must be at most 50")
    private int fontSize;

    private boolean bold;
    private boolean italic;
    private boolean underline;

    @Pattern(regexp = "^#([A-Fa-f0-9]{6})$", message = "Text color must be a valid hex color code (e.g. #FFFFFF)")
    private String textColor;

    @Pattern(regexp = "^#([A-Fa-f0-9]{6})$", message = "Background color must be a valid hex color code (e.g. #FFFFFF)")
    private String backgroundColor;

    @Min(value = 0, message = "X position cannot be negative")
    private int x;

    @Min(value = 0, message = "Y position cannot be negative")
    private int y;

    @Min(value = 0, message = "Top margin cannot be negative")
    private int marginTop;

    @Min(value = 0, message = "Right margin cannot be negative")
    private int marginRight;

    @Min(value = 0, message = "Bottom margin cannot be negative")
    private int marginBottom;

    @Min(value = 0, message = "Left margin cannot be negative")
    private int marginLeft;

    @Min(value = 0, message = "Padding top cannot be negative")
    private int paddingTop;

    @Min(value = 0, message = "Padding right cannot be negative")
    private int paddingRight;

    @Min(value = 0, message = "Padding bottom cannot be negative")
    private int paddingBottom;

    @Min(value = 0, message = "Padding left cannot be negative")
    private int paddingLeft;

    @Pattern(regexp = "^(LEFT|CENTER|RIGHT)$", message = "Alignment must be one of LEFT, CENTER, or RIGHT")
    private String alignment;
}