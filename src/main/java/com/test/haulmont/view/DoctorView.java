package com.test.haulmont.view;

import com.test.haulmont.controller.DoctorController;
import com.test.haulmont.model.Doctor;
import com.test.haulmont.model.Recipe;
import com.test.haulmont.repository.DoctorRepository;
import com.vaadin.flow.component.KeyNotifier;
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
import org.springframework.beans.factory.annotation.Autowired;


import java.io.Serializable;

import java.util.List;


@Route("doctors/list")
public class DoctorView extends VerticalLayout implements KeyNotifier, Serializable {

    DoctorController doctorController;
    DoctorView doctorView;
    DoctorRepository doctorRepository;
    Doctor doctor;
    Recipe recipe;

    private Grid<Doctor> grid = new Grid<>(Doctor.class);
    private final TextField filter = new TextField();

    private TextField name = new TextField("", "Имя");
    private TextField surname = new TextField("", "Фамилия");
    private TextField patronymic = new TextField("", "Отчество");
    private TextField specialisation = new TextField("", "Специализация");
    private TextField t = new TextField("","osgsegse");


    private Button save = new Button("Сохранить");
    private Button cancel = new Button("Отмена");
    private Button delete = new Button("Удалить");
    private Dialog buttonsAndFields = new Dialog();
    private final Button addNewButton = new Button("Добавить нового доктора");
    private final Button edit = new Button("Edit");
    private Binder<Doctor> binder = new Binder<>(Doctor.class);
    private VerticalLayout verticalLayout = new VerticalLayout(name,surname,patronymic,specialisation);
    private VerticalLayout vr2 = new VerticalLayout(name,surname,patronymic,specialisation);
    private HorizontalLayout hv = new HorizontalLayout(save,cancel);

    @Setter
    public ChangeHandler changeHandler;

    public interface ChangeHandler {
        void onChange();
    }


@Autowired
public DoctorView(DoctorRepository doctorRepository, DoctorController doctorController){
        this.doctorRepository = doctorRepository;
        this.doctorController = doctorController;

    verticalLayout.setVisible(false);

    binder.bindInstanceFields(this);

    setSpacing(true);

//    save.getElement().getThemeList().add("primary");
//    edit.getElement().getThemeList().add("primary");
//    delete.getElement().getThemeList().add("error");


    List<Doctor> list1 = doctorRepository.findAll();

    grid.setItems(list1);


    grid.addComponentColumn(doctor -> {
        if(doctor.getRecipes() != null){
        List<Recipe> list3 = doctor.getRecipes();
        StringBuilder list4 = new StringBuilder();
        for(Recipe recipe : list3){
            list4.append(recipe.getDescription());
        }
        return new Label(list4.toString());}
        else return new Label("no recipes");
    }).setHeader("Recipes");

        grid.getColumnByKey("id").setVisible(false);
        grid.getColumnByKey("name").setHeader("Имя");
        grid.getColumnByKey("surname").setHeader("Фамилия");
        grid.getColumnByKey("patronymic").setHeader("Отчество");
        grid.getColumnByKey("specialisation").setHeader("Cпециализация");




        grid.setHeight("200px");
        filter.setPlaceholder("Фильтр");
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> fillList(filter.getValue()));

    add(filter,grid,addNewButton,delete,edit,verticalLayout);

    grid
        .asSingleSelect()
        .addValueChangeListener(e -> editDoctor(e.getValue()));


//    buttonsAndFields.setHeight("500px");
//    buttonsAndFields.setWidth("600px");
    buttonsAndFields.add(vr2,hv);

    save.getElement().getThemeList().add("primary");
    delete.getElement().getThemeList().add("error");
    setSpacing(true);
    cancel.addClickListener(event -> buttonsAndFields.close());
    save.addClickListener(event -> save());
    save.addClickListener(event -> buttonsAndFields.close());
    save.addClickListener(event -> grid.getDataProvider().refreshAll());
    edit.addClickListener(e -> save());
    delete.addClickListener(event -> delete());
    delete.addClickListener(event -> grid.getDataProvider().refreshAll());
    addNewButton.addClickListener(event -> buttonsAndFields.open());
    addNewButton.addClickListener(event -> editDoctor(new Doctor()));
    buttonsAndFields.setCloseOnEsc(true);
    buttonsAndFields.setCloseOnOutsideClick(true);

    }

    private void fillList(String name) {
        if (name.isEmpty()) {
            grid.setItems(doctorRepository.findAll());
        } else {
            grid.setItems(doctorRepository.findByName(name));
        }
    }


    private void save() {
        doctorRepository.save(this.doctor);
        //changeHandler.onChange();
    }
    private void delete() {
        doctorRepository.delete(doctor);
        changeHandler.onChange();
    }

    public void editDoctor(Doctor doc) {
        if (doc == null) {

            verticalLayout.setVisible(false);
            return;
        }

        if (doc.getName() != null) {
            this.doctor = doctorRepository.findById(doc.getId()).orElse(doc);
        } else {
            this.doctor = doc;
        }

        binder.setBean(this.doctor);


        verticalLayout.setVisible(true);

        surname.focus();

    }


}
