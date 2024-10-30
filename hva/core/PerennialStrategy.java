package hva.core;
import java.util.*;

public class PerennialStrategy implements SeasonalStrategy {
    private Map<Season, String> cycleMap = Map.of(
        Season.WINTER, "LARGARFOLHAS",
        Season.SPRING, "GERARFOLHAS",
        Season.SUMMER, "COMFOLHAS",
        Season.FALL, "COMFOLHAS"
    );

    private Map<String, Integer> effortMap = Map.of(
        "LARGARFOLHAS", 2,
        "GERARFOLHAS", 1,
        "COMFOLHAS", 1
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