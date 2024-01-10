package dev.sadghost.espresso.groups;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * A simple generic class representing a pair of objects.
 *
 * @since 1.0.0
 * @author SadGhost
 * @param <A> the type of the first object.
 * @param <B> the type of the second object.
 */
public class Pair<A, B> {
    @NotNull private A a;
    @NotNull private B b;

    /**
     * Constructs a new Pair object with the given objects.
     *
     * @param a the first object.
     * @param b the second object.
     */
    @Contract(pure = true)
    public Pair(final @NotNull A a,
                final @NotNull B b) {
        this.a = a;
        this.b = b;
    }

    /**
     * Retrieves the first object of the pair.
     *
     * @return the first object.
     */
    public @NotNull A getA() {
        return a;
    }

    /**
     * Sets the first object of the pair.
     *
     * @param a the new value for the first object.
     */
    public void setA(final @NotNull A a) {
        this.a = a;
    }

    /**
     * Retrieves the second object of the pair.
     *
     * @return the second object.
     */
    public @NotNull B getB() {
        return b;
    }

    /**
     * Sets the second object of the pair.
     *
     * @param b the new value for the second object.
     */
    public void setB(final @NotNull B b) {
        this.b = b;
    }

    @Override
    @Contract(value = "null -> false", pure = true)
    public boolean equals(final @Nullable Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Pair<?, ?> pair = (Pair<?, ?>) o;
        if (!Objects.equals(a, pair.a)) return false;
        return Objects.equals(b, pair.b);
    }

    @Override
    public int hashCode() {
        int result = a.hashCode();
        result = 31 * result + b.hashCode();
        return result;
    }

    @Override
    public @NotNull String toString() {
        return "Pair{" +
                "a=" + a +
                ", b=" + b +
                '}';
    }
}
