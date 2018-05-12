# Warehouse

Programozási Környezetek beadandó program a Debreceni Egyetem Informatikai Kar 2017/18 2. félévében. 

A program egy raktár applikációt reprezentál, amely 2 fő funkciót lát el.

Az első funkció az új csomag leadás. Csomagokat tudunk leadni, amelyekhez kötelezően meg kell adnunk a következő adatokat:
* **_feladó neve_**
* **_feladó lakcíme_**
* **_címzett neve_**
* **_szállítási cím_**
* **_csomag típusa_** 
* **_csomag súlya_** 

A csomag típusát a következők közül kell kiválasztanunk:
* **_szabványos_** 
* **_törékeny_** 
* **_veszélyes_**

A csomag típusa és súlya nagyban befolyásolja a várható szállítási időt. 
Alap esetben a szállítási idő 1 nap.
Amennyiben a csomag súlya nagyobb vagy egyenlő mint 50kg, akkor a várható szállítási idő + 1 nap.
Törékeny csomag esetén a szállítási idő + 1 nap, veszélyes csomag esetén a szállítási idő + 2 nap.
Így legjobb esetben a csomag szállítása 1 nap, legrosszabb esetben pedig 4 nap.

A második funkció a csomagok kezelése. Itt láthatjuk a már leadott csomagokat, szűrhetünk státusz és típus szerint.
Megváltoztathatjuk a csomag státuszát, végső esetben pedig törölhetjük a csomagot. 
Amennyiben a csomag már kiszállításra került, nem lehetséges a csomag törlése!

# A program használatához szükséges programok
A program telepítéséhez az `Apache Maven 3-as` verziója szükséges, valamint a program
futtatásához a `Java JDK 1.8-as` verziója szükséges!

Az adatbázissal való kapcsolat létrehozásához szükséges az `Oracle JDBC 8 Driver`, 
amely elérhető az alábbi linken: [OJDBC 8](http://www.oracle.com/technetwork/database/features/jdbc/jdbc-ucp-122-3110062.html)

Az `OJDBC 8` driver telepítése az alábbi parancs segítségével lehetséges: `mvn install:install-file -DgroupId=com.oracle -DartifactId=ojdbc8 -Dversion=12.2.0.1 -Dpackaging=jar -Dfile=ojdbc8.jar -DgeneratePom=true`
A parancs kiadása után az `OJDBC 8` driver a {user.home}/.m2 könyvtár alá kerül elhelyezésre.

A `Java JDK 1.8-as` verziója az alábbi linken elérhető: [JDK 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

Az `Apache Maven 3.5.2-es` verziója az alábbi linken elérhető : [Apache Maven 3.5.2](https://maven.apache.org/docs/3.5.2/release-notes.html)

# Használíti útmutató

Az applikáció használatához szükségünk van egy oracle felhasználói fiókra,
amely a Debreceni Egyetem Informatikai Karán használt ora12c adatbázisban regisztrálva van.

A program telepítése az `mvn clean install` paranccsal lehetséges a program főkönyvtárában.

A program futtatása a `/target` mappa alatt `java -jar Warehouse-1.0-jar-with-dependencies.jar` paranccsal lehetséges.

Az API dokumentáció és jelentések elkészíthetőek az `mvn site` paranccsal a program főkönyvtárában.

# Felhasznált technológiák
* **Apache Maven 3.5.2** 
* **Java 8** 
* **JavaFX** 
* **Java Hibernate JPA 2.1 with Oracle JDBC 8**