package by.borisevich.gibdd.view;

import by.borisevich.gibdd.controller.CarInspectionController;
import by.borisevich.gibdd.model.CarInspection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.*;

import java.util.List;

public class CarInspectionView {
    private Table mainTable;
    private MainWindow mainWindow;
    private CarInspectionController controller = new CarInspectionController();

    CarInspectionView(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    void initCarInspectionTable() {
        mainTable = new Table(mainWindow.getShell(), SWT.MULTI | SWT.BORDER |
                SWT.FULL_SELECTION | SWT.V_SCROLL | SWT.H_SCROLL);
        mainTable.setHeaderVisible(true);
        mainTable.setLinesVisible(true);

        TableColumn dateColumn = new TableColumn(mainTable, SWT.NONE);
        dateColumn.setText("Дата");
        dateColumn.setWidth(150);

        TableColumn resultColumn = new TableColumn(mainTable, SWT.NONE);
        resultColumn.setText("Результат");
        resultColumn.setWidth(400);

        mainTable.setLayoutData(new GridData(1500, 400));
    }

    void addInspectionForm() {
        Shell addInspectionDialog = new Shell(mainWindow.getDisplay(), SWT.DIALOG_TRIM);
        addInspectionDialog.setText("Добавление осмотра");
        mainWindow.initLayout(addInspectionDialog);

        Composite enterDate = new Composite(addInspectionDialog, SWT.NONE);
        Label enterDateLabel = new Label(enterDate, SWT.NONE);
        final Text enterDateText = mainWindow.createTextField(enterDate, enterDateLabel, "дату",
                addInspectionDialog, addInspectionDialog.getLayout());

        Composite enterResult = new Composite(addInspectionDialog, SWT.NONE);
        Label enterResultLabel = new Label(enterResult, SWT.NONE);
        final Text enterResultText = mainWindow.createTextField(enterResult, enterResultLabel, "результат",
                addInspectionDialog, addInspectionDialog.getLayout());

        Button okButton = new Button(addInspectionDialog, SWT.PUSH);
        okButton.setText("Добавить");
        okButton.setSize(100, 150);

        okButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                CarInspection carInspection = new CarInspection(enterDateText.getText(), enterResultText.getText());
                controller.addInspection(carInspection);
            }
        });

        addInspectionDialog.setSize(750 , 500 );
        addInspectionDialog.open();
    }

    void showInspectionsForTheCar(String motorNumber) {
        List<CarInspection> carList = controller.getInspectionsListForTheCar(motorNumber);
        for (CarInspection inspection : carList) {
            TableItem item = new TableItem(mainTable, SWT.NONE);
            item.setText(0, inspection.getDateOfInspection());
            item.setText(1, inspection.getResultOfInspection());
        }
    }


}
