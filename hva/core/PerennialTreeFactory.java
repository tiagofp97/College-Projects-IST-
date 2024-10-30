package hva.core;
import java.io.Serializable;
public class PerennialTreeFactory implements TreeFactory ,Serializable{
    @Override
    public Tree createTree(String id, String name,int age, int cleaningDifficulty, Hotel hotel) {
        return new PerennialTree(hotel, id,name, age, cleaningDifficulty);
    }
}
