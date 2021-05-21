/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Excepciones.ClassroomNotFound;
import Excepciones.RepeatedSchedule;
import Excepciones.StudenNotExistsInHashTable;
import Excepciones.StudentAlreadyExists;
import Excepciones.SubjectNotFound;
import Excepciones.TeacherNotFound;
import Excepciones.UserAlreadyExists;
import GUI.Colaborador.Main_Colaborador;
import GUI.Estudiante.Main_Estudiante;
import GUI.SU.Main_SU;
import Modelos.Catedratico;
import Modelos.Curso;
import Modelos.Edificio;
import Modelos.Estudiante;
import Modelos.Horario;
import Modelos.SU;
import Modelos.Salon;
import Modelos.Usuario;
import java.io.File;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import static javax.swing.JFileChooser.APPROVE_OPTION;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import parser.FileHandler;

/**
 *
 * @author Anclenius
 */
public class Login extends javax.swing.JFrame {
    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login Usuario");

        jLabel1.setText("Inserte sus credenciales.");

        jLabel2.setText("Usuario");

        jLabel3.setText("Contraseña");

        jButton1.setText("Entrar al Sistema");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(105, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String user = jTextField1.getText();
        String pass = jTextField2.getText();
        Usuario usuario = Controlador.Estructuras.buscarUsuario(user, pass);
        if (usuario != null) {
            JOptionPane.showMessageDialog(null, "El usuario ha sido encontrado");
            if (usuario instanceof Estudiante) {
                new Main_Estudiante(usuario.getId());
            } else if (usuario instanceof Catedratico) {
                JOptionPane.showMessageDialog(null, "Este es un catedratico");
            } else if (usuario instanceof SU) {
                new Main_Colaborador(true);
            } else if (usuario instanceof Usuario) {
                new Main_Colaborador(false);
            }
            this.dispose();
        } else {
            //JOptionPane.showMessageDialog(null, "El usuario o contraseña no son validos");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    int a = 213;
//                    Estudiante estudiante = new Estudiante(a, "Juan Luis", "Quetzaltenango");
//                    Controlador.Estructuras.agregarEstudiante(estudiante);
//                    Estudiante estudiante1 = new Estudiante(a+3, "Juan Pablo", "Quetzaltenango");
//                    Controlador.Estructuras.agregarEstudiante(estudiante1);
                    Usuario usuario0 = new SU();
////                    Usuario usuario1 = new Estudiante(1, "capitan", "smash");
////                    Usuario usuario2 = new Catedratico(2, "fox", "corneria");
////                    Usuario usuario4 = new Usuario(3, "secre", "usac", "secre", true, false);
////                    Usuario usuario5 = new Usuario(a, "JuanLuis12", "xela", "elpro", false, true);
////                    Controlador.Estructuras.agregarUsuario(usuario1);
////                    Controlador.Estructuras.agregarUsuario(usuario2);
                         Controlador.Estructuras.agregarUsuario(usuario0);
////                    Controlador.Estructuras.agregarUsuario(usuario4);
////                    Controlador.Estructuras.agregarUsuario(usuario5);
//                    //Pruebas de edificios y salones
//                    Edificio edificio = new Edificio("T3");
////                    Edificio edificio1 = new Edificio("T1");
////                    Edificio edificio2 = new Edificio("T2");
////                    Edificio edificio3 = new Edificio("T4");
//                    Controlador.Estructuras.agregarEdificio(edificio);
////                    Controlador.Estructuras.agregarEdificio(edificio1);
////                    Controlador.Estructuras.agregarEdificio(edificio2);
////                    Controlador.Estructuras.agregarEdificio(edificio3);
//                    Controlador.Estructuras.agregarSalon("T3", new Salon(410, 66));
////                    Controlador.Estructuras.agregarSalon("T1", new Salon(410, 67));
////                    Controlador.Estructuras.agregarSalon("T1", new Salon(411, 67));
////                    Controlador.Estructuras.agregarSalon("T1", new Salon(412, 67));
////                    Controlador.Estructuras.agregarSalon("T1", new Salon(413, 67));
////
////                    //Pruebas de Catedraticos
//                    Catedratico cat1 = new Catedratico(1234, "Moises Vasquez", "USAC");
////                    Catedratico cat4 = new Catedratico(1234, "Moises Vasquez", "USAC");
////                    Catedratico cat2 = new Catedratico(1235, "Moises Vasquez", "USAC");
////                    Catedratico cat5 = new Catedratico(1230, "Moises Vasquez", "USAC");
////                    Catedratico cat3 = new Catedratico(1233, "Moises Vasquez", "USAC");
////                    Catedratico cat6 = new Catedratico(1231, "Moises Vasquez", "USAC");
////                    Catedratico cat7 = new Catedratico(1232, "Moises Vasquez", "USAC");
//                    Controlador.Estructuras.insertarCatedratico(cat1);
////                    Controlador.Estructuras.insertarCatedratico(cat2);
////                    Controlador.Estructuras.insertarCatedratico(cat3);
////                    Controlador.Estructuras.insertarCatedratico(cat4);
////                    
////                    Controlador.Estructuras.insertarCatedratico(cat6);
////                    Controlador.Estructuras.insertarCatedratico(cat7);
////                    Controlador.Estructuras.insertarCatedratico(cat5);
////                    
//                    Curso curso1 = new Curso(101, "Matematica Basica 1", 1, 7);
////                    Curso curso2 = new Curso(102, "Matematica Basica 2", 2, 7);
////                    Curso curso3 = new Curso(103, "Matematica Intermedia 1", 3, 10);
//                    Controlador.Estructuras.agregarCurso(curso1);
////                    Controlador.Estructuras.agregarCurso(curso2);
////                    Controlador.Estructuras.agregarCurso(curso3);
////
//                    Horario horario1 = new Horario(1, "10:00", "10:30", "Lunes", "T3", 410, 101,1234);
////                    Horario horario2 = new Horario(2, "10:30", "11:00", "Lunes", "T3", 410, 102);
//                    Horario horario3 = new Horario(3, "11:00", "11:30", "Lunes", "T3", 410, 101);
//                    Horario horario4 = new Horario(4, "11:30", "12:00", "Lunes", "T3", 410, 102);
//                    Horario horario5 = new Horario(5, "12:00", "12:30", "Lunes", "T3", 410, 103);
//                    Horario horario6 = new Horario(6, "12:30", "13:00", "Lunes", "T3", 410, 103);
//
//                    Controlador.Estructuras.agregarHorario(horario1);
//                    Controlador.Estructuras.agregarHorario(horario2);
//                    Controlador.Estructuras.agregarHorario(horario3);
//                    Controlador.Estructuras.agregarHorario(horario4);
//                    Controlador.Estructuras.agregarHorario(horario5);
//                    Controlador.Estructuras.agregarHorario(horario6);
//
//                    Controlador.Estructuras.asignarCurso(a, 1, 50, 11);
//                    Controlador.Estructuras.asignarCurso(a, 1, 31, 10);
//                    Controlador.Estructuras.asignarCurso(a, 1, 39, 8);
//                    Controlador.Estructuras.asignarCurso(a, 1, 20, 0);
//                    Controlador.Estructuras.asignarCurso(a, 2, 48, 20);
//                    Controlador.Estructuras.asignarCurso(a, 5, 60, 10);
//
//                    Controlador.Estructuras.asd();
                    
                    System.out.println("\n\n");
                    //Graficas
                    Controlador.Estructuras.estudiantes.print();
//                    Controlador.Estructuras.cursos.printCursos();
//                    Controlador.Estructuras.usuarios.printUsuarios();
//                    Controlador.Estructuras.edificios.printEdificios();
//                    Controlador.Estructuras.edificios.printSalones();
//                    Controlador.Estructuras.catedraticos.print();
                    
//                } catch (ClassroomNotFound | RepeatedSchedule | SubjectNotFound | ParseException ex) {
//                    ex.printStackTrace();
                } catch (SubjectNotFound ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassroomNotFound ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }  catch (TeacherNotFound ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                } catch (CloneNotSupportedException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UserAlreadyExists ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                } catch (StudentAlreadyExists ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                } catch (StudenNotExistsInHashTable ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
                new Login().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
