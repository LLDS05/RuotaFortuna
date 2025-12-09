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
}