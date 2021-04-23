/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controllers.AgenteController;
import controllers.CarroController;
import controllers.DonoCarroController;
import controllers.MultaController;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import model.Agente;
import model.Carro;
import model.DonoCarro;
import model.Multa;

/**
 *
 * @author natan
 */
public class View extends javax.swing.JFrame {

    /**
     * Creates new form ProjetoPADB
     */
    public View() {
        initComponents();
        /* se tiver ordenado por placa, atualizar a placa e ela trocar de ordem na tabela, fica selecionada a linha q essa placa dtava antes de atualizar
        DefaultTableModel tableModelCarro = (DefaultTableModel) jTableCarro.getModel();
        jTableCarro.setRowSorter(new TableRowSorter(tableModelCarro));
        
        DefaultTableModel tableModelAgente = (DefaultTableModel) jTableAgente.getModel();
        jTableAgente.setRowSorter(new TableRowSorter(tableModelAgente));*/
        
        DefaultTableModel tableModelMulta =(DefaultTableModel) jTableMulta.getModel();
        jTableMulta.setRowSorter(new TableRowSorter(tableModelMulta));
        
        jPanel5.setVisible(false);
        jPanel6.setVisible(false);
        jPanel100.setVisible(false);
        btnMultaDeletar.setVisible(false);
        jButton2.setVisible(false);
        
        readCarroTable();
        readDonoTable();
        readAgenteTable();
        readMultaTable();
        ComboBoxDonos();
        ComboBoxPlaca();
        ComboBoxAgente();
    }
    public void ComboBoxDonos(){
        DonoCarroController donocarroDao = new DonoCarroController();
        jComboBox1.removeAllItems();
        for(DonoCarro dc: donocarroDao.read()){
            jComboBox1.addItem(dc);
        }
        jComboEditDonoCarro.removeAllItems();
        for(DonoCarro dc: donocarroDao.read()){
            jComboEditDonoCarro.addItem(dc);
        }
    }
    public void ComboBoxPlaca(){
        CarroController carroDao = new CarroController();
        jComboMultaPlaca.removeAllItems();
        for(Carro car: carroDao.read()){
            jComboMultaPlaca.addItem(car);
        }
    }
    public void ComboBoxAgente(){
        AgenteController agnDao = new AgenteController();
        jComboMultaAgente.removeAllItems();
        for(Agente agn: agnDao.read()){
            jComboMultaAgente.addItem(agn);
        }
    }
    
    public void readCarroTable(){
        DefaultTableModel modelo = (DefaultTableModel) jTableCarro.getModel();
        modelo.setNumRows(0);
        
        CarroController carroDao = new CarroController();
        
        for(Carro car: carroDao.read()){
            double contMulta = 0;
            for(Multa mu: carroDao.readMultaCarro(car.getPlaca())){
                contMulta += mu.getValor();
            }
            
            modelo.addRow(new Object[]{
                car.getPlaca(),
                car.getCpf_dono(),
                car.getMarca(),
                contMulta
            });
        }
    }
    public void readDonoTable(){
        DefaultTableModel modelo = (DefaultTableModel) jTableDono.getModel();
        modelo.setNumRows(0);
        
        DonoCarroController dcDao = new DonoCarroController();
        
        for(DonoCarro dc: dcDao.read()){
            modelo.addRow(new Object[]{
                dc.getCpf(),
                dc.getNome(),
                dc.getEndereco(),
                dcDao.readDonoCarros(dc.getCpf()).size()
            });
        }
    }
    public void readAgenteTable(){
        DefaultTableModel modelo =  (DefaultTableModel) jTableAgente.getModel();
        modelo.setNumRows(0);
        
        AgenteController agenteDao = new AgenteController();
        
        for(Agente agn: agenteDao.read()){
            List<Multa> agenteMultas = agenteDao.readMultaAgente(agn.getCpf());
            
            modelo.addRow(new Object[]{
                agn.getCpf(),
                agn.getNome(),
                agn.getEndereco(),
                agenteMultas.size()
            });
        }
    }
    public void readMultaTable(){
        DefaultTableModel modelo =  (DefaultTableModel) jTableMulta.getModel();
        modelo.setNumRows(0);
        
        MultaController multaDao = new MultaController();
        
        for(Multa mu: multaDao.read()){
            modelo.addRow(new Object[]{
                mu.getCodigo(),
                mu.getCpf_agente(),
                mu.getPlaca_carro(),
                mu.getDescricao(),
                mu.getValor()
            });
        }
    }
    public void readListCarrosDono(Long cpf){
        DefaultListModel listCarros = new DefaultListModel();
        listCarros.setSize(0);
        
        DonoCarroController dcDao = new DonoCarroController();
        
        if(dcDao.readDonoCarros(cpf).size() == 0){
            listCarros.addElement(" ");
            listCarrosDono.setModel(listCarros);
        } else {
            for(Carro car: dcDao.readDonoCarros(cpf)){
                listCarros.addElement(car.getPlaca());
                listCarrosDono.setModel(listCarros);
            }
        }
        
    }
    public void readListAgenteMultas(Long cpf){
        DefaultListModel listMultas = new DefaultListModel();
        listMultas.setSize(0);
        
        AgenteController agnDao = new AgenteController();
        
        for(Multa mu: agnDao.readMultaAgente(cpf)){
            listMultas.addElement(mu.getCodigo() + " / " + mu.getPlaca_carro());
            //listAgenteMultas.setModel(listMultas);
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

        jLayeredPane3 = new javax.swing.JLayeredPane();
        jLayeredPane5 = new javax.swing.JLayeredPane();
        jLabel12 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jSpinner1 = new javax.swing.JSpinner();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jPanel6 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        textEditPlaca = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        textEditCor = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jComboEditDonoCarro = new javax.swing.JComboBox<>();
        jLabel28 = new javax.swing.JLabel();
        lblEditTipo = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        lblEditMarca = new javax.swing.JLabel();
        btnAtualizarCarro = new javax.swing.JButton();
        btnDeletarCarro = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        lblEditDivida = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableCarro = new javax.swing.JTable();
        jLabel23 = new javax.swing.JLabel();
        jLayeredPane7 = new javax.swing.JLayeredPane();
        jLabel19 = new javax.swing.JLabel();
        jPanel100 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        btnAtualizaDono = new javax.swing.JButton();
        btnDeletaDono = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        lblEditCpfDono = new javax.swing.JLabel();
        textEditNomeDono = new javax.swing.JTextField();
        textEditEnderecoDono = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        listCarrosDono = new javax.swing.JList<>();
        jLabel31 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTableDono = new javax.swing.JTable();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableAgente = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        textEditNomeAgente = new javax.swing.JTextField();
        textEditEnderecoAgente = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        btnAtualizaAgente = new javax.swing.JButton();
        btnDeletaAgente = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        lblEditCpfAgente = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLayeredPane4 = new javax.swing.JLayeredPane();
        jPanel7 = new javax.swing.JPanel();
        textValorMulta = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        btnAplicaMulta = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jComboMultaPlaca = new javax.swing.JComboBox<>();
        jComboMultaAgente = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        textDescMulta = new javax.swing.JTextArea();
        btnMultaDeletar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableMulta = new javax.swing.JTable();
        jLayeredPane6 = new javax.swing.JLayeredPane();
        jPanel2 = new javax.swing.JPanel();
        textNomeDono = new javax.swing.JTextField();
        textCpfDono = new javax.swing.JTextField();
        textEnderecoDono = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnCadastroDono = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        textPlacaCarro = new javax.swing.JTextField();
        textCorCarro = new javax.swing.JTextField();
        textTipoCarro = new javax.swing.JTextField();
        textMarcaCarro = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        textNomeAgente = new javax.swing.JTextField();
        textCpfAgente = new javax.swing.JTextField();
        textEnderecoAgente = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        btnCadastroAgente = new javax.swing.JButton();
        jLabel37 = new javax.swing.JLabel();

        javax.swing.GroupLayout jLayeredPane3Layout = new javax.swing.GroupLayout(jLayeredPane3);
        jLayeredPane3.setLayout(jLayeredPane3Layout);
        jLayeredPane3Layout.setHorizontalGroup(
            jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jLayeredPane3Layout.setVerticalGroup(
            jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jLayeredPane5Layout = new javax.swing.GroupLayout(jLayeredPane5);
        jLayeredPane5.setLayout(jLayeredPane5Layout);
        jLayeredPane5Layout.setHorizontalGroup(
            jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jLayeredPane5Layout.setVerticalGroup(
            jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jLabel12.setText("jLabel12");

        jButton6.setText("jButton6");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.setMaximumSize(new java.awt.Dimension(32000, 32767));

        jLayeredPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLayeredPane1MouseClicked(evt);
            }
        });

        jLabel13.setText("Placa");

        textEditPlaca.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        textEditPlaca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textEditPlacaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textEditPlacaKeyTyped(evt);
            }
        });

        jLabel14.setText("Cor");

        jLabel15.setText("Dono");

        jLabel28.setText("Tipo");

        lblEditTipo.setText(" ");

        jLabel30.setText("Marca");

        lblEditMarca.setText(" ");

        btnAtualizarCarro.setText("Atualizar");
        btnAtualizarCarro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtualizarCarroActionPerformed(evt);
            }
        });

        btnDeletarCarro.setText("Deletar");
        btnDeletarCarro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletarCarroActionPerformed(evt);
            }
        });

        jButton1.setText("X");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel20.setText("Dívida");

        lblEditDivida.setText(" ");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnDeletarCarro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAtualizarCarro))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel13)
                            .addComponent(textEditPlaca, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                            .addComponent(jLabel28)
                            .addComponent(lblEditTipo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(54, 54, 54)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(lblEditMarca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(textEditCor, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel30))
                        .addGap(50, 50, 50)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboEditDonoCarro, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblEditDivida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addGap(94, 94, 94)
                                        .addComponent(jButton1))
                                    .addComponent(jLabel20))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel15)))
                            .addComponent(jButton1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboEditDonoCarro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textEditPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20)
                            .addComponent(jLabel28))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblEditTipo)
                            .addComponent(lblEditDivida)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textEditCor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblEditMarca)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAtualizarCarro)
                    .addComponent(btnDeletarCarro))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jTableCarro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Placa", "CPF Dono", "Marca", "Dívida"
            }
        ));
        jTableCarro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableCarroMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTableCarro);

        jLabel23.setText("Selecione um carro da lista para ver detalhes sobre.");

        jLayeredPane1.setLayer(jPanel6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jScrollPane2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel23, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE)
                    .addComponent(jLabel23)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Carros", jLayeredPane1);

        jLabel19.setText("Selecione um agente da lista caso deseje atualizá-lo ou deletá-lo");

        jLabel22.setText("CPF:");

        jLabel29.setText("Endereço");

        btnAtualizaDono.setText("Atualizar");
        btnAtualizaDono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtualizaDonoActionPerformed(evt);
            }
        });

        btnDeletaDono.setText("Deletar");
        btnDeletaDono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletaDonoActionPerformed(evt);
            }
        });

        jButton7.setText("X");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        lblEditCpfDono.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblEditCpfDono.setText(" ");
        lblEditCpfDono.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel21.setText("Nome");

        listCarrosDono.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { " " };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane5.setViewportView(listCarrosDono);

        jLabel31.setText("Seus carros");

        javax.swing.GroupLayout jPanel100Layout = new javax.swing.GroupLayout(jPanel100);
        jPanel100.setLayout(jPanel100Layout);
        jPanel100Layout.setHorizontalGroup(
            jPanel100Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel100Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel100Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel100Layout.createSequentialGroup()
                        .addGroup(jPanel100Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel29)
                            .addGroup(jPanel100Layout.createSequentialGroup()
                                .addGap(225, 225, 225)
                                .addComponent(jLabel22))
                            .addComponent(jLabel21))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel100Layout.createSequentialGroup()
                        .addGroup(jPanel100Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(textEditEnderecoDono)
                            .addGroup(jPanel100Layout.createSequentialGroup()
                                .addComponent(textEditNomeDono, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblEditCpfDono, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(41, 41, 41)))
                .addGroup(jPanel100Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel100Layout.createSequentialGroup()
                        .addComponent(btnDeletaDono)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAtualizaDono))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel100Layout.createSequentialGroup()
                        .addComponent(jLabel31)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton7))
                    .addComponent(jScrollPane5))
                .addContainerGap())
        );
        jPanel100Layout.setVerticalGroup(
            jPanel100Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel100Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(jPanel100Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel100Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel21)
                        .addComponent(jLabel22))
                    .addGroup(jPanel100Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton7)
                        .addComponent(jLabel31)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel100Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel100Layout.createSequentialGroup()
                        .addGroup(jPanel100Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textEditNomeDono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblEditCpfDono))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textEditEnderecoDono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel100Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDeletaDono)
                    .addComponent(btnAtualizaDono))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        jTableDono.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CPF", "Nome", "Endereço", "N° de carros"
            }
        ));
        jTableDono.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableDonoMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(jTableDono);

        jLayeredPane7.setLayer(jLabel19, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane7.setLayer(jPanel100, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane7.setLayer(jScrollPane6, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane7Layout = new javax.swing.GroupLayout(jLayeredPane7);
        jLayeredPane7.setLayout(jLayeredPane7Layout);
        jLayeredPane7Layout.setHorizontalGroup(
            jLayeredPane7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel100, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jLayeredPane7Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE))
                .addContainerGap())
        );
        jLayeredPane7Layout.setVerticalGroup(
            jLayeredPane7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane7Layout.createSequentialGroup()
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel100, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Motoristas", jLayeredPane7);

        jLayeredPane2.setMaximumSize(new java.awt.Dimension(32000, 32000));

        jTableAgente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CPF", "Nome", "Endereço", "Multas aplicad."
            }
        ));
        jTableAgente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableAgenteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableAgente);

        jLabel16.setText("Nome");

        jLabel17.setText("CPF:");

        jLabel18.setText("Endereço");

        btnAtualizaAgente.setText("Atualizar");
        btnAtualizaAgente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtualizaAgenteActionPerformed(evt);
            }
        });

        btnDeletaAgente.setText("Deletar");
        btnDeletaAgente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletaAgenteActionPerformed(evt);
            }
        });

        jButton4.setText("X");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        lblEditCpfAgente.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblEditCpfAgente.setText(" ");
        lblEditCpfAgente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(textEditNomeAgente, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(textEditEnderecoAgente)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addGap(108, 108, 108)
                                .addComponent(jButton4))
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                    .addComponent(btnDeletaAgente)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnAtualizaAgente))
                                .addComponent(lblEditCpfAgente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17)))
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblEditCpfAgente)
                    .addComponent(textEditNomeAgente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textEditEnderecoAgente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAtualizaAgente)
                    .addComponent(btnDeletaAgente))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel32.setText("Selecione um agente da lista caso deseje atualizá-lo ou deletá-lo");

        jLayeredPane2.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jPanel5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel32, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane2Layout = new javax.swing.GroupLayout(jLayeredPane2);
        jLayeredPane2.setLayout(jLayeredPane2Layout);
        jLayeredPane2Layout.setHorizontalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jLayeredPane2Layout.createSequentialGroup()
                        .addComponent(jLabel32)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jLayeredPane2Layout.setVerticalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addComponent(jLabel32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Agentes", jLayeredPane2);

        jLabel24.setText("Valor R$");

        jLabel25.setText("Placa do carro");

        jLabel26.setText("Descrição da multa");

        btnAplicaMulta.setText("Aplicar");
        btnAplicaMulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAplicaMultaActionPerformed(evt);
            }
        });

        jLabel11.setText("Agente");

        jComboMultaAgente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboMultaAgenteActionPerformed(evt);
            }
        });

        textDescMulta.setColumns(20);
        textDescMulta.setRows(5);
        jScrollPane3.setViewportView(textDescMulta);

        btnMultaDeletar.setText("Deletar");
        btnMultaDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMultaDeletarActionPerformed(evt);
            }
        });

        jButton2.setText("X");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textValorMulta, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel24))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboMultaPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel25)))
                            .addComponent(jScrollPane3))
                        .addGap(60, 60, 60)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAplicaMulta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                            .addComponent(jComboMultaAgente, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(btnMultaDeletar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2)))))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(jLabel25)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textValorMulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboMultaPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboMultaAgente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnMultaDeletar)
                            .addComponent(jButton2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAplicaMulta))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel27.setText("Aplicar multa");

        jTableMulta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Agente", "Placa do carro", "Descrição", "Valor"
            }
        ));
        jTableMulta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableMultaMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTableMulta);

        jLayeredPane4.setLayer(jPanel7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane4.setLayer(jLabel27, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane4.setLayer(jScrollPane4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane4Layout = new javax.swing.GroupLayout(jLayeredPane4);
        jLayeredPane4.setLayout(jLayeredPane4Layout);
        jLayeredPane4Layout.setHorizontalGroup(
            jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jLayeredPane4Layout.createSequentialGroup()
                        .addComponent(jLabel27)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE))
                .addContainerGap())
        );
        jLayeredPane4Layout.setVerticalGroup(
            jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane4Layout.createSequentialGroup()
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Multas", jLayeredPane4);

        textNomeDono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textNomeDonoActionPerformed(evt);
            }
        });

        jLabel2.setText("Nome:");

        jLabel3.setText("CPF:");

        jLabel4.setText("Endereço:");

        btnCadastroDono.setText("Salvar");
        btnCadastroDono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastroDonoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textNomeDono, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(0, 172, Short.MAX_VALUE))
                            .addComponent(textCpfDono)))
                    .addComponent(textEnderecoDono)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCadastroDono, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(textCpfDono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textNomeDono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textEnderecoDono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCadastroDono)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setText("Novo Motorista");

        jLabel6.setText("Placa");

        jLabel7.setText("Cor");

        jLabel8.setText("Tipo");

        jLabel9.setText("Marca");

        jButton5.setText("Salvar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel10.setText("Proprietário");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6)
                            .addComponent(textPlacaCarro)
                            .addComponent(textMarcaCarro, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textCorCarro, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(textTipoCarro, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textPlacaCarro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textCorCarro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textMarcaCarro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textTipoCarro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addComponent(jButton5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel5.setText("Novo Carro");

        jLabel34.setText("Nome");

        jLabel35.setText("CPF");

        jLabel36.setText("Endereço");

        btnCadastroAgente.setText("Salvar");
        btnCadastroAgente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastroAgenteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textEnderecoAgente)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel34)
                                .addGap(320, 320, 320)
                                .addComponent(jLabel35))
                            .addComponent(jLabel36))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addComponent(textNomeAgente)
                        .addGap(18, 18, 18)
                        .addComponent(textCpfAgente, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCadastroAgente, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(jLabel35))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textNomeAgente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textCpfAgente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel36)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textEnderecoAgente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCadastroAgente)
                .addContainerGap())
        );

        jLabel37.setText("Novo Agente");

        jLayeredPane6.setLayer(jPanel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane6.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane6.setLayer(jPanel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane6.setLayer(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane6.setLayer(jPanel10, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane6.setLayer(jLabel37, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane6Layout = new javax.swing.GroupLayout(jLayeredPane6);
        jLayeredPane6.setLayout(jLayeredPane6Layout);
        jLayeredPane6Layout.setHorizontalGroup(
            jLayeredPane6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jLayeredPane6Layout.createSequentialGroup()
                        .addGroup(jLayeredPane6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel5)
                            .addComponent(jLabel37))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jLayeredPane6Layout.setVerticalGroup(
            jLayeredPane6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane6Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel37)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(101, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Cadastros", jLayeredPane6);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("Cadastro de Carros");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textNomeDonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textNomeDonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textNomeDonoActionPerformed

    private void btnCadastroDonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastroDonoActionPerformed
        // TODO add your handling code here:
        DonoCarro dono = new DonoCarro();
        DonoCarroController dcDao = new DonoCarroController();
        
        dono.setCpf(Long.parseLong(textCpfDono.getText()));
        dono.setNome(textNomeDono.getText());
        dono.setEndereco(textEnderecoDono.getText());
        
        dcDao.create(dono);
        
        textCpfDono.setText("");
        textNomeDono.setText("");
        textEnderecoDono.setText("");
        
        readDonoTable();
        readCarroTable();
        ComboBoxDonos();
    }//GEN-LAST:event_btnCadastroDonoActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        Carro carro = new Carro();
        CarroController carDao =  new CarroController();
        
        carro.setPlaca(textPlacaCarro.getText());
        carro.setCor(textCorCarro.getText());
        carro.setTipo(textTipoCarro.getText());
        carro.setMarca(textMarcaCarro.getText());
        
        DonoCarro dc = (DonoCarro) jComboBox1.getSelectedItem();
        carro.setCpf_dono(dc.getCpf());
        
        carDao.create(carro);
        
        textPlacaCarro.setText("");
        textCorCarro.setText("");
        textTipoCarro.setText("");
        textMarcaCarro.setText("");
        
        readCarroTable();
        readDonoTable();
        ComboBoxPlaca();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jComboMultaAgenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboMultaAgenteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboMultaAgenteActionPerformed

    private void btnAplicaMultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAplicaMultaActionPerformed
        // TODO add your handling code here:
        Multa multa = new Multa();
        MultaController multaDao = new MultaController();
        
        multa.setValor(Double.parseDouble(textValorMulta.getText()));
        Carro car = (Carro) jComboMultaPlaca.getSelectedItem();
        multa.setPlaca_carro(car.getPlaca());
        Agente agn = (Agente) jComboMultaAgente.getSelectedItem();
        multa.setCpf_agente(agn.getCpf());
        multa.setDescricao(textDescMulta.getText());
        
        multaDao.create(multa);
        
        textValorMulta.setText("");
        textDescMulta.setText("");
        
        btnMultaDeletar.setVisible(false);
        readCarroTable();
        readAgenteTable();
        readMultaTable();
    }//GEN-LAST:event_btnAplicaMultaActionPerformed

    private void jTableCarroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableCarroMouseClicked
        // TODO add your handling code here:
        if(jTableCarro.getSelectedRow() != -1){
            CarroController carroDao = new CarroController();
            String placaCar = jTableCarro.getValueAt(jTableCarro.getSelectedRow(), 0).toString();
            Carro car = carroDao.readCarro(placaCar);
            
            DonoCarroController dcDao = new DonoCarroController();
            DonoCarro dc = (DonoCarro) dcDao.readDono(car.getCpf_dono());
            
            double contMulta = 0;
            for(Multa mu: carroDao.readMultaCarro(placaCar)){
                contMulta += mu.getValor();
            }
            
            textEditPlaca.setText(car.getPlaca());
            textEditCor.setText(car.getCor());
            lblEditTipo.setText(car.getTipo());
            lblEditMarca.setText(car.getMarca());
            lblEditDivida.setText(Double.toString(contMulta));
            jComboEditDonoCarro.getModel().setSelectedItem(dc);
            
            jPanel6.setVisible(true);
        }
    }//GEN-LAST:event_jTableCarroMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        jPanel6.setVisible(false);
        jTableCarro.clearSelection();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTableAgenteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableAgenteMouseClicked
        // TODO add your handling code here:
        if(jTableAgente.getSelectedRow() != -1){
            String cpf_agn = jTableAgente.getValueAt(jTableAgente.getSelectedRow(), 0).toString();
            lblEditCpfAgente.setText(cpf_agn);
            textEditNomeAgente.setText(jTableAgente.getValueAt(jTableAgente.getSelectedRow(), 1).toString());
            textEditEnderecoAgente.setText(jTableAgente.getValueAt(jTableAgente.getSelectedRow(), 2).toString());
            
            jPanel5.setVisible(true);
        }
    }//GEN-LAST:event_jTableAgenteMouseClicked

    private void btnCadastroAgenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastroAgenteActionPerformed
        // TODO add your handling code here:
        Agente agente = new Agente();
        AgenteController agnDao = new AgenteController();
        
        agente.setCpf(Long.parseLong(textCpfAgente.getText()));
        agente.setNome(textNomeAgente.getText());
        agente.setEndereco(textEnderecoAgente.getText());
        
        agnDao.create(agente);
        
        textCpfAgente.setText("");
        textNomeAgente.setText("");
        textEnderecoAgente.setText("");
        
        readAgenteTable();
        ComboBoxAgente();
    }//GEN-LAST:event_btnCadastroAgenteActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        jPanel5.setVisible(false);
        jTableAgente.clearSelection();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTableMultaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMultaMouseClicked
        // TODO add your handling code here:
        if(jTableMulta.getSelectedRow() != -1){
            btnMultaDeletar.setVisible(true);
            jButton2.setVisible(true);
            
            textValorMulta.setEnabled(false);
            textDescMulta.setEnabled(false);
            jComboMultaPlaca.setEnabled(false);
            jComboMultaAgente.setEnabled(false);
            btnAplicaMulta.setEnabled(false);
        }
    }//GEN-LAST:event_jTableMultaMouseClicked

    private void btnMultaDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMultaDeletarActionPerformed
        // TODO add your handling code here:
        if(jTableMulta.getSelectedRow() != -1){
            MultaController multaDao = new MultaController();
            
            String cod = jTableMulta.getValueAt(jTableMulta.getSelectedRow(), 0).toString();
            multaDao.delete(Long.parseLong(cod));
            
            readAgenteTable();
            readMultaTable();
            readCarroTable();
            
            btnMultaDeletar.setVisible(false);
            jButton2.setVisible(false);
            
            textValorMulta.setEnabled(true);
            textDescMulta.setEnabled(true);
            jComboMultaPlaca.setEnabled(true);
            jComboMultaAgente.setEnabled(true);
            btnAplicaMulta.setEnabled(true);
        }
    }//GEN-LAST:event_btnMultaDeletarActionPerformed

    private void textEditPlacaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textEditPlacaKeyPressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_textEditPlacaKeyPressed

    private void textEditPlacaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textEditPlacaKeyTyped
        // TODO add your handling code here:
        /*if(textEditPlaca.getText().length() >6){
            textEditPlaca.setText(textEditPlaca.getText().substring(0, 7));
        }*/
    }//GEN-LAST:event_textEditPlacaKeyTyped

    private void btnAtualizarCarroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarCarroActionPerformed
        // TODO add your handling code here:
        if(jTableCarro.getSelectedRow() != -1){
            int linhaSelec = (int) jTableCarro.getSelectedRow();
            CarroController carroDao = new CarroController();
            Carro carro = new Carro();

            String placa = jTableCarro.getValueAt(linhaSelec, 0).toString();
            Long antigo_dono = Long.parseLong(jTableCarro.getValueAt(linhaSelec, 1).toString());

            carro.setPlaca(textEditPlaca.getText());
            carro.setCor(textEditCor.getText());
            carro.setMarca(lblEditMarca.getText());
            carro.setTipo(lblEditTipo.getText());
            DonoCarro dc = (DonoCarro) jComboEditDonoCarro.getSelectedItem();
            carro.setCpf_dono(dc.getCpf());

            carroDao.update(carro, placa);
            
            readDonoTable();
            readCarroTable();
            readListCarrosDono(antigo_dono);
            readListCarrosDono(dc.getCpf());
            readMultaTable();
            ComboBoxPlaca();
            
            jTableCarro.changeSelection(linhaSelec, 0, false, false);
        }
    }//GEN-LAST:event_btnAtualizarCarroActionPerformed

    private void btnAtualizaAgenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizaAgenteActionPerformed
        // TODO add your handling code here:
        if(jTableAgente.getSelectedRow() != -1){
            int linhaSelec = (int) jTableAgente.getSelectedRow();
            AgenteController agnDao = new AgenteController();
            Agente agente = new Agente();
            
            agente.setCpf(Long.parseLong(lblEditCpfAgente.getText()));
            agente.setNome(textEditNomeAgente.getText());
            agente.setEndereco(textEditEnderecoAgente.getText());
            
            agnDao.update(agente);
            
            readAgenteTable();
            readMultaTable();
            ComboBoxAgente();
            
            jTableAgente.changeSelection(linhaSelec, 0, false, false);
        }
    }//GEN-LAST:event_btnAtualizaAgenteActionPerformed

    private void btnDeletarCarroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletarCarroActionPerformed
        // TODO add your handling code here:
        if(jTableCarro.getSelectedRow() != -1){
            CarroController carroDao = new CarroController();
            String placa = jTableCarro.getValueAt(jTableCarro.getSelectedRow(), 0).toString();
            Long cpf_dono = Long.parseLong(jTableCarro.getValueAt(jTableCarro.getSelectedRow(), 1).toString());
            
            carroDao.delete(placa);

            jPanel6.setVisible(false);
            
            readDonoTable();
            readCarroTable();
            readAgenteTable();
            readMultaTable();
            readListCarrosDono(cpf_dono);
            
            ComboBoxPlaca();
        }
    }//GEN-LAST:event_btnDeletarCarroActionPerformed

    private void btnDeletaAgenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletaAgenteActionPerformed
        // TODO add your handling code here:
        if(jTableAgente.getSelectedRow() != -1){
            AgenteController agnDao = new AgenteController();
            Long cpf = Long.parseLong(jTableAgente.getValueAt(jTableAgente.getSelectedRow(), 0).toString());
            
            agnDao.delete(cpf);
            
            jPanel5.setVisible(false);
            
            readAgenteTable();
            readMultaTable();
            readCarroTable();
            
            ComboBoxAgente();
        }
    }//GEN-LAST:event_btnDeletaAgenteActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        btnMultaDeletar.setVisible(false);
        jButton2.setVisible(false);
        
        textValorMulta.setEnabled(true);
        textDescMulta.setEnabled(true);
        jComboMultaPlaca.setEnabled(true);
        jComboMultaAgente.setEnabled(true);
        btnAplicaMulta.setEnabled(true);
        
        jTableMulta.clearSelection();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnAtualizaDonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizaDonoActionPerformed
        // TODO add your handling code here:
        if(jTableDono.getSelectedRow() != -1){
            int linhaSelec = (int) jTableDono.getSelectedRow();
            DonoCarroController dcDao = new DonoCarroController();
            DonoCarro dono = new DonoCarro();
            
            dono.setCpf(Long.parseLong(lblEditCpfDono.getText()));
            dono.setNome(textEditNomeDono.getText());
            dono.setEndereco(textEditEnderecoDono.getText());
            
            dcDao.update(dono);
            
            readDonoTable();
            readMultaTable();
            ComboBoxDonos();
            
            jTableDono.changeSelection(linhaSelec, 0, false, false);
        }
    }//GEN-LAST:event_btnAtualizaDonoActionPerformed

    private void btnDeletaDonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletaDonoActionPerformed
        // TODO add your handling code here:
        if(jTableDono.getSelectedRow() != -1){
            DonoCarroController dcDao = new DonoCarroController();
            Long cpf = Long.parseLong(jTableDono.getValueAt(jTableDono.getSelectedRow(), 0).toString());
            
            dcDao.delete(cpf);
            
            jPanel100.setVisible(false);
            
            readDonoTable();
            readCarroTable();
            readAgenteTable();
            readMultaTable();
            
            ComboBoxDonos();
            ComboBoxPlaca();
        }
    }//GEN-LAST:event_btnDeletaDonoActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        jPanel100.setVisible(false);
        jTableDono.clearSelection();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jTableDonoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableDonoMouseClicked
        // TODO add your handling code here:
        if(jTableDono.getSelectedRow() != -1){
            String cpf_dono = jTableDono.getValueAt(jTableDono.getSelectedRow(), 0).toString();
            lblEditCpfDono.setText(cpf_dono);
            textEditNomeDono.setText(jTableDono.getValueAt(jTableDono.getSelectedRow(), 1).toString());
            textEditEnderecoDono.setText(jTableDono.getValueAt(jTableDono.getSelectedRow(), 2).toString());
            
            readListCarrosDono(Long.parseLong(cpf_dono));
            jPanel100.setVisible(true);
        }
    }//GEN-LAST:event_jTableDonoMouseClicked

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
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new View().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAplicaMulta;
    private javax.swing.JButton btnAtualizaAgente;
    private javax.swing.JButton btnAtualizaDono;
    private javax.swing.JButton btnAtualizarCarro;
    private javax.swing.JButton btnCadastroAgente;
    private javax.swing.JButton btnCadastroDono;
    private javax.swing.JButton btnDeletaAgente;
    private javax.swing.JButton btnDeletaDono;
    private javax.swing.JButton btnDeletarCarro;
    private javax.swing.JButton btnMultaDeletar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox<Object> jComboBox1;
    private javax.swing.JComboBox<Object> jComboEditDonoCarro;
    private javax.swing.JComboBox<Object> jComboMultaAgente;
    private javax.swing.JComboBox<Object> jComboMultaPlaca;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JLayeredPane jLayeredPane3;
    private javax.swing.JLayeredPane jLayeredPane4;
    private javax.swing.JLayeredPane jLayeredPane5;
    private javax.swing.JLayeredPane jLayeredPane6;
    private javax.swing.JLayeredPane jLayeredPane7;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel100;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableAgente;
    private javax.swing.JTable jTableCarro;
    private javax.swing.JTable jTableDono;
    private javax.swing.JTable jTableMulta;
    private javax.swing.JLabel lblEditCpfAgente;
    private javax.swing.JLabel lblEditCpfDono;
    private javax.swing.JLabel lblEditDivida;
    private javax.swing.JLabel lblEditMarca;
    private javax.swing.JLabel lblEditTipo;
    private javax.swing.JList<String> listCarrosDono;
    private javax.swing.JTextField textCorCarro;
    private javax.swing.JTextField textCpfAgente;
    private javax.swing.JTextField textCpfDono;
    private javax.swing.JTextArea textDescMulta;
    private javax.swing.JTextField textEditCor;
    private javax.swing.JTextField textEditEnderecoAgente;
    private javax.swing.JTextField textEditEnderecoDono;
    private javax.swing.JTextField textEditNomeAgente;
    private javax.swing.JTextField textEditNomeDono;
    private javax.swing.JTextField textEditPlaca;
    private javax.swing.JTextField textEnderecoAgente;
    private javax.swing.JTextField textEnderecoDono;
    private javax.swing.JTextField textMarcaCarro;
    private javax.swing.JTextField textNomeAgente;
    private javax.swing.JTextField textNomeDono;
    private javax.swing.JTextField textPlacaCarro;
    private javax.swing.JTextField textTipoCarro;
    private javax.swing.JTextField textValorMulta;
    // End of variables declaration//GEN-END:variables
}
