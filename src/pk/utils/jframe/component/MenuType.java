package pk.utils.jframe.component;

public abstract class MenuType {

	public enum Id {
		ITEM("ITEM"),
		SEPARATOR("SEPARATOR"),
		SUBMENU("SUBMENU")
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
