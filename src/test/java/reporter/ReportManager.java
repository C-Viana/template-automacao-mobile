package reporter;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import org.openqa.selenium.Dimension;
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
	 * framework. Este método é utilizada para a criação das evidências de teste, na
	 * classe Reporter. <br>
	 * 
	 * @param featureName
	 */
	public static void setFeature(String featureName) {
		feature = featureName;
	}

	/**
	 * Armazena o objeto de cenário de teste em execução para utilização interna
	 * deste framework. Este método deve ser utilizada preferencialmente antes do
	 * teste iniciar, na classe _Hooks e no métodos com a tag @Before. <br>
	 * 
	 * @param cucumberScenario
	 */
	public static void setScenario(Scenario cucumberScenario) {
		scenario = cucumberScenario;
	}

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

    public static void startReport() {
        if( reporter != null ) return;
        reporter = new ExtentReports( ROOT_PATH + "/results.html", true );
    }

    public static void startTest( String testName ) {
        test = reporter.startTest(testName);
        images_path = ROOT_PATH + "/screenshots";
        test.setStartedTime(Date.from(Instant.now()));
    }

    public static void endTest() {
        test.setEndedTime(Date.from(Instant.now()));
        reporter.endTest(test);
        test = null;
        if (!StaticResources.suite_test)
            iterator = 0;
    }

    public static void endReport() {
        reporter.flush();
        reporter.close();
        reporter = null;
    }

    /**
     * Adiciona ao arquivo de reporte/resultados um comentário informativo passado como parâmetro e uma imagem (screenshot) da tela do dispositivo como evidência.
     * <br/>
     * O parâmetro io.cucumber.java.Status definirá a situação da execução no momento em que o screenshot foi realizado.
     * 
     * @param status
     * @param stepLog
     */
    public static void setTestStep( Status status, String stepLog ) {
        LogStatus stat = ( LogStatus.valueOf(status.name().replace("ED", "")) != null ) ? LogStatus.valueOf(status.name().replace("ED", "")) : LogStatus.valueOf("error");
        test.log( stat, stepLog, test.addScreenCapture(getShot()) );
    }

    /**
     * Adiciona ao arquivo de reporte/resultados um comentário informativo passado como parâmetro e uma imagem (screenshot) da tela do dispositivo como evidência.
     * @param stepLog
     */
    public static void setTestInfoStep( String stepLog ) {
        test.log( LogStatus.INFO, stepLog, test.addScreenCapture(getShot()) );
    }
    
    /**
     * Adiciona ao arquivo de reporte/resultados um comentário informativo passado como parâmetro.
     * @param stepLog
     */
    public static void setTestLog(String stepLog) {
        test.log(LogStatus.INFO, stepLog);
    }

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

    public static String getFeatureName( String scenarioClass ) {
        return scenarioClass.split("features/")[1].split("[.]")[0];
    }

    private static String getShotJava() {
        File file = new File(images_path + "/step_" + (++iterator) + ".png");
        try {
            Dimension size = BasePage.getScreenDimensions();
            BufferedImage bi = new Robot().createScreenCapture( new Rectangle(0, 0, size.width, size.height) );
            ImageIO.write(bi, "png", file);
        } catch (AWTException | IOException e) {
            Logger.getLogger(ReportManager.class.getName()).log(Level.WARNING, "Erro ao realizar captura de tela na geração de evidência de teste", e);
        }
        return file.getPath();
    }

    public static void setTestStepOutContext(Status status, String stepLog) {
        LogStatus stat = (LogStatus.valueOf(status.name().replace("ED", "")) != null)
                ? LogStatus.valueOf(status.name().replace("ED", ""))
                : LogStatus.valueOf("error");
        test.log(stat, stepLog, test.addScreenCapture(getShotJava()));
    }

}
