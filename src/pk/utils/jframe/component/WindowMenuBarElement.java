package pk.utils.jframe.component;

import java.awt.event.ActionListener;
import java.io.Serializable;
import javax.swing.KeyStroke;

public class WindowMenuBarElement implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3583046715932555379L;

	private String type;
	private String name;
	private String toolTip;
	private int mnemonic;
	private KeyStroke accelerator;
	private ActionListener action;
	private WindowMenuBarRootElement rootElement = new WindowMenuBarRootElement();
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getToolTip() {
		return toolTip;
	}

	public void setToolTip(String toolTip) {
		this.toolTip = toolTip;
	}

	public int getMnemonic() {
		return mnemonic;
	}

	public void setMnemonic(int mnemonic) {
		this.mnemonic = mnemonic;
	}

	public KeyStroke getAccelerator() {
		return accelerator;
	}

	public void setAccelerator(KeyStroke accelerator) {
		this.accelerator = accelerator;
	}

	public ActionListener getAction() {
		return action;
	}

	public void setAction(ActionListener action) {
		this.action = action;
	}

	public WindowMenuBarRootElement getRootElement() {
		return rootElement;
	}

	public void setRootElement(WindowMenuBarRootElement rootElement) {
		this.rootElement = rootElement;
	}
	
}
