package hva.core;
import java.util.*;

public class DeciduousStrategy implements SeasonalStrategy {
    private Map<Season, String> cycleMap = Map.of(
        Season.WINTER, "SEMFOLHAS",
        Season.SPRING, "GERARFOLHAS",
        Season.SUMMER, "COMFOLHAS",
        Season.FALL, "LARGARFOLHAS"
    );

    private Map<String, Integer> effortMap = Map.of(
        "SEMFOLHAS", 0,
        "GERARFOLHAS", 1,
        "COMFOLHAS", 2,
        "LARGARFOLHAS", 5
    );

    @Override
    public String getTreeCycle(Season season) {
        return cycleMap.getOrDefault(season, "Unknown season");
    }

    @Override
    public int getSeasonalEffort(Season season) {
        return effortMap.getOrDefault(getTreeCycle(season), 0);
    }
}
