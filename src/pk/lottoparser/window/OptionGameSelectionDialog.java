package pk.lottoparser.window;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import pk.lottoparser.constant.Game;

public class OptionGameSelectionDialog extends JDialog implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3583072142014728931L;
	
	private static String optionChosen = Game.Id.MINI.getValue();
	private static String tmpOptionChosen = Game.Id.MINI.getValue();
	
	public OptionGameSelectionDialog(Frame parent) {
        super(parent);

        initUI();
    }

    private void initUI() {
    	createLayout();

        setModalityType(ModalityType.APPLICATION_MODAL);
        setTitle("Choose Game");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(getParent());
    }
    
    private List<JComponent> createRadioSectionDialogComponents() {
    	List<JComponent> components = new ArrayList<JComponent>();
    	ButtonGroup gameGroupButtons = new ButtonGroup();
    	
    	JRadioButton multiMultiButton = new JRadioButton(Game.Id.MULTILOTEK.getValue());
    	multiMultiButton.addActionListener(actionChangeGame());
    	if (Game.Id.MULTILOTEK.getValue().equals(optionChosen)) {
    		multiMultiButton.setSelected(true);
    	}
    	    	
    	JRadioButton lottoButton = new JRadioButton(Game.Id.LOTEK.getValue());
    	lottoButton.addActionListener(actionChangeGame());
    	if (Game.Id.LOTEK.getValue().equals(optionChosen)) {
    		lottoButton.setSelected(true);
    	}
    	
    	JRadioButton miniButton = new JRadioButton(Game.Id.MINI.getValue());
    	miniButton.addActionListener(actionChangeGame());
    	if (Game.Id.MINI.getValue().equals(optionChosen)) {
    		miniButton.setSelected(true);
    	}
    	
    	gameGroupButtons.add(lottoButton);    	
    	gameGroupButtons.add(multiMultiButton);
    	gameGroupButtons.add(miniButton);
    	
    	components.add(multiMultiButton);
    	components.add(lottoButton);
    	components.add(miniButton);
    	return components;
    }

    private List<JComponent> createBottomButtonsDialogComponents() {
    	List<JComponent> components = new ArrayList<JComponent>();
    	JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");
        
        okButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
            	setOptionChosen(getTmpOptionChosen());
                dispose();
            }
        });
        
        cancelButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                dispose();
            }
        });
        
        components.add(okButton);
        components.add(cancelButton);
        return components;
    }
    
    private void createLayout() {
    	JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        add(main);
    	
        JPanel radioPanel = new JPanel();
        radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.Y_AXIS));
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
        
        for (JComponent bottomButton : createRadioSectionDialogComponents()) {
        	radioPanel.add(bottomButton);
        }
        
        for (JComponent bottomButton : createBottomButtonsDialogComponents()) {
        	buttonPanel.add(bottomButton);
        }
        
        main.add(radioPanel);
        main.add(buttonPanel);
        pack();
    }
    
    public static ActionListener actionChangeGame() {
		return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	JRadioButton buttonChosen = (JRadioButton) event.getSource();
            	
            	if (buttonChosen.getText().equals(Game.Id.MULTILOTEK.getValue())) {
            		setTmpOptionChosen(Game.Id.MULTILOTEK.getValue());
            	} else if (buttonChosen.getText().equals(Game.Id.LOTEK.getValue())) {
            		setTmpOptionChosen(Game.Id.LOTEK.getValue());
            	} else if (buttonChosen.getText().equals(Game.Id.MINI.getValue())) {
            		setTmpOptionChosen(Game.Id.MINI.getValue());
            	}
            }
		};
    }
    
	public static String getOptionChosen() {
		return optionChosen;
	}

	public static void setOptionChosen(String optionChosen) {
		OptionGameSelectionDialog.optionChosen = optionChosen;
	}

	public static String getTmpOptionChosen() {
		return tmpOptionChosen;
	}

	public static void setTmpOptionChosen(String tmpOptionChosen) {
		OptionGameSelectionDialog.tmpOptionChosen = tmpOptionChosen;
	}
	
}
