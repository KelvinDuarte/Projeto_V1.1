/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.kelvin.projeto.apresentacao;

import br.edu.ifnmg.kelvin.projeto.entidade.Aparelho;
import br.edu.ifnmg.kelvin.projeto.excecao.PesquisaInvalidaException;
import br.edu.ifnmg.kelvin.projeto.negocio.AparelhoBO;
import java.awt.Dimension;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author KELVIN
 */
public class PesquisarAparelhosForm extends javax.swing.JInternalFrame {

    private CadastroAparelhosForm editarAparelhoForm;
    private CadastroAparelhosForm novoAparelhoForm;
    private List<Aparelho> aparelhos;
    
    /**
     * Creates new form PesquisarAparelhosForm
     */
    public PesquisarAparelhosForm() throws SQLException {
        this.prepararTela();
        
    } 
    // Metodo para iniciar o Form centralizdo na tela
    public void setPosicao() {
        Dimension dimensao = this.getDesktopPane().getSize();
        this.setLocation((dimensao.width - this.getSize().width) / 2, (dimensao.height - this.getSize().height) / 2); 
    }
    
    private void prepararTela() throws SQLException{
        this.initComponents();
        this.carregarTabelaAparelhos();
    }
    
    private void editarAparelho(){
        int linhaSelecionada = tblResultado.getSelectedRow();
        if(linhaSelecionada != -1)
        {
           Aparelho aparelhoSelecionado = aparelhos.get(linhaSelecionada);         
           CadastroAparelhosForm cadastroAparelhosForm = new CadastroAparelhosForm(aparelhoSelecionado);
           cadastroAparelhosForm.setVisible(true);
           cadastroAparelhosForm.setStatus(2);
        }
        else
        {
            String mesnagem = "Nenhum Aparelho Selecionado.";
            JOptionPane.showMessageDialog(this, mesnagem, "Alteração de Aparelhos", JOptionPane.INFORMATION_MESSAGE);    
        }
    }
    
    private void exibirTelaCadastroAparelhos(){
        CadastroAparelhosForm cadastroAparelhosForm = new CadastroAparelhosForm();
        cadastroAparelhosForm.setVisible(true);
        cadastroAparelhosForm.setStatus(1);
    }
    
    private void excluirAparelho(){
        try
        {
            int linhaSelecionada= tblResultado.getSelectedRow();
            if(linhaSelecionada != 1)
            {
                Aparelho aparelhoSelecionado = aparelhos.get(linhaSelecionada);
                int resposta;
                String mensagem = "Deseja excluir o Aparelho " +
                        aparelhoSelecionado.getNome()+ " (ID: "+
                        aparelhoSelecionado.getId_aparelho()+")?";
                        String titulo = "Exclusão de Aparelho";
                        resposta = JOptionPane.showConfirmDialog(this, mensagem, titulo, JOptionPane.YES_NO_OPTION);
                if(resposta == JOptionPane.YES_OPTION)
                {
                    AparelhoBO aparelhoBO = new AparelhoBO();
                    aparelhoBO.excluirAparelho(aparelhoSelecionado.getId_aparelho());
                    
                    String mensagemSucesso = "Aluno "+aparelhoSelecionado.getNome()+" (Matricula:" +
                            aparelhoSelecionado.getId_aparelho()+ ")"
                            + "excluido com sucesso.";
                    JOptionPane.showConfirmDialog(this, mensagem,"Exclusao de Aluno", JOptionPane.INFORMATION_MESSAGE);
                    this.carregarTabelaAparelhos();                      
                }
                else
                {
                    String mesnagem = "Nenhum Aparelho Selecionado.";
                    JOptionPane.showMessageDialog(this, mesnagem, "Exclusão de Aluno", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }catch (Exception e) {
            String mensagem = "Erro inesperado! Informe a mensagem de erro ao administrador do sistema.";
            mensagem += "\nMensagem de erro:\n" + e.getMessage();
            JOptionPane.showMessageDialog(this, mensagem, "Alunos Cadastrados", JOptionPane.ERROR_MESSAGE);
            this.dispose();   
        }            
    }
    
    public void pesquisar() throws SQLException{
        try{
            if(cboTipo.getSelectedItem().toString().equals("Selecionar") && cboCategoria.getSelectedItem().toString().equals("Selecionar")){
                this.carregarTabelaAparelhos();
                String msg = "Pesquisa em Branco!";
                throw new PesquisaInvalidaException(msg);              
            }else if(cboTipo.getSelectedItem().toString() != "Selecionar" && cboCategoria.getSelectedItem().toString().equals("Selecionar")){
                this.carregarTabelaAparelhosPorTipo(cboTipo.getSelectedItem().toString());
            }else if(cboTipo.getSelectedItem().toString().equals("Selecionar") && cboCategoria.getSelectedItem().toString() != "Selecionar"){
                this.carregarTabelaAparelhosPorCategoria(cboCategoria.getSelectedItem().toString());           
            }else{
                this.carregarTabelaAparelhosPorTipoCategoria(cboTipo.getSelectedItem().toString(),cboCategoria.getSelectedItem().toString());
            }
        }catch(PesquisaInvalidaException e){
            String mensagem = "Falha na pesquisa!\n" + e.getMessage();
            JOptionPane.showMessageDialog(this, mensagem, "Pesquisar Aparelhos", JOptionPane.ERROR_MESSAGE);
        }
    }    

    public void carregarTabelaAparelhos() throws SQLException{
        AparelhoBO aparelhoBO = new AparelhoBO();
        this.aparelhos = aparelhoBO.buscarTodos();     
        ModeloTabelaAparelho modeloTabelaAparelho = new ModeloTabelaAparelho() {};
        tblResultado.setModel(modeloTabelaAparelho);
    }

    public void carregarTabelaAparelhosPorTipo(String tipo) throws SQLException{
        AparelhoBO aparelhoBO = new AparelhoBO();
        this.aparelhos = aparelhoBO.carregarTabelaAparelhosPorTipo(tipo);     
        ModeloTabelaAparelho modeloTabelaAparelho = new ModeloTabelaAparelho() {};
        tblResultado.setModel(modeloTabelaAparelho);
    }    

    public void carregarTabelaAparelhosPorCategoria(String categoria) throws SQLException{
        AparelhoBO aparelhoBO = new AparelhoBO();
        this.aparelhos = aparelhoBO.carregarTabelaAparelhosPorCategoria(categoria);     
        ModeloTabelaAparelho modeloTabelaAparelho = new ModeloTabelaAparelho() {};
        tblResultado.setModel(modeloTabelaAparelho);
    }

    public void carregarTabelaAparelhosPorTipoCategoria(String tipo, String categoria) throws SQLException{
        AparelhoBO aparelhoBO = new AparelhoBO();
        this.aparelhos = aparelhoBO.carregarTabelaAparelhosPorTipoCategoria(tipo,categoria);     
        ModeloTabelaAparelho modeloTabelaAparelho = new ModeloTabelaAparelho() {};
        tblResultado.setModel(modeloTabelaAparelho);
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
        jButton1 = new javax.swing.JButton();
        lblTipo = new javax.swing.JLabel();
        cboTipo = new javax.swing.JComboBox<>();
        lblCategoria = new javax.swing.JLabel();
        cboCategoria = new javax.swing.JComboBox<>();

        setClosable(true);
        setTitle("Pesquisar - Aparelhos");

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(82, 82, 82))
        );

        pnlFiltro.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtro"));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/edu/ifnmg/kelvin/projeto/apresentacao/Imagens/search102.png"))); // NOI18N
        jButton1.setText("Pesquisar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        lblTipo.setText("Tipo");

        cboTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecionar", "Aeróbico", "Anaeróbico" }));

        lblCategoria.setText("Categoria");

        cboCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecionar", "Iniciante", "Intermediário", "Avançado" }));

        javax.swing.GroupLayout pnlFiltroLayout = new javax.swing.GroupLayout(pnlFiltro);
        pnlFiltro.setLayout(pnlFiltroLayout);
        pnlFiltroLayout.setHorizontalGroup(
            pnlFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFiltroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboTipo, 0, 574, Short.MAX_VALUE)
                    .addComponent(cboCategoria, 0, 574, Short.MAX_VALUE)
                    .addGroup(pnlFiltroLayout.createSequentialGroup()
                        .addGroup(pnlFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTipo)
                            .addComponent(lblCategoria)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlFiltroLayout.setVerticalGroup(
            pnlFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFiltroLayout.createSequentialGroup()
                .addComponent(lblTipo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCategoria)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cboCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGap(108, 108, 108))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlFiltro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlResultado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 173, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        this.excluirAparelho();
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        this.editarAparelho();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        this.exibirTelaCadastroAparelhos();
    }//GEN-LAST:event_btnNovoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            this.pesquisar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro Desconhecido, Contate o Administrador do Sistema.");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarActionPerformed
        try {
            this.carregarTabelaAparelhos();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro Desconhecido, Contate o Administrador do Sistema.");
        }
    }//GEN-LAST:event_btnAtualizarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtualizar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JComboBox<String> cboCategoria;
    private javax.swing.JComboBox<String> cboTipo;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCategoria;
    private javax.swing.JLabel lblTipo;
    private javax.swing.JPanel pnlFiltro;
    private javax.swing.JPanel pnlResultado;
    private javax.swing.JTable tblResultado;
    // End of variables declaration//GEN-END:variables
    
    private abstract class ModeloTabelaAparelho extends AbstractTableModel{
        @Override
        public String getColumnName(int coluna) {
            if(coluna == 0){
                return "ID";
            }else if(coluna == 1){
                return "Nome";
            }else if(coluna == 2){
                return "Quantidade";
            }else if(coluna == 3){
                return "Tipo";
            }else{
                return "Categoria";
            }
        }
 
        @Override
        public int getRowCount(){
            return aparelhos.size();
        }
        
        @Override
        public int getColumnCount(){
            return 5;
        }
        
        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Aparelho aparelho = aparelhos.get(rowIndex);
            if(columnIndex == 0){
                return aparelho.getId_aparelho();
            }else if (columnIndex == 1) {
                return aparelho.getNome();
            }else if (columnIndex == 2) {
                return aparelho.getQuantidade();
            }else if (columnIndex == 3) {
                return aparelho.getTipo();
            }else{
                return aparelho.getCategoria();
            }     
        
        }
    }

}

