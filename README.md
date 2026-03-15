# 🛒 E-Commerce / Marketplace Platform

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3+-brightgreen?style=for-the-badge&logo=spring)
![React](https://img.shields.io/badge/React-20232A?style=for-the-badge&logo=react&logoColor=61DAFB)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![RabbitMQ](https://img.shields.io/badge/Rabbitmq-FF6600?style=for-the-badge&logo=rabbitmq&logoColor=white)
![GitHub Actions](https://img.shields.io/badge/CI%2FCD-GitHub_Actions-2088FF?style=for-the-badge&logo=github-actions&logoColor=white)

Uma plataforma de E-commerce (Marketplace) full-stack robusta e escalável. O sistema permite o cadastro de usuários com papéis distintos (Compradores e Vendedores), gerenciamento de produtos, e conta com uma arquitetura protegida por JWT, mensageria assíncrona e uma esteira de CI/CD totalmente automatizada.

## 🚀 Status e Deploy

* **Backend API (Render):** [https://marketplace-pinc.onrender.com](https://marketplace-pinc.onrender.com)
* **Frontend (Vercel):** https://my-repository-kelvens-projects-dd6e1c3c.vercel.app

> **Nota sobre o Backend:** A API está hospedada no plano gratuito do Render. Para evitar a hibernação da API (*Cold Start*), foi configurado um serviço de Ping (Keep-Alive) que mantém o servidor sempre responsivo.

## 💻 Tecnologias Utilizadas

### Backend
* **Linguagem:** Java 21
* **Framework:** Spring Boot 3+ (Web, Data JPA, Validation)
* **Segurança:** Spring Security + JWT (JSON Web Tokens)
* **Banco de Dados:** PostgreSQL (Produção) e H2 Database (Testes)
* **Mensageria:** RabbitMQ
* **Testes:** JUnit 5 e Mockito (Testes Unitários e de Integração com excelente cobertura)

### Frontend
* **Tecnologia:** React com Vite
* **Gerenciamento de Estado/Cache:** React Query (TanStack Query) / Context API
* **Estilização:** CSS / Componentes estilizados

### DevOps & Infraestrutura
* **CI/CD:** GitHub Actions (Pipelines de build, teste e deploy)
* **Hospedagem:** Render (Backend) e Vercel (Frontend)
* **Containers:** Docker e Docker Compose (para ambiente local)

## ⚙️ Funcionalidades Principais

- [x] **Autenticação e Autorização:** Login seguro com JWT e controle de acesso baseado em *Roles* (`BUYER`, `SELLER`).
- [x] **Gerenciamento de Usuários:** Criação, leitura, atualização e exclusão (CRUD) de perfis, com criptografia de senha (`BCrypt`).
- [x] **Catálogo de Produtos:** Vendedores podem criar e gerenciar produtos vinculados a categorias.
- [x] **Validações de Regras de Negócio:** Proteção contra duplicação de CPF e E-mail, e bloqueio de criação de produtos por usuários sem permissão.
- [x] **Testes Automatizados:** Suíte de testes validando "Caminhos Felizes" e exceções de negócio customizadas (`UserAlreadyExistsException`, `CategoryNotFoundException`, etc).

## 🛠️ Como rodar o projeto localmente

### Pré-requisitos
* Java 21+ instalado
* Node.js 20+ instalado
* Docker e Docker Compose instalados

### Passo a Passo

1. **Clone o repositório:**
```bash
git clone https://github.com/KelvenAlvess/e-commerce.git
cd seu-repositorio
```

2. **Suba a infraestrutura local (Banco de Dados e Mensageria):**
```bash
docker-compose up -d postgres rabbitmq
```

3. **Inicie o Backend:**
```bash
cd backend
mvn clean install
mvn spring-boot:run
```

4. **Inicie o Frontend:**
```bash
cd ../frontend
npm install
npm run dev
```

## 🧪 Rodando os Testes

O projeto utiliza o banco em memória (H2) configurado através do perfil `@ActiveProfiles("test")`, garantindo que os testes rodem de forma isolada, rápida e sem depender do Docker para serem validados pelo GitHub Actions.

```bash
cd backend
mvn clean test
```

## 👨‍💻 Autor

**Kélven Alves Gomes**
Estudante do 4º período de Ciência da Computação - Universidade Federal Rural de Pernambuco (UFRPE)

* [LinkedIn]https://www.linkedin.com/in/kelven-alves
* [GitHub]https://github.com/KelvenAlvess