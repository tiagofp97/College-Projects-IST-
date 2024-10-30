package hva.core;

public class PerennialTree extends Tree {
    public PerennialTree(Hotel hotel, String id,String name, int age, int cleaningDifficulty) {
        super(hotel, id,name, age, cleaningDifficulty, new PerennialStrategy());
    }
    
    @Override
    public String toString() {
        return String.format("ÁRVORE|%s|%s|%d|%d|%s|%s", getId(),getName(), getAge(), getDifficulty(), "PERENE", getTreeCycle(getSeason()));
    }
}