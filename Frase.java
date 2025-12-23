enum DifficoltaFrase {
    FACILE, MEDIO, DIFFICILE
}

enum CategoriaFrase {
    NATURA, LUOGHI, AZIONI, EMOZIONI, VITA_QUOTIDIANA
}

class Frase {
    DifficoltaFrase difficolta;
    CategoriaFrase categoria;
    String indizio;
    String frase;

    Frase(DifficoltaFrase difficolta, CategoriaFrase categoria, String indizio, String frase) {
        this.difficolta = difficolta;
        this.categoria = categoria;
        this.indizio = indizio;
        this.frase = frase;
    }

    void mostra() {
        System.out.println("Difficoltà: " + difficolta);
        System.out.println("Categoria: " + categoria);
        System.out.println("Indizio: " + indizio);
        System.out.println("Frase: " + frase);
    }

    void mostraCensurata(char[] caratteriIndovinati){
        System.out.println("=============================================================================================");
        System.out.println();
        System.out.printf("Categoria: %s --- Indizio: %s",this.categoria.toString(), this.indizio);
        System.out.println();
        System.out.println(this.getCensurata(caratteriIndovinati));
        System.out.println();
        System.out.println("=============================================================================================");
    }

    String getCensurata(char[] caratteriIndovinati){
        String censurata = "";
        for (int i = 0; i < this.frase.length(); i++) {
            char carattere = this.frase.charAt(i);

            if(!Character.isAlphabetic(carattere)){
                censurata += carattere;
                continue;
            }

            boolean continua = true;

            for(char caratteriIndovinato : caratteriIndovinati){
                if(carattere == caratteriIndovinato){
                    censurata += carattere;
                    continua = false;
                    break;
                }
            }

            if(continua)
                censurata += "-";
        }
        return censurata;
    }
}