/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.kelvin.projeto.apresentacao;

import br.edu.ifnmg.kelvin.projeto.entidade.Atleta;
import br.edu.ifnmg.kelvin.projeto.entidade.Avaliacao;
import br.edu.ifnmg.kelvin.projeto.entidade.PersonalTrainer;
import br.edu.ifnmg.kelvin.projeto.excecao.PesquisaInvalidaException;
import br.edu.ifnmg.kelvin.projeto.negocio.AtletaBO;
import br.edu.ifnmg.kelvin.projeto.negocio.AvaliacaoBO;
import br.edu.ifnmg.kelvin.projeto.negocio.PersonalBO;
import java.awt.Dimension;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author KELVIN
 */
public class PesquisarAvaliacoesForm extends javax.swing.JInternalFrame {

    private CadastroAvaliacoesForm editarAvaliacaoForm;
    private CadastroAvaliacoesForm novoAvaliacaoForm;
    private List<Avaliacao> avaliacoes;
    private List<Atleta> atletas;
    private List<PersonalTrainer> personals;
    
    /**
     * Creates new form PesquisarAvaliacoesForm
     */
    public PesquisarAvaliacoesForm() throws SQLException {
        this.prepararTela();
    }
    
    public void prepararTela() throws SQLException{
        initComponents();
        this.carregarComboAtletas();
        this.carregarComboPersonal();
        this.carregarTabelaAvaliacao();
    }
    
    // Metodo para iniciar o Form centralizdo na tela
    public void setPosicao() {
        Dimension dimensao = this.getDesktopPane().getSize();
        this.setLocation((dimensao.width - this.getSize().width) / 2, (dimensao.height - this.getSize().height) / 2); 
    }
    
    public void visualizar(){
        int linhaSelecionada = tblResultado.getSelectedRow();
        if(linhaSelecionada != -1)
        {
           Avaliacao avaliacaoSelecionado = avaliacoes.get(linhaSelecionada);        
           VisualizarAvaliacao visualizarAvaliacao = new VisualizarAvaliacao(avaliacaoSelecionado);
           visualizarAvaliacao.setVisible(true);          
        }
        else
        {
            String mesnagem = "Nenhuma Avaliação Selecionada.";
            JOptionPane.showMessageDialog(this, mesnagem, "Alteração de Avaliações", JOptionPane.INFORMATION_MESSAGE);     
        }        
    }
    
    public void exibirTelaCadastroAvaliacao(){
        CadastroAvaliacoesForm cadastroAvaliacoesForm = new CadastroAvaliacoesForm();
        cadastroAvaliacoesForm.setVisible(true);
        cadastroAvaliacoesForm.setStatus(1);
    }
    
      private void editarAvaliacao(){
        int linhaSelecionada = tblResultado.getSelectedRow();
        if(linhaSelecionada != -1)
        {
           Avaliacao avaliacaoSelecionado = avaliacoes.get(linhaSelecionada);        
           CadastroAvaliacoesForm cadastroAvaliacoesForm = new CadastroAvaliacoesForm(avaliacaoSelecionado);
           cadastroAvaliacoesForm.setVisible(true);
           cadastroAvaliacoesForm.setStatus(2);           
        }
        else
        {
            String mesnagem = "Nenhuma Avaliação Selecionada.";
            JOptionPane.showMessageDialog(this, mesnagem, "Alteração de Avaliações", JOptionPane.INFORMATION_MESSAGE);     
        }
    }
      
    public void excluirAvaliacao(){
        try{
            int linhaSelecionada = tblResultado.getSelectedRow();
            if (linhaSelecionada != 1) {
                Avaliacao avaliacaoSelecionado = avaliacoes.get(linhaSelecionada);
                int resposta;
                String mensagem = "Deseja excluir a Avaliação ID? "
                        + avaliacaoSelecionado.getId_avaliacao()+ " (Do Atleta: "
                        + avaliacaoSelecionado.getNomeAtleta()+ ")?";
                String titulo = "Exclusão de Avaliações";
                resposta = JOptionPane.showConfirmDialog(this, mensagem, titulo, JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.YES_OPTION) {
                    AvaliacaoBO avaliacaoBO = new AvaliacaoBO();
                    avaliacaoBO.excluirAvaliacao(avaliacaoSelecionado.getId_avaliacao());
                    String mensagemSucesso = "Avaliação ID:" + avaliacaoSelecionado.getId_avaliacao() + " (Atleta ID:"
                            + avaliacaoSelecionado.getNomeAtleta()+ ")"
                            + "excluido com sucesso.";
                    JOptionPane.showConfirmDialog(this, mensagem, "Exclusão de Avaliações", JOptionPane.INFORMATION_MESSAGE);
                    this.carregarTabelaAvaliacao();
                } else {
                    String mesnagem = "Nenhuma Avaliação selecionada.";
                    JOptionPane.showMessageDialog(this, mesnagem, "Exclusão de Avaliações", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (Exception e) {
            String mensagem = "Erro inesperado! Informe a mensagem de erro ao administrador do sistema.";
            mensagem += "\nMensagem de erro:\n" + e.getMessage();
            JOptionPane.showMessageDialog(this, mensagem, "Avaliações Cadastradas", JOptionPane.ERROR_MESSAGE);
            this.dispose();
        }         
     }
    
    public void pesquisar() throws SQLException{
        try{
            if(cboAtleta.getSelectedItem().toString().equals("Selecionar") && cboAvaliador.getSelectedItem().toString().equals("Selecionar")){
                this.carregarTabelaAvaliacao();
                String msg = "Pesquisa em Branco!";
                throw new PesquisaInvalidaException(msg);
            }else if(cboAtleta.getSelectedItem().toString() != "Selecionar" && cboAvaliador.getSelectedItem().toString().equals("Selecionar")){
                this.carregarTabelaAvaliacaoPorAtleta(cboAtleta.getSelectedItem().toString());
            }else if(cboAtleta.getSelectedItem().toString().equals("Selecionar") && cboAvaliador.getSelectedItem().toString() != "Selecionar"){
                this.carregarTabelaAvaliacaoPorPersonal(cboAvaliador.getSelectedItem().toString());           
            }else{
                this.carregarTabelaAvaliacaoPorAtletaPersonal(cboAtleta.getSelectedItem().toString(),cboAvaliador.getSelectedItem().toString());
            } 
        }catch(PesquisaInvalidaException e){
            String mensagem = "Falha na pesquisa!\n" + e.getMessage();
            JOptionPane.showMessageDialog(this, mensagem, "Pesquisar Aparelhos", JOptionPane.ERROR_MESSAGE);
        }
    }    
    
    
     public void carregarTabelaAvaliacao() throws SQLException{
        AvaliacaoBO avaliacaoBO = new AvaliacaoBO();
        this.avaliacoes = avaliacaoBO.buscarTodos();        
        ModeloTabelaAvaliacao modeloTabelaAvaliacao = new ModeloTabelaAvaliacao() {};
        tblResultado.setModel(modeloTabelaAvaliacao);      
    }
 
     public void carregarTabelaAvaliacaoPorAtleta(String atleta) throws SQLException{
        AvaliacaoBO avaliacaoBO = new AvaliacaoBO();
        this.avaliacoes = avaliacaoBO.carregarTabelaAvaliacaoPorAtleta(atleta);        
        ModeloTabelaAvaliacao modeloTabelaAvaliacao = new ModeloTabelaAvaliacao() {};
        tblResultado.setModel(modeloTabelaAvaliacao);      
    } 

     public void carregarTabelaAvaliacaoPorPersonal(String personal) throws SQLException{
        AvaliacaoBO avaliacaoBO = new AvaliacaoBO();
        this.avaliacoes = avaliacaoBO.carregarTabelaAvaliacaoPorPersonal(personal);        
        ModeloTabelaAvaliacao modeloTabelaAvaliacao = new ModeloTabelaAvaliacao() {};
        tblResultado.setModel(modeloTabelaAvaliacao);      
    } 

     public void carregarTabelaAvaliacaoPorAtletaPersonal(String atleta, String personal) throws SQLException{
        AvaliacaoBO avaliacaoBO = new AvaliacaoBO();
        this.avaliacoes = avaliacaoBO.carregarTabelaAvaliacaoPorAtletaPersonal(atleta, personal);        
        ModeloTabelaAvaliacao modeloTabelaAvaliacao = new ModeloTabelaAvaliacao() {};
        tblResultado.setModel(modeloTabelaAvaliacao);      
    }      
     
    private void carregarComboAtletas() throws SQLException{
        AtletaBO atletaBO = new AtletaBO();
        try{
            atletas = atletaBO.buscarTodos();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro Desconhecido, Contate o Administrador do Sistema.");
        }
        cboAtleta.removeAllItems();
        cboAtleta.addItem("Selecionar");
        for(Atleta atleta : atletas){
            cboAtleta.addItem(atleta.getNome());
        }
    } 

    private void carregarComboPersonal() throws SQLException{
        PersonalBO personalBO = new PersonalBO();
        try{
            personals = personalBO.buscarTodos();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro Desconhecido, Contate o Administrador do Sistema.");
        }
        cboAvaliador.removeAllItems();
        cboAvaliador.addItem("Selecionar");
        for(PersonalTrainer personal : personals){
            cboAvaliador.addItem(personal.getNome());
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

        pnlResultado = new javax.swing.JPanel();
        btnExcluir = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnVisualizar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblResultado = new javax.swing.JTable();
        btnAtualizar = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();
        pnlFiltro = new javax.swing.JPanel();
        lblNomeAtleta = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        cboAtleta = new javax.swing.JComboBox<>();
        lblNomePersonal = new javax.swing.JLabel();
        cboAvaliador = new javax.swing.JComboBox<>();

        setClosable(true);
        setTitle("Pesquisar - Avaliações");

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

        btnVisualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/edu/ifnmg/kelvin/projeto/apresentacao/Imagens/search102.png"))); // NOI18N
        btnVisualizar.setText("Visualizar");
        btnVisualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVisualizarActionPerformed(evt);
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

        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/edu/ifnmg/kelvin/projeto/apresentacao/Imagens/add182.png"))); // NOI18N
        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlResultadoLayout = new javax.swing.GroupLayout(pnlResultado);
        pnlResultado.setLayout(pnlResultadoLayout);
        pnlResultadoLayout.setHorizontalGroup(
            pnlResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlResultadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 716, Short.MAX_VALUE)
                    .addGroup(pnlResultadoLayout.createSequentialGroup()
                        .addComponent(btnVisualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVisualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pnlFiltro.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtro"));

        lblNomeAtleta.setText("Nome do Atleta");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/edu/ifnmg/kelvin/projeto/apresentacao/Imagens/search102.png"))); // NOI18N
        jButton1.setText("Pesquisar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        cboAtleta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));

        lblNomePersonal.setText("Nome do Avaliador");

        cboAvaliador.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));

        javax.swing.GroupLayout pnlFiltroLayout = new javax.swing.GroupLayout(pnlFiltro);
        pnlFiltro.setLayout(pnlFiltroLayout);
        pnlFiltroLayout.setHorizontalGroup(
            pnlFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFiltroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboAtleta, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlFiltroLayout.createSequentialGroup()
                        .addGroup(pnlFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNomeAtleta)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNomePersonal))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(cboAvaliador, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlFiltroLayout.setVerticalGroup(
            pnlFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFiltroLayout.createSequentialGroup()
                .addComponent(lblNomeAtleta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboAtleta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNomePersonal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboAvaliador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        this.excluirAvaliacao();
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        this.editarAvaliacao();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnVisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVisualizarActionPerformed
       this.visualizar();
    }//GEN-LAST:event_btnVisualizarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            this.pesquisar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro Desconhecido, Contate o Administrador do Sistema.");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarActionPerformed
        try {
            this.carregarTabelaAvaliacao();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro Desconhecido, Contate o Administrador do Sistema.");
        }
    }//GEN-LAST:event_btnAtualizarActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        this.exibirTelaCadastroAvaliacao();
    }//GEN-LAST:event_btnNovoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtualizar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnVisualizar;
    private javax.swing.JComboBox<String> cboAtleta;
    private javax.swing.JComboBox<String> cboAvaliador;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblNomeAtleta;
    private javax.swing.JLabel lblNomePersonal;
    private javax.swing.JPanel pnlFiltro;
    private javax.swing.JPanel pnlResultado;
    private javax.swing.JTable tblResultado;
    // End of variables declaration//GEN-END:variables
private abstract class ModeloTabelaAvaliacao extends AbstractTableModel{
        @Override
        public String getColumnName(int coluna) {
            if(coluna == 0){
                return "ID";
            }else if(coluna == 1){
                return "Nome Atleta";
            }else if(coluna == 2){
                return "Nome Avaliador";
            }else{
                return "Data Validade";
            }
        }

        @Override
        public int getRowCount(){
            return avaliacoes.size();
        }
        
        @Override
        public int getColumnCount(){
            return 4;
        }
        
        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Avaliacao avaliacao = avaliacoes.get(rowIndex);
            if(columnIndex == 0){
                return avaliacao.getId_avaliacao();
            }else if (columnIndex == 1) {
                return avaliacao.getNomeAtleta();
            }else if(columnIndex ==2){
                return avaliacao.getNomePersonal();
            }else{
                return avaliacao.getDataValidade();
            }
        }
    }
}
