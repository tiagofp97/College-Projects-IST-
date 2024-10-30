package hva.core;

public interface SeasonalStrategy {
    String getTreeCycle(Season season);
    int getSeasonalEffort(Season season);
}

