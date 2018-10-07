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
import org.eclipse.swt.widgets.*;

import java.util.List;

public class InspectorView {
    private Table mainTable;
    private MainWindow mainWindow;
    private InspectorController inspectorController = new InspectorController();

    InspectorView(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    void initInspectorTable() {
        mainTable = new Table(mainWindow.getShell(), SWT.MULTI | SWT.BORDER |
                SWT.FULL_SELECTION | SWT.V_SCROLL | SWT.H_SCROLL);
        mainTable.setHeaderVisible(true);
        mainTable.setLinesVisible(true);

        TableColumn fullNameColumn = new TableColumn(mainTable, SWT.NONE);
        fullNameColumn.setText("ФИО");
        fullNameColumn.setWidth(250);

        TableColumn jobColumn = new TableColumn(mainTable, SWT.NONE);
        jobColumn.setText("Должность");
        jobColumn.setWidth(200);

        TableColumn rankColumn = new TableColumn(mainTable, SWT.NONE);
        rankColumn.setText("Звание");
        rankColumn.setWidth(200);

        mainTable.setLayoutData(new GridData(1500, 400));
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
        mainWindow.initLayout(showInspectorDialog, 2);

        Composite date = new Composite(showInspectorDialog, SWT.NONE);
        Label dateLabel = new Label(date, SWT.NONE);
        final Text dateText = mainWindow.createTextField(date, dateLabel,
                "дату", showInspectorDialog, showInspectorDialog.getLayout());

        Button showInspectorButton = new Button(showInspectorDialog, SWT.PUSH);
        showInspectorButton.setText("Найти");
        showInspectorButton.setSize(100, 150);

        showInspectorButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                List<Inspector> inspectorList = inspectorController.getInspectionsListForTheDate(dateText.getText());
                for (Inspector inspector : inspectorList) {
                    TableItem item = new TableItem(mainTable, SWT.NONE);
                    item.setText(0, inspector.getSurname() + " " + inspector.getName() + " " + inspector.getSecondName());
                    item.setText(1, inspector.getJob());
                    item.setText(2, inspector.getRank());
                }
            }
        });

        showInspectorDialog.setSize(500 , 400);
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
                final Inspector foundInspector = inspectorController.getInspectorBySurname(inspectorSurnameText.getText());

                final Shell addInspectorDialog = new Shell(mainWindow.getDisplay(), SWT.DIALOG_TRIM);
                addInspectorDialog.setText("Изменение информации инспектора");
                mainWindow.initLayout(addInspectorDialog, 2);

                Composite inspectorName = new Composite(addInspectorDialog, SWT.NONE);
                Label inspectorNameLabel = new Label(inspectorName, SWT.NONE);
                final Text inspectorNameText = mainWindow.createTextField(inspectorName, inspectorNameLabel,
                        "имя", addInspectorDialog, addInspectorDialog.getLayout());
                inspectorNameText.setText(foundInspector.getName());

                Composite inspectorSurname = new Composite(addInspectorDialog, SWT.NONE);
                Label inspectorSurnameLabel = new Label(inspectorSurname, SWT.NONE);
                final Text inspectorSurnameText = mainWindow.createTextField(inspectorSurname, inspectorSurnameLabel,
                        "фамилию", addInspectorDialog, addInspectorDialog.getLayout());
                inspectorSurnameText.setText(foundInspector.getSurname());

                Composite inspectorSecondName = new Composite(addInspectorDialog, SWT.NONE);
                Label inspectorSecondNameLabel = new Label(inspectorSecondName, SWT.NONE);
                final Text inspectorSecondNameText = mainWindow.createTextField(inspectorSecondName, inspectorSecondNameLabel,
                        "отчество", addInspectorDialog, addInspectorDialog.getLayout());
                inspectorSecondNameText.setText(foundInspector.getSecondName());

                Composite inspectorJob = new Composite(addInspectorDialog, SWT.NONE);
                Label inspectorJobLabel = new Label(inspectorJob, SWT.NONE);
                final Text inspectorJobText = mainWindow.createTextField(inspectorJob, inspectorJobLabel,
                        "должность", addInspectorDialog, addInspectorDialog.getLayout());
                inspectorJobText.setText(foundInspector.getJob());

                Composite inspectorRank = new Composite(addInspectorDialog, SWT.NONE);
                Label inspectorRankLabel = new Label(inspectorRank, SWT.NONE);
                final Text inspectorRankText = mainWindow.createTextField(inspectorRank, inspectorRankLabel,
                        "звание", addInspectorDialog, addInspectorDialog.getLayout());
                inspectorRankText.setText(foundInspector.getRank());

                Button addInspectorButton = new Button(addInspectorDialog, SWT.PUSH);
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
                        addInspectorDialog.close();
                    }
                });
                addInspectorDialog.setSize(600 , 500);
                addInspectorDialog.open();
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
