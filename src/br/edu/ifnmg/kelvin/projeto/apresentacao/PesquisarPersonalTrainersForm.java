/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.kelvin.projeto.apresentacao;

import br.edu.ifnmg.kelvin.projeto.entidade.PersonalTrainer;
import br.edu.ifnmg.kelvin.projeto.excecao.PesquisaInvalidaException;
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
public class PesquisarPersonalTrainersForm extends javax.swing.JInternalFrame {

    private CadastroPersonalTrainerForm editarPersonalTrainerForm;
    private CadastroPersonalTrainerForm novoPersonalTrainerForm;
    private List<PersonalTrainer> personals;
    /**
     * Creates new form PesquisarPersonalTrainersForm
     */
    public PesquisarPersonalTrainersForm() throws SQLException {
        this.prepararTela();
    }

    private void prepararTela() throws SQLException{
        this.initComponents();
        this.carregarComboPersonals();
        this.carregarTabelaPersonal();
    }
    
    // Metodo para iniciar o Form centralizdo na tela
    public void setPosicao() {
        Dimension dimensao = this.getDesktopPane().getSize();
        this.setLocation((dimensao.width - this.getSize().width) / 2, (dimensao.height - this.getSize().height) / 2); 
    }
    
    private void exibirTelaCadastroPersonal(){
        CadastroPersonalTrainerForm cadastroPersonalTrainerForm = new CadastroPersonalTrainerForm();
        cadastroPersonalTrainerForm.setVisible(true);
        cadastroPersonalTrainerForm.setStatus(1);
    }
    
    private void editarPersonal(){
        int linhaSelecionada = tblResultado.getSelectedRow();
        if(linhaSelecionada != -1)
        {
           PersonalTrainer personalSelecionado = personals.get(linhaSelecionada);          
           CadastroPersonalTrainerForm cadastroPersonalTrainerForm = new CadastroPersonalTrainerForm(personalSelecionado);
           cadastroPersonalTrainerForm.setVisible(true);
           cadastroPersonalTrainerForm.setStatus(2);
        }
        else
        {
            String mesnagem = "Nenhum Personal Trainer Selecionado.";
            JOptionPane.showMessageDialog(this, mesnagem, "Alteração de Personal Trainer", JOptionPane.INFORMATION_MESSAGE);    
        }
    }
    

            
    public void excluirPersonal(){
    try{
            int linhaSelecionada = tblResultado.getSelectedRow();
            if (linhaSelecionada != 1) {
                PersonalTrainer personalSelecionado = personals.get(linhaSelecionada);
                int resposta;
                String mensagem = "Deseja excluir o Personal Trainer? "
                        + personalSelecionado.getNome() + " (ID: "
                        + personalSelecionado.getId_personal() + ")?";
                String titulo = "Exclusão de PersonalTrainer";
                resposta = JOptionPane.showConfirmDialog(this, mensagem, titulo, JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.YES_OPTION) {
                    PersonalBO personalBO = new PersonalBO();
                    personalBO.excluirPersonal(personalSelecionado.getId_personal());
                    String mensagemSucesso = "Personal Trainer " + personalSelecionado.getNome() + " (ID:"
                            + personalSelecionado.getId_personal() + ")"
                            + "excluido com sucesso.";
                    JOptionPane.showConfirmDialog(this, mensagem, "Exclusão de Personal Trainer", JOptionPane.INFORMATION_MESSAGE);
                    this.carregarTabelaPersonal();
                } else {
                    String mesnagem = "Nenhum Personal Trainer Selecionado.";
                    JOptionPane.showMessageDialog(this, mesnagem, "Exclusão de Personal Trainer", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (Exception e) {
            String mensagem = "Erro inesperado! Informe a mensagem de erro ao administrador do sistema.";
            mensagem += "\nMensagem de erro:\n" + e.getMessage();
            JOptionPane.showMessageDialog(this, mensagem, "Personal Trainers Cadastrados", JOptionPane.ERROR_MESSAGE);
            this.dispose();
        }        
    }
    
    public void carregarComboPersonals(){
        PersonalBO personalBO = new PersonalBO();
        try {
            personals = personalBO.buscarTodos();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro Desconhecido, Contate o Administrador do Sistema.");
        }
        cboPersonal.removeAllItems();
        cboPersonal.addItem("Selecionar");
        for (PersonalTrainer personal : personals) {
            cboPersonal.addItem(personal.getNome());
        }
    }
    
    public void pesquisar() throws SQLException{
        try{
            if(cboPersonal.getSelectedItem().toString().equals("Selecionar") && txtCPF.getText().equals("   .   .   -  ")){
                this.carregarTabelaPersonal();
                String msg = "Pesquisa em Branco!";
                throw new PesquisaInvalidaException(msg); 
            }else if(cboPersonal.getSelectedItem().toString() != "Selecionar" && txtCPF.getText().equals("   .   .   -  ")){
                this.carregarTabelaPersonalPorNome(cboPersonal.getSelectedItem().toString());
            }else if(cboPersonal.getSelectedItem().toString().equals("Selecionar") && txtCPF.getText() != "   .   .   -  "){
                this.carregarTabelaPersonalPorCpf(txtCPF.getText());           
            }else{
                this.carregarTabelaPersonalPorNomeCpf(cboPersonal.getSelectedItem().toString(),txtCPF.getText());
            }  
        }catch(PesquisaInvalidaException e){
            String mensagem = "Falha na pesquisa!\n" + e.getMessage();
            JOptionPane.showMessageDialog(this, mensagem, "Pesquisar Aparelhos", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void carregarTabelaPersonal() throws SQLException{
        PersonalBO personalBO = new PersonalBO();
        this.personals = personalBO.buscarTodos();      
        ModeloTabelaPersonal modeloTabelaPersonal = new ModeloTabelaPersonal() {};
        tblResultado.setModel(modeloTabelaPersonal);      
    }
    
    public void carregarTabelaPersonalPorNome(String nome) throws SQLException{
        PersonalBO personalBO = new PersonalBO();
        this.personals = personalBO.carregarTabelaPersonalPorNome(nome);      
        ModeloTabelaPersonal modeloTabelaPersonal = new ModeloTabelaPersonal() {};
        tblResultado.setModel(modeloTabelaPersonal);      
    }
    
    public void carregarTabelaPersonalPorCpf(String cpf) throws SQLException{
        PersonalBO personalBO = new PersonalBO();
        this.personals = personalBO.carregarTabelaPersonalPorCpf(cpf);      
        ModeloTabelaPersonal modeloTabelaPersonal = new ModeloTabelaPersonal() {};
        tblResultado.setModel(modeloTabelaPersonal);      
    }
    
    public void carregarTabelaPersonalPorNomeCpf(String nome, String cpf) throws SQLException{
        PersonalBO personalBO = new PersonalBO();
        this.personals = personalBO.carregarTabelaPersonalPorNomeCpf(nome, cpf);      
        ModeloTabelaPersonal modeloTabelaPersonal = new ModeloTabelaPersonal() {};
        tblResultado.setModel(modeloTabelaPersonal);      
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
        lblNome = new javax.swing.JLabel();
        btnPesquisar = new javax.swing.JButton();
        cboPersonal = new javax.swing.JComboBox<>();

        setClosable(true);
        setMaximizable(true);
        setTitle("Pesquisar - Personal Trainers");

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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pnlFiltro.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtro"));

        lblCPF.setText("CPF");

        try {
            txtCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCPF.setText("");

        lblNome.setText("Nome do Personal Trainer");

        btnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/edu/ifnmg/kelvin/projeto/apresentacao/Imagens/search102.png"))); // NOI18N
        btnPesquisar.setText("Pesquisar");
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });

        cboPersonal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));

        javax.swing.GroupLayout pnlFiltroLayout = new javax.swing.GroupLayout(pnlFiltro);
        pnlFiltro.setLayout(pnlFiltroLayout);
        pnlFiltroLayout.setHorizontalGroup(
            pnlFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFiltroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboPersonal, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlFiltroLayout.createSequentialGroup()
                        .addGroup(pnlFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNome)
                            .addComponent(btnPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCPF)
                            .addComponent(txtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlFiltroLayout.setVerticalGroup(
            pnlFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFiltroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCPF)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblNome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
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
        this.excluirPersonal();
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        this.editarPersonal();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        this.exibirTelaCadastroPersonal();
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        try {
            this.pesquisar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro Desconhecido, Contate o Administrador do Sistema.");
        }
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void btnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarActionPerformed
        try {
            this.carregarTabelaPersonal();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro Desconhecido, Contate o Administrador do Sistema.");
        }
    }//GEN-LAST:event_btnAtualizarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtualizar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JComboBox<String> cboPersonal;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCPF;
    private javax.swing.JLabel lblNome;
    private javax.swing.JPanel pnlFiltro;
    private javax.swing.JPanel pnlResultado;
    private javax.swing.JTable tblResultado;
    private javax.swing.JFormattedTextField txtCPF;
    // End of variables declaration//GEN-END:variables
private abstract class ModeloTabelaPersonal extends AbstractTableModel{
        @Override
    public String getColumnName(int coluna) {
        if (coluna == 0) {
            return "ID";
        } else if (coluna == 1) {
            return "Nome";
        } else if (coluna == 2) {
            return "Funçao";
        } else if (coluna == 3) {
            return "CPF";
        } else {
            return "Telefone";
        }
    }

    @Override
    public int getRowCount() {
        return personals.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PersonalTrainer personalTrainer = personals.get(rowIndex);
        if (columnIndex == 0) {
            return personalTrainer.getId_personal();
        } else if (columnIndex == 1) {
            return personalTrainer.getNome();
        } else if (columnIndex == 2) {
            return personalTrainer.getFuncao();
        } else if (columnIndex == 3) {
            return personalTrainer.getCpf();
        } else {
            return personalTrainer.getTelefone();
        }
    }
}
}
