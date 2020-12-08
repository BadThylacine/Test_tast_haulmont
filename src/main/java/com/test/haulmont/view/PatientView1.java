package com.test.haulmont.view;

import com.test.haulmont.controller.PatientController;
import com.test.haulmont.model.Patient;
import com.test.haulmont.model.Recipe;
import com.test.haulmont.repository.PatientRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import lombok.Setter;



@Route("patient2/list")
public class PatientView1 extends VerticalLayout {

    PatientController patientController;
    PatientView1 patientView;
    PatientRepository patientRepository;
    Patient patient;



    private TextField name = new TextField("", "Имя");
    private TextField surname = new TextField("", "Фамилия");
    private TextField patronymic = new TextField("", "Отчество");
    private TextField phoneNumber = new TextField("", "Номер телефона");
    private Grid<Patient> patientGrid = new Grid<>(Patient.class);
    private final TextField filter = new TextField();

    private Button save = new Button("Сохранить");
    private Button cancel = new Button("Отмена");
    private Button delete = new Button("Удалить");
    private Button edit = new Button("Edit");
    private Dialog buttonsAndFields = new Dialog();
    private final Button addNewPatient = new Button("Добавить нового пациента");

    private Binder<Patient> binder = new Binder<>(Patient.class);
    private VerticalLayout vr = new VerticalLayout(name,surname,patronymic,phoneNumber);
    private VerticalLayout vr2 = new VerticalLayout(name,surname,patronymic,phoneNumber);
    private HorizontalLayout hr = new HorizontalLayout(save,cancel);
    private HorizontalLayout hr2 = new HorizontalLayout(addNewPatient,delete,edit);

    @Setter
    public PatientView1.ChangeHandler changeHandler;

    public interface ChangeHandler {
        void onChange();
    }


public PatientView1(PatientController patientController, PatientRepository patientRepository){
        this.patientController = patientController;
        this.patientRepository = patientRepository;


        vr.setVisible(false);
        binder.bindInstanceFields(this);


        add(filter,patientGrid,hr2,vr);

        patientGrid.setItems(patientRepository.findAll());

        patientGrid.addComponentColumn(patient -> {
            if(patient.getRecipe() != null){
                Recipe list3 = patient.getRecipe();
                StringBuilder list4 = new StringBuilder(list3.getDescription());
            return new Label(list4.toString());}
            else return new Label("no recipes");
        }).setHeader("Рецепты");

        patientGrid.getColumnByKey("id").setVisible(false);
        patientGrid.getColumnByKey("name").setHeader("Имя");
        patientGrid.getColumnByKey("surname").setHeader("Фамилия");
        patientGrid.getColumnByKey("patronymic").setHeader("Отчество");
        patientGrid.getColumnByKey("phoneNumber").setHeader("Номер телефона");



        filter.setPlaceholder("Фильтр");
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> fillList(filter.getValue()));

        patientGrid
                .asSingleSelect()
                .addValueChangeListener(e -> editPatient(e.getValue()));

    buttonsAndFields.add(vr2,hr);
    buttonsAndFields.setWidth("500px");
    buttonsAndFields.setHeight("500px");


        buttonsAndFields.add(vr,hr);
        cancel.addClickListener(event -> buttonsAndFields.close());
        save.addClickListener(event -> save());
        save.addClickListener(event -> buttonsAndFields.close());
        save.addClickListener(event -> patientGrid.getDataProvider().refreshAll());
        edit.addClickListener(event -> save());
        delete.addClickListener(event -> delete());
        delete.addClickListener(event -> patientGrid.getDataProvider().refreshAll());
        addNewPatient.addClickListener(event -> buttonsAndFields.open());
        addNewPatient.addClickListener(event -> editPatient(new Patient()));
        buttonsAndFields.setCloseOnEsc(true);
        buttonsAndFields.setCloseOnOutsideClick(true);

    }

    private void fillList(String name) {
        if (name.isEmpty()) {
            patientGrid.setItems(patientRepository.findAll());
        } else {
            patientGrid.setItems(patientRepository.findByName(name));
        }
    }


    private void save() {
        patientRepository.save(patient);
        changeHandler.onChange();
    }
    private void delete() {
        patientRepository.delete(patient);
        changeHandler.onChange();
    }

    public void editPatient(Patient pat) {
        if (pat == null) {
            vr.setVisible(false);
            return;
        }

        if (pat.getName() != null) {
            this.patient = patientRepository.findById(pat.getId()).orElse(pat);
        } else {
            this.patient = pat;
        }

        binder.setBean(this.patient);

        vr.setVisible(true);

        surname.focus();

    }
}
