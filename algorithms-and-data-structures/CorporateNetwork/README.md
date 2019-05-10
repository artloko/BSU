 # Задача 38. Корпоративная сеть

| Параметр | Значение |
| --- | --- |
|Имя входного файла|input.txt|
|Имя выходного файла|output.txt|
|Ограничение по времени|1 с|
|Ограничение по памяти|нет|

### Условие
 Очень большая корпорация решила разработать свою собственную сеть. В начале каждое из n предприятий этой корпорации, пронумерованных от 1 до n, имеет свой вычислительный и телекоммуникационный центр. Для повышения качества сервиса корпорация начала собирать некоторые предприятия в кластеры, каждый из которых обслуживается одним вычислительным и телекоммуникационным центром. Для этой цели корпорация выбирает один из существующих центров (пусть это будет центр i, который обслуживает кластер a) и одно из предприятий j, находящееся в каком-то другом кластере b (b не обязательно является центром). Затем i и j связываются телекоммуникационной линией. Длина линии между предприятиями i и j вычисляется по формуле |i − j| mod 1000. Таким образом, два старых кластера объединяются в один новый, причём телекоммуникационным центром нового кластера объявляется телекоммуникационный центр кластера b. Если телекоммуникационный сервер j обслуживает кластер a и предприятие i находится в кластере a, то будем говорить, что j является обслуживающим центром для i. Но, к сожалению, после каждого такого объединения длина телекоммуникационной линии между предприятием и обслуживающим его центром может меняться, и руководство корпорации хочет знать эту длину. Напишите программу, которая позволила бы корпорации узнавать расстояния от предприятия до центра в любой момент времени.

### Замечание
 На рисунках телекоммуникационные центры обведены в красные кружки, предприятие, для которого требуется вычислить расстояние от него до его телекоммуникационного центра выделяется зелёным кружком. Выполним по шагам заданную в примере последовательность команд:

  - E 3: между предприятием 3 и его обслуживающим центром проложена линия длиной 0 (рис. 1);
  - I 3 1: обслуживающий центр 3 присоединяется к предприятию 1 (рис. 2);
  - E 3: между предприятием 3 и его обслуживающим центром проложена линия длиной 2 (рис. 3);
  
  ![alt text](https://i.imgur.com/R9qiYHg.jpg)  ![alt text](https://i.imgur.com/1ZeaJyz.jpg)  ![alt text](https://i.imgur.com/A6JUm7L.jpg)
  
   - I 1 2: обслуживающий центр 1 присоединяется к предприятию 2 (рис. 4);
   - E 3: между предприятием 3 и его обслуживающим центром проложена линия длиной 3 (рис. 5);
   - I 2 4: обслуживающий центр 2 присоединяется к предприятию 4 (рис. 6);
   
   ![alt text](https://i.imgur.com/iEocXSD.jpg)  ![alt text](https://i.imgur.com/xOWbUSy.jpg)  ![alt text](https://i.imgur.com/MLiM71a.jpg)
  
   - E 3: между предприятием 3 и его обслуживающим центром проложена линия длиной 5 (рис. 7);
   - O: все команды успешно обработаны (рис. 8).
   
   ![alt text](https://i.imgur.com/aYC3Mjw.jpg)  ![alt text](https://i.imgur.com/iEo2FJU.jpg)
  
### Формат входных данных
 Первая строка содержит число n предприятий (4 ≤ n ≤ 200 000). Следующие строки описывают команды, подаваемые корпорацией. Число команд не превосходит 200 000. Формат команд:
  - E i — запрос длины пути между предприятием i и обслуживающим его телекоммуникационным центром;
  - I i j — информирует о том, что обслуживающий центр i присоединяется к предприятию j;
  - O — говорит о том, что корпорация заканчивает свои команды (конец входных данных).
Данные в строках разделены одиночными пробелами.

### Формат выходных данных
 Выведите по одной строке на каждую команду E во входных данных. Каждая такая строка должна содержать единственное число — запрашиваемую длину между предприятием и обслуживающим его телекоммуникационным центром.
> *by [@artloko](https://github.com/artloko)*