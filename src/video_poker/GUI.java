package video_poker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements ActionListener {

    // Componentes do frame
    private JPanel panMain, panInfo, panAposta, panSair, panCartas, panFimJogo;
    private JLabel lblTotalDinheiro, lblApostaAtual, lblAposta, lblCartas[], lblFimJogo, lblPontuacaoFinal;
    private JCheckBox ckbTrocaCarta[];
    private JTextField txtAposta;
    private JButton btnConfirmaAposta, btnFinaliza, btnSair, btnConfirmaCartas;

    // variaveis auxiliares para a logica
    private long creditos, aposta;
    private int numJogadas;
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
                aposta = Integer.parseInt(txtAposta.getText());
            } catch (Exception ex) {
                aposta = -1;
            }

            if (aposta <= 0 || aposta > creditos) {
                JOptionPane.showMessageDialog(this, "Valor invalido!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            iniciaRodada();
        } else if (actionEvent.getSource().equals(btnConfirmaCartas)) {
            if (numJogadas == 1) {
                encerraRodada();
            } else {
                trocaCartas();
            }
        } else if (actionEvent.getSource().equals(btnFinaliza)) {
            encerraJogo();
        }
    }

    /**
     * Funcao para pegar o icone de uma carta
     * @param i indice da mao atual
     * @return icone para a carta do indice i da mao atual
     */
    private Icon getCardIcon(int i) {
        Carta c = hand == null ? null : hand.getHand(i);
        String file;

        if (c == null) {
            file = "./images/ERROR.jpg";
        } else {
            String naipe = "";
            String valor = c.getValor().getSimbolo();

            if(c.getNaipe().equals(Carta.Naipe.PAUS)) {
                naipe = "C";
            } else if (c.getNaipe().equals(Carta.Naipe.OUROS)) {
                naipe = "D";
            } else if (c.getNaipe().equals(Carta.Naipe.ESPADAS)) {
                naipe = "S";
            } else if (c.getNaipe().equals(Carta.Naipe.COPAS)) {
                naipe = "H";
            }

            file = "./images/" + valor + naipe + ".jpg";
        }

        ImageIcon icon = new ImageIcon();
        Image im;

        try {
            icon = new ImageIcon(file);
        } catch (Exception ex) {
            icon = new ImageIcon("./images/ERROR.jpg");
        } finally {
            im = icon.getImage().getScaledInstance(60, 85, Image.SCALE_DEFAULT);
        }

        return new ImageIcon(im);
    }

    /**
     * Retorna o nome da carta atual
     * @param i indice da carta da mao atual
     * @return nome da carta
     */
    private String getCardName(int i) {
        Carta c = hand == null ? null : hand.getHand(i);
        return c == null ? "NULL" : c.getNaipe().getSimbolo() + " " + c.getValor();
    }

    /**
     * Atualiza o icone e texto das cartas atuais
     */
    private void atualizaCartas() {
        for (int i = 0; i < 5; i++) {
            lblCartas[i].setIcon(getCardIcon(i));
            ckbTrocaCarta[i].setText(getCardName(i));
            ckbTrocaCarta[i].setSelected(false);
        }
    }

    /**
     * Inicia a rodada atual, ajeitando todas as variaveis auxiliares
     */
    private void iniciaRodada() {
        baralho = new Baralho();
        baralho.embaralhar();
        hand = new Hand(baralho);
        atualizaCartas();

        txtAposta.setText("");

        lblApostaAtual.setText("Valor da aposta atual: " + aposta);

        creditos -= aposta;
        lblTotalDinheiro.setText("Dinheiro total disponivel: " + creditos);

        panCartas.setVisible(true);
        panAposta.setVisible(false);

        panMain.remove(panAposta);
        panMain.remove(panSair);
        panMain.add(panCartas);
        panMain.add(panSair);

        numJogadas = 0;
    }

    /**
     * Troca as cartas selecionadas
     */
    private void trocaCartas() {
        boolean trocar[] = new boolean[5];
        for (int i = 0; i < 5; i++) {
            trocar[i] = ckbTrocaCarta[i].isSelected();
        }

        hand.trocar(trocar);
        atualizaCartas();
        numJogadas++;
    }

    /**
     * Encerra a rodada atual, informando o profit
     */
    private void encerraRodada() {
        int mult = hand.multiplicadorMao();
        JOptionPane.showMessageDialog(this, "Profit de " + mult + "x!", "Profit", JOptionPane.INFORMATION_MESSAGE);

        creditos += aposta * mult;

        lblTotalDinheiro.setText("Dinheiro total disponivel: " + creditos);
        lblApostaAtual = new JLabel("Valor da aposta atual: ---");

        if (creditos <= 0) {
            encerraJogo();
        }

        panCartas.setVisible(false);
        panMain.remove(panCartas);
        panMain.remove(panSair);
        panAposta.setVisible(true);
        panMain.add(panAposta);
        panMain.add(panSair);
    }

    /**
     * Encerra o jogo, informando a pontuacao final
     */
    private void encerraJogo() {
        lblPontuacaoFinal.setText(creditos + " pontos!");

        panCartas.setVisible(false);
        panAposta.setVisible(false);
        panFimJogo.setVisible(true);

        panMain.remove(panCartas);
        panMain.remove(panAposta);
        panMain.remove(panSair);

        panMain.add(panFimJogo);
        panMain.add(panSair);
    }

    /**
     * Constroi todos os componentes
     */
    public GUI() {
        super("Video Poker - User Interface Edition");
        this.setSize(920, 720);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        creditos = 200;

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
        panAposta = new JPanel(new GridLayout(2, 2));

        lblAposta = new JLabel("Digite o quanto quer apostar:");
        txtAposta = new JTextField();
        txtAposta.setSize(400, txtAposta.getHeight());

        btnConfirmaAposta = new JButton("Confirma");
        btnConfirmaAposta.addActionListener(this);

        btnFinaliza = new JButton("Parar de jogar");
        btnFinaliza.addActionListener(this);

        panAposta.add(lblAposta);
        panAposta.add(txtAposta);
        panAposta.add(btnConfirmaAposta);
        panAposta.add(btnFinaliza);

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

        // JPanel de fim de jogo
        panFimJogo = new JPanel(new GridLayout(0, 1));
        lblFimJogo = new JLabel("Fim de jogo! Pontuacao final:");
        lblPontuacaoFinal = new JLabel("0");
        panFimJogo.add(lblFimJogo);
        panFimJogo.add(lblPontuacaoFinal);

        panMain.add(panInfo);
        panMain.add(panAposta);
        panMain.add(panSair);

        this.setVisible(true);
    }
}