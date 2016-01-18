
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.kelvin.projeto.apresentacao;

import br.edu.ifnmg.kelvin.projeto.entidade.Atleta;
import br.edu.ifnmg.kelvin.projeto.entidade.PersonalTrainer;
import br.edu.ifnmg.kelvin.projeto.negocio.AtletaBO;
import br.edu.ifnmg.kelvin.projeto.negocio.PersonalBO;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author KELVIN
 */
public class CadastroAtletasForm extends javax.swing.JFrame {
    
    private List<PersonalTrainer> personals;
    private int status;
    private Atleta atletaEmEdicao;
    private SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
    /**
     * Creates new form CadastroAtletas1
     */
    
    public CadastroAtletasForm() {
        this.atletaEmEdicao = new Atleta();
        this.prepararTela();       
    }
    
    public CadastroAtletasForm(Atleta atletaParaEdicao) {
        this.atletaEmEdicao = atletaParaEdicao;
        this.prepararTela();
        this.inicializarCamposTela();       
    }
    
    private void prepararTela(){
        try {
            this.initComponents();
            this.carregarComboPersonals();
            setLocationRelativeTo(null);
        } catch (Exception e) {
            String mensagem = "Erro inesperado! Informe a mensagem de erro ao administrador do sistema.";
            mensagem += "\nMensagem de erro:\n" + e.getMessage();
            JOptionPane.showMessageDialog(this, mensagem, "Cadastro de Atletas", JOptionPane.ERROR_MESSAGE);
            this.dispose();
        }       
    }
    
    private void inicializarCamposTela(){
        String codigo = Integer.toString(atletaEmEdicao.getId_atleta());
        txtCodigo.setText(codigo);
        txtNome.setText(atletaEmEdicao.getNome());
        txtApelido.setText(atletaEmEdicao.getApelido());
        txtNomePai.setText(atletaEmEdicao.getNomePai());
        txtNomeMae.setText(atletaEmEdicao.getNomeMae());
        String dataNascimento = formatador.format(atletaEmEdicao.getDataNascimento());
        txtDataNascimento.setText(dataNascimento);
        String dataInicio = formatador.format(atletaEmEdicao.getDataNascimento());
        txtDataInicio.setText(dataInicio);
        if(atletaEmEdicao.getSexo() == 'M')
        {
            rdoMasculino.setSelected(true);
        }else if(atletaEmEdicao.getSexo()== 'F')
        {
            rdoFeminino.setSelected(true);
        }
        int personal = atletaEmEdicao.getId_personal();
        cboPersonal.setSelectedIndex(personal);
        txtTelefone.setText(atletaEmEdicao.getTelefone());
        txtCPF.setText(atletaEmEdicao.getCpf());
        txtRG.setText(atletaEmEdicao.getRg());
        txtOrgaoExpedidor.setText(atletaEmEdicao.getOrgaoExpedidor());
        String dataExpedicao = formatador.format(atletaEmEdicao.getDataExpedicao());
        txtDataExpedicao.setText(dataExpedicao);
        txtEmail.setText(atletaEmEdicao.getEmail());
        String estadoCivil = atletaEmEdicao.getEstadoCivil();
        cboEstadoCivil.setSelectedItem(estadoCivil);
        txtEndereco.setText(atletaEmEdicao.getEndereco());
        String numero = Integer.toString(atletaEmEdicao.getNumero());
        txtNumeroCasa.setText(numero);
        txtBairro.setText(atletaEmEdicao.getBairro());
        txtCidade.setText(atletaEmEdicao.getCidade());
        String uf = atletaEmEdicao.getUf();
        cboUF.setSelectedItem(uf);
        txtCEP.setText(atletaEmEdicao.getCep());
        String nacionalidade = atletaEmEdicao.getNacionalidade();
        cboNacionalidade.setSelectedItem(nacionalidade);
    }
    
    public void salvarAtleta(){ 
        try{
            this.recuperarCamposTela();
            AtletaBO atletaBO = new AtletaBO();           
            atletaBO.cadastrarAtleta(atletaEmEdicao);
            JOptionPane.showMessageDialog(this, "Atleta cadastrado com sucesso!", "Cadastro de Atletas", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        }catch(Exception e){
            String mensagem = "Erro inesperado! Informe a mensagem de erro ao administrador do sistema.";
            mensagem += "\nMensagem de erro:\n" + e.getMessage();
            JOptionPane.showMessageDialog(this, mensagem, "Cadastro de Atletas", JOptionPane.ERROR_MESSAGE);                            
        }
    }
    
    public void editarAtleta(){
        try{
            this.recuperarCamposTela();
            AtletaBO atletaBO = new AtletaBO();           
            atletaBO.editarAtleta(atletaEmEdicao);
            JOptionPane.showMessageDialog(this, "Atleta alterado com sucesso!", "Cadastro de Atletas", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        }catch(Exception e){
            String mensagem = "Erro inesperado! Informe a mensagem de erro ao administrador do sistema.";
            mensagem += "\nMensagem de erro:\n" + e.getMessage();
            JOptionPane.showMessageDialog(this, mensagem, "Cadastro de Atletas", JOptionPane.ERROR_MESSAGE);                              
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
        for (PersonalTrainer personal : personals) {
            cboPersonal.addItem(personal.getNome());
        }
    }
    
    public void recuperarCamposTela() throws ParseException
    {
        atletaEmEdicao.setNome(txtNome.getText());
        atletaEmEdicao.setApelido(txtApelido.getText());
        atletaEmEdicao.setNomePai(txtNomePai.getText());
        atletaEmEdicao.setNomeMae(txtNomeMae.getText());
        atletaEmEdicao.setDataNascimento(formatador.parse(txtDataNascimento.getText()));
        atletaEmEdicao.setDataInicio(formatador.parse(txtDataInicio.getText()));
        if (rdoFeminino.isSelected()) {
            atletaEmEdicao.setSexo('F');
        } else if (rdoMasculino.isSelected()) {
            atletaEmEdicao.setSexo('M');
        }
        atletaEmEdicao.setId_personal(cboPersonal.getSelectedIndex());
        atletaEmEdicao.setTelefone(txtTelefone.getText());
        atletaEmEdicao.setCpf(txtCPF.getText());
        atletaEmEdicao.setRg(txtRG.getText());
        atletaEmEdicao.setOrgaoExpedidor(txtOrgaoExpedidor.getText());
        atletaEmEdicao.setDataExpedicao(formatador.parse(txtDataExpedicao.getText()));
        atletaEmEdicao.setEmail(txtEmail.getText());
        String estadoCivil = (String) cboEstadoCivil.getSelectedItem();
        atletaEmEdicao.setEstadoCivil(estadoCivil);
        atletaEmEdicao.setEndereco(txtEndereco.getText());
        int numero = Integer.parseInt(txtNumeroCasa.getText());
        atletaEmEdicao.setNumero(numero);
        atletaEmEdicao.setBairro(txtBairro.getText());
        atletaEmEdicao.setCidade(txtCidade.getText());
        String uf = (String) cboUF.getSelectedItem();
        atletaEmEdicao.setUf(uf);
        atletaEmEdicao.setCep(txtCEP.getText());
        String nacionalidade = cboNacionalidade.getSelectedItem().toString();
        atletaEmEdicao.setNacionalidade(nacionalidade);       
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

        pnlCadastroClientes = new javax.swing.JTabbedPane();
        pnlAtletaIdentificacao = new javax.swing.JPanel();
        lblCodigo = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        lblNome = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        lblDataNascimento = new javax.swing.JLabel();
        txtDataNascimento = new javax.swing.JFormattedTextField();
        lblSexo = new javax.swing.JLabel();
        rdoFeminino = new javax.swing.JRadioButton();
        rdoMasculino = new javax.swing.JRadioButton();
        lblDataNascimento1 = new javax.swing.JLabel();
        txtDataInicio = new javax.swing.JFormattedTextField();
        lblNomePai = new javax.swing.JLabel();
        txtNomePai = new javax.swing.JTextField();
        lblNomeMae = new javax.swing.JLabel();
        txtNomeMae = new javax.swing.JTextField();
        lblTelefone = new javax.swing.JLabel();
        txtTelefone = new javax.swing.JFormattedTextField();
        lblApelido = new javax.swing.JLabel();
        txtApelido = new javax.swing.JTextField();
        lblpersonal = new javax.swing.JLabel();
        cboPersonal = new javax.swing.JComboBox<>();
        pnlAtletaDadosPessoais = new javax.swing.JPanel();
        lblCPF = new javax.swing.JLabel();
        txtCPF = new javax.swing.JFormattedTextField();
        lblRG = new javax.swing.JLabel();
        txtRG = new javax.swing.JTextField();
        lblEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lblExpedicao = new javax.swing.JLabel();
        txtDataExpedicao = new javax.swing.JFormattedTextField();
        txtEstadoCivil = new javax.swing.JLabel();
        cboEstadoCivil = new javax.swing.JComboBox<>();
        lblEndereco = new javax.swing.JLabel();
        txtEndereco = new javax.swing.JTextField();
        lblBairro = new javax.swing.JLabel();
        txtBairro = new javax.swing.JTextField();
        lblNumeroCasa = new javax.swing.JLabel();
        txtNumeroCasa = new javax.swing.JTextField();
        txtOrgaoExpedidor = new javax.swing.JTextField();
        lblOrgaoExpedidor = new javax.swing.JLabel();
        txtCidade = new javax.swing.JTextField();
        lblCidade = new javax.swing.JLabel();
        lblCidade1 = new javax.swing.JLabel();
        cboUF = new javax.swing.JComboBox<>();
        lblCidade2 = new javax.swing.JLabel();
        cboNacionalidade = new javax.swing.JComboBox<>();
        lblCEP = new javax.swing.JLabel();
        txtCEP = new javax.swing.JFormattedTextField();
        btnSalvar = new javax.swing.JButton();
        btnFechar = new javax.swing.JButton();
        lblCadastroDeAtletas = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro - Atletas");
        setResizable(false);

        pnlAtletaIdentificacao.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lblCodigo.setText("Codigo");

        txtCodigo.setEditable(false);

        lblNome.setText("Nome");

        lblDataNascimento.setText("Data Nascimento");

        try {
            txtDataNascimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtDataNascimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDataNascimentoActionPerformed(evt);
            }
        });

        lblSexo.setText("Sexo");

        rdoFeminino.setText("Feminino");

        rdoMasculino.setText("Masculino");

        lblDataNascimento1.setText("Data Inicio");

        try {
            txtDataInicio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtDataInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDataInicioActionPerformed(evt);
            }
        });

        lblNomePai.setText("Nome do Pai");

        lblNomeMae.setText("Nome da Mãe");

        lblTelefone.setText("Telefone");

        try {
            txtTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)#-####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        lblApelido.setText("Apelido");

        lblpersonal.setText("Personal Trainer");

        cboPersonal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout pnlAtletaIdentificacaoLayout = new javax.swing.GroupLayout(pnlAtletaIdentificacao);
        pnlAtletaIdentificacao.setLayout(pnlAtletaIdentificacaoLayout);
        pnlAtletaIdentificacaoLayout.setHorizontalGroup(
            pnlAtletaIdentificacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAtletaIdentificacaoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAtletaIdentificacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAtletaIdentificacaoLayout.createSequentialGroup()
                        .addGroup(pnlAtletaIdentificacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCodigo)
                            .addComponent(lblNomePai)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 548, Short.MAX_VALUE))
                    .addGroup(pnlAtletaIdentificacaoLayout.createSequentialGroup()
                        .addGroup(pnlAtletaIdentificacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSexo)
                            .addGroup(pnlAtletaIdentificacaoLayout.createSequentialGroup()
                                .addComponent(rdoFeminino)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdoMasculino))
                            .addComponent(lblTelefone)
                            .addGroup(pnlAtletaIdentificacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtDataNascimento, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblDataNascimento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(112, 112, 112)
                        .addGroup(pnlAtletaIdentificacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlAtletaIdentificacaoLayout.createSequentialGroup()
                                .addGroup(pnlAtletaIdentificacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblDataNascimento1)
                                    .addComponent(txtDataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(pnlAtletaIdentificacaoLayout.createSequentialGroup()
                                .addGroup(pnlAtletaIdentificacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblpersonal)
                                    .addComponent(cboPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(pnlAtletaIdentificacaoLayout.createSequentialGroup()
                        .addGroup(pnlAtletaIdentificacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtNomeMae, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNomeMae, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlAtletaIdentificacaoLayout.createSequentialGroup()
                                .addGroup(pnlAtletaIdentificacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNome)
                                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pnlAtletaIdentificacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblApelido)
                                    .addComponent(txtApelido, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txtNomePai, javax.swing.GroupLayout.Alignment.LEADING))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        pnlAtletaIdentificacaoLayout.setVerticalGroup(
            pnlAtletaIdentificacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAtletaIdentificacaoLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lblCodigo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlAtletaIdentificacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNome)
                    .addComponent(lblApelido))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlAtletaIdentificacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtApelido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblNomePai)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNomePai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(lblNomeMae)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNomeMae, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addGroup(pnlAtletaIdentificacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAtletaIdentificacaoLayout.createSequentialGroup()
                        .addComponent(lblDataNascimento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlAtletaIdentificacaoLayout.createSequentialGroup()
                        .addComponent(lblDataNascimento1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblSexo)
                .addGap(2, 2, 2)
                .addGroup(pnlAtletaIdentificacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoFeminino)
                    .addComponent(rdoMasculino)
                    .addComponent(lblpersonal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlAtletaIdentificacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTelefone))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pnlCadastroClientes.addTab("Identificação", pnlAtletaIdentificacao);

        lblCPF.setText("CPF");

        try {
            txtCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        lblRG.setText("RG");

        lblEmail.setText("Email");

        lblExpedicao.setText("Data Expedicao");

        try {
            txtDataExpedicao.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        txtEstadoCivil.setText("Estado Civil");

        cboEstadoCivil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Solteiro(a)", "Casado(a)", "Viúvo(a)", "Divorciado(a)" }));

        lblEndereco.setText("Endereco");

        txtEndereco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEnderecoActionPerformed(evt);
            }
        });

        lblBairro.setText("Bairro");

        txtBairro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBairroActionPerformed(evt);
            }
        });

        lblNumeroCasa.setText("Número");

        txtNumeroCasa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumeroCasaActionPerformed(evt);
            }
        });

        lblOrgaoExpedidor.setText("Orgao Expedidor");

        txtCidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCidadeActionPerformed(evt);
            }
        });

        lblCidade.setText("Cidade");

        lblCidade1.setText("UF");

        cboUF.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));

        lblCidade2.setText("Nacionalidade");

        cboNacionalidade.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Afeganistão", "África do Sul", "Akrotiri", "Albânia", "Alemanha", "Andorra", "Angola", "Anguila", "Antárctida", "Antígua e Barbuda", "Antilhas Neerlandesas", "Arábia Saudita", "Arctic Ocean", "Argélia", "Argentina", "Arménia", "Aruba", "Ashmore and Cartier Islands", "Atlantic Ocean", "Austrália", "Áustria", "Azerbaijão", "Baamas", "Bangladeche", "Barbados", "Barém", "Bélgica", "Belize", "Benim", "Bermudas", "Bielorrússia", "Birmânia", "Bolívia", "Bósnia e Herzegovina", "Botsuana", "Brasil", "Brunei", "Bulgária", "Burquina Faso", "Burúndi", "Butão", "Cabo Verde", "Camarões", "Camboja", "Canadá", "Catar", "Cazaquistão", "Chade", "Chile", "China", "Chipre", "Clipperton Island", "Colômbia", "Comores", "Congo-Brazzaville", "Congo-Kinshasa", "Coral Sea Islands", "Coreia do Norte", "Coreia do Sul", "Costa do Marfim", "Costa Rica", "Croácia", "Cuba", "Dhekelia", "Dinamarca", "Domínica", "Egipto", "Emiratos Árabes Unidos", "Equador", "Eritreia", "Eslováquia", "Eslovénia", "Espanha", "Estados Unidos", "Estónia", "Etiópia", "Faroé", "Fiji", "Filipinas", "Finlândia", "França", "Gabão", "Gâmbia", "Gana", "Gaza Strip", "Geórgia", "Geórgia do Sul e Sandwich do Sul", "Gibraltar", "Granada", "Grécia", "Gronelândia", "Guame", "Guatemala", "Guernsey", "Guiana", "Guiné", "Guiné Equatorial", "Guiné-Bissau", "Haiti", "Honduras", "Hong Kong", "Hungria", "Iémen", "Ilha Bouvet", "Ilha do Natal", "Ilha Norfolk", "Ilhas Caimão", "Ilhas Cook", "Ilhas dos Cocos", "Ilhas Falkland", "Ilhas Heard e McDonald", "Ilhas Marshall", "Ilhas Salomão", "Ilhas Turcas e Caicos", "Ilhas Virgens Americanas", "Ilhas Virgens Britânicas", "Índia", "Indian Ocean", "Indonésia", "Irão", "Iraque", "Irlanda", "Islândia", "Israel", "Itália", "Jamaica", "Jan Mayen", "Japão", "Jersey", "Jibuti", "Jordânia", "Kuwait", "Laos", "Lesoto", "Letónia", "Líbano", "Libéria", "Líbia", "Listenstaine", "Lituânia", "Luxemburgo", "Macau", "Macedónia", "Madagáscar", "Malásia", "Malávi", "Maldivas", "Mali", "Malta", "Man, Isle of", "Marianas do Norte", "Marrocos", "Maurícia", "Mauritânia", "Mayotte", "México", "Micronésia", "Moçambique", "Moldávia", "Mónaco", "Mongólia", "Monserrate", "Montenegro", "Mundo", "Namíbia", "Nauru", "Navassa Island", "Nepal", "Nicarágua", "Níger", "Nigéria", "Niue", "Noruega", "Nova Caledónia", "Nova Zelândia", "Omã", "Pacific Ocean", "Países Baixos", "Palau", "Panamá", "Papua-Nova Guiné", "Paquistão", "Paracel Islands", "Paraguai", "Peru", "Pitcairn", "Polinésia Francesa", "Polónia", "Porto Rico", "Portugal", "Quénia", "Quirguizistão", "Quiribáti", "Reino Unido", "República Centro-Africana", "República Checa", "República Dominicana", "Roménia", "Ruanda", "Rússia", "Salvador", "Samoa", "Samoa Americana", "Santa Helena", "Santa Lúcia", "São Cristóvão e Neves", "São Marinho", "São Pedro e Miquelon", "São Tomé e Príncipe", "São Vicente e Granadinas", "Sara Ocidental", "Seicheles", "Senegal", "Serra Leoa", "Sérvia", "Singapura", "Síria", "Somália", "Southern Ocean", "Spratly Islands", "Sri Lanca", "Suazilândia", "Sudão", "Suécia", "Suíça", "Suriname", "Svalbard e Jan Mayen", "Tailândia", "Taiwan", "Tajiquistão", "Tanzânia", "Território Britânico do Oceano Índico", "Territórios Austrais Franceses", "Timor Leste", "Togo", "Tokelau", "Tonga", "Trindade e Tobago", "Tunísia", "Turquemenistão", "Turquia", "Tuvalu", "Ucrânia", "Uganda", "União Europeia", "Uruguai", "Usbequistão", "Vanuatu", "Vaticano", "Venezuela", "Vietname", "Wake Island", "Wallis e Futuna", "West Bank", "Zâmbia", "Zimbabué" }));

        lblCEP.setText("CEP");

        try {
            txtCEP.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCEP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCEPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlAtletaDadosPessoaisLayout = new javax.swing.GroupLayout(pnlAtletaDadosPessoais);
        pnlAtletaDadosPessoais.setLayout(pnlAtletaDadosPessoaisLayout);
        pnlAtletaDadosPessoaisLayout.setHorizontalGroup(
            pnlAtletaDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAtletaDadosPessoaisLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAtletaDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAtletaDadosPessoaisLayout.createSequentialGroup()
                        .addComponent(lblEmail)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlAtletaDadosPessoaisLayout.createSequentialGroup()
                        .addGroup(pnlAtletaDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCPF)
                            .addComponent(txtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlAtletaDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblRG)
                            .addComponent(txtRG, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(45, 45, 45)
                        .addGroup(pnlAtletaDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblOrgaoExpedidor)
                            .addComponent(txtOrgaoExpedidor, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(pnlAtletaDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblExpedicao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtDataExpedicao, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlAtletaDadosPessoaisLayout.createSequentialGroup()
                        .addGroup(pnlAtletaDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEstadoCivil)
                            .addComponent(cboEstadoCivil, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlAtletaDadosPessoaisLayout.createSequentialGroup()
                        .addGroup(pnlAtletaDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlAtletaDadosPessoaisLayout.createSequentialGroup()
                                .addGroup(pnlAtletaDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtCidade, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblCidade, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(90, 90, 90)
                                .addGroup(pnlAtletaDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblCidade1)
                                    .addComponent(cboUF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(100, 100, 100)
                                .addGroup(pnlAtletaDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblCEP)
                                    .addComponent(txtCEP, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(cboNacionalidade, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblCidade2)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 524, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlAtletaDadosPessoaisLayout.createSequentialGroup()
                                .addGroup(pnlAtletaDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblEndereco))
                                .addGap(18, 18, 18)
                                .addGroup(pnlAtletaDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNumeroCasa)
                                    .addComponent(txtNumeroCasa, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pnlAtletaDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblBairro)
                                    .addComponent(txtBairro))))
                        .addGap(0, 81, Short.MAX_VALUE))))
        );
        pnlAtletaDadosPessoaisLayout.setVerticalGroup(
            pnlAtletaDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAtletaDadosPessoaisLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(pnlAtletaDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAtletaDadosPessoaisLayout.createSequentialGroup()
                        .addComponent(lblCPF)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlAtletaDadosPessoaisLayout.createSequentialGroup()
                        .addComponent(lblRG)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtRG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlAtletaDadosPessoaisLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(txtDataExpedicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblExpedicao)
                    .addGroup(pnlAtletaDadosPessoaisLayout.createSequentialGroup()
                        .addComponent(lblOrgaoExpedidor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtOrgaoExpedidor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(lblEmail)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtEstadoCivil)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboEstadoCivil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlAtletaDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlAtletaDadosPessoaisLayout.createSequentialGroup()
                        .addComponent(lblNumeroCasa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNumeroCasa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlAtletaDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblEndereco)
                        .addGroup(pnlAtletaDadosPessoaisLayout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlAtletaDadosPessoaisLayout.createSequentialGroup()
                        .addComponent(lblBairro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(21, 21, 21)
                .addGroup(pnlAtletaDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAtletaDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblCidade1)
                        .addComponent(lblCEP))
                    .addGroup(pnlAtletaDadosPessoaisLayout.createSequentialGroup()
                        .addComponent(lblCidade)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlAtletaDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboUF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCEP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(lblCidade2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboNacionalidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(72, Short.MAX_VALUE))
        );

        pnlCadastroClientes.addTab("Dados Pessoais", pnlAtletaDadosPessoais);

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/edu/ifnmg/kelvin/projeto/apresentacao/Imagens/ok3.png"))); // NOI18N
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnFechar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/edu/ifnmg/kelvin/projeto/apresentacao/Imagens/cancel20.png"))); // NOI18N
        btnFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFecharActionPerformed(evt);
            }
        });

        lblCadastroDeAtletas.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/edu/ifnmg/kelvin/projeto/apresentacao/Imagens/Atletas.fw.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlCadastroClientes)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFechar)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(89, 89, 89)
                                .addComponent(lblCadastroDeAtletas))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(107, 107, 107)
                                .addComponent(jLabel1)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCadastroDeAtletas, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnSalvar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnFechar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(13, 13, 13)
                        .addComponent(pnlCadastroClientes)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void txtDataNascimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDataNascimentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDataNascimentoActionPerformed

    private void txtDataInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDataInicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDataInicioActionPerformed

    private void txtEnderecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEnderecoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEnderecoActionPerformed

    private void txtBairroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBairroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBairroActionPerformed

    private void txtNumeroCasaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumeroCasaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumeroCasaActionPerformed

    private void txtCidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCidadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCidadeActionPerformed

    private void txtCEPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCEPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCEPActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        if(this.status == 1)
        {
            this.status = 0;
            this.salvarAtleta();
            
        }else if(this.status == 2)
        {
            this.status = 0;
            this.editarAtleta();
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnFecharActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFechar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<String> cboEstadoCivil;
    private javax.swing.JComboBox<String> cboNacionalidade;
    private javax.swing.JComboBox<String> cboPersonal;
    private javax.swing.JComboBox<String> cboUF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblApelido;
    private javax.swing.JLabel lblBairro;
    private javax.swing.JLabel lblCEP;
    private javax.swing.JLabel lblCPF;
    private javax.swing.JLabel lblCadastroDeAtletas;
    private javax.swing.JLabel lblCidade;
    private javax.swing.JLabel lblCidade1;
    private javax.swing.JLabel lblCidade2;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblDataNascimento;
    private javax.swing.JLabel lblDataNascimento1;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblEndereco;
    private javax.swing.JLabel lblExpedicao;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblNomeMae;
    private javax.swing.JLabel lblNomePai;
    private javax.swing.JLabel lblNumeroCasa;
    private javax.swing.JLabel lblOrgaoExpedidor;
    private javax.swing.JLabel lblRG;
    private javax.swing.JLabel lblSexo;
    private javax.swing.JLabel lblTelefone;
    private javax.swing.JLabel lblpersonal;
    private javax.swing.JPanel pnlAtletaDadosPessoais;
    private javax.swing.JPanel pnlAtletaIdentificacao;
    private javax.swing.JTabbedPane pnlCadastroClientes;
    private javax.swing.JRadioButton rdoFeminino;
    private javax.swing.JRadioButton rdoMasculino;
    private javax.swing.JTextField txtApelido;
    private javax.swing.JTextField txtBairro;
    private javax.swing.JFormattedTextField txtCEP;
    private javax.swing.JFormattedTextField txtCPF;
    private javax.swing.JTextField txtCidade;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JFormattedTextField txtDataExpedicao;
    private javax.swing.JFormattedTextField txtDataInicio;
    private javax.swing.JFormattedTextField txtDataNascimento;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEndereco;
    private javax.swing.JLabel txtEstadoCivil;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtNomeMae;
    private javax.swing.JTextField txtNomePai;
    private javax.swing.JTextField txtNumeroCasa;
    private javax.swing.JTextField txtOrgaoExpedidor;
    private javax.swing.JTextField txtRG;
    private javax.swing.JFormattedTextField txtTelefone;
    // End of variables declaration//GEN-END:variables

}
