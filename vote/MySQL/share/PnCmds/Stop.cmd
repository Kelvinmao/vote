@echo off

rem -- http://www.upupw.net
rem -- webmaster@upupw.net

setlocal enableextensions
if exist MySQL\share\Pn\Config.cmd pushd . & goto cfg
if exist ..\MySQL\share\Pn\Config.cmd pushd .. & goto cfg
goto :eof


:execmd
echo %1
if exist %1 call %1 && goto :eof
if exist %PnCmds%\%1 call %PnCmds%\%1 && goto :eof
echo # 找不到 %1, 请检查 %PnCmds% 或 %CD% 目录.
%pause%
goto :eof


:cfg
call MySQL\share\Pn\Config.cmd
if "%php%"=="" exit /b
title 正在停止 Apache MySQL PHP及相关组件服务
echo.
call :execmd Apa_Stop.cmd
echo.
call :execmd My_Stop.cmd
echo.
call :execmd mem_Stop.cmd

popd
