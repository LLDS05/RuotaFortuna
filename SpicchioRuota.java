enum TipoSpicchioRuota {
    PUNTI, BANCAROTTA, PERDI_TURNO
}

class SpicchioRuota {
    String descrizione;
    TipoSpicchioRuota tipo;
    int valore;

    SpicchioRuota(String descrizione, TipoSpicchioRuota tipo, int valore) {
        this.descrizione = descrizione;
        this.tipo = tipo;
        this.valore = valore;
    }

    void mostra() {
        System.out.println(descrizione);
    }
}