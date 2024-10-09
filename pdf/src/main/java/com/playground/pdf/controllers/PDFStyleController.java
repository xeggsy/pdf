package com.playground.pdf.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.playground.pdf.components.Component;
import com.playground.pdf.dtos.PDFStyleDTO;
import com.playground.pdf.entities.PDFStyle;
import com.playground.pdf.services.PDFStyleService;

import lombok.AllArgsConstructor;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/pdf-style")
public class PDFStyleController {

    private final PDFStyleService pdfStyleService;

    @PostMapping("/convert")
    public PDFStyle convertStyle(@RequestBody PDFStyleDTO pdfStyleDTO) {
        return pdfStyleService.convertToPDFStyle(pdfStyleDTO);
    }

    @GetMapping("/dto")
    public PDFStyleDTO getPDFStyleDTO(@RequestParam String fontFamily) {
        PDFStyle pdfStyle = new PDFStyle();
        pdfStyle.setFontFamily(fontFamily);
        return pdfStyleService.convertToDTO(pdfStyle);
    }

    @PostMapping("/generate")
    public void generatePDF(@RequestBody PDFStyleDTO pdfStyleDTO, @RequestBody List<Component> components) {
        pdfStyleService.generatePDF(pdfStyleDTO, components);
    }
}
