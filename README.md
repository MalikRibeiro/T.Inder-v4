# Projeto T.inder

Este é um projeto de estudo para Android nativo, desenvolvido com o objetivo de aprender e aplicar os conceitos fundamentais do Jetpack Compose. O aplicativo é um clone visual simples da interface de um aplicativo de relacionamentos, como o Tinder.

*(**Nota:** Substitua os caminhos acima pelas suas próprias screenshots para exibir o resultado final do app.)*

## ✨ Funcionalidades

- **Tela de Login:** Interface visual para entrada do usuário (sem autenticação real).
- **Tela Principal:** Exibição de um perfil principal com botões de ação (aceitar/rejeitar).
- **Tela de Matches:** Listagem de perfis compatíveis em um formato de lista.
- **Navegação Simples:** Sistema de navegação entre as telas gerenciado por estado dentro de uma única `Activity`.

## 🛠️ Tecnologias Utilizadas

- **[Kotlin](https://kotlinlang.org/):** Linguagem de programação principal.
- **[Jetpack Compose](https://developer.android.com/jetpack/compose):** Toolkit moderno para construção de UIs nativas no Android.
- **[Android Studio](https://developer.android.com/studio):** IDE de desenvolvimento oficial para Android.

## 🚀 Como Executar o Projeto

1.  **Clone o repositório** (ou baixe os arquivos).
2.  **Abra o projeto** no Android Studio.
3.  **Adicione os recursos de imagem:**
    -   Na pasta `app/src/main/res/drawable/`, adicione as seguintes imagens:
        -   `logo.png`
        -   `profile_image.jpg`
4.  **Sincronize o Gradle** e aguarde o build do projeto.
5.  **Execute o aplicativo** em um emulador ou dispositivo físico.

## 📂 Estrutura do Código

Para manter a simplicidade e focar nos conceitos de UI do Jetpack Compose, todo o código do aplicativo (telas e lógica de navegação) foi centralizado em um único arquivo: `MainActivity.kt`.

A navegação entre as telas é controlada por uma variável de estado (`var telaAtual`) que armazena um número (`Int`) para identificar a tela ativa. Uma estrutura `when` renderiza o Composable correspondente à tela atual.

Para um projeto de produção, a estrutura ideal seria separar cada tela em seu próprio arquivo, utilizar a biblioteca `Navigation Compose` e adotar um padrão de arquitetura como o MVVM (Model-View-ViewModel).
