/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Colaborador;

import Estructuras.ListaSimple;
import Modelos.Asignacion;
import Modelos.Curso;
import Modelos.Estudiante;
import Modelos.Horario;
import Nodos.NodoSimple;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Anclenius
 */
public class AprobadosReprobados extends javax.swing.JFrame {

    private int aprobados;
    private int reprobados;
    public AprobadosReprobados(int semestre) {
        initComponents();
        this.setVisible(true);
        aprobados = 0;
        reprobados = 0;
        ListaSimple lista = Controlador.Estructuras.estudiantes.exportarEstudiantes();
        fillTable(lista,semestre);
        jLabel3.setText("Listado de Aprobados y Reprobados del semestre " + semestre);
        jLabel4.setText("Total de Aprobados: " + aprobados);
        jLabel5.setText("Total de Reprobados: " + reprobados);
    }
    
    public void fillTable(ListaSimple lista,int semestre) {
        NodoSimple aux = lista.inicio;
        Estudiante estudiante;
        while(aux != null) {
            estudiante = (Estudiante) aux.getInfo();
            ListaSimple a = estudiante.getAsignaciones();
            NodoSimple aux2 = a.inicio;
            while(aux2 != null) {
                if(((Asignacion)aux2.getInfo()).getHorario().getCurso().getSemestre() == semestre){
                    int zona = ((Asignacion)aux2.getInfo()).getZona();
                    int eFinal = ((Asignacion)aux2.getInfo()).getFinal();
                    boolean flag = zona + eFinal >= 61;
                    addDatatoJTable(estudiante,((Asignacion)aux2.getInfo()).getHorario().getCurso().getNombre(),flag);
                }
                aux2 = aux2.getSiguiente();
            }
            aux = aux.getSiguiente();
        }
    }
    
    public void eliminar(){
        DefaultTableModel tb = (DefaultTableModel) jTable1.getModel();
        int a = jTable1.getRowCount()-1;
        for (int i = a; i >= 0; i--) {           
        tb.removeRow(tb.getRowCount()-1);
        } 
    }
    
    public void addDatatoJTable(Estudiante estudiante,String nombreCurso,boolean aprobo){
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        Object rowData[] = new Object[4];
            rowData[0] = estudiante.getId();
            rowData[1] = estudiante.getNombre();
            rowData[2] = nombreCurso;
            if(aprobo) {
                rowData[3] = "Aprobado";
                aprobados++;
            } else {
                rowData[3] = "Reprobado";
                reprobados++;
            }
            model.addRow(rowData);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Listado de Aprobados");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel3.setText("Listado de Aprobados y Reprobados del semestre 0");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel4.setText("Total de Aprobados: 0");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel5.setText("Total de Reprobados: 0");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Carnet", "Nombre", "Curso", "Estado"
            }
        ));
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(1).setMinWidth(200);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(200);
            jTable1.getColumnModel().getColumn(1).setMaxWidth(200);
            jTable1.getColumnModel().getColumn(2).setMinWidth(200);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(200);
            jTable1.getColumnModel().getColumn(2).setMaxWidth(200);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(131, 131, 131)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(71, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(148, 148, 148))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(72, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
