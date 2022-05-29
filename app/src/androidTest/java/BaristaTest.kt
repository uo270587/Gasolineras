import android.R
import android.util.Log
import com.adevinta.android.barista.assertion.BaristaListAssertions.assertDisplayedAtPosition
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.adevinta.android.barista.interaction.BaristaListInteractions.clickListItem
import com.adevinta.android.barista.interaction.BaristaListInteractions.clickListItemChild
import com.adevinta.android.barista.interaction.BaristaListInteractions.scrollListToPosition
import com.adevinta.android.barista.interaction.BaristaSpinnerInteractions.clickSpinnerItem
import com.adevinta.android.barista.rule.BaristaRule
import com.example.myapplication.MainActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestName


class BaristaTest {
    @get:Rule
    var baristaRule: BaristaRule<MainActivity> = BaristaRule.create(MainActivity::class.java)

    var name = TestName()

    @Before
    fun setUp() {
        //Log.i("Info", "[START] - Launch Test: " + name.methodName)
        baristaRule.launchActivity()
    }

    @After
    fun tearDown() {
        //Log.i("Info", "[FINISH] - Test: " + name.methodName)
    }

    @Test
    fun testBotonGuardarIsDisplayed() {
        assertDisplayed(com.example.myapplication.R.id.botonGuardar)
        Log.i("Info", "The element text is displayed.")
    }
    @Test
    fun testReciclerviewIsDisplayed() {
        assertDisplayed(com.example.myapplication.R.id.recyclerview)
        Log.i("Info", "The element text is displayed.")

    }
    @Test
    fun testMapaIsDisplayed() {
        assertDisplayed(com.example.myapplication.R.id.map)
        Log.i("Info", "The element text is displayed.")

    }

}