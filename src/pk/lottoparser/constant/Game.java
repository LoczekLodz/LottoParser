package pk.lottoparser.constant;

public abstract class Game {

	public enum Id {
		MULTILOTEK("Multi Multi", 90),
		LOTEK("Duży Lotek", 49),
		MINI("Mini Lotek", 42);
		
		private final String value;
		private final Integer maxNumbers;

		/**
		 * Constructs an instance of game ID.
		 *
		 * @param value
		 *            the ID value to set
		 */
		private Id(final String value, final Integer maxNumbers) {
			this.value = value;
			this.maxNumbers = maxNumbers;
		}

		/**
		 * Gets characteristic ID value.
		 *
		 * @return the value
		 */
		public String getValue() {
			return this.value;
		}
		
		public Integer getMaxNumbers() {
			return this.maxNumbers;
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
