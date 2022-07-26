package common;

public class StaticResources {
    
    private StaticResources() {
        throw new IllegalStateException("Erro para iniciar a classe General.");
    }

    /**
     * LINK DO SERVIDOR APPIUM PARA CONECTAR-SE AO DISPOSITIVO DE TESTE.
     */
    public static final String APPIUM_LOCAL_DRIVER = "http://127.0.0.1:4723/wd/hub/";

    /**
     * PROPRIEDADE PARA IDENTIFICAÇÃO DO CHROMEDRIVER PELO SISTEMA LOCAL.
     */
    public static final String CHROMEDRIVERPROPERTY = "webdriver.chrome.driver";

    /**
     * DIRETÓRIO NA MÁQUINA LOCAL ONDE ESTÁ O CHROMEDRIVER.
     */
    public static final String CHROME_DRIVER = "C:\\Desenvolvimento\\WebDrivers\\chromedriver_103.exe";
    
    /**
     * DIRETÓRIO ONDE ESTÁ O APK PARA SER INSTALADO AO INICIAR O TESTE.
     */
    public static final String APK_LOCAL_PATH = "D:\\Downloads\\Softwares\\Android Support\\APKs\\CTAppium_1_2.apk";

    /**
     * LOCAL DE DESTINO PARA TESTES DE DOWNLOADS DE ARQUIVOS.
     */
    public static final String DOWNLOADS_DIR = System.getProperty("user.dir") + "\\downloads";
    
    /**
     * DIRETÓRIO PARA ARMAZENAMENTO DE IMAGENS DE REFERÊNCIA.
     */
    public static final String IMAGE_RESOURCES_DIR = System.getProperty("user.dir") + "\\src\\test\\resources\\images";

    /**
     * DIRETÓRIO PARA SALVAR SCREENSHOTS COMO RECURSOS DE VALIDAÇÃO EM TESTES DE IMAGENS.
     */
    public static final String IMAGE_SCREENSHOTS = System.getProperty("user.dir") + "\\src\\test\\resources\\screenshots";
    
    /**
	 * Tempo padrão de espera para as funções wait implementadas neste projeto.
	 */
    public static final int DEFAULT_WAIT_TIME = 15;

    /**
     * DETERMINA O TIPO DE EXECUÇÃO DOS TESTES.
     * SE FOR FALSE, OS TESTES SERÃO EXECUTADOS DE MANEIRA INDEPENDENTE E CADA UM GERARÁ UMA ÚNICA EVIDÊNCIA COMO RESULTADO.
     * SE TRUE, OS TESTES SERÃO EXECUTADOS COMO SUITE E SERÁ GERADA UMA ÚNICA EVIDÊNCIA PARA TODOS OS TESTES.
     */
    public static boolean suite_test = false;
}
