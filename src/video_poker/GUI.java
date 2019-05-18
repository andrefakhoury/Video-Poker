package video_poker;

import javax.swing.*;
import javax.xml.stream.Location;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements ActionListener {

    private JPanel panMain, panInfo, panAposta, panSair, panCartas;
    private JLabel lblTotalDinheiro, lblApostaAtual, lblAposta, lblCartas[];
    private JCheckBox ckbTrocaCarta[];
    private JFormattedTextField txtAposta;
    private JButton btnConfirmaAposta, btnSair, btnConfirmaCartas;

    private long creditos, aposta;
    private Baralho baralho;
    private Hand hand;

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(btnSair)) {
            if (JOptionPane.showConfirmDialog(this, "Deseja realmente sair?", "Sair", 0) == 0) {
                System.exit(0);
            }
        } else if (actionEvent.getSource().equals(btnConfirmaAposta)) {
            try {
                aposta = (Integer)(txtAposta.getValue());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Valor invalido!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            iniciaJogo();
        } else if (actionEvent.getSource().equals(btnConfirmaCartas)) {
            trocaCartas();
        }
    }

    private Icon getCardIcon(int i) {
        Carta c = hand == null ? null : hand.getHand()[i];
        String file;

        if (c == null) {
            file = "./images/test.png";
        } else {
            file = "./images/carta.png";
        }

        ImageIcon icon = new ImageIcon(file);
        Image im = icon.getImage().getScaledInstance(100, 150, Image.SCALE_DEFAULT);
        return new ImageIcon(im);
    }

    private String getCardName(int i) {
        Carta c = hand == null ? null : hand.getHand(i);
        return c == null ? "NULL" : c.getNaipe().getSimbolo() + " " + c.getValor();
    }

    private void atualizaCartas() {
        for (int i = 0; i < 5; i++) {
            lblCartas[i].setIcon(getCardIcon(i));
            ckbTrocaCarta[i].setText(getCardName(i));
            ckbTrocaCarta[i].setSelected(false);
        }
    }

    private void iniciaJogo() {
        lblApostaAtual.setText("Valor da aposta atual: " + aposta);
        panCartas.setVisible(true);
        panAposta.setVisible(false);

        panMain.remove(panAposta);
        panMain.remove(panSair);
        panMain.add(panCartas);
        panMain.add(panSair);
    }

    private void trocaCartas() {
        boolean trocar[] = new boolean[5];
        for (int i = 0; i < 5; i++) {
            trocar[i] = ckbTrocaCarta[i].isSelected();
        }

        hand.trocar(trocar);
        atualizaCartas();
    }

    public GUI() {
        super("Video Poker - User Interface Edition");
        this.setSize(800, 500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        creditos = 200;
        baralho = new Baralho();
        baralho.embaralhar();
        hand = new Hand(baralho);

        // JPanel principal, que vai abrigar todos os componentes
        panMain = new JPanel(new GridLayout(0, 1));
        this.add(panMain);

        // JPanel de informacoes atuais - dinheiro e aposta
        panInfo = new JPanel(new GridLayout(1, 2));
        panInfo.setBounds(0, 0, this.getWidth(), 100);

        lblTotalDinheiro = new JLabel("Dinheiro total disponivel: " + creditos);
        lblApostaAtual = new JLabel("Valor da aposta atual: ---");

        panInfo.add(lblTotalDinheiro);
        panInfo.add(lblApostaAtual);

        // JPanel inicial, que pergunta as informacoes iniciais
        panAposta = new JPanel();

        lblAposta = new JLabel("Digite o quanto quer apostar:");
        txtAposta = new JFormattedTextField();
        txtAposta.setValue(300);

        btnConfirmaAposta = new JButton("Confirma");
        btnConfirmaAposta.addActionListener(this);

        panAposta.add(lblAposta);
        panAposta.add(txtAposta);
        panAposta.add(btnConfirmaAposta);

        // JPanel que abriga o botao de sair
        panSair = new JPanel();
        btnSair = new JButton("Sair");
        btnSair.setBounds(btnSair.getX() + 100, btnSair.getY(), 300, btnSair.getHeight());
        btnSair.addActionListener(this);
        panSair.add(btnSair);

        // JPanel que abriga as cartas
        panCartas = new JPanel(new GridLayout(0, 5));
        panCartas.setSize(panCartas.getWidth(), 300);
        lblCartas = new JLabel[5];
        ckbTrocaCarta = new JCheckBox[5];
        for (int i = 0; i < 5; i++) {
            lblCartas[i] = new JLabel();
            lblCartas[i].setBounds(lblCartas[i].getX(), lblCartas[i].getY(), 100, 150);
            panCartas.add(lblCartas[i]);
        }

        for (int i = 0; i < 5; i++) {
            ckbTrocaCarta[i] = new JCheckBox();
            panCartas.add(ckbTrocaCarta[i]);
        }

        atualizaCartas();

        btnConfirmaCartas = new JButton("Confirmar");
        btnConfirmaCartas.addActionListener(this);
        panCartas.add(btnConfirmaCartas);
        panCartas.setVisible(false);

        panMain.add(panInfo);
        panMain.add(panAposta);
        panMain.add(panSair);

        this.setVisible(true);
    }

}
