import java.util.concurrent.Semaphore;

public class Mesa {
    private static final int INTERVALO_PEGAR_GARFOS = 2;
    private static final int N_GARFOS = 5;
    private Semaphore[] garfos = new Semaphore[N_GARFOS];

    public Mesa() {
        for (int i = 0; i < N_GARFOS; i++) {
            garfos[i] = new Semaphore(1);
        }
    }

    public void pegarGarfos(int direita, int esquerda) {
        while(true) {
            if (garfos[direita].tryAcquire()) {
                if (garfos[esquerda].tryAcquire()) {
                    return;
                } else {
                    garfos[direita].release();
                }
            }
            try {
                Thread.sleep((int)(Math.random() * 1000 * INTERVALO_PEGAR_GARFOS));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void largarGarfos(int direita, int esquerda) {
        garfos[esquerda].release();
        garfos[direita].release();
    }
    
}
