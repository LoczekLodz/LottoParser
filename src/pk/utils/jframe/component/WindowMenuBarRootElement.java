package pk.utils.jframe.component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class WindowMenuBarRootElement implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6196169187839305113L;
	
	private String name;
	private int mnemonic;
	private List<WindowMenuBarElement> elements = new ArrayList<WindowMenuBarElement>();
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMnemonic() {
		return mnemonic;
	}

	public void setMnemonic(int mnemonic) {
		this.mnemonic = mnemonic;
	}
	
	public List<WindowMenuBarElement> getElements() {
		return elements;
	}

	public void setElements(List<WindowMenuBarElement> elements) {
		this.elements = elements;
	}
	
}