set STAGE_FILE=%1
"%~dp0liquibase\liquibase.bat" --defaultsFile=%STAGE_FILE%.properties --logLevel=info updateSQL