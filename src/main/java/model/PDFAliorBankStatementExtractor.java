package model;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;

import java.util.List;

public class PDFAliorBankStatementExtractor implements PDFDataExtractor<PDFAliorBankStatement> {

    @Override
    public String beforeProcessing(PdfDocument doc) {
        return PdfTextExtractor.getTextFromPage(doc.getPage(1))
                .replaceAll("\\u00A0", " ");
    }

    @Override
    public List<String> processComposite(String pdfRawText) {
        return null;
    }

    @Override
    public PDFAliorBankStatement create(String pdfRawText) {
        return new PDFAliorBankStatement(pdfRawText);
    }

}
