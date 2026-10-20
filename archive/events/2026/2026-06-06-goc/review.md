# Goč 2026 — pregled izvora, uparivanje i odluke

## Status i obim

Status: odluke prihvaćene; migracije 017/018 i javni podaci provjereni u izolovanim MySQL bazama. Pregledani su svi dostavljeni fajlovi, javni katalog, migracije 001–016, sedam javnih SCG tabela mjerioca i svih 19 stranica dodatnog pravilnika/biltena. Primijenjene migracije 001–016 nijesu mijenjane; dopune i ispravke su u novim migracijama.

Programer: Goč, 06–07.06.2026. Izvorni puni naziv: Nagrada Grada Kraljeva — Goč 2026. Detaljni obim je Šampionat Crne Gore (SCG); ne prepisivati druga prvenstva samo zato što su navedena linkom. Pravilnik uključuje administrativni 05.06; datumi takmičarskog vikenda ostaju programerovih 06–07.06.

## Prihvaćene odluke i konačne reference

Programer je potvrdio svih šest grupa pitanja i dodatno naveo da je Jovan u posebnoj klasi. Ovaj odjeljak i canonical* polja u reconciliation.json zamjenjuju početne predloge ispod.

- Dodaju se GORAN DOJČINOVIĆ (driver-goran-dojcinovic) i ENA PLAKALO (driver-ena-plakalo), novi klub ASU Zeka Auto sport, staza Goč i domaćin SAKSS. Postojećih 44 vozača se ponovo koriste.
- DURAKOVIĆ Sahib #119 je postojeći SAKIB DURAKOVIĆ; oba izvora navode Rover. EFEMKIN Dimitrii #217 je postojeći EFIMIN DMITRI; oba izvora navode BMW M3. Slažu se i klub, klasa i broj. Kanonska imena i archive_key vrijednosti ostaju neizmijenjeni, a Goč zapisi dobijaju izvorne alias-e. Nije utvrđen identitet fizičke šasije.
- AMK MONTE racing je AMK MONTERACING; AMKK LOVĆEN racing team je AMKK LOVĆEN R.T.; ASK TIVAT je AMSK TIVAT.
- Baćkova NORMA M20 koristi postojeći vehicle-norma-n202b, a Aldinov GLOBAL GT LIGHTS koristi vehicle-global. To su potvrđeni opisi za iste vozače. Božidarov zaseban GLOBAL LIGHT se ne spaja. Rover MG ZR 25 koristi vehicle-rover-mg; Peugeot 106 Rally koristi vehicle-peugeot-106. Honda i Yugo ranije potvrđena pravila se zadržavaju.
- Potvrđeno je da VW POLO i WV POLO nijesu različiti modeli. Nova migracija 017 premješta sve istorijske entries i identity_aliases sa vehicle-wv-polo na postojeći vehicle-vw-polo, zatim uklanja samo potvrđeni duplikat. Izvorno WV POLO ostaje u aliasima i originalnim dokumentima. Nijedan nastup, rezultat ili izvor nije obrisan.
- Goč izvorna oznaka E2 SH za #14 Baćka i #21 Aldina mapira se na postojeću D/E2/CN. #36 Jovan ostaje zasebna E2SH klasa, prema dodatnoj programerovoj potvrdi. Oznaka E2 SH ima dokumentovane alias-e uz tačan kontekst učesnika, ne globalno pravilo spajanja klasa. Moto 1200 je E1 1200.
- Plasmani unutar potvrđenih kanonskih klasa slijede relativni redoslijed u objavljenom zbiru. Zato Jovan u svojoj zasebnoj klasi ima mjesto 1, iako sirovi Goč CSV prikazuje 2 u zajedničkom izvornom E2 SH prikazu. Izvorni plasmani ostaju sačuvani, uključujući CSV i HTML. Nema preraspodjele ili izmišljanja bodova.
- Mugoša na Goču koristi Peugeot 106, a Petar Jovanović ASU Zeka. Njihovi Kotor nastupi ostaju prethodno evidentirani, osim izričito odobrene korekcije identiteta Pola.
- Goč je drugo CG kolo; zbir se prihvata kao FINALIZED. Objavljeni SCG bodovi nijesu dostavljeni i ostaju NULL. Odobreni scoring_system sezone iz 016 ostaje povezan. Nema izmišljenog klupskog poretka.
- #416 Damjan Perović: vehicle_id=NULL, pojedinačni DNS su očuvani, nezavršen zbir je DNF prema prethodnoj programerovoj odluci.
- Ena je završila obje Goč vožnje: trka_1 #666 2:00.590 (CSV konsolidovani line 178; HTML trka_1 row 24), trka_2 2:01.217 (line 227; HTML row 27), zbir 4:01.807 (line 272; HTML zbir row 26). U Kotor dokumentima nije evidentirana, pa nema njenog Kotor nastupa. U plaćenim treninzima nije navedena; za njih se ne dodaje izmišljeni DNS.
- Instrukcije AGENTS.md i docs/curating-events.md sada izričito traže provjeru vozila/modela uz broj, klub i klasu kod bliskog neslaganja imena, prije odluke o identitetu.

| Broj / izvorno ime | Driver key | Club key | Vehicle key | Class key |
| --- | --- | --- | --- | --- |
| 4; DOJČINOVIĆ Goran | driver-goran-dojcinovic | club-amsk-crmnica | vehicle-honda-civic | class-akscg-e1-1200 |
| 7; VUJAČIĆ Mirko | driver-mirko-vujacic | club-amsk-crmnica | vehicle-citroen-c2 | class-akscg-a-1600 |
| 12; BULATOVIĆ Dejan | driver-dejan-bulatovic | club-amsk-kotor | vehicle-peugeot-306 | class-akscg-h-2000 |
| 13; VUJNOVIĆ Miloš | driver-milos-vujnovic | club-amsk-herceg-novi | vehicle-peugeot-106 | class-akscg-n-1400 |
| 14; VUJOVIĆ Baćko | driver-backo-vujovic | club-amsk-gorstak | vehicle-norma-n202b | class-akscg-d-e2-cn |
| 16; VUČKOVIĆ Goran | driver-goran-vuckovic | club-amsk-kotor | vehicle-vw-golf-v | class-akscg-h-2000-plus |
| 17; ŠAPIĆ Ilija | driver-ilija-sapic | club-amsk-herceg-novi | vehicle-peugeot-106 | class-akscg-n-1400 |
| 18; KOVAČEVIĆ Đorđe | driver-dorde-kovacevic | club-amsk-herceg-novi | vehicle-peugeot-106 | class-akscg-n-1400 |
| 19; KOVAČEVIĆ Dejan | driver-dejan-kovacevic | club-amsk-herceg-novi | vehicle-vw-polo | class-akscg-n-1400 |
| 21; ĆOROVIĆ Aldin | driver-aldin-corovic | club-amsk-ap-sport | vehicle-global | class-akscg-d-e2-cn |
| 23; ADŽIĆ Marko | driver-marko-adzic | club-amsk-podi | vehicle-rover-mg | class-akscg-a-1400 |
| 26; TUJKOVIĆ Dušan | driver-dusan-tujkovic | club-amsk-kotor | vehicle-honda-civic | class-akscg-n-2000 |
| 29; MUGOŠA Slobodan | driver-slobodan-mugosa | club-amsk-ap-sport | vehicle-peugeot-106 | class-akscg-n-1600 |
| 30; JOVANOVIĆ Petar | driver-petar-jovanovic | club-asu-zeka-auto-sport | vehicle-yugo | class-akscg-a-1150 |
| 33; ŠUŠIĆ Gligorije | driver-gligorije-susic | club-amsk-podi | vehicle-citroen-saxo | class-akscg-n-1600 |
| 36; PERUNOVIĆ Jovan | driver-jovan-perunovic | club-amsk-podi | vehicle-fiat-750 | class-akscg-e2sh |
| 43; MASONIČIĆ Radovan | driver-radovan-masonicic | club-amsk-crmnica | vehicle-honda-civic | class-akscg-a-2000 |
| 48; IVANOVIĆ Marija | driver-marija-ivanovic | club-amk-monteracing | vehicle-citroen-saxo | class-akscg-a-1600 |
| 76; MILOŠEV Stevan | driver-stevan-milosev | club-amsk-herceg-novi | vehicle-yugo | class-akscg-n-1150 |
| 80; JANKOVIĆ Marko | driver-marko-jankovic | club-amsk-gorstak | vehicle-honda-civic | class-akscg-n-2000 |
| 81; RADANOVIĆ Vojo | driver-vojo-radanovic | club-amsk-herceg-novi | vehicle-peugeot-106 | class-akscg-h-1000 |
| 89; ĐONOVIĆ Dejan | driver-dejan-donovic | club-amsk-crmnica | vehicle-peugeot-106 | class-akscg-n-1400 |
| 92; DODEROVIĆ Božidar | driver-bozidar-doderovic | club-amsk-podi | vehicle-yugo | class-akscg-a-1150 |
| 115; KRIVOKAPIĆ Milutin | driver-milutin-krivokapic | club-amsk-herceg-novi | vehicle-peugeot-106 | class-akscg-a-1400 |
| 119; DURAKOVIĆ Sahib | driver-sakib-durakovic | club-amsk-crmnica | vehicle-rover | class-akscg-a-1400 |
| 121; ĐURIŠIĆ Andrija | driver-andrija-durisic | club-amsk-ap-sport | vehicle-yugo | class-akscg-h-1000 |
| 155; RADMILOVIĆ Igor | driver-igor-radmilovic | club-amsk-herceg-novi | vehicle-yugo | class-akscg-n-1150 |
| 160; BRKAN Luka | driver-luka-brkan | club-amsk-tivat | vehicle-yugo | class-akscg-n-1150 |
| 197; NIKČEVIĆ Sava | driver-sava-nikcevic | club-amsk-gorstak | vehicle-yugo | class-akscg-a-1150 |
| 200; DŽOGAZ Dragan | driver-dragan-dzogaz | club-amsk-ap-sport | vehicle-yugo | class-akscg-n-1150 |
| 208; ZUROVAC Petar | driver-petar-zurovac | club-amsk-nilovic-racing | vehicle-yugo | class-akscg-n-1150 |
| 217; EFEMKIN Dimitrii | driver-efimin-dmitri | club-amsk-crmnica | vehicle-bmw-m3 | class-akscg-h-2000-plus |
| 222; ŠUĆUR Luka | driver-luka-sucur | club-amsk-podi | vehicle-suzuki-swift-gti | class-akscg-h-1400 |
| 224; NIKOLIĆ Miljan | driver-miljan-nikolic | club-amsk-nilovic-racing | vehicle-peugeot-106 | class-akscg-n-1400 |
| 236; MUDREŠA Dragan | driver-dragan-mudresa | club-amsk-podi | vehicle-yugo | class-akscg-a-1150 |
| 287; RAŽNATOVIĆ Milorad | driver-milorad-raznatovic | club-amsk-tivat | vehicle-yugo | class-akscg-n-1150 |
| 310; BOJANOVIĆ Miloš | driver-milos-bojanovic | club-amsk-gorstak | vehicle-peugeot-106 | class-akscg-h-1600 |
| 313; MILINIĆ Stefan | driver-stefan-milinic | club-amsk-podi | vehicle-honda-civic | class-akscg-h-2000 |
| 353; MARKOVIĆ Matija | driver-matija-markovic | club-amkk-lovcen-r-t | vehicle-yugo | class-akscg-n-1150 |
| 416; PEROVIĆ Damjan | driver-damjan-perovic | club-amsk-kotor | NULL | class-akscg-h-1600 |
| 506; ĆOROVIĆ Almin | driver-almin-corovic | club-amsk-ap-sport | vehicle-opel-astra | class-akscg-a-2000 |
| 510; MILIĆEVIĆ Stevan | driver-stevan-milickovic | club-amsk-ap-sport | vehicle-yugo | class-akscg-n-1150 |
| 666; PLAKALO Ena | driver-ena-plakalo | club-amsk-ap-sport | vehicle-citroen-saxo | class-akscg-n-1600 |
| 681; VUČETIĆ Goran | driver-goran-vucetic | club-amsk-podi | vehicle-honda-civic | class-akscg-n-2000 |
| 930; RADANOVIĆ Radovan | driver-radovan-radanovic | club-amsk-herceg-novi | vehicle-opel-astra | class-akscg-a-2000 |
| 961; MALTSEVA Margarita | driver-margarita-maltseva | club-amsk-crmnica | vehicle-citroen-saxo | class-akscg-a-1600 |

## Početna pitanja — istorija pregleda, riješeno iznad

1. **new_drivers**: Dodati Goran DOJČINOVIĆ #4 (driver-goran-dojcinovic) i Ena PLAKALO #666 (driver-ena-plakalo).

2. **driver_119**: Da li je DURAKOVIĆ Sahib #119 isti SAKIB DURAKOVIĆ (driver-sakib-durakovic)? Isti broj, Crmnica, Rover i A 1400; kanonsko ime bez automatske izmjene.

3. **driver_217**: Da li je EFEMKIN Dimitrii #217 isti EFIMIN DMITRI (driver-efimin-dmitri)? Isti broj, Crmnica, BMW M3 i H 2000+; sačuvati stari kanonski zapis dok programer ne odluči.

4. **club_aliases**: Upariti AMK MONTE racing → AMK MONTERACING, AMKK LOVĆEN racing team → AMKK LOVĆEN R.T., ASK TIVAT → AMSK TIVAT?

5. **new_club**: Dodati ASU Zeka Auto sport za Petra Jovanovića #30, bez mijenjanja njegovog Kotor kluba AMSK PODI. Država/federacija kluba ostaje NULL dok nije potvrđena.

6. **vehicle_descriptions**: NORMA M20 ↔ NORMA N202B; GLOBAL GT LIGHTS ↔ GLOBAL (uz zaseban GLOBAL LIGHT u katalogu); Rover MG ZR 25 ↔ ROVER MG; Peugeot 106 Rally ↔ PEUGEOT 106; VolksWagen Polo ↔ WV POLO ili VW POLO. Odlučiti alias ili novi opis za svaku stavku.

7. **event_classifications**: Potvrditi Moto 1200 → postojeća E1 1200 i E2 SH → E2SH. Baćko i Aldin su u Goč izvoru E2 SH, a u Kotoru D/E2/CN; zadržati događajne klase prema izvorima.

8. **entry_changes**: Slobodan Mugoša #29: sada PEUGEOT 106, ranije CITROEN SAXO; Petar Jovanović sada ASU Zeka. Sačuvati nove događajne podatke i stare nastupe netaknute.

9. **track_host**: Dodati track-goc, Goč kod Kraljeva, RS, 2950 m; domaćin Sportski auto i karting savez Srbije (SAKSS). Povezati postojeću CG hillclimb sezonu 2026, kao jedan događaj; ne praviti rezultate drugih prvenstava.

10. **missing_optional**: Damjan Perović #416 ima prazan automobil i eksplicitne DNS u svim neplaćenim sesijama i zbiru: vehicle_id=NULL. Nema bodova niti zvaničnog klupskog poretka. Bodovi ostaju NULL; postojeći odobreni scoring_system sezone se ne mijenja.

11. **round_number**: Koji je broj kola Goča u CG sezoni 2026? Sljedeći unos u arhivi sam po sebi ne dokazuje broj kola.

12. **classification_finality**: Prihvatiti objavljeni Generalni plasman mjerioca kao FINALIZED ili ostaviti PROVISIONAL do potpisanog rezultata?

Rani predlozi ne predstavljaju konačnu migracijsku mapu. Posebni kandidati za Sahib/Dimitrii su privremeno odvojeni u proposedKey dok se identitet ne razriješi.

## Izbor dokumenata i uklanjanje suvišnog iz unosa

Nema identičnih bajtovnih duplikata. Postoji mnogo semantičkih duplikata: sedam izdvojenih sesijskih tabela potpuno odgovara odgovarajućim redovima u 01_svi_rezultati_SCG.csv; 02/03 su široki prikazi istih treninga/trka; 04/05/06 su pomoćni kontrolni popisi. Za unos je dovoljan konsolidovani CSV uz sačuvane primarne HTML izvore i dva PDF-a. Svaki rezultat unijeti jednom.

Originalni dostavljeni fajlovi su hashovani i ostaju sačuvani radi audit traga, u skladu s projektnim postupkom. Suvišne tabele su isključene iz predloga objave i ne proizvode dodatne rezultate. Nije dodan runtime importer. README i izvedeni JSON ostaju pomoćni razvojni izvori; ne registrovati ih kao javne dokumente.

Preuzeta su dva originalna PDF-a i sedam izvornih javnih HTML tabela u files/originals/. To su sačuvani izvorni bajtovi bez izmjene. HTML se čuva radi provjere, a eventualno preuzimanje kroz aplikaciju mora biti attachment. PublicationProposed u manifestu je predlog, ne stvarna registracija.

| Fajl | Bajtovi / redovi | Uloga | Predlog objave |
| --- | --- | --- | --- |
| 01_svi_rezultati_SCG.csv | 61686 / 291 | Konsolidovani dostavljeni CSV izvod: 245 pojedinačnih ishoda + 46 zbirnih klasifikacijskih redova. | da, transparentno označen izvor/izvod |
| 02_treninzi_za_import.csv | 4864 / 46 | Pomoćni široki prikaz već prisutnih redova; nije ulaz u runtime importer. | ne, pomoćni ili ponovljeni prikaz |
| 03_trke_za_import.csv | 4624 / 46 | Pomoćni široki prikaz već prisutnih redova; nije ulaz u runtime importer. | ne, pomoćni ili ponovljeni prikaz |
| 04_evidentirani_ucesnici_SCG.csv | 10026 / 46 | Kontrolni popis učesnika rekonstruisan iz rezultata; nije prijavna lista. | ne, pomoćni ili ponovljeni prikaz |
| 05_klubovi_SCG.csv | 2219 / 11 | Agregacija članstva/učesnika, nije bodovni poredak klubova. | ne, pomoćni ili ponovljeni prikaz |
| 06_klase_SCG.csv | 1700 / 15 | Agregacija broja učesnika po normalizovanoj klasi. | ne, pomoćni ili ponovljeni prikaz |
| 07_prvenstva_i_izvori.csv | 908 / 5 | Katalog linkova drugih prvenstava; ne daje njihove detaljne rezultate. | ne, pomoćni ili ponovljeni prikaz |
| 08_sesije.csv | 917 / 7 | Katalog šest vremenskih sesija i jedne zbirne klasifikacije. | ne, pomoćni ili ponovljeni prikaz |
| 09_podaci_o_trci.json | 1408 / — | Dostavljeni izvedeni metapodaci, provjereni prema pravilniku i biltenu. | ne, pomoćni ili ponovljeni prikaz |
| README.md | 2810 / — | Razvojna uputstva i metodologija dostavljenog paketa; ne objavljivati. | ne, pomoćni ili ponovljeni prikaz |
| generalni_plasman.csv | 10119 / 46 | Izdvojena tabela: sadržaj je potpuno ponovljen u konsolidovanom CSV-u. | ne, pomoćni ili ponovljeni prikaz |
| originals/bilten-1.pdf | 1144515 / — | Originalni Bilten br. 1: dopuna ŠCG i OPRS i ispravka dvije vožnje; 25.05.2026. | da, transparentno označen izvor/izvod |
| originals/generalni_plasman.html | 187570 / — | Originalni HTML snimak javne SCG tabele mjerioca, sačuvan radi provjere izvornog zapisa. | da, transparentno označen izvor/izvod |
| originals/placeni_trening_1.html | 171187 / — | Originalni HTML snimak javne SCG tabele mjerioca, sačuvan radi provjere izvornog zapisa. | da, transparentno označen izvor/izvod |
| originals/placeni_trening_2.html | 164077 / — | Originalni HTML snimak javne SCG tabele mjerioca, sačuvan radi provjere izvornog zapisa. | da, transparentno označen izvor/izvod |
| originals/pravilnik.pdf | 1918091 / — | Originalni dodatni pravilnik: događaj, domaćin, staza i program; odobren 18.05.2026. | da, transparentno označen izvor/izvod |
| originals/trening_1.html | 188597 / — | Originalni HTML snimak javne SCG tabele mjerioca, sačuvan radi provjere izvornog zapisa. | da, transparentno označen izvor/izvod |
| originals/trening_2.html | 187818 / — | Originalni HTML snimak javne SCG tabele mjerioca, sačuvan radi provjere izvornog zapisa. | da, transparentno označen izvor/izvod |
| originals/trka_1.html | 188502 / — | Originalni HTML snimak javne SCG tabele mjerioca, sačuvan radi provjere izvornog zapisa. | da, transparentno označen izvor/izvod |
| originals/trka_2.html | 187563 / — | Originalni HTML snimak javne SCG tabele mjerioca, sačuvan radi provjere izvornog zapisa. | da, transparentno označen izvor/izvod |
| placeni_trening_1.csv | 7352 / 33 | Izdvojena tabela: sadržaj je potpuno ponovljen u konsolidovanom CSV-u. | ne, pomoćni ili ponovljeni prikaz |
| placeni_trening_2.csv | 6277 / 28 | Izdvojena tabela: sadržaj je potpuno ponovljen u konsolidovanom CSV-u. | ne, pomoćni ili ponovljeni prikaz |
| trening_1.csv | 9818 / 46 | Izdvojena tabela: sadržaj je potpuno ponovljen u konsolidovanom CSV-u. | ne, pomoćni ili ponovljeni prikaz |
| trening_2.csv | 9665 / 46 | Izdvojena tabela: sadržaj je potpuno ponovljen u konsolidovanom CSV-u. | ne, pomoćni ili ponovljeni prikaz |
| trka_1.csv | 9660 / 46 | Izdvojena tabela: sadržaj je potpuno ponovljen u konsolidovanom CSV-u. | ne, pomoćni ili ponovljeni prikaz |
| trka_2.csv | 9611 / 46 | Izdvojena tabela: sadržaj je potpuno ponovljen u konsolidovanom CSV-u. | ne, pomoćni ili ponovljeni prikaz |

## Inventar i SHA-256

| Fajl | Izvorni ključ | SHA-256 |
| --- | --- | --- |
| 01_svi_rezultati_SCG.csv | 2026-06-06-goc-01-svi-rezultati-scg | 626a9bb5eba0a765e69a399fe64effb9c21e0913ed970777c0b55b245565d7f0 |
| 02_treninzi_za_import.csv | 2026-06-06-goc-02-treninzi-za-import | 0f97fa20023578a81fbde690e6ddca8a1ac222479ba1ee02c9f5ffec6acc8475 |
| 03_trke_za_import.csv | 2026-06-06-goc-03-trke-za-import | 87d8c382c6fcb96a377642a4cf6e7bbeb9e48b4f3c6a7e0b87acb89378149d00 |
| 04_evidentirani_ucesnici_SCG.csv | 2026-06-06-goc-04-evidentirani-ucesnici-scg | a5f99f150b19255a52eaa8f040f977e23c8442a5f64be58d8d5f8e786b86cbda |
| 05_klubovi_SCG.csv | 2026-06-06-goc-05-klubovi-scg | 748b1fc23312438747a710ed57318a41f72039ab5eeb97085cb71d1b26e19659 |
| 06_klase_SCG.csv | 2026-06-06-goc-06-klase-scg | ab579f21d0ad95ffa4acb2688d19a822c035e318a8f599f43dbea68eff920426 |
| 07_prvenstva_i_izvori.csv | 2026-06-06-goc-07-prvenstva-i-izvori | fd971c510e42ff7a7dc4fa0c252241035f24d20867085d34c79c3602be982950 |
| 08_sesije.csv | 2026-06-06-goc-08-sesije | ce41c4b2244638205e0ad217f1283305b6cba66b215fee86d9ad52d53baf87b5 |
| 09_podaci_o_trci.json | 2026-06-06-goc-09-podaci-o-trci | fef2e60585f5bf1321f989b77322c88adc625c442e1d9f3f0f0f23f0f9650d5a |
| README.md | 2026-06-06-goc-readme | 2ecca495036be928a3ffe6648f364ffe6541478ce2a1c22111ba4c88301b4566 |
| generalni_plasman.csv | 2026-06-06-goc-generalni-plasman | 37f50f10bc16567ef27cafd554eba56b764dee407eb32d54aa35a306748142e1 |
| originals/bilten-1.pdf | 2026-06-06-goc-originals-bilten-1 | 68cda9505c90b6100fcac6010d325ef928391c0eeebd294a5ba1618384601fe2 |
| originals/generalni_plasman.html | 2026-06-06-goc-originals-generalni-plasman | d4bfe95e4396ef22865a9fe5180b24647efc997289aa7c3ecbefb2981709ccc2 |
| originals/placeni_trening_1.html | 2026-06-06-goc-originals-placeni-trening-1 | 7b00cf758f4229ecd4bc7f0787886e2e04bff9ba0dfada33e975d66eaef54e50 |
| originals/placeni_trening_2.html | 2026-06-06-goc-originals-placeni-trening-2 | 4fefbc2c83416290f6870e20a2b78c525f3065c373df46941838773543e2b954 |
| originals/pravilnik.pdf | 2026-06-06-goc-originals-pravilnik | e72b724cad75f49c8f274a110091b03da3f9c38f70f7601c0a441e4ba57d1774 |
| originals/trening_1.html | 2026-06-06-goc-originals-trening-1 | a5b7c5015064b58948d6f15854f55461e788ed77247da903853e3ce8246224b0 |
| originals/trening_2.html | 2026-06-06-goc-originals-trening-2 | 017caf86aba0a74cea2b0e8b0233871c3ece8c6b647f1c187bf00ef53482e757 |
| originals/trka_1.html | 2026-06-06-goc-originals-trka-1 | 132f2199dc0a925425066b19b24e596a307d66813cc7b4a2eefb6a035b6f9a8a |
| originals/trka_2.html | 2026-06-06-goc-originals-trka-2 | 46728cab0c2b8345ab50aecd3df378efb46cb57ad737191813347c84a4eebb2e |
| placeni_trening_1.csv | 2026-06-06-goc-placeni-trening-1 | 3c6886984d72dfea64ae274c95286071121735bba7710cb5e550b6ec28e19874 |
| placeni_trening_2.csv | 2026-06-06-goc-placeni-trening-2 | 3dd508b0c32b7b0d09dce397e7f0506b397f99401c1d8753550678413451bdad |
| trening_1.csv | 2026-06-06-goc-trening-1 | 227b0154e2ac79067c80f832da4fe879897da964530d021883e7b2d69c73f493 |
| trening_2.csv | 2026-06-06-goc-trening-2 | b2d50e5a918a8e36d12140bb5803d17798d26cc3d9638c2d0ace952832c6294f |
| trka_1.csv | 2026-06-06-goc-trka-1 | f1f86b136b5df04633fc03b82a73ecfca09787d29db59914136a677eb53e6822 |
| trka_2.csv | 2026-06-06-goc-trka-2 | cef4abba0b54d6443a18c55960a0e2eea781e1fae5a7b60eac93ad813763fab2 |

Originalni URL-ovi, medijski tip, veličina, svrha i porijeklo svakog dokumenta su u event.json. Datum prikupljanja sajta je 20.10.2026, ne datum objave rezultata. PDF metadata Title pominje stare godine/organizatore; autoritet su vidljivi tekst, naziv dokumenta i odgovarajući događaj, ne zastarjeli metadata Title.

## Poređenje s postojećim arhivom

Read-only GET http://localhost:8080/public/catalog je vratio 190 identiteta: 133 vozača, 12 klubova, 28 vozila, 16 klasa i jednu stazu; 312 aliasa. Katalog se slaže sa 015 i potvrđenim Kotor mapiranjem. 016 je naknadno odobrio Modern hillclimb scoring system CG sezoni 2026; Goč unos ga ne uklanja niti mijenja. Nazivi se porede kao kandidati, ne kao jedinstveni ključevi identiteta.

42 imena imaju podržane postojeće kandidate; svaki red ispod navodi broj, klub/klasu/model i raniji izvor, uz zabilježene promjene. Dva nova kandidata nemaju podudaranje u katalogu. Dva različita zapisa imena su izdvojena za obaveznu odluku; nijesu spojena. Radovan Radanović #930 već postoji kao profil iz Kotor prijave, bez Kotor nastupa; za njega se ne pravi nova osoba.

## Svi vozači — mapiranje i dokazi

| Izvor: 04 CSV linija; broj | Tačan zapis sajta / dostavljeni CSV | Postojeći kandidat / novi predlog | Stanje i dokaz |
| --- | --- | --- | --- |
| 2; #4 | DOJČINOVIĆ Goran / DOJČINOVIĆ Goran | driver-goran-dojcinovic | proposed-new; nema postojećeg identiteta; klub=AMSK CRMNICA; klasa=Moto 1200; auto=Honda Civic; novi kandidat |
| 3; #7 | VUJAČIĆ Mirko / VUJAČIĆ Mirko | driver-mirko-vujacic | candidate-existing; isti startni broj, isti klub ili izdvojen kandidat aliasa, ista klasa, isti model ili izdvojen kandidat opisa; klub=AMSK CRMNICA; klasa=A 1600; auto=Citroen C2; R str. 1, kat. 1, #7 |
| 4; #12 | BULATOVIĆ Dejan / BULATOVIĆ Dejan | driver-dejan-bulatovic | candidate-existing; isti startni broj, isti klub ili izdvojen kandidat aliasa, ista klasa, isti model ili izdvojen kandidat opisa; klub=AMSK KOTOR; klasa=H 2000; auto=Peugeot 306; R str. 1, kat. 1, #12 |
| 5; #13 | VUJNOVIĆ Miloš / VUJNOVIĆ Miloš | driver-milos-vujnovic | candidate-existing; isti startni broj, isti klub ili izdvojen kandidat aliasa, ista klasa, isti model ili izdvojen kandidat opisa; klub=AMSK Herceg Novi; klasa=N 1400; auto=Peugeot 106; R str. 1, kat. 1, #13 |
| 6; #14 | VUJOVIĆ Baćko / VUJOVIĆ Baćko | driver-backo-vujovic | candidate-existing; isti startni broj, isti klub ili izdvojen kandidat aliasa, isti model ili izdvojen kandidat opisa; klub=AMSK GORŠTAK; klasa=E2 SH; auto=NORMA M20; R str. 3, kat. 2, #14 |
| 7; #16 | VUČKOVIĆ Goran / VUČKOVIĆ Goran | driver-goran-vuckovic | candidate-existing; isti startni broj, isti klub ili izdvojen kandidat aliasa, ista klasa, isti model ili izdvojen kandidat opisa; klub=AMSK KOTOR; klasa=H 2000+; auto=VolksWagen Golf 5; R str. 1, kat. 1, #16 |
| 8; #17 | ŠAPIĆ Ilija / ŠAPIĆ Ilija | driver-ilija-sapic | candidate-existing; isti startni broj, isti klub ili izdvojen kandidat aliasa, ista klasa, isti model ili izdvojen kandidat opisa; klub=AMSK Herceg Novi; klasa=N 1400; auto=Peugeot 106; R str. 1, kat. 1, #17 |
| 9; #18 | KOVAČEVIĆ Đorđe / KOVAČEVIĆ Đorđe | driver-dorde-kovacevic | candidate-existing; isti startni broj, isti klub ili izdvojen kandidat aliasa, ista klasa, isti model ili izdvojen kandidat opisa; klub=AMSK Herceg Novi; klasa=N 1400; auto=Peugeot 106; R str. 2, kat. 1, #18 |
| 10; #19 | KOVAČEVIĆ Dejan / KOVAČEVIĆ Dejan | driver-dejan-kovacevic | candidate-existing; isti startni broj, isti klub ili izdvojen kandidat aliasa, ista klasa, isti model ili izdvojen kandidat opisa; klub=AMSK Herceg Novi; klasa=N 1400; auto=VolksWagen Polo; R str. 1, kat. 1, #19 |
| 11; #21 | ĆOROVIĆ Aldin / ĆOROVIĆ Aldin | driver-aldin-corovic | candidate-existing; isti startni broj, isti klub ili izdvojen kandidat aliasa, isti model ili izdvojen kandidat opisa; klub=AMSK AP sport; klasa=E2 SH; auto=GLOBAL GT LIGHTS; R str. 3, kat. 2, #21 |
| 12; #23 | ADŽIĆ Marko / ADŽIĆ Marko | driver-marko-adzic | candidate-existing; isti startni broj, isti klub ili izdvojen kandidat aliasa, ista klasa, isti model ili izdvojen kandidat opisa; klub=AMSK PODI; klasa=A 1400; auto=Rover MG ZR 25; R str. 1, kat. 1, #23 |
| 13; #26 | TUJKOVIĆ Dušan / TUJKOVIĆ Dušan | driver-dusan-tujkovic | candidate-existing; isti startni broj, isti klub ili izdvojen kandidat aliasa, ista klasa, isti model ili izdvojen kandidat opisa; klub=AMSK KOTOR; klasa=N 2000; auto=Honda Civic; R str. 1, kat. 1, #26 |
| 14; #29 | MUGOŠA Slobodan / MUGOŠA Slobodan | driver-slobodan-mugosa | candidate-existing; isti startni broj, isti klub ili izdvojen kandidat aliasa, ista klasa; klub=AMSK AP sport; klasa=N 1600; auto=Peugeot 106; R str. 1, kat. 1, #29 |
| 15; #30 | JOVANOVIĆ Petar / JOVANOVIĆ Petar | driver-petar-jovanovic | candidate-existing; isti startni broj, ista klasa, isti model ili izdvojen kandidat opisa; klub=ASU Zeka Auto sport; klasa=A 1150; auto=Zastava Yugo 55; R str. 1, kat. 1, #30 |
| 16; #33 | ŠUŠIĆ Gligorije / ŠUŠIĆ Gligorije | driver-gligorije-susic | candidate-existing; isti startni broj, isti klub ili izdvojen kandidat aliasa, ista klasa, isti model ili izdvojen kandidat opisa; klub=AMSK PODI; klasa=N 1600; auto=Citroën Saxo; R str. 2, kat. 1, #33 |
| 17; #36 | PERUNOVIĆ Jovan / PERUNOVIĆ Jovan | driver-jovan-perunovic | candidate-existing; isti startni broj, isti klub ili izdvojen kandidat aliasa, ista klasa, isti model ili izdvojen kandidat opisa; klub=AMSK PODI; klasa=E2 SH; auto=Fiat 750; R str. 1, kat. 1, #36 |
| 18; #43 | MASONIČIĆ Radovan / MASONIČIĆ Radovan | driver-radovan-masonicic | candidate-existing; isti startni broj, isti klub ili izdvojen kandidat aliasa, ista klasa, isti model ili izdvojen kandidat opisa; klub=AMSK CRMNICA; klasa=A 2000; auto=Honda Civic Type R; R str. 1, kat. 1, #43 |
| 19; #48 | IVANOVIĆ Marija / IVANOVIĆ Marija | driver-marija-ivanovic | candidate-existing; isti startni broj, isti klub ili izdvojen kandidat aliasa, ista klasa, isti model ili izdvojen kandidat opisa; klub=AMK MONTE racing; klasa=A 1600; auto=Citroën Saxo; R str. 2, kat. 1, #48 |
| 20; #76 | MILOŠEV Stevan / MILOŠEV Stevan | driver-stevan-milosev | candidate-existing; isti startni broj, isti klub ili izdvojen kandidat aliasa, ista klasa, isti model ili izdvojen kandidat opisa; klub=AMSK Herceg Novi; klasa=N 1150; auto=Zastava Yugo 55; R str. 2, kat. 1, #76 |
| 21; #80 | JANKOVIĆ Marko / JANKOVIĆ Marko | driver-marko-jankovic | candidate-existing; isti startni broj, isti klub ili izdvojen kandidat aliasa, ista klasa, isti model ili izdvojen kandidat opisa; klub=AMSK GORŠTAK; klasa=N 2000; auto=Honda Civic; R str. 2, kat. 1, #80 |
| 22; #81 | RADANOVIĆ Vojo / RADANOVIĆ Vojo | driver-vojo-radanovic | candidate-existing; isti startni broj, isti klub ili izdvojen kandidat aliasa, ista klasa, isti model ili izdvojen kandidat opisa; klub=AMSK Herceg Novi; klasa=H 1000; auto=Peugeot 106; R str. 2, kat. 1, #81 |
| 23; #89 | ĐONOVIĆ Dejan / ĐONOVIĆ Dejan | driver-dejan-donovic | candidate-existing; isti startni broj, isti klub ili izdvojen kandidat aliasa, ista klasa, isti model ili izdvojen kandidat opisa; klub=AMSK CRMNICA; klasa=N 1400; auto=Peugeot 106 Rally; R str. 2, kat. 1, #89 |
| 24; #92 | DODEROVIĆ Božidar / DODEROVIĆ Božidar | driver-bozidar-doderovic | candidate-existing; isti startni broj, isti klub ili izdvojen kandidat aliasa, ista klasa, isti model ili izdvojen kandidat opisa; klub=AMSK PODI; klasa=A 1150; auto=Zastava Yugo 55; R str. 2, kat. 1, #92 |
| 25; #115 | KRIVOKAPIĆ Milutin / KRIVOKAPIĆ Milutin | driver-milutin-krivokapic | candidate-existing; isti startni broj, isti klub ili izdvojen kandidat aliasa, ista klasa, isti model ili izdvojen kandidat opisa; klub=AMSK Herceg Novi; klasa=A 1400; auto=Peugeot 106; R str. 1, kat. 1, #115 |
| 26; #119 | DURAKOVIĆ Sahib / DURAKOVIĆ Sahib | driver-sakib-durakovic | ambiguous-spelling; isti startni broj, isti klub ili izdvojen kandidat aliasa, ista klasa, isti model ili izdvojen kandidat opisa; klub=AMSK CRMNICA; klasa=A 1400; auto=Rover; R str. 2, kat. 1, #119 |
| 27; #121 | ĐURIŠIĆ Andrija / ĐURIŠIĆ Andrija | driver-andrija-durisic | candidate-existing; isti startni broj, isti klub ili izdvojen kandidat aliasa, ista klasa, isti model ili izdvojen kandidat opisa; klub=AMSK AP sport; klasa=H 1000; auto=Zastava Yugo 55; R str. 2, kat. 1, #121 |
| 28; #155 | RADMILOVIĆ Igor / RADMILOVIĆ Igor | driver-igor-radmilovic | candidate-existing; isti startni broj, isti klub ili izdvojen kandidat aliasa, ista klasa, isti model ili izdvojen kandidat opisa; klub=AMSK Herceg Novi; klasa=N 1150; auto=Zastava Yugo 55; R str. 2, kat. 1, #155 |
| 29; #160 | BRKAN Luka / BRKAN Luka | driver-luka-brkan | candidate-existing; isti startni broj, isti klub ili izdvojen kandidat aliasa, ista klasa, isti model ili izdvojen kandidat opisa; klub=ASK TIVAT; klasa=N 1150; auto=Zastava Yugo 55; R str. 2, kat. 1, #160 |
| 30; #197 | NIKČEVIĆ Sava / NIKČEVIĆ Sava | driver-sava-nikcevic | candidate-existing; isti startni broj, isti klub ili izdvojen kandidat aliasa, ista klasa, isti model ili izdvojen kandidat opisa; klub=AMSK GORŠTAK; klasa=A 1150; auto=Zastava Yugo 55; R str. 3, kat. 1, #197 |
| 31; #200 | DŽOGAZ Dragan / DŽOGAZ Dragan | driver-dragan-dzogaz | candidate-existing; isti startni broj, isti klub ili izdvojen kandidat aliasa, ista klasa, isti model ili izdvojen kandidat opisa; klub=AMSK AP sport; klasa=N 1150; auto=Zastava Yugo 55; R str. 2, kat. 1, #200 |
| 32; #208 | ZUROVAC Petar / ZUROVAC Petar | driver-petar-zurovac | candidate-existing; isti startni broj, isti klub ili izdvojen kandidat aliasa, ista klasa, isti model ili izdvojen kandidat opisa; klub=AMSK NILOVIĆ Racing; klasa=N 1150; auto=Zastava Yugo 55; R str. 3, kat. 1, #208 |
| 33; #217 | EFEMKIN Dimitrii / EFEMKIN Dimitrii | driver-efimin-dmitri | ambiguous-spelling; isti startni broj, isti klub ili izdvojen kandidat aliasa, ista klasa, isti model ili izdvojen kandidat opisa; klub=AMSK CRMNICA; klasa=H 2000+; auto=BMW M3; R str. 1, kat. 1, #217 |
| 34; #222 | ŠUĆUR Luka / ŠUĆUR Luka | driver-luka-sucur | candidate-existing; isti startni broj, isti klub ili izdvojen kandidat aliasa, ista klasa, isti model ili izdvojen kandidat opisa; klub=AMSK PODI; klasa=H 1400; auto=Suzuki Swift GTI; R str. 1, kat. 1, #222 |
| 35; #224 | NIKOLIĆ Miljan / NIKOLIĆ Miljan | driver-miljan-nikolic | candidate-existing; isti startni broj, isti klub ili izdvojen kandidat aliasa, ista klasa, isti model ili izdvojen kandidat opisa; klub=AMSK NILOVIĆ Racing; klasa=N 1400; auto=Peugeot 106 Rally; R str. 3, kat. 1, #224 |
| 36; #236 | MUDREŠA Dragan / MUDREŠA Dragan | driver-dragan-mudresa | candidate-existing; isti startni broj, isti klub ili izdvojen kandidat aliasa, ista klasa, isti model ili izdvojen kandidat opisa; klub=AMSK PODI; klasa=A 1150; auto=Zastava Yugo 55; R str. 3, kat. 1, #236 |
| 37; #287 | RAŽNATOVIĆ Milorad / RAŽNATOVIĆ Milorad | driver-milorad-raznatovic | candidate-existing; isti startni broj, isti klub ili izdvojen kandidat aliasa, ista klasa, isti model ili izdvojen kandidat opisa; klub=ASK TIVAT; klasa=N 1150; auto=Zastava Yugo 55; R str. 3, kat. 1, #287 |
| 38; #310 | BOJANOVIĆ Miloš / BOJANOVIĆ Miloš | driver-milos-bojanovic | candidate-existing; isti klub ili izdvojen kandidat aliasa, ista klasa, isti model ili izdvojen kandidat opisa; klub=AMSK GORŠTAK; klasa=H 1600; auto=Peugeot 106; R str. 1, kat. 1, #31 |
| 39; #313 | MILINIĆ Stefan / MILINIĆ Stefan | driver-stefan-milinic | candidate-existing; isti klub ili izdvojen kandidat aliasa, ista klasa, isti model ili izdvojen kandidat opisa; klub=AMSK PODI; klasa=H 2000; auto=Honda Civic; R str. 2, kat. 1, #113 |
| 40; #353 | MARKOVIĆ Matija / MARKOVIĆ Matija | driver-matija-markovic | candidate-existing; isti klub ili izdvojen kandidat aliasa, ista klasa, isti model ili izdvojen kandidat opisa; klub=AMKK LOVĆEN racing team; klasa=N 1150; auto=Zastava Yugo 55; R str. 2, kat. 1, #153 |
| 41; #416 | PEROVIĆ Damjan / PEROVIĆ Damjan | driver-damjan-perovic | candidate-existing; isti startni broj, isti klub ili izdvojen kandidat aliasa, ista klasa; klub=AMSK KOTOR; klasa=H 1600; auto=NULL; R str. 1, kat. 1, #416 |
| 42; #506 | ĆOROVIĆ Almin / ĆOROVIĆ Almin | driver-almin-corovic | candidate-existing; isti startni broj, isti klub ili izdvojen kandidat aliasa, ista klasa, isti model ili izdvojen kandidat opisa; klub=AMSK AP sport; klasa=A 2000; auto=Opel Astra; R str. 2, kat. 1, #506 |
| 43; #510 | MILIĆEVIĆ Stevan / MILIČKOVIĆ Stevan | driver-stevan-milickovic | candidate-existing; isti startni broj, isti klub ili izdvojen kandidat aliasa, ista klasa, isti model ili izdvojen kandidat opisa; klub=AMSK AP sport; klasa=N 1150; auto=Zastava Yugo 55; R str. 2, kat. 1, #510 |
| 44; #666 | PLAKALO Ena / PLAKALO Ena | driver-ena-plakalo | proposed-new; nema postojećeg identiteta; klub=AMSK AP sport; klasa=N 1600; auto=Citroën Saxo; novi kandidat |
| 45; #681 | VUČETIĆ Goran / VUČETIĆ Goran | driver-goran-vucetic | candidate-existing; isti klub ili izdvojen kandidat aliasa, ista klasa, isti model ili izdvojen kandidat opisa; klub=AMSK PODI; klasa=N 2000; auto=Honda Civic Type R; R str. 1, kat. 1, #68 |
| 46; #930 | RADANOVIĆ Radovan / RADANOVIĆ Radovan | driver-radovan-radanovic | candidate-existing; ranija prijava u Kotoru ima isti broj, klub i vozilo; profil već postoji bez Kotor nastupa; klub=AMSK Herceg Novi; klasa=A 2000; auto=Opel Astra; P str. 1, R.B. 58 |
| 47; #961 | MALTSEVA Margarita / MALTSEVA Margarita | driver-margarita-maltseva | candidate-existing; isti klub ili izdvojen kandidat aliasa, ista klasa, isti model ili izdvojen kandidat opisa; klub=AMSK CRMNICA; klasa=A 1600; auto=Citroën Saxo; R str. 2, kat. 1, #96 |

Za svaki CSV red i njegov tačan primarni HTML red, sa svim vremenima/statusima/plasmanima, vidjeti reconciliation.json/sourceRows. Službeni izvor za #510 svuda glasi MILIĆEVIĆ Stevan; dostavljeni CSV već je ispravljen u MILIČKOVIĆ Stevan. Ranija programerova odluka ostaje važeća; očuvati oba izvorna zapisa s objašnjenjem greške, bez nove osobe.

## Izvorna nova imena i prvobitno nejasni kandidati — odluke su iznad

| Broj | Tačan novi/različiti zapis | Privremeni novi ključ | Kandidat postojećeg identiteta | Dokaz / pitanje |
| --- | --- | --- | --- | --- |
| 4 | DOJČINOVIĆ Goran | driver-goran-dojcinovic | NULL | originals/generalni_plasman.html; table res-results-tbody row 7; #4; AMSK CRMNICA; Moto 1200; Honda Civic. Potvrditi novu osobu. |
| 119 | DURAKOVIĆ Sahib | driver-sahib-durakovic | driver-sakib-durakovic | originals/generalni_plasman.html; table res-results-tbody row 27; #119; AMSK CRMNICA; A 1400; Rover. Potvrditi istu ili različitu osobu i kanonski zapis; podudaranje više podataka nije konačna odluka. |
| 217 | EFEMKIN Dimitrii | driver-dimitrii-efemkin | driver-efimin-dmitri | originals/generalni_plasman.html; table res-results-tbody row 10; #217; AMSK CRMNICA; H 2000+; BMW M3. Potvrditi istu ili različitu osobu i kanonski zapis; podudaranje više podataka nije konačna odluka. |
| 666 | PLAKALO Ena | driver-ena-plakalo | NULL | originals/generalni_plasman.html; table res-results-tbody row 26; #666; AMSK AP sport; N 1600; Citroën Saxo. Potvrditi novu osobu. |

## Svi klubovi — potvrđeni kandidati i novi zapisi

| Tačno u izvoru | Kandidat / novi ključ | Postojeći naziv | Stanje / dokaz | Brojevi |
| --- | --- | --- | --- | --- |
| AMSK CRMNICA | club-amsk-crmnica | AMSK CRMNICA | supported-existing; Isti naziv uz razliku u veličini slova; potvrđenim učesnicima i istorijskim nastupima potkrijepljeno. | 4, 7, 43, 89, 119, 217, 961 |
| AMSK KOTOR | club-amsk-kotor | AMSK KOTOR | supported-existing; Isti naziv uz razliku u veličini slova; potvrđenim učesnicima i istorijskim nastupima potkrijepljeno. | 12, 16, 26, 416 |
| AMSK Herceg Novi | club-amsk-herceg-novi | AMSK HERCEG NOVI | supported-existing; Isti naziv uz razliku u veličini slova; potvrđenim učesnicima i istorijskim nastupima potkrijepljeno. | 13, 17, 18, 19, 76, 81, 115, 155, 930 |
| AMSK GORŠTAK | club-amsk-gorstak | AMSK GORŠTAK | supported-existing; Isti naziv uz razliku u veličini slova; potvrđenim učesnicima i istorijskim nastupima potkrijepljeno. | 14, 80, 197, 310 |
| AMSK AP sport | club-amsk-ap-sport | AMSK AP SPORT | supported-existing; Isti naziv uz razliku u veličini slova; potvrđenim učesnicima i istorijskim nastupima potkrijepljeno. | 21, 29, 121, 200, 506, 510, 666 |
| AMSK PODI | club-amsk-podi | AMSK PODI | supported-existing; Isti naziv uz razliku u veličini slova; potvrđenim učesnicima i istorijskim nastupima potkrijepljeno. | 23, 33, 36, 92, 222, 236, 313, 681 |
| ASU Zeka Auto sport | club-asu-zeka-auto-sport | NULL | proposed-new; Petar Jovanović #30 sada je prijavljen za ovaj klub; njegov Kotor nastup ostaje AMSK PODI. Država/federacija kluba nijesu pretpostavljene. | 30 |
| AMK MONTE racing | club-amk-monteracing | AMK MONTERACING | alias-confirmation; Ista vozačica Marija Ivanović #48, isti model i A 1600; drugačiji razmak u nazivu. | 48 |
| ASK TIVAT | club-amsk-tivat | AMSK TIVAT | ambiguous-alias; Luka Brkan #160 i Milorad Ražnatović #287, ista klasa i model; prefiks ASK umjesto AMSK čeka potvrdu. | 160, 287 |
| AMSK NILOVIĆ Racing | club-amsk-nilovic-racing | AMSK NILOVIĆ RACING | supported-existing; Isti naziv uz razliku u veličini slova; potvrđenim učesnicima i istorijskim nastupima potkrijepljeno. | 208, 224 |
| AMKK LOVĆEN racing team | club-amkk-lovcen-r-t | AMKK LOVĆEN R.T. | alias-confirmation; Matija Marković, Yugo i N 1150; puni racing team odgovara inicijalima R.T., ali čeka potvrdu. | 353 |

05_klubovi_SCG.csv opisuje broj učesnika po klubu. Nema klupskih bodova ili poretka; ne unositi broj članova kao bodove i ne rangirati klubove po njemu. ASU Zeka ostaje zaseban novi kandidat i ne zamjenjuje AMSK PODI na Kotor nastupu Petra Jovanovića.

## Izvorni opisi vozila i raniji kandidati — konačni ključevi su iznad

| Tačno u izvoru | Postojeći kandidat / naziv | Novi ključ ako se odbije uparivanje | Stanje i razlog | Brojevi |
| --- | --- | --- | --- | --- |
| Honda Civic | vehicle-honda-civic / HONDA CIVIC | NULL | supported-existing; Doslovni model uz veličinu slova/dijakritiku; opis modela ne tvrdi fizičku šasiju. | 4, 26, 80, 313 |
| Citroen C2 | vehicle-citroen-c2 / CITROEN C2 | NULL | supported-existing; Doslovni model uz veličinu slova/dijakritiku; opis modela ne tvrdi fizičku šasiju. | 7 |
| Peugeot 306 | vehicle-peugeot-306 / PEUGEOT 306 | NULL | supported-existing; Doslovni model uz veličinu slova/dijakritiku; opis modela ne tvrdi fizičku šasiju. | 12 |
| Peugeot 106 | vehicle-peugeot-106 / PEUGEOT 106 | NULL | supported-existing; Doslovni model uz veličinu slova/dijakritiku; opis modela ne tvrdi fizičku šasiju. | 13, 17, 18, 29, 81, 115, 310 |
| NORMA M20 | vehicle-norma-n202b / NORMA N202B | vehicle-norma-m20 | ambiguous-model; Baćko Vujović #14; raniji izvor NORMA N202B. Ne dokazuje istu specifikaciju ili šasiju. | 14 |
| VolksWagen Golf 5 | vehicle-vw-golf-v / VW GOLF V | NULL | alias-confirmation; Goran Vučković #16, isti klub i klasa; puni naziv proizvođača i arapska oznaka generacije naspram VW GOLF V. | 16 |
| VolksWagen Polo | vehicle-wv-polo / WV POLO | NULL | ambiguous-catalog-candidates; Dejan Kovačević #19 je u Kotoru WV POLO; katalog istovremeno sadrži zaseban VW POLO. Potvrditi izbor bez prepisivanja Kotor podataka. | 19 |
| GLOBAL GT LIGHTS | vehicle-global / GLOBAL | vehicle-global-gt-lights | ambiguous-model; Aldin Ćorović #21; ranije GLOBAL. U katalogu postoji i zaseban GLOBAL LIGHT (Božidar Jablan); ne spajati sve tri oznake automatski. | 21 |
| Rover MG ZR 25 | vehicle-rover-mg / ROVER MG | vehicle-rover-mg-zr-25 | more-specific-description; Marko Adžić #23; isti klub i A 1400, ranije ROVER MG. Potvrditi da li ovaj duži naziv postaje alias ili novi opis. | 23 |
| Zastava Yugo 55 | vehicle-yugo / YUGO | NULL | previously-approved-model-policy; Programer je ranije odobrio YUGO bez varijanti; očuvati puni izvorni zapis kao alias. | 30, 76, 92, 121, 155, 160, 197, 200, 208, 236, 287, 353, 510 |
| Citroën Saxo | vehicle-citroen-saxo / CITROEN SAXO | NULL | supported-existing; Doslovni model uz veličinu slova/dijakritiku; opis modela ne tvrdi fizičku šasiju. | 33, 48, 666, 961 |
| Fiat 750 | vehicle-fiat-750 / FIAT 750 | NULL | supported-existing; Doslovni model uz veličinu slova/dijakritiku; opis modela ne tvrdi fizičku šasiju. | 36 |
| Honda Civic Type R | vehicle-honda-civic / HONDA CIVIC | NULL | previously-approved-model-policy; Programer je ranije odobrio HONDA CIVIC bez Type R; ovaj izvorni zapis ostaje alias. | 43, 681 |
| Peugeot 106 Rally | vehicle-peugeot-106 / PEUGEOT 106 | vehicle-peugeot-106-rally | more-specific-description; Dejan Đonović #89 i Miljan Nikolić #224; ranije PEUGEOT 106. Ne zaključivati specifikaciju bez odluke. | 89, 224 |
| Rover | vehicle-rover / ROVER | NULL | supported-existing; Doslovni model uz veličinu slova/dijakritiku; opis modela ne tvrdi fizičku šasiju. | 119 |
| BMW M3 | vehicle-bmw-m3 / BMW M3 | NULL | supported-existing; Doslovni model uz veličinu slova/dijakritiku; opis modela ne tvrdi fizičku šasiju. | 217 |
| Suzuki Swift GTI | vehicle-suzuki-swift-gti / SUZUKI SWIFT GTI | NULL | supported-existing; Doslovni model uz veličinu slova/dijakritiku; opis modela ne tvrdi fizičku šasiju. | 222 |
| Opel Astra | vehicle-opel-astra / OPEL ASTRA | NULL | supported-existing; Doslovni model uz veličinu slova/dijakritiku; opis modela ne tvrdi fizičku šasiju. | 506, 930 |

Za #416 vozilo nije navedeno u četiri neplaćene sesije/zbiru i ostaje NULL; ne preuzimati Hondu iz Kotor nastupa. Slobodan Mugoša #29 sada ima Peugeot 106, a ranije Citroen Saxo; čuvati događajnu promjenu. Opisi vozila ne dokazuju fizičku šasiju. Honda/Yugo ranije odobrena pravila naziva ostaju važeća.

## Klase i promjene između događaja

| Izvorna klasa | Normalizacija dostavljenog CSV-a | Postojeći kandidat | Stanje | Brojevi |
| --- | --- | --- | --- | --- |
| A 1150 | A 1150 | class-akscg-a-1150 | supported-existing | 30, 92, 197, 236 |
| A 1400 | A 1400 | class-akscg-a-1400 | supported-existing | 23, 115, 119 |
| A 1600 | A 1600 | class-akscg-a-1600 | supported-existing | 7, 48, 961 |
| A 2000 | A 2000 | class-akscg-a-2000 | supported-existing | 43, 506, 930 |
| E2 SH | E2SH | class-akscg-e2sh | alias-and-entry-class-confirmation | 14, 21, 36 |
| H 1000 | H 1000 | class-akscg-h-1000 | supported-existing | 81, 121 |
| H 1400 | H 1400 | class-akscg-h-1400 | supported-existing | 222 |
| H 1600 | H 1600 | class-akscg-h-1600 | supported-existing | 310, 416 |
| H 2000 | H 2000 | class-akscg-h-2000 | supported-existing | 12, 313 |
| H 2000+ | H 2000+ | class-akscg-h-2000-plus | supported-existing | 16, 217 |
| Moto 1200 | E1 1200 | class-akscg-e1-1200 | normalization-needs-confirmation | 4 |
| N 1150 | N 1150 | class-akscg-n-1150 | supported-existing | 76, 155, 160, 200, 208, 287, 353, 510 |
| N 1400 | N 1400 | class-akscg-n-1400 | supported-existing | 13, 17, 18, 19, 89, 224 |
| N 1600 | N 1600 | class-akscg-n-1600 | supported-existing | 29, 33, 666 |
| N 2000 | N 2000 | class-akscg-n-2000 | supported-existing | 26, 80, 681 |

Nema potrebe za novim klasama ako se potvrde dvije postojeće normalizacije. E2 SH je razmak naspram E2SH; Moto 1200 → E1 1200 je semantičko mapiranje koje je autor CSV-a već napravio i programer treba da potvrdi. Baćko Vujović #14 i Aldin Ćorović #21 na Goču su E2 SH; njihovi Kotor nastupi ostaju D/E2/CN. Ne ispravljati istoriju samo zato što je kasniji zapis drugačiji.

## Predlog nove staze i domaćina

| Vrsta | Predlog | Izvor |
| --- | --- | --- |
| Staza | track-goc; Goč; Goč/Kraljevo; RS; 2950 m | originals/pravilnik.pdf str. 5 čl. 4.1–4.2; str. 15 skica. Visinska razlika 205 m, prosječni uspon 6,95%, maksimalni 9,34%. |
| Domaćin | Sportski auto i karting savez Srbije (SAKSS), RS | Pravilnik str. 1 i 4 čl. 1; str. 5 čl. 3.2 odobrenje 121 od 18.05.2026. Nova federacija, ne klub takmičara. |
| Organizator | ASU MLAZ racing team iz Kraljeva | Pravilnik str. 4 čl. 1. Ne kreirati ga kao pripadnost vozača na osnovu organizatorske uloge. |
| Sezona | Postojeći Šampionat Crne Gore 2026 / HILLCLIMB | Bilten br. 1 str. 2 čl. 3.1 i 5.4; str. 4 dopuna 28.3; postojeći roditelji iz 015/016. |
| Događaj | 2026-06-06-goc; Nagrada Grada Kraljeva — Goč 2026 | Ime/datum programera i naslov javnog sajta. Redni broj u CG sezoni nije dokazan kalendarom; čeka programerovu potvrdu i ostaje neodređen u predlogu. |
| Druga prvenstva | NSS, OPS, FIA CEZ, OPRS i CEZ historic u pravilniku | Jedan zajednički događaj, ne više kopija. Detaljan unos ovog paketa obuhvata SCG; druge sezone/rezultate ne kreirati iz linkova ili tuđih brojčanih klasa. |

## Sesije, zbir i kontrola rezultata

| Tabela | Datum | FIN / DNF / DNS | Ukupno | Uloga |
| --- | --- | --- | --- | --- |
| placeni_trening_1 | 2026-06-06 | 30 / 3 / 0 | 33 | pojedinačna vremenska sesija |
| placeni_trening_2 | 2026-06-06 | 28 / 0 / 0 | 28 | pojedinačna vremenska sesija |
| trening_1 | 2026-06-06 | 43 / 2 / 1 | 46 | pojedinačna vremenska sesija |
| trening_2 | 2026-06-06 | 34 / 0 / 12 | 46 | pojedinačna vremenska sesija |
| trka_1 | 2026-06-07 | 42 / 3 / 1 | 46 | pojedinačna vremenska sesija |
| trka_2 | 2026-06-07 | 39 / 1 / 6 | 46 | pojedinačna vremenska sesija |
| generalni_plasman | 2026-06-07 | 39 / 6 / 1 | 46 | klasifikacija, nije vožnja |

Ukupno 245 pojedinačnih ishoda u šest vremenskih sesija i 46 zbirnih redova. Plaćeni treninzi nisu suvišni i ne brišu se iz sadržaja događaja: njihovi 33/28 zapisa nisu u redovnim treninzima. Odsustvo ostalih iz plaćenog treninga nije DNS. Warm-up je predviđen programom, ali nema dostavljenih niti objavljenih rezultata u provjerenom paketu; ne izmišljati da je održan.

Za svih 39 FIN zbirnih redova provjeren je tačan zbir obje vožnje. Sva vremena, statusi, plasmani i pojedinosti učesnika odgovaraju javnom sajtu na svih 291 red, osim već potvrđenog prezimena #510. Sva milisekundska polja odgovaraju prikaznom vremenu. Klasni plasmani iz dostavljenog CSV-a tačno odgovaraju relativnom poretku FIN učesnika iste klase u objavljenom ukupnom poretku; sajt ne prikazuje posebnu potpisanu klasnu tabelu.

Sedam sajtom objavljenih zbirnih nezavršenih ishoda: #12, #17, #21, #224, #313, #681 DNF; #416 DNS. Ranija programerova odluka o ukupnom DNF za nezavršen zbir može se primijeniti uz javnu napomenu i očuvan izvorni DNS, ali izvorna vrijednost mora ostati zabilježena; nema izmišljanja vremena.

## Svi zbirni ishodi i njihove dvije vožnje

| Broj / izvorno ime | Trka 1 | Trka 2 | Objavljen zbir/status | Plasman ukupni / u klasi | Lokatori |
| --- | --- | --- | --- | --- | --- |
| #14; VUJOVIĆ Baćko | 1:32.956 | 1:35.000 | 3:07.956 | 1 / 1 | generalni_plasman.csv line 2; trka_1.csv line 2; trka_2.csv line 2 |
| #36; PERUNOVIĆ Jovan | 1:39.622 | 1:38.026 | 3:17.648 | 2 / 2 | generalni_plasman.csv line 3; trka_1.csv line 4; trka_2.csv line 3 |
| #16; VUČKOVIĆ Goran | 1:42.806 | 1:43.522 | 3:26.328 | 3 / 1 | generalni_plasman.csv line 4; trka_1.csv line 6; trka_2.csv line 5 |
| #80; JANKOVIĆ Marko | 1:43.566 | 1:43.657 | 3:27.223 | 4 / 1 | generalni_plasman.csv line 5; trka_1.csv line 7; trka_2.csv line 6 |
| #7; VUJAČIĆ Mirko | 1:44.523 | 1:42.763 | 3:27.286 | 5 / 1 | generalni_plasman.csv line 6; trka_1.csv line 8; trka_2.csv line 4 |
| #310; BOJANOVIĆ Miloš | 1:44.986 | 1:44.243 | 3:29.229 | 6 / 1 | generalni_plasman.csv line 7; trka_1.csv line 10; trka_2.csv line 7 |
| #4; DOJČINOVIĆ Goran | 1:45.034 | 1:44.759 | 3:29.793 | 7 / 1 | generalni_plasman.csv line 8; trka_1.csv line 11; trka_2.csv line 8 |
| #26; TUJKOVIĆ Dušan | 1:44.701 | 1:45.691 | 3:30.392 | 8 / 2 | generalni_plasman.csv line 9; trka_1.csv line 9; trka_2.csv line 9 |
| #43; MASONIČIĆ Radovan | 1:48.272 | 1:48.078 | 3:36.350 | 9 / 1 | generalni_plasman.csv line 10; trka_1.csv line 12; trka_2.csv line 10 |
| #217; EFEMKIN Dimitrii | 1:49.764 | 1:49.597 | 3:39.361 | 10 / 2 | generalni_plasman.csv line 11; trka_1.csv line 13; trka_2.csv line 11 |
| #23; ADŽIĆ Marko | 1:49.935 | 1:50.469 | 3:40.404 | 11 / 1 | generalni_plasman.csv line 12; trka_1.csv line 14; trka_2.csv line 12 |
| #29; MUGOŠA Slobodan | 1:52.510 | 1:51.726 | 3:44.236 | 12 / 1 | generalni_plasman.csv line 13; trka_1.csv line 15; trka_2.csv line 13 |
| #89; ĐONOVIĆ Dejan | 1:53.941 | 1:53.542 | 3:47.483 | 13 / 1 | generalni_plasman.csv line 14; trka_1.csv line 17; trka_2.csv line 14 |
| #115; KRIVOKAPIĆ Milutin | 1:52.731 | 1:56.553 | 3:49.284 | 14 / 2 | generalni_plasman.csv line 15; trka_1.csv line 16; trka_2.csv line 19 |
| #19; KOVAČEVIĆ Dejan | 1:54.809 | 1:54.799 | 3:49.608 | 15 / 2 | generalni_plasman.csv line 16; trka_1.csv line 19; trka_2.csv line 16 |
| #13; VUJNOVIĆ Miloš | 1:54.299 | 1:55.384 | 3:49.683 | 16 / 3 | generalni_plasman.csv line 17; trka_1.csv line 18; trka_2.csv line 18 |
| #236; MUDREŠA Dragan | 1:55.116 | 1:54.622 | 3:49.738 | 17 / 1 | generalni_plasman.csv line 18; trka_1.csv line 20; trka_2.csv line 15 |
| #92; DODEROVIĆ Božidar | 1:56.038 | 1:55.380 | 3:51.418 | 18 / 2 | generalni_plasman.csv line 19; trka_1.csv line 22; trka_2.csv line 17 |
| #506; ĆOROVIĆ Almin | 1:55.573 | 1:56.811 | 3:52.384 | 19 / 2 | generalni_plasman.csv line 20; trka_1.csv line 21; trka_2.csv line 20 |
| #48; IVANOVIĆ Marija | 1:57.305 | 1:59.137 | 3:56.442 | 20 / 2 | generalni_plasman.csv line 21; trka_1.csv line 23; trka_2.csv line 23 |
| #30; JOVANOVIĆ Petar | 1:57.761 | 1:59.102 | 3:56.863 | 21 / 3 | generalni_plasman.csv line 22; trka_1.csv line 24; trka_2.csv line 22 |
| #197; NIKČEVIĆ Sava | 2:02.730 | 1:57.029 | 3:59.759 | 22 / 4 | generalni_plasman.csv line 23; trka_1.csv line 31; trka_2.csv line 21 |
| #121; ĐURIŠIĆ Andrija | 2:00.592 | 1:59.393 | 3:59.985 | 23 / 1 | generalni_plasman.csv line 24; trka_1.csv line 26; trka_2.csv line 24 |
| #961; MALTSEVA Margarita | 2:01.323 | 1:59.681 | 4:01.004 | 24 / 3 | generalni_plasman.csv line 25; trka_1.csv line 27; trka_2.csv line 26 |
| #222; ŠUĆUR Luka | 2:01.474 | 1:59.583 | 4:01.057 | 25 / 1 | generalni_plasman.csv line 26; trka_1.csv line 28; trka_2.csv line 25 |
| #666; PLAKALO Ena | 2:00.590 | 2:01.217 | 4:01.807 | 26 / 2 | generalni_plasman.csv line 27; trka_1.csv line 25; trka_2.csv line 28 |
| #119; DURAKOVIĆ Sahib | 2:02.638 | 2:00.304 | 4:02.942 | 27 / 3 | generalni_plasman.csv line 28; trka_1.csv line 30; trka_2.csv line 27 |
| #33; ŠUŠIĆ Gligorije | 2:02.614 | 2:01.222 | 4:03.836 | 28 / 3 | generalni_plasman.csv line 29; trka_1.csv line 29; trka_2.csv line 29 |
| #155; RADMILOVIĆ Igor | 2:03.696 | 2:02.877 | 4:06.573 | 29 / 1 | generalni_plasman.csv line 30; trka_1.csv line 32; trka_2.csv line 30 |
| #353; MARKOVIĆ Matija | 2:03.907 | 2:03.685 | 4:07.592 | 30 / 2 | generalni_plasman.csv line 31; trka_1.csv line 33; trka_2.csv line 33 |
| #18; KOVAČEVIĆ Đorđe | 2:04.407 | 2:03.363 | 4:07.770 | 31 / 4 | generalni_plasman.csv line 32; trka_1.csv line 35; trka_2.csv line 31 |
| #287; RAŽNATOVIĆ Milorad | 2:04.699 | 2:03.508 | 4:08.207 | 32 / 3 | generalni_plasman.csv line 33; trka_1.csv line 37; trka_2.csv line 32 |
| #510; MILIČKOVIĆ Stevan | 2:04.568 | 2:04.709 | 4:09.277 | 33 / 4 | generalni_plasman.csv line 34; trka_1.csv line 36; trka_2.csv line 34 |
| #160; BRKAN Luka | 2:06.078 | 2:05.108 | 4:11.186 | 34 / 5 | generalni_plasman.csv line 35; trka_1.csv line 38; trka_2.csv line 35 |
| #200; DŽOGAZ Dragan | 2:06.486 | 2:05.221 | 4:11.707 | 35 / 6 | generalni_plasman.csv line 36; trka_1.csv line 39; trka_2.csv line 36 |
| #76; MILOŠEV Stevan | 2:07.875 | 2:05.722 | 4:13.597 | 36 / 7 | generalni_plasman.csv line 37; trka_1.csv line 41; trka_2.csv line 37 |
| #930; RADANOVIĆ Radovan | 2:07.198 | 2:06.701 | 4:13.899 | 37 / 3 | generalni_plasman.csv line 38; trka_1.csv line 40; trka_2.csv line 38 |
| #208; ZUROVAC Petar | 2:09.139 | 2:08.815 | 4:17.954 | 38 / 8 | generalni_plasman.csv line 39; trka_1.csv line 42; trka_2.csv line 40 |
| #81; RADANOVIĆ Vojo | 2:10.827 | 2:07.991 | 4:18.818 | 39 / 2 | generalni_plasman.csv line 40; trka_1.csv line 43; trka_2.csv line 39 |
| #12; BULATOVIĆ Dejan | 1:39.695 | DNS | DNF | NULL / NULL | generalni_plasman.csv line 41; trka_1.csv line 5; trka_2.csv line 43 |
| #17; ŠAPIĆ Ilija | 2:04.239 | DNS | DNF | NULL / NULL | generalni_plasman.csv line 42; trka_1.csv line 34; trka_2.csv line 46 |
| #21; ĆOROVIĆ Aldin | 1:37.221 | DNF | DNF | NULL / NULL | generalni_plasman.csv line 43; trka_1.csv line 3; trka_2.csv line 41 |
| #224; NIKOLIĆ Miljan | DNF | DNS | DNF | NULL / NULL | generalni_plasman.csv line 44; trka_1.csv line 46; trka_2.csv line 45 |
| #313; MILINIĆ Stefan | DNF | DNS | DNF | NULL / NULL | generalni_plasman.csv line 45; trka_1.csv line 44; trka_2.csv line 42 |
| #681; VUČETIĆ Goran | DNF | DNS | DNF | NULL / NULL | generalni_plasman.csv line 46; trka_1.csv line 45; trka_2.csv line 44 |
| #416; PEROVIĆ Damjan | DNS | DNS | DNS | NULL / NULL | generalni_plasman.csv line 47; trka_1.csv line 47; trka_2.csv line 47 |

## Pravilnik, bilten, datumi i neslaganja

- Pravilnik je odobren 18.05.2026, a Bilten br. 1 nosi datum 25.05.2026. Datum 05.06.2026 prikazan u web katalogu dokumenata je datum sajta, ne štampani datum biltena.
- Pravilnik str. 12 čl. 28.1–28.2 zaostalo pominje tri vožnje, dok str. 11 čl. 23.3 navodi dvije. Bilten str. 3 vizuelno precrtava tri i uvodi obje; rezultati imaju dvije trkačke vožnje. Ovo je razriješeno primarnim biltenom, bez dodatne pretpostavke.
- Pravilnik program str. 3 i bilten str. 1 navode prvi start trke u 12:00, ali pravilnik čl. 23.1 str. 11 navodi 13:00. Za tačan scheduled_at prve trke nema jednoznačnog podatka; predlog ostaviti NULL, zadržati 07.06. datum i izvorne rasporede u pregledu. Druga vožnja nema apsolutan čas.
- Plaćeni trening I 06.06. u 11:00, II u 12:30 i prvi službeni trening u 14:30 su programirani u pravilniku/biltenu. To su planirani, ne dokazani stvarni časovi starta.
- Pravilnik str. 5 čl. 2 sadrži očigledno nesaglasnu godinu 2025 u jednoj rečenici uz nedjelju, a naslov, program i bilten potvrđuju 2026. Ne mijenjati izvorni PDF.
- Bilten str. 2 i 4 dodaje CG i OPRS u prvenstva i klasifikacije. Pravilnik sam za sebe nije dovoljan za SCG pripadnost, ali dopuna to uspostavlja.
- PDF-ovi i CSV-ovi ne nose dodijeljene SCG bodove ili klupski bodovni poredak. Pravilnik bodovanje CEZ/NSS/OPS nije dokaz dodijeljenih CG bodova. Ne računati nove službene bodove iz sirovih vremena, ne primjenjivati smanjenje ponovo i ne tretirati NULL kao 0. Postojeće odobreno bodovanje sezone iz 016 ostaje povezano.
- Javna stranica zbir nosi naslov Generalni plasman, bez potpisa/izričite oznake konačni; programer treba prihvatiti objavljenu klasifikaciju ili pribaviti zvanični potpisani rezultat prije statusa FINALIZED.

## Nedostajuće opcione vrijednosti

Damjan Perović: automobil NULL. Za nove osobe nema datuma rođenja; oznaka MNE na sajtu predstavlja izvorni takmičarski kontekst, ne dokaz pasoša. Za ASU Zeka ne pretpostavljati državu/federaciju iz države domaćina. Izvorni časovi stvarnih startova, rezultati Warm-upa, dodijeljeni bodovi SCG i klupski poredak nisu dostavljeni. Nepoznate vrijednosti ostaju NULL i mogu se dopuniti kasnijom migracijom.

## Odluke i migracije

Sve obavezne odluke su razriješene. Nova 017 koriguje potvrđeni dupli model Pola i čuva postojeće reference/alias-e. Nova 018 uvodi Goč kao drugo kolo postojeće CG sezone, dva nova vozača, jedan klub i stazu, domaćina SAKSS, šest vremenskih sesija, 46 nastupa, 245 pojedinačnih ishoda, 17 konačnih klasifikacija sa 92 reda i 10 registrovanih izvora. Nema novih modela ili klasa.

- `backend/src/main/resources/db/changelog/017-merge-polo-spellings.yaml`
- `backend/src/main/resources/db/changelog/017-merge-polo-spellings.sql`
- `backend/src/main/resources/db/changelog/018-goc-2026.yaml`
- `backend/src/main/resources/db/changelog/018-goc-identities.sql`
- `backend/src/main/resources/db/changelog/018-goc-event.sql`
- `backend/src/main/resources/db/changelog/018-goc-sources.sql`

HTML snimci su izvori za provjeru, preuzimaju se kao attachment. Suvišni CSV prikazi, razvojni README i izvedeni JSON ne registruju se kao javni dokumenti. Originalni bajtovi su sačuvani.

Tri poznata planirana subotnja časa čuvaju se u scheduled_at kao raspored: 11:00, 12:30 i 14:30. Nedokazani ili protivrječni apsolutni časovi ostaju NULL. Planirani Warm-up nije unijet kao održana sesija, jer nema ishoda koji dokazuju njegovo održavanje.

Čist MySQL 8.4.11 replay kroz 018 i nadogradnja sa 016 su prošli. Test nadogradnje je sačuvao sve postojeće session_results i classification_results bajt-za-bajt po vrijednostima, uz nepovezani stari profil. Novi zapisi su provjereni i sa auto-generisanim ID-jevima od 10000. Ponovljeni Liquibase update nije napravio duplikate. Migracije nijesu primijenjene na bazu pokrenute aplikacije.

## Verifikacija pregleda

Inventarisano i hashovano svih 17 dostavljenih dokumenata i devet pribavljenih primarnih izvora. Nijedan izvorni bajt nije promijenjen. Pročitani CSV-ovi uključuju i pomoćne duplikate; rezultati su provjereni prema primarnom sajtu.

- 291/291 poređenja prema sajtu: bez razlika u vremenima/statusima/plasmanima; sedam očekivanih odstupanja prezimena #510.
- 39/39 zbirnih vremena tačno; svi CSV formati, broj učesnika i članstva klubova/klasa se slažu.
- Frontend build i lint: prošli.
- Backend testovi: 20 testova, 0 grešaka/neuspjeha, 0 preskočenih testova uz podešene izolovane šeme.
- Puni MySQL Goč replay i upgrade su prošli u racing_archive_verify_goc_20261020_clean i racing_archive_verify_goc_20261020_upgrade. Prava Spring Boot aplikacija prošla je Hibernate validaciju i HTTP provjere: oba događaja, devet sesija, svih 35 klasifikacija, sezonski prikaz sa nepoznatim Goč bodovima i praznim Goč klupskim poretkom. Svih 245 Goč pojedinačnih rezultata i svih 92 zbirnih/klasnih redova poređeni su s prihvaćenim mapama. Svih 13 registrovanih izvora (3 Kotor + 10 Goč) preuzeto je sa odgovarajućim media_type, attachment zaglavljem i tačnim SHA-256. Spajanje WV/VW Pola je sačuvalo izvorne alias-e i istorijske reference. Samo je jedan kanonski VW POLO preostao u izolovanoj bazi.
