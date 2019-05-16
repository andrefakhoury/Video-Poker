package video_poker.main;

import video_poker.Baralho;
import video_poker.Carta;
import video_poker.Hand;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
    private JButton button1;
    private JPanel panel1;
    private JSpinner spinner1;
    private JLabel card1;
    private JLabel card2;
    private JLabel card3;
    private JLabel card4;
    private JLabel card5;
    private JCheckBox troca1CheckBox;
    private JCheckBox troca2CheckBox;
    private JCheckBox troca3CheckBox;
    private JCheckBox troca4CheckBox;
    private JCheckBox troca5CheckBox;
    private JButton button2;
    private JLabel trocado;

    Baralho b;
    Hand h;
    long bet;
    long creditos;
    int tradesLeft;

    public GUI() {
        b = null;
        h = null;
        creditos = 200;
        tradesLeft = 0;

        button1.addActionListener(e -> {
            if (h == null) {
                bet = ((Number) spinner1.getValue()).longValue();
                if (bet > 0) {
                    creditos -= bet;
                    b = new Baralho();
                    b.embaralhar();
                    h = new Hand(b);
                    tradesLeft = 2;

                    update();
                }
            } else {
                creditos += bet * h.multiplicadorMao();
                b = null;
                h = null;

                update();
            }
        });
        button2.addActionListener(e -> {
            if (h != null && tradesLeft > 0) {
                String trade = "";
                if (troca1CheckBox.isSelected()) trade += "1 ";
                if (troca2CheckBox.isSelected()) trade += "2 ";
                if (troca3CheckBox.isSelected()) trade += "3 ";
                if (troca4CheckBox.isSelected()) trade += "4 ";
                if (troca5CheckBox.isSelected()) trade += "5 ";
                h.trocar(trade);
                tradesLeft--;

                update();
            }
        });
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel1.add(panel2, gbc);
        card1 = new JLabel();
        card1.setText("");
        panel2.add(card1);
        card2 = new JLabel();
        card2.setText("");
        panel2.add(card2);
        card3 = new JLabel();
        card3.setText("");
        panel2.add(card3);
        card4 = new JLabel();
        card4.setText("");
        panel2.add(card4);
        card5 = new JLabel();
        card5.setText("");
        panel2.add(card5);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        panel1.add(panel3, gbc);
        spinner1 = new JSpinner();
        panel3.add(spinner1);
        button1 = new JButton();
        button1.setText("Button");
        panel3.add(button1);
        trocado = new JLabel();
        trocado.setText("Label");
        panel3.add(trocado);
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        panel1.add(panel4, gbc);
        troca1CheckBox = new JCheckBox();
        troca1CheckBox.setText("Troca 1");
        panel4.add(troca1CheckBox);
        troca2CheckBox = new JCheckBox();
        troca2CheckBox.setText("Troca 2");
        panel4.add(troca2CheckBox);
        troca3CheckBox = new JCheckBox();
        troca3CheckBox.setText("Troca 3");
        panel4.add(troca3CheckBox);
        troca4CheckBox = new JCheckBox();
        troca4CheckBox.setText("Troca 4");
        panel4.add(troca4CheckBox);
        troca5CheckBox = new JCheckBox();
        troca5CheckBox.setText("Troca 5");
        panel4.add(troca5CheckBox);
        button2 = new JButton();
        button2.setText("Button");
        panel4.add(button2);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }

    private void createUIComponents() {
    }

    private Icon getIcon(int i) {
        Carta c = h == null ? null : h.getHand()[i];
        String file;
        if (c == null) {
            file = "./images/test.png";
        } else {
            file = "./images/carta.png";
        }
        ImageIcon icon = new ImageIcon(file);
        Image im = icon.getImage().getScaledInstance(75, 150, Image.SCALE_DEFAULT);
        return new ImageIcon(im);
    }

    private String getName(int i) {
        Carta c = h == null ? null : h.getHand()[i];
        return c == null ? "" : c.getNaipe() + " " + c.getValor();
    }

    public void update() {
        trocado.setText("Voce tem " + creditos);
        if (h == null) {
            spinner1.setModel(new SpinnerNumberModel(0, -1, creditos, 1));
        } else {
            spinner1.setModel(new SpinnerNumberModel(bet, bet, bet, 1));
        }
        card1.setIcon(getIcon(0));
        card1.setText(getName(0));
        card2.setIcon(getIcon(1));
        card2.setText(getName(1));
        card3.setIcon(getIcon(2));
        card3.setText(getName(2));
        card4.setIcon(getIcon(3));
        card4.setText(getName(3));
        card5.setIcon(getIcon(4));
        card5.setText(getName(4));
    }
}
