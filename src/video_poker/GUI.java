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
    private JButton btnConfirmaAposta, btnSair;

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
        return c == null ? "NULL" : c.getNaipe() + " " + c.getValor();
    }

    public GUI() {
        super("Video Poker - User Interface Edition");
        this.setSize(800, 500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        creditos = 200;

        // JPanel principal, que vai abrigar todos os componentes
        panMain = new JPanel();
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
        panAposta.setSize(500, 500);


        lblAposta = new JLabel("Digite o quanto quer apostar:");
        txtAposta = new JFormattedTextField();
        txtAposta.setValue(300);

        txtAposta.setSize(300, txtAposta.getHeight());

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

        // JP
        panCartas = new JPanel(new GridLayout(5, 0));
        panCartas.setSize(panCartas.getWidth(), 300);
        lblCartas = new JLabel[5];
        ckbTrocaCarta = new JCheckBox[5];
        for (int i = 0; i < 5; i++) {
            lblCartas[i] = new JLabel("OIEIEE" + i);
            lblCartas[i].setBounds(lblCartas[i].getX(), lblCartas[i].getY(), 100, 150);
            lblCartas[i].setIcon(getCardIcon(i));
            lblCartas[i].setText(getCardName(i));
            panCartas.add(lblCartas[i]);

            ckbTrocaCarta[i] = new JCheckBox();
            panCartas.add(ckbTrocaCarta[i]);
        }
        panCartas.setVisible(false);


        panMain.add(panInfo);
        panMain.add(panAposta);
        panMain.add(panSair);

        this.setVisible(true);
    }

}
