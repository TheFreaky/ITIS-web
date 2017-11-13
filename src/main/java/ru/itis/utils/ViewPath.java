package ru.itis.utils;

import java.io.File;

/**
 * 12.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
public class ViewPath {
    final private static String VIEWS_PATH = "WEB-INF" + File.separator + "views" + File.separator;

    final public static String WELCOME_PAGE = VIEWS_PATH + "welcome.jsp";
    final public static String TRAININGS_PAGE = VIEWS_PATH + "trainings.jsp";
    final public static String TRAINING_PAGE = VIEWS_PATH + "training.jsp";
    final public static String PROFILE_PAGE = VIEWS_PATH + "profile.jsp";
    final public static String SETTING_PAGE = VIEWS_PATH + "setting.jsp";
}
