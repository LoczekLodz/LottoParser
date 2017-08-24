package pk.lottoparser.window.action;

import java.awt.event.ActionEvent;

import pk.lottoparser.data.ParsedNumber;
import pk.lottoparser.data.SingleDraw;
import pk.lottoparser.window.OptionGameSelectionDialog;
import pk.lottoparser.window.OptionStatisticsSelectionDialog;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ComponentActions implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 489625756185989468L;
	
	private static ParsedNumber numbersFromFile;
	
	public static ActionListener actionExit() {
		return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
		};
	}
	
	public static ActionListener actionOpenFileParseNumbersDialog() {
		return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	JFileChooser fileChooser = new JFileChooser();
    	        FileFilter filter = new FileNameExtensionFilter("Txt files", "txt");
    	        fileChooser.addChoosableFileFilter(filter);
    	        int ret = fileChooser.showDialog(null, "Open file");
    	    	
    	        if (ret == JFileChooser.APPROVE_OPTION) {
    	            File file = fileChooser.getSelectedFile();
    	            final String textFileBuffer = readFile(file);
    	            
    	            if (null != textFileBuffer && !textFileBuffer.isEmpty()) {
    	            	numbersFromFile = parseLottoNumbers(textFileBuffer);
    	            }
    	        }
            }
		};
	}
	
	public static ActionListener actionOpenGameOptionDialog(final JFrame parentFrame) {
		return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	OptionGameSelectionDialog openGameSelection = new OptionGameSelectionDialog(parentFrame);
        		openGameSelection.setVisible(true);
            }
		};
	}
	
	public static ActionListener actionOpenStatisticsOptionDialog(final JFrame parentFrame) {
		return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	OptionStatisticsSelectionDialog openStatisticsSelection = new OptionStatisticsSelectionDialog(parentFrame);
            	openStatisticsSelection.setVisible(true);
            }
		};
	}
	
	public static ActionListener runAllRules() {
		return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	OptionStatisticsSelectionDialog.getStatisticsOptions().fireAllRule(numbersFromFile);
            }
		};
	}
	
	public static ActionListener generateAllVariations() {
		return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	OptionStatisticsSelectionDialog.getStatisticsOptions().generateAllVariations();
            }
		};
	}
	
	private static String readFile(File file) {
        String content = "";

        try {
            content = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
        } catch (IOException ex) {
        	return null;
        }
        return content;
    }
	
	private static ParsedNumber parseLottoNumbers(final String inputParsedLines) {
		String[] parsedLines = inputParsedLines.split("\n");
		List<SingleDraw> singleDrawsList = new ArrayList<SingleDraw>();
		ParsedNumber allNumbers = new ParsedNumber();
		
		for (String line : parsedLines) {
			SingleDraw singleDraw = new SingleDraw();
			List<Integer> singleLine = new ArrayList<Integer>();
			
			for (String number : line.replace("\r", "").replace("\t", "").split(" ")) {
				singleLine.add(Integer.parseInt(number));
			}
			singleDraw.setDrawNumbers(singleLine);
			singleDrawsList.add(singleDraw);
		}
		allNumbers.setDrawsList(singleDrawsList);
		return allNumbers;
	}

	public static ParsedNumber getNumbersFromFile() {
		return numbersFromFile;
	}

	public static void setNumbersFromFile(ParsedNumber numbersFromFile) {
		ComponentActions.numbersFromFile = numbersFromFile;
	}

}
