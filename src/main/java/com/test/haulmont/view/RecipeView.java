package com.test.haulmont.view;

import com.test.haulmont.controller.RecipeController;
import com.test.haulmont.model.Recipe;
import com.test.haulmont.repository.RecipeRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;

import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import lombok.Setter;


@Route("rec/list")
public class RecipeView extends VerticalLayout{

    RecipeView recipeView;
    RecipeRepository recipeRepository;
    RecipeController recipeController;
    Recipe recipe;

    private final TextField filter = new TextField();
    private Grid<Recipe> recipeGrid = new Grid<>(Recipe.class);

    private TextField description = new TextField("", "Описание");
    private TextField patient = new TextField("", "Пациент");
    private TextField doctor = new TextField("", "Доктор");
    private TextField manufacture = new TextField("", "Дата создания");
    private TextField duration = new TextField("", "Время действия");
    private TextField priority = new TextField("", "Приоритет");

    private Button save = new Button("Сохранить");
    private Button cancel = new Button("Отмена");
    private Button delete = new Button("Удалить");
    private Button edit = new Button("Edit");
    private Dialog buttonsAndFields = new Dialog();
    private Button addNewButton = new Button("Добавить новый рецепт", VaadinIcon.PLUS.create());
    private Binder<Recipe> binder = new Binder<>(Recipe.class);
    private VerticalLayout vr = new VerticalLayout(description,manufacture,duration,priority);
    private VerticalLayout vr2 = new VerticalLayout(description,manufacture,duration,priority);
    private HorizontalLayout hv = new HorizontalLayout(save,cancel);

    @Setter
    public ChangeHandler changeHandler;

    public interface ChangeHandler {
        void onChange();
    }


public RecipeView(RecipeController recipeController, RecipeRepository recipeRepository){
        this.recipeRepository = recipeRepository;
        this.recipeController = recipeController;


    vr.setVisible(false);

    recipeGrid.setItems(recipeRepository.findAll());
    recipeGrid.getColumnByKey("id").setVisible(false);
    recipeGrid.getColumnByKey("description").setHeader("Описание");
    recipeGrid.getColumnByKey("patient").setHeader("Пациент");
    recipeGrid.getColumnByKey("doctor").setHeader("Врач");
    recipeGrid.getColumnByKey("manufacture").setHeader("Дата создания");
    recipeGrid.getColumnByKey("duration").setHeader("Время действия");
    recipeGrid.getColumnByKey("priority").setHeader("Приоритет");


   filter.setPlaceholder("Фильтр");
   filter.setValueChangeMode(ValueChangeMode.EAGER);
   filter.addValueChangeListener(e -> fillList(filter.getValue()));

    add(filter,recipeGrid,addNewButton,delete,edit,vr);

    recipeGrid
            .asSingleSelect()
            .addValueChangeListener(e -> editRecipe(e.getValue()));

    buttonsAndFields.add(vr2,hv);

    cancel.addClickListener(event -> buttonsAndFields.close());
    save.addClickListener(event -> save());
    save.addClickListener(event -> buttonsAndFields.close());
    delete.addClickListener(event -> delete());
    edit.addClickListener(e -> save());
    addNewButton.addClickListener(event -> buttonsAndFields.open());
    addNewButton.addClickListener(e -> editRecipe(new Recipe()));
    buttonsAndFields.setCloseOnEsc(true);
    buttonsAndFields.setCloseOnOutsideClick(true);

}

    private void fillList(String name) {
        if (name.isEmpty()) {
            recipeGrid.setItems(recipeRepository.findAll());
        } else {
            recipeGrid.setItems(recipeRepository.findByName(name));
        }
    }

    private void save() {
        recipeRepository.save(recipe);
        //changeHandler.onChange();
    }

    private void delete() {
        recipeRepository.delete(recipe);
        //changeHandler.onChange();
    }

    public void editRecipe(Recipe rec) {
        if (rec == null) {
            vr.setVisible(false);
            return;
        }

        if (rec.getManufacture() != null){
            this.recipe = recipeRepository.findById(rec.getId()).orElse(rec);
        } else {
            this.recipe = rec;
        }

        binder.setBean(this.recipe);

        vr.setVisible(true);

        duration.focus();

    }


}