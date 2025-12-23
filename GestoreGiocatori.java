import java.util.Arrays;
import java.util.Scanner;

public abstract class GestoreGiocatori {
    /**
     * 
     * @param scanner
     * @param numero
     * @return
     */
    static Giocatore[] generaGiocatori(Scanner scanner, int numero){
        Giocatore[] giocatori = new Giocatore[0];

        for (int i = 0; i < numero; i++) {
            String messaggio ="Inserisci il nome del giocatore numero "+(i+1)+": ";
            String nome = ScannerUtilities.getInputString(scanner, messaggio);
            
            while(!controllaSeGiocatoreUnivoco(giocatori,nome)){
                nome = ScannerUtilities.getInputString(scanner, messaggio);
            }

            Giocatore giocatore = new Giocatore(nome);
            giocatori = aggiungiGiocatore(giocatori, giocatore);
        }
        return giocatori;
    }

    /**
     * Controlla se il nome che si inserisce sia
     * presente o meno nell'array di Giocatori.
     * @param giocatori
     * @param nome
     * @return
     */
    static boolean controllaSeGiocatoreUnivoco(Giocatore[] giocatori, String nome){
        for(Giocatore giocatore : giocatori){
            if(giocatore == null)
                continue;

            if(giocatore.nome.equals(nome))
                return false;
        }
        return true;
    }

    /**
     * Restituisce la lista di vincitori di una partita.
     * (Giocatori con il punteggio più alto)
     * @param giocatori
     * @return
     */
    static Giocatore[] ottieniVincitori(Giocatore[] giocatori){
        int punteggioMassimo = ottiniPunteggioMassimo(giocatori);

        Giocatore[] vincitori = new Giocatore[0];

        for (Giocatore giocatore: giocatori)
            if(giocatore.punteggio == punteggioMassimo)
                vincitori = aggiungiGiocatore(vincitori, giocatore);

        return vincitori;
    }

    /**
     * Restituisce il punteggio più alto tra
     * i punteggi dei vari giocatori nell'array.
     * @param giocatori
     * @return
     */
    static int ottiniPunteggioMassimo(Giocatore[] giocatori){
        int punteggioMassimo = 0;

        for(Giocatore giocatore:giocatori)
            if(punteggioMassimo<giocatore.punteggio)
                punteggioMassimo=giocatore.punteggio;

        return punteggioMassimo;
    }

    /**
     * Aggiunge un giocatore ad un array di giocatori
     * @param giocatori dove viene aggiunto il giocatore
     * @param giocatore giocatore da aggiungere
     * @return
     */
    static Giocatore[] aggiungiGiocatore(Giocatore[] giocatori, Giocatore giocatore){
        int indice = giocatori.length;
        int lunghezza = giocatori.length + 1;
        Giocatore[] nuovoGiocatori = Arrays.copyOf(giocatori, lunghezza);
        nuovoGiocatori[indice] = giocatore;
        return nuovoGiocatori;
    }


}