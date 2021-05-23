package model;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PDFAliorTransactionExtractor implements PDFDataExtractor<PDFAliorTransaction> {

    @Override
    public String beforeProcessing(PdfDocument doc) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= doc.getNumberOfPages() - 0; i++) {
            String textFromPdf = PdfTextExtractor.getTextFromPage(doc.getPage(i));
            String rawTextTrimmed = textFromPdf.replaceAll("\\u00A0", " ")
                    .replace(Constants.valueOf("txt0"), "")
                    .replace(Constants.valueOf("txt1"), "")
                    .replaceAll(Constants.valueOf("txt2"), "");
            sb.append(rawTextTrimmed).append("\n");
        }
        return sb.toString();
    }

    @Override
    public List<String> processComposite(String pdfRawText) {
        List<String> matchResults = new ArrayList<>();
        Matcher matcher = Pattern.compile(Constants.valueOf("transactionData"), Pattern.MULTILINE).matcher(pdfRawText);
        while (matcher.find()) {
            matchResults.add(matcher.group());
        }
        return matchResults;
    }

    @Override
    public PDFAliorTransaction create(String pdfRawText) {
        return new PDFAliorTransaction(pdfRawText);
    }
}
