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
        int indice = random.nextInt(0,this.frasi[difficolta.ordinal()].length);
        return this.frasi[difficolta.ordinal()][indice];
    }

    Frase scegliFraseCasuale() {
        int indice1 = random.nextInt(0,this.frasi.length);
        int indice2 = random.nextInt(0,this.frasi[indice1].length);
        return this.frasi[indice1][indice2];
    }
}
