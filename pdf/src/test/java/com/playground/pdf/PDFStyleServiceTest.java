package com.playground.pdf;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import com.playground.pdf.components.Component;
import com.playground.pdf.components.ImageComponent;
import com.playground.pdf.components.TextComponent;
import com.playground.pdf.dtos.PDFStyleDTO;
import com.playground.pdf.entities.PDFStyle;
import com.playground.pdf.services.PDFStyleService;

import jakarta.validation.Validation;
import jakarta.validation.Validator;

import java.util.List;

@SpringBootTest
class PDFStyleServiceTest {

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private PDFStyleService pdfStyleService;

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void whenValidStyle_thenNoViolations() {
        PDFStyleDTO styleDTO = new PDFStyleDTO();
        styleDTO.setFontFamily("Arial");
        styleDTO.setFontSize(14);
        styleDTO.setTextColor("#FFFFFF");
        styleDTO.setAlignment("CENTER");

        Assertions.assertDoesNotThrow(() -> pdfStyleService.convertToPDFStyle(styleDTO));
    }

    @Test
    void whenInvalidFontSize_thenThrowException() {
        PDFStyleDTO styleDTO = new PDFStyleDTO();
        styleDTO.setFontFamily("Arial");
        styleDTO.setFontSize(60); // Invalid size, should be < 50
        styleDTO.setTextColor("#FFFFFF");
        styleDTO.setAlignment("CENTER");

        Assertions.assertThrows(IllegalArgumentException.class, () -> pdfStyleService.convertToPDFStyle(styleDTO));
    }

    @Test
    void whenInvalidTextColor_thenThrowException() {
        PDFStyleDTO styleDTO = new PDFStyleDTO();
        styleDTO.setFontFamily("Arial");
        styleDTO.setFontSize(14);
        styleDTO.setTextColor("#ZZZZZZ"); // Invalid hex code
        styleDTO.setAlignment("CENTER");

        Assertions.assertThrows(IllegalArgumentException.class, () -> pdfStyleService.convertToPDFStyle(styleDTO));
    }

    @Test
    void whenInvalidAlignment_thenThrowException() {
        PDFStyleDTO styleDTO = new PDFStyleDTO();
        styleDTO.setFontFamily("Arial");
        styleDTO.setFontSize(14);
        styleDTO.setTextColor("#FFFFFF");
        styleDTO.setAlignment("MIDDLE"); // Invalid alignment value

        Assertions.assertThrows(IllegalArgumentException.class, () -> pdfStyleService.convertToPDFStyle(styleDTO));
    }

    @Test
    void whenMissingRequiredFields_thenThrowException() {
        PDFStyleDTO styleDTO = new PDFStyleDTO();
        // Missing font family
        styleDTO.setFontSize(14);
        styleDTO.setTextColor("#FFFFFF");
        styleDTO.setAlignment("CENTER");

        Assertions.assertThrows(IllegalArgumentException.class, () -> pdfStyleService.convertToPDFStyle(styleDTO));
    }

    @Test
    void testGeneratePDFWithValidComponents() {
        PDFStyleDTO styleDTO = new PDFStyleDTO();
        styleDTO.setFontFamily("Arial");
        styleDTO.setFontSize(14);
        styleDTO.setTextColor("#000000");
        styleDTO.setAlignment("LEFT");

        List<Component> components = List.of(
            new TextComponent("Hello World!", modelMapper.map(styleDTO, PDFStyle.class)),
            new ImageComponent("/path/to/image.png", modelMapper.map(styleDTO, PDFStyle.class))
        );

        Assertions.assertDoesNotThrow(() -> pdfStyleService.generatePDF(styleDTO, components));
    }

    @Test
    void testGeneratePDFWithNoComponents() {
        PDFStyleDTO styleDTO = new PDFStyleDTO();
        styleDTO.setFontFamily("Arial");
        styleDTO.setFontSize(14);
        styleDTO.setTextColor("#000000");
        styleDTO.setAlignment("LEFT");

        List<Component> components = List.of();

        Assertions.assertDoesNotThrow(() -> pdfStyleService.generatePDF(styleDTO, components));
    }

    @Test
    void testGeneratePDFWithExtremeMargins() {
        PDFStyleDTO styleDTO = new PDFStyleDTO();
        styleDTO.setFontFamily("Arial");
        styleDTO.setFontSize(14);
        styleDTO.setTextColor("#000000");
        styleDTO.setAlignment("CENTER");

        styleDTO.setMarginTop(1000);
        styleDTO.setMarginRight(1000);
        styleDTO.setMarginBottom(1000);
        styleDTO.setMarginLeft(1000);

        List<Component> components = List.of(
            new TextComponent("Extreme margins!", modelMapper.map(styleDTO, PDFStyle.class))
        );

        Assertions.assertDoesNotThrow(() -> pdfStyleService.generatePDF(styleDTO, components));
    }
    @Test
    void testGeneratePDFWithNegativeMargins() {
        PDFStyleDTO styleDTO = new PDFStyleDTO();
        styleDTO.setFontFamily("Arial");
        styleDTO.setFontSize(14);
        styleDTO.setTextColor("#000000");
        styleDTO.setAlignment("CENTER");

        styleDTO.setMarginTop(-1); // Must be positive
        styleDTO.setMarginRight(-1);
        styleDTO.setMarginBottom(-1);
        styleDTO.setMarginLeft(-1);

        List<Component> components = List.of(
            new TextComponent("Negative margins!", modelMapper.map(styleDTO, PDFStyle.class))
        );

        Assertions.assertThrows(IllegalArgumentException.class, () -> pdfStyleService.generatePDF(styleDTO, components));
    }

    @Test
    void testApplyStyleToComponent() {
        PDFStyleDTO styleDTO = new PDFStyleDTO();
        styleDTO.setFontFamily("Arial");
        styleDTO.setFontSize(16);
        styleDTO.setTextColor("#0000FF"); // Blue
        styleDTO.setAlignment("LEFT");

        TextComponent textComponent = new TextComponent("Styled Text", modelMapper.map(styleDTO, PDFStyle.class));

        Assertions.assertEquals("Arial", textComponent.getStyle().getFontFamily());
        Assertions.assertEquals(16, textComponent.getStyle().getFontSize());
        Assertions.assertEquals("#0000FF", textComponent.getStyle().getTextColor());
    }

    @Test
    void testFullPDFGenerationWorkflow() {
        PDFStyleDTO styleDTO = new PDFStyleDTO();
        styleDTO.setFontFamily("Arial");
        styleDTO.setFontSize(14);
        styleDTO.setTextColor("#FFFFFF");
        styleDTO.setAlignment("CENTER");

        List<Component> components = List.of(
            new TextComponent("Hello, World!", modelMapper.map(styleDTO, PDFStyle.class)),
            new ImageComponent("/path/to/logo.png", modelMapper.map(styleDTO, PDFStyle.class))
        );

        Assertions.assertDoesNotThrow(() -> pdfStyleService.generatePDF(styleDTO, components));
    }

    @Test
    void testFullPDFGenerationWithInvalidStyle() {
        PDFStyleDTO styleDTO = new PDFStyleDTO();
        styleDTO.setFontFamily("Arial");
        styleDTO.setFontSize(60); // Invalid font size (>50)
        styleDTO.setTextColor("#FFFFFF");
        styleDTO.setAlignment("CENTER");

        List<Component> components = List.of(
            new TextComponent("Hello, World!", modelMapper.map(styleDTO, PDFStyle.class)),
            new ImageComponent("/path/to/logo.png", modelMapper.map(styleDTO, PDFStyle.class))
        );

        Assertions.assertThrows(IllegalArgumentException.class, () -> pdfStyleService.generatePDF(styleDTO, components));
    }
}
