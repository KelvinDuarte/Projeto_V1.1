/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.kelvin.projeto.apresentacao;



import br.edu.ifnmg.kelvin.projeto.entidade.Atleta;
import br.edu.ifnmg.kelvin.projeto.entidade.Mensalidade;
import br.edu.ifnmg.kelvin.projeto.negocio.AtletaBO;
import br.edu.ifnmg.kelvin.projeto.negocio.MensalidadeBO;
import java.sql.SQLException;
import java.text.ParseException;
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
public class VisualizarMensalidade extends javax.swing.JFrame {

    private List<Atleta> atletas;
    private List<Mensalidade> mensalidades;
    private Mensalidade mensalidadeEmEdicao;
    private SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
    /**
     * Creates new form VisualizarMensalidade
     */
    public VisualizarMensalidade(Mensalidade mensalidadeParaEdicao) throws ParseException {
        mensalidadeEmEdicao = mensalidadeParaEdicao;
        this.prepararTela();
        this.inicialiazarCamposTela();
    }

    private void prepararTela(){
        try {
            this.initComponents();
            this.carregarComboAtletas();
            setLocationRelativeTo(null);
        } catch (Exception e) {
            String mensagem = "Erro inesperado! Informe a mensagem de erro ao administrador do sistema.";
            mensagem += "\nMensagem de erro:\n" + e.getMessage();
            JOptionPane.showMessageDialog(this, mensagem, "Cadastro de Atletas", JOptionPane.ERROR_MESSAGE);
            this.dispose();
        }      
    } 
    
    private void recuperarCamposTela() throws ParseException{
        mensalidadeEmEdicao.setNome(cboAtletas.getSelectedItem().toString());
        mensalidadeEmEdicao.setDataEmissao(formatador.parse(txtDataEmissao.getText()));
        mensalidadeEmEdicao.setDataVencimento(formatador.parse(txtDataVencimento.getText()));
        mensalidadeEmEdicao.setValorAPagar(Double.parseDouble(txtValor.getText()));
        String status = cboStatus.getSelectedItem().toString();
        mensalidadeEmEdicao.setStatus(status);
    }
    
    private void inicialiazarCamposTela() throws ParseException{
        String codigo = Integer.toString(mensalidadeEmEdicao.getId_mensalidade());
        txtCodigo.setText(codigo);
        cboAtletas.setSelectedItem(mensalidadeEmEdicao.getNome());
        String dataEmissao = formatador.format(mensalidadeEmEdicao.getDataEmissao().getTime());
        txtDataEmissao.setText(dataEmissao);
        String dataVencimento = formatador.format(mensalidadeEmEdicao.getDataVencimento().getTime());
        txtDataVencimento.setText(dataVencimento);
        String valor = Double.toString(mensalidadeEmEdicao.getValorAPagar());
        txtValor.setText(valor);
        cboStatus.setSelectedItem(mensalidadeEmEdicao.getStatus());
    }    
    
    private void gerarBoleto() throws SQLException{
        MensalidadeBO mensalidadeBO = new MensalidadeBO();
        this.mensalidades = mensalidadeBO.buscarTodos();
        try{
            String arquivoRelatorio = System.getProperty("user.dir")+
                    "/relatorios/RelatorioBoletoPagamento.jasper";
            
            Map<String, Object> parametros = new HashMap<String, Object>();
            
            JRBeanCollectionDataSource fonteDados = new JRBeanCollectionDataSource(this.mensalidades);
            
            JasperPrint  relatorioGerado = JasperFillManager.fillReport(arquivoRelatorio, parametros, fonteDados);
            
            JasperViewer telaExibicaoRelatorio = new JasperViewer(relatorioGerado,false);
            telaExibicaoRelatorio.setTitle("Boleto de Pagamento");
            telaExibicaoRelatorio.setVisible(true);
        }catch(JRException ex){
            Logger.getLogger(VisualizarAvaliacao.class.getName()).log(Level.SEVERE, null, ex);
        }     
    }
    
    private void carregarComboAtletas() throws SQLException{
        AtletaBO atletaBO = new AtletaBO();
        try{
            atletas = atletaBO.buscarTodos();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro Desconhecido, Contate o Administrador do Sistema.");
        }
        cboAtletas.removeAllItems();
        for(Atleta atleta : atletas){
            cboAtletas.addItem(atleta.getNome());
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cboAtletas = new javax.swing.JComboBox<>();
        lblDataEmissao = new javax.swing.JLabel();
        lblDataVencimento = new javax.swing.JLabel();
        lblValor = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cboStatus = new javax.swing.JComboBox<>();
        lblCodigo = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        txtValor = new javax.swing.JTextField();
        txtDataEmissao = new javax.swing.JFormattedTextField();
        txtDataVencimento = new javax.swing.JFormattedTextField();
        btnGerarBoleto = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        lblMensalidade = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Nome do Atleta");

        cboAtletas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));

        lblDataEmissao.setText("Data Emissão");

        lblDataVencimento.setText("Data Vencimento");

        lblValor.setText("Valor à pagar");

        jLabel2.setText("Status");

        cboStatus.setEditable(true);
        cboStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pagamento em Débito", "Pagamento Aprovado" }));

        lblCodigo.setText("Código");

        txtCodigo.setEditable(false);
        txtCodigo.setEnabled(false);

        txtValor.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        try {
            txtDataEmissao.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            txtDataVencimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(60, 60, 60))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 499, Short.MAX_VALUE)
                        .addComponent(cboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cboAtletas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDataEmissao)
                            .addComponent(lblCodigo)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblValor)
                            .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtDataVencimento, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtDataEmissao, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblDataVencimento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCodigo)
                .addGap(3, 3, 3)
                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboAtletas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblDataEmissao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtDataEmissao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(lblDataVencimento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDataVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblValor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Mensalidades", jPanel1);

        btnGerarBoleto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/edu/ifnmg/kelvin/projeto/apresentacao/Imagens/ok3.png"))); // NOI18N
        btnGerarBoleto.setText("Gerar Boleto de Pagamento");
        btnGerarBoleto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGerarBoletoActionPerformed(evt);
            }
        });

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/edu/ifnmg/kelvin/projeto/apresentacao/Imagens/cancel20.png"))); // NOI18N
        btnCancelar.setText("Fechar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        lblMensalidade.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/edu/ifnmg/kelvin/projeto/apresentacao/Imagens/Mensalidades.fw.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnGerarBoleto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar)
                        .addGap(18, 18, 18)
                        .addComponent(lblMensalidade)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMensalidade, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGerarBoleto)
                    .addComponent(btnCancelar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGerarBoletoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGerarBoletoActionPerformed
        try {
            this.gerarBoleto();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro Desconhecido, Contate o Administrador do Sistema.");
        }
    }//GEN-LAST:event_btnGerarBoletoActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGerarBoleto;
    private javax.swing.JComboBox<String> cboAtletas;
    private javax.swing.JComboBox<String> cboStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblDataEmissao;
    private javax.swing.JLabel lblDataVencimento;
    private javax.swing.JLabel lblMensalidade;
    private javax.swing.JLabel lblValor;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JFormattedTextField txtDataEmissao;
    private javax.swing.JFormattedTextField txtDataVencimento;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables
}
