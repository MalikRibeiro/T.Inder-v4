 # Projeto T.inder v2

Um Estudo de UI com Jetpack Compose
📖 Sobre o Projeto
T.inder v2 é um projeto de estudo focado em replicar a experiência visual de um aplicativo de relacionamentos, como o Tinder, utilizando as mais modernas ferramentas de desenvolvimento Android. O principal objetivo foi aprofundar os conhecimentos em Jetpack Compose, explorando a criação de interfaces declarativas, gerenciamento de estado e navegação de forma concisa e eficiente.

(Nota: Substitua os caminhos abaixo por screenshots reais do seu aplicativo para demonstrar o resultado final.)

Tela de Login	Tela Principal	Tela de Matches
<img src="link_para_sua_screenshot_login.png" width="200"/>	<img src="link_para_sua_screenshot_principal.png" width="200"/>	<img src="link_para_sua_screenshot_matches.png" width="200"/>

Exportar para as Planilhas
✨ Funcionalidades Implementadas
Tela de Login: Uma interface de entrada visualmente atraente (sem lógica de autenticação real).

Tela Principal: Apresentação de um perfil central com botões de ação para aceitar ou rejeitar.

Tela de Matches: Uma lista vertical que exibe perfis compatíveis.

Navegação Simples: Um sistema de navegação reativo entre as telas, controlado por estado dentro de uma única Activity.

🛠️ Conceitos e Tecnologias Aplicadas
Linguagem: Kotlin

UI Toolkit: Jetpack Compose para a construção de toda a interface de usuário de forma declarativa.

Gerenciamento de Estado: Utilização de remember e mutableStateOf para controlar o estado da UI e a navegação entre as telas.

Componentização: Criação de Composables reutilizáveis para elementos de UI como botões, cards de perfil e layouts de tela.

IDE: Android Studio

🚀 Como Executar
Para rodar este projeto, siga os passos abaixo:

Clone o repositório:

Bash

git clone https://github.com/MalikRibeiro/T.Inder-v2.git
Abra no Android Studio:

Importe o projeto clonado.

Adicione os Recursos de Imagem:

Na pasta app/src/main/res/drawable/, adicione as imagens necessárias para o projeto, como logo.png e profile_image.jpg.

Sincronize o Gradle:

Aguarde o Android Studio baixar as dependências e construir o projeto.

Execute:

Inicie o aplicativo em um emulador Android ou em um dispositivo físico.

📂 Arquitetura e Estrutura do Código
Visando a simplicidade e o foco total nos conceitos de UI com Compose, todo o código da aplicação foi consolidado no arquivo MainActivity.kt.

A navegação é gerenciada por uma variável de estado (var telaAtual by remember { mutableStateOf(0) }) que determina qual Composable de tela deve ser renderizado. Uma estrutura when é utilizada para alternar entre as telas de forma reativa.

📈 Possíveis Melhorias (Próximos Passos)
Para evoluir este projeto para uma aplicação mais robusta e escalável, os seguintes passos seriam ideais:

Separar Composables: Dividir cada tela (Login, Home, Matches) em seus próprios arquivos .kt para melhor organização.

Adotar Navigation Compose: Substituir o controle de estado manual pela biblioteca androidx.navigation:navigation-compose para uma navegação mais idiomática e poderosa.

Implementar Arquitetura MVVM: Introduzir ViewModels para separar a lógica de negócio da UI, gerenciando o estado e as interações do usuário de forma mais limpa.

Criar um Modelo de Dados: Desenvolver classes de dados (data class) para representar perfis de usuário e matches.
