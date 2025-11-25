package org.example.filter;

import java.util.Objects;

public class EndStringFilter implements Filter {
    private final String pattern;

    public EndStringFilter(String pattern) {
        if (pattern == null || pattern.isEmpty()) {
            throw new IllegalArgumentException("Подстрока не может быть null или пустой");
        }
        this.pattern = pattern;
    }

    @Override
    public boolean apply(String str) {
        if (str == null){
            return false;
        }
        return str.endsWith(pattern);
    }

    public String getPattern() {
        return pattern;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EndStringFilter that = (EndStringFilter) o;
        return Objects.equals(pattern, that.pattern);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(pattern);
    }

    @Override
    public String toString() {
        return "EndStringFilter{" +
                "pattern='" + pattern + '\'' +
                '}';
    }
}
