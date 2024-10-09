package com.playground.pdf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.playground.pdf.components.Component;
import com.playground.pdf.entities.PDFStyle;

import java.util.List;

@SpringBootApplication
public class PDFGenerator {

    private List<Component> components;

    public PDFGenerator(List<Component> components) {
        this.components = components;
    }

    public void generatePDF(PDFStyle style) {
        for (Component component : components) {
            component.render(style);
        }
        System.out.println("PDF generated with style: " + style);
    }

	public static void main(String[] args) {
		SpringApplication.run(PDFGenerator.class, args);
	}
}