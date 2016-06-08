package pk.lottoparser.data;

import java.util.ArrayList;
import java.util.List;

import pk.lottoparser.constant.Statistics;

public class StatisticsData {
	
	private Statistics.Id typeOfStatRule;
	
	private UnitData unitData;

	public Statistics.Id getTypeOfStatRule() {
		return typeOfStatRule;
	}

	public void setTypeOfStatRule(Statistics.Id typeOfStatRule) {
		this.typeOfStatRule = typeOfStatRule;
	}
	
	public UnitData getUnitData() {
		return unitData;
	}

	public void setUnitData(UnitData unitData) {
		this.unitData = unitData;
	}
	
	public boolean compareAndIncrement(StatisticsData statData) {
		if (statData.getTypeOfStatRule().getValue().equals(this.getTypeOfStatRule().getValue())) {
			if (this.getUnitData().equals(statData.getUnitData())) {
				this.getUnitData().setOccuredNumber(this.getUnitData().getOccuredNumber() + 1);
				return true;
			}
		}	
		return false;
	}
	
	public boolean equals(StatisticsData statData) {
		if (statData.getTypeOfStatRule().getValue().equals(this.getTypeOfStatRule().getValue())) {
			if (this.getUnitData().equals(statData.getUnitData())) {
				return true;
			}
		}	
		return false;
	}

	public static class UnitData {
		private List<Integer>  numbers = new ArrayList<Integer>();
		private int occuredNumber;
		
		public List<Integer> getNumbers() {
			return numbers;
		}

		public void setNumbers(List<Integer> numbers) {
			this.numbers = numbers;
		}

		public int getOccuredNumber() {
			return occuredNumber;
		}

		public void setOccuredNumber(int occuredNumber) {
			this.occuredNumber = occuredNumber;
		}
		
		public boolean equals(final UnitData uniData) {
			for (int number : uniData.numbers) {
				 if (!this.numbers.contains(number)) {
					 return false;
				 }
			}
			return true;
		}
		
		@Override
		public String toString() {
			String outText = new String("");
			
			for (Integer number : numbers) {
				outText = outText.concat(number.toString());
				outText = outText.concat(", ");
			}
			
			return outText.substring(0, outText.length() -2);
		}
		
	}

	public boolean equals(int[] numArray, int size) {
		final List<Integer> inputList = new ArrayList<Integer>();
		
		for (int i=0; i<numArray.length; i++) {
			inputList.add(numArray[i]);
		}
		
		for (final Integer number : this.getUnitData().numbers) {
			if (!inputList.contains(number)) {
				return false;
			}
		}
		return true;
	}

}
