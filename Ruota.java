import java.util.Random;


class Ruota {
    Random random = new Random();
    SpicchioRuota[] spicchi;
    int indiceSpicchioAttuale;

    Ruota() {
        popolaSpicchi();
    }
    
    
    /**
    *
    */
    void popolaSpicchi() {
        String[] lineeFile = FileUtilities.leggiLineeFile("spicchi_ruota.csv");
        spicchi = new SpicchioRuota[lineeFile.length];

        for (int i = 0; i < spicchi.length; i++) {
            String[] tokens = lineeFile[i].split(",");
            if (tokens.length != 3)
                continue;
            spicchi[i] = new SpicchioRuota(tokens[0], TipoSpicchioRuota.valueOf(tokens[1]),
                    Integer.parseInt(tokens[2]));
        }

        mischiaSpicchi();
    }

    void mischiaSpicchi() {
        // Fisher-Yates shuffle
        for (int i = spicchi.length - 1; i > 0; i--) {
            int indice = random.nextInt(i + 1);
            SpicchioRuota tmp = spicchi[indice];
            spicchi[indice] = spicchi[i];
            spicchi[i] = tmp;
        }
    }

    SpicchioRuota gira() {
        indiceSpicchioAttuale = (indiceSpicchioAttuale + random.nextInt(1, 16)) % spicchi.length;
        return spicchi[indiceSpicchioAttuale];
    }
}