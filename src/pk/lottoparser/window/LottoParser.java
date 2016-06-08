package pk.lottoparser.window;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.Serializable;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.KeyStroke;

import pk.lottoparser.window.action.ComponentActions;
import pk.utils.jframe.component.MenuType;
import pk.utils.jframe.component.WindowButton;
import pk.utils.jframe.component.WindowMenu;
import pk.utils.jframe.component.WindowMenuBar;
import pk.utils.jframe.component.WindowMenuBarElement;
import pk.utils.jframe.component.WindowMenuBarRootElement;

public class LottoParser extends JFrame implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6664352441591966546L;
	
	public LottoParser () {
		createMainWindow();
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
	        
	        @Override
	        public void run() {
	        	LottoParser lp = new LottoParser();
	            lp.setVisible(true);
	        }
	    });
	}
	
	private void createMainWindow() {
		setTitle("Simple example");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setJMenuBar(createMenu());
        
        JButton but1 = WindowButton.createButton("gunwo", KeyEvent.VK_Q);
        but1.addActionListener(ComponentActions.actionExit());
        
        createMainWindowLayout(but1);
	}
	
	private void createMainWindowLayout(JComponent... arg) {
		Container contentPane = getContentPane();
        GroupLayout groupLayout = new GroupLayout(contentPane);
        contentPane.setLayout(groupLayout);

        groupLayout.setAutoCreateContainerGaps(true);
        
        for(JComponent tempComponent : arg) {
        	groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup().addComponent(tempComponent));
            groupLayout.setVerticalGroup(groupLayout.createSequentialGroup().addComponent(tempComponent));
        }
	}
	
	private JMenuBar createMenu() {
		WindowMenu mainMenu = new WindowMenu();
		WindowMenuBar menuBar = new WindowMenuBar();
		
		//File Menu creator
		WindowMenuBarRootElement fileMenu = new WindowMenuBarRootElement();
		fileMenu.setName("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		menuBar.getRootElementsList().add(fileMenu);
		
		WindowMenuBarElement openFileElement = new WindowMenuBarElement();
		openFileElement.setType(MenuType.Id.ITEM.getValue());
		openFileElement.setName("Open");
		openFileElement.setMnemonic(KeyEvent.VK_O);
		openFileElement.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		openFileElement.setAction(ComponentActions.actionOpenFileParseNumbersDialog());
		
		WindowMenuBarElement sep1FileElement = new WindowMenuBarElement();
		sep1FileElement.setType(MenuType.Id.SEPARATOR.getValue());
		
		WindowMenuBarElement quitFileElement = new WindowMenuBarElement();
		quitFileElement.setType(MenuType.Id.ITEM.getValue());
		quitFileElement.setName("Quit");
		quitFileElement.setMnemonic(KeyEvent.VK_Q);
		quitFileElement.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
		quitFileElement.setAction(ComponentActions.actionExit());
		
		fileMenu.getElements().add(openFileElement);
		fileMenu.getElements().add(sep1FileElement);
		fileMenu.getElements().add(quitFileElement);
		
		WindowMenuBarRootElement optionMenu = new WindowMenuBarRootElement();
		optionMenu.setName("Option");
		optionMenu.setMnemonic(KeyEvent.VK_O);
		menuBar.getRootElementsList().add(optionMenu);
		
		WindowMenuBarElement gamesOptionElement = new WindowMenuBarElement();
		gamesOptionElement.setType(MenuType.Id.ITEM.getValue());
		gamesOptionElement.setName("Games");
		gamesOptionElement.setMnemonic(KeyEvent.VK_G);
		gamesOptionElement.setAction(ComponentActions.actionOpenGameOptionDialog(this));
		
		WindowMenuBarElement statsCalcOptionElement = new WindowMenuBarElement();
		statsCalcOptionElement.setType(MenuType.Id.ITEM.getValue());
		statsCalcOptionElement.setName("Statistics To Calc");
		statsCalcOptionElement.setMnemonic(KeyEvent.VK_S);
		statsCalcOptionElement.setAction(ComponentActions.actionOpenStatisticsOptionDialog(this));
		
		WindowMenuBarElement sep1OptElement = new WindowMenuBarElement();
		sep1OptElement.setType(MenuType.Id.SEPARATOR.getValue());
		
		WindowMenuBarElement runRulesOptionElement = new WindowMenuBarElement();
		runRulesOptionElement.setType(MenuType.Id.ITEM.getValue());
		runRulesOptionElement.setName("Execute All Rules");
		runRulesOptionElement.setMnemonic(KeyEvent.VK_E);
		runRulesOptionElement.setAction(ComponentActions.runAllRules());
		
		WindowMenuBarElement sep2OptElement = new WindowMenuBarElement();
		sep2OptElement.setType(MenuType.Id.SEPARATOR.getValue());
		
		WindowMenuBarElement genVariationssOptionElement = new WindowMenuBarElement();
		genVariationssOptionElement.setType(MenuType.Id.ITEM.getValue());
		genVariationssOptionElement.setName("Generate All Possible Variations");
		genVariationssOptionElement.setMnemonic(KeyEvent.VK_V);
		genVariationssOptionElement.setAction(ComponentActions.generateAllVariations());
		
//		WindowMenuBarRootElement subMenu = new WindowMenuBarRootElement();
//		subMenu.setName("subMenu");
//		subMenu.setMnemonic(KeyEvent.VK_M);
		
//		WindowMenuBarElement exampleSubMenuElement = new WindowMenuBarElement();
//		exampleSubMenuElement.setType(MenuType.Id.ITEM.getValue());
//		exampleSubMenuElement.setName("Example");		
//		subMenu.getElements().add(exampleSubMenuElement);
		
//		WindowMenuBarElement submenuOptionElement = new WindowMenuBarElement();
//		submenuOptionElement.setType(MenuType.Id.SUBMENU.getValue());
//		submenuOptionElement.setRootElement(subMenu);
		
		optionMenu.getElements().add(gamesOptionElement);
		optionMenu.getElements().add(statsCalcOptionElement);
		optionMenu.getElements().add(sep1OptElement);
		optionMenu.getElements().add(runRulesOptionElement);
		optionMenu.getElements().add(sep2OptElement);
		optionMenu.getElements().add(genVariationssOptionElement);
		
		WindowMenuBarRootElement helpMenu = new WindowMenuBarRootElement();
		helpMenu.setName("Help");
		helpMenu.setMnemonic(KeyEvent.VK_H);
		menuBar.getRootElementsList().add(helpMenu);
		
		return mainMenu.createMainMenuBar(menuBar);
	}

}