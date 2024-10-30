package hva.core.employee;

import java.util.function.BiPredicate;
import hva.core.Habitat;
import java.io.*;

public class ZooKeeperCondition implements BiPredicate<Employee, Responsibility>,Serializable {
    @Override
    public boolean test(Employee emp, Responsibility resp) {
        // Ensure the Employee is a ZooKeeper and checks if they work in the specified Habitat
        if (emp instanceof ZooKeeper && resp instanceof Habitat) {
            return ((ZooKeeper) emp).worksInHabitat((Habitat) resp);
        }
        return false; // Return false if the types do not match
    }
}