/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnd.controllers;

import anhnd.entity.RegistrationView;
import anhnd.entity.Registrations;
import anhnd.interfaces.rmi.IRegistrationsRMI;
import anhnd.view.FamilyHealthcareView;
import java.rmi.Naming;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author anhnd
 */
public class RegistrationController {

    private IRegistrationsRMI registrationsRMI;
    private FamilyHealthcareView view;
    private DefaultTableModel registrationModel;
    private boolean isAddNew = true;
    private static final String URL = "rmi://192.168.1.7:6789/RegistrationsRMI";

    public RegistrationController(FamilyHealthcareView view) {
        this.view = view;
    }

    public void init() {
        registrationModel = (DefaultTableModel) view.getTblRegistration().getModel();
        view.getCbSortByName().removeAllItems();
        view.getCbSortByName().addItem("Ascending");
        view.getCbSortByName().addItem("Descending");
        view.getRbMale().setSelected(true);
        view.getTblRegistration().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chooseItemOnTblRegistration(evt);
            }
        });
        getRegistrations();
        view.setVisible(true);

    }

    public void getRegistrations() {
        try {
            registrationsRMI = (IRegistrationsRMI) Naming.lookup(URL);
            ArrayList<Registrations> result = registrationsRMI.getAllRegistrations();
            registrationModel.setRowCount(0);
            for (Registrations registration : result) {
                RegistrationView registrationView = new RegistrationView();
                registrationView.setRegistrationId(registration.getRegistrationId());
                registrationView.setFullname(registration.getFullname());
                registrationView.setAddress(registration.getAddress());
                registrationView.setAge(registration.getAge());
                registrationView.setPhone(registration.getPhone());
                if (registration.getGender() == true) {
                    registrationView.setGender("Male");
                } else {
                    registrationView.setGender("Female");
                }
                registrationModel.addRow(registrationView.toVector());
            }
            view.getTblRegistration().updateUI();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void chooseItemOnTblRegistration(java.awt.event.MouseEvent evt) {
        try {
            isAddNew = false;
            int pos = view.getTblRegistration().getSelectedRow();
            String registrationId = (String) view.getTblRegistration().getValueAt(pos, 0);
            registrationsRMI = (IRegistrationsRMI) Naming.lookup(URL);
            Registrations registration = registrationsRMI.findByRegistrationID(registrationId);
            if(registration == null){
                JOptionPane.showMessageDialog(view, "Cannot view information of : " + registrationId);
            }else{
                view.getTxtRegistrationID().setText(registrationId);
                view.getTxtRegistrationID().setEditable(false);
                view.getTxtFullname().setText(registration.getFullname());
                view.getTxtAge().setText(registration.getAge().toString());
                if(registration.getGender() == true){
                    view.getRbMale().setSelected(true);
                    view.getRbFemale().setSelected(false);
                }else{
                    view.getRbMale().setSelected(false);
                    view.getRbFemale().setSelected(true);
                }
                view.getTxtEmail().setText(registration.getEmail());
                view.getTxtPhone().setText(registration.getPhone());
                view.getTxtAddress().setText(registration.getAddress());
                view.getTxtNumberMember().setText(registration.getNumberOfMember().toString());
                view.getTxtNumChildren().setText(registration.getNumberOfChildren().toString());
                view.getTxtNumAdult().setText(registration.getNumberOfAdults().toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
