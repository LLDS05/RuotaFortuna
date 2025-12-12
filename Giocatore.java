public class Giocatore {
    String nome;
    int punteggio;
    

    Giocatore(String nome){
        this.nome = nome;
    }


    void bancarotta(){
        punteggio = 0;
        System.out.println("Bancarotta!! " + nome + "perde tutto il patrimonio e il turno.");
    }

    void vinci(int punti){     
        

    }
}

