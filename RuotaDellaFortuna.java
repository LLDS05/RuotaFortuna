import java.util.Scanner;

public class RuotaDellaFortuna {
    boolean mancheAttiva;
    

    int nGiocatori;
    static int nGiocatoriMin = 2;
    static int nGiocatoriMax = 4;
    Giocatore[] giocatori;

    char[] caratteriIndovinati;
    Frase fraseEstratta;
    ElencoFrasi elencoFrasi;

    Ruota ruota;

    /**
     * Costruttore
     * @param scanner
     */
    RuotaDellaFortuna(Scanner scanner){
        // Creazione dei giocatori.
        String messaggio = "Inserire il numero di giocatori: ";
        this.nGiocatori = ScannerUtilities.getInputInt(scanner, messaggio, nGiocatoriMin, nGiocatoriMax);
        this.giocatori = GestoreGiocatori.generaGiocatori(scanner, nGiocatori);

        // Popolamento dell'elenco frasi.
        this.elencoFrasi = new ElencoFrasi();
        this.elencoFrasi.popolaFrasi();

        this.ruota = new Ruota();

        // Avvio della prima manche.
        this.manche(scanner);
    }

    /**
     * Nella manche si gestisce l'indovinamento della frase.
     * @param scanner
     */
    void manche(Scanner scanner){
        // Resetta le variabili per la nuova manche.
        this.caratteriIndovinati = new char[0];
        this.mancheAttiva = true;
        int indiceGiocatori=0;

        // Crea la frase con la difficoltà scelta o causale 
        // a dipendenza di cosa sceglie l'utente.
        this.chiediDifficolta(scanner);
        this.fraseEstratta.mostraCensurata(this.caratteriIndovinati);

        // Avvio vero e proprio della manche.
        while (this.mancheAttiva) {
            if(GestoreInserimentoCaratteri.controllaSeTuttiCaratteriIndovinati(this.fraseEstratta.frase, caratteriIndovinati)){
                this.mancheAttiva = false;
                continue;
            }

            Giocatore giocatore = this.giocatori[indiceGiocatori];
            System.out.printf("\n*** Turno di %s\n",giocatore.nome);
            boolean tieneTurno = turno(scanner, giocatore);

            // Assegna il nuovo turno se un giocatore lo perde.
            if(!tieneTurno && mancheAttiva){
                System.out.printf("\n%s perde il turno.", giocatore.nome);

                // Passa il turno al giocatore successivo.
                indiceGiocatori += 1;
                indiceGiocatori = indiceGiocatori % this.giocatori.length;
            }
        }

        this.mostraFraseIndovinata();

        // Chiede se si vuole avviare una nuova manche.
        String messaggio = "Nuova frase? (s/n): ";
        boolean nuovaPartita = ScannerUtilities.getInputYesOrNot(scanner, messaggio);
        if(nuovaPartita)
            manche(scanner);

        // Altrimenti mostra la lista dei vincitori.
        this.mostraVincitori();
    }

    /**
     * 
     * @param scanner
     * @param giocatore
     */
    boolean turno(Scanner scanner, Giocatore giocatore){
        // ==========
        // ESTRAZIONE
        // ==========

        ruota.mischiaSpicchi();
        SpicchioRuota spicchioEstratto = ruota.gira();

        if(!GestoreTurno.estrazione(ruota, giocatore, spicchioEstratto))
            return false;

        // ==========
        // CONSONANTE
        // ==========

        if(!GestoreTurno.consonante(this, scanner, giocatore, spicchioEstratto))
            return false;

        // Controllo se la frase è stata indovinata.
        if(GestoreInserimentoCaratteri.controllaSeTuttiCaratteriIndovinati(this.fraseEstratta.frase, caratteriIndovinati)){
            this.mancheAttiva = false;
            return false;
        }

        // ======
        // VOCALE
        // ======

        if(!GestoreTurno.vocale(this, scanner, giocatore, spicchioEstratto))
            return false;

        // Controllo se la frase è stata indovinata.
        if(GestoreInserimentoCaratteri.controllaSeTuttiCaratteriIndovinati(this.fraseEstratta.frase, caratteriIndovinati)){
            this.mancheAttiva = false;
            return false;
        }

        // =====
        // FRASE
        // =====

        if(!GestoreTurno.frase(this, scanner))
            return false;

        return true;
    }


    /**
     * Chiede all'utense se vuole scegliere la difficolta
     * e nel chiede di selezionare tra:
     * - FACILE
     * - MEDIO
     * - DIFFICILE
     * 
     * @param scanner
     */
    void chiediDifficolta(Scanner scanner){
        String messaggio = "\n Vuoi scegliere la difficoltà della frase? (s/n): ";
        boolean scegliDifficolta = ScannerUtilities.getInputYesOrNot(scanner, messaggio);
        
        if(!scegliDifficolta){
            this.fraseEstratta = this.elencoFrasi.scegliFraseCasuale();
            return;
        }

        int min = 0;
        int max = 2;
        messaggio = "Scelta: ";
        System.out.println("""
        0: Facile
        1: Medio
        2: Difficile""");
        int difficolta = ScannerUtilities.getInputInt(scanner, messaggio, min, max);
        this.fraseEstratta = this.elencoFrasi.scegliFraseCasuale(DifficoltaFrase.values()[difficolta]);
    }

    /**
     * Controlla che il carattere inserito sia presente nella frase.
     * Ritorna true se è presente e false nel caso non lo sia o sia già presente.
     * Nel caso sia corretto si occupa anche di richiamare il metodo inserimento().
     * @param giocatore
     * @param valore
     * @param carattere
     * @return
     */
    boolean inserimentoCorretto(Giocatore giocatore, int valore, char carattere){
        int occorrenze = GestoreInserimentoCaratteri.controllaSeCarattereCorretto(this.fraseEstratta.frase, carattere);

        // Carttere non presente nella frase.
        if(occorrenze==0){
            System.out.println("Lettera non presente.");
            return false;
        }

        // Carttere già scelto.
        if(!GestoreInserimentoCaratteri.controllaSeCarattereUnivoco(this.caratteriIndovinati, carattere)){
            System.out.println("Lettera già scelta.");
            return false;
        }

        // Carttere indovinato.
        this.inserimento(giocatore, valore, occorrenze, carattere);
        return true;
    }

    /**
     * Assegna i punti al giocatore e 
     * mostra la frase censurata, eccetto i caratteri indovinati. 
     * @param giocatore a cui assegnare i punti
     * @param punti che verranno assegnati al giocatore moltiplicati per le occorrenze
     * @param occorrenze moltiplicatore per il punteggio
     * @param carattere indovinato da inserire 
     */
    void inserimento(Giocatore giocatore, int punti, int occorrenze, char carattere){
        int vincita = punti*occorrenze;
        carattere = Character.toUpperCase(carattere);
        giocatore.vinci(vincita);
        carattere = Character.toLowerCase(carattere);
        System.out.printf("\n%c: %d occorrenze",carattere,occorrenze);
        System.out.printf("\n%s vince %d punti.",giocatore.nome,vincita);
        System.out.println();
        
        this.caratteriIndovinati = GestoreInserimentoCaratteri.aggiungiCarattereIndovinato(this.caratteriIndovinati, carattere);
        this.fraseEstratta.mostraCensurata(this.caratteriIndovinati);
    }

    /**
     * Mostra la frase corretta.
     */
    void mostraFraseIndovinata(){
        System.out.println();
        System.out.println("Complimenti, la frase è quella giusta!!");
        System.out.println();
        this.fraseEstratta.mostra();
    }

    /**
     * Stampa a schermo i vincitori.
     */
    void mostraVincitori(){
        Giocatore[] vincitori = GestoreGiocatori.ottieniVincitori(this.giocatori);
        System.out.println("\nVincitore/i:");
        for(Giocatore vincitore: vincitori)
            vincitore.stampa();
    }
}
