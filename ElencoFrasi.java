import java.util.Random;


class ElencoFrasi {
    Random random = new Random();
    Frase[][] frasi;

    ElencoFrasi() {
        popolaFrasi();
    }

    void popolaFrasi() {
        frasi = new Frase[DifficoltaFrase.values().length][];

        for (DifficoltaFrase difficolta : DifficoltaFrase.values()) {
            String[] lineeFile = FileUtilities.leggiLineeFile(
                    "frasi_" + difficolta
                    .toString()
                    .toLowerCase() + ".csv");

            Frase[] frasiAttuali = new Frase[lineeFile.length];

            for (int i = 0; i < lineeFile.length; i++) {
                String[] tokens = lineeFile[i].split(",");
                if (tokens.length != 4)
                    continue;

                frasiAttuali[i] = new Frase(DifficoltaFrase.valueOf(tokens[0]), CategoriaFrase.valueOf(tokens[1]),
                        tokens[2], tokens[3].toUpperCase());
            }
            frasi[difficolta.ordinal()] = frasiAttuali;
        }
    }

    Frase scegliFraseCasuale(DifficoltaFrase difficolta) {
        // FIXME Da completare
        // Selezionare casualmente una frase della difficolta' desiderata
        return null;
    }

    Frase scegliFraseCasuale() {
        // FIXME Da completare
        // Selezionare casualmente una difficolta' tra quelle disponibili
        // Selezionare casualmente una frase della difficolta' selezionata
        return null;
    }
}
