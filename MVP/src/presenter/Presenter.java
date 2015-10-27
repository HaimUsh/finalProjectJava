package presenter;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import model.Model;
import model.MyModel;
import view.MyView;
import view.View;
import controller.Command;
import controller.CommandList;
import controller.DirCommand;
import controller.DisplayCrossSectionCommand;
import controller.DisplayMazeCommand;
import controller.DisplaySolutionCommand;
import controller.FileSizeCommand;
import controller.Generate3DMazeCommand;
import controller.LoadMazeCommand;
import controller.MazeSizeCommand;
import controller.SaveMazeCommand;

public class Presenter implements Observer {

	Model model;
	View ui;
	HashMap<String, Command> commandList;


	@Override
	public void update(Observable o, Object arg) {

		if (o instanceof MyModel)
		{
			String command = ((String) arg);
			switch (command)
			{
			case "dir":
			{
				ui.displayDirectory((String[]) model.getCommandData().get("dir"));
			} 	break;
			case "generated":
			{
				
			}	break;
			case "crossed":
			{
				ui.displayCrossSection((int[][]) model.getCommandData().get("crossed"));
			}	break;
			case "calcedMazeSize":
			{
				ui.displaySize((String) model.getCommandData().get("maze"), (double) model.getCommandData().get("calcedMazeSize"));
			}	break;
			case "loaded":
			{
			}	break;
			case "saved":
			{
			}	break;
			case "solved":
			{
				ui.display("Solution for maze: "+model.getCommandData().get("solved")+" is ready");
			}	break;
			case "notify":
			{
				ui.display((String) model.getCommandData().get("notify"));
			}	break;
			case "quit":
			{
				ui.display((String) model.getCommandData().get("quit"));
			}	break;
			case "saveZip":
			{
				ui.display((String) model.getCommandData().get("saveZip"));
			}	break;
			case "loadZip":
			{
				ui.display((String) model.getCommandData().get("loadZip"));
			}	break;
				

			default:
				ui.display("ERROR!!!");
				break;
			}
		}
		else
		{
			if (o instanceof MyView)
			{
;
				invokeCommand((String)arg);
			}
			else
			{
				System.out.println("ERROR");
				return;
			}
		}
	}

	public Presenter(Model m, View v) {
		this.ui=v;
		this.model=m;
		this.commandList= new HashMap<String, Command>();
		commandList.put("dir", new DirCommand());
		commandList.put("generate",new Generate3DMazeCommand());
		commandList.put("displayMaze",new DisplayMazeCommand());
		commandList.put("displayCrossSection",new DisplayCrossSectionCommand());
		commandList.put("save",new SaveMazeCommand());
		commandList.put("load",new LoadMazeCommand());
		commandList.put("mazeSize",new MazeSizeCommand());
		commandList.put("fileSize",new FileSizeCommand());
		commandList.put("solve",new Command() {
			
			@Override
			public void doCommand(String name, View v, Model m) {
				m.solveMaze(name);
				
			}
		});
		commandList.put("displaySolution",new DisplaySolutionCommand());
		commandList.put("menu",new CommandList());
		commandList.put("exit", new Command(){
			@Override
			public void doCommand(String command, View v, Model m) {
				model.officialExit();
			}
		});
	}
	
	
	
	public void display(String s){
		ui.display(s);
	}
	
	public HashMap<String, Command> getCommandList(){
		return this.commandList;
	}
	
	public void invokeCommand(String command) {
		String[] sp = command.split(" ");

		String commandName = sp[0];
		
		String args = null;
		if (sp.length > 1){
			args = new String();
			for (int i = 1; i < sp.length; i++)
				if(i==1)
					args = sp[i];
				else
					args += " " + sp[i];
		}
	
		Command cmd = selectCommand(commandName);
		if (cmd != null)
			cmd.doCommand(args,ui, model);
		else
			ui.display("Command not found");
			
	}
	

	public Command selectCommand(String commandName){
			
		return commandList.get(commandName);
	}
}