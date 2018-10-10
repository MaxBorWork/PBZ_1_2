package by.borisevich.gibdd.view;

import by.borisevich.gibdd.controller.CarController;
import by.borisevich.gibdd.model.Car;
import by.borisevich.gibdd.model.CarInspection;
import by.borisevich.gibdd.model.CarOwner;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class CarView {
    private MainWindow mainWindow;
    private Table mainTable;
    private CarController carController = new CarController();

    CarView(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    private void initCarTable(Shell shell) {
        mainTable = new Table(shell, SWT.MULTI | SWT.BORDER |
                SWT.FULL_SELECTION | SWT.V_SCROLL | SWT.H_SCROLL);
        mainTable.setHeaderVisible(true);
        mainTable.setLinesVisible(true);

        TableColumn stateNumColumn = new TableColumn(mainTable, SWT.NONE);
        stateNumColumn.setText("Госномер");
        stateNumColumn.setWidth(50);

        TableColumn engineNumColumn = new TableColumn(mainTable, SWT.NONE);
        engineNumColumn.setText("Номер двигателя");
        engineNumColumn.setWidth(50);

        TableColumn colorColumn = new TableColumn(mainTable, SWT.NONE);
        colorColumn.setText("Цвет");
        colorColumn.setWidth(50);

        TableColumn modelColumn = new TableColumn(mainTable, SWT.NONE);
        modelColumn.setText("Марка");
        modelColumn.setWidth(100);

        TableColumn techPassNumColumn = new TableColumn(mainTable, SWT.NONE);
        techPassNumColumn.setText("Номер тех паспорта");
        techPassNumColumn.setWidth(50);

        TableColumn driverCertNumColumn = new TableColumn(mainTable, SWT.NONE);
        driverCertNumColumn.setText("Номер водительского удостоверения");
        driverCertNumColumn.setWidth(100);

        TableColumn driverFullNameColumn = new TableColumn(mainTable, SWT.NONE);
        driverFullNameColumn.setText("ФИО владельца");
        driverFullNameColumn.setWidth(150);

        TableColumn driverAddressColumn = new TableColumn(mainTable, SWT.NONE);
        driverAddressColumn.setText("Адрес владельца");
        driverAddressColumn.setWidth(100);

        TableColumn driverDateOfBirthColumn = new TableColumn(mainTable, SWT.NONE);
        driverDateOfBirthColumn.setText("Дата рожения владельца");
        driverDateOfBirthColumn.setWidth(100);

        TableColumn driverSexColumn = new TableColumn(mainTable, SWT.NONE);
        driverSexColumn.setText("Пол");
        driverSexColumn.setWidth(50);

        mainTable.setLayoutData(new GridData(800, 300));
    }

    void addCarForm() {
        final Shell addCarDialog = new Shell(mainWindow.getDisplay(), SWT.DIALOG_TRIM);
        addCarDialog.setText("Добавление машины");
        mainWindow.initLayout(addCarDialog, 2);

        Composite driverSurname = new Composite(addCarDialog, SWT.NONE);
        driverSurname.setLayout(new GridLayout(2, false));
        Label driverSurnameLabel = new Label(driverSurname, SWT.NONE);
        final Text driverSurnameText = mainWindow.createTextField(driverSurname, driverSurnameLabel,
                "фамилию владельца", addCarDialog, driverSurname.getLayout());

        Button hasCarOwnerButton = new Button(addCarDialog, SWT.PUSH);
        hasCarOwnerButton.setText("Проверить");
        hasCarOwnerButton.setSize(100, 150);

        hasCarOwnerButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                final CarOwner foundOwner = carController.getCarOwnerBySurname(driverSurnameText.getText());
                if (foundOwner == null) {
                    Composite stateNum = new Composite(addCarDialog, SWT.NONE);
                    stateNum.setLayout(new GridLayout(2, false));
                    Label stateNumLabel = new Label(stateNum, SWT.NONE);
                    final Text stateNumText = mainWindow.createTextField(stateNum, stateNumLabel,
                            "госномер", addCarDialog, stateNum.getLayout());

                    Composite motorNum = new Composite(addCarDialog, SWT.NONE);
                    motorNum.setLayout(new GridLayout(2, false));
                    Label motorNumLabel = new Label(motorNum, SWT.NONE);
                    final Text motorNumText = mainWindow.createTextField(motorNum, motorNumLabel,
                            "двигатель", addCarDialog, motorNum.getLayout());

                    Composite color = new Composite(addCarDialog, SWT.NONE);
                    color.setLayout(new GridLayout(2, false));
                    Label colorLabel = new Label(color, SWT.NONE);
                    final Text colorText = mainWindow.createTextField(color, colorLabel,
                            "цвет", addCarDialog, color.getLayout());

                    Composite model = new Composite(addCarDialog, SWT.NONE);
                    model.setLayout(new GridLayout(2, false));
                    Label modelLabel = new Label(model, SWT.NONE);
                    final Text modelText = mainWindow.createTextField(model, modelLabel,
                            "марку", addCarDialog, model.getLayout());

                    Composite techPassNum = new Composite(addCarDialog, SWT.NONE);
                    techPassNum.setLayout(new GridLayout(2, false));
                    Label techPassNumLabel = new Label(techPassNum, SWT.NONE);
                    final Text techPassNumText = mainWindow.createTextField(techPassNum, techPassNumLabel,
                            "техпаспорт", addCarDialog, techPassNum.getLayout());

                    Composite driverCertNum = new Composite(addCarDialog, SWT.NONE);
                    driverCertNum.setLayout(new GridLayout(2, false));
                    Label driverCertNumLabel = new Label(driverCertNum, SWT.NONE);
                    final Text driverCertNumText = mainWindow.createTextField(driverCertNum, driverCertNumLabel,
                            "удостоверение", addCarDialog, driverCertNum.getLayout());

                    Composite driverName = new Composite(addCarDialog, SWT.NONE);
                    driverName.setLayout(new GridLayout(2, false));
                    Label driverNameLabel = new Label(driverName, SWT.NONE);
                    final Text driverNameText = mainWindow.createTextField(driverName, driverNameLabel,
                            "имя",addCarDialog, driverName.getLayout());

                    Composite driverSurname = new Composite(addCarDialog, SWT.NONE);
                    driverSurname.setLayout(new GridLayout(2, false));
                    Label driverSurnameLabel = new Label(driverSurname, SWT.NONE);
                    final Text driverSurnameText = mainWindow.createTextField(driverSurname, driverSurnameLabel,
                            "фамилию", addCarDialog, driverSurname.getLayout());

                    Composite driverSecondName = new Composite(addCarDialog, SWT.NONE);
                    driverSecondName.setLayout(new GridLayout(2, false));
                    Label driverSecondNameLabel = new Label(driverSecondName, SWT.NONE);
                    final Text driverSecondNameText = mainWindow.createTextField(driverSecondName, driverSecondNameLabel,
                            "отчество", addCarDialog, driverSecondName.getLayout());

                    Composite driverAddress = new Composite(addCarDialog, SWT.NONE);
                    driverAddress.setLayout(new GridLayout(2, false));
                    Label driverAddressLabel = new Label(driverAddress, SWT.NONE);
                    final Text driverAddressText = mainWindow.createTextField(driverAddress, driverAddressLabel,
                            "адрес", addCarDialog, driverAddress.getLayout());

                    Composite driverDateOfBirth = new Composite(addCarDialog, SWT.NONE);
                    driverDateOfBirth.setLayout(new GridLayout(2, false));
                    Label driverDateOfBirthLabel = new Label(driverDateOfBirth, SWT.NONE);
                    final Text driverDateOfBirthText = mainWindow.createTextField(driverDateOfBirth, driverDateOfBirthLabel,
                            "дату рождения", addCarDialog, driverDateOfBirth.getLayout());

                    Composite driverSex = new Composite(addCarDialog, SWT.NONE);
                    driverSex.setLayout(new GridLayout(2, false));
                    Label driverSexLabel = new Label(driverSex, SWT.NONE);
                    final Text driverSexText = mainWindow.createTextField(driverSex, driverSexLabel,
                            "пол", addCarDialog, driverSex.getLayout());

                    Button okButton = new Button(addCarDialog, SWT.PUSH);
                    okButton.setText("Добавить");
                    okButton.setSize(100, 150);

                    addCarDialog.layout();

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

                } else {
                    Composite stateNum = new Composite(addCarDialog, SWT.NONE);
                    stateNum.setLayout(new GridLayout(2, false));
                    Label stateNumLabel = new Label(stateNum, SWT.NONE);
                    final Text stateNumText = mainWindow.createTextField(stateNum, stateNumLabel,
                            "госномер", addCarDialog, stateNum.getLayout());

                    Composite motorNum = new Composite(addCarDialog, SWT.NONE);
                    motorNum.setLayout(new GridLayout(2, false));
                    Label motorNumLabel = new Label(motorNum, SWT.NONE);
                    final Text motorNumText = mainWindow.createTextField(motorNum, motorNumLabel,
                            "двигатель", addCarDialog, motorNum.getLayout());

                    Composite color = new Composite(addCarDialog, SWT.NONE);
                    color.setLayout(new GridLayout(2, false));
                    Label colorLabel = new Label(color, SWT.NONE);
                    final Text colorText = mainWindow.createTextField(color, colorLabel,
                            "цвет", addCarDialog, color.getLayout());

                    Composite model = new Composite(addCarDialog, SWT.NONE);
                    model.setLayout(new GridLayout(2, false));
                    Label modelLabel = new Label(model, SWT.NONE);
                    final Text modelText = mainWindow.createTextField(model, modelLabel,
                            "марку", addCarDialog, model.getLayout());

                    Composite techPassNum = new Composite(addCarDialog, SWT.NONE);
                    techPassNum.setLayout(new GridLayout(2, false));
                    Label techPassNumLabel = new Label(techPassNum, SWT.NONE);
                    final Text techPassNumText = mainWindow.createTextField(techPassNum, techPassNumLabel,
                            "техпаспорт", addCarDialog, techPassNum.getLayout());

                    Button okButton = new Button(addCarDialog, SWT.PUSH);
                    okButton.setText("Добавить");
                    okButton.setSize(100, 150);

                    addCarDialog.layout();

                    okButton.addSelectionListener(new SelectionAdapter() {
                        @Override
                        public void widgetSelected(SelectionEvent e) {
                            Car car = new Car(stateNumText.getText(), motorNumText.getText(), colorText.getText(),
                                    modelText.getText(), techPassNumText.getText());
                            car.setCarOwner(foundOwner);
                            if (car.getCarOwner().getSex() != null) {
                                carController.addCar(car);
                            }
                            addCarDialog.close();
                        }
                    });
                }
            }
        });

        addCarDialog.setSize(800 , 900);
        addCarDialog.open();
    }

    void showCars() {
        final Shell showCarsDialog = new Shell(mainWindow.getDisplay(), SWT.DIALOG_TRIM);
        showCarsDialog.setText("Поиск машин за промежуток");
        mainWindow.initLayout(showCarsDialog, 1);
        initCarTable(showCarsDialog);

        final Composite infoComposite = new Composite(showCarsDialog, SWT.NONE);
        infoComposite.setLayout(new GridLayout(2, true));

        final Composite inputDate = new Composite(infoComposite, SWT.NONE);
        inputDate.setLayout(new GridLayout(2, false));
        inputDate.setData(new GridData(400, 300));
        Label startDateLabel = new Label(inputDate, SWT.NONE);
        final Text startDateText = mainWindow.createTextField(inputDate, startDateLabel,
                "начальную дату", showCarsDialog, inputDate.getLayout());

        Label endDateLabel = new Label(inputDate, SWT.NONE);
        final Text endDateText = mainWindow.createTextField(inputDate, endDateLabel,
                "конечную дату", showCarsDialog, inputDate.getLayout());

        final Button showCarsButton = new Button(showCarsDialog, SWT.PUSH);
        showCarsButton.setText("Найти");
        showCarsButton.setSize(100, 150);

        final Composite colOfCarsComposite = new Composite(infoComposite, SWT.NONE);
        colOfCarsComposite.setLayout(new GridLayout(1, false));
        colOfCarsComposite.setData(new GridData(400, 300));


        showCarsButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                String startDateString = startDateText.getText();
                String endDateString = endDateText.getText();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date startDate = new Date();
                Date endDate = new Date();
                try {
                    startDate = format.parse(startDateString);
                    endDate = format.parse(endDateString);
                } catch (ParseException e1) {
                    System.out.println("Parse String to Date exception:" + e1);
                }
                Map<String, Integer> dateAndColMap = new HashMap<String, Integer>();
                List<Car> carList = carController.getCarsListOfPeriod(startDateString, endDateString);
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

                    for (CarInspection inspection : car.getCarInspections()) {
                        Date inspectionDate = new Date();
                        try {
                            inspectionDate = format.parse(inspection.getDateOfInspection());
                        } catch (ParseException e1) {
                            System.out.println("Parse String to Date exception:" + e1);
                        }
                        Calendar inspectionCal = Calendar.getInstance();
                        inspectionCal.setTime(inspectionDate);
                        Calendar calStart = Calendar.getInstance();
                        Calendar calEnd = Calendar.getInstance();
                        calStart.setTime(startDate);
                        calStart.add(Calendar.DAY_OF_MONTH, -1);
                        calEnd.setTime(endDate);
                        calEnd.add(Calendar.DAY_OF_MONTH, +1);
                        if (calStart.before(inspectionCal) && calEnd.after(inspectionCal)) {
                            if (dateAndColMap.get(inspection.getDateOfInspection()) != null) {
                                dateAndColMap.put(inspection.getDateOfInspection(),
                                        dateAndColMap.get(inspection.getDateOfInspection()) + 1);
                            } else {
                                dateAndColMap.put(inspection.getDateOfInspection(), 1);
                            }
                        }
                    }
                }

                for (Map.Entry<String, Integer> entry : dateAndColMap.entrySet()) {
                    final Label numForDateLabel = new Label(colOfCarsComposite, SWT.NONE);
                    numForDateLabel.setText("Осмотров на " + entry.getKey() + " : " + entry.getValue() + "\n");
                }
                showCarsDialog.layout();
            }
        });

        showCarsDialog.setSize(820 , 650);
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

                Composite stateNum = new Composite(updateCarDialog, SWT.NONE);
                stateNum.setLayout(new GridLayout(2, false));
                Label stateNumLabel = new Label(stateNum, SWT.NONE);
                final Text stateNumText = mainWindow.createTextField(stateNum, stateNumLabel,
                        "госномер", updateCarDialog, stateNum.getLayout());
                stateNumText.setText(foundCar.getStateNumber());

                Composite color = new Composite(updateCarDialog, SWT.NONE);
                color.setLayout(new GridLayout(2, false));
                Label colorLabel = new Label(color, SWT.NONE);
                final Text colorText = mainWindow.createTextField(color, colorLabel,
                        "цвет", updateCarDialog, color.getLayout());
                colorText.setText(foundCar.getColor());

                Composite model = new Composite(updateCarDialog, SWT.NONE);
                model.setLayout(new GridLayout(2, false));
                Label modelLabel = new Label(model, SWT.NONE);
                final Text modelText = mainWindow.createTextField(model, modelLabel,
                        "марку", updateCarDialog, model.getLayout());
                modelText.setText(foundCar.getModel());

                Composite techPassNum = new Composite(updateCarDialog, SWT.NONE);
                techPassNum.setLayout(new GridLayout(2, false));
                Label techPassNumLabel = new Label(techPassNum, SWT.NONE);
                final Text techPassNumText = mainWindow.createTextField(techPassNum, techPassNumLabel,
                        "техпаспорт", updateCarDialog, techPassNum.getLayout());
                techPassNumText.setText(foundCar.getTechPassportNumber());

                Composite driverCertNum = new Composite(updateCarDialog, SWT.NONE);
                driverCertNum.setLayout(new GridLayout(2, false));
                Label driverCertNumLabel = new Label(driverCertNum, SWT.NONE);
                final Text driverCertNumText = mainWindow.createTextField(driverCertNum, driverCertNumLabel,
                        "удостоверение", updateCarDialog, driverCertNum.getLayout());
                driverCertNumText.setText(foundCar.getCarOwner().getDriverCertificateNumber());

                Composite driverName = new Composite(updateCarDialog, SWT.NONE);
                driverName.setLayout(new GridLayout(2, false));
                Label driverNameLabel = new Label(driverName, SWT.NONE);
                final Text driverNameText = mainWindow.createTextField(driverName, driverNameLabel,
                        "имя",updateCarDialog, driverName.getLayout());
                driverNameText.setText(foundCar.getCarOwner().getName());

                Composite driverSurname = new Composite(updateCarDialog, SWT.NONE);
                driverSurname.setLayout(new GridLayout(2, false));
                Label driverSurnameLabel = new Label(driverSurname, SWT.NONE);
                final Text driverSurnameText = mainWindow.createTextField(driverSurname, driverSurnameLabel,
                        "фамилию", updateCarDialog, driverSurname.getLayout());
                driverSurnameText.setText(foundCar.getCarOwner().getSurname());

                Composite driverSecondName = new Composite(updateCarDialog, SWT.NONE);
                driverSecondName.setLayout(new GridLayout(2, false));
                Label driverSecondNameLabel = new Label(driverSecondName, SWT.NONE);
                final Text driverSecondNameText = mainWindow.createTextField(driverSecondName, driverSecondNameLabel,
                        "отчество", updateCarDialog, driverSecondName.getLayout());
                driverSecondNameText.setText(foundCar.getCarOwner().getSecondName());

                Composite driverAddress = new Composite(updateCarDialog, SWT.NONE);
                driverAddress.setLayout(new GridLayout(2, false));
                Label driverAddressLabel = new Label(driverAddress, SWT.NONE);
                final Text driverAddressText = mainWindow.createTextField(driverAddress, driverAddressLabel,
                        "адрес", updateCarDialog, driverAddress.getLayout());
                driverAddressText.setText(foundCar.getCarOwner().getAddress());

                Composite driverDateOfBirth = new Composite(updateCarDialog, SWT.NONE);
                driverDateOfBirth.setLayout(new GridLayout(2, false));
                Label driverDateOfBirthLabel = new Label(driverDateOfBirth, SWT.NONE);
                final Text driverDateOfBirthText = mainWindow.createTextField(driverDateOfBirth, driverDateOfBirthLabel,
                        "дату рождения", updateCarDialog, driverDateOfBirth.getLayout());
                driverDateOfBirthText.setText(foundCar.getCarOwner().getDateOfBirth());

                Composite driverSex = new Composite(updateCarDialog, SWT.NONE);
                driverSex.setLayout(new GridLayout(2, false));
                Label driverSexLabel = new Label(driverSex, SWT.NONE);
                final Text driverSexText = mainWindow.createTextField(driverSex, driverSexLabel,
                        "пол", updateCarDialog, driverSex.getLayout());
                driverSexText.setText(foundCar.getCarOwner().getSex());

                Button okButton = new Button(updateCarDialog, SWT.PUSH);
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
                        }
                    }
                });
                updateCarDialog.layout();
            }
        });

        updateCarDialog.setSize(600 , 900);
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
