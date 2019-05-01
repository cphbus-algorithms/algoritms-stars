package dk.cphbusiness.algorithms.utilities;

@FunctionalInterface
public interface ComparableByAxis<T> {
    
    int compareToByAxis(int axis, T other);
    
    }
