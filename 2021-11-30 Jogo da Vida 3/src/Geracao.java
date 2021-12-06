import javax.swing.JLabel;

public abstract class Geracao {
    public static int geracao = 0;
    public static JLabel lblGeracao = new JLabel("Geração: " + geracao);

    public static void adicionar() {
        geracao++;
        lblGeracao.setText("Geração: " + geracao);
    }
}
