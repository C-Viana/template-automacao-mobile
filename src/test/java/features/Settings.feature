#Author: your.email@your.domain.com

@Settings
Feature: Tela de configurações do Android

  @Settings1
  Scenario: Validar lista de opções na home de configurações
    Given que acessei a tela de configurações
    Then valido a lista de menus disponível

  @Settings2
  Scenario: Validar ícones na home de configurações
    Given que acessei a tela de configurações
    And identificar todos os ícones da lista de configurações
    Then valido que os ícones estão de acordo com o esperado
