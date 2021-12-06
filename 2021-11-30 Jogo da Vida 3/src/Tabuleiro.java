import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class Tabuleiro extends JPanel implements ComponentListener, MouseListener {
    public static final int DIMENSAO = 60;
    public static final int VIVA = 1;
    public static final int MORTA = 0;

    private static final int DIMENSAO_QUADRADO = DIMENSAO * DIMENSAO;
    private static final int CELULA_DIMENSAO = 10;
    private int[] celulas = new int[DIMENSAO_QUADRADO];

    public Tabuleiro() {
        addComponentListener(this);
        addMouseListener(this);        
    }

    public void atualizarPanel() {
        revalidate();
        repaint();
    }

    public void preencher() {
        for (int i = 0; i < celulas.length; i++) {
            celulas[i] = VIVA;
        }
        atualizarPanel();
    }

    public void preencherBorda() {
        limpar();

        for (int y = 0; y < DIMENSAO; y++) {
            for (int x = 0; x < DIMENSAO; x++) {
                if (x == 0 ||
                    y == 0 ||
                    x == DIMENSAO - 1 ||
                    y == DIMENSAO - 1) setCelula(x, y, VIVA);
            }
        }

        atualizarPanel();
    }

    public void preencherSemBorda() {
        limpar();

        for (int y = 0; y < DIMENSAO; y++) {
            for (int x = 0; x < DIMENSAO; x++) {
                if (x != 0 &&
                    y != 0 &&
                    x != DIMENSAO - 1 &&
                    y != DIMENSAO - 1) setCelula(x, y, VIVA);
            }
        }

        atualizarPanel();
    }

    public void preencherPadraoX() {
        limpar();

        for (int y = 0; y < DIMENSAO; y++) {
            for (int x = 0; x < DIMENSAO; x++) {
                if (y == x) setCelula(x, y, VIVA);
                if (x == (DIMENSAO - 1 - y)) setCelula(x, y, VIVA);
            }
        }

        atualizarPanel();
    }

    public void limpar() {
        for (int i = 0; i < celulas.length; i++) {
            celulas[i] = MORTA;
        }
        atualizarPanel();
    }

    public int getCelula(int x, int y) {
        return celulas[y * DIMENSAO + x];
    }

    public void setCelula(int x, int y, int estado) {
        celulas[y * DIMENSAO + x] = estado;
    }

    public int getCelulaId(int x, int y) {
        return y * DIMENSAO + x;
    }

    public void imprimirCelulas() {
        for (int y = 0; y < DIMENSAO; y++) {
            for (int x = 0; x < DIMENSAO; x++) {
                System.out.println("i: " + (y * DIMENSAO + x) + " | x: " + x + " | y: " + y + " | y");
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for (int x = 0; x < DIMENSAO; x++) {
            for (int y = 0; y < DIMENSAO; y++) {
                if (celulas[y * DIMENSAO + x] == VIVA) g.setColor(Color.GREEN);
                else g.setColor(Color.GRAY);

                g.fillRect(x * CELULA_DIMENSAO, y * CELULA_DIMENSAO, CELULA_DIMENSAO, CELULA_DIMENSAO);
                
                g.setColor(Color.BLACK);
                g.drawRect(x * CELULA_DIMENSAO, y * CELULA_DIMENSAO, CELULA_DIMENSAO, CELULA_DIMENSAO);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (int y = 0; y < DIMENSAO; y++) {
            for (int x = 0; x < DIMENSAO; x++) {
                if (e.getX() > x * CELULA_DIMENSAO &&
                    e.getX() < ((x + 1) * CELULA_DIMENSAO) &&
                    e.getY() > y * CELULA_DIMENSAO && 
                    e.getY() < ((y + 1) * CELULA_DIMENSAO)) {
                        if (getCelula(x, y) == VIVA) setCelula(x, y, MORTA);
                        else setCelula(x, y, VIVA);
                }
            }
        }

        atualizarPanel();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void componentResized(ComponentEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void componentMoved(ComponentEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void componentShown(ComponentEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void componentHidden(ComponentEvent e) {
        // TODO Auto-generated method stub
        
    }
}
