# Projeto T.inder v3

### Um Estudo de UI com Jetpack Compose

## 📖 Sobre o Projeto

**T.inder v2** é um projeto de estudo focado em replicar a experiência visual de um aplicativo de relacionamentos, como o Tinder, utilizando as mais modernas ferramentas de desenvolvimento Android.  
O principal objetivo foi aprofundar os conhecimentos em **Jetpack Compose**, explorando a criação de interfaces declarativas, gerenciamento de estado e navegação de forma concisa e eficiente.

> **Nota:** Substitua os caminhos abaixo por *screenshots reais* do seu aplicativo para demonstrar o resultado final.

| Tela de Login | Tela Principal | Tela de Matches |
|:-------------:|:--------------:|:---------------:|
| <img src="link_para_sua_screenshot_login.png" width="200"/> | <img src="link_para_sua_screenshot_principal.png" width="200"/> | <img src="link_para_sua_screenshot_matches.png" width="200"/> |

---

## ✨ Funcionalidades Implementadas

- **Tela de Login**: Interface de entrada visualmente atraente (sem lógica de autenticação real).
- **Tela Principal**: Apresentação de um perfil central com botões de ação para aceitar ou rejeitar.
- **Tela de Matches**: Lista vertical que exibe perfis compatíveis.
- **Navegação Simples**: Sistema de navegação reativo entre telas, controlado por estado em uma única `Activity`.

---

## 🛠️ Conceitos e Tecnologias Aplicadas

- **Linguagem**: Kotlin  
- **UI Toolkit**: Jetpack Compose para construção declarativa da UI  
- **Gerenciamento de Estado**: `remember` e `mutableStateOf` para controle de UI e navegação  
- **Componentização**: Composables reutilizáveis para botões, cards de perfil e layouts  
- **IDE**: Android Studio  

---

## 🚀 Como Executar

1. **Clone o repositório**:

   ```bash
   git clone https://github.com/MalikRibeiro/T.Inder-v2.git
Abra no Android Studio:

Importe o projeto clonado.

Adicione os Recursos de Imagem:

Na pasta app/src/main/res/drawable/, adicione as imagens necessárias como logo.png e profile_image.jpg.

Sincronize o Gradle:

Aguarde o Android Studio baixar as dependências e construir o projeto.

Execute:

Inicie o app em um emulador ou dispositivo físico Android.

## 📂 Arquitetura e Estrutura do Código
O projeto foi simplificado para foco total em UI com Compose, mantendo todo o código centralizado em MainActivity.kt.

A navegação é gerenciada por uma variável de estado:

kotlin
Copiar código
var telaAtual by remember { mutableStateOf(0) }
Uma estrutura when é usada para alternar entre os Composables das telas de forma reativa.

## 📈 Possíveis Melhorias (Próximos Passos)
Para evoluir o projeto em direção a uma aplicação mais robusta:

Separar Composables: Criar arquivos .kt individuais para cada tela (Login, Home, Matches).

Adotar Navigation Compose: Usar androidx.navigation:navigation-compose para uma navegação mais estruturada.

Implementar Arquitetura MVVM: Introduzir ViewModels para separar lógica de negócio da UI.

Criar um Modelo de Dados: Usar data class para representar perfis de usuário e matches.
