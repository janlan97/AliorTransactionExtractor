package model;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws Exception {

        String path = "C:\\Users\\jan\\Desktop\\wyciagi";

        Set<PDFDataExtractor> extractors = new HashSet<>();
        //extractors.add(new PDFAliorBankStatementExtractor());
        extractors.add(new PDFAliorTransactionExtractor());


        PDFObjectsExtractor extractorService = new PDFObjectsExtractor(path, extractors);

        Map<String, String> objs = extractorService.extractAsCSV();

      //  objs.entrySet().stream().forEach(s -> System.out.println(s.getValue()));

    }
}
