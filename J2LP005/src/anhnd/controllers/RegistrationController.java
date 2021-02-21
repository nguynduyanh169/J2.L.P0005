/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnd.controllers;

import anhnd.entity.RegistrationView;
import anhnd.entity.Registrations;
import anhnd.interfaces.rmi.IRegistrationsRMI;
import anhnd.utils.Constants;
import anhnd.view.FamilyHealthcareView;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.rmi.Naming;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
        view.getBtnRemove().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDeleteRegistration(evt);
            }
        });
        view.getBtnSearchName().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchRegistrationByName(evt);
            }
        });
        view.getBtnGetAll().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGetAllRegistrations(evt);
            }
        });
        view.getCbSortByName().addItemListener(new java.awt.event.ItemListener() {
            @Override
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                sortRegistrationByName(evt);
            }
        });
        view.getRbMale().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbMaleMouseClicked(evt);
            }
        });
        view.getRbFemale().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbFemaleMouseClicked(evt);
            }
        });
        getRegistrations();
        view.setVisible(true);

    }

    public void getRegistrations() {
        try {
            registrationsRMI = (IRegistrationsRMI) Naming.lookup(Constants.URL);
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
            registrationsRMI = (IRegistrationsRMI) Naming.lookup(Constants.URL);
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
            registrationsRMI = (IRegistrationsRMI) Naming.lookup(Constants.URL);
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

    public void buttonDeleteRegistration(java.awt.event.ActionEvent evt) {
        try {
            int pos = view.getTblRegistration().getSelectedRow();
            String registrationId = (String) view.getTblRegistration().getValueAt(pos, 0);
            int confirm = JOptionPane.showConfirmDialog(view, "Do you want to delete " + registrationId + " ?", "Confirm delete", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                registrationsRMI = (IRegistrationsRMI) Naming.lookup(Constants.URL);
                boolean check = registrationsRMI.removeRegistration(registrationId);
                if (check == true) {
                    getRegistrations();
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
                } else {
                    JOptionPane.showMessageDialog(view, "Delete failed!");
                }
            } else {
                getRegistrations();
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

        } catch (Exception e) {
            e.printStackTrace();
        }
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
        if (registrationId.length() > 10 || registrationId.isEmpty() || !registrationId.matches(Constants.REGISTRATIONID_REGEX)) {
            errorMsg += "\n RegistrationID: max length is 10, not contains special characters";
            invalid = true;
        }
        if (fullName.length() > 50 || fullName.isEmpty()) {
            errorMsg += "\n FullName: max length is 50";
            invalid = true;
        }
        if (ageText.isEmpty() || !ageText.matches(Constants.AGE_REGEX)) {
            errorMsg += "\n Age: must be >= 0";
            invalid = true;
        }
        if (email.length() > 30 || email.isEmpty() || !email.matches(Constants.EMAIL_REGEX)) {
            errorMsg += "\n Email: max length is 30, contain only one “@” character, do not contain special characters (!, #, $)";
            invalid = true;
        }
        if (phone.length() > 15 || phone.isEmpty() || !phone.matches(Constants.NUMBER_REGEX)) {
            errorMsg += "\n Phone: max length is 15, contain numeric characters only (0-9)";
            invalid = true;
        }
        if (numberMemberText.isEmpty() || !numberMemberText.matches(Constants.NUMBER_REGEX)) {
            errorMsg += "\n Number of member: must be >= 0";
            invalid = true;
        }
        if (numberChildrenText.isEmpty() || !numberChildrenText.matches(Constants.NUMBER_REGEX)) {
            errorMsg += "\n Number of children: must be >= 0";
            invalid = true;
        }
        if (numberAdultText.isEmpty() || !numberAdultText.matches(Constants.NUMBER_REGEX)) {
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
            if (numberChildren + numberAdult != numberMember) {
                JOptionPane.showMessageDialog(view, "Please enter correct number of member!");
            } else {
                if (isAddNew == true) {
                    try {
                        if (checkDuplicateId(registrationId) == true) {
                            Registrations registration = new Registrations(registrationId, fullName, phone, email, address, age, gender, numberMember, numberChildren, numberAdult);
                            registrationsRMI = (IRegistrationsRMI) Naming.lookup(Constants.URL);
                            boolean check = registrationsRMI.createRegistration(registration);
                            if (check == true) {
                                getRegistrations();
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
                            } else {
                                JOptionPane.showMessageDialog(view, "Create Failed!");
                            }
                        } else {
                            JOptionPane.showMessageDialog(view, "Registration " + registrationId + " has been exist!");
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        Registrations registration = new Registrations(registrationId, fullName, phone, email, address, age, gender, numberMember, numberChildren, numberAdult);
                        registrationsRMI = (IRegistrationsRMI) Naming.lookup(Constants.URL);
                        boolean check = registrationsRMI.updateRegistration(registration);
                        if (check == true) {
                            getRegistrations();
                        } else {
                            JOptionPane.showMessageDialog(view, "Update Failed!");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        }

    }

    public void buttonSearchRegistrationByName(java.awt.event.ActionEvent evt) {
        try {
            String keywords = view.getTxtSearchName().getText();
            registrationsRMI = (IRegistrationsRMI) Naming.lookup(Constants.URL);
            ArrayList<Registrations> result = registrationsRMI.findRegistrationByLikeName(keywords);
            if (result.size() > 0) {
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
            } else {
                JOptionPane.showMessageDialog(view, "Cannot find any registration with keywords: " + keywords);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void buttonGetAllRegistrations(java.awt.event.ActionEvent evt) {
        try {
            view.getTxtSearchName().setText("");
            view.getCbSortByName().setSelectedIndex(0);
            getRegistrations();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sortRegistrationByName(java.awt.event.ItemEvent evt) {
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            String selected = (String) evt.getItem();
            if (selected.equals("Ascending")) {
                ArrayList<RegistrationView> registrationViews = new ArrayList<>();
                for (int i = 0; i < registrationModel.getRowCount(); i++) {
                    String registrationId = (String) view.getTblRegistration().getValueAt(i, 0);
                    String fullName = (String) view.getTblRegistration().getValueAt(i, 1);
                    int age = (int) view.getTblRegistration().getValueAt(i, 2);
                    String gender = (String) view.getTblRegistration().getValueAt(i, 3);
                    String phone = (String) view.getTblRegistration().getValueAt(i, 4);
                    String address = (String) view.getTblRegistration().getValueAt(i, 5);
                    RegistrationView registrationView = new RegistrationView(registrationId, fullName, phone, address, age, gender);
                    registrationViews.add(registrationView);
                }
                sortAscendingByRegistrationName(registrationViews);
            } else if (selected.equals("Descending")) {
                ArrayList<RegistrationView> registrationViews = new ArrayList<>();
                for (int i = 0; i < registrationModel.getRowCount(); i++) {
                    String registrationId = (String) view.getTblRegistration().getValueAt(i, 0);
                    String fullName = (String) view.getTblRegistration().getValueAt(i, 1);
                    int age = (int) view.getTblRegistration().getValueAt(i, 2);
                    String gender = (String) view.getTblRegistration().getValueAt(i, 3);
                    String phone = (String) view.getTblRegistration().getValueAt(i, 4);
                    String address = (String) view.getTblRegistration().getValueAt(i, 5);
                    RegistrationView registrationView = new RegistrationView(registrationId, fullName, phone, address, age, gender);
                    registrationViews.add(registrationView);
                }
                sortDescendingByRegistrationName(registrationViews);
            }
        }
    }

    public void sortAscendingByRegistrationName(ArrayList<RegistrationView> registrationViews) {
        Collections.sort(registrationViews, new Comparator<RegistrationView>() {
            @Override
            public int compare(RegistrationView o1, RegistrationView o2) {
                return o1.getFullname().compareTo(o2.getFullname());
            }
        });
        registrationModel.setRowCount(0);
        for (RegistrationView registrationView : registrationViews) {
            registrationModel.addRow(registrationView.toVector());
        }
        view.getTblRegistration().updateUI();
    }

    public void sortDescendingByRegistrationName(ArrayList<RegistrationView> registrationViews) {
        Collections.sort(registrationViews, new Comparator<RegistrationView>() {
            @Override
            public int compare(RegistrationView o1, RegistrationView o2) {
                return o2.getFullname().compareTo(o1.getFullname());
            }
        });
        registrationModel.setRowCount(0);
        for (RegistrationView registrationView : registrationViews) {
            registrationModel.addRow(registrationView.toVector());
        }
        view.getTblRegistration().updateUI();
    }

    public void rbMaleMouseClicked(java.awt.event.MouseEvent evt) {
        view.getRbFemale().setSelected(false);
    }

    public void rbFemaleMouseClicked(java.awt.event.MouseEvent evt) {
        view.getRbMale().setSelected(false);
    }

    private boolean checkDuplicateId(String id) {
        boolean check = true;
        for (int i = 0; i < registrationModel.getRowCount(); i++) {
            String registrationId = (String) view.getTblRegistration().getValueAt(i, 0);
            if (registrationId.equals(id) == true) {
                check = false;
            }
        }
        return check;
    }

}
