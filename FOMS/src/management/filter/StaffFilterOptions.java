package management.filter;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

/**
 * Enumeration representing the staff filter options: branch/role/gender/age.
 */
public enum StaffFilterOptions {
    BRANCH(1),
    ROLE(2),
    GENDER(3),
    AGE(4);

    private static final Map<Integer, StaffFilterOptions> MAP;

    private final int filterNum;

    static {
        Map<Integer, StaffFilterOptions> filterOptionsMap = Arrays.stream(values())
                .collect(toMap(option -> option.filterNum, e -> e));
        MAP = Collections.unmodifiableMap(filterOptionsMap);
    }

    /**
     * Constructs a StaffFilterOptions enum with the specified filter number.
     *
     * @param value The number associated with the filter option.
     */
    StaffFilterOptions(int value) {
        filterNum = value;
    }

    /**
     * Returns the StaffFilterOptions corresponding to the given option number.
     *
     * @param option The option number to get the corresponding StaffFilterOptions for.
     * @return The StaffFilterOptions corresponding to the given option number.
     */
    public static StaffFilterOptions of(final int option) {
        return MAP.get(option);
    }
}
