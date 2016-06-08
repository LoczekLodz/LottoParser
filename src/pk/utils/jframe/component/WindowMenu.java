package pk.utils.jframe.component;

import java.awt.event.ActionListener;
import java.io.Serializable;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;


public class WindowMenu implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5508111247088428269L;

	public JMenuBar createMainMenuBar(WindowMenuBar menuBar) {
		JMenuBar mainMenuBar = new JMenuBar();
		
		if (null != menuBar && null != menuBar.getRootElementsList() && !menuBar.getRootElementsList().isEmpty()) {
			for (WindowMenuBarRootElement rootElement : menuBar.getRootElementsList()) {
				JMenu menu = createRootElement(rootElement);
				if (null != menu) {
					mainMenuBar.add(menu);
				}
			}			
			return mainMenuBar;
		}
		return null;
	}
	
	private JMenu createRootElement(WindowMenuBarRootElement rootElement) {
		JMenu menu = new JMenu(rootElement.getName());
		menu.setMnemonic(rootElement.getMnemonic());
		
		if (null != rootElement.getElements() && !rootElement.getElements().isEmpty()) {
			for (WindowMenuBarElement element : rootElement.getElements()) {
				if (element.getType().equals(MenuType.Id.ITEM.getValue())) {
					menu.add(createMenuItemWithAdds(element.getName(), element.getMnemonic(), element.getToolTip(), element.getAccelerator(), element.getAction()));
				} else if (element.getType().equals(MenuType.Id.SUBMENU.getValue())) {
					menu.add(createRootElement(element.getRootElement()));
				} else if (element.getType().equals(MenuType.Id.SEPARATOR.getValue())) {
					menu.addSeparator();
				}
			}
		}
		
		return menu;
	}
	
	private JMenuItem createMenuItemWithAdds(final String name, int actionKey, final String toolTip, final KeyStroke keyStroke, ActionListener action) {
		JMenuItem newMenuItem = new JMenuItem(name);
		newMenuItem.setMnemonic(actionKey);
		newMenuItem.setToolTipText(toolTip);
		if (null != keyStroke) {
			newMenuItem.setAccelerator(keyStroke);
		}
		if (null != action) {
			newMenuItem.addActionListener(action);
		}
		return newMenuItem;
	}
	
}
