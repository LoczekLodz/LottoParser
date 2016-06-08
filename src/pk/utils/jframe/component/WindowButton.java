package pk.utils.jframe.component;

import java.io.Serializable;
import javax.swing.JButton;

public class WindowButton  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1072021147459304676L;

	public static JButton createButton(final String name) {
		JButton newButton = new JButton(name);
		return newButton;
	}
	
	public static JButton createButton(final String name, int keyEvent) {
		JButton newButton = new JButton(name);
		newButton.setMnemonic(keyEvent);
		return newButton;
	}
}
