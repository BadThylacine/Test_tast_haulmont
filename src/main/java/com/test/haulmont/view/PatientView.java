//package com.test.haulmont.view;
//
//import com.test.haulmont.controller.PatientController;
//import com.test.haulmont.model.Patient;
//import com.test.haulmont.repository.PatientRepository;
//import com.vaadin.flow.component.button.Button;
//import com.vaadin.flow.component.dialog.Dialog;
//import com.vaadin.flow.component.grid.Grid;
//import com.vaadin.flow.component.icon.VaadinIcon;
//import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
//import com.vaadin.flow.component.orderedlayout.VerticalLayout;
//import com.vaadin.flow.component.textfield.TextField;
//import com.vaadin.flow.data.binder.Binder;
//import com.vaadin.flow.data.value.ValueChangeMode;
//import com.vaadin.flow.router.Route;
//
//import lombok.Setter;
//import org.springframework.beans.factory.annotation.Autowired;
//
//
//
//@Route("patient1/list")
//public class PatientView extends VerticalLayout {
//
////    PatientController patientController;
////    PatientView patientView;
////    PatientRepository patientRepository;
////    Patient patient;
//
//    TextField textField = new TextField("", "some text");
//    public PatientView(){
//        add(textField);
//    }
//
////    private Grid<Patient> patientGrid = new Grid<>(Patient.class);
////    private final TextField filter = new TextField();
////
////    private TextField name = new TextField("", "Имя");
////    private TextField surname = new TextField("", "Фамилия");
////    private TextField patronymic = new TextField("", "Отчество");
////    private TextField phoneNumber = new TextField("", "Номер телефона");
////
////
////    private Button save = new Button("Сохранить");
////    private Button cancel = new Button("Отмена");
////    private Button delete = new Button("Удалить");
////    private Dialog buttonsAndFields = new Dialog();
////    private final Button addNewButton = new Button("Добавить нового доктора", VaadinIcon.PLUS.create());
////    private Binder<Patient> binder = new Binder<>(Patient.class);
////    private VerticalLayout vr = new VerticalLayout(name,surname,patronymic,phoneNumber);
////    private HorizontalLayout hv = new HorizontalLayout(save,delete,cancel);
////
////    @Setter
////    public ChangeHandler changeHandler;
////
////    public interface ChangeHandler {
////        void onChange();
////    }
////
////
////    @Autowired
////    public PatientView(PatientRepository patientRepository, PatientController patientController){
////
////
////        vr.add(name,surname,patronymic,phoneNumber);
////        hv.add(save,delete,cancel);
////
////
////        patientGrid.setColumns("id","name","surname","patronymic","phoneNumber");
////        patientGrid.getColumnByKey("id").setHeader("ID");
////        patientGrid.getColumnByKey("name").setHeader("Имя");
////        patientGrid.getColumnByKey("surname").setHeader("Фамилия");
////        patientGrid.getColumnByKey("patronymic").setHeader("Отчество");
////        patientGrid.getColumnByKey("phoneNumber").setHeader("Номер телефона");
////
////        patientGrid.setItems(patientRepository.findAll());
////
////        filter.setPlaceholder("Фильтр");
////        filter.setValueChangeMode(ValueChangeMode.EAGER);
////        filter.addValueChangeListener(e -> fillList(filter.getValue()));
////
////
////        add(filter,patientGrid,addNewButton);
////
////        patientGrid
////                .asSingleSelect()
////                .addValueChangeListener(e ->patientView.editPatient(e.getValue()));
////
////        //buttonsAndFields.add(vr,hv);
////        cancel.addClickListener(event -> buttonsAndFields.close());
////        save.addClickListener(event -> save());
////        delete.addClickListener(event -> delete());
////        addNewButton.addClickListener(event -> buttonsAndFields.open());
////        buttonsAndFields.setCloseOnEsc(true);
////        buttonsAndFields.setCloseOnOutsideClick(true);
////
////    }
////
////    private void fillList(String name) {
////        if (name.isEmpty()) {
////            patientGrid.setItems(patientRepository.findAll());
////        } else {
////            patientGrid.setItems(patientRepository.findByName(name));
////        }
////    }
////
////
////    private void save() {
////        patientRepository.save(patient);
////        changeHandler.onChange();
////    }
////    private void delete() {
////        patientRepository.delete(patient);
////        buttonsAndFields.close();
////        changeHandler.onChange();
////    }
////
////    public void editPatient(Patient patient) {
////        if (patient == null) {
////            setVisible(false);
////            return;
////        }
////
////        if (patient.getPhoneNumber() != null) {
////            this.patient = patientRepository.findById(patient.getId()).orElse(patient);
////        } else {
////            this.patient = patient;
////        }
////
////        binder.setBean(this.patient);
////
////        setVisible(true);
////
////        surname.focus();
////    }
//
//}
