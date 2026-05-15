<img src="https://user-images.githubusercontent.com/74038190/212284158-e840e285-664b-44d7-b79b-e264b5e54825.gif" width="100%">
<br><br>

# О проекте: 🎮 JGames Server

Бэкенд-система для управления игровыми статистиками и рейтингами игроков. Проект реализует безопасную авторизацию и хранение рекордов для различных мини-игр.

# В проект входят:

* 🟦 Breakout
* 🐦 Flappy Bird
* 🐍 Snake
* 👻 Pacman
* 💣 Minesweeper

## 🛠 Технологический стек и зависимости
* Java 17
* Spring Boot 4.0.6 (Starter Web, Data JPA, Security)   
* PostgreSQL — основная база данных   
* JWT (JSON Web Token) — для обеспечения безопасности и аутентификации   
* Lombok — для сокращения шаблонного кода   
* Dotenv — для управления переменными окружения   
* BCrypt — для шифрования паролей   

## 📁 Структура проекта

```text
jgames-server/
├── src/main/java/jgames_server/jgames_sever/
│   ├── config/             # Конфигурация безопасности (Spring Security)
│   ├── controller/         # REST-контроллеры (Auth, Stats)
│   ├── models/             # Сущности базы данных (Game, Player, Stat)
│   ├── repository/         # Интерфейсы для работы с БД (JPA Repositories)
│   ├── service/            # Бизнес-логика (JWT, Stats, Seeder)
│   └── JgamesSeverApplication.java  # Точка входа в приложение
├── src/main/resources/
│   └── application.properties # Основные настройки Spring
├── .env                    # (Создать самому) Переменные окружения для БД
└── pom.xml                 # Описание зависимостей Maven
```

* `/src/main/java/games` — Папка в которой находится код для каждой игры.
* `/src/main/java/org/example/Main.java` — Точка входа в приложение.
* `/src/main/resources` — Папка с ресурсами для каждой игры.

## 👨‍💻 Авторы проекта
* Андроник Александр
* Кустаев Руслан
* Янич Андрей

## 🚦 Как запустить проект
## 1.Клонируйте репозиторий:
```bash
git clone https://github.com/your-username/jgames-server.git
cd jgames-server
```

### 2. Настройка переменных окружения
В корне проекта создай файл .env и укажи данные для подключения к твоей PostgreSQL:
```bash
DATABASE_URL=jdbc:postgresql://localhost:5432/имя_вашей_бд
DATABASE_USERNAME=ваш_логин
DATABASE_PASSWORD=ваш_пароль
```
### 3. Сборка и запуск
Убедись, что у тебя установлен Maven и JDK 17.
```bash
mvn clean install
mvn spring-boot:run
```


## 📈 Что нучно улучшить в будущем:
* всё

