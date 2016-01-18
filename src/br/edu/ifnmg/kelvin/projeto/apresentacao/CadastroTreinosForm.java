/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.kelvin.projeto.apresentacao;

import br.edu.ifnmg.kelvin.projeto.entidade.Aparelho;
import br.edu.ifnmg.kelvin.projeto.entidade.Treino;
import br.edu.ifnmg.kelvin.projeto.negocio.AparelhoBO;
import br.edu.ifnmg.kelvin.projeto.negocio.TreinoBO;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author KELVIN
 */
public class CadastroTreinosForm extends javax.swing.JFrame {
    
    private int status;
    private List<Aparelho> aparelhos;
    private Treino treinoEmEdicao;
    
    /**
     * Creates new form CadastroTreinosForm
     */
    public CadastroTreinosForm(){
        this.treinoEmEdicao = new Treino();
        this.prepararTela();         
    }
    
    public CadastroTreinosForm(Treino treinoParaEdicao){
        this.treinoEmEdicao = treinoParaEdicao;
        this.prepararTela();
        this.inicializarCamposTela();
        
    }

    private void recuperarCamposTela(){     
        treinoEmEdicao = new Treino();
        String tipo = cboTipo.getSelectedItem().toString();
        treinoEmEdicao.setTipo(tipo);
        String categoria = cboCategoria.getSelectedItem().toString();
        treinoEmEdicao.setCategoria(categoria);
        String aparelho01 = cboAparelho1.getSelectedItem().toString();
        treinoEmEdicao.setAparelho01(aparelho01);
        String aparelho02 = cboAparelho2.getSelectedItem().toString();
        treinoEmEdicao.setAparelho02(aparelho02);
        String aparelho03 = cboAparelho3.getSelectedItem().toString();
        treinoEmEdicao.setAparelho03(aparelho03);
        String aparelho04 = cboAparelho4.getSelectedItem().toString();
        treinoEmEdicao.setAparelho04(aparelho04);
        String aparelho05 = cboAparelho5.getSelectedItem().toString();
        treinoEmEdicao.setAparelho05(aparelho05);
        String aparelho06 = cboAparelho6.getSelectedItem().toString();
        treinoEmEdicao.setAparelho06(aparelho06);
        String aparelho07 = cboAparelho7.getSelectedItem().toString();
        treinoEmEdicao.setAparelho07(aparelho07);      
    }
    
    private void inicializarCamposTela(){       
        String codigo = Integer.toString(treinoEmEdicao.getId_treino());
        txtCodigo.setText(codigo);
        cboTipo.setSelectedItem(treinoEmEdicao.getTipo());
        cboCategoria.setSelectedItem(treinoEmEdicao.getCategoria());
        cboAparelho1.setSelectedItem(treinoEmEdicao.getAparelho01());
        cboAparelho2.setSelectedItem(treinoEmEdicao.getAparelho02());
        cboAparelho3.setSelectedItem(treinoEmEdicao.getAparelho03());
        cboAparelho4.setSelectedItem(treinoEmEdicao.getAparelho04());
        cboAparelho5.setSelectedItem(treinoEmEdicao.getAparelho05());
        cboAparelho6.setSelectedItem(treinoEmEdicao.getAparelho06());
        cboAparelho7.setSelectedItem(treinoEmEdicao.getAparelho07());             
    }
    
    private void prepararTela(){
        try{
            this.initComponents();
            this.carregarComboAparelhos();
            setLocationRelativeTo(null);
            } catch (Exception e) {
            String mensagem = "Erro inesperado! Informe a mensagem de erro ao administrador do sistema.";
            mensagem += "\nMensagem de erro:\n" + e.getMessage();
            JOptionPane.showMessageDialog(this, mensagem, "Cadastro de Treinos", JOptionPane.ERROR_MESSAGE);
            this.dispose();
        }
    }
    
    private void cadastrarTreino(){
        try{
            this.recuperarCamposTela();
            TreinoBO treinoBO = new TreinoBO();
            treinoBO.cadastrarTreino(treinoEmEdicao);
            JOptionPane.showMessageDialog(this, "Treino cadastrado com sucesso!", "Cadastro de Treinos", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        }catch(Exception e){
            String mensagem = "Erro inesperado! Informe a mensagem de erro ao administrador do sistema.";
            mensagem += "\nMensagem de erro:\n" + e.getMessage();
            JOptionPane.showMessageDialog(this, mensagem, "Cadastro de Treinos", JOptionPane.ERROR_MESSAGE);            
        }       
    }

    private void editarTreino(){
        try{
            this.recuperarCamposTela();
            TreinoBO treinoBO = new TreinoBO();
            treinoBO.editarTreino(treinoEmEdicao);
            JOptionPane.showMessageDialog(this, "Treino cadastrado com sucesso!", "Cadastro de Personal Trainers", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        }catch(Exception e){
            String mensagem = "Erro inesperado! Informe a mensagem de erro ao administrador do sistema.";
            mensagem += "\nMensagem de erro:\n" + e.getMessage();
            JOptionPane.showMessageDialog(this, mensagem, "Cadastro de Treinos", JOptionPane.ERROR_MESSAGE);            
        }       
    } 
    
    private void carregarComboAparelhos() throws SQLException{
        AparelhoBO aparelhoBO = new AparelhoBO();
        try{
             aparelhos = aparelhoBO.buscarTodos();
        }catch(SQLException ex){           
        
        }
        for(Aparelho aparelho : aparelhos){
            cboAparelho1.addItem(aparelho.getNome());
            cboAparelho2.addItem(aparelho.getNome());
            cboAparelho3.addItem(aparelho.getNome());
            cboAparelho4.addItem(aparelho.getNome());
            cboAparelho5.addItem(aparelho.getNome());
            cboAparelho6.addItem(aparelho.getNome());
            cboAparelho7.addItem(aparelho.getNome());           
        }
    }
    
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;    
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    
    //Getter e Setter para fazer verificação se é para Cadastrar ou Editar    

     
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lblTipo = new javax.swing.JLabel();
        cboTipo = new javax.swing.JComboBox<>();
        lblCategoria = new javax.swing.JLabel();
        cboCategoria = new javax.swing.JComboBox<>();
        lblCodigo = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        lblAparelho1 = new javax.swing.JLabel();
        cboAparelho1 = new javax.swing.JComboBox<>();
        lblAparelho2 = new javax.swing.JLabel();
        cboAparelho2 = new javax.swing.JComboBox<>();
        lblNomeAtleta4 = new javax.swing.JLabel();
        cboAparelho3 = new javax.swing.JComboBox<>();
        lblAparelho4 = new javax.swing.JLabel();
        cboAparelho4 = new javax.swing.JComboBox<>();
        lblAparelho5 = new javax.swing.JLabel();
        cboAparelho5 = new javax.swing.JComboBox<>();
        lblAparelho6 = new javax.swing.JLabel();
        cboAparelho6 = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        lblAparelho7 = new javax.swing.JLabel();
        cboAparelho7 = new javax.swing.JComboBox<>();
        btnCadastrar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro - Treinos");
        setExtendedState(6);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Definição"));

        lblTipo.setText("Tipo");

        cboTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Peito e Tríceps", "Costa e Bíceps", "Ombro, Trapézio", "Pernas" }));

        lblCategoria.setText("Categoria");

        cboCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Iniciante", "Intermediário", "Avançado" }));

        lblCodigo.setText("Codigo");

        txtCodigo.setEditable(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboCategoria, 0, 722, Short.MAX_VALUE)
                    .addComponent(cboTipo, 0, 722, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCategoria)
                            .addComponent(lblTipo)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblCodigo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCodigo)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTipo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCategoria)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Anaeróbicos"));

        lblAparelho1.setText("Aparelho 1");

        cboAparelho1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));

        lblAparelho2.setText("Aparelho 2");

        cboAparelho2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));

        lblNomeAtleta4.setText("Aparelho 3");

        cboAparelho3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));

        lblAparelho4.setText("Aparelho 4");

        cboAparelho4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));

        lblAparelho5.setText("Aparelho 5");

        cboAparelho5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));

        lblAparelho6.setText("Aparelho 6");

        cboAparelho6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboAparelho1, 0, 722, Short.MAX_VALUE)
                    .addComponent(cboAparelho2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboAparelho3, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboAparelho4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblAparelho1)
                            .addComponent(lblAparelho2)
                            .addComponent(lblNomeAtleta4)
                            .addComponent(lblAparelho4)
                            .addComponent(lblAparelho5)
                            .addComponent(lblAparelho6))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(cboAparelho5, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboAparelho6, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(lblAparelho1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboAparelho1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblAparelho2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboAparelho2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNomeAtleta4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboAparelho3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblAparelho4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cboAparelho4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblAparelho5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboAparelho5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblAparelho6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboAparelho6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Aeróbicos"));

        lblAparelho7.setText("Aparelho 7");

        cboAparelho7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lblAparelho7)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(cboAparelho7, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(lblAparelho7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboAparelho7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Treinos", jPanel1);

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
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCadastrar)
                    .addComponent(btnCancelar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed
        if(this.status == 1)
        {
            this.status = 0;
            this.cadastrarTreino();
            
        }else if(this.status == 2)
        {
            this.status = 0;
            this.editarTreino();
        }
    }//GEN-LAST:event_btnCadastrarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CadastroTreinosForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroTreinosForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroTreinosForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroTreinosForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JComboBox<String> cboAparelho1;
    private javax.swing.JComboBox<String> cboAparelho2;
    private javax.swing.JComboBox<String> cboAparelho3;
    private javax.swing.JComboBox<String> cboAparelho4;
    private javax.swing.JComboBox<String> cboAparelho5;
    private javax.swing.JComboBox<String> cboAparelho6;
    private javax.swing.JComboBox<String> cboAparelho7;
    private javax.swing.JComboBox<String> cboCategoria;
    private javax.swing.JComboBox<String> cboTipo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblAparelho1;
    private javax.swing.JLabel lblAparelho2;
    private javax.swing.JLabel lblAparelho4;
    private javax.swing.JLabel lblAparelho5;
    private javax.swing.JLabel lblAparelho6;
    private javax.swing.JLabel lblAparelho7;
    private javax.swing.JLabel lblCategoria;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblNomeAtleta4;
    private javax.swing.JLabel lblTipo;
    private javax.swing.JTextField txtCodigo;
    // End of variables declaration//GEN-END:variables
}
