# MPI

[Условия задания в формате docx](https://github.com/artloko/BSU/blob/master/Computer%20Architecture/MPI/%D0%9B%D0%B0%D0%B1.1%20%E2%80%94%20%D0%97%D0%B0%D0%B4%D0%B0%D0%BD%D0%B8%D1%8F%20(MPI).docx)

## How to install and set up MPI

MSDN : https://msdn.microsoft.com/en-us/library/bb524831(v=vs.85).aspx 

Описание всех функций MPI (для Фортрана, но разницы особой нету): http://mpi-forum.org/docs/mpi-1.1/mpi-11-html/node182.html

Ошибка «ERROR: unable to allocate launching block» возникает из-за того, что стандарт MPI не поддерживает юникод. Проверьте, чтобы пути не содержали символов вне ASCII-128.

Что необходимо установить (для запуска на Windows):
- MPI SDK, то есть сами библиотеки (.lib + .h);
- MS MPI, который позволяет запускать программы MPI на вашей машине.

Актуальная версия может быть найдена в MSDN по запросу «Microsoft MPI».

Проверьте, что у вас установлены библиотеки C++. В Студии нажмите File → New → Project... , выберите в дереве Installed → Templates → Other Languages → Visual C++. В списке должен быть пункт «Win32 Console Application». Если С++ не установлен, то пункт будет начинаться со слова «Install».

Чтобы расшарить свойства проекта используются файлы конфигурации. Для создания такого файла сделайте следующее:
- вызовите окно «Property Manager» (View → Other Windows → Property Manager);
- выберите папку «Debug | x64» (Release | x64, Debug | Win32, Release | Win32);
- нажмите кнопку на тулбаре или выберите из контекстного меню «Add New Project Property Sheet»;
- назовите его, например, «MPI_x64» (MPI_x32);
- два раза кликните, или нажмите Alt+Enter, или выберите пункт «Properties» из контекстного меню, чтобы начать редактирование свойств.

Чтобы применить свойства к новому проекту:
- выберите нужные папки в Property Manager (Debug | x64, Release | x64, Debug | Win32, Release | Win32), 
- Нажмите кнопку на тулбаре или выберите в контекстном меню «Add Existing Property Sheet» и выберите ранее созданный файл.
- Чтобы изменения вступили в силу, сделайте билд солюшена (Ctrl+Shift+B).

Что прописывать в свойствах для билда проекта WMPI (MS VS 2015):
- Выбрать проект (или файл свойств), нажать Alt+Enter
- Раздел C/C++: Additional Include Directories: **$(MSMPI_INC);$(MSMPI_INC)\x64** (или **x86**)
- Раздел Linker → General: Additional Library Directories: **$(MSMPI_LIB64)** (или **$(MSMPI_LIB32)**)
- Раздел Linker → Input: Additional Dependencies: **msmpi.lib;%(AdditionalDependencies)**
- если у вас не установлены библиотеки C++ 2015/2017, то дополнительно надо изменить используемую версию C++. Выделите все проекты, нажмите Alt+Enter и измените **Platform Toolset** на установленный.
- если у вас не Windows 10, то также измените **Windows SDK Version**.
- Чтобы сбилдить сразу все проекты в солюшене нажмите Ctrl+Shift+B.

Для подключения заголовочного файла MPI в файл .cpp используйте директиву #include "mpi.h"

Как запустить: при помощи программы mpiexec.
Типичный запуск из командной строки (Win+R, cmd) выглядит так:
*mpiexec -n ProcessCount “AppPath”*
где ключ *-n* означает, что следующий аргумент — число, обозначающее количество процессов,
*ProcessCount* — число процессов и
*“AppPath”* — путь к приложению. Обратите внимание, что путь должен быть записан в кавычки, если он содержит пробелы. Также напоминаю, что в пути должны быть только символы ASCII-128 (символы базовой латиницы, цифры и андерскор).
Либо вы можете использовать скрипт *«Runner.bat»*:
```
set AppPath="%1"
set ProcessCount=8
start cmd /k "mpiexec -n %ProcessCount% %AppPath%"
```
Команда *set A=B* выполняет присваивание значение *B* переменной *A*,
макрос *%1* получает первый аргумент командной строки,
команда *start App args* запускает приложение *App* с аргументами *args*,
ключ */k* не даёт закрыться командной строке после завершения программы.


  ##### Для запуска MPI на Linux:
  1. В терминале ввести: sudo apt-get install libcr-dev mpich2 mpich2-doc
  2. Для компиляции: mpicxx -o <имя_исполняемого_файла> <файл_исходного_кода> --std=c++11
  3. Для запуска: mpirun -n <число_процессов> <исполняемый_файл>
  
  > *The manual was written by [@Igorious](https://github.com/Igorious)*


  > *by [@artloko](https://github.com/artloko)*
