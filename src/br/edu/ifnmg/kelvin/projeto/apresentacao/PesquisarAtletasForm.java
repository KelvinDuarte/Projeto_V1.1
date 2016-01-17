/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.kelvin.projeto.apresentacao;

import br.edu.ifnmg.kelvin.projeto.entidade.Atleta;
import br.edu.ifnmg.kelvin.projeto.negocio.AtletaBO;
import java.awt.Dimension;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author KELVIN
 */
public class PesquisarAtletasForm extends javax.swing.JInternalFrame {

    private CadastroAtletasForm editarAtletaForm;
    private CadastroAtletasForm novoAtletaForm;
    private List<Atleta> atletas;
    /**
     * Creates new form PesquisarAtletas
     */
    public PesquisarAtletasForm() throws SQLException{
        this.prepararTela();
    }

    private void prepararTela() throws SQLException{
        this.initComponents();
        this.carregarComboAtletas();
        this.carregarTabelaAtleta();  
        
    }    
    
    public void setPosicao() {
        Dimension dimensao = this.getDesktopPane().getSize();
        this.setLocation((dimensao.width - this.getSize().width) / 2, (dimensao.height - this.getSize().height) / 2); 
    }
    
    public void exibirTelaCadastroAtletas(){
        CadastroAtletasForm cadastroAtletasForm = new CadastroAtletasForm();
        cadastroAtletasForm.setVisible(true);
        cadastroAtletasForm.setStatus(1);
    }
     private void editarAtleta(){
        int linhaSelecionada = tblResultado.getSelectedRow();
        if(linhaSelecionada != -1)
        {
           Atleta atletaSelecionado = atletas.get(linhaSelecionada);
           CadastroAtletasForm cadastroAtletasForm = new CadastroAtletasForm(atletaSelecionado);
           cadastroAtletasForm.setVisible(true);
           cadastroAtletasForm.setStatus(2);          
        }
        else
        {
            String mesnagem = "Nenhum Personal Trainer Selecionado.";
            JOptionPane.showMessageDialog(this, mesnagem, "Alteração de Personal Trainer", JOptionPane.INFORMATION_MESSAGE);     
        }
    } 
     
    public void excluirAtleta(){
        try{
            int linhaSelecionada = tblResultado.getSelectedRow();
            if (linhaSelecionada != 1) {
                Atleta atletaSelecionado = atletas.get(linhaSelecionada);
                int resposta;
                String mensagem = "Deseja excluir o Atleta? "
                        + atletaSelecionado.getNome() + " (ID: "
                        + atletaSelecionado.getId_atleta()+ ")?";
                String titulo = "Exclusão de Atletas";
                resposta = JOptionPane.showConfirmDialog(this, mensagem, titulo, JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.YES_OPTION) {
                    AtletaBO AtletaBO = new AtletaBO();
                    AtletaBO.excluirAtleta(atletaSelecionado.getId_atleta());
                    String mensagemSucesso = "Atleta" + atletaSelecionado.getNome() + " (ID:"
                            + atletaSelecionado.getId_personal() + ")"
                            + "excluido com sucesso.";
                    JOptionPane.showConfirmDialog(this, mensagem, "Exclusão de Atletas", JOptionPane.INFORMATION_MESSAGE);
                    this.carregarTabelaAtleta();
                } else {
                    String mesnagem = "Nenhum Atleta selecinado.";
                    JOptionPane.showMessageDialog(this, mesnagem, "Exclusão de Atletas", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (Exception e) {
            String mensagem = "Erro inesperado! Informe a mensagem de erro ao administrador do sistema.";
            mensagem += "\nMensagem de erro:\n" + e.getMessage();
            JOptionPane.showMessageDialog(this, mensagem, "Personal Trainers Cadastrados", JOptionPane.ERROR_MESSAGE);
            this.dispose();
        }         
     }  

    private void carregarComboAtletas() throws SQLException{
        AtletaBO atletaBO = new AtletaBO();
        try{
            atletas = atletaBO.buscarTodos();
        }catch(SQLException ex){
        
        }
        cboNome.removeAllItems();
        for(Atleta atleta : atletas){
            cboNome.addItem(atleta.getNome());
        }
    }    
    
    public void pesquisar() throws SQLException{
        if(cboNome.getSelectedItem().toString().equals(" ") && txtCPF.getText().equals("   .   .   -  ")){
            JOptionPane.showMessageDialog(null, "Nenhum Campo Preenchido!");
            this.carregarTabelaAtleta();
        }else if(cboNome.getSelectedItem().toString() != " " && txtCPF.getText().equals("   .   .   -  ")){
            this.carregarTabelaAtletaPorNome(cboNome.getSelectedItem().toString());
        }else if(cboNome.getSelectedItem().toString().equals(" ") && txtCPF.getText() != "   .   .   -  "){
            this.carregarTabelaAtletaPorCpf(txtCPF.getText());           
        }else{
            this.carregarTabelaAtletaPorNomeCpf(cboNome.getSelectedItem().toString(),txtCPF.getText());
        }        
    }
    
    public void carregarTabelaAtleta() throws SQLException{
        AtletaBO atletaBO = new AtletaBO();
        this.atletas = atletaBO.buscarTodos();      
        ModeloTabelaAtleta modeloTabelaAtleta = new ModeloTabelaAtleta(){};
        tblResultado.setModel(modeloTabelaAtleta);     
    }
    
    public void carregarTabelaAtletaPorNome(String nome) throws SQLException{
        AtletaBO atletaBO = new AtletaBO();
        this.atletas = atletaBO.carregarTabelaAtletaPorNome(nome);      
        ModeloTabelaAtleta modeloTabelaAtleta = new ModeloTabelaAtleta(){};
        tblResultado.setModel(modeloTabelaAtleta);     
    }    

    public void carregarTabelaAtletaPorCpf(String cpf) throws SQLException{
        AtletaBO atletaBO = new AtletaBO();
        this.atletas = atletaBO.carregarTabelaAtletaPorCpf(cpf);      
        ModeloTabelaAtleta modeloTabelaAtleta = new ModeloTabelaAtleta(){};
        tblResultado.setModel(modeloTabelaAtleta);     
    }
    
    public void carregarTabelaAtletaPorNomeCpf(String Nome, String cpf) throws SQLException{
        AtletaBO atletaBO = new AtletaBO();
        this.atletas = atletaBO.carregarTabelaAtletaPorNomeCpf(Nome, cpf);      
        ModeloTabelaAtleta modeloTabelaAtleta = new ModeloTabelaAtleta(){};
        tblResultado.setModel(modeloTabelaAtleta);     
    }    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlResultado = new javax.swing.JPanel();
        btnExcluir = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblResultado = new javax.swing.JTable();
        btnAtualizar = new javax.swing.JButton();
        pnlFiltro = new javax.swing.JPanel();
        lblCPF = new javax.swing.JLabel();
        txtCPF = new javax.swing.JFormattedTextField();
        lblNomeAtleta = new javax.swing.JLabel();
        btnPesquisar = new javax.swing.JButton();
        cboNome = new javax.swing.JComboBox<>();

        setClosable(true);
        setTitle("Pesquisar - Atletas");

        pnlResultado.setBorder(javax.swing.BorderFactory.createTitledBorder("Resultado"));

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/edu/ifnmg/kelvin/projeto/apresentacao/Imagens/delete16.png"))); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/edu/ifnmg/kelvin/projeto/apresentacao/Imagens/write62.png"))); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/edu/ifnmg/kelvin/projeto/apresentacao/Imagens/add182.png"))); // NOI18N
        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        tblResultado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblResultado);

        btnAtualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/edu/ifnmg/kelvin/projeto/apresentacao/Imagens/arrow95.png"))); // NOI18N
        btnAtualizar.setText("Atualizar");
        btnAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlResultadoLayout = new javax.swing.GroupLayout(pnlResultado);
        pnlResultado.setLayout(pnlResultadoLayout);
        pnlResultadoLayout.setHorizontalGroup(
            pnlResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlResultadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE)
                    .addGroup(pnlResultadoLayout.createSequentialGroup()
                        .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlResultadoLayout.setVerticalGroup(
            pnlResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlResultadoLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pnlFiltro.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtro"));

        lblCPF.setText("CPF:");

        try {
            txtCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCPF.setText("");

        lblNomeAtleta.setText("Nome do Atleta");

        btnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/edu/ifnmg/kelvin/projeto/apresentacao/Imagens/search102.png"))); // NOI18N
        btnPesquisar.setText("Pesquisar");
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });

        cboNome.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));

        javax.swing.GroupLayout pnlFiltroLayout = new javax.swing.GroupLayout(pnlFiltro);
        pnlFiltro.setLayout(pnlFiltroLayout);
        pnlFiltroLayout.setHorizontalGroup(
            pnlFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFiltroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFiltroLayout.createSequentialGroup()
                        .addGroup(pnlFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNomeAtleta)
                            .addComponent(btnPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCPF)
                            .addComponent(txtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 418, Short.MAX_VALUE))
                    .addComponent(cboNome, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlFiltroLayout.setVerticalGroup(
            pnlFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFiltroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCPF)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNomeAtleta)
                .addGap(1, 1, 1)
                .addComponent(cboNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnPesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlResultado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlFiltro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlResultado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        this.excluirAtleta();
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        this.editarAtleta();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        this.exibirTelaCadastroAtletas();
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        try {
            this.pesquisar();
        } catch (SQLException ex) {
            
        }
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void btnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarActionPerformed
        try {
            this.carregarTabelaAtleta();
        } catch (SQLException ex) {
            
        }
    }//GEN-LAST:event_btnAtualizarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtualizar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JComboBox<String> cboNome;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCPF;
    private javax.swing.JLabel lblNomeAtleta;
    private javax.swing.JPanel pnlFiltro;
    private javax.swing.JPanel pnlResultado;
    private javax.swing.JTable tblResultado;
    private javax.swing.JFormattedTextField txtCPF;
    // End of variables declaration//GEN-END:variables
private abstract class ModeloTabelaAtleta extends AbstractTableModel{
        @Override
        public String getColumnName(int coluna) {
            if(coluna == 0){
                return "ID";
            }else if(coluna == 1){
                return "Nome";
            }else{
                return "Data Nascimento";
            }
        }

        @Override
        public int getRowCount(){
            return atletas.size();
        }
        
        @Override
        public int getColumnCount(){
            return 3;
        }
        
        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Atleta atleta = atletas.get(rowIndex);
            if(columnIndex == 0){
                return atleta.getId_atleta();
            }else if (columnIndex == 1) {
                return atleta.getNome();
            }else{
                return atleta.getDataNascimento();
            }
        }
    }
}
