package pl.edu.utp.wtie.homeworkweek3.gui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.utp.wtie.homeworkweek3.model.Car;
import pl.edu.utp.wtie.homeworkweek3.service.CarService;

import java.util.HashSet;

@Route("remove-car")
public class CarRemoveGui extends VerticalLayout implements quickReturn {

    private CarService carService;

    @Autowired
    public CarRemoveGui(CarService carService) {
        this.carService = carService;

        HashSet<Long> ids = new HashSet<>();
        for (Car car : carService.getCarList()){
            ids.add(car.getId());
        }

        ComboBox<Long> comboBoxId = new ComboBox<>("Id");
        comboBoxId.setItems(ids);

        Dialog dialogCar = new Dialog();
        dialogCar.setWidth("400px");
        dialogCar.setHeight("150px");

        Button buttonRemove = new Button("Remove car");
        buttonRemove.addClickListener(buttonClick -> {
            dialogCar.removeAll();
            if (carService.deleteCar(comboBoxId.getValue())) {
                dialogCar.add(new Label("Car removed"));
            } else {
                dialogCar.add(new Label("No car with this id"));
            }
            dialogCar.open();
        });

        add(comboBoxId, buttonRemove, addButtonBack());
    }
}
