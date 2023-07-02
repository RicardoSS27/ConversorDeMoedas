import javax.swing.JOptionPane;

public class MetodosConversor {

    double moedaIn;     //moeda que se deseja converter
    double moedaOut;    //moeda para qual sera convertida
    double valorIn;     //valor que deseja converter
    double valorOut;    //valor convertido
    int indexIn;
    int indexOut;

    double[] cotacaoMoeda = {5.21, 1, 0.95, 0.84, 195.5, 823.52, 0, 0, 0, 0};  //Cotação em relação a US$1.00
    String[] labelMoeda = {"Real", "Dolar", "Euro", "Libra", "Peso Argentino", "Peso Chileno", "", "", "", ""};

    int numeroMoedas = 6;                    // Número inicial de Moedas
    int numMaxMoedas = cotacaoMoeda.length;  // Número máximo de Moedas lenght = 10

    public int getIndexIn() {
        return indexIn;
    }

    public void setIndexIn(int indexIn) {
        this.indexIn = indexIn;
    }

    public int getIndexOut() {
        return indexOut;
    }

    public void setIndexOut(int indexOut) {
        this.indexOut = indexOut;
    }

    public double getValorOut() {
        return valorOut;
    }

    public void setValorOut(double valorOut) {
        this.valorOut = valorOut;
    }

    public String[] getLabelMoeda() {
        return labelMoeda;
    }

    public void setLabelMoeda(String[] labelMoeda) {
        this.labelMoeda = labelMoeda;
    }

    public MetodosConversor(double moedaIn, double moedaOut) {
        super();
        this.moedaIn = moedaIn;
        this.moedaOut = moedaOut;
    }

    public double getMoedaIn() {
        return moedaIn;
    }

    public void setMoedaIn(double moedaIn) {
        this.moedaIn = moedaIn;
    }

    public double getMoedaOut() {
        return moedaOut;
    }

    public void setMoedaOut(double moedaOut) {
        this.moedaOut = moedaOut;
    }

    public double getValorIn() {
        return valorIn;
    }

    public void setValorIn(double valorIn) {
        this.valorIn = valorIn;
    }

    public MetodosConversor() {
    }

    //*********************************************
    // Método que converte moedaIn em moedaOut
    //********************************************
    public double converte() {

        setMoedaIn(cotacaoMoeda[indexIn]);
        setMoedaOut(cotacaoMoeda[indexOut]);
        double valorOut = ((this.moedaOut * this.valorIn) / this.moedaIn);

        return valorOut;
    }

    //*****************************************************
    //Atribui a Moeda adicionada ao array de cotação e label
    //******************************************************
    public void addCotacao(String moeda, double cotacao) {

        numeroMoedas = numeroMoedas + 1;
        cotacaoMoeda[numeroMoedas - 1] = cotacao;
        labelMoeda[numeroMoedas - 1] = moeda;

    }

    //**********************************************
    //Atualiza cotações
    //**********************************************
    public void atualizarCotacoes() {
        for (int i = 0; i < numeroMoedas; i++) {
            System.out.println("Cotação do " + labelMoeda[i] + " " + i + "/" + cotacaoMoeda.length);
            for (; ; ) {
                try {

                    cotacaoMoeda[i] = Double.parseDouble(JOptionPane.showInputDialog("Cotação do " + labelMoeda[i]));
                    break;

                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(null, "Apena números e ponto decimal");
                }
            }

        }
    }


}

