import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class App {
    public static void main(String[] args) throws Exception {
        Tabuleiro tabuleiro = new Tabuleiro();
        Simulacao simulacao = new Simulacao(tabuleiro, 3);

        JFrame frame = new JFrame("Jogo da Vida");
        frame.setLayout(new BorderLayout());
        frame.setPreferredSize(new Dimension(700, 700));
        frame.add(tabuleiro, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

        JPanel pnlGUI = new JPanel();
        pnlGUI.setLayout(new FlowLayout(FlowLayout.LEFT));
        Button btnComecar = new Button("Começar");
        Button btnParar = new Button("Parar");
        Button btnPreencher = new Button("Preencher");
        Button btnPreencherBorda = new Button("Preencher Borda");
        Button btnPreencherSBorda = new Button("Preencher sem Borda");
        Button btnPadraoX = new Button("Padrão X");
        Button btnLimpar = new Button("Limpar");
        pnlGUI.add(btnComecar);
        pnlGUI.add(btnParar);
        pnlGUI.add(btnPreencher);
        pnlGUI.add(btnPreencherBorda);
        pnlGUI.add(btnPreencherSBorda);
        pnlGUI.add(btnPadraoX);
        pnlGUI.add(btnLimpar);
        pnlGUI.add(Geracao.lblGeracao);
        frame.add(pnlGUI, BorderLayout.SOUTH);
        frame.pack();

        btnComecar.addActionListener(e -> {
            pnlGUI.remove(btnComecar);
            simulacao.comecar();
        });

        btnParar.addActionListener(e -> simulacao.parar());

        btnPreencher.addActionListener(e -> tabuleiro.preencher());

        btnPreencherBorda.addActionListener(e -> tabuleiro.preencherBorda());

        btnPreencherSBorda.addActionListener(e -> tabuleiro.preencherSemBorda());

        btnPadraoX.addActionListener(e -> tabuleiro.preencherPadraoX());

        btnLimpar.addActionListener(e -> tabuleiro.limpar());
    }
}
