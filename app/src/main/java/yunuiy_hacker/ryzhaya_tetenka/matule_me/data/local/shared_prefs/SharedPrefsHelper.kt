package yunuiy_hacker.ryzhaya_tetenka.matule_me.data.local.shared_prefs

import android.content.Context
import androidx.core.content.edit

class SharedPrefsHelper(context: Context) {

    private val app_data = context.getSharedPreferences(APP_DATA, Context.MODE_PRIVATE)
    private val personal_data = context.getSharedPreferences(PERSONAL_DATA, Context.MODE_PRIVATE)

    companion object {
        private const val APP_DATA = "app_data"
        private const val PERSONAL_DATA = "personal_data"

        //app data
        private const val ONBOARDING_IS_PASSED = "onboardingIsPassed"

        //personal data
        private const val USER_ID = "userId"
        private const val USER_NAME = "userName"
        private const val USER_SURNAME = "userSurname"

    }

    //app data
    var onboardingIsPassed
        set(value) {
            app_data.edit {
                putBoolean(ONBOARDING_IS_PASSED, value)
                apply()
            }
        }
        get() = app_data.getBoolean(ONBOARDING_IS_PASSED, false)

    //personal data
    var userId
        set(value) {
            personal_data.edit {
                if (value != 0) putInt(USER_ID, value)
                else remove(USER_ID)
            }
        }
        get() = personal_data.getInt(USER_ID, 0)

    var userName
        set(value) {
            personal_data.edit {
                if (value != null) putString(USER_NAME, value)
                else remove(USER_NAME)
            }
        }
        get() = personal_data.getString(USER_NAME, "")

    var userSurname
        set(value) {
            personal_data.edit {
                if (value != null) putString(USER_SURNAME, value)
                else remove(USER_SURNAME)
            }
        }
        get() = personal_data.getString(USER_SURNAME, "")
}