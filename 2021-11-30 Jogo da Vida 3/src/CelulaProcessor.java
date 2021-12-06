public class CelulaProcessor extends Thread {
    private Tabuleiro tabuleiro;

    private int inicioY;
    private int qtd;
    private int[] celulasLocal;

    public CelulaProcessor(Tabuleiro tabuleiro, int inicioY, int qtd) {
        this.tabuleiro = tabuleiro;
        this.inicioY = inicioY;
        this.qtd = qtd;

        celulasLocal = new int[Tabuleiro.DIMENSAO * qtd];
    }

    private int getCelulaLocal(int x, int y) {
        return celulasLocal[y * Tabuleiro.DIMENSAO + x];
    }

    private void setCelulaLocal(int x, int y, int estado) {
        celulasLocal[y * Tabuleiro.DIMENSAO + x] = estado;
    }

    public void atualizarTabuleiro() {
        for (int y = 0; y < qtd; y++) {
            for (int x = 0; x < Tabuleiro.DIMENSAO; x++) {
                tabuleiro.setCelula(x, inicioY + y, getCelulaLocal(x, y));
            }
        }
        tabuleiro.atualizarPanel();
    }
    
    @Override
    public void run() {
        super.run();

        for (int y = 0; y < qtd; y++) {
            for (int x = 0; x < Tabuleiro.DIMENSAO; x++) {
                int contVizinhas = 0;
                int celulaEstado = tabuleiro.getCelula(x, inicioY + y);
                setCelulaLocal(x, y, celulaEstado);

                for (int vy = -1; vy < 2; vy++) {
                    for (int vx = -1; vx < 2; vx++) {
                        int idx = x + vx;
                        int idy = y + vy;

                        if (idx >= 0 &&
                            idy >= 0 &&
                            (idx != x || idy != y) &&
                            idx < Tabuleiro.DIMENSAO &&
                            idy < qtd) {
                                if (tabuleiro.getCelula(idx, inicioY + idy) == Tabuleiro.VIVA) {
                                    contVizinhas++;
                                }
                            }
                    }
                }

                if (celulaEstado == Tabuleiro.VIVA && contVizinhas <= 1) setCelulaLocal(x, y, Tabuleiro.MORTA);
                else if (celulaEstado == Tabuleiro.VIVA && contVizinhas >= 4) setCelulaLocal(x, y, Tabuleiro.MORTA);
                else if (celulaEstado == Tabuleiro.MORTA && contVizinhas == 3) setCelulaLocal(x, y, Tabuleiro.VIVA);
            }
        }

        atualizarTabuleiro();
    }
}
