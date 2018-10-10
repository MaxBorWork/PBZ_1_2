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

public class InspectorView {
    private Table mainTable;
    private MainWindow mainWindow;
    private InspectorController inspectorController = new InspectorController();

    InspectorView(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    private void initInspectorTable(Shell shell) {
        mainTable = new Table(shell, SWT.MULTI | SWT.BORDER |
                SWT.FULL_SELECTION | SWT.V_SCROLL | SWT.H_SCROLL);
        mainTable.setHeaderVisible(true);
        mainTable.setLinesVisible(true);

        TableColumn fullNameColumn = new TableColumn(mainTable, SWT.NONE);
        fullNameColumn.setText("ФИО");
        fullNameColumn.setWidth(200);

        TableColumn rankColumn = new TableColumn(mainTable, SWT.NONE);
        rankColumn.setText("Звание");
        rankColumn.setWidth(100);

        TableColumn stateNumsColumn = new TableColumn(mainTable, SWT.NONE);
        stateNumsColumn.setText("Госномера");
        stateNumsColumn.setWidth(100);

        mainTable.setLayoutData(new GridData(400, 300));
    }

    void addInspectionForm() {
        final Shell addInspectorDialog = new Shell(mainWindow.getDisplay(), SWT.DIALOG_TRIM);
        addInspectorDialog.setText("Добавление инспектора");
        mainWindow.initLayout(addInspectorDialog, 2);

        Composite inspectorName = new Composite(addInspectorDialog, SWT.NONE);
        Label inspectorNameLabel = new Label(inspectorName, SWT.NONE);
        final Text inspectorNameText = mainWindow.createTextField(inspectorName, inspectorNameLabel,
                "имя", addInspectorDialog, addInspectorDialog.getLayout());

        Composite inspectorSurname = new Composite(addInspectorDialog, SWT.NONE);
        Label inspectorSurnameLabel = new Label(inspectorSurname, SWT.NONE);
        final Text inspectorSurnameText = mainWindow.createTextField(inspectorSurname, inspectorSurnameLabel,
                "фамилию", addInspectorDialog, addInspectorDialog.getLayout());

        Composite inspectorSecondName = new Composite(addInspectorDialog, SWT.NONE);
        Label inspectorSecondNameLabel = new Label(inspectorSecondName, SWT.NONE);
        final Text inspectorSecondNameText = mainWindow.createTextField(inspectorSecondName, inspectorSecondNameLabel,
                "отчество", addInspectorDialog, addInspectorDialog.getLayout());

        Composite inspectorJob = new Composite(addInspectorDialog, SWT.NONE);
        Label inspectorJobLabel = new Label(inspectorJob, SWT.NONE);
        final Text inspectorJobText = mainWindow.createTextField(inspectorJob, inspectorJobLabel,
                "должность", addInspectorDialog, addInspectorDialog.getLayout());

        Composite inspectorRank = new Composite(addInspectorDialog, SWT.NONE);
        Label inspectorRankLabel = new Label(inspectorRank, SWT.NONE);
        final Text inspectorRankText = mainWindow.createTextField(inspectorRank, inspectorRankLabel,
                "звание", addInspectorDialog, addInspectorDialog.getLayout());

        Button addInspectorButton = new Button(addInspectorDialog, SWT.PUSH);
        addInspectorButton.setText("Добавить");
        addInspectorButton.setSize(100, 150);

        addInspectorButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                Inspector inspector = new Inspector(inspectorNameText.getText(), inspectorSurnameText.getText(),
                        inspectorSecondNameText.getText(), inspectorJobText.getText(), inspectorRankText.getText());
                if (inspector.getRank() != null) {
                    inspectorController.addInspector(inspector);
                }
                addInspectorDialog.close();
            }
        });
        addInspectorDialog.setSize(600 , 500);
        addInspectorDialog.open();
    }

    void showInspectionsForTheCar() {
        final Shell showInspectorDialog = new Shell(mainWindow.getDisplay(), SWT.DIALOG_TRIM);
        showInspectorDialog.setText("Поиск инспекторов на дату");
        mainWindow.initLayout(showInspectorDialog, 1);
        initInspectorTable(showInspectorDialog);

        Composite date = new Composite(showInspectorDialog, SWT.NONE);
        date.setLayout(new GridLayout(2, false));
        Label dateLabel = new Label(date, SWT.NONE);
        final Text dateText = mainWindow.createTextField(date, dateLabel,
                "дату", showInspectorDialog, date.getLayout());

        Button showInspectorButton = new Button(showInspectorDialog, SWT.PUSH);
        showInspectorButton.setText("Найти");
        showInspectorButton.setSize(100, 150);

        showInspectorButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                List<Inspector> inspectorList = inspectorController.getInspectorsListForTheDate(dateText.getText());

                for (Inspector inspector : inspectorList) {
                    StringBuilder motorNumberList = new StringBuilder();
                    TableItem item = new TableItem(mainTable, SWT.NONE);
                    item.setText(0, inspector.getSurname() + " " + inspector.getName() + " " + inspector.getSecondName());
                    item.setText(1, inspector.getRank());
                    for (CarInspection inspection : inspector.getCarInspectionsList()) {
                        motorNumberList.append(inspection.getCar().getMotorNumber()).append(", ");
                    }
                    item.setText(2, String.valueOf(motorNumberList.toString()));
                }
            }
        });

        showInspectorDialog.setSize(420 , 500);
        showInspectorDialog.open();
    }

    void updateInspector() {
        final Shell updateInspectorDialog = new Shell(mainWindow.getDisplay(), SWT.DIALOG_TRIM);
        updateInspectorDialog.setText("Изменение инспектора");
        mainWindow.initLayout(updateInspectorDialog, 2);

        Composite inspectorSurname = new Composite(updateInspectorDialog, SWT.NONE);
        Label inspectorSurnameLabel = new Label(inspectorSurname, SWT.NONE);
        final Text inspectorSurnameText = mainWindow.createTextField(inspectorSurname, inspectorSurnameLabel,
                "фамилию", updateInspectorDialog, updateInspectorDialog.getLayout());

        Button updateInspectorButton = new Button(updateInspectorDialog, SWT.PUSH);
        updateInspectorButton.setText("Обновить");
        updateInspectorButton.setSize(100, 150);

        updateInspectorButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                final Inspector foundInspector = inspectorController.getInspectorBySurname(
                                                                                        inspectorSurnameText.getText());

                Composite inspectorName = new Composite(updateInspectorDialog, SWT.NONE);
                inspectorName.setLayout(new GridLayout(2, false));
                Label inspectorNameLabel = new Label(inspectorName, SWT.NONE);
                final Text inspectorNameText = mainWindow.createTextField(inspectorName, inspectorNameLabel,
                        "имя", updateInspectorDialog, inspectorName.getLayout());
                inspectorNameText.setText(foundInspector.getName());

                Composite inspectorSurname = new Composite(updateInspectorDialog, SWT.NONE);
                inspectorSurname.setLayout(new GridLayout(2, false));
                Label inspectorSurnameLabel = new Label(inspectorSurname, SWT.NONE);
                final Text inspectorSurnameText = mainWindow.createTextField(inspectorSurname, inspectorSurnameLabel,
                        "фамилию", updateInspectorDialog, inspectorSurname.getLayout());
                inspectorSurnameText.setText(foundInspector.getSurname());

                Composite inspectorSecondName = new Composite(updateInspectorDialog, SWT.NONE);
                inspectorSecondName.setLayout(new GridLayout(2, false));
                Label inspectorSecondNameLabel = new Label(inspectorSecondName, SWT.NONE);
                final Text inspectorSecondNameText = mainWindow.createTextField(inspectorSecondName, inspectorSecondNameLabel,
                        "отчество", updateInspectorDialog, inspectorSecondName.getLayout());
                inspectorSecondNameText.setText(foundInspector.getSecondName());

                Composite inspectorJob = new Composite(updateInspectorDialog, SWT.NONE);
                inspectorJob.setLayout(new GridLayout(2, false));
                Label inspectorJobLabel = new Label(inspectorJob, SWT.NONE);
                final Text inspectorJobText = mainWindow.createTextField(inspectorJob, inspectorJobLabel,
                        "должность", updateInspectorDialog, inspectorJob.getLayout());
                inspectorJobText.setText(foundInspector.getJob());

                Composite inspectorRank = new Composite(updateInspectorDialog, SWT.NONE);
                inspectorRank.setLayout(new GridLayout(2, false));
                Label inspectorRankLabel = new Label(inspectorRank, SWT.NONE);
                final Text inspectorRankText = mainWindow.createTextField(inspectorRank, inspectorRankLabel,
                        "звание", updateInspectorDialog, inspectorRank.getLayout());
                inspectorRankText.setText(foundInspector.getRank());

                Button addInspectorButton = new Button(updateInspectorDialog, SWT.PUSH);
                addInspectorButton.setText("Добавить");
                addInspectorButton.setSize(100, 150);

                addInspectorButton.addSelectionListener(new SelectionAdapter() {
                    @Override
                    public void widgetSelected(SelectionEvent e) {
                        Inspector inspector = new Inspector(inspectorNameText.getText(), inspectorSurnameText.getText(),
                                inspectorSecondNameText.getText(), inspectorJobText.getText(), inspectorRankText.getText());
                        inspector.setId(foundInspector.getId());
                        if (inspector.getRank() != null) {
                            inspectorController.addInspector(inspector);
                        }
                    }
                });
                updateInspectorDialog.layout();
            }
        });

        updateInspectorDialog.setSize(500 , 400);
        updateInspectorDialog.open();
    }

    void deleteInspector() {
        final Shell deleteInspectorDialog = new Shell(mainWindow.getDisplay(), SWT.DIALOG_TRIM);
        deleteInspectorDialog.setText("Удаление инспектора");
        mainWindow.initLayout(deleteInspectorDialog, 2);

        Composite inspectorSurname = new Composite(deleteInspectorDialog, SWT.NONE);
        Label inspectorSurnameLabel = new Label(inspectorSurname, SWT.NONE);
        final Text inspectorSurnameText = mainWindow.createTextField(inspectorSurname, inspectorSurnameLabel,
                "фамилию", deleteInspectorDialog, deleteInspectorDialog.getLayout());

        Button deleteInspectorButton = new Button(deleteInspectorDialog, SWT.PUSH);
        deleteInspectorButton.setText("Удалить");
        deleteInspectorButton.setSize(100, 150);

        deleteInspectorButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                inspectorController.deleteInspector(inspectorSurnameText.getText());
            }
        });

        deleteInspectorDialog.setSize(500 , 400);
        deleteInspectorDialog.open();
    }

}
