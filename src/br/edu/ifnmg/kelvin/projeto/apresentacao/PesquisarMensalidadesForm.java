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
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author KELVIN
 */
public class PesquisarMensalidadesForm extends javax.swing.JInternalFrame {
    private List<Atleta> atletas;  
    private List<Mensalidade> mensalidades;
    private SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
    /**
     * Creates new form Financas
     */
    public PesquisarMensalidadesForm() throws SQLException{
        this.prepararTela();
    }

    private void prepararTela() throws SQLException{
        initComponents();
        this.carregarComboAtletas();
        this.carregarTabelaMensalidade();
    }
    
    public void carregarComboAtletas() throws SQLException{
        AtletaBO atletaBO = new AtletaBO();
        try{
            atletas = atletaBO.buscarTodos();
        }catch(SQLException ex){
            
        }
        cboAtletas.removeAllItems();
        cboAtletas.addItem("Selecionar");
        for(Atleta atleta : atletas){
            cboAtletas.addItem(atleta.getNome());
        }
    }
    
    public void exibirTelaCadastroMensalidade() throws SQLException{
        CadastroMensalidadeForm cadastroMensalidadeForm = new CadastroMensalidadeForm();
        cadastroMensalidadeForm.setVisible(true);
        cadastroMensalidadeForm.setStatus(1);
        this.carregarTabelaMensalidade();
    }

      private void editarMensalidade() throws ParseException{
        int linhaSelecionada = tblResultado.getSelectedRow();
        if(linhaSelecionada != -1){
           Mensalidade mensalidadeSelecionado = mensalidades.get(linhaSelecionada);        
           CadastroMensalidadeForm cadastroMensalidadeForm = new CadastroMensalidadeForm(mensalidadeSelecionado);
           cadastroMensalidadeForm.setVisible(true);
           cadastroMensalidadeForm.setStatus(2);           
        } else{
            String mesnagem = "Nenhuma Mensalidade Selecionada.";
            JOptionPane.showMessageDialog(this, mesnagem, "Alteração de Mensalidades", JOptionPane.INFORMATION_MESSAGE);         
        }
    }    
    
    public void excluirMensalidade(){
        try{
            int linhaSelecionada = tblResultado.getSelectedRow();
            if (linhaSelecionada != 0) {
                Mensalidade mensalidadeSelecionado = mensalidades.get(linhaSelecionada);
                int resposta;
                String mensagem = "Deseja excluir a Mensalidade ID? "
                        + mensalidadeSelecionado.getId_mensalidade()+")?";
                String titulo = "Exclusão de Mensalidades";
                resposta = JOptionPane.showConfirmDialog(this, mensagem, titulo, JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.YES_OPTION) {
                    MensalidadeBO mensalidadeBO = new MensalidadeBO();
                    mensalidadeBO.excluirMensalidade(mensalidadeSelecionado.getId_mensalidade());
                    String mensagemSucesso = "Mensalidade ID:" + mensalidadeSelecionado.getId_mensalidade()+ ")"
                            + "excluida com sucesso.";
                    JOptionPane.showConfirmDialog(this, mensagem, "Exclusão de Mensalidades", JOptionPane.INFORMATION_MESSAGE);
                    this.carregarTabelaMensalidade();
                } else {
                    String mesnagem = "Nenhuma Mensalidade selecionada.";
                    JOptionPane.showMessageDialog(this, mesnagem, "Exclusão de Mensalidade", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (Exception e) {
            String mensagem = "Erro inesperado! Informe a mensagem de erro ao administrador do sistema.";
            mensagem += "\nMensagem de erro:\n" + e.getMessage();
            JOptionPane.showMessageDialog(this, mensagem, "Mensalidades Cadastradas", JOptionPane.ERROR_MESSAGE);
            this.dispose();
        }         
     }      
    // lançar exceção
    public void pesquisar() throws SQLException{
        
        if(cboAtletas.getSelectedItem().toString().equals("Selecionar") && cboMes.getSelectedIndex()== 0){
            JOptionPane.showMessageDialog(null, "Nenhum Campo Preenchido!");
            this.carregarTabelaMensalidade();
        }else if(cboAtletas.getSelectedItem().toString() != "Selecionar" && cboMes.getSelectedIndex() == 0){
            this.carregarTabelaMensalidadePorNome(cboAtletas.getSelectedItem().toString());
        }else if(cboAtletas.getSelectedItem().equals("Selecionar") && cboMes.getSelectedIndex() != 0){
            this.carregarTabelaMensalidadePorData(cboMes.getSelectedIndex());           
        }else{
            this.carregarTabelaMensalidadePorNomeData(cboAtletas.getSelectedItem().toString(), cboMes.getSelectedIndex());
        }
    }
    
    public void carregarTabelaMensalidade() throws SQLException{
        MensalidadeBO mensalidadeBO = new MensalidadeBO();
        this.mensalidades = mensalidadeBO.buscarTodos();        
        ModeloTabelaMensalidade modeloTabelaMensalidade = new ModeloTabelaMensalidade() {};
        tblResultado.setModel(modeloTabelaMensalidade);  
        
    }     

    public void carregarTabelaMensalidadePorNome(String nome) throws SQLException{
        MensalidadeBO mensalidadeBO = new MensalidadeBO();
        this.mensalidades = mensalidadeBO.pesquisarPorNome(nome);        
        ModeloTabelaMensalidade modeloTabelaMensalidade = new ModeloTabelaMensalidade() {};
        tblResultado.setModel(modeloTabelaMensalidade);         
    } 

    public void carregarTabelaMensalidadePorData(int mes) throws SQLException{
        MensalidadeBO mensalidadeBO = new MensalidadeBO();
        this.mensalidades = mensalidadeBO.pesquisarPorData(mes);        
        ModeloTabelaMensalidade modeloTabelaMensalidade = new ModeloTabelaMensalidade() {};
        tblResultado.setModel(modeloTabelaMensalidade);         
    }
    
    public void carregarTabelaMensalidadePorNomeData(String nome, int mes) throws SQLException{
        MensalidadeBO mensalidadeBO = new MensalidadeBO();
        this.mensalidades = mensalidadeBO.pesquisarPorNomeData(nome,mes);        
        ModeloTabelaMensalidade modeloTabelaMensalidade = new ModeloTabelaMensalidade() {};
        tblResultado.setModel(modeloTabelaMensalidade);         
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
        btnExcluir1 = new javax.swing.JButton();
        btnAtualizar = new javax.swing.JButton();
        pnlFiltro = new javax.swing.JPanel();
        lblNomeAtleta = new javax.swing.JLabel();
        lblDataMensalidade = new javax.swing.JLabel();
        btnPesquisar = new javax.swing.JButton();
        cboAtletas = new javax.swing.JComboBox<>();
        cboMes = new javax.swing.JComboBox<>();

        setClosable(true);
        setTitle("Controle de Finanças");

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

        btnExcluir1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/edu/ifnmg/kelvin/projeto/apresentacao/Imagens/document199.png"))); // NOI18N
        btnExcluir1.setText("Relatórios");
        btnExcluir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluir1ActionPerformed(evt);
            }
        });

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
                    .addComponent(jScrollPane1)
                    .addGroup(pnlResultadoLayout.createSequentialGroup()
                        .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnExcluir1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlResultadoLayout.setVerticalGroup(
            pnlResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlResultadoLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExcluir1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pnlFiltro.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtro"));

        lblNomeAtleta.setText("Nome Atleta");

        lblDataMensalidade.setText("Mês");

        btnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/edu/ifnmg/kelvin/projeto/apresentacao/Imagens/search102.png"))); // NOI18N
        btnPesquisar.setText("Pesquisar");
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });

        cboAtletas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cboAtletas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboAtletasActionPerformed(evt);
            }
        });

        cboMes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro" }));

        javax.swing.GroupLayout pnlFiltroLayout = new javax.swing.GroupLayout(pnlFiltro);
        pnlFiltro.setLayout(pnlFiltroLayout);
        pnlFiltroLayout.setHorizontalGroup(
            pnlFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFiltroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFiltroLayout.createSequentialGroup()
                        .addComponent(btnPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlFiltroLayout.createSequentialGroup()
                        .addGroup(pnlFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlFiltroLayout.createSequentialGroup()
                                .addComponent(lblNomeAtleta)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(cboAtletas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDataMensalidade)
                            .addComponent(cboMes, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        pnlFiltroLayout.setVerticalGroup(
            pnlFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFiltroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNomeAtleta)
                    .addComponent(lblDataMensalidade))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboAtletas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnPesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        this.excluirMensalidade();
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        try {
            this.editarMensalidade();
        } catch (ParseException ex) {
            
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        try {
            this.exibirTelaCadastroMensalidade();
        } catch (SQLException ex) {
            
        }
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnExcluir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluir1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnExcluir1ActionPerformed

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        try {
            this.pesquisar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void btnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarActionPerformed
        try {
            this.carregarTabelaMensalidade();
        } catch (SQLException ex) {
            
        }
    }//GEN-LAST:event_btnAtualizarActionPerformed

    private void cboAtletasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboAtletasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboAtletasActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtualizar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnExcluir1;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JComboBox<String> cboAtletas;
    private javax.swing.JComboBox<String> cboMes;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDataMensalidade;
    private javax.swing.JLabel lblNomeAtleta;
    private javax.swing.JPanel pnlFiltro;
    private javax.swing.JPanel pnlResultado;
    private javax.swing.JTable tblResultado;
    // End of variables declaration//GEN-END:variables
    private abstract class ModeloTabelaMensalidade extends AbstractTableModel{
            @Override
        public String getColumnName(int coluna) {
            if (coluna == 0) {
                return "ID";
            } else if (coluna == 1) {
                return "Nome Atleta";
            } else if (coluna == 2) {
                return "Data Emissão";
            } else if(coluna == 3){
                return "Data Vencimento";
            }else{
                return "Status";
            }
        }

         @Override
        public int getRowCount() {
            return mensalidades.size();
        }

        @Override
        public int getColumnCount() {
            return 5;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Mensalidade mensalidade = mensalidades.get(rowIndex);
            if (columnIndex == 0) {
                return mensalidade.getId_mensalidade();
            } else if (columnIndex == 1) {
                return mensalidade.getNome();
            } else if (columnIndex == 2) {
                return mensalidade.getDataEmissao();
            } else if (columnIndex == 3) {
                return mensalidade.getDataVencimento();
            } else{
                return mensalidade.getStatus();
            }  
        }
    }
}
