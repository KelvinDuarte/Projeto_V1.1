/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.kelvin.projeto.apresentacao;

import br.edu.ifnmg.kelvin.projeto.entidade.Treino;
import br.edu.ifnmg.kelvin.projeto.negocio.TreinoBO;
import java.awt.Dimension;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author KELVIN
 */
public class PesquisarTreinosForm extends javax.swing.JInternalFrame {

    private CadastroTreinosForm editarTreinoForm;
    private CadastroTreinosForm novoTreinosForm;
    private List<Treino> treinos;

    /**
     * Creates new form PesquisarTreinosForm
     */
    public PesquisarTreinosForm() throws SQLException {
        this.prepatarTela();
    }

    public void prepatarTela() throws SQLException {
        initComponents();
        this.carregarTabelaTreino();
    }

    // Metodo para iniciar o Form centralizdo na tela
    public void setPosicao() {
        Dimension dimensao = this.getDesktopPane().getSize();
        this.setLocation((dimensao.width - this.getSize().width) / 2, (dimensao.height - this.getSize().height) / 2);
    }

    private void exibirTelaCadastroTreino() {
        CadastroTreinosForm cadastroTreinosForm = new CadastroTreinosForm();
        cadastroTreinosForm.setVisible(true);
        cadastroTreinosForm.setStatus(1);
    }

    private void editarTreino() {
        int linhaSelecionada = tblResultado.getSelectedRow();
        if (linhaSelecionada != 0) {
            Treino treinoSelecionado = treinos.get(linhaSelecionada);
            CadastroTreinosForm cadastroTreinosForm = new CadastroTreinosForm(treinoSelecionado);
            cadastroTreinosForm.setVisible(true);
            cadastroTreinosForm.setStatus(2);
        } else {
            String mesnagem = "Nenhum Treino Selecionado.";
            JOptionPane.showMessageDialog(this, mesnagem, "Alteração de Treinos", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void excluirTreino() {
        try {
            int linhaSelecionada = tblResultado.getSelectedRow();
            if (linhaSelecionada != 0) {
                Treino treinoSelecionado = treinos.get(linhaSelecionada);
                int resposta;
                String mensagem = "Deseja excluir o Treino ID? "
                        + treinoSelecionado.getId_treino();
                String titulo = "Exclusão de Treinos";
                resposta = JOptionPane.showConfirmDialog(this, mensagem, titulo, JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.YES_OPTION) {
                    TreinoBO treinoBO = new TreinoBO();
                    treinoBO.excluirTreino(treinoSelecionado.getId_treino());
                    String mensagemSucesso = "Treino ID:" + treinoSelecionado.getId_treino() + " (Treino ID:"
                            + treinoSelecionado.getId_treino() + ")"
                            + "excluido com sucesso.";
                    JOptionPane.showConfirmDialog(this, mensagem, "Exclusão de Treinos", JOptionPane.INFORMATION_MESSAGE);
                    this.carregarTabelaTreino();
                } else {
                    String mesnagem = "Nenhum Treino selecionado.";
                    JOptionPane.showMessageDialog(this, mesnagem, "Exclusão de Treinos", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (Exception e) {
            String mensagem = "Erro inesperado! Informe a mensagem de erro ao administrador do sistema.";
            mensagem += "\nMensagem de erro:\n" + e.getMessage();
            JOptionPane.showMessageDialog(this, mensagem, "Treinos Cadastrados", JOptionPane.ERROR_MESSAGE);
            this.dispose();
        }
    }

    public void pesquisar() throws SQLException{
        if(cboTipo.getSelectedItem().toString().equals("Selecionar") && cboCategoria.getSelectedItem().toString().equals("Selecionar")){
            JOptionPane.showMessageDialog(null, "Nenhum Campo Preenchido!");
            this.carregarTabelaTreino();
        }else if(cboTipo.getSelectedItem().toString() != "Selecionar" && cboCategoria.getSelectedItem().toString().equals("Selecionar")){
            this.carregarTabelaTreinoPorTipo(cboTipo.getSelectedItem().toString());
        }else if(cboTipo.getSelectedItem().toString().equals("Selecionar") && cboCategoria.getSelectedItem().toString() != "Selecionar"){
            this.carregarTabelaTreinoPorCategoria(cboCategoria.getSelectedItem().toString());           
        }else{
            this.carregarTabelaTreinoPorTipoCategoria(cboTipo.getSelectedItem().toString(),cboCategoria.getSelectedItem().toString());
        }        
    }
    
    public void carregarTabelaTreino() throws SQLException {
        TreinoBO treinoBO = new TreinoBO();
        this.treinos = treinoBO.buscarTodos();
        ModeloTabelaTreino modeloTabelaTreino = new ModeloTabelaTreino() {
        };
        tblResultado.setModel(modeloTabelaTreino);
    }

    public void carregarTabelaTreinoPorTipo(String tipo) throws SQLException {
        TreinoBO treinoBO = new TreinoBO();
        this.treinos = treinoBO.carregarTabelaTreinoPorTipo(tipo);
        ModeloTabelaTreino modeloTabelaTreino = new ModeloTabelaTreino() {
        };
        tblResultado.setModel(modeloTabelaTreino);
    }

    public void carregarTabelaTreinoPorCategoria(String categoria) throws SQLException {
        TreinoBO treinoBO = new TreinoBO();
        this.treinos = treinoBO.carregarTabelaTreinoPorCategoria(categoria);
        ModeloTabelaTreino modeloTabelaTreino = new ModeloTabelaTreino() {
        };
        tblResultado.setModel(modeloTabelaTreino);
    }

    public void carregarTabelaTreinoPorTipoCategoria(String tipo, String categoria) throws SQLException {
        TreinoBO treinoBO = new TreinoBO();
        this.treinos = treinoBO.carregarTabelaTreinoPorTipoCategoria(tipo,categoria);
        ModeloTabelaTreino modeloTabelaTreino = new ModeloTabelaTreino() {
        };
        tblResultado.setModel(modeloTabelaTreino);
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
        lblCategoria = new javax.swing.JLabel();
        cboCategoria = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        cboTipo = new javax.swing.JComboBox();
        lblTipo1 = new javax.swing.JLabel();

        setClosable(true);
        setTitle("Pesquisar - Treinos");

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
                    .addComponent(jScrollPane1)
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pnlFiltro.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtro"));

        lblCategoria.setText("Categoria");

        cboCategoria.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecionar", "Iniciante", "Intermediário", "Avançado" }));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/edu/ifnmg/kelvin/projeto/apresentacao/Imagens/search102.png"))); // NOI18N
        jButton1.setText("Pesquisar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        cboTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecionar", "Peito e Tríceps", "Costa e Bíceps", "Ombro, Trapézio", "Pernas" }));

        lblTipo1.setText("Tipo");

        javax.swing.GroupLayout pnlFiltroLayout = new javax.swing.GroupLayout(pnlFiltro);
        pnlFiltro.setLayout(pnlFiltroLayout);
        pnlFiltroLayout.setHorizontalGroup(
            pnlFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFiltroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFiltroLayout.createSequentialGroup()
                        .addComponent(lblTipo1)
                        .addGap(564, 564, 564))
                    .addGroup(pnlFiltroLayout.createSequentialGroup()
                        .addGroup(pnlFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCategoria)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFiltroLayout.createSequentialGroup()
                        .addGroup(pnlFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cboCategoria, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboTipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        pnlFiltroLayout.setVerticalGroup(
            pnlFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFiltroLayout.createSequentialGroup()
                .addComponent(lblTipo1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblCategoria)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        this.excluirTreino();
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        this.editarTreino();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        this.exibirTelaCadastroTreino();
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
            this.carregarTabelaTreino();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro Desconhecido, Contate o Administrador do Sistema.");
        }
    }//GEN-LAST:event_btnAtualizarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtualizar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JComboBox cboCategoria;
    private javax.swing.JComboBox cboTipo;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCategoria;
    private javax.swing.JLabel lblTipo1;
    private javax.swing.JPanel pnlFiltro;
    private javax.swing.JPanel pnlResultado;
    private javax.swing.JTable tblResultado;
    // End of variables declaration//GEN-END:variables
private abstract class ModeloTabelaTreino extends AbstractTableModel {

    @Override
    public String getColumnName(int coluna) {
        if (coluna == 0) {
            return "ID";
        } else if (coluna == 1) {
            return "Tipo do Treino";
        } else {
            return "Categoria";
        }
    }
    
    @Override
    public int getRowCount() {
        return treinos.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Treino treino = treinos.get(rowIndex);
        if (columnIndex == 0) {
            return treino.getId_treino();
        } else if (columnIndex == 1) {
            return treino.getTipo();
        } else {
            return treino.getCategoria();
        }
    }
}
}
