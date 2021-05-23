package model;
import java.util.HashMap;
import java.util.Map;

public class Constants {
    private static final Map<String, String> constants = new HashMap<>();

    static {
        constants.put("transactionData", "^(\\d{4}\\.\\d{2}\\.\\d{2}) ((\\D{0,}\\s{1,})((\\-{0,1}\\d{0,}\\s{0,1}\\d{0,}\\,\\d{0,})\\s(\\d{0,}\\s\\d{0,}\\,\\d{0,}))\\r?)\\n(\\d{4}\\.\\d{2}\\.\\d{2})(\\b.*(?:\\r?\\n(?!\\d{4}\\.\\d{2}\\.\\d{2}\\b).*)*)");
        constants.put("date", "(\\d{4}\\.\\d{2}\\.\\d{2})");
        constants.put("transactionType", "\\s([a-zA-ZĄŻąźżŹĘęÓóŚś\u0141\u0142\\s]{0,})\\s");
        constants.put("amount", "-?\\d{1,}\\s?\\d{0,},\\d{2}");
        constants.put("txt0", "Niniejszy dokument jest wydrukiem komputerowym sporządzonym zgodnie z art. 7 Ustawy Prawo Bankowe (Dz. U. Nr 140 z 1997 roku,\n" +
                "poz.939 z późniejszymi zmianami) i nie wymaga podpisów ani stempli bankowych.");
        constants.put("txt1", "Infolinia Alior Banku dostępna jest w godzinach 8 - 22 pod numerami: 19 502 z Polski (koszt połączenia lokalnego) i telefonów\n" +
                "komórkowych (koszt połączenia według stawek operatora), +48 12 19 502 z zagranicy (koszt połączenia według stawek operatora).\n" +
                "Alior Bank SA, ul. Łopuszańska 38D 02-232 Warszawa Sąd Rejonowy dla m.st. Warszawy w Warszawie, XIII Wydział Gospodarczy KRS:\n0000305178, REGON: 141387142, NIP: 1070010731; Kapitał zakładowy: 1 305 187 160 PLN (opłacony w całości).");
        constants.put("txt2", "Nr wyciągu: \\d{1,2}\\nWyciąg za okres: (\\d{4}\\.\\d{2}\\.\\d{2}) - (\\d{4}\\.\\d{2}\\.\\d{2})\\nData wyciągu: (\\d{4}\\.\\d{2}\\.\\d{2})\\nStrona \\d{1,} z \\d{1,}\\nWyciąg z rachunku bankowego");
        constants.put("statementStartAndEnd", "Wyciąg za okres: (\\d{4}\\.\\d{2}\\.\\d{2}) - (\\d{4}\\.\\d{2}\\.\\d{2})");
        constants.put("statementGenerationDate", "Data wyciągu: (\\d{4}\\.\\d{2}\\.\\d{2})");
        constants.put("startingBalance", "Saldo poczatkowe: (-?\\d{1,}\\s?\\d{0,},\\d{2})");
        constants.put("endingBalance", "Saldo końcowe: (-?\\d{1,}\\s?\\d{0,},\\d{2})");
        constants.put("averageBalance", "Saldo średnie: (-?\\d{1,}\\s?\\d{0,},\\d{2})");
        constants.put("grantedRevolvingLimit", "Przyznany limit odnawialny w rachunku: (-?\\d{1,}\\s?\\d{0,},\\d{2})");
        constants.put("blockades", "Kwota blokad: (-?\\d{1,}\\s?\\d{0,},\\d{2})");
        constants.put("income", "Uznania ogółem: (-?\\d{1,}\\s?\\d{0,},\\d{2})");
        constants.put("outcome", "Obciążenia ogółem: (-?\\d{1,}\\s?\\d{0,},\\d{2})");
        constants.put("unpaidInterestOnDebt", "Niespłacone odsetki od zadłużenia: (-?\\d{1,}\\s?\\d{0,},\\d{2})");
        constants.put("unpaidDebtServiceCosts", "Niespłacone koszty obsługi zadłużenia: (-?\\d{1,}\\s?\\d{0,},\\d{2})");
        constants.put("malformedSpace", "\\u00A");
    }

    public static String valueOf(String key) {
        return constants.get(key);
    }

    public Map.Entry<String,String> entry(String key) {
        return constants.entrySet().stream().filter(e -> e.getKey().equals(key)).findAny().get();
    }
}