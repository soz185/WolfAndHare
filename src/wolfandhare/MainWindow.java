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
		
		Canvas canvas = new Canvas(shlWolfAndHare, SWT.NONE | SWT.DOUBLE_BUFFERED);
		canvas.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		canvas.setBounds(10, 10, 500, 350);
		
		Label labelCountHares = new Label(shlWolfAndHare, SWT.RIGHT);
		labelCountHares.setBounds(578, 26, 117, 15);
		labelCountHares.setText("\u041A\u043E\u043B\u0438\u0447\u0435\u0441\u0442\u0432\u043E \u0437\u0430\u0439\u0446\u0435\u0432:");
		
		Spinner spinnerCountHare = new Spinner(shlWolfAndHare, SWT.BORDER);
		spinnerCountHare.setMaximum(50);
		spinnerCountHare.setBounds(701, 23, 47, 22);
		
		Spinner spinnerCountWolf = new Spinner(shlWolfAndHare, SWT.BORDER);
		spinnerCountWolf.setMaximum(35);
		spinnerCountWolf.setBounds(701, 51, 47, 22);
		
		Spinner spinnerCountObstacle = new Spinner(shlWolfAndHare, SWT.BORDER);
		spinnerCountObstacle.setMaximum(50);
		spinnerCountObstacle.setBounds(701, 79, 47, 22);
		
		Label labelCountWolf = new Label(shlWolfAndHare, SWT.RIGHT);
		labelCountWolf.setBounds(578, 54, 117, 15);
		labelCountWolf.setText("\u041A\u043E\u043B\u0438\u0447\u0435\u0441\u0442\u0432\u043E \u0432\u043E\u043B\u043A\u043E\u0432:");
		
		
		Label labelCurrentCountWolf = new Label(shlWolfAndHare, SWT.NONE);
		labelCurrentCountWolf.setBounds(10, 387, 117, 15);
		labelCurrentCountWolf.setText("\u041A\u043E\u043B\u0438\u0447\u0435\u0441\u0442\u0432\u043E \u0432\u043E\u043B\u043A\u043E\u0432: ");
		
		Label labelCurrentCountHare = new Label(shlWolfAndHare, SWT.NONE);
		labelCurrentCountHare.setBounds(10, 366, 117, 15);
		labelCurrentCountHare.setText("\u041A\u043E\u043B\u0438\u0447\u0435\u0441\u0442\u0432\u043E \u0437\u0430\u0439\u0446\u0435\u0432: ");
		
		Label labelVisionHare = new Label(shlWolfAndHare, SWT.NONE);
		labelVisionHare.setAlignment(SWT.RIGHT);
		labelVisionHare.setBounds(539, 105, 156, 32);
		labelVisionHare.setText("\u041C\u0430\u043A\u0441\u0438\u043C\u0430\u043B\u044C\u043D\u043E\u0435 \u0440\u0430\u0441\u0441\u0442\u043E\u044F\u043D\u0438\u0435, \u043D\u0430 \u043A\u043E\u0442\u043E\u0440\u043E\u043C \u0432\u0438\u0434\u044F\u0442 \u0437\u0430\u0439\u0446\u044B:");
		
		Label labelVisionWolf = new Label(shlWolfAndHare, SWT.NONE);
		labelVisionWolf.setText("\u041C\u0430\u043A\u0441\u0438\u043C\u0430\u043B\u044C\u043D\u043E\u0435 \u0440\u0430\u0441\u0441\u0442\u043E\u044F\u043D\u0438\u0435, \u043D\u0430 \u043A\u043E\u0442\u043E\u0440\u043E\u043C \u0432\u0438\u0434\u044F\u0442 \u0432\u043E\u043B\u043A\u0438:");
		labelVisionWolf.setAlignment(SWT.RIGHT);
		labelVisionWolf.setBounds(539, 143, 156, 32);
		
		Spinner spinnerVisionHare = new Spinner(shlWolfAndHare, SWT.BORDER);
		spinnerVisionHare.setMaximum(40);
		spinnerVisionHare.setMinimum(5);
		spinnerVisionHare.setBounds(701, 109, 47, 22);
		
		Spinner spinnerVisionWolf = new Spinner(shlWolfAndHare, SWT.BORDER);
		spinnerVisionWolf.setMaximum(60);
		spinnerVisionWolf.setMinimum(5);
		spinnerVisionWolf.setBounds(701, 143, 47, 22);
		
		Label currentCountHare = new Label(shlWolfAndHare, SWT.NONE);
		currentCountHare.setText("0");
		currentCountHare.setBounds(133, 366, 55, 15);
		
		Label currentCountWolf = new Label(shlWolfAndHare, SWT.NONE);
		currentCountWolf.setText("0");
		currentCountWolf.setBounds(133, 387, 55, 15);
		
		Button buttonStart = new Button(shlWolfAndHare, SWT.NONE);
		buttonStart.setBounds(539, 335, 75, 25);
		buttonStart.setText("\u0421\u0442\u0430\u0440\u0442");
		
		Button buttonStep = new Button(shlWolfAndHare, SWT.NONE);
		buttonStep.setBounds(637, 335, 75, 25);
		buttonStep.setText("\u0428\u0430\u0433");
		
		Button buttonStop = new Button(shlWolfAndHare, SWT.NONE);
		buttonStop.setBounds(735, 335, 75, 25);
		buttonStop.setText("\u041E\u0441\u0442\u0430\u043D\u043E\u0432\u0438\u0442\u044C");
		
		Label labelCountObstacle = new Label(shlWolfAndHare, SWT.RIGHT);
		labelCountObstacle.setText("\u041A\u043E\u043B\u0438\u0447\u0435\u0441\u0442\u0432\u043E \u043F\u0440\u0435\u043F\u044F\u0442\u0441\u0442\u0432\u0438\u0439:");
		labelCountObstacle.setBounds(510, 82, 185, 15);
		
		// непрерывное выполнение
		buttonStart.addSelectionListener(new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent e) {
			// отключение кнопок на время выполнения
			buttonStep.setEnabled(false);
			spinnerCountHare.setEnabled(false);
			spinnerCountWolf.setEnabled(false);
			spinnerCountObstacle.setEnabled(false);
			spinnerVisionHare.setEnabled(false);
			spinnerVisionWolf.setEnabled(false);
			// передача необходимых параметров в классы
			Hare.setVision(Integer.valueOf(spinnerVisionHare.getText()));
			Wolf.setVision(Integer.valueOf(spinnerVisionWolf.getText()));
			// создание объектов карты
			TMap.getMap().setMap(canvas, Integer.valueOf(spinnerCountHare.getText()), Integer.valueOf(spinnerCountWolf.getText()), Integer.valueOf(spinnerCountObstacle.getText()));
			GC gc = new GC(canvas);
			while (true)
			{
				// вывод текущего количества объектов
				int countWolf = TMap.getMap().getCountWolf();
				int countHare = TMap.getMap().getCountHare();
				currentCountWolf.setText(String.valueOf(countWolf));
				currentCountHare.setText(String.valueOf(countHare));
				if (countWolf == 0 || countHare == 0 || countWolf > 500 || countHare > 500) {
					gc.dispose();
					buttonStep.setEnabled(true);
					spinnerCountHare.setEnabled(true);
					spinnerCountWolf.setEnabled(true);
					spinnerCountObstacle.setEnabled(true);
					spinnerVisionHare.setEnabled(true);
					spinnerVisionWolf.setEnabled(true);
					return;
				}
				// отрисовка объектов
				canvas.drawBackground(gc, 0, 0, canvas.getBounds().width, canvas.getBounds().height);
				TMap.getMap().draw(canvas);
				try {
					Thread.sleep(30);
				} catch (InterruptedException ie) {
					ie.printStackTrace();
				}
			}
		}
	});
		
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
				buttonStart.setEnabled(false);
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
				buttonStart.setEnabled(true);
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
