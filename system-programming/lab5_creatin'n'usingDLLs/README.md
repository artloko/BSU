 # Создание и использование DLL
 
  Разработать консольное приложение, расширяемое за счет плагинов. Каждый плагин представляет
собой DLL и должен экспортировать следующие функции:

  ***BOOLEAN GetAuthor(LPSTR buffer, DWORD dwBufferSize, DWORD* pdwBytesWritten);** *// автор плагина*
  
  ***BOOLEAN GetDescription(LPSTR buffer, DWORD dwBufferSize, DWORD* pdwBytesWritten);** *// описание
плагина*

  **VOID Execute();** *// произвольное действие. Желательно вывод какой-нибудь системной информации,
например число разделов на жестком диске, количество и тип процессоров, присутствуют ли в
системе cd/dvd …*

  Функции экспортируются из библиотеки по соглашению stdcall. Плагины находятся в каталоге Plugins,
расположенном в каталоге с программой

Главное приложение должно предоставлять пользователю следующее меню:
1. Загрузить плагины.
2. Выгрузить плагины.
3. Показать на экране список плагинов. (просто имена модулей).
4. Показать информацию о каждом плагине (включая имя модуля, автора, описание).
5. Выполнить действие, предоставляемое плагином.

Разработать не менее двух плагинов.

Вынести часть функционала приложения в отдельную библиотеку с неявным связыванием.

### How to build

  В директории представлены файлы исходного кода, на основе которых нужно собрать работающий проект. Все данные ниже манипуляции исполнял в VS2017.
  Неявно подключал .dll и .lib через References в одном Solution, ниже дам ссылки на подключение библиотек не лежащих в одном Solution'е (более верное решение).
  Одна из .dll, .lib - неявное подключение. Вторая .dll - явное.
  Названия файлов фиктивны
  
  ##### Создаем Empty Project в VS
  Создаем main.c и копируем исходный код из соответствующего в данной директории.
  
  
  ##### Создаем нужные библиотеки
  - Solution Explorer -> *"Solution Name"* -> Add Project -> Dynamic Link Library (х2)
  - Solution Explorer -> *"Solution Name"* -> Add Project -> Static Link Library
  
  Копируем исходный код соответствующим образом.
  Билдим наши .dll и .lib
  
  ##### Связываем
  
  В основном проекте, нужно выставить reference на наши неявно подключаемые библиотеки.
  - Solution Explorer -> *"Solution Name"* -> References
  
  Выставить галочки у Plugin_1 и Plugin_3.
  
  ##### Путь к .h
  
  Указать путь на .h файл библиотеки, в котором объявлены экспортируемые функции.
  - Solution Explorer -> *"Solution Name"* -> Properties -> C/C++ -> General -> Additional Include Directories
  
  ##### Завершение
  
  В связи с тем, что Plugin_2 писался на C++, а основной проект на C, искажаются имена экспортируемых из .dll функций, нужно добавить .def файл такого вида:
  
  ```
  LIBRARY Plugin_2_GetMemory

  EXPORTS getAuthorSecond
		getDescriptionSecond
		getNameSecond
		executeSecond
  ```
  
  Осталось подкорректировать main.c, в куске кода, который получает адрес импортируемых явно функций, изменить путь, по которому лежит ваш Plugin_2.dll
  
  На этом все.
  
  Полезные ссылки:
  
  - [Create and use your DLL](https://docs.microsoft.com/en-us/cpp/build/walkthrough-creating-and-using-a-dynamic-link-library-cpp?view=vs-2017)
  - [Create and use your LIB](https://docs.microsoft.com/en-us/cpp/windows/walkthrough-creating-and-using-a-static-library-cpp?view=vs-2017)
  - [Create and use your LIB (2)](https://stackoverflow.com/questions/23882112/how-to-add-the-static-libraries-to-the-project-in-visual-studio)
  
  > *by [@artloko](https://github.com/artloko)*
  
  
  
  
