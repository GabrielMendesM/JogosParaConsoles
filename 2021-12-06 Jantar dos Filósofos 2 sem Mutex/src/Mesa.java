public class Mesa {
    private static int N_GARFOS = 5;
    private int[] garfos = new int[N_GARFOS];

    public Mesa() {
        for (int i = 0; i < N_GARFOS; i++) {
            garfos[i] = -1;
        }
    }

    public boolean pegouGarfo(int id) {
        if (garfos[id] == -1) {
            garfos[id] = id;
            return true;
        }

        return false;
    }

    public void largarGarfo(int id) {
        garfos[id] = -1;
    }
    
}
