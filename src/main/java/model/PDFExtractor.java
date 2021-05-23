package model;

import com.itextpdf.kernel.pdf.PdfDocument;

import java.util.*;

public class PDFExtractor {
    private Set<PDFDataExtractor> extractors;
    private PdfDocument doc;

    public PDFExtractor(PdfDocument doc, Set<PDFDataExtractor> extractors) {
        this.doc = doc;
        this.extractors = extractors;
    }

    public Map<String, List<PDFObject>> extractPDFObjects() {
        Map<String, List<PDFObject>> extractedObjects = new HashMap<String, List<PDFObject>>();
        for (PDFDataExtractor e : extractors) {
            List<PDFObject> objs = new ArrayList<>();
            String processedRawPdfText = e.beforeProcessing(doc);
            List<String> chunks = e.processComposite(processedRawPdfText);
            if (chunks == null) {
                PDFObject obj = (PDFObject) e.create(processedRawPdfText);
                objs.add(obj);
                extractedObjects.put(obj.getClass().getSimpleName(), objs);
            } else {
                String className = null;
                for (String chunk : chunks) {
                    PDFObject obj = (PDFObject) e.create(chunk);
                    System.out.println(obj);
                    if (className == null) {
                        className = obj.getClass().getSimpleName();
                    }
                    objs.add(obj);
                }
                extractedObjects.put(className, objs);
            }
        }
        return extractedObjects;
    }
}
