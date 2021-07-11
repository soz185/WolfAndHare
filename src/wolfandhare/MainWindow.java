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
		shlWolfAndHare.setMinimumSize(new Point(900, 500));
		shlWolfAndHare.setSize(900, 500);
		shlWolfAndHare.setText("Wolf and Hare");
		
		Canvas canvas = new Canvas(shlWolfAndHare, SWT.DOUBLE_BUFFERED);
		canvas.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		canvas.setBounds(10, 10, 500, 350);
		
		Label labelCountHares = new Label(shlWolfAndHare, SWT.RIGHT);
		labelCountHares.setBounds(620, 25, 117, 15);
		labelCountHares.setText("\u041A\u043E\u043B\u0438\u0447\u0435\u0441\u0442\u0432\u043E \u0437\u0430\u0439\u0446\u0435\u0432:");
		
		Spinner spinnerCountHare = new Spinner(shlWolfAndHare, SWT.BORDER);
		spinnerCountHare.setBounds(743, 22, 47, 22);
		
		Spinner spinnerCountWolf = new Spinner(shlWolfAndHare, SWT.BORDER);
		spinnerCountWolf.setBounds(743, 50, 47, 22);
		
		Label labelCount = new Label(shlWolfAndHare, SWT.RIGHT);
		labelCount.setBounds(620, 53, 117, 15);
		labelCount.setText("\u041A\u043E\u043B\u0438\u0447\u0435\u0441\u0442\u0432\u043E \u0432\u043E\u043B\u043A\u043E\u0432:");
		
		
		Label labelCurrentCountWolf = new Label(shlWolfAndHare, SWT.NONE);
		labelCurrentCountWolf.setBounds(10, 387, 117, 15);
		labelCurrentCountWolf.setText("\u041A\u043E\u043B\u0438\u0447\u0435\u0441\u0442\u0432\u043E \u0432\u043E\u043B\u043A\u043E\u0432: ");
		
		Label labelCurrentCountHare = new Label(shlWolfAndHare, SWT.NONE);
		labelCurrentCountHare.setBounds(10, 366, 117, 15);
		labelCurrentCountHare.setText("\u041A\u043E\u043B\u0438\u0447\u0435\u0441\u0442\u0432\u043E \u0437\u0430\u0439\u0446\u0435\u0432: ");
		
		Label labelVisionHare = new Label(shlWolfAndHare, SWT.NONE);
		labelVisionHare.setAlignment(SWT.RIGHT);
		labelVisionHare.setBounds(581, 74, 156, 32);
		labelVisionHare.setText("\u041C\u0430\u043A\u0441\u0438\u043C\u0430\u043B\u044C\u043D\u043E\u0435 \u0440\u0430\u0441\u0441\u0442\u043E\u044F\u043D\u0438\u0435, \u043D\u0430 \u043A\u043E\u0442\u043E\u0440\u043E\u043C \u0432\u0438\u0434\u044F\u0442 \u0437\u0430\u0439\u0446\u044B:");
		
		Label labelVisionWolf = new Label(shlWolfAndHare, SWT.NONE);
		labelVisionWolf.setText("\u041C\u0430\u043A\u0441\u0438\u043C\u0430\u043B\u044C\u043D\u043E\u0435 \u0440\u0430\u0441\u0441\u0442\u043E\u044F\u043D\u0438\u0435, \u043D\u0430 \u043A\u043E\u0442\u043E\u0440\u043E\u043C \u0432\u0438\u0434\u044F\u0442 \u0432\u043E\u043B\u043A\u0438:");
		labelVisionWolf.setAlignment(SWT.RIGHT);
		labelVisionWolf.setBounds(581, 112, 156, 32);
		
		Spinner spinnerVisionHare = new Spinner(shlWolfAndHare, SWT.BORDER);
		spinnerVisionHare.setMaximum(20);
		spinnerVisionHare.setBounds(743, 78, 47, 22);
		
		Spinner spinnerVisionWolf = new Spinner(shlWolfAndHare, SWT.BORDER);
		spinnerVisionWolf.setMaximum(40);
		spinnerVisionWolf.setBounds(743, 112, 47, 22);
		
		Label countHare = new Label(shlWolfAndHare, SWT.NONE);
		countHare.setText("0");
		countHare.setBounds(133, 366, 55, 15);
		
		Label countWolf = new Label(shlWolfAndHare, SWT.NONE);
		countWolf.setText("0");
		countWolf.setBounds(133, 387, 55, 15);
		
		Button buttonStart = new Button(shlWolfAndHare, SWT.NONE);
		buttonStart.setBounds(563, 335, 75, 25);
		buttonStart.setText("\u0421\u0442\u0430\u0440\u0442");
		
		Button buttonStep = new Button(shlWolfAndHare, SWT.NONE);
		buttonStep.setBounds(661, 335, 75, 25);
		buttonStep.setText("\u0428\u0430\u0433");
		
		Button buttonStop = new Button(shlWolfAndHare, SWT.NONE);
		buttonStop.setBounds(759, 335, 75, 25);
		buttonStop.setText("\u041E\u0441\u0442\u0430\u043D\u043E\u0432\u0438\u0442\u044C");
		
		TMap items = new TMap();
		
		buttonStart.addSelectionListener(new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent e) {
			buttonStep.setEnabled(false);
			spinnerCountHare.setEnabled(false);
			spinnerCountWolf.setEnabled(false);
			spinnerVisionHare.setEnabled(false);
			spinnerVisionWolf.setEnabled(false);
			
			Hare.setVision(Integer.valueOf(spinnerVisionHare.getText()));
			Wolf.setVision(Integer.valueOf(spinnerVisionWolf.getText()));
			items.setMap(canvas, Integer.valueOf(spinnerCountHare.getText()), Integer.valueOf(spinnerCountWolf.getText()));
			GC gc = new GC(canvas);
			//for (int i = 0; i < 1000; i++)
			while (true)
			{
				countWolf.setText(String.valueOf(Wolf.getCountWolf()));
				countHare.setText(String.valueOf(Hare.getCountHare()));
				if (Wolf.getCountWolf() == 0 || Hare.getCountHare() == 0) {
					gc.dispose();
					return;
				}
				canvas.drawBackground(gc, 0, 0, canvas.getBounds().width, canvas.getBounds().height);
				items.draw(canvas);
				try {
					Thread.sleep(30);
				} catch (InterruptedException ie) {
					ie.printStackTrace();
				}
			}
		}
	});
		
		buttonStep.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (isNewMap) {
					items.setMap(canvas, Integer.valueOf(spinnerCountHare.getText()), Integer.valueOf(spinnerCountWolf.getText()));
					isNewMap = false;
				}
					buttonStart.setEnabled(false);
					spinnerCountHare.setEnabled(false);
					spinnerCountWolf.setEnabled(false);
					spinnerVisionHare.setEnabled(false);
					spinnerVisionWolf.setEnabled(false);
				
					Hare.setVision(Integer.valueOf(spinnerVisionHare.getText()));
					Wolf.setVision(Integer.valueOf(spinnerVisionWolf.getText()));
					//TMap items = new TMap(canvas, Integer.valueOf(spinnerCountHare.getText()), Integer.valueOf(spinnerCountWolf.getText()));
					GC gc = new GC(canvas);
					countWolf.setText(String.valueOf(Wolf.getCountWolf()));
					countHare.setText(String.valueOf(Hare.getCountHare()));
					canvas.drawBackground(gc, 0, 0, canvas.getBounds().width, canvas.getBounds().height);
					items.draw(canvas);
					gc.dispose();
			}
		});
		
		buttonStop.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				buttonStart.setEnabled(true);
				spinnerCountHare.setEnabled(true);
				spinnerCountWolf.setEnabled(true);
				spinnerVisionHare.setEnabled(true);
				spinnerVisionWolf.setEnabled(true);
				GC gc = new GC(canvas);
				canvas.drawBackground(gc, 0, 0, canvas.getBounds().width, canvas.getBounds().height);
				gc.dispose();
				isNewMap = true;
			}
		});
	}
}
