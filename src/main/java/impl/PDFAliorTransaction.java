package impl;

import model.Constants;
import model.FieldTemplate;
import model.PDFObject;
import model.PDFObjectField;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.regex.Pattern;

public class PDFAliorTransaction extends PDFObject {

    public PDFAliorTransaction(String rawPdfText) {
        super(rawPdfText);
    }

    @Override
    protected void setFields() {
        if (fields == null) {
            fields = new HashMap<>();

            final String transactionPattern = Constants.valueOf("transactionData");

            fields.put("Payment_Posting_Date__c", new PDFObjectField<>("paymentPostingDate",
                    new FieldTemplate<>(transactionPattern,
                            s -> LocalDate.parse(s, DateTimeFormatter.ofPattern("yyyy.MM.dd")), 0, 1)));

            fields.put("Transaction_Type__c", new PDFObjectField<>("transactionType", new FieldTemplate<>(Constants.valueOf("transactionType"), s -> s, 0, 1)));

            fields.put("Amount__c", new PDFObjectField<>("amount", new FieldTemplate<>(transactionPattern, s ->
                    BigDecimal.valueOf(Double.parseDouble(s.replace(",", ".").replace(" ", ""))).setScale(2, RoundingMode.CEILING), 0, 5)));

            fields.put("Balance_After_Transaction__c", new PDFObjectField<>("balanceAfterTransaction", new FieldTemplate<>(transactionPattern, s ->
                    BigDecimal.valueOf(Double.parseDouble(s.replace(",", ".").replace(" ", ""))), 0, 6)));

            fields.put("Payment_Date__c", new PDFObjectField<>("paymentDate",
                    new FieldTemplate<>(transactionPattern,
                            s -> LocalDate.parse(s, DateTimeFormatter.ofPattern("yyyy.MM.dd")), 0, 7)));

            fields.put("Comments__c", new PDFObjectField<>("comment", new FieldTemplate<>(transactionPattern, s -> s.replaceAll("[\\r\\n]+",""), Pattern.MULTILINE, 8)));

            fields.put("Bank_Statement__c", new PDFObjectField<>("comment", new FieldTemplate<>(transactionPattern, s ->"a00090000072zcYAAQ", Pattern.MULTILINE, 8)));
        }
    }
}
