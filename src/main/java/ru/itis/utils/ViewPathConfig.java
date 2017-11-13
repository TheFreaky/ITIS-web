package ru.itis.utils;

import java.io.File;

/**
 * 12.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
public class ViewPathConfig {
    final private static String VIEWS_PATH = "WEB-INF" + File.separator + "views" + File.separator;

    public final static String WELCOME_PAGE = VIEWS_PATH + "welcome.jsp";
    public final static String TRAININGS_PAGE = VIEWS_PATH + "trainings.jsp";
    public final static String TRAINING_PAGE = VIEWS_PATH + "training.jsp";
    public final static String PROFILE_PAGE = VIEWS_PATH + "profile.jsp";
    public final static String SETTING_PAGE = VIEWS_PATH + "setting.jsp";
}
