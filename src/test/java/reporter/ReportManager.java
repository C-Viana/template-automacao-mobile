package reporter;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import common.BasePage;
import common.StaticResources;
import driver.Driver;
import io.cucumber.java.Scenario;
import io.cucumber.java.Status;


public class ReportManager {
    
	private static String feature;
	private static Scenario scenario;
    private static ExtentReports reporter;
    private static ExtentTest test;
    private static int iterator = 0;
    private static String ROOT_PATH = "";
    private static String images_path = "";
    
	/**
	 * Retorna o nome da feature extraída do arquivo com a escrita Gherkin que
	 * estiver em execução. <br>
	 * 
	 * @return
	 */
	public static String getFeature() {
		return feature;
	}

	/**
	 * Retorna o objeto de cenário de teste em execução extraído do Cucumber. <br>
	 * 
	 * @return
	 */
	public static Scenario getScenario() {
		return scenario;
	}

	/**
	 * Retorna o resultado da execução do teste atual. <br>
	 * 
	 * @return
	 */
	public static Status getScenarioStatus() {
		return scenario.getStatus();
	}

	/**
	 * Define o valor da feature em execução para utilização interna deste
	 * framework. 
	 * <br>
	 * Este método é utilizada para a criação das evidências de teste, na
	 * classe Reporter. <br>
	 * 
	 * @param featureName
	 */
	public static void setFeature(String featureName) {
		feature = featureName;
	}

	/**
	 * Armazena o objeto de cenário de teste em execução para utilização interna
	 * deste framework. 
	 * <br>
	 * Este método deve ser utilizada preferencialmente antes do
	 * teste iniciar, na classe _Hooks e nos métodos com a tag @Before. <br>
	 * 
	 * @param cucumberScenario
	 */
	public static void setScenario(Scenario cucumberScenario) {
		scenario = cucumberScenario;
	}
	
	/**
     * Define o caminho para armazenamento dos arquivos de reporte. 
     * <br>
     * A depender do tipo de execução definida, os diretórios criados para armazenamento será diferente. 
     * <br>
     * Para testes individuais o caminho será C:/Test_Results/[DATA_dd-MM-yyyy]/[CucumberFileFeatureName]/[CucumberScenarioName]
     * <br>
     * Para suite de testes será C:/Test_Results/[DATA_dd-MM-yyyy]/[NomeDoProjeto]_Suite_Run_[HORA_HH-mm]
     * <br>
     * @param featureName
     * @param scenarioName
     */
    public static void setResultPath(String featureName, String scenarioName) {
        String date = new SimpleDateFormat("dd-MM-yyyy").format( Date.from(Instant.now()) );
        if(!StaticResources.suite_test) {
            ROOT_PATH = "C:/Test_Results/" + date + "/" + getFeatureName(featureName) + "/" + scenarioName.replaceAll("[\\/?\\*:<>]", "");
        }
        else
            if(reporter == null) {
            	String[] names = System.getProperty("user.dir").replace("\\", "_").split("_");
                ROOT_PATH = "C:/Test_Results/" + date + "/" + names[names.length-1] + "_Suite_Run_" + new SimpleDateFormat("HH-mm").format(Calendar.getInstance().getTime());
            }
    }
    
    /**
     * Iniciará a instância de reporte para o(s) teste(s) em execução.
     * <br>
     * Este método deve ser chamado logo após o método ReportManager.setResultPath().3
     * <br>
     */
    public static void startReport() {
        if( reporter != null ) return;
        reporter = new ExtentReports( ROOT_PATH + "/results.html", true );
    }
    
    /**
     * Cria uma instância de teste para compor o arquivo de reporte. 
     * <br>
     * Deve ser chamado logo após o método ReportManager.startReport().
     * <br>
     * @param testName
     */
    public static void startTest( String testName ) {
        test = reporter.startTest(testName);
        images_path = ROOT_PATH + "/screenshots";
        test.setStartedTime(Date.from(Instant.now()));
    }
    
    /**
     * Finaliza uma instância de teste para compor o arquivo de reporte.
     * <br>
     * Deve ser chamado após o último passo de execução do teste atual.
     * <br>
     */
    public static void endTest() {
        test.setEndedTime(Date.from(Instant.now()));
        reporter.endTest(test);
        test = null;
        if (!StaticResources.suite_test)
            iterator = 0;
    }
    
    /**
     * Finaliza o reporte com os resultados do(s) teste(s) executados.
     * <br>
     * Deve ser chamado após a finalização de todos os testes e após o método ReportManager.endTest().
     * <br>
     */
    public static void endReport() {
        reporter.flush();
        reporter.close();
        reporter = null;
    }

    /**
     * Faz uma imagem de captura de tela e adiciona o status atual do passo em execução informado através do recurso io.cucumber.java.Scenario.
     * <br>
     * Como parâmetro, pode ser informado um texto personalizado que sirva de explicação à imagem e às validações realizadas.
     * <br>
     * Este método pode ser utilizado em qualquer ponto da execução do teste que se queira registrar uma evidência de teste.
     * <br>
     * @param stepLog
     */
    public static void setTestStep( String stepLog ) {
        LogStatus stat = ( 
        		LogStatus.valueOf(getScenario().getStatus().name().replace("ED", "")) != null ) 
        		? LogStatus.valueOf(getScenario().getStatus().name().replace("ED", "")) 
        				: LogStatus.valueOf("error");
        test.log( stat, stepLog, test.addScreenCapture(getShot()) );
    }
    
    /**
     * Realiza um registro de execução de teste ao arquivo de reporte com objetivo unicamente informativo. Deste modo, nenhuma imagem será adicionada. 
     * <br>
     * O io.cucumber.java.Scenario neste método será sempre LogStatus.INFO e não deve ser utilizado com caráter validativo.
     * <br>
     * Como parâmetro, pode ser informado um texto personalizado que sirva de explicação à imagem e às validações realizadas.
     * <br>
     * @param stepLog
     */
    public static void setTestLog(String stepLog) {
        test.log(LogStatus.INFO, stepLog);
    }

    /**
     * Realiza um registro de execução de teste ao arquivo de reporte com captura de imagem e objetivo unicamente informativo.
     * <br>
     * O io.cucumber.java.Scenario neste método será sempre LogStatus.INFO e não deve ser utilizado com caráter validativo.
     * <br>
     * Como parâmetro, pode ser informado um texto personalizado que sirva de explicação à imagem e às validações realizadas.
     * <br>
     * @param stepLog
     */
    public static void setTestLogAndScreenshot( String stepLog ) {
        test.log( LogStatus.INFO, stepLog, test.addScreenCapture(getShot()) );
    }
    
    /**
     * Método que implementa a execução de uma captura de imagem (screenshot) através de recursos do Selenium.
     * <br>
     * É necessário que o método ReportManager.setResultPath() tenha sido chamado anterirmente para definir o local de armazenamento das imagens.
     * <br>
     * Não é recomendado utilizar este método de forma independente uma vez que este já é utilizado dentro dos métodos ReportManager.setTestStep(String).
     * <br>
     * @return
     */
    private static String getShot() {
        byte[] image = ((TakesScreenshot)Driver.get()).getScreenshotAs(OutputType.BYTES);
        File file = new File( images_path + "/step_" + (++iterator) + ".png" );
        BasePage.createFolders(images_path);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(image);
        }
        catch(Exception e) {
            Logger.getLogger(ReportManager.class.getName()).log(Level.WARNING, "Erro ao realizar captura de tela na geração de evidência de teste", e);
        }
        return file.getPath();
    }
    
    /**
     * Método para recuperar o nome da feature a partir do arquivo.
     * <br>
     * Para que este método funcione é necessário que os arquivos .feature estejam dentro de uma pasta com o nome "features".
     * <br>
     * @param scenarioClass
     * @return
     */
    public static String getFeatureName( String scenarioClass ) {
        return scenarioClass.split("features/")[1].split("[.]")[0];
    }
    
    
}
