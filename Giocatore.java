public class Giocatore {
    String nome;
    int punteggio;
    
    /**
     * Costruttore
     * @param nome
     */
    public Giocatore(String nome){
        this.nome = nome;
    }


    /**
     * Metodo che azzera il punteggio all utente nel caso
     * estragga "bancarotta".
     */
    void bancarotta(){
        this.punteggio = 0;
        System.out.println("Bancarotta!! " + this.nome + "perde tutto il patrimonio e il turno.");
    }

    /**
     * Metodo per aggiungere punteggio al giocatore alla vincita.
     * @param punti da aggiungere al punteggio
     */
    void vinci(int punti){     
        this.punteggio += punti;
    }

    /**
     * Sottrae al punteggio il costo della vocale
     */
    void compraVocale(){
        if(this.punteggio>=250)
            this.punteggio-= 250;
    }

    /**
     * Stampa a schermo le informazioni dei giocatori
     */
    void stampa(){
        System.out.printf("Giocatore: %s, punteggio: %d\n",this.nome,this.punteggio);
    }
}