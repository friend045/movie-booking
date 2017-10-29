package php.movie.booking;


import android.app.Activity;
import android.content.Intent;

class Utils {
    private static int sTheme;

    static int COLOR_1 = 0;
    static int COLOR_2 = 1;
    static int COLOR_3 = 2;
    static int COLOR_4 = 3;
    static int COLOR_5 = 4;
    static int COLOR_6 = 5;
    static int COLOR_7 = 6;
    static int COLOR_8 = 7;
    static int COLOR_9 = 8;

    /**
     * Set the theme of the Activity, and restart it by creating a new Activity of the same type.
     */
    static void changeToTheme(Activity activity, int theme) {
        sTheme = theme;
        activity.finish();

        activity.startActivity(new Intent(activity, activity.getClass()));

    }

    /**
     * Set the theme of the activity, according to the configuration.
     */
    static void onActivityCreateSetTheme(Activity activity) {
        switch (sTheme) {
            default:
            case 0:
                activity.setTheme(R.style.AppTheme);

                break;
            case 1:
                activity.setTheme(R.style.AppTheme2);
                break;
            case 2:
                activity.setTheme(R.style.AppTheme3);
                break;
            case 3:
                activity.setTheme(R.style.AppTheme4);
                break;
            case 4:
                activity.setTheme(R.style.AppTheme5);
                break;
            case 5:
                activity.setTheme(R.style.AppTheme6);
                break;
            case 6:
                activity.setTheme(R.style.AppTheme7);
                break;
            case 7:
                activity.setTheme(R.style.AppTheme8);
                break;
            case 8:
                activity.setTheme(R.style.AppTheme9);
                break;
        }
    }
}
