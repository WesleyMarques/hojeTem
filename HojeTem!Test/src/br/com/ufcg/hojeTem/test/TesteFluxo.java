/**
 * 
 */
package br.com.ufcg.hojeTem.test;

import android.test.ActivityInstrumentationTestCase2;
import br.com.ufcg.hojeTem.ListViewActivity;
import br.com.ufcg.hojeTem.ListViewMyEventsActivity;
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
		setActivityInitialTouchMode(true);
	}
	
	@Override
	protected void tearDown() throws Exception {
		this.soloMonitor.finishOpenedActivities();
	}
	
	
	public void testASideBar(){
		soloMonitor.assertCurrentActivity("Activity wrong", LoginActivity.class);
		soloMonitor.clickOnText("Facebook");
		if (soloMonitor.waitForActivity(MapActivity.class)) {
			soloMonitor.assertCurrentActivity("Activity wrong", MapActivity.class);
			soloMonitor.clickOnActionBarHomeButton();
			soloMonitor.searchText("Meus Eventos");
			soloMonitor.searchText("Pesquisar");
			soloMonitor.searchText("Filtrar");
			soloMonitor.searchText("Visualizar");
		}else{
			fail();
		}
		
		soloMonitor.clickOnText("Meus Eventos");
		soloMonitor.waitForActivity(ListViewMyEventsActivity.class);
		soloMonitor.goBack();
		soloMonitor.clickOnActionBarHomeButton();
		soloMonitor.clickOnText("Visualizar");
		soloMonitor.waitForActivity(ListViewActivity.class);
		soloMonitor.goBack();
			
	}
	
	public void testMeusEventos(){
		soloMonitor.assertCurrentActivity("Activity wrong", LoginActivity.class);
		if (soloMonitor.waitForActivity(MapActivity.class)) {
			soloMonitor.clickOnText("Meus Eventos");
			soloMonitor.waitForActivity(ListViewMyEventsActivity.class);
		}
	}
	
	public void testVisualizar(){
		soloMonitor.assertCurrentActivity("Activity wrong", LoginActivity.class);
		if (soloMonitor.waitForActivity(MapActivity.class)) {
			soloMonitor.clickOnText("Pesquisar");
			soloMonitor.waitForActivity(ListViewMyEventsActivity.class);
		}

	}
	
	public void testPesquisarInput(){
		
		soloMonitor.assertCurrentActivity("Activity wrong", LoginActivity.class);
		if (soloMonitor.waitForActivity(MapActivity.class)) {
			soloMonitor.clickOnText("Pesquisar");
			soloMonitor.waitForActivity(ListViewMyEventsActivity.class);
		}
}

}
