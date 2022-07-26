#Author: your.email@your.domain.com

@cta
Feature: Treinamento Appium com o app CTA

  @cta001 @cta_form
  Scenario: Formulário - Deve preencher o campo de texto
    Given que acessei o app CTA
    When selecionar a opção "Formulário"
    Then preencho o campo de texto com o valor "Teste de texto"


  @cta002 @cta_form
  Scenario Outline: Formulário - Deve interagir com o combo
    Given que acessei o app CTA
    When selecionar a opção "Formulário"
    Then seleciono a opção "<OPTION>" no combo
    
    Examples:
    |OPTION					|
    |XBox One				|
    |PS4						|
    |Nintendo Switch|


  @cta003 @cta_form
  Scenario: Formulário - Deve interagir com o botão switch
    Given que acessei o app CTA
    When selecionar a opção "Formulário"
    Then valido a ativação do checkbox
    Then valido a desativação do switch


  @cta004 @cta_form
  Scenario: Formulário - Deve realizar um cadastro completo
    Given que acessei o app CTA
    When selecionar a opção "Formulário"
    Then preencher todo o formulário
	    |NOME			|Jacinto		|
			|CONSOLE	|Nintendo		|
			|SLIDER		|18					|
			|CHECKBOX	|true				|
			|SWITCH		|true				|
			|DATA			|10/01/2000	|
			|HORA			|13:32			|
    And valido que dados informados foram cadastrados
	
	
	@cta005 @cta_splash
	Scenario: Splash - Passar pela tela de Splash
		Given que acessei o app CTA
		When selecionar a opção "Splash"
		Then valido a tela splash e aguardo retorno para a home
	
	
	@cta006 @cta_alerts
	Scenario: Alertas - Realizar operação do alerta de confirmação
		Given que acessei o app CTA
		When selecionar a opção "Alertas"
		And visualizar o alerta de confirmação
		Then verifico que é possível interagir com os botões deste alerta
		And confirmo operação de confirmação
	
	
	@cta007 @cta_abas
	Scenario: Abas - Navegar entre as abas
		Given que acessei o app CTA
		When selecionar a opção "Abas"
		And confirmar que a tela inicial é da aba 1
		Then troco para a aba 2
		Then troco para a aba 1
	
	
	@cta008 @cta_cliques
	Scenario: Cliques - Realizar diferentes ações de clique
		Given que acessei o app CTA
		When selecionar a opção "Cliques"
		Then realizo as devidas interações de clique
	
	
	@cta009 @cta_swipes
	Scenario: Swipe List - Realizar diferentes ações swipe
		Given que acessei o app CTA
		When selecionar a opção "Swipe List"
		Then realizo as devidas interações swipe
	
	
	@cta010 @cta_drag
	Scenario: Drag and drop - Arrastar componentes para alterar a ordem de cada elemento
		Given que acessei o app CTA
		When selecionar a opção "Drag and drop"
		Then realizo as alterações de posicionamento dos elementos em tela
	
	
	@cta011 @cta_hibrido
	Scenario: SeuBarriga Híbrido - Realizar login pelo meio híbrido
		Given que acessei o app CTA
		When selecionar a opção "SeuBarriga Híbrido"
		Then realizo login na tela híbrida
	
	
	
	
