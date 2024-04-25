
package utils;

/**
 * Interface for Staff filter implementation.
 */
public interface IFilter {
    /**
     * Abstract method to implement in the classes that implement this interface.
     *
     * @param filterVal The value to filter by out of Branch/Age/Gender/Role.
     */
    void filter(String filterVal);
}
