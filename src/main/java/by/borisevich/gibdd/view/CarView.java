package by.borisevich.gibdd.view;

import by.borisevich.gibdd.controller.CarController;
import by.borisevich.gibdd.model.Car;
import by.borisevich.gibdd.model.CarInspection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.*;

import java.util.List;

public class CarView {
    private MainWindow mainWindow;
    private Table mainTable;
    private CarController carController = new CarController();

    CarView(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    void initCarTable() {
        mainTable = new Table(mainWindow.getShell(), SWT.MULTI | SWT.BORDER |
                SWT.FULL_SELECTION | SWT.V_SCROLL | SWT.H_SCROLL);
        mainTable.setHeaderVisible(true);
        mainTable.setLinesVisible(true);

        TableColumn stateNumColumn = new TableColumn(mainTable, SWT.NONE);
        stateNumColumn.setText("Госномер");
        stateNumColumn.setWidth(150);

        TableColumn engineNumColumn = new TableColumn(mainTable, SWT.NONE);
        engineNumColumn.setText("Номер двигателя");
        engineNumColumn.setWidth(250);

        TableColumn colorColumn = new TableColumn(mainTable, SWT.NONE);
        colorColumn.setText("Цвет");
        colorColumn.setWidth(250);

        TableColumn modelColumn = new TableColumn(mainTable, SWT.NONE);
        modelColumn.setText("Марка");
        modelColumn.setWidth(150);

        TableColumn techPassNumColumn = new TableColumn(mainTable, SWT.NONE);
        techPassNumColumn.setText("Номер тех паспорта");
        techPassNumColumn.setWidth(150);

        TableColumn driverCertNumColumn = new TableColumn(mainTable, SWT.NONE);
        driverCertNumColumn.setText("Номер водительского удостоверения");
        driverCertNumColumn.setWidth(150);

        TableColumn driverFullNameColumn = new TableColumn(mainTable, SWT.NONE);
        driverFullNameColumn.setText("ФИО владельца");
        driverFullNameColumn.setWidth(150);

        TableColumn driverAddressColumn = new TableColumn(mainTable, SWT.NONE);
        driverAddressColumn.setText("Адрес владельца");
        driverAddressColumn.setWidth(150);

        TableColumn driverDateOfBirthColumn = new TableColumn(mainTable, SWT.NONE);
        driverDateOfBirthColumn.setText("Дата рожения владельца");
        driverDateOfBirthColumn.setWidth(150);

        TableColumn driverSexColumn = new TableColumn(mainTable, SWT.NONE);
        driverSexColumn.setText("Пол владельца");
        driverSexColumn.setWidth(150);

        mainTable.setLayoutData(new GridData(1500, 400));
    }

    void addCarForm() {
        Shell addInspectionDialog = new Shell(mainWindow.getDisplay(), SWT.DIALOG_TRIM);
        addInspectionDialog.setText("Добавление машины");
        mainWindow.initLayout(addInspectionDialog);

        Composite enterDate = new Composite(addInspectionDialog, SWT.NONE);
        Label enterDateLabel = new Label(enterDate, SWT.NONE);
        final Text enterDateText = mainWindow.createTextField(enterDate, enterDateLabel, "дата",
                addInspectionDialog, addInspectionDialog.getLayout());

        Composite enterResult = new Composite(addInspectionDialog, SWT.NONE);
        Label enterResultLabel = new Label(enterResult, SWT.NONE);
        final Text enterResultText = mainWindow.createTextField(enterResult, enterResultLabel, "дата",
                addInspectionDialog, addInspectionDialog.getLayout());

        Button okButton = new Button(addInspectionDialog, SWT.PUSH);
        okButton.setText("Добавить");
        okButton.setSize(100, 150);

        okButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                Car car = new Car();
                carController.addCar(car);
            }
        });

        addInspectionDialog.setSize(750 , 500 );
        addInspectionDialog.open();
    }

    void showCars(String startDate, String endDate) {
        List<Car> carList = carController.getCarsListOfPeriod(startDate, endDate);
        for (Car car : carList) {
            TableItem item = new TableItem(mainTable, SWT.NONE);
            item.setText(0, car.getStateNumber());
            item.setText(1, car.getMotorNumber());
            item.setText(2, car.getColor());
            item.setText(3, car.getModel());
            item.setText(4, car.getTechPassportNumber());
            item.setText(5, car.getCarOwner().getDriverCertificateNumber());
            String fullName = car.getCarOwner().getName() + " " + car.getCarOwner().getSurname() +
                    " " + car.getCarOwner().getSecondName();
            item.setText(6, fullName);
            item.setText(7, car.getCarOwner().getAddress());
            item.setText(8, car.getCarOwner().getDateOfBirth());
            item.setText(9, car.getCarOwner().getSex());
        }
    }
}
