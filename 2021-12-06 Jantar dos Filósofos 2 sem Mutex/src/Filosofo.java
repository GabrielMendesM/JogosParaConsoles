import java.util.Random;

public class Filosofo extends Thread {
    private Mesa mesa;
    private Random random = new Random();
    private static final int TEMPO_PENSANDO = 2;
    private static final int TEMPO_ESPERANDO = 1;
    private static final int TEMPO_COMENDO = 10;
    private int indice = 0;
    private String nome;
    private int garfoEsq, garfoDir;

    public Filosofo(Mesa mesa, int indice, int nFilosofos) {
        this.mesa = mesa;
        this.indice = indice;
        
        switch (indice) {
            case 0:
                nome = "Marx";
                break;
            case 1:
                nome = "Engels";
                break;
            case 2:
                nome = "Lenin";
                break;
            case 3:
                nome = "Trotsky";
                break;
            case 4:
                nome = "Rui";
                break;
        }

        this.garfoEsq = (indice + 1) % nFilosofos;
        this.garfoDir = indice == 0 ? nFilosofos - 1 : indice--;
    }

    @Override
    public void run() {
        while(true) {
            pensar();
            pegarGarfo(garfoDir);
            pegarGarfo(garfoEsq);
            comer();
            largarGarfo(garfoDir);
            largarGarfo(garfoEsq);
        }
    }

    private void pensar() {
        System.out.println(nome + " está pensando.");

        //System.out.println("Filósofo " + indice + " está pensando.");
        try {
            Thread.sleep((int)(random.nextFloat() * 1000f * TEMPO_PENSANDO));
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    private void pegarGarfo(int garfo) {
        while(!mesa.getGarfo(garfo)) {
            System.out.println(nome + " está esperando um garfo.");

            //System.out.println("Filósofo " + indice + " está esperando um garfo.");
            
            try {
                Thread.sleep((int)(random.nextFloat() * 1000f * TEMPO_ESPERANDO));
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }

    private void comer() {
        System.out.println(nome + " está comendo.");

        //System.out.println("Filósofo " + indice + " está comendo.");

        try {
            Thread.sleep((int)(random.nextFloat() * 1000f * TEMPO_COMENDO));
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    private void largarGarfo(int garfo) {
        mesa.largarGarfo(garfo);
    }    
}
