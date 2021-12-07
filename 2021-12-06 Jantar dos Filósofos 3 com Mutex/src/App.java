public class App {
    private static final int TEMPO_EXECUCAO = 5;

    public static void main(String[] args) throws Exception {
        Filosofo[] filosofos = new Filosofo[5];
        Mesa mesa = new Mesa();

        for (int i = 0; i < filosofos.length; i++) {
            filosofos[i] = new Filosofo(mesa, i, filosofos.length);
            filosofos[i].start();
        }

        try {
            Thread.sleep(TEMPO_EXECUCAO * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < filosofos.length; i++) {
            filosofos[i].interrupt();
            filosofos[i].setIsRodando(false);
        }

        for (int i = 0; i < filosofos.length; i++) {
            System.out.println(filosofos[i].getNome() + " comeu " + filosofos[i].getNComeuCont() + " vezes.");
        }
    }
}
