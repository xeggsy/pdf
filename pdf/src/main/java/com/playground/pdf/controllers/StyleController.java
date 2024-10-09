package com.playground.pdf.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.playground.pdf.components.Component;
import com.playground.pdf.dtos.StyleDTO;
import com.playground.pdf.entities.Style;
import com.playground.pdf.services.StyleService;

import lombok.AllArgsConstructor;

import java.util.List;

// TODO Do I want to make this as a generic controller interface to be implemented (thus with default methods)?
@RestController
@AllArgsConstructor
@RequestMapping("/pdf/style")
public class StyleController {

    private final StyleService styleService;

    @PostMapping("/convert")
    public Style convertStyle(@RequestBody StyleDTO styleDTO) {
        return styleService.convertToPDFStyle(styleDTO);
    }

    @GetMapping("/dto")
    public StyleDTO getPDFStyleDTO(@RequestParam String fontFamily) {
        Style style = new Style();
        style.setFontFamily(fontFamily);
        return styleService.convertToDTO(style);
    }

    @PostMapping("/generate")
    public void generatePDF(@RequestBody StyleDTO styleDTO, @RequestBody List<Component> components) {
        styleService.generatePDF(styleDTO, components);
    }
}
