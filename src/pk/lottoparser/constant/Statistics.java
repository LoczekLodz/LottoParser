package pk.lottoparser.constant;

public abstract class Statistics {
	
	public enum Id {
		STALA_3("3 w losowaniu"),
		STALA_4("4 w losowaniach"),
		STALA_5("5 w losowaniach"),
		STALA_6("6 w losowaniach"),
		TOP10("Top 10"),
		NEG_ALL("negacja wszystkiego"),
		;
		
		private final String value;

		/**
		 * Constructs an instance of game ID.
		 *
		 * @param value
		 *            the ID value to set
		 */
		private Id(final String value) {
			this.value = value;
		}

		/**
		 * Gets characteristic ID value.
		 *
		 * @return the value
		 */
		public String getValue() {
			return this.value;
		}

		/**
		 * Gets an ID for specified value.
		 *
		 * @param value
		 *            the value to get ID for
		 * @return ID if found, otherwise {@code null}
		 */
		public static Id forValue(final String value) {
			Id toReturn = null;

			for (final Id id : Id.values()) {
				if (id.getValue().equals(value)) {
					toReturn = id;
					break;
				}
			}
			return toReturn;
		}
	}
	
}
