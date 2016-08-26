@echo off
cd /d "%~dp0"
title Offset-Scanner

set bat="./build/install/Offset-Scanner/bin/Offset-Scanner.bat"

:loop
if exist %bat% (
    call %bat%
    pause
) else (
    call build.bat
    cls
    title Offset-Scanner
    goto loop
)