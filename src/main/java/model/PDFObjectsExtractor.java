package model;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import format.CSVFormat;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class PDFObjectsExtractor implements CSVFormat<PDFObject> {

    private final File[] pdfs;
    private Set<PDFDataExtractor> extractors;

    public PDFObjectsExtractor(String folderPath, Set<PDFDataExtractor> extractors) throws Exception {
        File folder = new File(folderPath);
        if (folder.isDirectory()) {
            pdfs = new File(folderPath).listFiles();
            this.extractors = extractors;
        } else {
            throw new Exception("Given path does not point to folder.");
        }
    }

    private static PdfDocument loadPdf(String fileName) {
        PdfReader reader = null;
        try {
            reader = new PdfReader(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new PdfDocument(reader);
    }

    public Map<String, List<PDFObject>> extract() {
        Map<String, List<PDFObject>> objs = new HashMap<>();
        for (File pdf : pdfs) {
            PdfDocument pdfDoc = loadPdf(pdf.getAbsolutePath());
            PDFExtractor e = new PDFExtractor(pdfDoc, extractors);
            objs = e.extractPDFObjects();
        }
        return objs;
    }

    public Map<String, String> extractAsCSV() {
        Map<String, String> csv = new HashMap<>();
        Map<String, List<PDFObject>> objs = extract();
        for (Map.Entry<String, List<PDFObject>> e : objs.entrySet()) {
            StringBuilder sb = new StringBuilder();
            String headers = (String) e.getValue().get(0).getFields().keySet().stream().collect(Collectors.joining(","));
            sb.append(headers);
            sb.append("\n");
            for (PDFObject pdfO : e.getValue()) {
                sb.append(pdfO.toCsvLine());
                sb.append("\n");
            }
            csv.put(e.getKey(), sb.toString());
        }
        return csv;
    }

    @Override
    public String convertTo(PDFObject<PDFObject> elem) {
        return elem.getFields().values().stream().map(PDFObjectField::toString).collect(Collectors.joining(","));
    }

    @Override
    public PDFObject<PDFObject> convertBack(String elem) {
        return null;
    }

    @Override
    public List<PDFObject<PDFObject>> readFrom(String path) {
        return null;
    }

    @Override
    public File writeTo(List<PDFObject<PDFObject>> elems, String path) {
        return null;
    }
}
