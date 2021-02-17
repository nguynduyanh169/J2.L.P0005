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
import java.awt.event.ActionEvent;
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
    private static final String REGISTRATIONID_REGEX = "^[a-zA-Z0-9 ]+$";
    private static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    private static final String NUMBER_REGEX = "^[0-9]+$";

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
        view.getBtnFindID().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonFindById(evt);
            }
        });
        view.getBtnNew().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNewRegistration(evt);
            }
        });
        view.getBtnSave().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSaveRegistration(evt);
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
            int pos = view.getTblRegistration().getSelectedRow();
            String registrationId = (String) view.getTblRegistration().getValueAt(pos, 0);
            registrationsRMI = (IRegistrationsRMI) Naming.lookup(URL);
            Registrations registration = registrationsRMI.findByRegistrationID(registrationId);
            if (registration == null) {
                JOptionPane.showMessageDialog(view, "Cannot view information of : " + registrationId);
            } else {
                isAddNew = false;
                view.getTxtRegistrationID().setText(registrationId);
                view.getTxtRegistrationID().setEditable(false);
                view.getTxtFullname().setText(registration.getFullname());
                view.getTxtAge().setText(registration.getAge().toString());
                if (registration.getGender() == true) {
                    view.getRbMale().setSelected(true);
                    view.getRbFemale().setSelected(false);
                } else {
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

    public void buttonFindById(java.awt.event.ActionEvent evt) {
        try {
            String registrationId = view.getTxtRegistrationID().getText().trim();
            registrationsRMI = (IRegistrationsRMI) Naming.lookup(URL);
            Registrations registration = registrationsRMI.findByRegistrationID(registrationId);
            if (registration == null) {
                JOptionPane.showMessageDialog(view, "Cannot view infomation of: " + registrationId);
            } else {
                isAddNew = false;
                view.getTxtRegistrationID().setText(registrationId);
                view.getTxtRegistrationID().setEditable(false);
                view.getTxtFullname().setText(registration.getFullname());
                view.getTxtAge().setText(registration.getAge().toString());
                if (registration.getGender() == true) {
                    view.getRbMale().setSelected(true);
                    view.getRbFemale().setSelected(false);
                } else {
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

    public void buttonNewRegistration(java.awt.event.ActionEvent evt) {
        isAddNew = true;
        view.getTxtRegistrationID().setText("");
        view.getTxtRegistrationID().setEditable(true);
        view.getTxtFullname().setText("");
        view.getTxtAge().setText("");
        view.getRbMale().setSelected(true);
        view.getRbFemale().setSelected(false);
        view.getTxtEmail().setText("");
        view.getTxtPhone().setText("");
        view.getTxtAddress().setText("");
        view.getTxtNumberMember().setText("");
        view.getTxtNumChildren().setText("");
        view.getTxtNumAdult().setText("");
    }

    public void buttonSaveRegistration(java.awt.event.ActionEvent evt) {
        boolean gender = true;
        boolean invalid = false;
        String errorMsg = "";
        String registrationId = view.getTxtRegistrationID().getText().trim();
        String fullName = view.getTxtFullname().getText().trim();
        String ageText = view.getTxtAge().getText().trim();
        if (view.getRbMale().isSelected()) {
            gender = true;
        } else {
            gender = false;
        }
        String email = view.getTxtEmail().getText().trim();
        String phone = view.getTxtPhone().getText().trim();
        String address = view.getTxtAddress().getText().trim();
        String numberMemberText = view.getTxtNumberMember().getText().trim();
        String numberChildrenText = view.getTxtNumChildren().getText().trim();
        String numberAdultText = view.getTxtNumAdult().getText().trim();
        System.out.println(ageText);
        if (registrationId.length() > 10 || registrationId.isEmpty() || !registrationId.matches(REGISTRATIONID_REGEX)) {
            errorMsg += "\n RegistrationID: max length is 10, not contains special characters";
            invalid = true;
        }
        if (fullName.length() > 50 || fullName.isEmpty()) {
            errorMsg += "\n FullName: max length is 50";
            invalid = true;
        }
        if (ageText.isEmpty() || !numberMemberText.matches(NUMBER_REGEX)) {
            System.out.println(numberMemberText.contains(NUMBER_REGEX));
            errorMsg += "\n Age: must be >= 0";
            invalid = true;
        }
        if (email.length() > 30 || email.isEmpty() || !email.matches(EMAIL_REGEX)) {
            errorMsg += "\n Email: max length is 30, contain only one “@” character, do not contain special characters (!, #, $)";
            invalid = true;
        }
        if (phone.length() > 15 || phone.isEmpty() || !phone.matches(NUMBER_REGEX)) {
            errorMsg += "\n o Phone: max length is 15, contain numeric characters only (0-9)";
            invalid = true;
        }
        if (numberMemberText.isEmpty() || !numberMemberText.matches(NUMBER_REGEX)) {
            errorMsg += "\n Number of member: must be >= 0";
            invalid = true;
        }
        if (numberChildrenText.isEmpty() || !numberChildrenText.matches(NUMBER_REGEX)) {
            errorMsg += "\n Number of children: must be >= 0";
            invalid = true;
        }
        if (numberAdultText.isEmpty() || !numberAdultText.matches(NUMBER_REGEX)) {
            errorMsg += "\n Number of adult: must be >= 0";
            invalid = true;
        }
        if (invalid == true) {
            JOptionPane.showMessageDialog(view, errorMsg);
        } else {
            int age = Integer.valueOf(ageText);
            int numberMember = Integer.valueOf(numberMemberText);
            int numberChildren = Integer.valueOf(numberChildrenText);
            int numberAdult = Integer.valueOf(numberAdultText);
            if(isAddNew == true){
                try {
                    Registrations registration = new Registrations(registrationId, fullName, phone, email, address, age, gender, numberMember, numberChildren, numberAdult);
                    registrationsRMI = (IRegistrationsRMI) Naming.lookup(URL);
                    boolean check = registrationsRMI.createRegistration(registration);
                    if(check == true){
                        getRegistrations();
                    }else{
                        JOptionPane.showMessageDialog(view, "Create Failed!");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                try {
                    Registrations registration = new Registrations(registrationId, fullName, phone, email, address, age, gender, numberMember, numberChildren, numberAdult);
                    registrationsRMI = (IRegistrationsRMI) Naming.lookup(URL);
                    boolean check = registrationsRMI.updateRegistration(registration);
                    if(check == true){
                        getRegistrations();
                    }else{
                        JOptionPane.showMessageDialog(view, "Update Failed!");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
