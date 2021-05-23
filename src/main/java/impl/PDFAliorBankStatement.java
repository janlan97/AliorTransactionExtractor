package impl;

import model.Constants;
import model.FieldTemplate;
import model.PDFObject;
import model.PDFObjectField;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.regex.Pattern;

public class PDFAliorBankStatement extends PDFObject {

    public PDFAliorBankStatement(String rawPdfText) {
        super(rawPdfText);
    }

    @Override
    protected void setFields() {
        if (fields == null) {
            fields = new HashMap<>();

            fields.put("Statement_End__c", new PDFObjectField<>("statementEnd",
                    new FieldTemplate<>(Constants.valueOf("statementStartAndEnd"),
                            s -> LocalDate.parse(s, DateTimeFormatter.ofPattern("yyyy.MM.dd")), Pattern.MULTILINE, 2)));

            fields.put("Statement_Start__c", new PDFObjectField<>("statementStart",
                    new FieldTemplate<>(Constants.valueOf("statementStartAndEnd"),
                            s -> LocalDate.parse(s, DateTimeFormatter.ofPattern("yyyy.MM.dd")), Pattern.MULTILINE, 1)));

            fields.put("Generation_Date__c", new PDFObjectField<>("statementGenerationDate",
                    new FieldTemplate<>(Constants.valueOf("statementGenerationDate"),
                            s -> LocalDate.parse(s, DateTimeFormatter.ofPattern("yyyy.MM.dd")), 0, 1)));

            fields.put("Starting_Balance__c", new PDFObjectField<>("startingBalance",
                    new FieldTemplate<>(Constants.valueOf("startingBalance"),
                            s -> BigDecimal.valueOf(Double.parseDouble(s.replace(",", ".").replace(" ", ""))), 0, 1)));

            fields.put("Ending_Balance__c", new PDFObjectField<>("endingBalance",
                    new FieldTemplate<>(Constants.valueOf("endingBalance"),
                            s -> BigDecimal.valueOf(Double.parseDouble(s.replace(",", ".").replace(" ", ""))), 0, 1)));

            fields.put("Average_Balance__c", new PDFObjectField<>("averageBalance",
                    new FieldTemplate<>(Constants.valueOf("averageBalance"),
                            s -> BigDecimal.valueOf(Double.parseDouble(s.replace(",", ".").replace(" ", ""))), 0, 1)));

            fields.put("Granted_Revolving_Limit__c", new PDFObjectField<>("grantedRevolvingLimit",
                    new FieldTemplate<>(Constants.valueOf("grantedRevolvingLimit"),
                            s -> BigDecimal.valueOf(Double.parseDouble(s.replace(",", ".").replace(" ", ""))), 0, 1)));

            fields.put("Blockades__c", new PDFObjectField<>("blockades",
                    new FieldTemplate<>(Constants.valueOf("blockades"),
                            s -> BigDecimal.valueOf(Double.parseDouble(s.replace(",", ".").replace(" ", ""))), 0, 1)));

            fields.put("Income__c", new PDFObjectField<>("income",
                    new FieldTemplate<>(Constants.valueOf("income"),
                            s -> BigDecimal.valueOf(Double.parseDouble(s.replace(",", ".").replace(" ", ""))), 0, 1)));

            fields.put("Outcome__c", new PDFObjectField<>("outcome",
                    new FieldTemplate<>(Constants.valueOf("outcome"),
                            s -> BigDecimal.valueOf(Double.parseDouble(s.replace(",", ".").replace(" ", ""))), 0, 1)));

            fields.put("Unpaid_Interest_On_Debt__c", new PDFObjectField<>("unpaidInterestOnDebt",
                    new FieldTemplate<>(Constants.valueOf("unpaidInterestOnDebt"),
                            s -> BigDecimal.valueOf(Double.parseDouble(s.replace(",", ".").replace(" ", ""))), 0, 1)));

            fields.put("Unpaid_Debt_Service_Costs__c", new PDFObjectField<>("unpaidDebtServiceCosts",
                    new FieldTemplate<>(Constants.valueOf("unpaidDebtServiceCosts"),
                            s -> BigDecimal.valueOf(Double.parseDouble(s.replace(",", ".").replace(" ", ""))), 0, 1)));
        }

    }
}
