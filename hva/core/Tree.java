package hva.core;

import java.util.*;

/**
 * Represents a tree in the hotel, encapsulating its properties and behaviors.
 * This is an abstract class that should be extended by specific tree types.
 */
public abstract class Tree extends BaseEntity {
    
    /** The hotel this tree belongs to */
    private Hotel _hotel;
    
    /** The number of seasons that have passed without aging the tree */
    private int _seasonsPassed = 0;
    
    /** The age of the tree */
    private int _age;
    
    /** The cleaning difficulty of the tree */
    private int _cleaningDifficulty;
    
    /** The strategy for seasonal behaviors */
    private SeasonalStrategy _seasonalStrategy;

    /**
     * Constructs a Tree instance with the specified attributes.
     *
     * @param hotel the hotel this tree belongs to
     * @param id the unique identifier for the tree
     * @param name the name of the tree
     * @param age the initial age of the tree
     * @param cleaningDifficulty the difficulty of cleaning the tree
     * @param seasonalStrategy the strategy for seasonal behaviors of the tree
     */
    public Tree(Hotel hotel, String id, String name, int age, int cleaningDifficulty, SeasonalStrategy seasonalStrategy) {
        super(id, name);
        _age = age;
        _cleaningDifficulty = cleaningDifficulty;
        _hotel = hotel;
        _seasonalStrategy = seasonalStrategy;
    }

    /**
     * Gets the current season from the hotel.
     *
     * @return the current Season
     */
    public Season getSeason() {
        return _hotel.getCurrentSeason();
    }

    /**
     * Gets the age of the tree.
     *
     * @return the age of the tree
     */
    public int getAge() {
        return _age;
    }

    /**
     * Gets the cleaning difficulty of the tree.
     *
     * @return the cleaning difficulty
     */
    public int getDifficulty() {
        return _cleaningDifficulty;
    }

    /**
     * Increases the age of the tree based on the seasons passed.
     * The age increases every four seasons.
     */
    public void increaseAge() {
        _seasonsPassed++;
        if (_seasonsPassed == 4) {
            _age++;
            _seasonsPassed = 0;
        }
    }

    /**
     * Gets the number of seasons that have passed without aging the tree.
     *
     * @return the seasons passed
     */
    public int getSeasonsPassed() {
        return _seasonsPassed;
    }

    /**
     * Computes the cleaning effort based on the tree's cleaning difficulty,
     * the current season's effort, and the age of the tree.
     *
     * @return the calculated cleaning effort
     */
    public double computeCleaningEffort() {
        return _cleaningDifficulty * _seasonalStrategy.getSeasonalEffort(_hotel.getCurrentSeason()) * Math.log(_age + 1);
    }

    /**
     * Gets the tree's growth cycle for the specified season.
     *
     * @param season the season to get the growth cycle for
     * @return the tree cycle description for the given season
     */
    public String getTreeCycle(Season season) {
        return _seasonalStrategy.getTreeCycle(season);
    }

    /**
     * Provides a string representation of the tree.
     *
     * @return a string representation of the tree
     */
    @Override
    public abstract String toString();
}
