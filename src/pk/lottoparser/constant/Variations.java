package pk.lottoparser.constant;

public abstract class Variations {
	
	public enum Id {
		_3z6("3z6"),
		_4z6("4z6"),
		_5z6("5z6"),
		_6z6("6z6"),
		_3z5("3z5"),
		_4z5("4z5"),
		_5z5("5z5"),		
		_5z42("5z42"),
		_6z49("6z49")
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
