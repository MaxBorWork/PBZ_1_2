package by.borisevich.gibdd.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

public class MainWindow {

    private Display display;
    private Shell shell;
    private CarView carView;
    private CarInspectionView carInspectionView;
    private InspectorView inspectorView;

    public MainWindow() {
        display = new Display();
        shell = new Shell(display);
        carView = new CarView(this);
        carInspectionView = new CarInspectionView(this);
        inspectorView = new InspectorView(this);
        shell.setText("Кулинарная книга");
        shell.setSize(1400, 900);

        initLayout(shell, 2);
        initMenuBar();

        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        display.dispose();
    }

    private void initMenuBar() {
        Menu mainMenu = new Menu(shell, SWT.BAR);

        final MenuItem carSubMenuItem = new MenuItem(mainMenu, SWT.CASCADE);
        carSubMenuItem.setText("Авто");

        MenuItem carInspectionSubMenuItem = new MenuItem(mainMenu, SWT.CASCADE);
        carInspectionSubMenuItem.setText("Осмотр");

        MenuItem carInspectorSubMenuItem = new MenuItem(mainMenu, SWT.CASCADE);
        carInspectorSubMenuItem.setText("Инспектор");

        MenuItem extraTasksSubMenuItem = new MenuItem(mainMenu, SWT.CASCADE);
        extraTasksSubMenuItem.setText("Дополнительно");

        Menu carSubMenu = new Menu(shell, SWT.DROP_DOWN);
        carSubMenuItem.setMenu(carSubMenu);

        MenuItem addCar = new MenuItem(carSubMenu, SWT.PUSH);
        addCar.setText("Добавить");
        addCar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                carView.addCarForm();
            }
        });

        MenuItem editCar = new MenuItem(carSubMenu, SWT.PUSH);
        editCar.setText("Изменить");
        editCar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                carView.updateCar();
            }
        });

        MenuItem deleteCar = new MenuItem(carSubMenu, SWT.PUSH);
        deleteCar.setText("Удалить");
        deleteCar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                carView.deleteCar();
            }
        });

        Menu carInspectionSubMenu = new Menu(shell, SWT.DROP_DOWN);
        carInspectionSubMenuItem.setMenu(carInspectionSubMenu);

        MenuItem addCarInspection = new MenuItem(carInspectionSubMenu, SWT.PUSH);
        addCarInspection.setText("Добавить");
        addCarInspection.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                carInspectionView.addInspectionForm();
            }
        });

        MenuItem editCarInspection = new MenuItem(carInspectionSubMenu, SWT.PUSH);
        editCarInspection.setText("Изменить");
        editCarInspection.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                carInspectionView.updateInspection();
            }
        });

        MenuItem deleteCarInspection = new MenuItem(carInspectionSubMenu, SWT.PUSH);
        deleteCarInspection.setText("Удалить");
        deleteCarInspection.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                carInspectionView.deleteInspection();
            }
        });

        Menu carInspectorSubMenu = new Menu(shell, SWT.DROP_DOWN);
        carInspectorSubMenuItem.setMenu(carInspectorSubMenu);

        MenuItem addCarInspector = new MenuItem(carInspectorSubMenu, SWT.PUSH);
        addCarInspector.setText("Добавить");
        addCarInspector.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                inspectorView.addInspectionForm();
            }
        });

        MenuItem editCarInspector = new MenuItem(carInspectorSubMenu, SWT.PUSH);
        editCarInspector.setText("Изменить");
        editCarInspector.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                inspectorView.updateInspector();
            }
        });

        MenuItem deleteCarInspector = new MenuItem(carInspectorSubMenu, SWT.PUSH);
        deleteCarInspector.setText("Удалить");
        deleteCarInspector.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                inspectorView.deleteInspector();
            }
        });

        Menu extraTasksSubMenu = new Menu(shell, SWT.DROP_DOWN);
        extraTasksSubMenuItem.setMenu(extraTasksSubMenu);

        MenuItem carsByDatesItem = new MenuItem(extraTasksSubMenu, SWT.PUSH);
        carsByDatesItem.setText("Авто по дням");
        carsByDatesItem.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {

            }
        });

        MenuItem inspectorsByDateItem = new MenuItem(extraTasksSubMenu, SWT.PUSH);
        inspectorsByDateItem.setText("Инспекторы за день");
        inspectorsByDateItem.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                inspectorView.showInspectionsForTheCar();
            }
        });

        MenuItem inspectionsByCarItem = new MenuItem(extraTasksSubMenu, SWT.PUSH);
        inspectionsByCarItem.setText("Осмотры авто");
        inspectionsByCarItem.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                carInspectionView.showInspectionsForTheCar();
            }
        });

        shell.setMenuBar(mainMenu);
    }

    void updateShell() {

    }

    void initLayout(Shell shell, int numColumns) {
        GridLayout gridLayout = new GridLayout();
        gridLayout.makeColumnsEqualWidth = false;
        gridLayout.numColumns = numColumns;
        shell.setLayout(gridLayout);
    }

    Text createTextField(Composite compositeName, Label labelName, String text, Shell parent, Layout layout) {
        compositeName = new Composite(parent, SWT.NONE);
        compositeName.setLayout(layout);

        labelName = new Label(compositeName, SWT.NONE);
        labelName.setText("Введите "+text+":");

        Text textName = new Text(compositeName, SWT.CENTER);
        textName.setSize(100, 100);

        return textName;
    }

    Display getDisplay() {
        return display;
    }

    Shell getShell() {
        return shell;
    }
}
