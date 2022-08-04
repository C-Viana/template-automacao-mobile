package steps;

import org.junit.Assert;

import io.cucumber.java.en.Then;
import pages.CtaAppDragAndDropPage;
import reporter.ReportManager;

public class CtaAppDragAndDropSteps {
	
	private CtaAppDragAndDropPage page;
	
	public CtaAppDragAndDropSteps() {
		page = new CtaAppDragAndDropPage();
	}
	
	@Then("realizo as alterações de posicionamento dos elementos em tela")
	public void realizoAsAlteracoesDePosicionamentoDosElementosEmTela() {
    	ReportManager.setStepName(new Object(){}.getClass().getEnclosingMethod().getAnnotations()[0].toString());
		page.aguardarTelaCaregar();
		Assert.assertEquals( 6, page.getListTextos().size() );
		ReportManager.setTestStep("Tela inicial carregada");
		
		int elA = 0;
		int elB = 5;
		
		page.arrastarElementoAParaElementoB( page.getListTextos().get(elA), page.getListTextos().get(elB));
		ReportManager.setTestStep("Alterados os textos " + page.getListTextos().get(elA) + "" + page.getListTextos().get(elB));
		
		elA = 4;
		elB = 2;
		
		page.arrastarElementoAParaElementoB( page.getListTextos().get(elA), page.getListTextos().get(elB));
		ReportManager.setTestStep("Alterados os textos " + page.getListTextos().get(elA) + "" + page.getListTextos().get(elB));
	}
	
}
