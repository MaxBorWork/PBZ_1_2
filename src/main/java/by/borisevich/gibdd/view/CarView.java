package by.borisevich.gibdd.view;

import by.borisevich.gibdd.controller.CarController;
import by.borisevich.gibdd.model.Car;
import by.borisevich.gibdd.model.CarInspection;
import by.borisevich.gibdd.model.CarOwner;
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
        final Shell addCarDialog = new Shell(mainWindow.getDisplay(), SWT.DIALOG_TRIM);
        addCarDialog.setText("Добавление машины");
        mainWindow.initLayout(addCarDialog, 2);

        Composite stateNum = new Composite(addCarDialog, SWT.NONE);
        Label stateNumLabel = new Label(stateNum, SWT.NONE);
        final Text stateNumText = mainWindow.createTextField(stateNum, stateNumLabel,
                "госномер", addCarDialog, addCarDialog.getLayout());

        Composite motorNum = new Composite(addCarDialog, SWT.NONE);
        Label motorNumLabel = new Label(motorNum, SWT.NONE);
        final Text motorNumText = mainWindow.createTextField(motorNum, motorNumLabel,
                "двигатель", addCarDialog, addCarDialog.getLayout());

        Composite color = new Composite(addCarDialog, SWT.NONE);
        Label colorLabel = new Label(color, SWT.NONE);
        final Text colorText = mainWindow.createTextField(color, colorLabel,
                "цвет", addCarDialog, addCarDialog.getLayout());

        Composite model = new Composite(addCarDialog, SWT.NONE);
        Label modelLabel = new Label(model, SWT.NONE);
        final Text modelText = mainWindow.createTextField(model, modelLabel,
                "марку", addCarDialog, addCarDialog.getLayout());

        Composite techPassNum = new Composite(addCarDialog, SWT.NONE);
        Label techPassNumLabel = new Label(techPassNum, SWT.NONE);
        final Text techPassNumText = mainWindow.createTextField(techPassNum, techPassNumLabel,
                "техпаспорт", addCarDialog, addCarDialog.getLayout());

        Composite driverCertNum = new Composite(addCarDialog, SWT.NONE);
        Label driverCertNumLabel = new Label(driverCertNum, SWT.NONE);
        final Text driverCertNumText = mainWindow.createTextField(driverCertNum, driverCertNumLabel,
                "удостоверение", addCarDialog, addCarDialog.getLayout());

        Composite driverName = new Composite(addCarDialog, SWT.NONE);
        Label driverNameLabel = new Label(driverName, SWT.NONE);
        final Text driverNameText = mainWindow.createTextField(driverName, driverNameLabel,
                "имя",addCarDialog, addCarDialog.getLayout());

        Composite driverSurname = new Composite(addCarDialog, SWT.NONE);
        Label driverSurnameLabel = new Label(driverSurname, SWT.NONE);
        final Text driverSurnameText = mainWindow.createTextField(driverSurname, driverSurnameLabel,
                "фамилию", addCarDialog, addCarDialog.getLayout());

        Composite driverSecondName = new Composite(addCarDialog, SWT.NONE);
        Label driverSecondNameLabel = new Label(driverSecondName, SWT.NONE);
        final Text driverSecondNameText = mainWindow.createTextField(driverSecondName, driverSecondNameLabel,
                "отчество", addCarDialog, addCarDialog.getLayout());

        Composite driverAddress = new Composite(addCarDialog, SWT.NONE);
        Label driverAddressLabel = new Label(driverAddress, SWT.NONE);
        final Text driverAddressText = mainWindow.createTextField(driverAddress, driverAddressLabel,
                "адрес", addCarDialog, addCarDialog.getLayout());

        Composite driverDateOfBirth = new Composite(addCarDialog, SWT.NONE);
        Label driverDateOfBirthLabel = new Label(driverDateOfBirth, SWT.NONE);
        final Text driverDateOfBirthText = mainWindow.createTextField(driverDateOfBirth, driverDateOfBirthLabel,
                "дату рождения", addCarDialog, addCarDialog.getLayout());

        Composite driverSex = new Composite(addCarDialog, SWT.NONE);
        Label driverSexLabel = new Label(driverSex, SWT.NONE);
        final Text driverSexText = mainWindow.createTextField(driverSex, driverSexLabel,
                "пол", addCarDialog, addCarDialog.getLayout());

        Button okButton = new Button(addCarDialog, SWT.PUSH);
        okButton.setText("Добавить");
        okButton.setSize(100, 150);

        okButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                Car car = new Car(stateNumText.getText(), motorNumText.getText(), colorText.getText(),
                        modelText.getText(), techPassNumText.getText());
                CarOwner carOwner = new CarOwner(driverNameText.getText(), driverSurnameText.getText(),
                        driverSecondNameText.getText(), driverAddressText.getText(), driverDateOfBirthText.getText(),
                        driverSexText.getText(), driverCertNumText.getText());
                car.setCarOwner(carOwner);
                if (car.getCarOwner().getSex() != null) {
                    carController.addCar(car);
                }
                addCarDialog.close();
            }
        });

        addCarDialog.setSize(800 , 900);
        addCarDialog.open();
    }

    void showCars() {
        final Shell showCarsDialog = new Shell(mainWindow.getDisplay(), SWT.DIALOG_TRIM);
        showCarsDialog.setText("Поиск машин за промежуток");
        mainWindow.initLayout(showCarsDialog, 2);

        final Composite startDate = new Composite(showCarsDialog, SWT.NONE);
        Label startDateLabel = new Label(startDate, SWT.NONE);
        final Text startDateText = mainWindow.createTextField(startDate, startDateLabel,
                "начальную дату", showCarsDialog, showCarsDialog.getLayout());

        final Composite endDate = new Composite(showCarsDialog, SWT.NONE);
        Label endDateLabel = new Label(endDate, SWT.NONE);
        final Text endDateText = mainWindow.createTextField(endDate, endDateLabel,
                "конечную дату", showCarsDialog, showCarsDialog.getLayout());

        final Button showCarsButton = new Button(showCarsDialog, SWT.PUSH);
        showCarsButton.setText("Найти");
        showCarsButton.setSize(100, 150);

        showCarsButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                List<Car> carList = carController.getCarsListOfPeriod(startDateText.getText(), endDateText.getText());
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
        });

        showCarsDialog.setSize(500 , 400);
        showCarsDialog.open();
    }

    void updateCar() {
        final Shell updateCarDialog = new Shell(mainWindow.getDisplay(), SWT.DIALOG_TRIM);
        updateCarDialog.setText("Изменение машины");
        mainWindow.initLayout(updateCarDialog, 2);

        final Composite carMotorNumber = new Composite(updateCarDialog, SWT.NONE);
        Label carMotorNumberLabel = new Label(carMotorNumber, SWT.NONE);
        final Text carMotorNumberText = mainWindow.createTextField(carMotorNumber, carMotorNumberLabel,
                "номер двигателя", updateCarDialog, updateCarDialog.getLayout());

        final Button updateCarButton = new Button(updateCarDialog, SWT.PUSH);
        updateCarButton.setText("Изменить");
        updateCarButton.setSize(100, 150);

        updateCarButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                final Car foundCar = carController.getCarByMotorNumber(carMotorNumberText.getText());
                final Shell inputCarDialog = new Shell(mainWindow.getDisplay(), SWT.DIALOG_TRIM);
                inputCarDialog.setText("Изменение информации машины");
                mainWindow.initLayout(inputCarDialog, 4);

                Composite stateNum = new Composite(inputCarDialog, SWT.NONE);
                Label stateNumLabel = new Label(stateNum, SWT.NONE);
                final Text stateNumText = mainWindow.createTextField(stateNum, stateNumLabel,
                        "госномер", inputCarDialog, inputCarDialog.getLayout());
                stateNumText.setText(foundCar.getStateNumber());

                Composite color = new Composite(inputCarDialog, SWT.NONE);
                Label colorLabel = new Label(color, SWT.NONE);
                final Text colorText = mainWindow.createTextField(color, colorLabel,
                        "цвет", inputCarDialog, inputCarDialog.getLayout());
                colorText.setText(foundCar.getColor());

                Composite model = new Composite(inputCarDialog, SWT.NONE);
                Label modelLabel = new Label(model, SWT.NONE);
                final Text modelText = mainWindow.createTextField(model, modelLabel,
                        "марку", inputCarDialog, inputCarDialog.getLayout());
                modelText.setText(foundCar.getModel());

                Composite techPassNum = new Composite(inputCarDialog, SWT.NONE);
                Label techPassNumLabel = new Label(techPassNum, SWT.NONE);
                final Text techPassNumText = mainWindow.createTextField(techPassNum, techPassNumLabel,
                        "техпаспорт", inputCarDialog, inputCarDialog.getLayout());
                techPassNumText.setText(foundCar.getTechPassportNumber());

                Composite driverCertNum = new Composite(inputCarDialog, SWT.NONE);
                Label driverCertNumLabel = new Label(driverCertNum, SWT.NONE);
                final Text driverCertNumText = mainWindow.createTextField(driverCertNum, driverCertNumLabel,
                        "удостоверение", inputCarDialog, inputCarDialog.getLayout());
                driverCertNumText.setText(foundCar.getCarOwner().getDriverCertificateNumber());

                Composite driverName = new Composite(inputCarDialog, SWT.NONE);
                Label driverNameLabel = new Label(driverName, SWT.NONE);
                final Text driverNameText = mainWindow.createTextField(driverName, driverNameLabel,
                        "имя",inputCarDialog, inputCarDialog.getLayout());
                driverNameText.setText(foundCar.getCarOwner().getName());

                Composite driverSurname = new Composite(inputCarDialog, SWT.NONE);
                Label driverSurnameLabel = new Label(driverSurname, SWT.NONE);
                final Text driverSurnameText = mainWindow.createTextField(driverSurname, driverSurnameLabel,
                        "фамилию", inputCarDialog, inputCarDialog.getLayout());
                driverSurnameText.setText(foundCar.getCarOwner().getSurname());

                Composite driverSecondName = new Composite(inputCarDialog, SWT.NONE);
                Label driverSecondNameLabel = new Label(driverSecondName, SWT.NONE);
                final Text driverSecondNameText = mainWindow.createTextField(driverSecondName, driverSecondNameLabel,
                        "отчество", inputCarDialog, inputCarDialog.getLayout());
                driverSecondNameText.setText(foundCar.getCarOwner().getSecondName());

                Composite driverAddress = new Composite(inputCarDialog, SWT.NONE);
                Label driverAddressLabel = new Label(driverAddress, SWT.NONE);
                final Text driverAddressText = mainWindow.createTextField(driverAddress, driverAddressLabel,
                        "адрес", inputCarDialog, inputCarDialog.getLayout());
                driverAddressText.setText(foundCar.getCarOwner().getAddress());

                Composite driverDateOfBirth = new Composite(inputCarDialog, SWT.NONE);
                Label driverDateOfBirthLabel = new Label(driverDateOfBirth, SWT.NONE);
                final Text driverDateOfBirthText = mainWindow.createTextField(driverDateOfBirth, driverDateOfBirthLabel,
                        "дату рождения", inputCarDialog, inputCarDialog.getLayout());
                driverDateOfBirthText.setText(foundCar.getCarOwner().getDateOfBirth());

                Composite driverSex = new Composite(inputCarDialog, SWT.NONE);
                Label driverSexLabel = new Label(driverSex, SWT.NONE);
                final Text driverSexText = mainWindow.createTextField(driverSex, driverSexLabel,
                        "пол", inputCarDialog, inputCarDialog.getLayout());
                driverSexText.setText(foundCar.getCarOwner().getSex());

                Button okButton = new Button(inputCarDialog, SWT.PUSH);
                okButton.setText("Записать");
                okButton.setSize(100, 150);

                okButton.addSelectionListener(new SelectionAdapter() {
                    @Override
                    public void widgetSelected(SelectionEvent e) {
                        Car car = new Car(stateNumText.getText(), foundCar.getMotorNumber(), colorText.getText(),
                                modelText.getText(), techPassNumText.getText());
                        CarOwner carOwner = new CarOwner(driverNameText.getText(), driverSurnameText.getText(),
                                driverSecondNameText.getText(), driverAddressText.getText(), driverDateOfBirthText.getText(),
                                driverSexText.getText(), driverCertNumText.getText());
                        carOwner.setId(foundCar.getCarOwner().getId());
                        car.setCarOwner(carOwner);
                        car.setId(foundCar.getId());
                        if (car.getCarOwner().getSex() != null) {
                            carController.updateCar(car);
                            inputCarDialog.close();
                        }
                    }
                });
                inputCarDialog.setSize(800 , 600);
                inputCarDialog.open();

            }
        });

        updateCarDialog.setSize(500 , 400);
        updateCarDialog.open();

    }

    void deleteCar() {
        final Shell deleteCarDialog = new Shell(mainWindow.getDisplay(), SWT.DIALOG_TRIM);
        deleteCarDialog.setText("Удаление машины");
        mainWindow.initLayout(deleteCarDialog, 2);

        Composite carMotorNumber = new Composite(deleteCarDialog, SWT.NONE);
        Label carMotorNumberLabel = new Label(carMotorNumber, SWT.NONE);
        final Text carMotorNumberText = mainWindow.createTextField(carMotorNumber, carMotorNumberLabel,
                "номер двигателя", deleteCarDialog, deleteCarDialog.getLayout());

        Button deleteCarButton = new Button(deleteCarDialog, SWT.PUSH);
        deleteCarButton.setText("Удалить");
        deleteCarButton.setSize(100, 150);

        deleteCarButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                carController.deleteCar(carMotorNumberText.getText());
            }
        });

        deleteCarDialog.setSize(500 , 400);
        deleteCarDialog.open();
    }
}
