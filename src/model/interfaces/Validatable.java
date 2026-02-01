package  model.interfaces;

public interface Validatable<T> {

    // ABSTRACT METHOD
    boolean isValid();

    // DEFAULT METHOD
    default void validateOrThrow(RuntimeException ex) {
        if (!isValid()) {
            throw ex;
        }
    }

    // STATIC METHOD
    static void requireNonNull(Object obj, String message) {
        if (obj == null) {
            throw new IllegalArgumentException(message);
        }
    }
}