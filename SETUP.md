#DB: p0iapp-db4001lv.vtb24.ru
1. Создание пользователя DB:
CREATE USER iapp WITH encrypted password '(TBD)';
2. Создание базы:
CREATE DATABASE iapp OWNER iapp ENCODING 'UTF8' LC_COLLATE='ru_RU.utf8' LC_CTYPE='ru_RU.utf8';
3. Настройка pg_hba.conf:
host    all             all             10.64.123.214/32            scram-sha-256

#112PushNotifications: p0iapp-ap4101lv.vtb24.ru
1. Установить JDK 15 с https://jdk.java.net/15/
2. Проверить, что заполнена переменная окружения JAVA_HOME
3. Создать переменные окружения:
	set JAVA_HOME=путь до JDK
	set KEY_ALIAS=(TBD)
	set KEY_STORE_PATH=(TBD)
	set KEY_STORE_PASSWORD=(TBD)
	set TRUST_STORE_PATH=(TBD)
	set TRUST_STORE_PASSWORD=(TBD)
4. Добавать в PATH %JAVA_HOME%\bin (если еще нет)
5. Скачать архив по ссылке (TBD)
6. Разархивировать архив в (TBD)
7. Создать локальную учетную запись user112-push
8. Выдать полные права пользователю user112-push на папку и все подпапки (Если такого пользователя нет, то пропустить этот шаг)
9. Настроить запуск сервиса с командой java -jar 112PushNotifications-1.0.0.jar --spring.profiles.active=stage --app112.settings.key=0DA5E4F34B789C413A34E71930A215F7
	Сервис должен автоматически запускаться при старте ОС
10. Запустить сервис
11. Убедиться что сервис работает curl https://localhost/actuator/health

#112Monitoring: p0iapp-ap4001wn.msk.vtb24.ru
1. Установить JDK 15 с https://jdk.java.net/15/ в C:\Program Files\Java\jdk-15.0.1\
2. Создать переменные окружения:
	set JAVA_HOME=C:\Program Files\Java\jdk-15.0.1\ (если еще нет)
	set POSTGRES_PASSWORD=(TBD)
	set VERTICA_PASSWORD=(TBD)
	set SM_PASSWORD=(TBD)
4. Добавать в PATH %JAVA_HOME%\bin (если еще нет)
5. Скачать архив по ссылке (TBD)
6. Разархивировать архив в корень диска С (при необходимости можно разместить в другое место. В этом случае соответствующе изменить пути в следующах пунктах)
7. Убедиться что создалась папка C:\112Monitoring, в ней есть подпапка config и файл 112Monitoring-1.0.0.jar
8. Выдать полные права пользователю region\mobile112-user-tech на папку C:\112Monitoring и все подпапки (Если такого пользователя нет, то пропустить этот шаг)
9. Создать задачу в Windows Scheduler со следующими настройками:
	Имя: 112Monitoring
	Триггер: При запуске компьютера (At Startup)
	Действие: Запуск программы
	Скрипт: C:\Program Files\Java\jdk-15.0.1\bin\java.exe 
	Аргументы: java -jar 112Monitoring-1.0.0.jar --spring.profiles.active=stage
	Запуск от имени region\mobile112-user-tech (если нет, то от системного пользователя)
	Запускать даже если пользователь не залогинен
	Перезапускать через 1 минуту, если старт не удался
10. Проверить что в Windows Scheduler создана задача 112Monitoring
11. Запустить эту задачу вручную
12. Проверить что появился процесс java.exe
13. Проверить что появилась папка C:\112Monitoring\logs

Разово: 
Подождать пару минут и выслать содержимое папки C:\112Monitoring\logs на почту (TBD)
