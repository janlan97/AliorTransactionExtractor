package model;

import java.util.List;

import com.itextpdf.kernel.pdf.PdfDocument;

public interface PDFDataExtractor<T extends PDFObject> {

    String beforeProcessing(PdfDocument doc); // replace sequences etc...

    List<String> processComposite(String pdfRawText); // for series of single objects, return null if not needed for particular pdfObject

    T create(String pdfRawText); // initialize single instance of pdfobject
}