import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.JSeparator;
import java.text.DecimalFormat;
import java.util.Locale;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Color;
import javax.swing.SwingConstants;

public class Conversor extends JFrame {

    private JPanel contentPane;
    private JTextField tfValor;
    private JTextField tfValorOut;
    JComboBox cbMoedaIn = new JComboBox();
    JComboBox cbMoedaOut = new JComboBox();
    private JLabel lblMoeda;

    MetodosConversor conv = new MetodosConversor();

    /**
     * Launch the application.
     */

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Conversor frame = new Conversor();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Conversor() {

        setTitle("Conversor de Moedas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 435);
        contentPane = new JPanel();
        contentPane.setForeground(new Color(128, 255, 128));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Valor");
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 14));
        lblNewLabel.setBounds(25, 29, 51, 31);
        contentPane.add(lblNewLabel);

        tfValor = new JTextField();
        tfValor.setHorizontalAlignment(SwingConstants.RIGHT);
        tfValor.setForeground(new Color(0, 128, 0));
        tfValor.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                // Apaga valor da conversão anterior
                tfValorOut.setText("");
            }
        });

        tfValor.setFont(new Font("Tahoma", Font.BOLD, 14));
        tfValor.setBounds(25, 61, 159, 20);
        contentPane.add(tfValor);
        tfValor.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Valor");
        lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 14));
        lblNewLabel_1.setBounds(243, 29, 51, 31);
        contentPane.add(lblNewLabel_1);

        tfValorOut = new JTextField();
        tfValorOut.setHorizontalAlignment(SwingConstants.RIGHT);
        tfValorOut.setForeground(new Color(0, 0, 160));
        tfValorOut.setEditable(false);
        tfValorOut.setFont(new Font("Tahoma", Font.BOLD, 14));
        tfValorOut.setColumns(10);
        tfValorOut.setBounds(243, 61, 159, 20);
        contentPane.add(tfValorOut);

        //*************************************************
        // Botão para disparar a conversão
        //*************************************************
        JButton btnNewButton = new JButton("Converter");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    conv.setValorIn(Double.parseDouble(tfValor.getText()));
                    conv.setIndexIn(cbMoedaIn.getSelectedIndex());
                    conv.setIndexOut(cbMoedaOut.getSelectedIndex());

                    conv.setValorOut(conv.converte());

                    Locale.setDefault( Locale.US );
                    DecimalFormat myFormatter = new DecimalFormat("#,##0.0000");

                    String valorFormatado = myFormatter.format(conv.getValorOut());

                    tfValorOut.setText(valorFormatado);

                }
                catch (NumberFormatException nfe) {
                    System.out.println("One of the command-line ");
                    JOptionPane.showMessageDialog(null, "Apena números e ponto decimal");
                }

            }
        });
        btnNewButton.setFont(new Font("Arial", Font.BOLD, 14));
        btnNewButton.setBounds(150, 174, 113, 23);
        contentPane.add(btnNewButton);

        lblMoeda = new JLabel("Moeda");
        lblMoeda.setFont(new Font("Arial", Font.BOLD, 14));
        lblMoeda.setBounds(25, 92, 51, 31);
        contentPane.add(lblMoeda);
        cbMoedaIn.setForeground(new Color(0, 128, 0));
        cbMoedaIn.addPopupMenuListener(new PopupMenuListener() {
            public void popupMenuCanceled(PopupMenuEvent e) {
            }
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {

                // Apaga valor da conversão anterior
                limpaValorOut();
            }
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
            }
        });

        //**************************************************
        // Selecionar a moeda a ser convertida
        //**************************************************
        cbMoedaIn.setModel(new DefaultComboBoxModel(new String[] {"Real", "Dolar", "Euro", "Libra", "Peso Argentino", "Peso Chileno"}));
        cbMoedaIn.setFont(new Font("Arial", Font.BOLD, 14));
        cbMoedaIn.setBounds(25, 121, 159, 22);
        contentPane.add(cbMoedaIn);

        JLabel lblConverterPara = new JLabel("Converter Para");
        lblConverterPara.setFont(new Font("Arial", Font.BOLD, 14));
        lblConverterPara.setBounds(241, 92, 125, 31);
        contentPane.add(lblConverterPara);
        cbMoedaOut.setForeground(new Color(0, 0, 160));
        cbMoedaOut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // Apaga valor da conversão anterior
                limpaValorOut();
            }
        });

        //****************************************************
        // Selecionar a moeda para qual se deseja converter
        //****************************************************
        cbMoedaOut.setModel(new DefaultComboBoxModel(new String[] {"Real", "Dolar", "Euro", "Libra", "Peso Argentino", "Peso Chileno"}));
        cbMoedaOut.setFont(new Font("Arial", Font.BOLD, 14));
        cbMoedaOut.setBounds(241, 121, 159, 22);
        contentPane.add(cbMoedaOut);

        JSeparator separator = new JSeparator();
        separator.setBounds(10, 227, 414, 2);
        contentPane.add(separator);

        //************************************************
        // Adicionar Nova Moeda
        //***********************************************
        JButton btnNewButton_1 = new JButton("Adicionar Moeda");
        btnNewButton_1.setFont(new Font("Arial", Font.BOLD, 12));
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // Apaga valor da conversão anterior
                limpaValorOut();

                if(conv.numeroMoedas == conv.numMaxMoedas) {
                    JOptionPane.showMessageDialog(null, "Versão Free -" + "Número Máximo de Moedas: " + conv.numeroMoedas);
                }
                else {

                    String novaMoeda = "";
                    for(;;) {

                        novaMoeda = JOptionPane.showInputDialog("Nome da Moeda");

                        if((!novaMoeda.trim().isEmpty()) && (novaMoeda.matches("[a-zA-Z\s]+"))) {
                            System.out.println(novaMoeda + " " + novaMoeda.matches("[a-zA-Z\s]+"));
                            break;
                        }else {
                            System.out.println("Nome false:" + novaMoeda + ":" + novaMoeda.matches("[a-zA-Z\s]+"));
                            JOptionPane.showMessageDialog(null, "Digite um nome de Moeda!");
                        }
                    }

                    double cotacao = 0;
                    for(;;) {
                        try {
                            cotacao = Double.parseDouble(JOptionPane.showInputDialog("Cotação do " + novaMoeda));

                            cbMoedaIn.addItem(novaMoeda);
                            cbMoedaOut.addItem(novaMoeda);
                            break;
                        }
                        catch (NumberFormatException nfe) {

                            JOptionPane.showMessageDialog(null, "Apena números e ponto decimal");
                        }
                    }
                    if(novaMoeda != null) {
                        conv.addCotacao(novaMoeda, cotacao);
                    }
                }
            }
        });
        btnNewButton_1.setBounds(38, 262, 159, 23);
        contentPane.add(btnNewButton_1);

        //*********************************************************
        // Atualizar Cotações
        //*********************************************************
        JButton btnNewButton_2 = new JButton("Atualizar Cotações");
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // Apaga valor da conversão anterior
                limpaValorOut();

                conv.atualizarCotacoes();
            }
        });
        btnNewButton_2.setFont(new Font("Arial", Font.BOLD, 12));
        btnNewButton_2.setBounds(220, 262, 159, 23);
        contentPane.add(btnNewButton_2);

        //********************************************************
        //Sair
        //********************************************************
        JButton btnSair = new JButton("Sair");
        btnSair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                System.exit(0);
            }
        });
        btnSair.setFont(new Font("Arial", Font.BOLD, 12));
        btnSair.setBounds(150, 312, 113, 23);
        contentPane.add(btnSair);

    }

    //******************************************************
    //Limpa valor da caixa com valor convertido
    //******************************************************
    public void limpaValorOut() {
        // Apaga valor da conversão anterior
        tfValorOut.setText("");
    }


}