import java.util.Arrays;

public abstract class GestoreInserimentoCaratteri {
    
    /**
     * Controlla che il carattere da inserire nell'array
     * non sia già presente.
     * @param carattereIndovinati
     * @param carattereInserito
     * @return
     */
    static boolean controllaSeCarattereUnivoco(char[] carattereIndovinati, char carattereInserito){
        carattereInserito = Character.toUpperCase(carattereInserito);
        for(char carattere : carattereIndovinati){
            if(carattere==carattereInserito){
                return false;
            }
        }
        return true;
    }

    /**
     * Il metodo conta il numero di occorrenze di 
     * un carattere all'interno della frase.
     * @param frase da indovinare
     * @param carattereInserito
     * @return numero di occorrenze
     */
    static int controllaSeCarattereCorretto(String frase, char carattereInserito){
        int contatore = 0;
        carattereInserito = Character.toUpperCase(carattereInserito);
        for (int i = 0; i < frase.length(); i++) {
            if(frase.charAt(i)==carattereInserito)
                contatore++;
        }
        return contatore;
    }

    /**
     * Verifica se la frase da indovinare
     * coincida con quella inserita dall'utente.
     * @param frase da indovinare
     * @param fraseInserita dall'utente
     * @return
     */
    static boolean controllaSeFraseCorretta(String frase, String fraseInserita){
        fraseInserita = pulisci(fraseInserita);
        frase = pulisci(frase);

        if(fraseInserita.equals(frase))
            return true;
        return false;
    }

    static boolean controllaSeTuttiCaratteriIndovinati(String frase, char[] caratteriIndovinati){
        frase = pulisci(frase);

        for (int i = 0; i < frase.length(); i++) {
            char carattere = frase.charAt(i);
            boolean presente = false;

            for(char carattereIndovinato: caratteriIndovinati){
                if(carattere==carattereIndovinato){
                    presente=true;
                    break;
                }
            }

            if(!presente)
                return false;
        }
        return true;
    }

    /**
     * Ripulisce una scringa da caratteri speciali
     * @return della stringa pulita in upperCase
     */
    static String pulisci(String stringa) {
    return stringa
            .toUpperCase()
            .replaceAll("[^A-Z0-9]", "");
}


    /**
     * Verifica se il carattere sia una vocale.
     * @param carattereInserito
     * @return
     */
    static boolean controllaSeVocale(char carattereInserito){
        carattereInserito = Character.toUpperCase(carattereInserito);
        char[] vocali = {'A','E','I','O','U'};
        for(char vocale: vocali){
            if(vocale==carattereInserito){
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica se il carattere sia una consonante.
     * @param carattereInserito
     * @return
     */
    static boolean controllaSeConsonante(char carattereInserito){
        if(!Character.isAlphabetic(carattereInserito))
            return false;
        if(controllaSeVocale(carattereInserito))
            return false;
        return true;
    }

    /**
     * 
     * @param carattereIndovinati
     * @param carattereIndovinato
     * @return
     */
    static char[] aggiungiCarattereIndovinato(char[] carattereIndovinati, char carattereIndovinato){
        carattereIndovinato = Character.toUpperCase(carattereIndovinato);
        char[] nuovoCaratteriIndovinati = Arrays.copyOf(carattereIndovinati, carattereIndovinati.length+1);
        nuovoCaratteriIndovinati[carattereIndovinati.length] = carattereIndovinato;
        return nuovoCaratteriIndovinati;
    } 
}
