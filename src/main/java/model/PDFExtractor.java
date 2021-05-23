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
        for (PDFDataExtractor extractor : extractors) {
            List<PDFObject> extractedPDFObjectInstances = new ArrayList<>();

            String processedRawPdfText = extractor.beforeProcessing(doc);

            List<String> chunks = extractor.processComposite(processedRawPdfText);

            if (chunks == null) {
                PDFObject extractedPdfObj = extractor.create(processedRawPdfText);
                extractedPDFObjectInstances.add(extractedPdfObj);
                extractedObjects.put(extractedPdfObj.getClass().getSimpleName(), extractedPDFObjectInstances);
            } else {
                String PDFObjectClassName = null;
                for (String chunk : chunks) {
                    PDFObject extractedPdfObj = extractor.create(chunk);
                    if (PDFObjectClassName == null) {
                        PDFObjectClassName = extractedPdfObj.getClass().getSimpleName();
                    }
                    extractedPDFObjectInstances.add(extractedPdfObj);
                }
                extractedObjects.put(PDFObjectClassName, extractedPDFObjectInstances);
            }
        }
        return extractedObjects;
    }
}
