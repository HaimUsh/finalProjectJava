package boot;

import model.MyModel;
import presenter.Presenter;
import presenter.Properties;
import view.BasicWindow;
import view.MazeWindow;
import view.MyView;

public class Run {

	public static void main(String[] args) {


		Properties prop= new Properties();
		prop.loadPreferences();
		MyModel model= new MyModel(prop.getNumOfThreads(),prop.getGenerateAlgo(),prop.getSolveAlgo(),prop.getGameInterface());
		MyView ui= new MyView();
		Presenter p= new Presenter(model, ui);
		model.addObserver(p);
		ui.addObserver(p);
		ui.start();
		BasicWindow bs= new MazeWindow("maze",500,600);
		bs.run();



	}

}
