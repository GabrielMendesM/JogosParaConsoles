import java.util.Random;

public class Filosofo extends Thread {
    private Mesa mesa;
    private Random random = new Random();
    private boolean rodando = false;
    private int nComeuCont = 0;
    private static final int TEMPO_PENSANDO = 2;
    private static final int TEMPO_COMENDO = 10;
    private String nome;
    private int garfoEsq, garfoDir;

    public Filosofo(Mesa mesa, int indice, int nFilosofos) {
        this.mesa = mesa;
        
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

        garfoEsq = (indice + 1) % nFilosofos;
        garfoDir = indice == 0 ? nFilosofos - 1 : indice--;

        rodando = true;
    }

    @Override
    public void run() {
        while(rodando) {
            pensar();
            pegarGarfos();
            comer();
            largarGarfos();
        }
    }

    private void pensar() {
        System.out.println(nome + " está pensando.");

        try {
            Thread.sleep((int)(random.nextFloat() * 1000f * TEMPO_PENSANDO));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void pegarGarfos() {
        System.out.println(nome + " está esperando um garfo.");

        mesa.pegarGarfos(garfoDir, garfoEsq);
    }

    private void comer() {
        nComeuCont++;
        System.out.println(nome + " está comendo.");

        try {
            Thread.sleep((int)(random.nextFloat() * 1000f * TEMPO_COMENDO));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void largarGarfos() {
        mesa.largarGarfos(garfoDir, garfoEsq);
    }

    public void setIsRodando(boolean rodando) {
        this.rodando = rodando;
    }

    public int getNComeuCont() {
        return nComeuCont;
    } 

    public String getNome() {
        return nome;
    }
}
