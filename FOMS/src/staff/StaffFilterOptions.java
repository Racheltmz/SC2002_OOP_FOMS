package staff;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.toMap;

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

    StaffFilterOptions(int value) {
        filterNum = value;
    }
    public static StaffFilterOptions of(final int option) {
        return MAP.get(option);
    }
}
