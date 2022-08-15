package common;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.collect.ImmutableMap;

import driver.Driver;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.LoadLibs;

public class BasePage {

	private static Dimension deviceScreenDimensions;

	private BasePage() {
		throw new IllegalStateException("Erro para iniciar a classe General.");
	}

	/**
	 * Cria os diretórios informados através de um caminho definido em uma string.
	 * Se os diretórios já existirem, nenhuma ação será realizada. <br>
	 * Exemplo: <br>
	 * path = "D:/PDI/template-automacao-web/uploads" <br>
	 * 
	 * @param path
	 */
	public static void createFolders(String path) {
		try {
			File f = new File(path);
			if (!f.exists()) {
				f.mkdirs();
			}
		} catch (Exception e) {
			Logger.getLogger(BasePage.class.getName()).log(Level.WARNING,
					"Erro para utilizar createFolders(String path)", e);
			Thread.currentThread().interrupt();
		}
	}

	/**
	 * Utiliza a biblioteca Apache Commons para realizar um download de arquivo
	 * diretamente através de uma URL. <br>
	 * O arquivo baixado será armazenado na pasta /downloads, no diretório do
	 * próprio projeto. <br>
	 * Caso esta pasta não exista, ela será criada ao executar esta função. <br>
	 * 
	 * @param url
	 * @param fileName
	 */
	public static void downloadFile(String url, String fileName) {
		try {
			createFolders(StaticResources.DOWNLOADS_DIR);
			FileUtils.copyURLToFile(new URL(url), new File(StaticResources.DOWNLOADS_DIR + "/" + fileName));
		} catch (MalformedURLException e) {
			Logger.getLogger(BasePage.class.getName()).log(Level.WARNING,
					"Erro para utilizar downloadFile(String url, String fileName)", e);
			Thread.currentThread().interrupt();
		} catch (IOException e) {
			Logger.getLogger(BasePage.class.getName()).log(Level.WARNING,
					"Erro para utilizar downloadFile(String url, String fileName)", e);
			Thread.currentThread().interrupt();
		}
	}

	/**
	 * A partir do locator informado, retornará uma lista de todos os elementos
	 * encontrados que atendam à referência. <br>
	 * 
	 * @param locator
	 * @return java.util.List<WebElement>
	 */
	public static List<WebElement> getElements(By locator) {
		return Driver.get().findElements(locator);
	}

	public static WebElement getElement(By locator) {
		waitToBeVisible(locator, StaticResources.DEFAULT_WAIT_TIME);
		return Driver.get().findElement(locator);
	}

	public static WebElement getElementById(String id) {
		return getElement(By.xpath("//*[@resource-id='" + id + "']"));
	}

	public static WebElement getElementByText(String innerText) {
		return getElement(By.xpath("//*[@text='" + innerText + "']"));
	}

	public static WebElement getElementByContentDesc(String accessibilityId) {
		return getElement(By.xpath("//*[@content-desc='" + accessibilityId + "']"));
	}

	public static WebElement getElementByXpath(String xpath) {
		return getElement(By.xpath(xpath));
	}

	/**
	 * A partir do locator informado, retornará o texto do elemento encontrado que
	 * atenda à referência. <br>
	 * 
	 * @param locator
	 * @return String
	 */
	public static String getTextFromElement(By locator) {
		return Driver.get().findElement(locator).getText().trim();
	}

	/**
	 * Para interações web em que for necessário realizar o preenchimento de campos
	 * via Javascript, <br>
	 * informe neste método o elemento web (ex.: input) e o valor a ser inserido.
	 * <br>
	 * 
	 * @param element
	 * @param value
	 */
	public static void sendKeysByJavascript(WebElement element, String value) {
		try {
			((JavascriptExecutor) Driver.get()).executeScript("document[0].value = '" + value + "';", element);
		} catch (Exception e) {
			Logger.getLogger(BasePage.class.getName()).log(Level.WARNING,
					"Erro para utilizar sendKeysByJavascript(WebElement element, String value)", e);
			Thread.currentThread().interrupt();
		}
	}

	/**
	 * Para interações web em que for necessário realizar um clique em um componente
	 * via Javascript, <br>
	 * informe neste método o elemento web (ex.: button) a ser clicado. <br>
	 * 
	 * @param element
	 * @param value
	 */
	public static void clickByJavascript(WebElement element) {
		try {
			((JavascriptExecutor) Driver.get()).executeScript("document[0].click()", element);
		} catch (Exception e) {
			Logger.getLogger(BasePage.class.getName()).log(Level.WARNING,
					"Erro para utilizar clickByJavascript(WebElement element)", e);
			Thread.currentThread().interrupt();
		}
	}

	public static void dragAndDrop(Point originCoords, Point destinationCoords, int holdTimeInMillis, int dragTimeInMillis) {
		try {
			PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
			Sequence sequence = new Sequence(finger, 1);

			sequence.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(),
					originCoords.x, originCoords.y));
			sequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
			sequence.addAction(new Pause(finger, Duration.ofMillis(holdTimeInMillis)));
			sequence.addAction(finger.createPointerMove(Duration.ofMillis(dragTimeInMillis),
					PointerInput.Origin.viewport(), destinationCoords.x, destinationCoords.y));
			sequence.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
			sequence.addAction(new Pause(finger, Duration.ofMillis(2000)));

			Driver.get().perform(Collections.singletonList(sequence));
		} catch (Exception e) {
			Logger.getLogger(BasePage.class.getName()).log(Level.WARNING,
					"Erro para utilizar clickByCoordinates(int posX, int posY)", e);
			Thread.currentThread().interrupt();
		}
	}

	public static void dragAndDrop(Point originCoords, Point destinationCoords) {
		dragAndDrop(originCoords, destinationCoords, 1500, 2000);
	}

	public static void dragAndDrop(WebElement elementToMove, WebElement elementToDrop) {
		Point centerOrigin = getElementCenter(elementToMove);
		Point centerDestination = getElementCenter(elementToDrop);
		dragAndDrop(centerOrigin, centerDestination, 1500, 2000);
	}

	/**
	 * Através das coordenadas informadas, utiliza as funções W3C para mover o mouse
	 * ao ponto xy desta referência e, então, realizar um toque de tela nesta
	 * posição. <br>
	 * 
	 * Informe um tempo em milissegundos para especificar quanto tempo deve esperar
	 * entre as ações de toque e soltura (ex. para um clique longo, o tempo de
	 * espera deve ser maior). <br>
	 * 
	 * @param coordsX
	 * @param coordsY
	 * @param tapTimeMillis
	 */
	public static void tap(int coordsX, int coordsY, int tapTimeMillis) {
		try {
			PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
			Sequence sequence = new Sequence(finger, 1);

			sequence.addAction(
					finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), coordsX, coordsY));
			sequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
			sequence.addAction(new Pause(finger, Duration.ofMillis(tapTimeMillis)));
			sequence.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

			Driver.get().perform(Collections.singletonList(sequence));
		} catch (Exception e) {
			Logger.getLogger(BasePage.class.getName()).log(Level.WARNING,
					"Erro para utilizar clickByCoordinates(int posX, int posY)", e);
			Thread.currentThread().interrupt();
		}
	}

	/**
	 * Através das coordenadas informadas, utiliza as funções W3C para mover o mouse
	 * ao ponto xy desta referência e, então, realizar um toque de tela nesta
	 * posição. <br>
	 * 
	 * @param coordsX
	 * @param coordsY
	 */
	public static void tap(int coordsX, int coordsY) {
		tap(coordsX, coordsY, 100);
	}

	/**
	 * Realiza um toque na tela tomando como coordenadas xy o ponto central do
	 * WebElement parametrizado. <br>
	 * Informe um tempo em milissegundos para especificar quanto tempo deve esperar
	 * entre as ações de toque e soltura (ex. para um clique longo, o tempo de
	 * espera deve ser maior). <br>
	 * 
	 * @param element
	 * @param tapTimeMillis
	 */
	public static void tap(WebElement element, int tapTimeMillis) {
		Point center = getElementCenter(element);
		tap(center.x, center.y, tapTimeMillis);
	}

	/**
	 * Realiza um toque na tela tomando como coordenadas xy o ponto central do
	 * WebElement parametrizado. <br>
	 * 
	 * @param posX
	 * @param posY
	 */
	public static void tap(WebElement element) {
		Point center = getElementCenter(element);
		tap(center.x, center.y);
	}

	/**
	 * Através das coordenadas informadas, utiliza as funções W3C para mover o mouse
	 * ao ponto xy desta referência e, então, realizar dois toques rápidos
	 * equivalente a uma ação de clique duplo. <br>
	 * Informe um tempo em milissegundos para especificar quanto tempo deve esperar
	 * entre os toques/cliques (ex. para toques/cliques espalhados, o tempo de
	 * espera deve ser maior). <br>
	 * 
	 * @param element
	 */
	public static void doubleTap(int coordsX, int coordsY, int timeInMillis) {
		try {
			PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
			Sequence sequence = new Sequence(finger, 1);

			sequence.addAction(
					finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), coordsX, coordsY));

			sequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
			sequence.addAction(new Pause(finger, Duration.ofMillis(100)));
			sequence.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

			sequence.addAction(new Pause(finger, Duration.ofMillis(timeInMillis)));

			sequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
			sequence.addAction(new Pause(finger, Duration.ofMillis(100)));
			sequence.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

			Driver.get().perform(Collections.singletonList(sequence));
		} catch (Exception e) {
			Logger.getLogger(BasePage.class.getName()).log(Level.WARNING,
					"Erro para utilizar clickByCoordinates(int posX, int posY)", e);
			Thread.currentThread().interrupt();
		}
	}

	public static void doubleTap(int coordsX, int coordsY) {
		doubleTap(coordsX, coordsY, 100);
	}

	public static void doubleTap(WebElement element) {
		Point center = getElementCenter(element);
		doubleTap(center.x, center.y, 100);
	}

	public static void doubleTap(WebElement element, int timeInMillis) {
		Point center = getElementCenter(element);
		doubleTap(center.x, center.y, timeInMillis);
	}

	/**
	 * Para interações web em que for necessário mover a tela para visualizar um
	 * elemento, este método <br>
	 * realizará um scroll down/up para tentar colocar o elemento dentro da área do
	 * viewport. <br>
	 * Em algumas situações, pode ser necessário utilizar este recurso duas vezes.
	 * <br>
	 * 
	 * @param element
	 */
	public static void scrollIntoByJavascript(WebElement element) {
		try {
			((JavascriptExecutor) Driver.get()).executeScript("document.querySelector('a.sb_pagN').scrollIntoView();",
					element);
		} catch (Exception e) {
			Logger.getLogger(BasePage.class.getName()).log(Level.WARNING,
					"Erro para utilizar scrollIntoByJavascript(WebElement element)", e);
			Thread.currentThread().interrupt();
		}
	}

	/**
	 * Retorna os atributos HTML que estejam implementados na tag deste WebElement.
	 * <br>
	 * 
	 * @param element
	 * @return
	 */
	public static String getTagAttributesByJavaScript(WebElement element) {
		String test = null;
		try {
			test = ((JavascriptExecutor) Driver.get()).executeScript("return arguments[0].attributes;", element)
					.toString();
		} catch (Exception e) {
			Logger.getLogger(BasePage.class.getName()).log(Level.WARNING,
					"Erro para utilizar getTagAttributesByJavaScript(WebElement element)", e);
			Thread.currentThread().interrupt();
		}
		return test;
	}

	/**
	 * Atribui à variável General.deviceScreenDimensions as dimensões atuais das da
	 * tela do dispositivo. <br>
	 * 
	 * @param width
	 * @param height
	 */
	public static void setScreenDimensionsLocalVariable() {
		deviceScreenDimensions = Driver.get().manage().window().getSize();
	}

	/**
	 * Retorna um objeto Dimension com o atual tamanho da tela do dispositivo. <br>
	 * 
	 * @return
	 */
	public static Dimension getScreenDimensions() {
		return deviceScreenDimensions;
	}

	/**
	 * Realiza uma atualização na tela, semelhante ao comando F5 para a maioria dos
	 * navegadores. <br>
	 * 
	 */
	public static void refreshPage() {
		Driver.get().navigate().refresh();
	}

	/**
	 * Dado um WebElement, retorna um objeto Point com as coordenadas x e y que
	 * representam o ponto central deste elemento. <br>
	 * 
	 * @param element
	 * @return
	 */
	public static Point getElementCenter(WebElement element) {
		int width = element.getSize().width;
		int posX = element.getLocation().x;

		int height = element.getSize().height;
		int posY = element.getLocation().y;
		return new Point(posX + (width / 2), posY + (height / 2));
	}

	public static void getDownloadedFileNames() {
		try {
			List<String> internalMemoryPath = Arrays.asList("", "/storage/self/primary/Download/");
			Map<String, Object> jsonComm = ImmutableMap.of("command", "find", "args", internalMemoryPath);
			String searchResult = (String) ((AndroidDriver) Driver.get()).executeScript("mobile: shell", jsonComm);

			String[] filesNamesResult = searchResult.replace("/storage/self/primary/Download/", "").split("\n");
			for (String result : filesNamesResult) {
				System.out.println("ARQUIVO ENCONTRADO! -> " + result);
			}
//			internalMemoryPath = Arrays.asList("-f", "/storage/self/primary/Download/" + file_name);
//			jsonComm = ImmutableMap.of("command", "rm", "args", internalMemoryPath);
//			searchResult = (String) ((AndroidDriver)Driver.get()).executeScript("mobile: shell", jsonComm);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void getDownloadedFileFromInternalMemory(String file_name) {
		try {
			byte[] foundFileBytes = ((AndroidDriver) Driver.get())
					.pullFile("/storage/self/primary/Download/" + file_name);
			createFolders(StaticResources.DOWNLOADS_DIR);
			File fileToSave = new File(StaticResources.DOWNLOADS_DIR + "\\" + file_name);

			FileOutputStream out;

			out = new FileOutputStream(fileToSave);
			out.write(foundFileBytes);
			out.close();

			foundFileBytes = null;
			fileToSave = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void closeKeyboard() {
		((AndroidDriver) Driver.get()).hideKeyboard();
	}

	public static void expandNotifications() {
		((AndroidDriver) Driver.get()).openNotifications();
	}

	public static void androidBack() {
		((AndroidDriver) Driver.get()).pressKey(new io.appium.java_client.android.nativekey.KeyEvent(AndroidKey.BACK));
	}

	public static void androidHome() {
		((AndroidDriver) Driver.get()).pressKey(new io.appium.java_client.android.nativekey.KeyEvent(AndroidKey.HOME));
	}

	public static void androidTasksView() {
		((AndroidDriver) Driver.get())
				.pressKey(new io.appium.java_client.android.nativekey.KeyEvent(AndroidKey.APP_SWITCH));
	}

	/**
	 * Através da classe java.awt.Robot simula a utilização de uma tecla através das
	 * ações key press e key release respectivamente. <br>
	 * Como parâmetro, considere utilizar a java.awt.event.KeyEvent, que retornará
	 * um valor em Integer apropriado a este método. <br>
	 * 
	 * @param key
	 */
	public static void typeKeyboard(int key) {
		Robot robot;
		try {
			robot = new Robot();
			robot.keyPress(key);
			robot.keyRelease(key);
			robot = null;
		} catch (Exception e) {
			Logger.getLogger(BasePage.class.getName()).log(Level.WARNING, "Erro para utilizar typeKeyboard(int key)",
					e);
			Thread.currentThread().interrupt();
		}
	}

	/**
	 * Este método utiliza a classe Robot do Java para simular interações do teclado
	 * ao navegador. <br>
	 * Informe um caractere (char) com o valor que deseja simular a digitação. <br>
	 * 
	 * @param letter
	 */
	public static void typeKeyboard(char letter) {
		Robot robot;
		try {
			robot = new Robot();
			robot.keyPress(KeyEvent.getExtendedKeyCodeForChar(letter));
			robot.keyRelease(KeyEvent.getExtendedKeyCodeForChar(letter));
		} catch (AWTException e) {
			Logger.getLogger(BasePage.class.getName()).log(Level.WARNING,
					"Erro para utilizar typeKeyboard(char letter)", e);
			Thread.currentThread().interrupt();
		}
	}

	/**
	 * Este método utiliza a classe Robot do Java para simular interações do teclado
	 * ao navegador. <br>
	 * Informe uma String com o valor que deseja simular a digitação. <br>
	 * 
	 * @param word
	 */
	public static void typeKeyboard(String word) {
		for (int i = 0; i < word.length(); i++) {
			typeKeyboard(KeyEvent.getExtendedKeyCodeForChar(word.charAt(i)));
		}
	}

	/**
	 * Este método utiliza a classe Robot do Java para simular interações do teclado
	 * ao navegador. <br>
	 * Realiza a digitação da tecla TAB.
	 * 
	 */
	public static void typeKeyboardTAB() {
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
			robot = null;
		} catch (AWTException e) {
			Logger.getLogger(BasePage.class.getName()).log(Level.WARNING, "Erro para utilizar typeKeyboardTAB()", e);
			Thread.currentThread().interrupt();
		}
	}

	/**
	 * Este método utiliza a classe Robot do Java para simular interações do teclado
	 * ao navegador. <br>
	 * Realiza a digitação da tecla ENTER.
	 * 
	 */
	public static void typeKeyboardENTER() {
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			robot = null;
		} catch (Exception e) {
			Logger.getLogger(BasePage.class.getName()).log(Level.WARNING, "Erro para utilizar typeKeyboardENTER()", e);
			Thread.currentThread().interrupt();
		}
	}

	/**
	 * Informe um tempo em segundos para que a aplicação espere de maneira
	 * incondicional para executar a próxima ação. Caso o parâmetro de tempo seja
	 * nulo, será utilizado o valor DEFAULT_WAIT_TIME
	 */
	public static void waitFor(Integer timeInSeconds) {
		try {
			TimeUnit.SECONDS.sleep( (timeInSeconds == null) ? StaticResources.DEFAULT_WAIT_TIME : timeInSeconds );
//			Thread.sleep((timeInSeconds == null) ? StaticResources.DEFAULT_WAIT_TIME : timeInSeconds * 1000);
		} catch (InterruptedException e) {
			Logger.getLogger(BasePage.class.getName()).log(Level.WARNING, "Erro para utilizar Thread.sleep()", e);
			Thread.currentThread().interrupt();
		}
	}

	/**
	 * Especifique um WebElement e um tempo em segundos para aguardar até que este
	 * elemento esteja disponível em tela para ser encontrado pelo Selenium. Caso o
	 * elemento esteja disponível antes do tempo especificado, o teste prosseguirá
	 * antes do tempo máximo especificado. Caso o parâmetro de tempo seja nulo, será
	 * utilizado o valor DEFAULT_WAIT_TIME <br>
	 * 
	 * @param element
	 * @param timeInSeconds
	 */
	public static void waitToBeVisible(WebElement element, Integer timeInSeconds) {
		new WebDriverWait(Driver.get(),
				Duration.ofSeconds((timeInSeconds == null) ? StaticResources.DEFAULT_WAIT_TIME : timeInSeconds))
				.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * Especifique um locator (By:xpath, By:css, etc) e um tempo em segundos para
	 * aguardar até que este elemento esteja disponível em tela para ser encontrado
	 * pelo Selenium. Caso o elemento esteja disponível antes do tempo especificado,
	 * o teste prosseguirá antes do tempo máximo especificado. Caso o parâmetro de
	 * tempo seja nulo, será utilizado o valor DEFAULT_WAIT_TIME <br>
	 * 
	 * @param locator
	 * @param timeInSeconds
	 */
	public static void waitToBeVisible(By locator, Integer timeInSeconds) {
		waitToBeVisible(Driver.get().findElement(locator), timeInSeconds);
	}

	/**
	 * Informe um WebElemento e um tempo em segundos para aguardar até que este
	 * elemento esteja disponível em tela para ser encontrado pelo Selenium. <br>
	 * Este método realizará a espera ignorando as exceções
	 * StaleElementReferenceException, NoSuchElementException e NotFoundException.
	 * <br>
	 * 
	 * @param element
	 * @param timeInSeconds
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object waitToBeVisibleIgnoringExceptions(WebElement element, Integer timeInSeconds) {
		List<Class> exceptions = new ArrayList<Class>();
		exceptions.add(StaleElementReferenceException.class);
		exceptions.add(NoSuchElementException.class);
		exceptions.add(NotFoundException.class);

		try {
			return new FluentWait(Driver.get())
					.withTimeout(Duration
							.ofSeconds((timeInSeconds == null) ? StaticResources.DEFAULT_WAIT_TIME : timeInSeconds))
					.pollingEvery(Duration.ofSeconds(1)).ignoreAll(exceptions)
					.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			return null;
		}
	}

	public static void waitUntilNotVisible(WebElement element, Integer timeInSeconds) {
		new WebDriverWait(Driver.get(),
				Duration.ofSeconds((timeInSeconds == null) ? StaticResources.DEFAULT_WAIT_TIME : timeInSeconds))
				.until(ExpectedConditions.invisibilityOf(element));
	}

	public static void waitUntilNotVisible(String innerText, Integer timeInSeconds) {
		new WebDriverWait(Driver.get(),
				Duration.ofSeconds((timeInSeconds == null) ? StaticResources.DEFAULT_WAIT_TIME : timeInSeconds))
				.until(ExpectedConditions.invisibilityOf(getElementByText(innerText)));
	}

	public static void waitUntilNotVisible(By locator, Integer timeInSeconds) {
		waitUntilNotVisible(Driver.get().findElement(locator), timeInSeconds);
	}

	/**
	 * Retorna true se ao menos uma ocorrência do WebElement que possua o texto
	 * informado estiver visível na área do viewport. <br>
	 * Caso contrário, retornará false. <br>
	 * Note que mesmo que o elemento exista em tela, caso esteja além das dimensões
	 * do viewport (área visível da tela), o retorno será false. <br>
	 * 
	 * @param element
	 * @return
	 */
	public static boolean elementExists(By locator) {
		try {
			return Driver.get().findElements(locator).size() > 0;
		} catch (StaleElementReferenceException | NoSuchElementException ex) {
			return false;
		}
	}

	public static boolean elementExistsByText(String innerText) {
		try {
			return Driver.get().findElements(By.xpath("//*[@text='" + innerText + "']")).size() > 0;
		} catch (StaleElementReferenceException | NoSuchElementException ex) {
			return false;
		}
	}

	public static boolean elementExistsByContentDesc(String accessibilityId) {
		try {
			return Driver.get().findElements(By.xpath("//*[@content-desc='" + accessibilityId + "']")).size() > 0;
		} catch (StaleElementReferenceException | NoSuchElementException ex) {
			return false;
		}
	}

	public static boolean elementExistsById(String id) {
		try {
			return Driver.get().findElements(By.xpath("//*[@resource-id='" + id + "']")).size() > 0;
		} catch (StaleElementReferenceException | NoSuchElementException ex) {
			return false;
		}
	}

	/**
	 * Retorna um objeto web Alert após a espera condicionada. Caso o tempo
	 * informado seja null, será considerado o tempo definido como padrão. <br>
	 * 
	 * @param timeInSeconds
	 * @return
	 */
	public static Alert getAlert(Integer timeInSeconds) {
		return new WebDriverWait(Driver.get(),
				Duration.ofSeconds((timeInSeconds == null) ? StaticResources.DEFAULT_WAIT_TIME : timeInSeconds))
				.until(ExpectedConditions.alertIsPresent());
	}

	/**
	 * Retorna o foco do app para o contexto principal; saindo de webviews que
	 * estejam presentes.
	 */
	public static void setDefaultContext() {
		Driver.get().switchTo().defaultContent();
	}

	/**
	 * Verifica e lista quais os contextos estão disponíveis. Caso haja a presentaça
	 * de alguma WebView, retorna true.
	 * 
	 * @return
	 */
	public static Set<String> getWebviewContexts() {
		Set<String> contextNames = ((AndroidDriver) Driver.get()).getContextHandles();
		System.out.println("LOG\t-\tFound contexts names:");
		contextNames.stream().forEach(context -> {
			System.out.println(context);
		});
		return contextNames;
	}

	/**
	 * Entra no contexto de uma WebView a partir do nome que a identifique. <br>
	 * 
	 * @param frame
	 */
	public static void setWebviewContext(String webviewName) {
		((AndroidDriver) Driver.get()).context(webviewName);
	}

	/**
	 * Através de recursos da classe java.net, retorna o status code ao tentar
	 * realizar uma conexão com a URL informada. <br>
	 * É realizado um método GET sobre a URL e o status code é retornado como
	 * inteiro. <br>
	 * Considere utilizar este método para validar o acesso a uma página antes de
	 * abri-la ou para validar a integridade de recursos da página de teste
	 * (imagens, arquivos de som, etc.). <br>
	 * 
	 * @param targetUrl
	 * @return
	 */
	public static int getStatusCodeFromURL(String targetUrl) {
		try {
			HttpURLConnection conn = (HttpURLConnection) new URL(targetUrl).openConnection();
			conn.setRequestMethod("GET");
			return conn.getResponseCode();
		} catch (MalformedURLException e) {
			Logger.getLogger(BasePage.class.getName()).log(Level.WARNING,
					"Erro para utilizar getStatusCodeFromURL(String targetUrl)", e);
			Thread.currentThread().interrupt();
			return 0;
		} catch (IOException e) {
			Logger.getLogger(BasePage.class.getName()).log(Level.WARNING,
					"Erro para utilizar getStatusCodeFromURL(String targetUrl)", e);
			Thread.currentThread().interrupt();
			return 0;
		}
	}

	/**
	 * Retorna um objeto java.awt.image.BufferedImage a partir de um WebElement em
	 * tela. <br>
	 * A imagem será capturada a partir das dimensões do objeto isolado. <br>
	 * 
	 * @param element
	 * @return
	 */
	public static BufferedImage getImageFromObject(WebElement element) {
		BufferedImage bi = new BufferedImage(element.getRect().width, element.getRect().height,
				BufferedImage.TYPE_INT_ARGB);
		try {
			bi = ImageIO.read(element.getScreenshotAs(OutputType.FILE));
			return bi;
		} catch (WebDriverException | IOException e) {
			Logger.getLogger(BasePage.class.getName()).log(Level.WARNING,
					"Erro para utilizar getImageFromObject(WebElement element)", e);
			Thread.currentThread().interrupt();
			return null;
		}
	}

	public static void getAndSaveImageFromObject(WebElement element, String pathToSave, String imageName) {
		try {
			createFolders(StaticResources.IMAGE_RESOURCES_DIR);
			ImageIO.write(getImageFromObject(element), "png", new File(pathToSave + "/" + imageName + ".png"));
		} catch (WebDriverException | IOException e) {
			Logger.getLogger(BasePage.class.getName()).log(Level.WARNING,
					"Erro para utilizar getAndSaveImageFromObject(WebElement element)", e);
			Thread.currentThread().interrupt();
		}
	}
	
	public static BufferedImage getImageFile(String refImgFilePath) {
		try {
			return ImageIO.read(new File(refImgFilePath));
		} catch (IOException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
			return null;
		}
	}

	public static double compareImages(BufferedImage img1, BufferedImage img2) throws Exception {
		int w1 = img1.getWidth();
		int w2 = img2.getWidth();
		int h1 = img1.getHeight();
		int h2 = img2.getHeight();
		
		if ((w1!=w2)||(h1!=h2)) {
			throw new Exception("Both images should have same dimensions\nCurrent image: "+w1+"x"+h1+"\nReference image: "+w2+"x"+h2);
		}
		else {
			long difference = 0;
			int pixel1, pixel2;
			Color color1, color2;
			
			for (int j = 0; j < h1; j++) {
				for (int i = 0; i < w1; i++) {
					color1 = new Color(img1.getRGB(i, j), false);
					color2 = new Color(img2.getRGB(i, j), false);
					
					pixel1 = color1.getRed()+color1.getGreen()+color1.getBlue();
					pixel2 = color2.getRed()+color2.getGreen()+color2.getBlue();
					
					difference += (pixel1 == pixel2) ? 0 : 1;
				}
			}
			double avg = difference/(double)(w1*h1);
			
			return avg*100;
		}
	}

	public static String get_64Base_fromScreenElement(WebElement element, String imageName) {
		String encoded = null;
		File screenShot = ((TakesScreenshot) Driver.get()).getScreenshotAs(OutputType.FILE);
		try {
			createFolders(StaticResources.IMAGE_SCREENSHOTS);

			BufferedImage imageScreen = ImageIO.read(screenShot);
			BufferedImage cutScreen = imageScreen.getSubimage(element.getLocation().x, element.getLocation().y,
					element.getSize().width, element.getSize().height);
			ImageIO.write(cutScreen, "png", new File(StaticResources.IMAGE_SCREENSHOTS + "\\" + imageName + ".png"));

			screenShot = new File(StaticResources.IMAGE_SCREENSHOTS + "\\" + imageName + ".png");
			encoded = Base64.getEncoder().encodeToString(Files.readAllBytes(screenShot.toPath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return encoded;
	}

	public static String get_64Base_fromScreenElement(WebElement element) {
		return get_64Base_fromScreenElement(element, "testComparisonImage");
	}

	public static String get_64Base_fromLocalImage(String image) {
		String codeBase64 = null;
		try {
			File file = new File(StaticResources.IMAGE_RESOURCES_DIR + "\\" + image + ".png");
			codeBase64 = Base64.getEncoder().encodeToString(Files.readAllBytes(file.toPath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return codeBase64;
	}

	/**
	 * Utiliza a classe net.sourceforge.tess4j.Tesseract para identificar valores de
	 * texto em arquivos de imagens. <br>
	 * Considere que a imagem não precisa ser um arquivo armazenado localmente. <br>
	 * Uma vez que este método espera um objeto java.awt.image.BufferedImage, basta
	 * capturar em um objeto Java a imagem do WebElement. <br>
	 * 
	 * @param img
	 * @return
	 */
	public static String getTextFromImage(BufferedImage img) {
		Tesseract tesseract = new Tesseract();
		try {
			// tesseract.setLanguage("por");
			tesseract.setDatapath(LoadLibs.extractTessResources("tessdata").getAbsolutePath());
			return tesseract.doOCR(img);
		} catch (TesseractException e) {
			Logger.getLogger(BasePage.class.getName()).log(Level.WARNING,
					"Erro para utilizar getTextFromImage(BufferedImage img)", e);
			Thread.currentThread().interrupt();
			return null;
		}
	}

	public static void swipe(Point originTap, Point destinationTap, int swipesCount) {
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence sequence = new Sequence(finger, 1);

		sequence.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), originTap.x,
				originTap.y));
		sequence.addAction(finger.createPointerDown(PointerInput.MouseButton.MIDDLE.asArg()));
		sequence.addAction(new Pause(finger, Duration.ofMillis(2000)));
		sequence.addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(),
				destinationTap.x, destinationTap.y));
		sequence.addAction(finger.createPointerUp(PointerInput.MouseButton.MIDDLE.asArg()));
		sequence.addAction(new Pause(finger, Duration.ofMillis(1000)));

		for (int i = 0; i < swipesCount; i++) {
			Driver.get().perform(Collections.singletonList(sequence));
		}
		finger = null;
		sequence = null;
	}

	public static void swipeVertically(double from, double to) {
		swipe(new Point(deviceScreenDimensions.width / 2, (int) (deviceScreenDimensions.height * from)),
				new Point(deviceScreenDimensions.width / 2, (int) (deviceScreenDimensions.height * to)), 1);
	}

	public static void swipeVertically(int fromY, int toY, int axis) {
		swipe(new Point(axis, fromY), new Point(axis, toY), 1);
	}

	public static void swipeHorizontaly(double from, double to) {
		swipe(new Point((int) (deviceScreenDimensions.width * from), deviceScreenDimensions.height / 2),
				new Point((int) (deviceScreenDimensions.width * to), deviceScreenDimensions.height / 2), 1);
	}

	public static void swipeHorizontaly(int fromX, int toX, int axis) {
		swipe(new Point(fromX, axis), new Point(toX, axis), 1);
	}

	public static void swipeDown() {
		swipe(new Point(deviceScreenDimensions.width / 2, deviceScreenDimensions.height - 150),
				new Point(deviceScreenDimensions.width / 2, 50), 1);
	}

	public static void swipeDown(int swipeCounts) {
		swipe(new Point(deviceScreenDimensions.width / 2, deviceScreenDimensions.height - 150),
				new Point(deviceScreenDimensions.width / 2, 50), swipeCounts);
	}

	public static void swipeUp() {
		swipe(new Point(getScreenDimensions().width / 2, 80),
				new Point(deviceScreenDimensions.width / 2, deviceScreenDimensions.height - 150), 1);
	}

	public static void swipeUp(int swipeCounts) {
		swipe(new Point(deviceScreenDimensions.width / 2, 80),
				new Point(deviceScreenDimensions.width / 2, deviceScreenDimensions.height - 150), swipeCounts);
	}

	public static void swipeLeft() {
		swipe(new Point(deviceScreenDimensions.width - 10, deviceScreenDimensions.height / 2),
				new Point(10, deviceScreenDimensions.height / 2), 1);
	}

	public static void swipeLeft(int swipeCounts) {
		swipe(new Point(deviceScreenDimensions.width - 10, deviceScreenDimensions.height / 2),
				new Point(10, deviceScreenDimensions.height / 2), swipeCounts);
	}

	public static void swipeRight() {
		swipe(new Point(10, deviceScreenDimensions.height / 2),
				new Point(deviceScreenDimensions.width - 10, deviceScreenDimensions.height / 2), 1);
	}

	public static void swipeRight(int swipeCounts) {
		swipe(new Point(10, deviceScreenDimensions.height / 2),
				new Point(deviceScreenDimensions.width - 10, deviceScreenDimensions.height / 2), swipeCounts);
	}

	public static void scrollToElement_byAttribute_andValue(String attribute, String value) {
		((AndroidDriver) Driver.get()).findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()."
				+ "scrollable(true)).scrollIntoView(new UiSelector()." + attribute + "(\"" + value + "\"))"));
	}

	public static void scrollToElement_byId(String id) {
		((AndroidDriver) Driver.get()).findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()."
				+ "scrollable(true)).scrollIntoView(new UiSelector().resourceId(\"" + id + "\"))"));
	}

	public static void scrollToElement_byText(String inner_text) {
		Driver.get().findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()."
				+ "scrollable(true)).scrollIntoView(new UiSelector().text(\"" + inner_text + "\"))"));
	}

}
