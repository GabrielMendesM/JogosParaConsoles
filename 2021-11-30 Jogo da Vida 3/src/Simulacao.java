public class Simulacao extends Thread {
    private static final int INTERVALO_THREAD = 2000;

    private Tabuleiro tabuleiro;
    private volatile boolean rodando = false;
    private int nThreads;
    private CelulaProcessor[] processors;

    public Simulacao(Tabuleiro tabuleiro, int nThreads) {
        this.tabuleiro = tabuleiro;
        this.nThreads = nThreads;
        this.processors = new CelulaProcessor[nThreads];
    }

    public void comecar() {
        rodando = true;
        start();
    }
    
    public void parar() {
        rodando = false;
    }

    public void startThreads() {
        int qtd = Tabuleiro.DIMENSAO / nThreads;

        for (int i = 0; i < nThreads; i++) {
            int inicio = i * qtd;

            processors[i] = new CelulaProcessor(tabuleiro, inicio, qtd);
            processors[i].start();
        }
    }

    @Override
    public void run() {
        super.run();

        while (rodando) {
            startThreads();

            try {
                for (CelulaProcessor c : processors) {
                    c.join();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
            for (CelulaProcessor c : processors) {
                c.atualizarTabuleiro();
            }

            tabuleiro.atualizarPanel();
            Geracao.adicionar();

            try {
                Thread.sleep(INTERVALO_THREAD);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }
    
}
