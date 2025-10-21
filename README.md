# Projeto T.inder v

### Um Estudo de UI com Jetpack Compose e Funcionalidades de App Completas

## 📖 Sobre o Projeto

**T.inder v3** é a evolução de um projeto de estudo que replica a experiência de um aplicativo de relacionamentos.  
Esta versão vai além do estudo de UI e implementa uma arquitetura robusta, com funcionalidades reais e modernas práticas de desenvolvimento Android.

O foco principal é o uso do **Jetpack Compose**, integrando **Navigation Compose** para navegação estruturada e **Room Database** para persistência de dados locais, resultando em uma experiência mais completa e interativa.

> **Nota:** Substitua os caminhos abaixo por *screenshots reais* do seu aplicativo para demonstrar o resultado final.

| Tela de Login | Tela de Cadastro | Tela Principal | Tela de Matches | Tela de Perfil |
|:---:|:---:|:---:|:---:|:---:|
| <img src="link_para_sua_screenshot_login.png" width="200"/> | <img src="link_para_sua_screenshot_cadastro.png" width="200"/> | <img src="link_para_sua_screenshot_principal.png" width="200"/> | <img src="link_para_sua_screenshot_matches.png" width="200"/> | <img src="link_para_sua_screenshot_perfil.png" width="200"/> |

---

## ✨ Funcionalidades Implementadas

- **Sistema de Autenticação Completo**  
  - Tela de **Login**: autenticação de usuários com dados persistidos localmente.  
  - Tela de **Cadastro**: criação de novos usuários com armazenamento no banco de dados.  

- **Gerenciamento de Perfil**  
  - Tela de **Perfil**: exibe nome e bio do usuário, permitindo edição e exclusão da conta.  

- **Interação Principal**  
  - Tela **Principal**: exibe perfis com botões de ação para aceitar ou rejeitar.  
  - Tela de **Matches**: lista compatíveis com busca e filtragem.  

- **Navegação Estruturada**  
  - Implementada com **Navigation Compose**, incluindo passagem segura de argumentos entre telas.  

---

## 🛠️ Conceitos e Tecnologias Aplicadas

- **Linguagem:** Kotlin  
- **UI Toolkit:** Jetpack Compose  
- **Navegação:** Navigation Compose  
- **Persistência de Dados:** Room Database  
- **Gerenciamento de Estado:** `remember`, `mutableStateOf`, coroutines  
- **Componentização:** Composables reutilizáveis (botões, cards, layouts)  
- **IDE:** Android Studio  

---

## 🚀 Como Executar

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/MalikRibeiro/T.Inder-v3.git
Abra no Android Studio:

Importe o projeto clonado.

Adicione os recursos de imagem:

Coloque as imagens em app/src/main/res/drawable/.

Sincronize o Gradle:

Aguarde o download das dependências.

Execute o app:

Rode em um emulador ou dispositivo físico Android.

📂 Arquitetura e Estrutura do Código
O projeto adota uma estrutura modular e escalável.
Cada tela (TelaLogin, TelaCadastro, TelaPrincipal, TelaMatches, TelaPerfil) é um Composable em seu próprio arquivo .kt.

A navegação é centralizada em AppNavigation.kt, que define o NavHost e todas as rotas da aplicação, garantindo clareza e desacoplamento.

📈 Possíveis Melhorias (Próximos Passos)
Implementar Arquitetura MVVM com ViewModels para melhor separação de responsabilidades.

Adotar injeção de dependência (Hilt ou Koin) para gerenciar DAOs e banco de dados.

Conectar a um backend real para buscar perfis dinâmicos.

Aprimorar UX/UI com animações e gestos de swipe.

Criar testes unitários para ViewModel e DAO, garantindo estabilidade e qualidade do código.

📱 Autor: Malik Ribeiro Mourad
💡 Projeto desenvolvido com foco em aprendizado e aprimoramento em Android moderno com Jetpack Compose.
