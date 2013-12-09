package game.helpers;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class IntGenerator {

    private Set<Integer> set = new HashSet<>();
    private Random rand = new Random();
    private int range = 10000;

    public IntGenerator()
    {
        super();
    }

    public IntGenerator(int range)
    {
        super();
        this.range = range;
    }

    public Integer getNextRandomUniqueInt()
    {
        Integer i = null;

        boolean added = false;
        while (!added)
        {
            i = rand.nextInt(range);
            added = set.add(i);
        }

        return i;

    }
}
