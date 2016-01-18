/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.kelvin.projeto.apresentacao;

import br.edu.ifnmg.kelvin.projeto.entidade.Atleta;
import br.edu.ifnmg.kelvin.projeto.entidade.Avaliacao;
import br.edu.ifnmg.kelvin.projeto.entidade.PersonalTrainer;
import br.edu.ifnmg.kelvin.projeto.negocio.AtletaBO;
import br.edu.ifnmg.kelvin.projeto.negocio.AvaliacaoBO;
import br.edu.ifnmg.kelvin.projeto.negocio.PersonalBO;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author KELVIN
 */
public class VisualizarAvaliacao extends javax.swing.JFrame {

    private List<Avaliacao> avaliacoes;
    private List<Atleta> atletas;
    private List<PersonalTrainer> personals;
    private SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy"); 
    private Avaliacao avaliacaoEmEdicao;
    
    
    /**
     * Creates new form VisualizarAvaliacao
     */
    public VisualizarAvaliacao(Avaliacao avaliacaoParaEdicao) {
        avaliacaoEmEdicao = avaliacaoParaEdicao;
        this.prepararTela();
        this.inicializarCamposTela();
    }
 
    private void prepararTela(){
        try {
            this.initComponents();
            this.carregarComboAtletas();
            this.carregarComboPersonals();
            setLocationRelativeTo(null);
        } catch (Exception e) {
            String mensagem = "Erro inesperado! Informe a mensagem de erro ao administrador do sistema.";
            mensagem += "\nMensagem de erro:\n" + e.getMessage();
            JOptionPane.showMessageDialog(this, mensagem, "Cadastro de Atletas", JOptionPane.ERROR_MESSAGE);
            this.dispose();
        }      
    }     

    public void carregarComboPersonals(){
        PersonalBO personalBO = new PersonalBO();
        try {
            personals = personalBO.buscarTodos();
        } catch (SQLException ex) {
        
        }
        cboAvaliador.removeAllItems();
        for (PersonalTrainer personal : personals) {
            cboAvaliador.addItem(personal.getNome());
        }
    }
       
    private void carregarComboAtletas() throws SQLException{
        AtletaBO atletaBO = new AtletaBO();
        try{
            atletas = atletaBO.buscarTodos();
        }catch(SQLException ex){
        
        }
        cboAtletas.removeAllItems();
        for(Atleta atleta : atletas){
            cboAtletas.addItem(atleta.getNome());
        }
    }    
    
    public void inicializarCamposTela(){
        String peso = Double.toString(avaliacaoEmEdicao.getPeso());
        txtPeso.setText(peso);
        String altura = Double.toString(avaliacaoEmEdicao.getAltura());
        txtAltura.setText(altura);
        String frequencia = Double.toString(avaliacaoEmEdicao.getFrequenciaCardiaca());
        txtFrequenciaCardiaca.setText(frequencia);
        String pressao = Double.toString(avaliacaoEmEdicao.getPressaoArterial());
        txtPressaoArterial.setText(pressao);
        String torax = Double.toString(avaliacaoEmEdicao.getTorax());
        txtTorax.setText(torax);
        String cintura = Double.toString(avaliacaoEmEdicao.getCintura());
        txtCintura.setText(cintura);
        String abdome = Double.toString(avaliacaoEmEdicao.getAbdome());
        txtAbdome.setText(abdome);
        String quadril = Double.toString(avaliacaoEmEdicao.getQuadril());
        txtQuadril.setText(quadril);
        String antebracoEsquerdo = Double.toString(avaliacaoEmEdicao.getAntebracoEsquerdo());
        txtAntebracoEsquerdo.setText(antebracoEsquerdo);
        String antebracoDireito = Double.toString(avaliacaoEmEdicao.getAntebracoDireito());
        txtAntebracoDireito.setText(antebracoDireito);
        String bracoEsquerdo = Double.toString(avaliacaoEmEdicao.getBracoEsquerdo());
        txtBracoEsquerdo.setText(bracoEsquerdo);
        String bracoDireito = Double.toString(avaliacaoEmEdicao.getBracoDireito());
        txtBracoDireito.setText(bracoDireito);
        String coxaEsquerda = Double.toString(avaliacaoEmEdicao.getCoxaEsquerda());
        txtCoxaEsquerda.setText(coxaEsquerda);
        String coxaDireita = Double.toString(avaliacaoEmEdicao.getCoxaDireita());
        txtCoxaDireita.setText(coxaDireita);
        cboAtletas.setSelectedItem(avaliacaoEmEdicao.getNomeAtleta());
        cboAvaliador.setSelectedItem(avaliacaoEmEdicao.getNomePersonal());
        String panturrilhaEsquerda = Double.toString(avaliacaoEmEdicao.getPanturrilhaEsquerda());
        txtPanturrilhaEsquerda.setText(panturrilhaEsquerda);
        String panturrilhaDireita = Double.toString(avaliacaoEmEdicao.getPanturrilhaDireita());
        txtPanturrilhaDireita.setText(panturrilhaDireita);
        String subscapular = Double.toString(avaliacaoEmEdicao.getSubscapular());
        txtSubscapular.setText(subscapular);
        String tricipital = Double.toString(avaliacaoEmEdicao.getTricipital());
        txtTricipital.setText(tricipital);
        String peitoral = Double.toString(avaliacaoEmEdicao.getPeitoral());
        txtPeitoral.setText(peitoral);
        String axilarMedia = Double.toString(avaliacaoEmEdicao.getAxilarMedia());
        txtAxilarMedia.setText(axilarMedia);
        String supraIliaca = Double.toString(avaliacaoEmEdicao.getSupraIliaca());
        txtSupraIliaca.setText(supraIliaca);
        String abdominal = Double.toString(avaliacaoEmEdicao.getAbdominal());
        txtAbdominal.setText(abdominal);
        String coxa = Double.toString(avaliacaoEmEdicao.getCoxa());
        txtDobraCoxa.setText(coxa);
        String flexoes = Integer.toString(avaliacaoEmEdicao.getFlexoes());
        txtFlexoes.setText(flexoes);
        String abdominais = Integer.toString(avaliacaoEmEdicao.getAbdominais());
        txtAbdominais.setText(abdominais);
        String dataAvaliacao = formatador.format(avaliacaoEmEdicao.getDataAvaliacao());
        txtDataAvaliacao.setText(dataAvaliacao);
        String dataValidade = formatador.format(avaliacaoEmEdicao.getDataValidade());
        txtDataValidade.setText(dataValidade);
    }
    
    private void gerarRelatorio() throws SQLException{
        AvaliacaoBO avaliacaoBO = new AvaliacaoBO();
        this.avaliacoes = avaliacaoBO.buscarTodos();
        try{
            String arquivoRelatorio = System.getProperty("user.dir")+
                    "/relatorios/RelatorioAvaliacao.jasper";
            
            Map<String, Object> parametros = new HashMap<String, Object>();
            
            JRBeanCollectionDataSource fonteDados = new JRBeanCollectionDataSource(this.avaliacoes);
            
            JasperPrint  relatorioGerado = JasperFillManager.fillReport(arquivoRelatorio, parametros, fonteDados);
            
            JasperViewer telaExibicaoRelatorio = new JasperViewer(relatorioGerado,false);
            telaExibicaoRelatorio.setTitle("Relatório de Avaliações");
            telaExibicaoRelatorio.setVisible(true);
        }catch(JRException ex){
            Logger.getLogger(VisualizarAvaliacao.class.getName()).log(Level.SEVERE, null, ex);
        }     
    }    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlAvaliacao = new javax.swing.JTabbedPane();
        pnlSubAvaliacao = new javax.swing.JPanel();
        pnlPerimetros = new javax.swing.JPanel();
        lblTorax = new javax.swing.JLabel();
        txtTorax = new javax.swing.JTextField();
        lblCintura = new javax.swing.JLabel();
        txtCintura = new javax.swing.JTextField();
        lblAbdome = new javax.swing.JLabel();
        txtAbdome = new javax.swing.JTextField();
        lblQuadril = new javax.swing.JLabel();
        txtQuadril = new javax.swing.JTextField();
        lblAntebraco = new javax.swing.JLabel();
        txtAntebracoEsquerdo = new javax.swing.JTextField();
        lblBraco = new javax.swing.JLabel();
        txtBracoDireito = new javax.swing.JTextField();
        txtPanturrilhaEsquerda = new javax.swing.JTextField();
        lblCoxa = new javax.swing.JLabel();
        lblPanturrilha = new javax.swing.JLabel();
        txtCoxaEsquerda = new javax.swing.JTextField();
        txtCoxaDireita = new javax.swing.JTextField();
        txtPanturrilhaDireita = new javax.swing.JTextField();
        txtAntebracoDireito = new javax.swing.JTextField();
        txtBracoEsquerdo = new javax.swing.JTextField();
        lblDireito1 = new javax.swing.JLabel();
        lblEsquerdo1 = new javax.swing.JLabel();
        lblEsquerdo2 = new javax.swing.JLabel();
        lblDireito2 = new javax.swing.JLabel();
        pnlDobrasCutaneas = new javax.swing.JPanel();
        lblSubscapular = new javax.swing.JLabel();
        txtSubscapular = new javax.swing.JTextField();
        lblTricipital = new javax.swing.JLabel();
        txtTricipital = new javax.swing.JTextField();
        lblPeitoral = new javax.swing.JLabel();
        txtPeitoral = new javax.swing.JTextField();
        txtAxilarMedia = new javax.swing.JTextField();
        txtSupraIliaca = new javax.swing.JTextField();
        txtAbdominal = new javax.swing.JTextField();
        lblAbdominal = new javax.swing.JLabel();
        lblSupraIliaca = new javax.swing.JLabel();
        lblAxilarMedia = new javax.swing.JLabel();
        lblDobraCoxa = new javax.swing.JLabel();
        txtDobraCoxa = new javax.swing.JTextField();
        pnlResistencia = new javax.swing.JPanel();
        lblFlexoes = new javax.swing.JLabel();
        txtFlexoes = new javax.swing.JTextField();
        lblAbdominais = new javax.swing.JLabel();
        txtAbdominais = new javax.swing.JTextField();
        lblPeso = new javax.swing.JLabel();
        txtPeso = new javax.swing.JTextField();
        lblPeso1 = new javax.swing.JLabel();
        txtAltura = new javax.swing.JTextField();
        lblFrequenciaCardiaca = new javax.swing.JLabel();
        txtFrequenciaCardiaca = new javax.swing.JTextField();
        lblPressaoArterial = new javax.swing.JLabel();
        txtPressaoArterial = new javax.swing.JTextField();
        lblAvaliador = new javax.swing.JLabel();
        cboAvaliador = new javax.swing.JComboBox<>();
        lblDataAvaliacao = new javax.swing.JLabel();
        txtDataAvaliacao = new javax.swing.JFormattedTextField();
        lblDataValidade = new javax.swing.JLabel();
        txtDataValidade = new javax.swing.JFormattedTextField();
        btnCadastrar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        cboAtletas = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Visualizar Avaliação");

        pnlPerimetros.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Perímetros"));

        lblTorax.setText("Tórax");

        lblCintura.setText("Cintura");

        lblAbdome.setText("Abdome");

        lblQuadril.setText("Quadril");

        lblAntebraco.setText("Antebraco");

        lblBraco.setText("Braco");

        lblCoxa.setText("Coxa");

        lblPanturrilha.setText("Panturrilha");

        txtCoxaEsquerda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCoxaEsquerdaActionPerformed(evt);
            }
        });

        txtCoxaDireita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCoxaDireitaActionPerformed(evt);
            }
        });

        lblDireito1.setText("Direito");

        lblEsquerdo1.setText("Esquerdo");

        lblEsquerdo2.setText("Esquerdo");

        lblDireito2.setText("Direito");

        javax.swing.GroupLayout pnlPerimetrosLayout = new javax.swing.GroupLayout(pnlPerimetros);
        pnlPerimetros.setLayout(pnlPerimetrosLayout);
        pnlPerimetrosLayout.setHorizontalGroup(
            pnlPerimetrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPerimetrosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPerimetrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlPerimetrosLayout.createSequentialGroup()
                        .addComponent(lblCintura)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCintura, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(lblQuadril)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtQuadril, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlPerimetrosLayout.createSequentialGroup()
                        .addComponent(lblTorax)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTorax, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(lblAbdome)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAbdome, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(14, 14, 14)
                .addGroup(pnlPerimetrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAntebraco)
                    .addComponent(lblBraco, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlPerimetrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblEsquerdo1)
                    .addGroup(pnlPerimetrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtBracoEsquerdo, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                        .addComponent(txtAntebracoEsquerdo)))
                .addGap(18, 18, 18)
                .addGroup(pnlPerimetrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtBracoDireito, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAntebracoDireito, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDireito1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlPerimetrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblPanturrilha)
                    .addComponent(lblCoxa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlPerimetrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPerimetrosLayout.createSequentialGroup()
                        .addComponent(lblEsquerdo2)
                        .addGap(18, 18, 18)
                        .addComponent(lblDireito2))
                    .addGroup(pnlPerimetrosLayout.createSequentialGroup()
                        .addComponent(txtCoxaEsquerda, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCoxaDireita, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlPerimetrosLayout.createSequentialGroup()
                        .addComponent(txtPanturrilhaEsquerda, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPanturrilhaDireita, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlPerimetrosLayout.setVerticalGroup(
            pnlPerimetrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPerimetrosLayout.createSequentialGroup()
                .addGroup(pnlPerimetrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPerimetrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCintura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblCintura))
                    .addGroup(pnlPerimetrosLayout.createSequentialGroup()
                        .addGroup(pnlPerimetrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlPerimetrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblDireito2)
                                .addComponent(lblEsquerdo2))
                            .addGroup(pnlPerimetrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblDireito1)
                                .addComponent(lblEsquerdo1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlPerimetrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtAntebracoEsquerdo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCoxaEsquerda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCoxaDireita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCoxa)
                            .addComponent(lblAntebraco)
                            .addComponent(txtAntebracoDireito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAbdome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAbdome)
                            .addComponent(txtTorax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTorax))
                        .addGap(26, 26, 26)
                        .addGroup(pnlPerimetrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBracoDireito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPanturrilha)
                            .addComponent(txtPanturrilhaEsquerda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPanturrilhaDireita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblBraco)
                            .addComponent(txtBracoEsquerdo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtQuadril, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblQuadril))))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pnlDobrasCutaneas.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Dobras Cutâneas"));

        lblSubscapular.setText("Subscapular");

        lblTricipital.setText("Tricipital");

        lblPeitoral.setText("Peitoral");

        lblAbdominal.setText("Abdominal");

        lblSupraIliaca.setText("Supra-Ilíaca");

        lblAxilarMedia.setText("Axilar-Média");

        lblDobraCoxa.setText("Coxa");

        javax.swing.GroupLayout pnlDobrasCutaneasLayout = new javax.swing.GroupLayout(pnlDobrasCutaneas);
        pnlDobrasCutaneas.setLayout(pnlDobrasCutaneasLayout);
        pnlDobrasCutaneasLayout.setHorizontalGroup(
            pnlDobrasCutaneasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDobrasCutaneasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDobrasCutaneasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDobrasCutaneasLayout.createSequentialGroup()
                        .addComponent(lblSubscapular)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSubscapular, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDobrasCutaneasLayout.createSequentialGroup()
                        .addComponent(lblTricipital)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTricipital, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDobrasCutaneasLayout.createSequentialGroup()
                        .addComponent(lblPeitoral)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPeitoral, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(144, 144, 144)
                .addGroup(pnlDobrasCutaneasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDobrasCutaneasLayout.createSequentialGroup()
                        .addComponent(lblAxilarMedia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAxilarMedia, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDobrasCutaneasLayout.createSequentialGroup()
                        .addComponent(lblSupraIliaca)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSupraIliaca, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDobrasCutaneasLayout.createSequentialGroup()
                        .addComponent(lblAbdominal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAbdominal, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblDobraCoxa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDobraCoxa, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlDobrasCutaneasLayout.setVerticalGroup(
            pnlDobrasCutaneasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDobrasCutaneasLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlDobrasCutaneasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSubscapular)
                    .addComponent(txtSubscapular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAxilarMedia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAxilarMedia)
                    .addComponent(lblDobraCoxa)
                    .addComponent(txtDobraCoxa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDobrasCutaneasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTricipital)
                    .addComponent(txtTricipital, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSupraIliaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSupraIliaca))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDobrasCutaneasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPeitoral)
                    .addComponent(txtPeitoral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAbdominal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAbdominal)))
        );

        pnlResistencia.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Resistência"));

        lblFlexoes.setText("Flexões");

        lblAbdominais.setText("Abdominais");

        javax.swing.GroupLayout pnlResistenciaLayout = new javax.swing.GroupLayout(pnlResistencia);
        pnlResistencia.setLayout(pnlResistenciaLayout);
        pnlResistenciaLayout.setHorizontalGroup(
            pnlResistenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlResistenciaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlResistenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFlexoes)
                    .addComponent(txtFlexoes, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(82, 82, 82)
                .addGroup(pnlResistenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAbdominais)
                    .addComponent(txtAbdominais, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlResistenciaLayout.setVerticalGroup(
            pnlResistenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlResistenciaLayout.createSequentialGroup()
                .addGroup(pnlResistenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlResistenciaLayout.createSequentialGroup()
                        .addComponent(lblFlexoes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFlexoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlResistenciaLayout.createSequentialGroup()
                        .addComponent(lblAbdominais)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAbdominais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        lblPeso.setText("Peso");

        lblPeso1.setText("Altura");

        lblFrequenciaCardiaca.setText("Frequência Cardiaca (repouso)");

        lblPressaoArterial.setText("Pressão Arterial (repouso)");

        lblAvaliador.setText("Avaliador Responsável");

        cboAvaliador.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lblDataAvaliacao.setText("Data da Avaliação");

        try {
            txtDataAvaliacao.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        lblDataValidade.setText("Data de Validade");

        try {
            txtDataValidade.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        btnCadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/edu/ifnmg/kelvin/projeto/apresentacao/Imagens/ok3.png"))); // NOI18N
        btnCadastrar.setText("Gerar Ficha de Avaliação");
        btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarActionPerformed(evt);
            }
        });

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/edu/ifnmg/kelvin/projeto/apresentacao/Imagens/cancel20.png"))); // NOI18N
        btnCancelar.setText("Fechar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlSubAvaliacaoLayout = new javax.swing.GroupLayout(pnlSubAvaliacao);
        pnlSubAvaliacao.setLayout(pnlSubAvaliacaoLayout);
        pnlSubAvaliacaoLayout.setHorizontalGroup(
            pnlSubAvaliacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSubAvaliacaoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSubAvaliacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlPerimetros, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlDobrasCutaneas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlResistencia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboAvaliador, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlSubAvaliacaoLayout.createSequentialGroup()
                        .addGroup(pnlSubAvaliacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblDataAvaliacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtDataAvaliacao, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(61, 61, 61)
                        .addGroup(pnlSubAvaliacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblDataValidade)
                            .addComponent(txtDataValidade, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(51, 51, 51)
                        .addComponent(btnCadastrar)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlSubAvaliacaoLayout.createSequentialGroup()
                        .addGroup(pnlSubAvaliacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlSubAvaliacaoLayout.createSequentialGroup()
                                .addComponent(lblPeso1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtAltura, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblAvaliador)
                            .addGroup(pnlSubAvaliacaoLayout.createSequentialGroup()
                                .addComponent(lblPeso)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblFrequenciaCardiaca)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFrequenciaCardiaca, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblPressaoArterial, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtPressaoArterial, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 24, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlSubAvaliacaoLayout.setVerticalGroup(
            pnlSubAvaliacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSubAvaliacaoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSubAvaliacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSubAvaliacaoLayout.createSequentialGroup()
                        .addGroup(pnlSubAvaliacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPeso)
                            .addComponent(txtPeso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSubAvaliacaoLayout.createSequentialGroup()
                        .addGroup(pnlSubAvaliacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblFrequenciaCardiaca)
                            .addComponent(txtFrequenciaCardiaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPressaoArterial)
                            .addComponent(txtPressaoArterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(1, 1, 1)))
                .addGroup(pnlSubAvaliacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPeso1)
                    .addComponent(txtAltura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlPerimetros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlDobrasCutaneas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlResistencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblAvaliador)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboAvaliador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlSubAvaliacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDataValidade)
                    .addGroup(pnlSubAvaliacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(pnlSubAvaliacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCadastrar)
                            .addComponent(btnCancelar))
                        .addGroup(pnlSubAvaliacaoLayout.createSequentialGroup()
                            .addComponent(lblDataAvaliacao)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(pnlSubAvaliacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtDataValidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtDataAvaliacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlAvaliacao.addTab("Ficha de Avaliação", pnlSubAvaliacao);

        jLabel1.setText("Nome do Atleta");

        cboAtletas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlAvaliacao)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(cboAtletas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboAtletas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlAvaliacao, javax.swing.GroupLayout.DEFAULT_SIZE, 532, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCoxaEsquerdaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCoxaEsquerdaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCoxaEsquerdaActionPerformed

    private void txtCoxaDireitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCoxaDireitaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCoxaDireitaActionPerformed

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed
        try {
            this.gerarRelatorio();
        } catch (SQLException ex) {
            //lançar exceção
        }
    }//GEN-LAST:event_btnCadastrarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JComboBox<String> cboAtletas;
    private javax.swing.JComboBox<String> cboAvaliador;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblAbdome;
    private javax.swing.JLabel lblAbdominais;
    private javax.swing.JLabel lblAbdominal;
    private javax.swing.JLabel lblAntebraco;
    private javax.swing.JLabel lblAvaliador;
    private javax.swing.JLabel lblAxilarMedia;
    private javax.swing.JLabel lblBraco;
    private javax.swing.JLabel lblCintura;
    private javax.swing.JLabel lblCoxa;
    private javax.swing.JLabel lblDataAvaliacao;
    private javax.swing.JLabel lblDataValidade;
    private javax.swing.JLabel lblDireito1;
    private javax.swing.JLabel lblDireito2;
    private javax.swing.JLabel lblDobraCoxa;
    private javax.swing.JLabel lblEsquerdo1;
    private javax.swing.JLabel lblEsquerdo2;
    private javax.swing.JLabel lblFlexoes;
    private javax.swing.JLabel lblFrequenciaCardiaca;
    private javax.swing.JLabel lblPanturrilha;
    private javax.swing.JLabel lblPeitoral;
    private javax.swing.JLabel lblPeso;
    private javax.swing.JLabel lblPeso1;
    private javax.swing.JLabel lblPressaoArterial;
    private javax.swing.JLabel lblQuadril;
    private javax.swing.JLabel lblSubscapular;
    private javax.swing.JLabel lblSupraIliaca;
    private javax.swing.JLabel lblTorax;
    private javax.swing.JLabel lblTricipital;
    private javax.swing.JTabbedPane pnlAvaliacao;
    private javax.swing.JPanel pnlDobrasCutaneas;
    private javax.swing.JPanel pnlPerimetros;
    private javax.swing.JPanel pnlResistencia;
    private javax.swing.JPanel pnlSubAvaliacao;
    private javax.swing.JTextField txtAbdome;
    private javax.swing.JTextField txtAbdominais;
    private javax.swing.JTextField txtAbdominal;
    private javax.swing.JTextField txtAltura;
    private javax.swing.JTextField txtAntebracoDireito;
    private javax.swing.JTextField txtAntebracoEsquerdo;
    private javax.swing.JTextField txtAxilarMedia;
    private javax.swing.JTextField txtBracoDireito;
    private javax.swing.JTextField txtBracoEsquerdo;
    private javax.swing.JTextField txtCintura;
    private javax.swing.JTextField txtCoxaDireita;
    private javax.swing.JTextField txtCoxaEsquerda;
    private javax.swing.JFormattedTextField txtDataAvaliacao;
    private javax.swing.JFormattedTextField txtDataValidade;
    private javax.swing.JTextField txtDobraCoxa;
    private javax.swing.JTextField txtFlexoes;
    private javax.swing.JTextField txtFrequenciaCardiaca;
    private javax.swing.JTextField txtPanturrilhaDireita;
    private javax.swing.JTextField txtPanturrilhaEsquerda;
    private javax.swing.JTextField txtPeitoral;
    private javax.swing.JTextField txtPeso;
    private javax.swing.JTextField txtPressaoArterial;
    private javax.swing.JTextField txtQuadril;
    private javax.swing.JTextField txtSubscapular;
    private javax.swing.JTextField txtSupraIliaca;
    private javax.swing.JTextField txtTorax;
    private javax.swing.JTextField txtTricipital;
    // End of variables declaration//GEN-END:variables
}
