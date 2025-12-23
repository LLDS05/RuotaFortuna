import java.util.Scanner;

public abstract class GestoreTurno {

    /**
     * Estrae uno spicchio e gestisce i vari esiti.
     * Assegna il valore dello spicchio estratto alla
     * variabile passata come parametro.
     * @param ruota
     * @param giocatore
     * @param spicchioEstratto
     * @return
     */
    static boolean estrazione(Ruota ruota, Giocatore giocatore, SpicchioRuota spicchioEstratto){
        // Perdi turno
        if(spicchioEstratto.tipo.equals(TipoSpicchioRuota.PERDI_TURNO)){
            System.out.println("Spicchio: perdi il turno");
            return false;
        }

        // Bancarotta
        if(spicchioEstratto.tipo.equals(TipoSpicchioRuota.BANCAROTTA)){
            giocatore.bancarotta();
            System.out.println("Spicchio: Bancarotta");
            return false;
        }

        // Vinci punti
        System.out.printf("\nSpicchio: %d punti", spicchioEstratto.valore);
        return true;
    }
    
    /**
     * Gestisce la fase di richiesta della consonante durante 
     * il turno del giocatore.
     * @param ruotaDellaFortuna
     * @param scanner
     * @param giocatore
     * @param spicchioEstratto
     * @return
     */
    static boolean consonante(RuotaDellaFortuna ruotaDellaFortuna, Scanner scanner, Giocatore giocatore, SpicchioRuota spicchioEstratto){
        // Chiedo di inserire una consonante.
        String messaggio = "\nConsonante: ";
        char consonante = ScannerUtilities.getInputString(scanner, messaggio).charAt(0);
        while (!GestoreInserimentoCaratteri.controllaSeConsonante(consonante)) {
            System.out.println("La lettera inserita non è una consonante. Riprova.");
            consonante = ScannerUtilities.getInputString(scanner, messaggio).charAt(0);
        } 

        // Controllo se la consonante è corretta.
        if(!ruotaDellaFortuna.inserimentoCorretto(giocatore, spicchioEstratto.valore, consonante)){
            // Se non lo è ritorno false (perde il tuno).
            return false;
        }

        // Altrimenti mantiene il turno. 
        return true;
    }

    /**
     * Gestisce la fase di richiesta della vocale durante 
     * il turno del giocatore.
     * @param ruotaDellaFortuna
     * @param scanner
     * @param giocatore
     * @param spicchioEstratto
     * @return
     */
    static boolean vocale(RuotaDellaFortuna ruotaDellaFortuna, Scanner scanner, Giocatore giocatore, SpicchioRuota spicchioEstratto){
        // Se il giocatore non ha sufficienti crediti esce.
        if(giocatore.punteggio<250)
            return true;

        // Chiedo al giocatore se intende acquistare la richiesta di una vocale.
        String messaggio = "\nVuoi comprare una vocale (s/n): ";
        boolean acquistoVocale = ScannerUtilities.getInputYesOrNot(scanner, messaggio);

        // Nel caso rifiuti esco.
        if(!acquistoVocale)
            // Il giocatore mantiene il turno
            return true;

        // Altrimenti procedo a chiedere la vocale
        giocatore.compraVocale(); // Sottrae al punteggio il costo della vocale
        messaggio = "Vocale: ";

        // Continua a chiedere di inserire il carattere finche è una vocale.
        char vocale = ScannerUtilities.getInputString(scanner, messaggio).charAt(0);
        while(!GestoreInserimentoCaratteri.controllaSeVocale(vocale)){
            System.out.println("La lettera inserita non è una vocale. Riprova.");
            vocale = ScannerUtilities.getInputString(scanner, messaggio).charAt(0);
        }

        // Controllo se la vocale è corretta.
        if(!ruotaDellaFortuna.inserimentoCorretto(giocatore, spicchioEstratto.valore, vocale))
            return false;
    
        // Altrimenti mantiene il turno.
        return true;
    }

    /**
     * Gestisce la fase di richiesta della frase durante 
     * il turno del giocatore.
     * @param ruotaDellaFortuna
     * @param scanner
     * @return
     */
    static boolean frase(RuotaDellaFortuna ruotaDellaFortuna, Scanner scanner){
        // Chiede al giocatore se vuole indovinare la frase
        String messaggio = "\nVuoi indovinare la frase (s/n): ";
        boolean indovinaFrase = ScannerUtilities.getInputYesOrNot(scanner, messaggio);

        // Il giocatore non vuole indovinare la frase (mantiene il turno)
        if(!indovinaFrase)
            return true;

        messaggio = "\nFrase: ";
        String frase = ScannerUtilities.getInputString(scanner, messaggio, true);
        
        boolean fraseCorretta = GestoreInserimentoCaratteri.controllaSeFraseCorretta(ruotaDellaFortuna.fraseEstratta.frase, frase);
        
        if(fraseCorretta){
            ruotaDellaFortuna.mancheAttiva = false;
            return true;
        }

        System.out.println("Frase sbagliata");
        return false;
    }
}
