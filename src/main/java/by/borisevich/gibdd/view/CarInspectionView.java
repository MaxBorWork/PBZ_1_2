package by.borisevich.gibdd.view;

import by.borisevich.gibdd.controller.CarController;
import by.borisevich.gibdd.controller.CarInspectionController;
import by.borisevich.gibdd.controller.InspectorController;
import by.borisevich.gibdd.model.Car;
import by.borisevich.gibdd.model.CarInspection;
import by.borisevich.gibdd.model.CarOwner;
import by.borisevich.gibdd.model.Inspector;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

import java.util.List;

public class CarInspectionView {
    private Table mainTable;
    private MainWindow mainWindow;
    private CarController carController = new CarController();
    private CarInspectionController inspectionController = new CarInspectionController();
    private InspectorController inspectorController = new InspectorController();

    CarInspectionView(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    private void initCarInspectionTable(Shell shell) {
        mainTable = new Table(shell, SWT.MULTI | SWT.BORDER |
                SWT.FULL_SELECTION | SWT.V_SCROLL | SWT.H_SCROLL);
        mainTable.setHeaderVisible(true);
        mainTable.setLinesVisible(true);

        TableColumn dateColumn = new TableColumn(mainTable, SWT.NONE);
        dateColumn.setText("Дата");
        dateColumn.setWidth(150);

        TableColumn resultColumn = new TableColumn(mainTable, SWT.NONE);
        resultColumn.setText("Результат");
        resultColumn.setWidth(200);

        mainTable.setLayoutData(new GridData(350, 200));
    }

    void addInspectionForm() {
        final Shell addInspectionDialog = new Shell(mainWindow.getDisplay(), SWT.DIALOG_TRIM);
        addInspectionDialog.setText("Добавление осмотра");
        mainWindow.initLayout(addInspectionDialog, 2);

        Composite carMotorNumber = new Composite(addInspectionDialog, SWT.NONE);
        Label carMotorNumberLabel = new Label(carMotorNumber, SWT.NONE);
        final Text carMotorNumberText = mainWindow.createTextField(carMotorNumber, carMotorNumberLabel,
                "двигатель", addInspectionDialog, addInspectionDialog.getLayout());

        Composite inspectorSurname = new Composite(addInspectionDialog, SWT.NONE);
        Label inspectorSurnameLabel = new Label(inspectorSurname, SWT.NONE);
        final Text inspectorSurnameText = mainWindow.createTextField(inspectorSurname, inspectorSurnameLabel,
                "фамилию инспектора", addInspectionDialog, addInspectionDialog.getLayout());

        Button hasCarButton = new Button(addInspectionDialog, SWT.PUSH);
        hasCarButton.setText("Проверить");
        hasCarButton.setSize(100, 150);

        hasCarButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                final Car foundCar = carController.getCarByMotorNumber(carMotorNumberText.getText());
                if (foundCar == null) {
                    Shell noInspectionCarDialog = new Shell(mainWindow.getDisplay(), SWT.DIALOG_TRIM);
                    noInspectionCarDialog.setText("Добавить и машину");
                    mainWindow.initLayout(noInspectionCarDialog, 6);

                    Composite enterDate = new Composite(noInspectionCarDialog, SWT.NONE);
                    Label enterDateLabel = new Label(enterDate, SWT.NONE);
                    final Text enterDateText = mainWindow.createTextField(enterDate, enterDateLabel,
                            "дату", noInspectionCarDialog, noInspectionCarDialog.getLayout());

                    Composite enterResult = new Composite(noInspectionCarDialog, SWT.NONE);
                    Label enterResultLabel = new Label(enterResult, SWT.NONE);
                    final Text enterResultText = mainWindow.createTextField(enterResult, enterResultLabel,
                            "результат", noInspectionCarDialog, noInspectionCarDialog.getLayout());

                    Composite stateNum = new Composite(noInspectionCarDialog, SWT.NONE);
                    Label stateNumLabel = new Label(stateNum, SWT.NONE);
                    final Text stateNumText = mainWindow.createTextField(stateNum, stateNumLabel,
                            "госномер", noInspectionCarDialog, noInspectionCarDialog.getLayout());

                    Composite motorNum = new Composite(noInspectionCarDialog, SWT.NONE);
                    Label motorNumLabel = new Label(motorNum, SWT.NONE);
                    final Text motorNumText = mainWindow.createTextField(motorNum, motorNumLabel,
                            "двигатель", noInspectionCarDialog, noInspectionCarDialog.getLayout());

                    Composite color = new Composite(noInspectionCarDialog, SWT.NONE);
                    Label colorLabel = new Label(color, SWT.NONE);
                    final Text colorText = mainWindow.createTextField(color, colorLabel,
                            "цвет", noInspectionCarDialog, noInspectionCarDialog.getLayout());

                    Composite model = new Composite(noInspectionCarDialog, SWT.NONE);
                    Label modelLabel = new Label(model, SWT.NONE);
                    final Text modelText = mainWindow.createTextField(model, modelLabel,
                            "марку", noInspectionCarDialog, noInspectionCarDialog.getLayout());

                    Composite techPassNum = new Composite(noInspectionCarDialog, SWT.NONE);
                    Label techPassNumLabel = new Label(techPassNum, SWT.NONE);
                    final Text techPassNumText = mainWindow.createTextField(techPassNum, techPassNumLabel,
                            "техпаспорт", noInspectionCarDialog, noInspectionCarDialog.getLayout());

                    Composite driverCertNum = new Composite(noInspectionCarDialog, SWT.NONE);
                    Label driverCertNumLabel = new Label(driverCertNum, SWT.NONE);
                    final Text driverCertNumText = mainWindow.createTextField(driverCertNum, driverCertNumLabel,
                            "удостоверение", noInspectionCarDialog, noInspectionCarDialog.getLayout());

                    Composite driverName = new Composite(noInspectionCarDialog, SWT.NONE);
                    Label driverNameLabel = new Label(driverName, SWT.NONE);
                    final Text driverNameText = mainWindow.createTextField(driverName, driverNameLabel,
                            "имя",noInspectionCarDialog, noInspectionCarDialog.getLayout());

                    Composite driverSurname = new Composite(noInspectionCarDialog, SWT.NONE);
                    Label driverSurnameLabel = new Label(driverSurname, SWT.NONE);
                    final Text driverSurnameText = mainWindow.createTextField(driverSurname, driverSurnameLabel,
                            "фамилию", noInspectionCarDialog, noInspectionCarDialog.getLayout());

                    Composite driverSecondName = new Composite(noInspectionCarDialog, SWT.NONE);
                    Label driverSecondNameLabel = new Label(driverSecondName, SWT.NONE);
                    final Text driverSecondNameText = mainWindow.createTextField(driverSecondName, driverSecondNameLabel,
                            "отчество", noInspectionCarDialog, noInspectionCarDialog.getLayout());

                    Composite driverAddress = new Composite(noInspectionCarDialog, SWT.NONE);
                    Label driverAddressLabel = new Label(driverAddress, SWT.NONE);
                    final Text driverAddressText = mainWindow.createTextField(driverAddress, driverAddressLabel,
                            "адрес", noInspectionCarDialog, noInspectionCarDialog.getLayout());

                    Composite driverDateOfBirth = new Composite(noInspectionCarDialog, SWT.NONE);
                    Label driverDateOfBirthLabel = new Label(driverDateOfBirth, SWT.NONE);
                    final Text driverDateOfBirthText = mainWindow.createTextField(driverDateOfBirth, driverDateOfBirthLabel,
                            "дату рождения", noInspectionCarDialog, noInspectionCarDialog.getLayout());

                    Composite driverSex = new Composite(noInspectionCarDialog, SWT.NONE);
                    Label driverSexLabel = new Label(driverSex, SWT.NONE);
                    final Text driverSexText = mainWindow.createTextField(driverSex, driverSexLabel,
                            "пол", noInspectionCarDialog, noInspectionCarDialog.getLayout());

                    Button okHasCarButton = new Button(noInspectionCarDialog, SWT.PUSH);
                    okHasCarButton.setText("Добавить");
                    okHasCarButton.setSize(100, 150);

                    okHasCarButton.addSelectionListener(new SelectionAdapter() {
                        @Override
                        public void widgetSelected(SelectionEvent e) {
                            Inspector inspector = inspectorController.getInspectorBySurname(inspectorSurnameText.getText());
                            CarInspection carInspection = new CarInspection(enterDateText.getText(), enterResultText.getText());
                            Car car = new Car(stateNumText.getText(), motorNumText.getText(), colorText.getText(),
                                    modelText.getText(), techPassNumText.getText());
                            CarOwner carOwner = new CarOwner(driverNameText.getText(), driverSurnameText.getText(),
                                    driverSecondNameText.getText(), driverAddressText.getText(), driverDateOfBirthText.getText(),
                                    driverSexText.getText(), driverCertNumText.getText());
                            car.setCarOwner(carOwner);
                            carInspection.setCar(car);
                            List<CarInspection> list = inspector.getCarInspectionsList();
                            list.add(carInspection);
                            carInspection.setInspector(inspector);
                            carInspection.setInspector_id(inspector.getId());
                            if (carInspection.getResultOfInspection() != null) {
                                inspectionController.addInspectionAndCar(carInspection);
                            }
                        }
                    });
                    noInspectionCarDialog.setSize(1200 , 900);
                    noInspectionCarDialog.open();
                } else {
                    Shell hasInspectionCarDialog = new Shell(mainWindow.getDisplay(), SWT.DIALOG_TRIM);
                    hasInspectionCarDialog.setText("Добавить осмотр");
                    mainWindow.initLayout(hasInspectionCarDialog, 4);

                    Composite enterDate = new Composite(hasInspectionCarDialog, SWT.NONE);
                    Label enterDateLabel = new Label(enterDate, SWT.NONE);
                    final Text enterDateText = mainWindow.createTextField(enterDate, enterDateLabel,
                            "дату", hasInspectionCarDialog, hasInspectionCarDialog.getLayout());

                    Composite enterResult = new Composite(hasInspectionCarDialog, SWT.NONE);
                    Label enterResultLabel = new Label(enterResult, SWT.NONE);
                    final Text enterResultText = mainWindow.createTextField(enterResult, enterResultLabel,
                            "результат", hasInspectionCarDialog, hasInspectionCarDialog.getLayout());

                    Button okNewCarButton = new Button(hasInspectionCarDialog, SWT.PUSH);
                    okNewCarButton.setText("Добавить");
                    okNewCarButton.setSize(100, 150);

                    okNewCarButton.addSelectionListener(new SelectionAdapter() {
                        @Override
                        public void widgetSelected(SelectionEvent e) {
                            Inspector inspector = inspectorController.getInspectorBySurname(inspectorSurnameText.getText());
                            CarInspection carInspection = new CarInspection(enterDateText.getText(),
                                    enterResultText.getText());
                            carInspection.setCar(foundCar);
                            carInspection.setCar_id(foundCar.getId());
                            carInspection.setInspector(inspector);
                            carInspection.setInspector_id(inspector.getId());
                            if (carInspection.getResultOfInspection() != null) {
                                inspectionController.addInspection(carInspection);
                            }
                        }
                    });
                    hasInspectionCarDialog.setSize(1200 , 500);
                    hasInspectionCarDialog.open();
                }
            }
        });
        addInspectionDialog.setSize(500 , 400);
        addInspectionDialog.open();
    }

    void showInspectionsForTheCar() {
        final Shell showInspectionDialog = new Shell(mainWindow.getDisplay(), SWT.DIALOG_TRIM);
        showInspectionDialog.setText("Поиск осмотров машины");
        mainWindow.initLayout(showInspectionDialog, 1);
        initCarInspectionTable(showInspectionDialog);

        Composite carMotorNumber = new Composite(showInspectionDialog, SWT.NONE);
        carMotorNumber.setLayout(new GridLayout(2, false));
        Label carMotorNumberLabel = new Label(carMotorNumber, SWT.NONE);
        final Text carMotorNumberText = mainWindow.createTextField(carMotorNumber, carMotorNumberLabel,
                "номер двигателя", showInspectionDialog, carMotorNumber.getLayout());

        Button showInspectionsButton = new Button(showInspectionDialog, SWT.PUSH);
        showInspectionsButton.setText("Найти");
        showInspectionsButton.setSize(100, 150);

        showInspectionsButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                List<CarInspection> carList = inspectionController.getInspectionsListForTheCar(carMotorNumberText.getText());

                for (CarInspection inspection : carList) {
                    TableItem item = new TableItem(mainTable, SWT.NONE);
                    item.setText(0, inspection.getDateOfInspection());
                    item.setText(1, inspection.getResultOfInspection());
                }
            }
        });

        showInspectionDialog.setSize(370 , 400);
        showInspectionDialog.open();
    }

    void updateInspection() {
        final Shell updateInspectionDialog = new Shell(mainWindow.getDisplay(), SWT.DIALOG_TRIM);
        updateInspectionDialog.setText("Изменение осмотра");
        mainWindow.initLayout(updateInspectionDialog, 2);

        Composite carMotorNumber = new Composite(updateInspectionDialog, SWT.NONE);
        Label carMotorNumberLabel = new Label(carMotorNumber, SWT.NONE);
        final Text carMotorNumberText = mainWindow.createTextField(carMotorNumber, carMotorNumberLabel,
                "номер двигателя", updateInspectionDialog, updateInspectionDialog.getLayout());

        Composite inspectionDate = new Composite(updateInspectionDialog, SWT.NONE);
        Label inspectionDateLabel = new Label(inspectionDate, SWT.NONE);
        final Text inspectionDateText = mainWindow.createTextField(inspectionDate, inspectionDateLabel,
                "дату", updateInspectionDialog, updateInspectionDialog.getLayout());

        Button updateInspectionButton = new Button(updateInspectionDialog, SWT.PUSH);
        updateInspectionButton.setText("Изменить");
        updateInspectionButton.setSize(100, 150);

        updateInspectionButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                final CarInspection foundInspection = inspectionController.getInspectionByMotorNumberAndDate(
                                                                        carMotorNumberText.getText(),
                                                                        inspectionDateText.getText());

                Composite enterDate = new Composite(updateInspectionDialog, SWT.NONE);
                enterDate.setLayout(new GridLayout(2, false));
                Label enterDateLabel = new Label(enterDate, SWT.NONE);
                final Text enterDateText = mainWindow.createTextField(enterDate, enterDateLabel,
                        "дату", updateInspectionDialog, enterDate.getLayout());
                enterDateText.setText(foundInspection.getDateOfInspection());

                Composite enterResult = new Composite(updateInspectionDialog, SWT.NONE);
                enterResult.setLayout(new GridLayout(2, false));
                Label enterResultLabel = new Label(enterResult, SWT.NONE);
                final Text enterResultText = mainWindow.createTextField(enterResult, enterResultLabel,
                        "результат", updateInspectionDialog, enterResult.getLayout());
                enterResultText.setText(foundInspection.getResultOfInspection());

                Button okNewCarButton = new Button(updateInspectionDialog, SWT.PUSH);
                okNewCarButton.setText("Добавить");
                okNewCarButton.setSize(100, 150);

                okNewCarButton.addSelectionListener(new SelectionAdapter() {
                    @Override
                    public void widgetSelected(SelectionEvent e) {
                        CarInspection carInspection = new CarInspection(enterDateText.getText(),
                                enterResultText.getText());
                        carInspection.setId(foundInspection.getId());
                        carInspection.setCar(foundInspection.getCar());
                        carInspection.setInspector(foundInspection.getInspector());
                        carInspection.setInspector_id(foundInspection.getInspector_id());
                        if (carInspection.getResultOfInspection() != null) {
                            inspectionController.addInspection(carInspection);
                        }
                    }
                });
                updateInspectionDialog.layout();
            }
        });

        updateInspectionDialog.setSize(500 , 400);
        updateInspectionDialog.open();
    }

    void deleteInspection() {
        final Shell deleteInspectionDialog = new Shell(mainWindow.getDisplay(), SWT.DIALOG_TRIM);
        deleteInspectionDialog.setText("Добавление осмотра");
        mainWindow.initLayout(deleteInspectionDialog, 2);

        Composite carMotorNumber = new Composite(deleteInspectionDialog, SWT.NONE);
        Label carMotorNumberLabel = new Label(carMotorNumber, SWT.NONE);
        final Text carMotorNumberText = mainWindow.createTextField(carMotorNumber, carMotorNumberLabel,
                "номер двигателя", deleteInspectionDialog, deleteInspectionDialog.getLayout());

        Composite inspectionDate = new Composite(deleteInspectionDialog, SWT.NONE);
        Label inspectionDateLabel = new Label(inspectionDate, SWT.NONE);
        final Text inspectionDateText = mainWindow.createTextField(inspectionDate, inspectionDateLabel,
                "дату", deleteInspectionDialog, deleteInspectionDialog.getLayout());

        Button deleteInspectionButton = new Button(deleteInspectionDialog, SWT.PUSH);
        deleteInspectionButton.setText("Удалить");
        deleteInspectionButton.setSize(100, 150);

        deleteInspectionButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                inspectionController.deleteInspectionByMotorNumberAndDate(carMotorNumberText.getText(),
                                                                            inspectionDateText.getText());
            }
        });

        deleteInspectionDialog.setSize(500 , 400);
        deleteInspectionDialog.open();
    }

}
