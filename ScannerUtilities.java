import java.util.Scanner;

public abstract class ScannerUtilities{
    /**
     * 
     * @param scanner
     * @param messsaggio
     * @return
     */
    public static String getInputString(Scanner scanner, String messsaggio){
        String risultato = "";
        
        while (risultato.length()==0) {
            
            System.out.print(messsaggio);

            if(!scanner.hasNext()){
                System.out.println("Errore: inserisci qualcosa");
                scanner.nextLine();
                continue;
            }

            risultato = scanner.next();
        }

        return risultato;
    }

    public static String getInputString(Scanner scanner, String messsaggio, boolean frase){
        String risultato = "";
        
        while (risultato.length()==0) {
            
            System.out.print(messsaggio);

            if(!scanner.hasNext()){
                System.out.println("Errore: inserisci qualcosa");
                scanner.nextLine();
                continue;
            }

            if(frase){
                risultato = scanner.nextLine();
                continue;
            }

            risultato = scanner.next();
        }

        return risultato;
    }

    /***
     * Metodo richiedere un valore intero all'utente.
     * @param scanner per raccogliere l'input dall'utente
     * @param messaggio da stampare a schermo durante la richiesta
     * @param min valore minimo incluso
     * @param max valore massimo incluso
     * @return valore inserito.
     */
    static int getInputInt(Scanner scanner,String messsaggio, int min, int max){
        int risultato = min - 1;
        while (risultato<min) {
            
            System.out.print(messsaggio);

            // Controlla che sia un valore numerico intero
            if (!scanner.hasNextInt()) {
                System.out.println("Errore: non è un valore numerico intero");
                scanner.nextLine();
                continue;
            }

            int input = scanner.nextInt();

            // Controlla che non sia un valore troppo basso
            if (input<min) {
                System.out.println("Errore: valore troppo basso");
                scanner.nextLine();
                continue;
            }

            // Controlla che non sia un valore troppo alto
            if (input>max) {
                System.out.println("Errore: valore troppo alto");
                scanner.nextLine();
                continue;
            }

            risultato = input;
        }
        return risultato;
    }

    static boolean getInputYesOrNot(Scanner scanner, String messagio){
        while (true) {
            System.out.print(messagio);

            // Input vuoto
            if(!scanner.hasNext()){
                System.out.println("Errore: inserisci qualcosa");
                scanner.nextLine();
                continue;
            }

            //
            String input = scanner.next();
            scanner.nextLine();
            input = input.toUpperCase();
            input = Character.toString(input.charAt(0));

            if(input.equals("S")){
                return true;
            }

            if(input.equals("N")){
                return false;
            }

            System.out.println("Errore: inserire S o N");
        }
    }
}