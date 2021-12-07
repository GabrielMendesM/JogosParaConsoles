public class App {
    public static void main(String[] args) throws Exception {
        Filosofo[] filosofos = new Filosofo[5];
        Mesa mesa = new Mesa();

        for (int i = 0; i < filosofos.length; i++) {
            filosofos[i] = new Filosofo(mesa, i, filosofos.length);
            filosofos[i].start();
        }
    }
}
