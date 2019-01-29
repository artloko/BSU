# OpenCL

[Условия задания в формате docx](https://github.com/artloko/BSU/blob/master/Computer%20Architecture/OpenCL/%D0%97%D0%B0%D0%B4%D0%B0%D0%BD%D0%B8%D1%8F.docx)

## How to install and set up OpenCL

Закройте Студию перед установкой SDK. Это позволит подхватить ей новые переменные среды (путь к SDK).

Теоретически, вы можете поставить любой из этих SDK или даже все, так как все устройства OpenCL реализуют общий стандарт. На практике же лучше использовать версию, оптимизированную для вашего вычислителя.

#### Общая SDK (можете устанавливать этот): 
  - установите OpenCL SDK: https://github.com/GPUOpen-LibrariesAndSDKs/OCL-SDK/releases  
  - после установки необходимые файлы могут быть найдены в папке **C:\Program Files (x86)\OCL_SDK_Light** или сокращённо **%OCL_ROOT%**

#### Только для карт NVIDIA (OpenCL + CUDA):
  - Идём на страницу с инструментами разработчика: https://developer.nvidia.com/cuda-downloads
  - Выбираем нужные параметры платформы; exe (network), нажимаем на кнопку «Download».
  - Во время установки выберите режим Custom (Advanced) и оставьте выбранным только Cuda SDK.
  - После установки вы можете найти файлы OpenCL в папке (по умолчанию) **C:\Program Files\NVIDIA GPU Computing Toolkit\CUDA\v10.0** или сокращённо **%Cuda_Path%**

#### Для билда установите следующие свойства проекта:
  - C/C++: Additional Include Directories:
**$(CUDA_PATH)\include**
  - Linker->General: Additional Library Directories:
**$(CUDA_PATH)\lib\x64;%(AdditionalLibraryDirectories)**
или для 32-битных систем:  **$(CUDA_PATH)\lib\Win32;%(AdditionalLibraryDirectories)**
  - Linker → Input: Additional Dependencies:
**OpenCL.lib;%(AdditionalDependencies)**

#### Только для карт и суперпроцессоров Intel:
  - Заходим на сайт Intel https://software.intel.com  и регистрируемся; подтверждаем свой почтовый ящик.
  - Заходим в раздел OpenCL: https://software.intel.com/en-us/intel-opencl/download, скачиваем SDK (указываем почтовый ящик).
  - Устанавливаем драйвер видеокарты (если он вдруг не установлен :) https://software.intel.com/en-us/articles/opencl-drivers 
  - Необходимые файлы будут в папке **C:\Program Files (x86)\Intel\OpenCL SDK\6.3** или сокращённо **%IntelOclSdkRoot%**
Свойства проекта выставляются аналогично NVIDIA.
Вы можете посмотреть посмотреть пути в примерах, в папке Properties.

Чтобы начать использовать возможности OpenCL необходимо подключить заголовочный файл <CL/cl.hpp> (<OpenCL/cl.hpp>). В нём представлены C++-обёртки методов.

Чтобы разрешить выброску исключений, объявите символ __CL_ENABLE_EXCEPTIONS перед импортом .hpp.

По какой-то причине при использовании SDK от Intel, компилятор начинает ругаться на использование устаревших функций в .hpp файле. Чтобы разрешить билд, объявите символ  CL_USE_DEPRECATED_OPENCL_2_0_APIS.

Файлы с *ядром* обычно называют с расширением *.cl. В нём представлен код на языке С99 с некоторыми расширениями.
Если у вас поставлена Студия и версия OpenCL для Intel, то для этих файлов у вас будет полная поддержка IntelliSense (будут подсвечены новые ключевые слова, в списках будут перечислены встроенные функции).

  > *by [@artloko](https://github.com/artloko)*
