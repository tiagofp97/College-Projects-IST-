package hva.core;
import java.io.Serializable;
public enum Season implements Serializable {
    SPRING,    // 0 for Spring
    SUMMER,    // 1 for Summer
    FALL,      // 2 for Fall
    WINTER;    // 3 for Winter

    // Get the next season, cycling back to SPRING after WINTER
    public Season nextSeason() {
        return values()[(this.ordinal() + 1) % values().length];
    }
    
}
