package wolfandhare;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;

public class MainWindow {

	protected Shell shlWolfAndHare;
	protected boolean isNewMap = true;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainWindow window = new MainWindow();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlWolfAndHare.open();
		shlWolfAndHare.layout();
		while (!shlWolfAndHare.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlWolfAndHare = new Shell();
		shlWolfAndHare.setMinimumSize(new Point(840, 450));
		shlWolfAndHare.setSize(840, 450);
		shlWolfAndHare.setText("Wolf and Hare");
		shlWolfAndHare.setLayout(null);
		
		Canvas canvas = new Canvas(shlWolfAndHare, SWT.NONE | SWT.DOUBLE_BUFFERED);
		canvas.setBounds(10, 10, 433, 298);
		canvas.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		Label labelCountHares = new Label(shlWolfAndHare, SWT.RIGHT);
		labelCountHares.setBounds(449, 27, 203, 22);
		labelCountHares.setText("\u041A\u043E\u043B\u0438\u0447\u0435\u0441\u0442\u0432\u043E \u0437\u0430\u0439\u0446\u0435\u0432:");
		
		Spinner spinnerCountHare = new Spinner(shlWolfAndHare, SWT.BORDER);
		spinnerCountHare.setBounds(658, 24, 64, 22);
		spinnerCountHare.setMaximum(50);
		
		Spinner spinnerCountWolf = new Spinner(shlWolfAndHare, SWT.BORDER);
		spinnerCountWolf.setBounds(658, 52, 64, 22);
		spinnerCountWolf.setMaximum(35);
		
		Spinner spinnerCountObstacle = new Spinner(shlWolfAndHare, SWT.BORDER);
		spinnerCountObstacle.setBounds(658, 80, 64, 22);
		spinnerCountObstacle.setMaximum(50);
		
		Label labelCountWolf = new Label(shlWolfAndHare, SWT.RIGHT);
		labelCountWolf.setBounds(449, 55, 203, 30);
		labelCountWolf.setText("\u041A\u043E\u043B\u0438\u0447\u0435\u0441\u0442\u0432\u043E \u0432\u043E\u043B\u043A\u043E\u0432:");
		
		
		Label labelCurrentCountWolf = new Label(shlWolfAndHare, SWT.NONE);
		labelCurrentCountWolf.setBounds(10, 352, 120, 22);
		labelCurrentCountWolf.setText("\u041A\u043E\u043B\u0438\u0447\u0435\u0441\u0442\u0432\u043E \u0432\u043E\u043B\u043A\u043E\u0432: ");
		
		Label labelCurrentCountHare = new Label(shlWolfAndHare, SWT.NONE);
		labelCurrentCountHare.setBounds(10, 324, 120, 22);
		labelCurrentCountHare.setText("\u041A\u043E\u043B\u0438\u0447\u0435\u0441\u0442\u0432\u043E \u0437\u0430\u0439\u0446\u0435\u0432: ");
		
		Label labelVisionHare = new Label(shlWolfAndHare, SWT.NONE);
		labelVisionHare.setBounds(449, 112, 203, 76);
		labelVisionHare.setAlignment(SWT.RIGHT);
		labelVisionHare.setText("\u041C\u0430\u043A\u0441\u0438\u043C\u0430\u043B\u044C\u043D\u043E\u0435 \u0440\u0430\u0441\u0441\u0442\u043E\u044F\u043D\u0438\u0435, \u043D\u0430 \u043A\u043E\u0442\u043E\u0440\u043E\u043C \u0432\u0438\u0434\u044F\u0442 \u0437\u0430\u0439\u0446\u044B:");
		
		Label labelVisionWolf = new Label(shlWolfAndHare, SWT.NONE);
		labelVisionWolf.setBounds(449, 152, 203, 70);
		labelVisionWolf.setText("\u041C\u0430\u043A\u0441\u0438\u043C\u0430\u043B\u044C\u043D\u043E\u0435 \u0440\u0430\u0441\u0441\u0442\u043E\u044F\u043D\u0438\u0435, \u043D\u0430 \u043A\u043E\u0442\u043E\u0440\u043E\u043C \u0432\u0438\u0434\u044F\u0442 \u0432\u043E\u043B\u043A\u0438:");
		labelVisionWolf.setAlignment(SWT.RIGHT);
		
		Spinner spinnerVisionHare = new Spinner(shlWolfAndHare, SWT.BORDER);
		spinnerVisionHare.setBounds(658, 124, 64, 22);
		spinnerVisionHare.setMaximum(40);
		spinnerVisionHare.setMinimum(5);
		
		Spinner spinnerVisionWolf = new Spinner(shlWolfAndHare, SWT.BORDER);
		spinnerVisionWolf.setBounds(658, 166, 64, 22);
		spinnerVisionWolf.setMaximum(60);
		spinnerVisionWolf.setMinimum(5);
		
		Label currentCountHare = new Label(shlWolfAndHare, SWT.NONE);
		currentCountHare.setBounds(136, 324, 55, 22);
		currentCountHare.setText("0");
		
		Label currentCountWolf = new Label(shlWolfAndHare, SWT.NONE);
		currentCountWolf.setBounds(136, 352, 55, 22);
		currentCountWolf.setText("0");
		
		Button buttonStep = new Button(shlWolfAndHare, SWT.NONE);
		buttonStep.setBounds(526, 335, 104, 25);
		buttonStep.setText("\u0428\u0430\u0433");
		
		Button buttonStop = new Button(shlWolfAndHare, SWT.NONE);
		buttonStop.setBounds(653, 335, 104, 25);
		buttonStop.setText("\u041E\u0441\u0442\u0430\u043D\u043E\u0432\u0438\u0442\u044C");
		
		Label labelCountObstacle = new Label(shlWolfAndHare, SWT.RIGHT);
		labelCountObstacle.setBounds(449, 83, 203, 22);
		labelCountObstacle.setText("\u041A\u043E\u043B\u0438\u0447\u0435\u0441\u0442\u0432\u043E \u043F\u0440\u0435\u043F\u044F\u0442\u0441\u0442\u0432\u0438\u0439:");
		
		Label lblNewLabel = new Label(shlWolfAndHare, SWT.NONE);
		lblNewLabel.setBounds(728, 27, 80, 22);
		lblNewLabel.setText("(\u043C\u0430\u043A\u0441. 50)");
		
		Label lblNewLabel_1 = new Label(shlWolfAndHare, SWT.NONE);
		lblNewLabel_1.setBounds(728, 55, 80, 30);
		lblNewLabel_1.setText("(\u043C\u0430\u043A\u0441. 35)");
		
		Label lblNewLabel_2 = new Label(shlWolfAndHare, SWT.NONE);
		lblNewLabel_2.setBounds(728, 83, 80, 22);
		lblNewLabel_2.setText("(\u043C\u0430\u043A\u0441. 50)");
		
		Label lblNewLabel_3 = new Label(shlWolfAndHare, SWT.NONE);
		lblNewLabel_3.setBounds(728, 127, 80, 27);
		lblNewLabel_3.setText("(\u043C\u0430\u043A\u0441. 40)");
		
		Label lblNewLabel_4 = new Label(shlWolfAndHare, SWT.NONE);
		lblNewLabel_4.setBounds(728, 169, 80, 29);
		lblNewLabel_4.setText("(\u043C\u0430\u043A\u0441. 60)");
		
		// пошаговое выполнение
		buttonStep.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (isNewMap) {
					// передача необходимых параметров в классы
					Hare.setVision(Integer.valueOf(spinnerVisionHare.getText()));
					Wolf.setVision(Integer.valueOf(spinnerVisionWolf.getText()));
					TMap.getMap().setMap(canvas, Integer.valueOf(spinnerCountHare.getText()), Integer.valueOf(spinnerCountWolf.getText()), Integer.valueOf(spinnerCountObstacle.getText()));
					isNewMap = false;
				}
				// отключение кнопок на время выполнения
				spinnerCountHare.setEnabled(false);
				spinnerCountWolf.setEnabled(false);
				spinnerCountObstacle.setEnabled(false);
				spinnerVisionHare.setEnabled(false);
				spinnerVisionWolf.setEnabled(false);
				GC gc = new GC(canvas);
				// вывод текущего количества объектов
				currentCountWolf.setText(String.valueOf(TMap.getMap().getCountWolf()));
				currentCountHare.setText(String.valueOf(TMap.getMap().getCountHare()));
				// отрисовка объектов
				canvas.drawBackground(gc, 0, 0, canvas.getBounds().width, canvas.getBounds().height);
				TMap.getMap().draw(canvas);
				gc.dispose();
			}
		});
		
		// остановка выполнения
		buttonStop.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// активация кнопок
				spinnerCountHare.setEnabled(true);
				spinnerCountWolf.setEnabled(true);
				spinnerCountObstacle.setEnabled(true);
				spinnerVisionHare.setEnabled(true);
				spinnerVisionWolf.setEnabled(true);
				// очистка карты
				currentCountWolf.setText("0");
				currentCountHare.setText("0");
				GC gc = new GC(canvas);
				canvas.drawBackground(gc, 0, 0, canvas.getBounds().width, canvas.getBounds().height);
				gc.dispose();
				isNewMap = true;
			}
		});
	}
}
