# Дипломный проект по автоматизации тестовых сценариев с использованием UI и API тестов для [GitHub](https://github.com/)
##  Содержание:

+ [Использованный стек технологий](#computer-использованный-стек-технологий)
+ [Запуск тестов из терминала](#arrow_forward-запуск-тестов-из-терминала)
+ [Сборка в Jenkins](#сборка-в-jenkins)
+ [Пример Allure-отчета](#пример-allure-отчета)
+ [Уведомления в Telegram с использованием бота](#уведомления-в-telegram-с-использованием-бота)
+ [Видео примера запуска тестов в Selenoid](#-видео-примера-запуска-теста-в-selenoid)

## :computer: Использованный стек технологий

<p align="center">
<a href="https://www.jetbrains.com/ru-ru/idea/"><img width="6%" title="IntelliJ IDEA" src="media/logo/Intelij_IDEA.svg" alt="Intelij_IDEA"></a>
<a href="https://www.java.com/"><img width="6%" title="Java" src="media/logo/Java.svg" alt="Java"></a>
<a href="https://selenide.org/"><img width="6%" title="Selenide" src="media/logo/Selenide.svg" alt="Selenide"></a>
<a href="https://aerokube.com/selenoid/latest/"><img width="6%" title="Selenoid" src="media/logo/Selenoid.svg" alt="Selenoid"></a>
<a href="https://allurereport.org/"><img width="6%" title="Allure Report" src="media/logo/Allure_Report.svg" alt="Allure_Report"></a>
<a href="https://qameta.io/"><img width="5%" title="Allure TestOps" src="media/logo/AllureTestOps.svg" alt="AllureTestOps"></a>
<a href="https://gradle.org/"><img width="6%" title="Gradle" src="media/logo/Gradle.svg" alt="Gradle"></a>
<a href="https://junit.org/junit5/docs/current/user-guide/"><img width="6%" title="JUnit5" src="media/logo/JUnit5.svg" alt="JUnit5"></a>
<a href="https://github.com/"><img width="6%" title="GitHub" src="media/logo/GitHub.svg" alt="GitHub"></a>
<a href="https://www.jenkins.io/"><img width="6%" title="Jenkins" src="media/logo/Jenkins.svg" alt="Jenkins"></a>
<a href="https://web.telegram.org/"><img width="6%" title="Telegram" src="media/logo/Telegram.svg" alt="Telegram"></a>
<a href="https://www.atlassian.com/ru/software/jira"><img width="5%" title="Jira" src="media/logo/Jira.svg" alt="Jira"></a>
<a href="https://rest-assured.io/"><img width="5%" title="Rest Assured" src="media/logo/RestAssured.svg" alt="Jira"></a>
</p>

- В данном проекте автотесты написаны на языке <code>Java</code> с использованием фреймворка для тестирования Selenide.
- В качестве сборщика был использован - <code>Gradle</code>.
- Использованы фреймворки <code>JUnit 5</code> и [Selenide](https://selenide.org/).
- При прогоне тестов браузер запускается в [Selenoid](https://aerokube.com/selenoid/).
- Page Object шаблон проектирования.
- Использование библиотеки Owner для легкости конфигурации.
- Для удаленного запуска реализована сборка в <code>Jenkins</code> с формированием Allure-отчета и отправкой результатов в <code>Telegram</code> при помощи бота.
- Осуществлена интеграция с <code>Allure TestOps</code> и <code>Jira</code>
- Использование Lombok для моделей в API тестах.
- Использована спецификация для API-тестов.
- Применён allure rest-assured listener 

Содержание Allure-отчета:
* Шаги теста;
* Скриншот страницы на последнем шаге;
* Page Source;
* Логи браузерной консоли;
* Видео выполнения автотеста.

## :arrow_forward: Запуск автотестов

### Запуск тестов из терминала
```
gradle clean test
```
### Запуск тестов на удаленном браузере
```
gradle clean test -DisRemote=true
```
При необходимости также можно переопределить параметры запуска

```
clean
test
-DbaseUrl=${BASE_URL}
-DwebDriverHost=${WEB_DRIVER_HOST}
-DvideoHost=${VIDEO_HOST}
-Dbrowser=${BROWSER}
-Dversion=${VERSION}
-DbrowserSize=${BROWSER_SIZE}
```

### Параметры сборки
* <code>BASE_URL</code> – Url, по которому будет открываться тестируемое приложение.
* <code>WEBDRIVER_HOST</code> – адрес удаленного сервера, на котором будут запускаться тесты.
* <code>VIDEO_HOST</code> – адрес, где будут хранится записанные видео о прохождении тестов.
* <code>BROWSER</code> – браузер, в котором будут выполняться тесты. По-умолчанию - <code>chrome</code>.
* <code>VERSION</code> – версия браузера, в которой будут выполняться тесты. По-умолчанию - <code>100.0</code>.
* <code>BROWSER_SIZE</code> – размер окна браузера, в котором будут выполняться тесты.

## <img src="media/logo/Jenkins.svg" title="Jenkins" width="4%"/> Сборка в [Jenkins](https://jenkins.autotests.cloud/job/022_FennyKat-Jenkins-full-project/)
<p align="center">
<img title="Jenkins Build" src="media/screens/JenkinsBuild.png">
</p>

## <img src="media/logo/Allure_Report.svg" title="Allure Report" width="4%"/> Пример [Allure-отчета](https://jenkins.autotests.cloud/job/022_FennyKat-Jenkins-full-project/4/allure/)
### Overview

<p align="center">
<img title="Allure Overview" src="media/screens/allureReport.png">
</p>

### Результат выполнения теста

<p align="center">
<img title="Test Results in Alure" src="media/screens/ResultTest.png">
</p>

## <img src="media/logo/AllureTestOps.svg" title="Allure TestOps" width="4%"/> Интеграция с [Allure TestOps](https://allure.autotests.cloud/project/3755/dashboards)

Выполнена интеграция сборки <code>Jenkins</code> с <code>Allure TestOps</code>.
Результат выполнения автотестов отображается в <code>Allure TestOps</code>.
На Dashboard в <code>Allure TestOps</code> отображена статистика пройденных тестов.

<p align="center">
<img title="Allure TestOps DashBoard" src="media/screens/allureAutotestCloud.png">
</p>


<p align="center">
<img title="Allure TestOps DashBoard" src="media/screens/allureTestCases.png">
</p>

## <img src="media/logo/Jira.svg" title="Jira" width="4%"/> Интеграция с [Jira](https://jira.autotests.cloud/browse/HOMEWORK-934)

Реализована интеграция <code>Allure TestOps</code> с <code>Jira</code>, в тикете отображается информация, какие тест-кейсы были написаны в рамках задачи и результат их прогона.

<p align="center">
<img title="Jira Task" src="media/screens/jiraTask.png">
</p>

## <img width="4%" style="vertical-align:middle" title="Telegram" src="media/logo/Telegram.svg"> Уведомления в Telegram с использованием бота

После завершения сборки, бот созданный в <code>Telegram</code>, автоматически обрабатывает и отправляет сообщение с результатом.

<p align="center">
<img width="70%" title="Telegram Notifications" src="media/screens/notification.png">
</p>

## <img width="4%" style="vertical-align:middle" title="Selenoid" src="media/logo/Selenoid.svg">Видео примера запуска тестов в Selenoid

К тестам в отчете прилагается видео прогона.

<p align="center">
  <img src="/media/screens/Video.gif">
</p>
