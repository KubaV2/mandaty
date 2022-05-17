--liquibase formatted sql
--changeset jpirko:5
insert into person(id, pesel, first_name, last_name, email) values (1, '49102431442', 'Jan', 'Nowak', 'nowak@gmail.com');
insert into person(id, pesel, first_name, last_name, email) values (2, '94051913613', 'Andrzej', 'Duda', 'pis@gmail.com');
insert into offense(id, description) values (1, 'Zajmowanie więcej niż jednego, wyznaczonego na jezdni, pasa ruchu');
insert into offense(id, description) values (2, 'Naruszenie obowiązku poruszania się po poboczu przez kierującego pojazdem zaprzęgowym, rowerem, wózkiem rowerowym, motorowerem, wózkiem ręcznym albo osobę prowadzącą pojazd napędzany silnikiem');
insert into offense(id, description) values (3, 'Nieustąpienie miejsca nadjeżdżającemu pojazdowi szynowemu przez kierującego pojazdem');
insert into offense(id, description) values (4, 'Niekorzystanie z pasów bezpieczeństwa podczas jazdy');
insert into offense(id, description) values (5, 'Przewożenie dziecka w pojeździe poza fotelikiem ochronnym lub innym urządzeniem do przewożenia dzieci');
insert into offense(id, description) values (6, 'Przewożenie w foteliku ochronnym dziecka siedzącego tyłem do kierunku jazdy na przednim siedzeniu pojazdu wyposażonego w aktywną poduszkę powietrzną dla pasażera. (23 a,b,c): przewożenie dziecka do lat 3 w aucie niewyposażonym w pasy bezpieczeństwa i fotelik, dziecka o wzroście do 150 cm na przednim siedzeniu bez fotelika, przewożenie dziecka w nieprawidłowo zamontowanym foteliku');
insert into offense(id, description) values (7, 'Kierowanie pojazdem przewożącym pasażerów niekorzystających z pasów bezpieczeństwa');
insert into offense(id, description) values (8, 'Nieużywanie kasku ochronnego odpowiadającego właściwym warunkom technicznym podczas jazdy motocyklem, czterokołowcem lub motorowerem');
insert into offense(id, description) values (9, 'Kierowanie motocyklem, czterokołowcem lub motorowerem przewożącym pasażera nieużywającego kasku ochronnego odpowiadającego właściwym warunkom technicznym');
insert into offense(id, description) values (10, 'Przewożenie osoby w stanie nietrzeźwości, stanie po użyciu alkoholu lub środka działającego podobnie do alkoholu na rowerze lub motorowerze albo motocyklu poza bocznym wózkiem');
insert into offense(id, description) values (11, 'Korzystanie podczas jazdy z telefonu wymagające trzymania słuchawki lub mikrofonu w ręku przez kierującego pojazdem');
insert into offense(id, description) values (12, 'Wjeżdżanie na pas między jezdniami');
insert into offense(id, description) values (13, 'Niedopełnienie obowiązków przez uczestników wypadku poprzez nieusunięcie pojazdu z miejsca wypadku, w którym nie było zabitego lub rannego');
insert into offense(id, description) values (14, 'Nieużywanie elementów odblaskowych w sposób widoczny dla innych uczestników ruchu przez osobę wykonującą roboty lub inne czynności na drodze');
insert into offense(id, description) values (15, 'Nieustąpienie pierwszeństwa innemu pojazdowi lub uczestnikowi ruchu podczas włączania się do ruchu');
insert into offense(id, description) values (16, 'Uniemożliwienie włączenia się do ruchu autobusowi (trolejbusowi) sygnalizującemu zamiar zmiany pasa ruchu lub wjechania z zatoki na jezdnię z oznaczonego przystanku na obszarze zabudowanym');
insert into offense(id, description) values (17, 'Naruszenie obowiązków przez kierującego pojazdem zbliżającego się do miejsca postoju autobusu szkolnego');
insert into offense(id, description) values (18, 'Przekroczenie dopuszczalnej prędkości do 10 km/h');
insert into offense(id, description) values (19, 'Przekroczenie dopuszczalnej prędkości o 11-20 km/h');
insert into offense(id, description) values (20, 'Przekroczenie dopuszczalnej prędkości o 21-30 km/h');
insert into offense(id, description) values (21, 'Przekroczenie dopuszczalnej prędkości o 31-40 km/h');
insert into offense(id, description) values (22, 'Przekroczenie dopuszczalnej prędkości o 41-50 km/h');
insert into offense(id, description) values (23, 'Przekroczenie dopuszczalnej prędkości o 51 km/h i więcej');
insert into offense(id, description) values (24, 'Hamowanie w sposób powodujący zagrożenie bezpieczeństwa ruchu lub jego utrudnienie');
insert into offense(id, description) values (25, 'Jazda z prędkością utrudniającą ruch innym kierującym');
insert into offense(id, description) values (26, 'Niezachowanie przez kierujących pojazdami objętymi indywidualnym ograniczeniem prędkości albo pojazdami lub zespołami pojazdów o długości większej niż 7 metrów niezbędnego odstępu od pojazdów znajdujących się przed nimi');
insert into offense(id, description) values (27, 'Zawracanie w warunkach, w których mogłoby to zagrażać bezpieczeństwu ruchu lub ruch ten utrudnić');
insert into offense(id, description) values (28, 'Zawracanie w tunelach, na mostach i wiaduktach oraz na drogach jednokierunkowych');
insert into offense(id, description) values (29, 'Utrudnianie lub tamowanie ruchu poprzez niesygnalizowanie lub błędne sygnalizowanie manewru');
insert into offense(id, description) values (30, 'Nieodpowiednie ustawienie pojazdu na jezdni przed skręceniem');
insert into offense(id, description) values (31, 'Nieustąpienie pierwszeństwa przez kierującego pojazdem zmieniającego zajmowany pas ruchu');
insert into offense(id, description) values (32, 'Omijanie pojazdu, który jechał w tym samym kierunku, lecz zatrzymał się w celu ustąpienia pierwszeństwa pieszemu');
insert into offense(id, description) values (33, 'Omijanie pojazdu oczekującego na otwarcie ruchu przez przejazd kolejowy w sytuacji, w której wymagało to wjechania na część jezdni przeznaczoną dla przeciwnego kierunku ruchu');
insert into offense(id, description) values (34, 'Utrudnianie ruchu podczas cofania	');
insert into offense(id, description) values (35, 'Naruszenie zakazów cofania w tunelach, na mostach i wiaduktach');
insert into offense(id, description) values (36, 'Wyprzedzanie na przejściach dla pieszych lub bezpośrednio przed nimi');
insert into offense(id, description) values (37, 'Wyprzedzanie na przejazdach dla rowerzystów lub bezpośrednio przed nimi	');
insert into offense(id, description) values (38, 'Naruszenie przez kierującego obowiązku upewnienia się przed wyprzedzaniem, czy ma odpowiednią widoczność i dostateczne miejsce do wyprzedzania bez utrudnienia komukolwiek ruchu');
insert into offense(id, description) values (39, 'Naruszenie przez kierującego obowiązku upewnienia się przed wyprzedzaniem, czy kierujący jadący za nim nie rozpoczął manewru wyprzedzania');
insert into offense(id, description) values (40, 'Naruszenie zakazu wyprzedzania pojazdu silnikowego przy dojeżdżaniu do wierzchołka wzniesienia');
insert into offense(id, description) values (41, 'Naruszenie zakazu wyprzedzania na zakrętach oznaczonych znakami ostrzegawczymi');
insert into offense(id, description) values (42, 'Naruszenie zakazu wyprzedzania na skrzyżowaniach');
insert into offense(id, description) values (43, 'Wyprzedzanie na przejazdach kolejowych albo tramwajowych i bezpośrednio przed nimi');
insert into offense(id, description) values (44, 'Wyprzedzanie bez zachowania bezpiecznego odstępu od wyprzedzanego pojazdu lub uczestnika ruchu');
insert into offense(id, description) values (45, 'Wyprzedzanie z niewłaściwej strony');
insert into offense(id, description) values (46, 'Zwiększanie prędkości przez kierującego pojazdem wyprzedzanym');
insert into offense(id, description) values (48, 'Niestosowanie się przez kierującego pojazdem wolnobieżnym, ciągnikiem rolniczym lub pojazdem bez silnika do obowiązku zjechania jak najbardziej na prawo w celu ułatwienia wyprzedzenia');
insert into offense(id, description) values (49, 'Wyprzedzanie pojazdu uprzywilejowanego na obszarze zabudowanym');
insert into offense(id, description) values (50, 'Nieustąpienie przez kierującego pojazdem pierwszeństwa pieszym znajdującym się na przejściu');
insert into offense(id, description) values (51, 'Nieustąpienie przez kierującego pojazdem pierwszeństwa pieszym na skrzyżowaniu przy skręcaniu w drogę poprzeczną	');
insert into offense(id, description) values (52, 'Nieustąpienie przez kierującego pojazdem pierwszeństwa pieszym przy przejeżdżaniu przez chodnik lub drogę dla pieszych albo podczas jazdy po placu, na którym ruch pieszych i pojazdów odbywa się po tej samej powierzchni');
insert into offense(id, description) values (53, 'Nieustąpienie przez kierującego pojazdem pierwszeństwa rowerowi znajdującemu się na przejeździe dla rowerzystów');
insert into offense(id, description) values (54, 'Nieustąpienie przez kierującego pojazdem, który skręca w drogę poprzeczną, pierwszeństwa rowerzyście jadącemu na wprost po jezdni, pasie ruchu dla rowerów, drodze dla rowerów lub innej części drogi, którą zamierza opuścić');
insert into offense(id, description) values (55, 'Nieustąpienie przez kierującego pojazdem pierwszeństwa rowerowi przy przejeżdżaniu przez drogę dla rowerów poza jezdnią	');
insert into offense(id, description) values (56, 'Niezatrzymanie pojazdu w celu umożliwienia przejścia przez jezdnię osobie niepełnosprawnej, używającej specjalnego znaku lub osobie o widocznej ograniczonej sprawności ruchowej');
insert into offense(id, description) values (57, 'Naruszenie obowiązku usunięcia pojazdu unieruchomionego na przejeździe kolejowym (tramwajowym) lub ostrzegania kierujących pojazdami szynowymi w sytuacji, w której usunięcie pojazdu nie było możliwe');
insert into offense(id, description) values (58, 'Naruszenie obowiązku zatrzymania pojazdu w takim miejscu i na taki czas, aby umożliwić pieszym swobodny dostęp do pojazdów komunikacji publicznej – w przypadku braku wysepki dla pasażerów na przystanku');
insert into offense(id, description) values (59, 'Naruszenie zakazu objeżdżania opuszczonych zapór lub półzapór oraz wjeżdżania na przejazd, jeśli opuszczanie ich zostało rozpoczęte lub podnoszenie ich nie zostało zakończone');
insert into offense(id, description) values (60, 'Naruszenie zakazu wjeżdżania na przejazd, jeśli po jego drugiej stronie nie ma miejsca do kontynuowania jazdy');
insert into offense(id, description) values (61, 'Jazda wzdłuż po chodniku lub przejściu dla pieszych pojazdem silnikowym');
insert into offense(id, description) values (62, 'Jazda wzdłuż po chodniku lub przejściu dla pieszych innym pojazdem niż pojazd silnikowy');
insert into offense(id, description) values (63, 'Naruszenie przez kierującego zakazu wjeżdżania na skrzyżowanie, jeśli na skrzyżowaniu lub za nim nie ma miejsca do kontynuowania jazdy');
insert into offense(id, description) values (64, 'Niewłączenie przez kierującego pojazdem wymaganych świateł w czasie jazdy w warunkach zmniejszonej przejrzystości powietrza');
insert into offense(id, description) values (66, 'Naruszenie przez kierującego pojazdem innym niż silnikowy zakazu wyprzedzania innych pojazdów w czasie jazdy w warunkach zmniejszonej przejrzystości powietrza oraz obowiązku korzystania z pobocza drogi, a jeżeli nie jest to możliwe – to jazdy jak najbliżej krawędzi jezdni');
insert into offense(id, description) values (67, 'Nadużywanie sygnałów dźwiękowych lub świetlnych');
insert into offense(id, description) values (68, 'Używanie sygnałów dźwiękowych na obszarze zabudowanym');
insert into offense(id, description) values (69, 'Używanie tylnych świateł przeciwmgłowych przy normalnej przejrzystości powietrza');
insert into offense(id, description) values (70, 'Holowanie pojazdu, w którym znajduje się kierujący niemający wymaganych uprawnień do kierowania tym pojazdem');
insert into offense(id, description) values (71, 'Holowanie pojazdu na połączeniu sztywnym, jeżeli nie jest sprawny co najmniej jeden układ hamulcowy, albo na połączeniu giętkim, jeżeli nie są sprawne dwa układy hamulcowe');
insert into offense(id, description) values (72, 'Holowanie pojazdu o niesprawnym układzie kierowniczym lub niesprawnych hamulcach');
insert into offense(id, description) values (73, 'Holowanie pojazdu na połączeniu giętkim, jeżeli w pojeździe tym działanie układu hamulcowego uzależnione jest od pracy silnika, a silnik jest unieruchomiony');
insert into offense(id, description) values (74, 'Holowanie więcej niż jednego pojazdu, z wyjątkiem pojazdu członowego');
insert into offense(id, description) values (75, 'Holowanie pojazdem z przyczepą (naczepą)');
insert into offense(id, description) values (76, 'Naruszenie przez kierującego warunków holowania poprzez niewłączenie w pojeździe holującym świateł mijania');
insert into offense(id, description) values (77, 'Naruszenie przez kierującego warunków holowania poprzez nieprawidłowe połączenie pojazdu holowanego z holującym');
insert into offense(id, description) values (78, 'Naruszenie przez kierującego warunków holowania poprzez brak oznaczenia lub niewłaściwe oznaczenie pojazdu holowanego');
insert into offense(id, description) values (79, 'Naruszenie przez kierującego warunków holowania poprzez niezachowanie właściwej odległości między pojazdami holowanym a holującym');
insert into offense(id, description) values (80, 'Naruszenie przez kierującego warunków holowania poprzez niewłaściwe oznakowanie połączenia między pojazdami (holu) albo brak takiego oznakowania');
insert into offense(id, description) values (81, 'Naruszenie przez kierującego rowerem obowiązku przewożenia dziecka do lat 7 na dodatkowym siodełku');
insert into offense(id, description) values (82, 'Naruszenie przez kierującego rowerem obowiązku korzystania z drogi dla rowerów lub pasa ruchu dla rowerów, jeśli są one wyznaczone dla kierunku, w którym się porusza lub zamierza skręcić');
insert into offense(id, description) values (83, 'Utrudnianie poruszania się innym uczestnikom ruchu przez kierującego rowerem jadącego po jezdni obok innego roweru lub motoroweru');
insert into offense(id, description) values (84, 'Naruszenie przez kierującego rowerem lub motorowerem zakazu jazdy bez trzymania co najmniej jednej ręki na kierownicy oraz nóg na pedałach lub podnóżkach	');
insert into offense(id, description) values (85, 'Naruszenie przez kierującego rowerem lub motorowerem zakazu czepiania się pojazdów');
insert into offense(id, description) values (86, 'Naruszenie przez jeźdźca lub poganiacza obowiązku korzystania z określonej drogi lub części drogi');
insert into offense(id, description) values (87, 'Pędzenie zwierząt płochliwych, niesprawnych fizycznie lub niedających sobą kierować');
insert into offense(id, description) values (89, 'Naruszenie zakazu zawracania na autostradzie lub drodze ekspresowej');
insert into offense(id, description) values (90, 'Cofanie na autostradzie lub drodze ekspresowej');
insert into offense(id, description) values (91, 'Wjazd na autostradę lub drogę ekspresową pojazdem, dla ruchu którego drogi te nie są przeznaczone');
insert into offense(id, description) values (92, 'Zatrzymanie lub postój pojazdu na autostradzie lub drodze ekspresowej w innych miejscach niż wyznaczone w tym celu');
insert into offense(id, description) values (93, 'Nieusunięcie z jezdni pojazdu unieruchomionego z przyczyn technicznych oraz nieostrzeganie innych uczestników ruchu');
insert into offense(id, description) values (94, 'Niesygnalizowanie lub niewłaściwe sygnalizowanie postoju pojazdu silnikowego z powodu uszkodzenia lub wypadku na autostradzie lub drodze ekspresowej');
insert into offense(id, description) values (95, 'Zatrzymanie lub postój pojazdu w warunkach, w których nie jest on z dostatecznej odległości widoczny dla innych kierujących lub powoduje utrudnienie ruchu');
insert into offense(id, description) values (96, 'Zatrzymywanie pojazdu na przejeździe kolejowym lub tramwajowym, na skrzyżowaniu oraz w odległości mniejszej niż 10 m od przejazdu lub skrzyżowania');
insert into offense(id, description) values (97, 'Zatrzymywanie pojazdu na przejściu dla pieszych lub na przejeździe dla rowerzystów oraz w odległości mniejszej niż 10 m przed tym przejściem lub przejazdem, a na drodze dwukierunkowej o dwóch pasach ruchu – także za nimi');
insert into offense(id, description) values (98, 'Zatrzymywanie pojazdu w tunelu, na moście lub na wiadukcie');
insert into offense(id, description) values (99, 'Zatrzymywanie pojazdu na jezdni wzdłuż linii ciągłej oraz w pobliżu jej punktów krańcowych, jeżeli kierujący pojazdami wielośladowymi są zmuszeni do najeżdżania na tę linię');
insert into offense(id, description) values (100, 'Zatrzymywanie pojazdu na jezdni obok linii przerywanej wyznaczającej krawędź jezdni oraz na jezdni lub na poboczu obok linii ciągłej wyznaczającej krawędź jezdni');
insert into offense(id, description) values (101, 'Zatrzymywanie pojazdu w odległości mniejszej niż 10 m od przedniej strony znaku lub sygnału drogowego, jeżeli pojazd je zasłania');
insert into offense(id, description) values (102, 'Zatrzymywanie pojazdu przy lewej krawędzi jezdni');
insert into offense(id, description) values (103, 'Zatrzymywanie pojazdu na pasie między jezdniami');
insert into offense(id, description) values (104, 'Zatrzymywanie pojazdu w odległości mniejszej niż 15 m od słupka lub tablicy oznaczającej przystanek, a na przystanku z zatoką – na całej jej długości');
insert into offense(id, description) values (105, 'Zatrzymywanie pojazdu w odległości mniejszej niż 15 m od punktów krańcowych wysepki, jeżeli jezdnia z prawej jej strony ma tylko jeden pas ruchu');
insert into offense(id, description) values (106, 'Zatrzymywanie pojazdu na drodze dla rowerów, pasie ruchu dla rowerów lub w śluzie rowerowej, z wyjątkiem roweru');
insert into offense(id, description) values (107, 'Brak sygnalizowania lub niewłaściwe sygnalizowanie postoju z powodu uszkodzenia lub wypadku');
insert into offense(id, description) values (108, 'Naruszenie warunków dopuszczalności zatrzymania lub postoju pojazdu na chodniku');
insert into offense(id, description) values (109, 'Naruszenie obowiązku zatrzymania pojazdu na jezdni jak najbliżej jej krawędzi i równolegle do niej');
insert into offense(id, description) values (110, 'Naruszenie obowiązku umieszczania pojazdu (z wyjątkiem pojazdu zaprzęgowego) poza jezdnią w czasie postoju na drodze poza obszarem zabudowanym');
insert into offense(id, description) values (111, 'Naruszenie zakazu postoju w miejscach utrudniających wjazd lub wyjazd');
insert into offense(id, description) values (112, 'Naruszenie zakazu postoju w miejscach utrudniających dostęp do innego prawidłowo zaparkowanego pojazdu lub wyjazd tego pojazdu');
insert into offense(id, description) values (113, 'Naruszenie zakazu postoju przed lub za przejazdem kolejowym na odcinku od przejazdu do słupka wskaźnikowego z jedną kreską');
insert into offense(id, description) values (114, 'Naruszenie zakazu postoju w strefie zamieszkania w miejscach innych niż wyznaczone');
insert into offense(id, description) values (115, 'Jazda bez wymaganych świateł od zmierzchu do świtu');
insert into offense(id, description) values (116, 'Jazda bez wymaganych świateł od świtu do zmierzchu');
insert into offense(id, description) values (117, 'Jazda bez wymaganych świateł pojazdami niewyposażonymi w światła mijania, drogowe lub do jazdy dziennej');
insert into offense(id, description) values (118, 'Jazda bez wymaganych świateł w tunelu, niezależnie od pory doby');
insert into offense(id, description) values (119, 'Nieużywanie wymaganego oświetlenia podczas zatrzymania lub postoju w warunkach niedostatecznej widoczności');
insert into offense(id, description) values (120, 'Korzystanie ze świateł drogowych w sposób niezgodny z przepisami');
insert into offense(id, description) values (121, 'Naruszenie warunków dopuszczalności używania przednich świateł przeciwmgłowych');
insert into offense(id, description) values (122, 'Używanie „szperacza” podczas jazdy');
insert into offense(id, description) values (123, 'Bezpodstawne korzystanie z ułatwień w ruchu drogowym przez kierującego pojazdem uprzywilejowanym');
insert into offense(id, description) values (124, 'Używanie pojazdu w sposób zagrażający bezpieczeństwu osoby znajdującej się w pojeździe lub poza nim');
insert into offense(id, description) values (125, 'Zakrywanie świateł oraz urządzeń sygnalizacyjnych');
insert into offense(id, description) values (126, 'Zakrywanie tablic rejestracyjnych lub innych wymaganych tablic albo znaków, które powinny być widoczne');
insert into offense(id, description) values (127, 'Oddalenie się kierującego od pojazdu, gdy silnik jest w ruchu');
insert into offense(id, description) values (128, 'Używanie opon z umieszczonymi w nich na trwałe elementami przeciwślizgowymi (kolcami)');
insert into offense(id, description) values (129, 'Używanie łańcuchów przeciwślizgowych na oponach na drodze niepokrytej śniegiem');
insert into offense(id, description) values (130, 'Ozdabianie tablic rejestracyjnych oraz umieszczanie z przodu lub z tyłu pojazdu znaków, napisów lub przedmiotów ograniczających czytelność tych tablic');
insert into offense(id, description) values (131, 'Używanie pojazdu na obszarze zabudowanym w sposób powodujący uciążliwości związane z nadmierną emisją spalin do środowiska lub nadmiernym hałasem');
insert into offense(id, description) values (132, 'Pozostawienie pracującego silnika podczas postoju na obszarze zabudowanym');
insert into offense(id, description) values (133, 'Umieszczenie na pojeździe ładunku w sposób zasłaniający światła lub urządzenia sygnalizacyjne');
insert into offense(id, description) values (134, 'Ciągnięcie przyczepy o rzeczywistej masie całkowitej większej niż dopuszczalna w odniesieniu do określonego rodzaju pojazdu ciągnącego');
insert into offense(id, description) values (135, 'Przewóz osób w liczbie przekraczającej liczbę miejsc określonych w dowodzie rejestracyjnym pojazdu');
insert into offense(id, description) values (136, 'Kierowanie pojazdem silnikowym, z wyłączeniem czterokołowca lekkiego, lub tramwajem przez osobę niemającą uprawnienia do kierowania pojazdami');
insert into offense(id, description) values (137, 'Kierowanie pojazdem silnikowym, z wyłączeniem czterokołowca lekkiego, lub tramwajem przez osobę niemającą uprawnienia wymaganego dla danego rodzaju pojazdu');
insert into offense(id, description) values (138, 'Kierowanie pojazdem przez osobę nieposiadającą przy sobie wymaganych dokumentów');
insert into offense(id, description) values (139, 'Samowolne umieszczanie, usuwanie, włączanie, wyłączanie, zmiana położenia lub zasłanianie znaku, sygnału drogowego oraz urządzenia ostrzegawczo-zabezpieczającego lub kontrolnego');
insert into offense(id, description) values (140, 'Używanie pojazdu niszczącego drogę');
insert into offense(id, description) values (141, 'Dopuszczenie przez właściciela, posiadacza, użytkownika lub prowadzącego pojazd do jazdy na drodze publicznej, w strefie zamieszkania lub w strefie ruchu przez osobę bez uprawnień');
insert into offense(id, description) values (142, 'Zaśmiecanie lub zanieczyszczanie drogi publicznej');
insert into offense(id, description) values (143, 'Samowolne umieszczanie na drodze znaków, napisów lub symboli');
insert into offense(id, description) values (144, 'Naruszenie przepisów w sprawie okresowych ograniczeń oraz zakazu ruchu niektórych rodzajów pojazdów na drogach');
insert into offense(id, description) values (145, 'Posługiwanie się przez osobę do tego nieuprawnioną kartą parkingową dla osób niepełnosprawnych, w tym także ignorowanie poszczególnych ograniczeń i zakazów, z których zwolnione są osoby niepełnosprawne');
insert into offense(id, description) values (146, 'Niestosowanie się do znaków B-3 „zakaz wjazdu pojazdów silnikowych, z wyjątkiem motocykli jednośladowych”, B-3a „zakaz wjazdu autobusów”, B-4 „zakaz wjazdu motocykli”, B6 „zakaz wjazdu ciągników rolniczych” lub B-7 „zakaz wjazdu pojazdów silnikowych z przyczepą”');
insert into offense(id, description) values (147, 'Niestosowanie się do znaków B-13 do B-14 „zakaz wjazdu...”');
insert into offense(id, description) values (148, 'Niestosowanie się do znaków C-12 „ruch okrężny”');
insert into offense(id, description) values (150, 'Niestosowanie się do znaków B-5 „zakaz wjazdu samochodów ciężarowych”, B-18 „zakaz wjazdu pojazdów o rzeczywistej masie całkowitej ponad ....... t” lub B-19 „zakaz wjazdu pojazdów o nacisku osi większym niż ...... t”');
insert into offense(id, description) values (151, 'Niestosowanie się do znaków B-8 do B-12 „zakaz wjazdu...”');
insert into offense(id, description) values (152, 'Niestosowanie się do znaków B-29 „zakaz używania sygnałów dźwiękowych”');
insert into offense(id, description) values (153, 'Niestosowanie się do znaków B-31 „pierwszeństwo dla nadjeżdżających z przeciwka”');
insert into offense(id, description) values (154, 'Niestosowanie się do znaków B-32 „stój – kontrola celna” lub jego odpowiedników');
insert into offense(id, description) values (155, 'Niestosowanie się do znaków B-35 „zakaz postoju”');
