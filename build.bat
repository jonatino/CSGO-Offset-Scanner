@echo off
cd "%~dp0"
title Offset-Scanner Builder
call gradlew installDist
echo.
pause