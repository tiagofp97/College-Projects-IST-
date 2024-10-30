package hva.core.employee;
import java.util.*;
import hva.core.Habitat;
import java.io.*;

public class ZooKeeperSatisfactionStrategy implements SatisfactionStrategy,Serializable {
    @Override
    public int calculateSatisfaction(Employee employee, EmployeeManager empManager) {
        ZooKeeper zookeeper = (ZooKeeper) employee;
        int sum = 0;
        List<Habitat> habitats = zookeeper.getResponsibilityList();
        
        for (Habitat habitat : habitats) {
            int n_zookeepers = empManager.findAllResponsibleEmployees(ZooKeeper.class, habitat);
            int work = habitat.getWork();
            sum += work / n_zookeepers;
        }
        return Math.round(300 - sum);
    }
}