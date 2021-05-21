/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Colaborador;

import Estructuras.ListaSimple;
import Modelos.Curso;
import Modelos.Horario;
import Modelos.Salon;
import Nodos.NodoSimple;
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Anclenius
 */
public class CursosEnSalon extends javax.swing.JFrame {

    /**
     * Creates new form CursosEnSalon
     * @param edificio es el nombre del edificio buscado
     * @param salon es el numero del salon del edificio
     */
    private static SimpleDateFormat format = new SimpleDateFormat("hh:mm");
    public CursosEnSalon(String edificio,int noSalon, Salon salon) {
        initComponents();
        jLabel3.setText("Cursos en el Salon: " + noSalon + ", Edificio: " + edificio);
        this.setVisible(true);
        fillTable(Controlador.Estructuras.obtenerHorarios(salon));
    }
    
    public void fillTable(ListaSimple lista) {
        NodoSimple aux = lista.inicio;
        while(aux != null) {
            addDatatoJTable((Horario)aux.getInfo());
            aux = aux.getSiguiente();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("CursosEnSalon");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel3.setText("Lista de Cursos Asignados");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Horario", "ID Curso", "Nombre", "Creditos", "Semestre", "Hora Inicial", "Hora Final", "Dia", "Catedratico"
            }
        ));
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setMinWidth(75);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(75);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(75);
            jTable1.getColumnModel().getColumn(1).setMinWidth(75);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(75);
            jTable1.getColumnModel().getColumn(1).setMaxWidth(75);
            jTable1.getColumnModel().getColumn(2).setMinWidth(100);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(200);
            jTable1.getColumnModel().getColumn(2).setMaxWidth(300);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(164, 164, 164)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(451, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(62, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void eliminar(){
        DefaultTableModel tb = (DefaultTableModel) jTable1.getModel();
        int a = jTable1.getRowCount()-1;
        for (int i = a; i >= 0; i--) {           
        tb.removeRow(tb.getRowCount()-1);
        } 
    }
    
    public void addDatatoJTable(Horario horario){
        Curso curso = horario.getCurso();
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        Object rowData[] = new Object[9];
            rowData[0] = horario.getCodigo();
            rowData[1] = curso.getCodigo();
            rowData[2] = curso.getNombre();
            rowData[3] = curso.getCreditos();
            rowData[4] = curso.getSemestre();
            rowData[5] = format.format(horario.getHoraInicial());
            rowData[6] = format.format(horario.getHoraFinal());
            rowData[7] = horario.getDia();
            rowData[8] = horario.getCatedratico().getNombre();
            model.addRow(rowData);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
