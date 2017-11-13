package ru.itis.servlets;

/**
 * 05.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
public class LevelService {
    public static Integer getLvl(Long xp) {
        return (int) ((Math.sqrt(625 + 100 * xp) - 25) / 50);
    }

    public static Long getXpForLvl(Integer lvl) {
        return (long) (25 * lvl * (1 + lvl));
    }
}
