package pk.lottoparser.window;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;

import pk.lottoparser.constant.Statistics;
import pk.lottoparser.data.StatisticsOptions;

public class OptionStatisticsSelectionDialog extends JDialog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5246969244688383451L;
	
	private static StatisticsOptions statisticsOptions = new StatisticsOptions();
	private static StatisticsOptions tmpStatisticsOptions = new StatisticsOptions();

	public OptionStatisticsSelectionDialog(Frame parent) {
        super(parent);

        initUI();
    }

    public static StatisticsOptions getStatisticsOptions() {
		return statisticsOptions;
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
    	
    	
    	
    	JCheckBox stala3 = new JCheckBox(Statistics.Id.STALA_3.getValue());
    	stala3.addActionListener(actionAddStatistics());
    	if (statisticsOptions.getStatisticsList().contains(Statistics.Id.STALA_3)) {
    		stala3.setSelected(true);
    	}
    	
    	JCheckBox stala4 = new JCheckBox(Statistics.Id.STALA_4.getValue());
    	stala4.addActionListener(actionAddStatistics());
    	if (statisticsOptions.getStatisticsList().contains(Statistics.Id.STALA_4)) {
    		stala4.setSelected(true);
    	}
    	
    	JCheckBox stala5 = new JCheckBox(Statistics.Id.STALA_5.getValue());
    	stala5.addActionListener(actionAddStatistics());
    	if (statisticsOptions.getStatisticsList().contains(Statistics.Id.STALA_5)) {
    		stala5.setSelected(true);
    	}
    	
    	JCheckBox top10 = new JCheckBox(Statistics.Id.TOP10.getValue());
    	top10.addActionListener(actionAddStatistics());
    	if (statisticsOptions.getStatisticsList().contains(Statistics.Id.TOP10)) {
    		top10.setSelected(true);
    	}
    	
    	JCheckBox negall = new JCheckBox(Statistics.Id.NEG_ALL.getValue());
    	negall.addActionListener(actionAddStatistics());
    	if (statisticsOptions.getStatisticsList().contains(Statistics.Id.NEG_ALL)) {
    		negall.setSelected(true);
    	}

    	components.add(stala3);
    	components.add(stala4);
    	components.add(stala5);
    	components.add(top10);
    	components.add(negall);
    	return components;
    }

    private List<JComponent> createBottomButtonsDialogComponents() {
    	List<JComponent> components = new ArrayList<JComponent>();
    	JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");
        
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	statisticsOptions.setStatisticsList(tmpStatisticsOptions.getStatisticsList());
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
    
    public static ActionListener actionAddStatistics() {
		return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	JCheckBox checkBoxChossen = (JCheckBox) event.getSource();
            	
            	if (checkBoxChossen.getText().equals(Statistics.Id.STALA_5.getValue())) {
            		tmpStatisticsOptions.toggleStatisticRule(Statistics.Id.STALA_5);
            	} else if (checkBoxChossen.getText().equals(Statistics.Id.STALA_4.getValue())) {
            		tmpStatisticsOptions.toggleStatisticRule(Statistics.Id.STALA_4);
            	} else if (checkBoxChossen.getText().equals(Statistics.Id.STALA_3.getValue())) {
            		tmpStatisticsOptions.toggleStatisticRule(Statistics.Id.STALA_3);
            	} else if (checkBoxChossen.getText().equals(Statistics.Id.TOP10.getValue())) {
            		tmpStatisticsOptions.toggleStatisticRule(Statistics.Id.TOP10);
            	}
            }
		};
    }
    
}
