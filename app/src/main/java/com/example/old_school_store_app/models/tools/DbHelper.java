package com.example.old_school_store_app.models.tools;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper
{
    public DbHelper(Context context)
    {
        super(context,"app12.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE IF NOT EXISTS \"categories\" (\n" +
                "\t\"id\"\tINTEGER NOT NULL,\n" +
                "\t\"name\"\tTEXT NOT NULL,\n" +
                "\t\"description\"\tTEXT NOT NULL,\n" +
                "\t\"picture_path\"\tTEXT NOT NULL,\n" +
                "\tPRIMARY KEY(\"id\" AUTOINCREMENT)\n" +
                ");");

        db.execSQL("CREATE TABLE IF NOT EXISTS \"products\" (\n" +
                "\t\"id\"\tINTEGER NOT NULL,\n" +
                "\t\"name\"\tTEXT NOT NULL,\n" +
                "\t\"price\"\tINTEGER NOT NULL,\n" +
                "\t\"count_purchases\"\tINTEGER NOT NULL,\n" +
                "\t\"description\"\tTEXT NOT NULL,\n" +
                "\t\"category_id\"\tINTEGER NOT NULL,\n" +
                "\t\"count_left\"\tINTEGER NOT NULL,\n" +
                "\tPRIMARY KEY(\"id\" AUTOINCREMENT)\n" +
                ");");

        db.execSQL("CREATE TABLE IF NOT EXISTS \"products_pictures\" (\n" +
                "\t\"id\"\tINTEGER NOT NULL,\n" +
                "\t\"product_id\"\tINTEGER NOT NULL,\n" +
                "\t\"picture_path\"\tTEXT NOT NULL,\n" +
                "\tPRIMARY KEY(\"id\" AUTOINCREMENT)\n" +
                ");");

        db.execSQL("CREATE TABLE IF NOT EXISTS \"users\" (\n" +
                "    \"id\"    INTEGER NOT NULL,\n" +
                "    \"login\"    TEXT NOT NULL,\n" +
                "    \"password\"    TEXT NOT NULL,\n" +
                "    \"name\"    TEXT NOT NULL,\n" +
                "    \"bdate\"    INTEGER NOT NULL,\n" +
                "    \"phone\"    TEXT NOT NULL,\n" +
                "    \"email\"    TEXT NOT NULL,\n" +
                "    PRIMARY KEY(\"id\" AUTOINCREMENT)\n" +
                ");");

        db.execSQL("CREATE TABLE IF NOT EXISTS \"settings_app\" (\n" +
                "    \"id\"    INTEGER NOT NULL,\n" +
                "    \"key_field\"    TEXT NOT NULL,\n" +
                "    \"value_field\"    TEXT NOT NULL,\n" +
                "    PRIMARY KEY(\"id\" AUTOINCREMENT)\n" +
                ");");

        db.execSQL("DELETE FROM categories");
        db.execSQL("DELETE FROM products");
        db.execSQL("DELETE FROM products_pictures");
        db.execSQL("DELETE FROM users");

        db.execSQL("INSERT INTO \"categories\" (\"id\",\"name\",\"description\",\"picture_path\") VALUES (1,'Консоли','лёгкое, компактное, портативное электронное устройство, предназначенное для того, чтобы играть в видеоигры.','category_console'),\n" +
                " (2,'Диски','Игра, которая предназначена для игр на типа игровой консоли.','category_cds'),\n" +
                " (3,'Фигурки','коллекционная фигурка, обычно изготовленная из пластмассы.','category_figures'),\n" +
                " (4,'Брелки','аксессуар, украшение, выполненное в виде подвески на цепочке, браслете, кольце для ключей и другое.','category_brelocks'),\n" +
                " (5,'Комиксы','рисованная история, рассказ в картинках.','category_comixes');");

        db.execSQL("INSERT INTO \"products\" (\"id\",\"name\",\"price\",\"count_purchases\",\"description\",\"category_id\",\"count_left\") VALUES (1,'Dendy Steepler',1499,20,'Игровая приставка 8-bit Junior 2 Classic mini - классическая модель консоли типа Денди. Всё что необходимо для игры идёт в комплекте (без пистолета)! Гарантия на консоль Dendy - 3 месяца. На комплектующие - 1 месяц! В комплект 8-битной приставки входит: - игровая консоль, - два игровых джойстика (узкий разъём - 9 Pin) - блок питания (сетевой адаптер) - AV кабель - игровой картридж (сборник игр) - инструкция по эксплуатации - гарантийный талон Технические характеристики: Процессор 8 bit Питание 5 V, 300mA Разрешение 320x224 Количество цветов 512 Видео-выход PAL Звуковой выход стерео Совместимость с картриджами 8 bit Вес 770 гр.',1,40),\n" +
                " (2,'NINTENDO Switch New',22890,23,'Nintendo Switch – инновационная игровая консоль-гибрид. Ее не только можно подключить к телевизору, она также мгновенно превращается в портативную игровую систему с экраном 6,2 дюйма. Впервые игроки смогут наслаждаться масштабными игровыми проектами, типичными для домашних консолей, где угодно и когда угодно. Игровая консоль поддерживает amiibo и многопользовательскую локальную/онлайн игру на 8 человек.',1,39),\n" +
                " (3,'SEGA MEGA DRIVE 2 16bit',1800,30,'Игровая приставка Sega Mega Drive 2 – классика видеоигр. Она появилась много лет назад и смогла стать настоящей легендой.',1,20),\n" +
                " (4,'Mortal Kombat',700,50,'Вращающийся брелок с драконом',4,40),\n" +
                " (5,'Mario',650,55,'Брелок с Марио и Луиджи',4,30),\n" +
                " (6,'Sonic',675,52,'Брелок с Соником',4,18),\n" +
                " (7,'человек-павук',699,231,'человек-павук против доктора тентаклойда.',5,23423),\n" +
                " (8,'Гачи-мучи. Последний славянин.',300,89327647,'Давнее противостояние ♂️facking slave♂️ против его заклятого врага - ♂️dungeon master♂ должно закончиться сегодня️. Сможет ли гг дать ♂️fisting ass♂️ всем врагам и получить свои ♂️300 bucks♂️ обратно?',5,87),\n" +
                " (9,'Алексей. В поисках смысла.',3,1,'Комикс от неизвестного автора, который продолжает сюжет игры от неизвестного автора(другого автора, не того, кто нарисовал комикс). В общем одно сплошное гейство.',5,100000),\n" +
                " (10,'Пудж (Pudge) из игры Дота 2',1290,3,'Жирный, гладкий, приятный на вид ',3,13),\n" +
                " (11,'Соник с кольцом - Sonic the Hedgehog, Funko POP',3200,8,'Виниловая игровая фигурка FUNKO POP! – СОНИК станет замечательным подарком для любителей мультфильма «Соник». Дизайн выполнен в японском аниме-стиле «тиби» - персонажи с маленьким туловищем и большой головой Высокая детализация обеспечивает максимальное сходство с героем и позволяет сделать игровой процесс более увлекательным! Коллекционная фигурка понравится как детям, так и взрослым.Игровая фигурка FUNKO POP! способствует развитию воображения, умению играть и эмоциональному развитию. Размер фигурки около 9,6 см (в зависимости от аксессуаров) Создайте собственную коллекцию FUNKO POP! Рекомендовано для детей от 3 лет.',3,62),\n" +
                " (12,'Фигурка Марио из Super Mario Bros',800,17,'Герой всех времен, великий Марио, не теряет популярность ни на минуту. Каждый ребенок мечтает получить в подарок хотя бы одного героя из мультсериала Супер Марио. Производители пошли навстречу детским желаниям и создали набор из трех миниатюр Марио по старой цене! Создавайте головоломки, стройте замки, играйте вместе с Марио!',3,172),\n" +
                " (13,'Animal crossing',2000,3443,'игра на nintendo switch',2,5000),\n" +
                " (14,'Grand theft auto',1000,5000,'игра на PS one',2,5000),\n" +
                " (15,'Sonic',899,4342,'игра на SEGA MEGA DRIVE 2',2,5000);");

        db.execSQL("INSERT INTO \"products_pictures\" (\"id\",\"product_id\",\"picture_path\") VALUES (1,1,'dendy1'),\n" +
                " (2,1,'dendy2'),\n" +
                " (3,2,'nintendo1'),\n" +
                " (4,2,'nintendo2'),\n" +
                " (5,2,'nintendo3'),\n" +
                " (6,3,'sega1'),\n" +
                " (7,3,'sega2'),\n" +
                " (8,4,'mk1'),\n" +
                " (9,4,'mk2'),\n" +
                " (10,5,'mariob1'),\n" +
                " (11,5,'mariob2'),\n" +
                " (12,5,'mariob3'),\n" +
                " (13,6,'sonicb1'),\n" +
                " (14,7,'sp1'),\n" +
                " (15,8,'gachi1'),\n" +
                " (16,9,'asmysl'),\n" +
                " (17,10,'pudg1'),\n" +
                " (18,10,'pudg2'),\n" +
                " (19,10,'pudg3'),\n" +
                " (20,11,'sonick1'),\n" +
                " (21,11,'sonick2'),\n" +
                " (22,11,'sonick3'),\n" +
                " (23,12,'mariof1'),\n" +
                " (24,12,'mariof2'),\n" +
                " (25,12,'mariof3'),\n" +
                " (26,13,'animalcrossing1'),\n" +
                " (27,13,'animalcrossing2'),\n" +
                " (28,13,'animalcrossing3'),\n" +
                " (29,14,'gta1'),\n" +
                " (30,14,'gta2'),\n" +
                " (31,15,'sonicg1'),\n" +
                " (32,15,'sonicg2'),\n" +
                " (33,15,'sonicg3');");

        db.execSQL("INSERT INTO \"users\" (\"id\",\"login\",\"password\",\"name\",\"bdate\",\"phone\",\"email\") VALUES (1,'vik','123','Виктор',742710058,'8923732342','vik@mail.ru'),\n" +
                " (2,'shiki','342','Михаил',890198458,'8956732843','shik@yandex.ru'),\n" +
                " (3,'jopa','76378','Дмитрий',634368058,'8963742734','jojpa@mail.ru');");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
