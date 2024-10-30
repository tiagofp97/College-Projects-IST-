package hva.core;


public class DeciduousTree extends Tree {
    public DeciduousTree(Hotel hotel, String id,String name, int age, int cleaningDifficulty) {
        super(hotel, id,name, age, cleaningDifficulty, new DeciduousStrategy());
    }

    @Override
    public String toString() {
        return String.format("ÁRVORE|%s|%s|%d|%d|%s|%s", getId(),getName(), getAge(), getDifficulty(), "CADUCA", getTreeCycle(getSeason()));
    }
}