/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import controle.Controle;
import excecoes.AdultoNaoEncontradoException;
import excecoes.CadastroResponsavelException;
import excecoes.PreenchimentoCamposException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import modelo.Aluno;
import modelo.Responsabilidade;

/**
 *
 * @author Elizeu
 */
public class CadastroAlunoForm extends javax.swing.JFrame {

    private Controle controle;
    private String[] responsaveis_list;
    private Aluno aluno_em_cadastro;

    public CadastroAlunoForm() {
        controle = new Controle();
        aluno_em_cadastro = null;

        popular_propriedades();
        initComponents();
        popular_campos();
    }

    public void setAluno(Aluno aluno) {
        aluno_em_cadastro = aluno;
    }

    public void popular_propriedades() {
        responsaveis_list = controle.getAdultoStringArray();

        tabelaAddResponsavel = new javax.swing.JTable();
        tabelaAddResponsavel.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Nome", "Grau Parentesco", "CPF", "gp"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
    }

    public void popular_campos() {
        if (responsavelCampo != null) {
            responsavelCampo.setModel(new javax.swing.DefaultComboBoxModel<>(responsaveis_list));
        }

        tabelaAddResponsavel.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Nome", "Grau Parentesco", "CPF", "gp"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });

        if (aluno_em_cadastro != null) {
            this.matriculaTxt.setText(aluno_em_cadastro.getMatricula());
            this.campoNome.setText(aluno_em_cadastro.getNome());

            if (aluno_em_cadastro.getSexo() == 1) {
                this.sexo_m.setSelected(true);
                this.sexo_f.setSelected(false);
            } else {
                this.sexo_m.setSelected(false);
                this.sexo_f.setSelected(true);
            }

            this.campoNascDia.setText(String.valueOf(aluno_em_cadastro.getData_nascimento().getDate()));
            this.campoNascMes.setSelectedIndex(aluno_em_cadastro.getData_nascimento().getMonth());

            String[] anos = controle.getAnosPermitidosIdadeAluno();
            int indexAno = -1;
            for (int i = 0; i < anos.length; i++) {

                if (anos[i].equals(String.valueOf(aluno_em_cadastro.getData_nascimento().getYear() + 1900))) {
                    indexAno = i;
                    break;
                }
            }
            this.campoNascAno.setSelectedIndex(indexAno);

            this.campoCertidao.setText(String.valueOf(aluno_em_cadastro.getNumero_certidao()));

            if (aluno_em_cadastro.isAtivo()) {
                this.ativo_radio.setSelected(true);
                this.inativo_radio.setSelected(false);
            } else {
                this.ativo_radio.setSelected(false);
                this.inativo_radio.setSelected(true);
            }

            DefaultTableModel model = (DefaultTableModel) tabelaAddResponsavel.getModel();

            for (Responsabilidade resp : aluno_em_cadastro.getResponsavel()) {
                String nome = resp.getResponsavel().getNome();
                String grau_parentesco = Responsabilidade.GRAU_PARENTESCO_NOME[resp.getGrau_parentesco()];
                String cpf_string = String.valueOf(resp.getResponsavel().getCpf());
                String gp_string = String.valueOf(resp.getGrau_parentesco());
                model.addRow(new Object[]{nome, grau_parentesco, cpf_string, gp_string});
            }

        } else {
            this.matriculaTxt.setText("");
            this.campoNome.setText("");
            this.sexo_m.setSelected(true);
            this.sexo_f.setSelected(false);
            this.campoNascDia.setText("");
            this.campoNascMes.setSelectedIndex(0);
            this.campoNascAno.setSelectedIndex(0);
            this.campoCertidao.setText("");
            this.ativo_radio.setSelected(true);
            this.inativo_radio.setSelected(false);
//            tabelaResponsavel.setModel(new javax.swing.table.DefaultTableModel(
//                    new Object[][]{},
//                    new String[]{"Nome", "Grau Parentesco", "CPF", "parentesco"}
//            ) {
//                boolean[] canEdit = new boolean[]{false, false, false, false};
//
//                public boolean isCellEditable(int rowIndex, int columnIndex) {
//                    return canEdit[columnIndex];
//                }
//            });

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

        sexo_grupo = new javax.swing.ButtonGroup();
        ativo_grupo = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        matriculaTxt = new javax.swing.JLabel();
        campoNome = new javax.swing.JTextField();
        campoNascDia = new javax.swing.JTextField();
        campoCertidao = new javax.swing.JTextField();
        sexo_m = new javax.swing.JRadioButton();
        sexo_f = new javax.swing.JRadioButton();
        ativo_radio = new javax.swing.JRadioButton();
        inativo_radio = new javax.swing.JRadioButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        parentescoCampo = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaAddResponsavel = new javax.swing.JTable();
        pai_desconhecido = new javax.swing.JCheckBox();
        mae_desconhecida = new javax.swing.JCheckBox();
        responsavelCampo = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        campoNascMes = new javax.swing.JComboBox<>();
        campoNascAno = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setText("Cadastro de Aluno");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Matricula");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Nome");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Sexo");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Data Nasc.");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Cert. Nasc");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("Situação");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("Responsável");

        matriculaTxt.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        sexo_grupo.add(sexo_m);
        sexo_m.setSelected(true);
        sexo_m.setText("Masculino");

        sexo_grupo.add(sexo_f);
        sexo_f.setText("Feminino");

        ativo_grupo.add(ativo_radio);
        ativo_radio.setSelected(true);
        ativo_radio.setText("Ativo");

        ativo_grupo.add(inativo_radio);
        inativo_radio.setText("Inativo");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.black, null));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("Parentesco");

        parentescoCampo.setModel(new javax.swing.DefaultComboBoxModel<>(Responsabilidade.GRAU_PARENTESCO_NOME));

        jButton2.setText("Adicionar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        tabelaAddResponsavel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Grau Parentesco", "CPF", "gp"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaAddResponsavel.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tabelaAddResponsavel);
        if (tabelaAddResponsavel.getColumnModel().getColumnCount() > 0) {
            tabelaAddResponsavel.getColumnModel().getColumn(0).setResizable(false);
            tabelaAddResponsavel.getColumnModel().getColumn(1).setResizable(false);
            tabelaAddResponsavel.getColumnModel().getColumn(2).setResizable(false);
            tabelaAddResponsavel.getColumnModel().getColumn(3).setResizable(false);
            tabelaAddResponsavel.getColumnModel().getColumn(3).setPreferredWidth(0);
        }

        pai_desconhecido.setText("Pai falecido/desconhecido");

        mae_desconhecida.setText("Mãe falecida/desconhecida");

        responsavelCampo.setModel(new javax.swing.DefaultComboBoxModel<>(responsaveis_list));

        jButton1.setText("Remover");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(parentescoCampo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(responsavelCampo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mae_desconhecida)
                            .addComponent(pai_desconhecido))
                        .addGap(0, 18, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(responsavelCampo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(parentescoCampo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(jButton2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pai_desconhecido)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mae_desconhecida))
        );

        jButton3.setText("Cancelar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Salvar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        campoNascMes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro" }));
        campoNascMes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoNascMesActionPerformed(evt);
            }
        });

        campoNascAno.setModel(new javax.swing.DefaultComboBoxModel<>(controle.getAnosPermitidosIdadeAluno()));
        campoNascAno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoNascAnoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jButton4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton3))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addComponent(jLabel5)
                                .addComponent(jLabel6)
                                .addComponent(jLabel4))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(5, 5, 5)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(campoNascDia, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(campoNascMes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(campoNascAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(ativo_radio)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(inativo_radio))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(sexo_m)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(sexo_f))
                                            .addComponent(campoCertidao))))
                                .addGroup(layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(campoNome)))
                            .addGap(19, 19, 19)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel8)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(matriculaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel6))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(campoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(sexo_m)
                                            .addComponent(sexo_f))
                                        .addGap(4, 4, 4)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(campoNascDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(campoNascMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(campoNascAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(campoCertidao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel7)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(ativo_radio)
                                        .addComponent(inativo_radio))))
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3)
                            .addComponent(jButton4)))
                    .addComponent(matriculaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        int indice = responsavelCampo.getSelectedIndex();
        if (indice > -1) {
            int cpf = controle.getAdultoPorIndice(indice);
            String nome = (String) responsavelCampo.getSelectedItem();
            int parentesco_index = parentescoCampo.getSelectedIndex();
            String grau_parentesco = Responsabilidade.GRAU_PARENTESCO_NOME[parentesco_index];

            DefaultTableModel model = (DefaultTableModel) tabelaAddResponsavel.getModel();

            StringBuilder cpf_sb = new StringBuilder();
            cpf_sb.append(cpf);
            String cpf_string = cpf_sb.toString();

            StringBuilder gp_sb = new StringBuilder();
            gp_sb.append(parentesco_index);
            String gp_string = gp_sb.toString();

            System.out.println(nome + "-" + grau_parentesco + "-" + cpf_string + "-" + gp_string);

            model.addRow(new Object[]{nome, grau_parentesco, cpf_string, gp_string});
        } else {
            System.out.println(" Selecione alguma coisa ");
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        int sexo = sexo_m.isSelected() ? 1 : 0;
        boolean ativo = ativo_radio.isSelected();
        DefaultTableModel model2 = (DefaultTableModel) tabelaAddResponsavel.getModel();
        int rc = model2.getRowCount();
        String responsaveis[][] = new String[rc][4];

        for (int i = 0; i < rc; i++) {
            responsaveis[i][0] = (String) model2.getValueAt(i, 0);
            responsaveis[i][1] = (String) model2.getValueAt(i, 1);
            responsaveis[i][2] = (String) model2.getValueAt(i, 2);
            responsaveis[i][3] = (String) model2.getValueAt(i, 3);
//            System.out.println("Responsaveis " + model2.getValueAt(i, 0));
        }

        boolean pd = pai_desconhecido.isSelected();
        boolean md = mae_desconhecida.isSelected();

        String mes = String.valueOf(campoNascMes.getSelectedIndex() + 1);
        String ano = String.valueOf(campoNascAno.getSelectedItem());

        try {
            if (aluno_em_cadastro != null) {
                controle.editarAluno(
                        aluno_em_cadastro,
                        campoNome.getText(),
                        sexo,
                        campoNascDia.getText(),
                        mes,
                        ano,
                        campoCertidao.getText(),
                        ativo,
                        responsaveis,
                        pd,
                        md
                );
            } else {
                controle.cadastrarAluno(
                        campoNome.getText(),
                        sexo,
                        campoNascDia.getText(),
                        mes,
                        ano,
                        campoCertidao.getText(),
                        ativo,
                        responsaveis,
                        pd,
                        md
                );
            }
            dispose();
        } catch (PreenchimentoCamposException ex) {
            PopUps.showMessage(ex.getMessage());
        } catch (AdultoNaoEncontradoException ex) {
            PopUps.showMessage(ex.getMessage());
        } catch (Exception ex) {
            PopUps.showMessage(ex.getMessage());
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ((DefaultTableModel) tabelaAddResponsavel.getModel()).removeRow(tabelaAddResponsavel.getSelectedRow());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void campoNascMesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoNascMesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoNascMesActionPerformed

    private void campoNascAnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoNascAnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoNascAnoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup ativo_grupo;
    private javax.swing.JRadioButton ativo_radio;
    private javax.swing.JTextField campoCertidao;
    private javax.swing.JComboBox<String> campoNascAno;
    private javax.swing.JTextField campoNascDia;
    private javax.swing.JComboBox<String> campoNascMes;
    private javax.swing.JTextField campoNome;
    private javax.swing.JRadioButton inativo_radio;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JCheckBox mae_desconhecida;
    private javax.swing.JLabel matriculaTxt;
    private javax.swing.JCheckBox pai_desconhecido;
    private javax.swing.JComboBox<String> parentescoCampo;
    private javax.swing.JComboBox<String> responsavelCampo;
    private javax.swing.JRadioButton sexo_f;
    private javax.swing.ButtonGroup sexo_grupo;
    private javax.swing.JRadioButton sexo_m;
    private javax.swing.JTable tabelaAddResponsavel;
    // End of variables declaration//GEN-END:variables
}
