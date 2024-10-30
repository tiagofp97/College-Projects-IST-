package hva.core;
import java.io.Serializable;
public abstract class BaseEntity implements Comparable<BaseEntity> ,Serializable{
    protected String _id;
    protected String _name;

    public BaseEntity(String id, String name) {
        _id = id;
        _name = name;
    }
    public BaseEntity(String id) {
        _id = id;
        _name = "Uknown";
    }


    public String getId() {
        return _id;
    }

    public String getName() {
        return _name;
    }

    @Override
    public int compareTo(BaseEntity other) {
        return this._id.compareToIgnoreCase(other._id); // Case-insensitive comparison
    }

}