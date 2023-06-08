package dev.sadghost.ghostingly.groups;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class Pair<A, B> {
    @NotNull private A a;
    @NotNull private B b;

    @Contract(pure = true)
    public Pair(final @NotNull A a, final @NotNull B b) {
        this.a = a;
        this.b = b;
    }

    public @NotNull A getA() {
        return a;
    }

    public void setA(final @NotNull A a) {
        this.a = a;
    }

    public @NotNull B getB() {
        return b;
    }

    public void setB(final @NotNull B b) {
        this.b = b;
    }

    @Override
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