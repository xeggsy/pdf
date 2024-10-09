package com.playground.pdf.services;

import java.util.Set;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.playground.pdf.PDFGenerator;
import com.playground.pdf.components.Component;
import com.playground.pdf.dtos.StyleDTO;
import com.playground.pdf.entities.Style;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;

import java.util.List;

@Service
@AllArgsConstructor
public class StyleService {
    private final ModelMapper modelMapper;
    private final Validator validator;

    public Style convertToPDFStyle(StyleDTO styleDTO) {
        Style style = modelMapper.map(styleDTO, Style.class);

        Set<ConstraintViolation<Style>> violations = validator.validate(style);
        if (!violations.isEmpty()) {
            for (ConstraintViolation<Style> violation : violations) {
                throw new IllegalArgumentException("Validation failed: " + violation.getMessage());
            }
        }

        return style;
    }

    public StyleDTO convertToDTO(Style style) {
        return modelMapper.map(style, StyleDTO.class);
    }

    public void generatePDF(StyleDTO styleDTO, List<Component> components) {
        Style style = convertToPDFStyle(styleDTO);
        PDFGenerator generator = new PDFGenerator(components);
        generator.generatePDF(style);
    }
}

