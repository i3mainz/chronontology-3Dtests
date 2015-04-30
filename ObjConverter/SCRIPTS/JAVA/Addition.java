package objconverter;

/**
 * Addition Class
 *
 * @author Florian Thiery
 */
public class Addition {

	private static int[] values = {2, 123, 244, 355, 466};

	public static void main(String[] args) {
		int value = 722;
		int match = 244;
		System.out.println(match(value, match));
	}

	public static boolean match(int value, int match) {
		// single value
		if (value == values[0] && match == values[0]) {
			return true;
		} else if (value == values[1] && match == values[1]) {
			return true;
		} else if (value == values[2] && match == values[2]) {
			return true;
		} else if (value == values[3] && match == values[3]) {
			return true;
		} else if (value == values[4] && match == values[4]) {
			return true;
		}
		// double value
		if (value == (values[0] + values[1]) && (match == values[0] || match == values[1])) {
			return true;
		} else if (value == (values[0] + values[2]) && (match == values[0] || match == values[2])) {
			return true;
		} else if (value == (values[0] + values[3]) && (match == values[0] || match == values[3])) {
			return true;
		} else if (value == (values[0] + values[4]) && (match == values[0] || match == values[4])) {
			return true;
		} else if (value == (values[1] + values[2]) && (match == values[1] || match == values[2])) {
			return true;
		} else if (value == (values[1] + values[3]) && (match == values[1] || match == values[3])) {
			return true;
		} else if (value == (values[1] + values[4]) && (match == values[1] || match == values[4])) {
			return true;
		} else if (value == (values[2] + values[3]) && (match == values[2] || match == values[3])) {
			return true;
		} else if (value == (values[2] + values[4]) && (match == values[2] || match == values[4])) {
			return true;
		} else if (value == (values[3] + values[4]) && (match == values[3] || match == values[4])) {
			return true;
		}
		// triple value
		if (value == (values[0] + values[1] + values[2])
				&& (match == values[0] || match == values[1] || match == values[2])) {
			return true;
		} else if (value == (values[0] + values[2] + values[3])
				&& (match == values[0] || match == values[2] || match == values[3])) {
			return true;
		} else if (value == (values[0] + values[3] + values[4])
				&& (match == values[0] || match == values[3] || match == values[4])) {
			return true;
		} else if (value == (values[1] + values[2] + values[3])
				&& (match == values[1] || match == values[2] || match == values[3])) {
			return true;
		} else if (value == (values[1] + values[3] + values[4])
				&& (match == values[1] || match == values[3] || match == values[4])) {
			return true;
		} else if (value == (values[4] + values[3] + values[2])
				&& (match == values[4] || match == values[3] || match == values[2])) {
			return true;
		} else if (value == (values[3] + values[1] + values[0])
				&& (match == values[3] || match == values[1] || match == values[0])) {
			return true;
		} else if (value == (values[4] + values[1] + values[0])
				&& (match == values[4] || match == values[1] || match == values[0])) {
			return true;
		} else if (value == (values[4] + values[2] + values[1])
				&& (match == values[4] || match == values[2] || match == values[1])) {
			return true;
		}
		// quadruple value
		if (value == (values[1] + values[2] + values[3] + values[4])
				&& (match == values[1] || match == values[2] || match == values[3] || match == values[4])) {
			return true;
		} else if (value == (values[0] + values[2] + values[3] + values[4])
				&& (match == values[0] || match == values[2] || match == values[3] || match == values[4])) {
			return true;
		} else if (value == (values[0] + values[1] + values[3] + values[4])
				&& (match == values[0] || match == values[1] || match == values[3] || match == values[4])) {
			return true;
		} else if (value == (values[0] + values[1] + values[2] + values[4])
				&& (match == values[0] || match == values[1] || match == values[2] || match == values[4])) {
			return true;
		} else if (value == (values[0] + values[1] + values[2] + values[3])
				&& (match == values[0] || match == values[1] || match == values[2] || match == values[3])) {
			return true;
		}
		// quintuple value
		if (value == (values[0] + values[1] + values[2] + values[3] + values[4]) && (match == values[0] || match == values[1] || match == values[2] || match == values[3] || match == values[4])) {
			return true;
		}
		// no match
		return false;
	}

}
