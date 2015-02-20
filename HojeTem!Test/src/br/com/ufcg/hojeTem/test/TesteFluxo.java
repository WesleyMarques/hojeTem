/**
 * 
 */
package br.com.ufcg.hojeTem.test;

import android.test.ActivityInstrumentationTestCase2;
import br.com.ufcg.hojeTem.LoginActivity;
import br.com.ufcg.hojeTem.MapActivity;
import br.com.ufcg.hojeTem.R;

import com.robotium.solo.Solo;

/**
 * @author Wesley
 *
 */
public class TesteFluxo extends ActivityInstrumentationTestCase2<LoginActivity> {
	
	private Solo soloMonitor;

	public TesteFluxo() {
		super(LoginActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		this.soloMonitor = new Solo(getInstrumentation(), getActivity());
	}
	
	@Override
	protected void tearDown() throws Exception {
		this.soloMonitor.finishOpenedActivities();
	}
	
	public void testInit(){
		soloMonitor.assertCurrentActivity("Activity wrong", LoginActivity.class);
		soloMonitor.waitForActivity(MapActivity.class);
		soloMonitor.assertCurrentActivity("Activity wrong", MapActivity.class);
		soloMonitor.clickOnActionBarHomeButton();
	}

}
