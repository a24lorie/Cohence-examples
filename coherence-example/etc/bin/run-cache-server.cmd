@echo off
setlocal enabledelayedexpansion

set EXAMPLES_DIR=%~dp0..
set SCRIPT_NAME=%0
set EXAMPLE_PATH_BEGIN=com\tangosol\examples

call set_env SCRIPT_NAME
if defined RET (
    goto %RET%
    )

"%JAVA_HOME%"\bin\java %COH_OPTS% -Xms128m -Xmx128m com.tangosol.net.DefaultCacheServer %2 %3 %4 %5 %6 %7

:exit
