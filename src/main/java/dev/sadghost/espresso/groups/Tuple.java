package dev.sadghost.espresso.groups;

import org.jetbrains.annotations.NotNull;

public class Tuple<A, B, C> extends Pair<A, B> {
    @NotNull protected C c;

    public Tuple(final @NotNull A a,
                 final @NotNull B b,
                 final @NotNull C c) {
        super(a, b);
        this.c = c;
    }

    public @NotNull C getC() {
        return c;
    }

    public void setC(final @NotNull C c) {
        this.c = c;
    }

    @Override
    public @NotNull String toString() {
        return "Tuple{" +
                "a=" + super.a +
                ", b=" + super.b +
                ", c=" + c +
                '}';
    }
}
