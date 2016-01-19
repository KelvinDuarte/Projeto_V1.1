/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.kelvin.projeto.apresentacao;

import br.edu.ifnmg.kelvin.projeto.entidade.Aparelho;
import br.edu.ifnmg.kelvin.projeto.negocio.AparelhoBO;
import java.text.ParseException;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author KELVIN
 */
public class CadastroAparelhosForm extends javax.swing.JFrame {
   private List<Aparelho> aparelhos;
   private Aparelho aparelhoEmEdicao;
   private int status;
   private PesquisarAparelhosForm pesquisarAparelhoForm;
    /**
     * Creates new form CadastroAparelhosForm
     */
    
   public CadastroAparelhosForm() {
        this.aparelhoEmEdicao = new Aparelho();
        this.prepararTela();             
    }
    
    public CadastroAparelhosForm(Aparelho aparelhoParaEdicao){
        this.aparelhoEmEdicao = aparelhoParaEdicao;
        this.prepararTela(); 
        this.inicializarCamposTela();  
    }
    
    public void inicializarCamposTela(){
        String codigo = Integer.toString(aparelhoEmEdicao.getId_aparelho());
        txtCodigo.setText(codigo);
        txtNome.setText(aparelhoEmEdicao.getNome());
        String quantidade = Integer.toString(aparelhoEmEdicao.getQuantidade());
        txtQuantidade.setText(quantidade);
        cboTipo.setSelectedItem(aparelhoEmEdicao.getTipo());
        cboCategoria.setSelectedItem(aparelhoEmEdicao.getCategoria());
    }
    
    public void cadastrarAparelho(){
        try{
            this.recuperarCamposTela();
            AparelhoBO aparelhoBO = new AparelhoBO();
            aparelhoBO.cadastrarAparelho(aparelhoEmEdicao);
            JOptionPane.showMessageDialog(this, "Aparelho cadastrado com sucesso!", "Cadastro de Aparelhos", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        }catch(Exception e){
            String mensagem = "Erro inesperado! Informe a mensagem de erro ao administrador do sistema.";
            mensagem += "\nMensagem de erro:\n" + e.getMessage();
            JOptionPane.showMessageDialog(this, mensagem, "Cadastro de Aparelhos", JOptionPane.ERROR_MESSAGE);            
        }
    }
    
    public void editarAparelho(){
      try{
          this.recuperarCamposTela(); 
          AparelhoBO aparelhoBO = new AparelhoBO();
          aparelhoBO.editarAparelho(aparelhoEmEdicao); 
          JOptionPane.showMessageDialog(this, "Aparelho modificado com sucesso!", "Cadastro de Aparelhos", JOptionPane.INFORMATION_MESSAGE);
          this.dispose();    
      }catch(Exception e){
            String mensagem = "Erro inesperado! Informe a mensagem de erro ao administrador do sistema.";
            mensagem += "\nMensagem de erro:\n" + e.getMessage();
            JOptionPane.showMessageDialog(this, mensagem, "Cadastro de Aparelhos", JOptionPane.ERROR_MESSAGE);         
      }
    }
    private void prepararTela(){
        try{
            this.initComponents();
            setLocationRelativeTo(null); 
            } catch (Exception e) {
            String mensagem = "Erro inesperado! Informe a mensagem de erro ao administrador do sistema.";
            mensagem += "\nMensagem de erro:\n" + e.getMessage();
            JOptionPane.showMessageDialog(this, mensagem, "Cadastro de Aparelhos", JOptionPane.ERROR_MESSAGE);
            this.dispose();
        }
    }
    
    public void recuperarCamposTela() throws ParseException{
        aparelhoEmEdicao.setNome(txtNome.getText());
        int quantidade = Integer.parseInt(txtQuantidade.getText());
        aparelhoEmEdicao.setQuantidade(quantidade);
        String tipo = cboTipo.getSelectedItem().toString();
        aparelhoEmEdicao.setTipo(tipo);
        String categoria = cboCategoria.getSelectedItem().toString();
        aparelhoEmEdicao.setCategoria(categoria);       
    }
    
    public void limparCamposTela(){
        txtNome.setText("");
        txtQuantidade.setText("");
        cboTipo.setSelectedItem("Selecionar");
        cboCategoria.setSelectedItem("Selecionar");       
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        pnlAparelhos = new javax.swing.JPanel();
        lblCodigo = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        lblNome = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        txtQuantidade = new javax.swing.JTextField();
        lblQuantidade = new javax.swing.JLabel();
        lblTipo = new javax.swing.JLabel();
        cboTipo = new javax.swing.JComboBox<>();
        lblCategoria = new javax.swing.JLabel();
        cboCategoria = new javax.swing.JComboBox<>();
        btnCadastrar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        lblAparelhos = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro - Aparelhos");

        lblCodigo.setText("Codigo");

        txtCodigo.setEditable(false);

        lblNome.setText("Nome");

        lblQuantidade.setText("Quantidade");

        lblTipo.setText("Tipo");

        cboTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecionar", "Aeróbico", "Anaeróbico" }));

        lblCategoria.setText("Categoria");

        cboCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecionar", "Iniciante", "Intermediário", "Avançado" }));

        javax.swing.GroupLayout pnlAparelhosLayout = new javax.swing.GroupLayout(pnlAparelhos);
        pnlAparelhos.setLayout(pnlAparelhosLayout);
        pnlAparelhosLayout.setHorizontalGroup(
            pnlAparelhosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAparelhosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAparelhosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboTipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNome)
                    .addGroup(pnlAparelhosLayout.createSequentialGroup()
                        .addGroup(pnlAparelhosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTipo)
                            .addComponent(lblCategoria)
                            .addComponent(lblCodigo)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblQuantidade)
                            .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNome))
                        .addGap(0, 394, Short.MAX_VALUE))
                    .addComponent(cboCategoria, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlAparelhosLayout.setVerticalGroup(
            pnlAparelhosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAparelhosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCodigo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblNome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblQuantidade)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblTipo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(lblCategoria)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(54, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Aparelhos", pnlAparelhos);

        btnCadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/edu/ifnmg/kelvin/projeto/apresentacao/Imagens/ok3.png"))); // NOI18N
        btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarActionPerformed(evt);
            }
        });

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/edu/ifnmg/kelvin/projeto/apresentacao/Imagens/cancel20.png"))); // NOI18N
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        lblAparelhos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/edu/ifnmg/kelvin/projeto/apresentacao/Imagens/Aparelhos.fw.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCadastrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar)
                        .addGap(80, 80, 80)
                        .addComponent(lblAparelhos)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnCadastrar)
                        .addComponent(btnCancelar))
                    .addComponent(lblAparelhos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed
    if(this.status == 1){
            this.status = 0;
            this.cadastrarAparelho();        
        }else if(this.status == 2){
            this.status = 0;
            this.editarAparelho();         
        }
    }//GEN-LAST:event_btnCadastrarActionPerformed
    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JComboBox<String> cboCategoria;
    private javax.swing.JComboBox<String> cboTipo;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblAparelhos;
    private javax.swing.JLabel lblCategoria;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblQuantidade;
    private javax.swing.JLabel lblTipo;
    private javax.swing.JPanel pnlAparelhos;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtQuantidade;
    // End of variables declaration//GEN-END:variables
}
