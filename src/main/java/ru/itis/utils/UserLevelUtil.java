package ru.itis.utils;

/**
 * 05.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
public class UserLevelUtil {
    public static int getLvl(Long xp) {
        return (int) ((Math.sqrt(625 + 100 * xp) - 25) / 50);
    }

    public static int getXpForLvl(Integer lvl) {
        return 25 * lvl * (1 + lvl);
    }
}
