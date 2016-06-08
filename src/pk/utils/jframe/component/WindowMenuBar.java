package pk.utils.jframe.component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class WindowMenuBar implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6226056045094498507L;
	
	private List<WindowMenuBarRootElement> rootElementsList = new ArrayList<WindowMenuBarRootElement>();
	
	public List<WindowMenuBarRootElement> getRootElementsList() {
		return rootElementsList;
	}

	public void setRootElementsList(List<WindowMenuBarRootElement> rootElementsList) {
		this.rootElementsList = rootElementsList;
	}
	
}
