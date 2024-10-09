package com.playground.pdf.services;

import java.util.Set;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.playground.pdf.PDFGenerator;
import com.playground.pdf.components.Component;
import com.playground.pdf.dtos.PDFStyleDTO;
import com.playground.pdf.entities.PDFStyle;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;

import java.util.List;

@Service
@AllArgsConstructor
public class PDFStyleService {
    private final ModelMapper modelMapper;
    private final Validator validator;

    public PDFStyle convertToPDFStyle(PDFStyleDTO styleDTO) {
        PDFStyle pdfStyle = modelMapper.map(styleDTO, PDFStyle.class);

        // Perform validation using Hibernate Validator
        Set<ConstraintViolation<PDFStyle>> violations = validator.validate(pdfStyle);
        if (!violations.isEmpty()) {
            for (ConstraintViolation<PDFStyle> violation : violations) {
                throw new IllegalArgumentException("Validation failed: " + violation.getMessage());
            }
        }

        return pdfStyle;
    }

    public PDFStyleDTO convertToDTO(PDFStyle pdfStyle) {
        return modelMapper.map(pdfStyle, PDFStyleDTO.class);
    }

    public void generatePDF(PDFStyleDTO styleDTO, List<Component> components) {
        PDFStyle style = convertToPDFStyle(styleDTO);
        PDFGenerator generator = new PDFGenerator(components);
        generator.generatePDF(style);
    }
}

