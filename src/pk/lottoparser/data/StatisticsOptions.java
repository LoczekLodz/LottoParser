package pk.lottoparser.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import pk.lottoparser.constant.Game;
import pk.lottoparser.constant.Statistics;
import pk.lottoparser.constant.Variations;
import pk.lottoparser.constant.Variations.Id;
import pk.lottoparser.window.OptionGameSelectionDialog;

public class StatisticsOptions {

	private static int[] combinations = new int[100];
	private static ArrayList<String> combinationList = new ArrayList<String>(1000000);
	private Set<Statistics.Id> statisticsList = new HashSet<Statistics.Id>();
	private Set<StatisticsData> statisticsAllPossibleVariations = new HashSet<StatisticsData>(1000000);
	private Map<Statistics.Id, Variations.Id> variations2StatsDL = new HashMap<Statistics.Id, Variations.Id>();
	
	{
		//Defaults
		addNewStatisticRule(Statistics.Id.NEG_ALL);
		variations2StatsDL.put(Statistics.Id.STALA_3, Variations.Id._3z6);
		variations2StatsDL.put(Statistics.Id.STALA_4, Variations.Id._4z6);
		variations2StatsDL.put(Statistics.Id.STALA_5, Variations.Id._5z6);
		variations2StatsDL.put(Statistics.Id.STALA_6, Variations.Id._6z6);
	}
	
	public void toggleStatisticRule(Statistics.Id statRule) {
		if (!statisticsList.contains(statRule)) {
			addNewStatisticRule(statRule);
		} else {
			removeStatisticRule(statRule);
		}
	}

	private void addNewStatisticRule(Statistics.Id statRule) {
		if (!statisticsList.contains(statRule)) {
			statisticsList.add(statRule);
		}
	}

	private void removeStatisticRule(Statistics.Id statRule) {
		if (statisticsList.contains(statRule)) {
			statisticsList.remove(statRule);
		}
	}

	public Set<Statistics.Id> getStatisticsList() {
		return statisticsList;
	}

	public void setStatisticsList(Set<Statistics.Id> statisticsList) {
		this.statisticsList = statisticsList;
	}
	
	public static int[] getCombinations() {
		return combinations;
	}

	public static void setCombinations(int[] combinations) {
		StatisticsOptions.combinations = combinations;
	}

	public static ArrayList<String> getCombinationList() {
		return combinationList;
	}

	public static void setCombinationList(ArrayList<String> combinationList) {
		StatisticsOptions.combinationList = combinationList;
	}

	public void fireAllRule(final ParsedNumber numbersFromFile) {
		for (Statistics.Id statRule : statisticsList) {
			if (statRule.equals(Statistics.Id.STALA_3)) {
				fireRuleStala(numbersFromFile, 3, Statistics.Id.STALA_3);
			} else if (statRule.equals(Statistics.Id.STALA_4)) {
				fireRuleStala(numbersFromFile, 4, Statistics.Id.STALA_4);
			} else if (statRule.equals(Statistics.Id.STALA_5)) {
				fireRuleStala(numbersFromFile, 5, Statistics.Id.STALA_5);
			} else if (statRule.equals(Statistics.Id.STALA_6)) {
				fireRuleStala(numbersFromFile, 6, Statistics.Id.STALA_6);
			} else if (statRule.equals(Statistics.Id.TOP10)) {
				fireRuleTop10();
			} else if (statRule.equals(Statistics.Id.NEG_ALL)) {
				fireRuleNegation(numbersFromFile, 6, Statistics.Id.NEG_ALL);
			}
		}		
	}
	
	private void evaluateRules(final Statistics.Id type) {
		write2File(type.getValue(), true);
		statisticsAllPossibleVariations.clear();
  		combinationList.clear();
	}
	
	private void evaluateRulesNegation(final Statistics.Id type, final String outputFileName) {
		try {
			File outputFile =  new File(outputFileName);
			BufferedReader readFile = new BufferedReader(new FileReader(outputFile));
			
			File outputTmpFile = new File(outputFileName + ".tmp");
			PrintWriter writeFile = new PrintWriter(new FileWriter(outputTmpFile));
			
			String line;
			while ((line = readFile.readLine()) != null) {
				int[] numArray = new int[6];
				int i = 0;
				for (String singleNum : line.split(" ")) {
					numArray[i++] = Integer.parseInt(singleNum);
				}
				
				for (StatisticsData data : statisticsAllPossibleVariations) {
					if (!data.equals(numArray, data.getUnitData().getNumbers().size())) {
						writeFile.println(line);
						writeFile.flush();
						break;
					}
				}
			}
			
			readFile.close();
			writeFile.close();

			outputFile.delete();
			outputTmpFile.renameTo(new File(outputFileName));
		
		} catch (IOException e) {
			System.err.println("Error: " + e.getMessage());
		}
	}

	private void fireRuleStala(final ParsedNumber numbersFromFile, final int count, final Statistics.Id type) {
		fireRuleStaleAll(numbersFromFile, count, type);
		evaluateRules(type);
	}

	private void fireRuleTop10() {

	}
	
	private void fireRuleNegation(final ParsedNumber numbersFromFile, final int count, final Statistics.Id type) {
		try {
			final String outputFileName = type.getValue() + "_output_file.txt";
			File oldFile = new File(outputFileName);
			oldFile.delete();
			
			File outputFile = new File(outputFileName);
			if (OptionGameSelectionDialog.getOptionChosen().equals(Game.Id.LOTEK.getValue())) {
				File fstream = new File(Variations.Id._6z49.getValue() + "_variations.txt");
				Files.copy(fstream.toPath(), outputFile.toPath());
			}

			for (int i=3; i<count-1; i++) {
				fireRuleStaleAll(numbersFromFile, i, type);
				evaluateRulesNegation(type, outputFileName);	
			}
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}
	
	private void fireRuleStaleAll(final ParsedNumber numbersFromFile, final int count, final Statistics.Id type) {
		int drawNumber = 1;
		if (null != numbersFromFile && null != numbersFromFile.getDrawsList()
				&& !numbersFromFile.getDrawsList().isEmpty()) {
			
			getAllVariationListFromFile(count);
			
			for (SingleDraw singleDraw : numbersFromFile.getDrawsList()) {
				System.out.println(drawNumber++);
				getAllSceneriosFromSingleDraw(singleDraw, count, type);
			}
		}
	}

	private void getAllSceneriosFromSingleDraw(final SingleDraw singleDraw,
			int maxNumbers, Statistics.Id id) {
		
		int[] simpleArray = createIntArray(singleDraw);
		for (String combination : combinationList) {
			List<Integer> tmpScenario = new ArrayList<Integer>();
			
			//System.out.println(combination + " - " + singleDraw.getDrawNumbers());
			for (String number : combination.split(" ")) {
				tmpScenario.add(simpleArray[Integer.parseInt(number) - 1]);
			}
			
			StatisticsData firedRule = createStatisticDataStruct(id, tmpScenario);
			checkDuplicityAdIncrement(firedRule);
		}
	}

	private void checkDuplicityAdIncrement(final StatisticsData firedRule) {
		boolean isDuplicated = false;
		if (!statisticsAllPossibleVariations.isEmpty()) {
			for (StatisticsData data : statisticsAllPossibleVariations) {
				if (data.compareAndIncrement(firedRule)) {
					isDuplicated = true;
					break;
				}
			}
		}

		if (!isDuplicated) {
			statisticsAllPossibleVariations.add(firedRule);
		}
	}

	private int[] createIntArray(final SingleDraw singleDraw) {
		int[] tmpArray = new int[singleDraw.getDrawNumbers().size()];
		int index = 0;

		for (Integer tmpInt : singleDraw.getDrawNumbers()) {
			tmpArray[index++] = tmpInt;
		}
		return tmpArray;
	}
	
	private static void getAllVariationList(final int sourceCollecionSize,
			final int maxIntVariable, final int variationNumber, BufferedWriter text) {
		int i;
		if (maxIntVariable == variationNumber) {
			String variationText = "";
			for (i = 1; i <= maxIntVariable; i++) {
				variationText = variationText.concat(String.valueOf(combinations[i])).concat(
						" ");
			}
			if (null != text) {
				try {
					text.write(variationText.substring(0, variationText.length() - 1));
					text.newLine();
				} catch (IOException e) {
					System.err.println("Error: " + e.getMessage());
					return;
				}
			}
		} else {
			for (i = combinations[variationNumber] + 1; i <= sourceCollecionSize; i++) {
				combinations[variationNumber + 1] = i;
				getAllVariationList(sourceCollecionSize, maxIntVariable, variationNumber + 1, text);
			}
		}
	}
	
	public void getAllVariationListFromFile(int count) {
		String fileName = ""; 
		if (OptionGameSelectionDialog.getOptionChosen().equals(Game.Id.LOTEK.getValue())) {
			fileName = count + "z6_variations.txt";
		} else if (OptionGameSelectionDialog.getOptionChosen().equals(Game.Id.MINI.getValue())) {
			fileName = count + "z5_variations.txt";
		}
		
		try {
			File fstream = new File(fileName);
			BufferedReader readFile = new BufferedReader(new FileReader(fstream));
		
			String line;
			while ((line = readFile.readLine()) != null) {
				combinationList.add(line);
			}
			
			readFile.close();
		} catch (IOException e) {
			System.err.println("Error: " + e.getMessage());
		}
	}
	
	public void generateAllVariations() {
		for (Id variation : Variations.Id.values()) {
			final String[] numbers = variation.getValue().split("z");
			
			try {
				FileWriter fstream = new FileWriter(variation.getValue() + "_variations.txt");
				BufferedWriter outBuffer = new BufferedWriter(fstream);
				
				getAllVariationList(Integer.parseInt(numbers[1]), Integer.parseInt(numbers[0]), 0, outBuffer);
				outBuffer.close();
			} catch (Exception e) {//Catch exception if any
				System.err.println("Error: " + e.getMessage());
			}
		}
	}
	
	private StatisticsData createStatisticDataStruct(final Statistics.Id id, final List<Integer> numList) {
		StatisticsData statData = new StatisticsData();
		if (null != id) {
			statData.setTypeOfStatRule(id);
		}

		StatisticsData.UnitData singleUnit = new StatisticsData.UnitData();
		singleUnit.setNumbers(numList);
		singleUnit.setOccuredNumber(1);
		statData.setUnitData(singleUnit);
		return statData;
	}
	
	private void write2File(final String type, boolean withOccurance) {
		try{
			// Create file
			FileWriter fstream = new FileWriter(type + "_variations.txt");
			BufferedWriter out = new BufferedWriter(fstream);
		
			for (StatisticsData singleEntry : statisticsAllPossibleVariations) {
				if (null != singleEntry && null != singleEntry.getUnitData()) {
					if (withOccurance) {
						out.write(singleEntry.getUnitData().getOccuredNumber() + " - ");
					}					
					out.write(singleEntry.getUnitData().toString());
					out.newLine();
				}
			}
		    
			out.close();
		} catch (Exception e) {//Catch exception if any
				System.err.println("Error: " + e.getMessage());
		}
	}
	
}
