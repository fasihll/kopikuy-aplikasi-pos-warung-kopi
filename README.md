# Kopikuy
Aplikasi poitn of sale untuk memudahkan warung kopi dalah proses transaksi maupun laporan berbasis dekstop
## Requirement
```bash
JDK 17
Mysql
```
## Alat & Bahan
- Netbean
- Laragon
- phpmyadmin
## Database location
```bash
src/config/kopikuy.sql
```
## Note
Pastikan folder Lib dan dist/lib sama

## Run
- open kopikuy/dist
- open in terminal
```bash
java --add-opens=java.base/java.lang=ALL-UNNAMED --add-opens=java.base/java.util=ALL-UNNAMED -jar kopikuy.jar

```

## build setup 
- pidahkan lib dan src ke folder kopikuy/dist/ replace
  
- downlaod & install  https://github.com/wixtoolset/wix3/releases/tag/wix3141rtm
- add path "C:\Program Files (x86)\WiX Toolset v3.14\bin" to system variable

- go to kopikuy/
- open in terminal
- and run this
```bash
jpackage --input dist/ --dest output/ --name KopikuyApp --main-jar kopikuy.jar --type exe --runtime-image "C:/Program Files/Java/jdk-17" --java-options "--add-opens=java.base/java.lang=ALL-UNNAMED --add-opens=java.base/java.util=ALL-UNNAMED" --win-menu --win-shortcut --win-dir-chooser --icon "D:\Project\dekstop\NetBeansProjects\kopikuy\kopikuy.ico"
```
### Sesuaikan
- nama jar: kopikuy.jar
- letak jdk: C:/Program Files/Java/jdk-17
- letak icon: D:/Project/dekstop/NetBeansProjects/kopikuy/lib/kopikuy.ico



