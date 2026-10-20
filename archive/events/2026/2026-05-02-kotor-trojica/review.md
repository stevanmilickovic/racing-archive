# Kotor–Trojica 2026 — pregled izvora i predlog podataka

## Status i obim

Status: odluke prihvaćene, migracije i javni prikaz provjereni u izolovanim MySQL bazama. Konačni ključevi i odluke su u narednim tabelama i canonical* poljima reconciliation.json. Početni predlozi su sačuvani kao istorija pregleda i ne predstavljaju dodatne identitete. Ovaj pregled i reconciliation.json su razvojni zapisi, ne javni metapodaci.

Programer je potvrdio trening 02.05.2026, trku 03.05.2026. i samo jedan trening. Rezultati treninga možda stižu naknadno. U postojećim PDF-ovima nema rezultata treninga. Ne praviti izmišljena vremena, startove, redoslijed ili rezultate treninga.

## Prihvaćene odluke programera

Programer je odgovorio na svih pet predstavljenih pitanja; ove odluke zamjenjuju početne predloge ispod.

- Svih 133 pregledana vozača dodaju se kao samostalni identiteti. Samo 68 redova iz konačnih rezultata imaju entries i rezultate ovog događaja. Ostalih 65 imaju samo profil, bez povezivanja s trkom i bez statusa DNS. Dokument potvrđuje njihovu prijavu; ne tvrdimo da nijesu došli.
- #510: STEVAN MILIČKOVIĆ, ključ driver-stevan-milickovic. STEVAN MILIĆEVIĆ u P str. 1 R.B. 11 je potvrđena greška prepisivanja; sačuvana je kao izvorni zapis s objašnjenjem u aliasu.
- #217: EFIMIN DMITRI, doslovno kako piše u P/R/K. Ne mijenjati redoslijed ni transliteraciju.
- Konačni startni brojevi i klase iz R/K su prihvaćeni; ĐURO POPOVIĆ je E1 1200.
- Vozila: WV POLO, NISAN MICRA i FIAT SIECENTO ostaju u tom obliku. HONDA TYPER, HONDA CIVIC TYPER i HONDA CIVIC postaju jedan opis HONDA CIVIC. YUGO, YUGO 55 i ZASTAVA YUGO postaju YUGO. ROVER/ROVER MG i GLOBAL/GLOBAL LIGHT ostaju zasebni opisi; za njih nije potvrđeno objedinjavanje. Izvorni opisi ostaju u aliasima.
- Nezavršen zbir je DNF, uključujući DNS jedne ili obje vožnje. Pojedinačni DNS/DNF ostaju kako su objavljeni. DISQ se normalizuje u DSQ; nije zamijenjen sa DNF. Konačno: 43 FINISHED, 19 DNF, 6 DSQ po kategorijskim klasifikacijama.
- Prihvaćen je prikaz zvaničnih klasifikacija i objavljenog klupskog poretka, bez pretpostavljenih sezonskih pravila. scoring_system_id ostaje NULL.
- Jedan trening postoji kao sesija bez rezultata; dopuna ide novom migracijom kada stignu dokumenti.

Konačan obim: 133 vozača, 12 klubova, 28 opisa vozila, 16 klasa, 1 staza, 68 nastupa, 3 sesije (jedan trening bez rezultata + dvije trkačke vožnje), 136 pojedinačnih rezultata, 18 klasifikacija sa 136 redova, 12 klupskih rezultata i 3 izvorna PDF-a. Novi klubovi i vozila ostalih 65 prijavljenih se ne kreiraju jer nemaju istorijski nastup koji bi na njih referencirao.

## Konačni opisi vozila i ključevi

| Kanonski opis | archive_key | Tačni opisi iz konačnih rezultata |
| --- | --- | --- |
| BMW | vehicle-bmw | BMW |
| BMW E46 | vehicle-bmw-e46 | BMW E46 |
| BMW M3 | vehicle-bmw-m3 | BMW M3 |
| CITROEN C2 | vehicle-citroen-c2 | CITROEN C2 |
| CITROEN SAXO | vehicle-citroen-saxo | CITROEN SAXO |
| FIAT 500 | vehicle-fiat-500 | FIAT 500 |
| FIAT 750 | vehicle-fiat-750 | FIAT 750 |
| FIAT SIECENTO | vehicle-fiat-siecento | FIAT SIECENTO |
| GLOBAL | vehicle-global | GLOBAL |
| GLOBAL LIGHT | vehicle-global-light | GLOBAL LIGHT |
| HONDA CIVIC | vehicle-honda-civic | HONDA  TYPER / HONDA  CIVIC TYPER / HONDA CIVIC / HONDA TYPER |
| NISAN MICRA | vehicle-nisan-micra | NISAN MICRA |
| NORMA N202B | vehicle-norma-n202b | NORMA N202B |
| OPEL ASTRA | vehicle-opel-astra | OPEL ASTRA |
| OPEL CORSA | vehicle-opel-corsa | OPEL CORSA |
| PEUGEOT 106 | vehicle-peugeot-106 | PEUGEOT 106 |
| PEUGEOT 306 | vehicle-peugeot-306 | PEUGEOT 306 |
| ROVER | vehicle-rover | ROVER |
| ROVER MG | vehicle-rover-mg | ROVER MG |
| SEAT LEON | vehicle-seat-leon | SEAT LEON |
| SUZUKI SWIFT | vehicle-suzuki-swift | SUZUKI SWIFT |
| SUZUKI SWIFT GTI | vehicle-suzuki-swift-gti | SUZUKI SWIFT GTI |
| VW GOLF I | vehicle-vw-golf-i | VW GOLF I |
| VW GOLF V | vehicle-vw-golf-v | VW GOLF V |
| VW POLO | vehicle-vw-polo | VW POLO |
| WV POLO | vehicle-wv-polo | WV POLO |
| YUGO | vehicle-yugo | ZASTAVA YUGO / YUGO / YUGO 55 |
| ŠKODA FELICIA | vehicle-skoda-felicia | ŠKODA FELICIA |

## Konačne reference i ukupni status za svih 68 nastupa

| Broj / vozač | Driver key | Club key | Vehicle key | Class key | Ukupni status |
| --- | --- | --- | --- | --- | --- |
| 36; JOVAN PERUNOVIĆ | driver-jovan-perunovic | club-amsk-podi | vehicle-fiat-750 | class-akscg-e2sh | FINISHED |
| 16; GORAN VUČKOVIĆ | driver-goran-vuckovic | club-amsk-kotor | vehicle-vw-golf-v | class-akscg-h-2000-plus | FINISHED |
| 12; DEJAN BULATOVIĆ | driver-dejan-bulatovic | club-amsk-kotor | vehicle-peugeot-306 | class-akscg-h-2000 | FINISHED |
| 88; TRIPO PINJATIĆ | driver-tripo-pinjatic | club-amsk-crmnica | vehicle-fiat-500 | class-akscg-e2sh | FINISHED |
| 7; MIRKO VUJAČIĆ | driver-mirko-vujacic | club-amsk-crmnica | vehicle-citroen-c2 | class-akscg-a-1600 | FINISHED |
| 26; DUŠAN TUJKOVIĆ | driver-dusan-tujkovic | club-amsk-kotor | vehicle-honda-civic | class-akscg-n-2000 | FINISHED |
| 31; MILOŠ BOJANOVIĆ | driver-milos-bojanovic | club-amsk-gorstak | vehicle-peugeot-106 | class-akscg-h-1600 | FINISHED |
| 68; GORAN VUČETIĆ | driver-goran-vucetic | club-amsk-podi | vehicle-honda-civic | class-akscg-n-2000 | FINISHED |
| 416; DAMJAN PEROVIĆ | driver-damjan-perovic | club-amsk-kotor | vehicle-honda-civic | class-akscg-h-1600 | FINISHED |
| 71; ĐURO POPOVIĆ | driver-duro-popovic | club-amsk-podi | vehicle-suzuki-swift | class-akscg-e1-1200 | FINISHED |
| 217; EFIMIN DMITRI | driver-efimin-dmitri | club-amsk-crmnica | vehicle-bmw-m3 | class-akscg-h-2000-plus | FINISHED |
| 262; BALŠA ĐURIČKOVIĆ | driver-balsa-durickovic | club-amsk-gorstak | vehicle-skoda-felicia | class-akscg-e1-1200 | FINISHED |
| 43; RADOVAN MASONIČIĆ | driver-radovan-masonicic | club-amsk-crmnica | vehicle-honda-civic | class-akscg-a-2000 | FINISHED |
| 115; MILUTIN KRIVOKAPIĆ | driver-milutin-krivokapic | club-amsk-herceg-novi | vehicle-peugeot-106 | class-akscg-a-1400 | FINISHED |
| 23; MARKO ADŽIĆ | driver-marko-adzic | club-amsk-podi | vehicle-rover-mg | class-akscg-a-1400 | FINISHED |
| 91; MILO ĐUKANOVIĆ | driver-milo-dukanovic | club-amsk-kotor | vehicle-peugeot-106 | class-akscg-h-1400 | FINISHED |
| 13; MILOŠ VUJNOVIĆ | driver-milos-vujnovic | club-amsk-herceg-novi | vehicle-peugeot-106 | class-akscg-n-1400 | FINISHED |
| 231; MARKO PEJOVIĆ | driver-marko-pejovic | club-amk-podgorica-rt | vehicle-peugeot-106 | class-akscg-n-1400 | FINISHED |
| 29; SLOBODAN MUGOŠA | driver-slobodan-mugosa | club-amsk-ap-sport | vehicle-citroen-saxo | class-akscg-n-1600 | FINISHED |
| 19; DEJAN KOVAČEVIĆ | driver-dejan-kovacevic | club-amsk-herceg-novi | vehicle-wv-polo | class-akscg-n-1400 | FINISHED |
| 104; GORAN MILOŠEV | driver-goran-milosev | club-amsk-herceg-novi | vehicle-vw-golf-i | class-akscg-h-2000 | FINISHED |
| 17; ILIJA ŠAPIĆ | driver-ilija-sapic | club-amsk-herceg-novi | vehicle-peugeot-106 | class-akscg-n-1400 | FINISHED |
| 222; LUKA ŠUĆUR | driver-luka-sucur | club-amsk-podi | vehicle-suzuki-swift-gti | class-akscg-h-1400 | FINISHED |
| 151; DARKO KOKOTOVIĆ | driver-darko-kokotovic | club-amsk-herceg-novi | vehicle-opel-corsa | class-akscg-h-1600 | FINISHED |
| 30; PETAR JOVANOVIĆ | driver-petar-jovanovic | club-amsk-podi | vehicle-yugo | class-akscg-a-1150 | FINISHED |
| 318; ŽARKO LAGATOR | driver-zarko-lagator | club-amkk-lovcen-r-t | vehicle-yugo | class-akscg-a-1150 | FINISHED |
| 95; STEFAN BOKOVAC | driver-stefan-bokovac | club-amsk-crmnica | vehicle-vw-polo | class-akscg-a-1400 | FINISHED |
| 121; ANDRIJA ĐURIŠIĆ | driver-andrija-durisic | club-amsk-ap-sport | vehicle-yugo | class-akscg-h-1000 | FINISHED |
| 113; STEFAN MILINIĆ | driver-stefan-milinic | club-amsk-podi | vehicle-honda-civic | class-akscg-h-2000 | FINISHED |
| 506; ALMIN ĆOROVIĆ | driver-almin-corovic | club-amsk-ap-sport | vehicle-opel-astra | class-akscg-a-2000 | FINISHED |
| 48; MARIJA IVANOVIĆ | driver-marija-ivanovic | club-amk-monteracing | vehicle-citroen-saxo | class-akscg-a-1600 | FINISHED |
| 119; SAKIB DURAKOVIĆ | driver-sakib-durakovic | club-amsk-crmnica | vehicle-rover | class-akscg-a-1400 | FINISHED |
| 33; GLIGORIJE ŠUŠIĆ | driver-gligorije-susic | club-amsk-podi | vehicle-citroen-saxo | class-akscg-n-1600 | FINISHED |
| 96; MARGARITA MALTSEVA | driver-margarita-maltseva | club-amsk-crmnica | vehicle-citroen-saxo | class-akscg-a-1600 | FINISHED |
| 160; LUKA BRKAN | driver-luka-brkan | club-amsk-tivat | vehicle-yugo | class-akscg-n-1150 | FINISHED |
| 76; STEVAN MILOŠEV | driver-stevan-milosev | club-amsk-herceg-novi | vehicle-yugo | class-akscg-n-1150 | FINISHED |
| 200; DRAGAN DŽOGAZ | driver-dragan-dzogaz | club-amsk-ap-sport | vehicle-yugo | class-akscg-n-1150 | FINISHED |
| 155; IGOR RADMILOVIĆ | driver-igor-radmilovic | club-amsk-herceg-novi | vehicle-yugo | class-akscg-n-1150 | FINISHED |
| 510; STEVAN MILIČKOVIĆ | driver-stevan-milickovic | club-amsk-ap-sport | vehicle-yugo | class-akscg-n-1150 | FINISHED |
| 500; NIKOLA TOŠKOVIĆ | driver-nikola-toskovic | club-amsk-ap-sport | vehicle-nisan-micra | class-akscg-h-1000 | FINISHED |
| 81; VOJO RADANOVIĆ | driver-vojo-radanovic | club-amsk-herceg-novi | vehicle-peugeot-106 | class-akscg-h-1000 | FINISHED |
| 18; ĐORĐE KOVAČEVIĆ | driver-dorde-kovacevic | club-amsk-herceg-novi | vehicle-peugeot-106 | class-akscg-n-1400 | DNF |
| 25; ŽELJKO RADONJIĆ | driver-zeljko-radonjic | club-amsk-kotor | vehicle-yugo | class-akscg-e2sh | DNF |
| 53; LAZAR MUJOVIĆ | driver-lazar-mujovic | club-amsk-podi | vehicle-bmw-e46 | class-akscg-h-2000 | DNF |
| 75; NIKOLA VLAHOVIĆ | driver-nikola-vlahovic | club-amsk-vlahovic | vehicle-peugeot-106 | class-akscg-n-1400 | DSQ |
| 78; MILAN ROGANOVIĆ | driver-milan-roganovic | club-amsk-ap-sport | vehicle-fiat-siecento | class-akscg-a-1150 | DNF |
| 80; MARKO JANKOVIĆ | driver-marko-jankovic | club-amsk-gorstak | vehicle-honda-civic | class-akscg-n-2000 | DNF |
| 86; DARKO IVANOVIĆ | driver-darko-ivanovic | club-amsk-tivat | vehicle-peugeot-106 | class-akscg-h-1400 | DNF |
| 89; DEJAN ĐONOVIĆ | driver-dejan-donovic | club-amsk-crmnica | vehicle-peugeot-106 | class-akscg-n-1400 | DNF |
| 92; BOŽIDAR DODEROVIĆ | driver-bozidar-doderovic | club-amsk-podi | vehicle-yugo | class-akscg-a-1150 | DNF |
| 94; ZLATKO SINDIK | driver-zlatko-sindik | club-amsk-crmnica | vehicle-yugo | class-akscg-h-1600 | DNF |
| 152; MILAN BRACANOVIĆ | driver-milan-bracanovic | club-amsk-ap-sport | vehicle-peugeot-106 | class-akscg-h-1400 | DNF |
| 153; MATIJA MARKOVIĆ | driver-matija-markovic | club-amkk-lovcen-r-t | vehicle-yugo | class-akscg-n-1150 | DSQ |
| 197; SAVA NIKČEVIĆ | driver-sava-nikcevic | club-amsk-gorstak | vehicle-yugo | class-akscg-a-1150 | DSQ |
| 208; PETAR ZUROVAC | driver-petar-zurovac | club-amsk-nilovic-racing | vehicle-yugo | class-akscg-n-1150 | DSQ |
| 220; KRSTO RADOVIĆ | driver-krsto-radovic | club-amsk-herceg-novi | vehicle-citroen-c2 | class-akscg-a-1600 | DNF |
| 224; MILJAN NIKOLIĆ | driver-miljan-nikolic | club-amsk-nilovic-racing | vehicle-peugeot-106 | class-akscg-n-1400 | DNF |
| 230; LAZAR KOSTIĆ | driver-lazar-kostic | club-amsk-nilovic-racing | vehicle-peugeot-106 | class-akscg-h-1400 | DNF |
| 236; DRAGAN MUDREŠA | driver-dragan-mudresa | club-amsk-podi | vehicle-yugo | class-akscg-a-1150 | DNF |
| 237; MILE MALOVIĆ | driver-mile-malovic | club-amk-podgorica-rt | vehicle-seat-leon | class-akscg-h-2000-plus | DNF |
| 287; MILORAD RAŽNATOVIĆ | driver-milorad-raznatovic | club-amsk-tivat | vehicle-yugo | class-akscg-n-1150 | DSQ |
| 333; DEJAN ŠUŠKAVČEVIĆ | driver-dejan-suskavcevic | club-amsk-nilovic-racing | vehicle-bmw | class-akscg-h-2000-plus | DNF |
| 442; SPASOJE NIKOLIĆ | driver-spasoje-nikolic | club-amsk-podi | vehicle-yugo | class-akscg-a-1150 | DNF |
| 454; RADIVOJE JANJUŠEVIĆ | driver-radivoje-janjusevic | club-amsk-ap-sport | vehicle-peugeot-106 | class-akscg-n-1600 | DNF |
| 511; NIKOLA LAGATOR | driver-nikola-lagator | club-amkk-lovcen-r-t | vehicle-yugo | class-akscg-n-1150 | DSQ |
| 21; ALDIN ĆOROVIĆ | driver-aldin-corovic | club-amsk-ap-sport | vehicle-global | class-akscg-d-e2-cn | FINISHED |
| 14; BAĆKO VUJOVIĆ | driver-backo-vujovic | club-amsk-gorstak | vehicle-norma-n202b | class-akscg-d-e2-cn | FINISHED |
| 52; BOŽIDAR JABLAN | driver-bozidar-jablan | club-amsk-kotor | vehicle-global-light | class-akscg-d-e2-cn | DNF |

## Dokumenti i integritet

| Oznaka | Datoteka / izvorni ključ | Stranice / bajtovi | Datum i status |
| --- | --- | --- | --- |
| R | AKSCG KONACNI.pdf; 2026-05-02-kotor-trojica-akscg-official-2026-05-12 | 7 / 296292 | 2026-05-12; official-final |
| K | KLUBOVI.pdf; 2026-05-02-kotor-trojica-clubs-2026-05-13 | 4 / 455741 | nema štampanog datuma izdanja; not-explicitly-labelled |
| P | LIST PRIJAVA - MBAT _KOTOR TROJICA 2026 -MEMORIJAL TRIPO BUKILICA.pdf; 2026-05-02-kotor-trojica-registrations-2026-04-30 | 2 / 96861 | 2026-04-30; final-for-akscg-and-ph-open-for-cups |

- AKSCG KONACNI.pdf: SHA-256 `307eb71f72b6426a2f62f64a741858c3dc5033f4e5ad5ed3cf126da1a5cccfbb`.
- KLUBOVI.pdf: SHA-256 `d49aa2e545559236b693a7efc53b5561fad29d0e1622e7c758528d4869bbf2bb`.
- LIST PRIJAVA - MBAT _KOTOR TROJICA 2026 -MEMORIJAL TRIPO BUKILICA.pdf: SHA-256 `72dce69bd17cb6f723c0708a8daddca7eb7be9de58daab78a65bc9d3fc5b5114`.

Originalne URL adrese nijesu dostavljene. Sačuvani su izvorni nazivi i bajtovi; nijedan PDF nije prepisan. Metapodatak datuma kreiranja nije dokaz datuma objavljivanja ili nove revizije. R ima štampan datum izrade 12.05.2026. u 20:40; datum trke je 03.05.2026. K metapodaci kažu 13.05.2026, ali dokument nema eksplicitnu oznaku konačnosti niti štampan datum izdanja. P je datiran 30.04.2026. i označen kao konačan za Šampionat Crne Gore i Prvenstvo Hrvatske, a otvoren za kupove do verifikacija.

## Poređenje s postojećim podacima

Pregledane su migracije 001–013, uključujući povlačenje nepotvrđenih klubova i klasa u 013. Dana 20.10.2026. read-only GET /public/catalog vratio je identities=[] i aliases=[]; GET /public/dashboard vratio je prazan niz. Nema postojećih identiteta za usvajanje niti potvrđenih aliasa. Stari spekulativni seedovi nijesu dokaz identiteta. Direktan SQL popis neaktivnih sezona i roditeljskih evidencija nije obavljen; prazan javni dashboard sam po sebi nije dokaz da tih zapisa nema. Buduća migracija mora provjeriti roditelje i jedinstvenost prije upotrebe.

Poklapanja među dokumentima u nastavku su kandidati istog događaja, potkrijepljeni klubom, klasom, vozilom i startnim brojem gdje se slaže. Nijesu automatsko spajanje ljudi po imenu; programer treba da ih potvrdi.

## Brojevi i provjera rezultata

- P: 133 fizička reda prijava: 74 AKSCG/MNE i 59 ostalih; na prvom listu fizički red 38 greškom nosi R.B. 28.
- R: 68 različitih vozača/nastupa: 65 u kategoriji 1 i 3 u kategoriji 2. Isti nastupi su ponovljeni u klasnim tabelama; to nijesu dodatni rezultati.
- 43 završena ukupna nastupa: 41 u kategoriji 1 i 2 u kategoriji 2. Za svih 43 zbir prve i druge vožnje tačno odgovara ukupnom vremenu, u milisekundama.
- Obje trkačke vožnje imaju po 68 objavljenih ishoda: prva 51 vrijeme + 17 statusa, druga 43 vremena + 25 statusa. Rangovi u R su ukupni kategorijski/klasni rangovi, ne rangovi pojedinačnih vožnji.
- Svih 68 redova iz kategorija odgovara klasnim ponavljanjima po imenu, klasi, klubu, vozilu, vremenima/statusima, klasnom mjestu i bodovima.
- K: 12 klubova i 68 pojedinačnih redova; imena, brojevi, klase, pripadnost i pojedinačni bodovi odgovaraju R. Izjednačeni klubovi zadržavaju objavljeni rang; pravilo razrješenja nije dostavljeno.
- 65 prijavljenih nema rezultat u dostavljenom R: šest MNE/AKSCG i 59 ostalih. Odsustvo nije dokaz DNS, odustajanja ili nenastupanja.
- Broj ljudi, novih klubova i vozila za migraciju zavisi od odluke o obimu.

## Predlog događaja, staze i sezone

| Vrsta | Predlog | Dokaz / nepoznato |
| --- | --- | --- |
| Događaj | 2026-05-02-kotor-trojica; Kotor–Trojica 2026 — Memorijal Tripo Bukilica | P naslov; K str. 1 zaglavlje; R sve stranice; datumi programera. |
| Staza | track-kotor-trojica; Kotor–Trojica; lokacija Kotor | R podnožja: KOTOR,TRACK trojica L=4350 m; R i K naziv događaja. Nema tvrdnje o istovjetnosti konfiguracije drugih godina. |
| Federacija | Auto i karting savez Crne Gore (AKSCG) | R str. 1/3 logo i ŠAMPIONAT CRNE GORE; P Prvenstvo=AKSCG. Naziv iz logotipa. |
| Disciplina / prvenstvo / sezona | Hillclimb / Šampionat Crne Gore / 2026 | R zaglavlje BRDSKA AUTO TRKA, International hill Climb RACE i tabele; ime sezone predlog za potvrdu. |
| Runda | 1 | Programer navodi prvu trku 2026; numeracija sezone čeka potvrdu. |
| Bodovanje | scoring_system_id = NULL | Dostavljeni su ostvareni bodovi, ali ne potpuna pravila sezone. Ne dodjeljivati zadržani moderni sistem po pretpostavci. |
| Druga prvenstva/kupovi | BPPA, PH, ADRIATIC KUP i KUP KO samo iz prijava | Nema njihovih rezultata niti pravila; bez izmišljanja punih naziva, federacija, sezona ili plasmana. Zajednički vikend ostaje jedan događaj. |
| Trening | Jedan, 02.05.2026; bez rezultata | Programer; tačno vrijeme i redoslijed nijesu dostavljeni. |
| Trka | Prva vožnja i druga vožnja, 03.05.2026 | R 1.Vožnja/2.Vožnja; datum trke. Nema tačnih časova starta. |

## Početna pitanja — riješena odlukama iznad

1. Obim: predlog prve migracije je 68 nastupa potkrijepljenih konačnim rezultatima R, 12 klubova, 32 doslovna opisa vozila, 16 klasa i jedna staza. Sve ostale prijave su popisane ispod. Da li dodati i ostalih šest MNE vozača i/ili 59 stranih prijavljenih samo kao identitete ili prijave bez rezultata? Ne praviti DNS na osnovu odsustva iz R.

2. #510: P str. 1 R.B. 11 piše STEVAN MILIĆEVIĆ; R str. 2/4 i K str. 1 pišu STEVAN MILIČKOVIĆ. Klub AP SPORT, YUGO 55, klasa N 1150 i broj 510 se slažu. Predlog: jedan vozač STEVAN MILIČKOVIĆ, driver-stevan-milickovic; MILIĆEVIĆ kao dokumentovan alias tek nakon potvrde programera. Potvrditi stvarni identitet i kanonski zapis.

3. #217: P eksplicitno navodi Ime=EFIMIN, Prezime=DMITRI; R i K takođe EFIMIN DMITRI. Predlog zadržava taj doslovni zapis i izvorni MNE bez nagađanja drugog imena ili državljanstva. Potvrditi da li treba ispravka redoslijeda/transliteracije prije kreiranja osobe.

4. Za nastupe koji imaju obje evidencije, koristiti konačne startne brojeve iz R i potvrđenog K; sve razlike su u posebnoj tabeli. #71 ĐURO POPOVIĆ je u P H 1400, a u R str. 1/5 i K str. 1 E1 1200. Predlog: konačna klasa E1 1200; izvorni zapis P sačuvati u pregledu.

5. Vozila: predlog za početak čuva doslovne opise kao u R. HONDA TYPER i HONDA CIVIC TYPER ostaju različiti opisi; isto YUGO, YUGO 55 i ZASTAVA YUGO; ROVER i ROVER MG; GLOBAL i GLOBAL LIGHT. Ne tvrditi identitet fizičke šasije. Potvrditi eventualno objedinjavanje i ispravke WV POLO/VW POLO, NISAN MICRA, FIAT SIECENTO i NORMA N202B. PEUGEUOT 106 iz P odgovara PEUGEOT 106 istog reda učesnika u R; predlog je potvrđeni alias nakon odobrenja.

6. Osam nastupa ima vrijeme prve vožnje i DNS/DNF druge, ali praznu ukupnu ćeliju Time: #18, #25, #78, #89, #94, #224, #230 i #454. Prva/druga vožnja su jasne i mogu se prenijeti doslovno. Ukupna klasifikacija u modelu traži status; predlog DNF za nezavršen zbir treba potvrdu programera, bez izmišljanja ukupnog vremena ili plasmana. Objavljenih 0,00 klasnih bodova je eksplicitno u R.

7. Potvrditi sve nove identitete u tabelama, stazu i roditelje događaja, te prihvatiti naknadnu dopunu rezultata jedinog treninga. Klasne i kategorijske bodove čuvati kao objavljene klasifikacije; sezonska pravila ostaviti nepoznata dok se ne dostave. K nema izričitu oznaku konačnosti; potvrditi njegovo korišćenje kao objavljenog klupskog poretka.

## Prihvaćeni vozači sa konačnim rezultatima — izvorni redovi

| Izvori / lokatori | Tačno ime u R | Predloženi archive_key | Klub / vozilo / konačna klasa |
| --- | --- | --- | --- |
| P str. 1, R.B. 52; R str. 1, kat. 1, #36; R str. 5, E2SH, #36; K str. 1, AMSK PODI, #36 | JOVAN PERUNOVIĆ | driver-jovan-perunovic | AMSK PODI; FIAT 750; E2SH |
| P str. 1, R.B. 71; R str. 1, kat. 1, #16; R str. 7, H 2000+, #16; K str. 1, AMSK KOTOR, #16 | GORAN VUČKOVIĆ | driver-goran-vuckovic | AMSK KOTOR; VW GOLF V; H 2000+ |
| P str. 1, R.B. 61; R str. 1, kat. 1, #12; R str. 7, H 2000, #12; K str. 1, AMSK KOTOR, #12 | DEJAN BULATOVIĆ | driver-dejan-bulatovic | AMSK KOTOR; PEUGEOT 306; H 2000 |
| P str. 1, R.B. 51; R str. 1, kat. 1, #88; R str. 5, E2SH, #88; K str. 2, AMSK CRMNICA, #88 | TRIPO PINJATIĆ | driver-tripo-pinjatic | AMSK CRMNICA; FIAT 500; E2SH |
| P str. 1, R.B. 43; R str. 1, kat. 1, #7; R str. 6, A 1600, #7; K str. 2, AMSK CRMNICA, #7 | MIRKO VUJAČIĆ | driver-mirko-vujacic | AMSK CRMNICA; CITROEN C2; A 1600 |
| P str. 1, R.B. 54; R str. 1, kat. 1, #26; R str. 6, N 2000, #26; K str. 1, AMSK KOTOR, #26 | DUŠAN TUJKOVIĆ | driver-dusan-tujkovic | AMSK KOTOR; HONDA  TYPER; N 2000 |
| P str. 1, R.B. 46; R str. 1, kat. 1, #31; R str. 6, H 1600, #31; K str. 3, AMSK GORŠTAK, #31 | MILOŠ BOJANOVIĆ | driver-milos-bojanovic | AMSK GORŠTAK; PEUGEOT 106; H 1600 |
| P str. 1, R.B. 56; R str. 1, kat. 1, #68; R str. 6, N 2000, #68; K str. 1, AMSK PODI, #68 | GORAN VUČETIĆ | driver-goran-vucetic | AMSK PODI; HONDA  CIVIC TYPER; N 2000 |
| P str. 1, R.B. 45; R str. 1, kat. 1, #416; R str. 6, H 1600, #416; K str. 1, AMSK KOTOR, #416 | DAMJAN PEROVIĆ | driver-damjan-perovic | AMSK KOTOR; HONDA CIVIC; H 1600 |
| P str. 1, R.B. 35; R str. 1, kat. 1, #71; R str. 5, E1 1200, #71; K str. 1, AMSK PODI, #71 | ĐURO POPOVIĆ | driver-duro-popovic | AMSK PODI; SUZUKI SWIFT; E1 1200 |
| P str. 1, R.B. 69; R str. 1, kat. 1, #217; R str. 7, H 2000+, #217; K str. 2, AMSK CRMNICA, #217 | EFIMIN DMITRI | driver-efimin-dmitri | AMSK CRMNICA; BMW M3; H 2000+ |
| P str. 1, R.B. 53; R str. 1, kat. 1, #262; R str. 5, E1 1200, #262; K str. 3, AMSK GORŠTAK, #262 | BALŠA ĐURIČKOVIĆ | driver-balsa-durickovic | AMSK GORŠTAK; ŠKODA FELICIA; E1 1200 |
| P str. 1, R.B. 57; R str. 1, kat. 1, #43; R str. 7, A 2000, #43; K str. 2, AMSK CRMNICA, #43 | RADOVAN MASONIČIĆ | driver-radovan-masonicic | AMSK CRMNICA; HONDA  CIVIC TYPER; A 2000 |
| P str. 1, R.B. 30; R str. 1, kat. 1, #115; R str. 5, A 1400, #115; K str. 2, AMSK HERCEG NOVI, #115 | MILUTIN KRIVOKAPIĆ | driver-milutin-krivokapic | AMSK HERCEG NOVI; PEUGEOT 106; A 1400 |
| P str. 1, R.B. 31; R str. 1, kat. 1, #23; R str. 5, A 1400, #23; K str. 1, AMSK PODI, #23 | MARKO ADŽIĆ | driver-marko-adzic | AMSK PODI; ROVER MG; A 1400 |
| P str. 1, R.B. 32; R str. 1, kat. 1, #91; R str. 5, H 1400, #91; K str. 1, AMSK KOTOR, #91 | MILO ĐUKANOVIĆ | driver-milo-dukanovic | AMSK KOTOR; PEUGEOT 106; H 1400 |
| P str. 1, R.B. 23; R str. 1, kat. 1, #13; R str. 4, N 1400, #13; K str. 2, AMSK HERCEG NOVI, #13 | MILOŠ VUJNOVIĆ | driver-milos-vujnovic | AMSK HERCEG NOVI; PEUGEOT 106; N 1400 |
| P str. 1, R.B. 22; R str. 1, kat. 1, #231; R str. 4, N 1400, #231; K str. 3, AMK PODGORICA RT, #231 | MARKO PEJOVIĆ | driver-marko-pejovic | AMK PODGORICA RT; PEUGEOT 106; N 1400 |
| P str. 1, R.B. 39; R str. 1, kat. 1, #29; R str. 6, N 1600, #29; K str. 1, AMSK AP SPORT, #29 | SLOBODAN MUGOŠA | driver-slobodan-mugosa | AMSK AP SPORT; CITROEN SAXO; N 1600 |
| P str. 1, R.B. 24; R str. 1, kat. 1, #19; R str. 4, N 1400, #19; K str. 2, AMSK HERCEG NOVI, #19 | DEJAN KOVAČEVIĆ | driver-dejan-kovacevic | AMSK HERCEG NOVI; WV POLO; N 1400 |
| P str. 1, R.B. 63; R str. 1, kat. 1, #104; R str. 7, H 2000, #104; K str. 2, AMSK HERCEG NOVI, #104 | GORAN MILOŠEV | driver-goran-milosev | AMSK HERCEG NOVI; VW GOLF I; H 2000 |
| P str. 1, R.B. 26; R str. 1, kat. 1, #17; R str. 4, N 1400, #17; K str. 2, AMSK HERCEG NOVI, #17 | ILIJA ŠAPIĆ | driver-ilija-sapic | AMSK HERCEG NOVI; PEUGEOT 106; N 1400 |
| P str. 1, R.B. 36; R str. 1, kat. 1, #222; R str. 5, H 1400, #222; K str. 1, AMSK PODI, #222 | LUKA ŠUĆUR | driver-luka-sucur | AMSK PODI; SUZUKI SWIFT GTI; H 1400 |
| P str. 1, R.B. 49; R str. 1, kat. 1, #151; R str. 6, H 1600, #151; K str. 2, AMSK HERCEG NOVI, #151 | DARKO KOKOTOVIĆ | driver-darko-kokotovic | AMSK HERCEG NOVI; OPEL CORSA; H 1600 |
| P str. 1, R.B. 18; R str. 1, kat. 1, #30; R str. 4, A 1150, #30; K str. 1, AMSK PODI, #30 | PETAR JOVANOVIĆ | driver-petar-jovanovic | AMSK PODI; ZASTAVA YUGO; A 1150 |
| P str. 1, R.B. 14; R str. 1, kat. 1, #318; R str. 4, A 1150, #318; K str. 3, AMKK LOVĆEN R.T., #318 | ŽARKO LAGATOR | driver-zarko-lagator | AMKK LOVĆEN R.T.; YUGO; A 1150 |
| P str. 1, R.B. 28; R str. 2, kat. 1, #95; R str. 5, A 1400, #95; K str. 2, AMSK CRMNICA, #95 | STEFAN BOKOVAC | driver-stefan-bokovac | AMSK CRMNICA; VW POLO; A 1400 |
| P str. 1, R.B. 3; R str. 2, kat. 1, #121; R str. 3, H 1000, #121; K str. 1, AMSK AP SPORT, #121 | ANDRIJA ĐURIŠIĆ | driver-andrija-durisic | AMSK AP SPORT; YUGO 55; H 1000 |
| P str. 1, R.B. 67; R str. 2, kat. 1, #113; R str. 7, H 2000, #113; K str. 1, AMSK PODI, #113 | STEFAN MILINIĆ | driver-stefan-milinic | AMSK PODI; HONDA TYPER; H 2000 |
| P str. 1, R.B. 59; R str. 2, kat. 1, #506; R str. 7, A 2000, #506; K str. 1, AMSK AP SPORT, #506 | ALMIN ĆOROVIĆ | driver-almin-corovic | AMSK AP SPORT; OPEL ASTRA; A 2000 |
| P str. 1, R.B. 41; R str. 2, kat. 1, #48; R str. 6, A 1600, #48; K str. 3, AMK MONTERACING, #48 | MARIJA IVANOVIĆ | driver-marija-ivanovic | AMK MONTERACING; CITROEN SAXO; A 1600 |
| P str. 1, R.B. 29; R str. 2, kat. 1, #119; R str. 5, A 1400, #119; K str. 2, AMSK CRMNICA, #119 | SAKIB DURAKOVIĆ | driver-sakib-durakovic | AMSK CRMNICA; ROVER; A 1400 |
| P str. 1, R.B. 28 (38. fizički red; štampano 28); R str. 2, kat. 1, #33; R str. 6, N 1600, #33; K str. 1, AMSK PODI, #33 | GLIGORIJE ŠUŠIĆ | driver-gligorije-susic | AMSK PODI; CITROEN SAXO; N 1600 |
| P str. 1, R.B. 42; R str. 2, kat. 1, #96; R str. 6, A 1600, #96; K str. 2, AMSK CRMNICA, #96 | MARGARITA MALTSEVA | driver-margarita-maltseva | AMSK CRMNICA; CITROEN SAXO; A 1600 |
| P str. 1, R.B. 6; R str. 2, kat. 1, #160; R str. 3, N 1150, #160; K str. 3, AMSK TIVAT, #160 | LUKA BRKAN | driver-luka-brkan | AMSK TIVAT; YUGO; N 1150 |
| P str. 1, R.B. 10; R str. 2, kat. 1, #76; R str. 4, N 1150, #76; K str. 2, AMSK HERCEG NOVI, #76 | STEVAN MILOŠEV | driver-stevan-milosev | AMSK HERCEG NOVI; YUGO; N 1150 |
| P str. 1, R.B. 12; R str. 2, kat. 1, #200; R str. 4, N 1150, #200; K str. 1, AMSK AP SPORT, #200 | DRAGAN DŽOGAZ | driver-dragan-dzogaz | AMSK AP SPORT; YUGO 55; N 1150 |
| P str. 1, R.B. 9; R str. 2, kat. 1, #155; R str. 4, N 1150, #155; K str. 2, AMSK HERCEG NOVI, #155 | IGOR RADMILOVIĆ | driver-igor-radmilovic | AMSK HERCEG NOVI; YUGO; N 1150 |
| P str. 1, R.B. 11; R str. 2, kat. 1, #510; R str. 4, N 1150, #510; K str. 1, AMSK AP SPORT, #510 | STEVAN MILIČKOVIĆ | driver-stevan-milickovic | AMSK AP SPORT; YUGO 55; N 1150 |
| P str. 1, R.B. 2; R str. 2, kat. 1, #500; R str. 3, H 1000, #500; K str. 1, AMSK AP SPORT, #500 | NIKOLA TOŠKOVIĆ | driver-nikola-toskovic | AMSK AP SPORT; NISAN MICRA; H 1000 |
| P str. 1, R.B. 1; R str. 2, kat. 1, #81; R str. 3, H 1000, #81; K str. 2, AMSK HERCEG NOVI, #81 | VOJO RADANOVIĆ | driver-vojo-radanovic | AMSK HERCEG NOVI; PEUGEOT 106; H 1000 |
| P str. 1, R.B. 25; R str. 2, kat. 1, #18; R str. 4, N 1400, #18; K str. 2, AMSK HERCEG NOVI, #18 | ĐORĐE KOVAČEVIĆ | driver-dorde-kovacevic | AMSK HERCEG NOVI; PEUGEOT 106; N 1400 |
| P str. 1, R.B. 50; R str. 2, kat. 1, #25; R str. 6, E2SH, #25; K str. 1, AMSK KOTOR, #25 | ŽELJKO RADONJIĆ | driver-zeljko-radonjic | AMSK KOTOR; YUGO; E2SH |
| P str. 1, R.B. 66; R str. 2, kat. 1, #53; R str. 7, H 2000, #53; K str. 1, AMSK PODI, #53 | LAZAR MUJOVIĆ | driver-lazar-mujovic | AMSK PODI; BMW E46; H 2000 |
| P str. 1, R.B. 27; R str. 2, kat. 1, #75; R str. 4, N 1400, #75; K str. 3, AMSK VLAHOVIĆ, #75 | NIKOLA VLAHOVIĆ | driver-nikola-vlahovic | AMSK VLAHOVIĆ; PEUGEOT 106; N 1400 |
| P str. 1, R.B. 19; R str. 2, kat. 1, #78; R str. 4, A 1150, #78; K str. 1, AMSK AP SPORT, #78 | MILAN ROGANOVIĆ | driver-milan-roganovic | AMSK AP SPORT; FIAT SIECENTO; A 1150 |
| P str. 1, R.B. 55; R str. 2, kat. 1, #80; R str. 6, N 2000, #80; K str. 3, AMSK GORŠTAK, #80 | MARKO JANKOVIĆ | driver-marko-jankovic | AMSK GORŠTAK; HONDA  TYPER; N 2000 |
| P str. 1, R.B. 33; R str. 2, kat. 1, #86; R str. 5, H 1400, #86; K str. 3, AMSK TIVAT, #86 | DARKO IVANOVIĆ | driver-darko-ivanovic | AMSK TIVAT; PEUGEOT 106; H 1400 |
| P str. 1, R.B. 21; R str. 2, kat. 1, #89; R str. 5, N 1400, #89; K str. 2, AMSK CRMNICA, #89 | DEJAN ĐONOVIĆ | driver-dejan-donovic | AMSK CRMNICA; PEUGEOT 106; N 1400 |
| P str. 1, R.B. 16; R str. 2, kat. 1, #92; R str. 4, A 1150, #92; K str. 2, AMSK PODI, #92 | BOŽIDAR DODEROVIĆ | driver-bozidar-doderovic | AMSK PODI; YUGO; A 1150 |
| P str. 1, R.B. 48; R str. 2, kat. 1, #94; R str. 6, H 1600, #94; K str. 2, AMSK CRMNICA, #94 | ZLATKO SINDIK | driver-zlatko-sindik | AMSK CRMNICA; ZASTAVA YUGO; H 1600 |
| P str. 1, R.B. 37; R str. 2, kat. 1, #152; R str. 5, H 1400, #152; K str. 1, AMSK AP SPORT, #152 | MILAN BRACANOVIĆ | driver-milan-bracanovic | AMSK AP SPORT; PEUGEOT 106; H 1400 |
| P str. 1, R.B. 4; R str. 2, kat. 1, #153; R str. 4, N 1150, #153; K str. 3, AMKK LOVĆEN R.T., #153 | MATIJA MARKOVIĆ | driver-matija-markovic | AMKK LOVĆEN R.T.; YUGO; N 1150 |
| P str. 1, R.B. 13; R str. 3, kat. 1, #197; R str. 4, A 1150, #197; K str. 3, AMSK GORŠTAK, #197 | SAVA NIKČEVIĆ | driver-sava-nikcevic | AMSK GORŠTAK; YUGO 55; A 1150 |
| P str. 1, R.B. 8; R str. 3, kat. 1, #208; R str. 4, N 1150, #208; K str. 4, AMSK NILOVIĆ RACING, #208 | PETAR ZUROVAC | driver-petar-zurovac | AMSK NILOVIĆ RACING; ZASTAVA YUGO; N 1150 |
| P str. 1, R.B. 44; R str. 3, kat. 1, #220; R str. 6, A 1600, #220; K str. 2, AMSK HERCEG NOVI, #220 | KRSTO RADOVIĆ | driver-krsto-radovic | AMSK HERCEG NOVI; CITROEN C2; A 1600 |
| P str. 1, R.B. 20; R str. 3, kat. 1, #224; R str. 5, N 1400, #224; K str. 4, AMSK NILOVIĆ RACING, #224 | MILJAN NIKOLIĆ | driver-miljan-nikolic | AMSK NILOVIĆ RACING; PEUGEOT 106; N 1400 |
| P str. 1, R.B. 34; R str. 3, kat. 1, #230; R str. 5, H 1400, #230; K str. 4, AMSK NILOVIĆ RACING, #230 | LAZAR KOSTIĆ | driver-lazar-kostic | AMSK NILOVIĆ RACING; PEUGEOT 106; H 1400 |
| P str. 1, R.B. 17; R str. 3, kat. 1, #236; R str. 4, A 1150, #236; K str. 2, AMSK PODI, #236 | DRAGAN MUDREŠA | driver-dragan-mudresa | AMSK PODI; ZASTAVA YUGO; A 1150 |
| P str. 1, R.B. 70; R str. 3, kat. 1, #237; R str. 7, H 2000+, #237; K str. 3, AMK PODGORICA RT, #237 | MILE MALOVIĆ | driver-mile-malovic | AMK PODGORICA RT; SEAT LEON; H 2000+ |
| P str. 1, R.B. 7; R str. 3, kat. 1, #287; R str. 4, N 1150, #287; K str. 3, AMSK TIVAT, #287 | MILORAD RAŽNATOVIĆ | driver-milorad-raznatovic | AMSK TIVAT; YUGO; N 1150 |
| P str. 1, R.B. 68; R str. 3, kat. 1, #333; R str. 7, H 2000+, #333; K str. 4, AMSK NILOVIĆ RACING, #333 | DEJAN ŠUŠKAVČEVIĆ | driver-dejan-suskavcevic | AMSK NILOVIĆ RACING; BMW; H 2000+ |
| P str. 1, R.B. 15; R str. 3, kat. 1, #442; R str. 4, A 1150, #442; K str. 2, AMSK PODI, #442 | SPASOJE NIKOLIĆ | driver-spasoje-nikolic | AMSK PODI; ZASTAVA YUGO; A 1150 |
| P str. 1, R.B. 40; R str. 3, kat. 1, #454; R str. 6, N 1600, #454; K str. 1, AMSK AP SPORT, #454 | RADIVOJE JANJUŠEVIĆ | driver-radivoje-janjusevic | AMSK AP SPORT; PEUGEOT 106; N 1600 |
| P str. 1, R.B. 5; R str. 3, kat. 1, #511; R str. 4, N 1150, #511; K str. 3, AMKK LOVĆEN R.T., #511 | NIKOLA LAGATOR | driver-nikola-lagator | AMKK LOVĆEN R.T.; YUGO; N 1150 |
| P str. 2, R.B. 74; R str. 3, kat. 2, #21; R str. 7, D/E2/CN, #21; K str. 1, AMSK AP SPORT, #21 | ALDIN ĆOROVIĆ | driver-aldin-corovic | AMSK AP SPORT; GLOBAL; D/E2/CN |
| P str. 1, R.B. 72; R str. 3, kat. 2, #14; R str. 7, D/E2/CN, #14; K str. 3, AMSK GORŠTAK, #14 | BAĆKO VUJOVIĆ | driver-backo-vujovic | AMSK GORŠTAK; NORMA N202B; D/E2/CN |
| P str. 2, R.B. 73; R str. 3, kat. 2, #52; R str. 7, D/E2/CN, #52; K str. 1, AMSK KOTOR, #52 | BOŽIDAR JABLAN | driver-bozidar-jablan | AMSK KOTOR; GLOBAL LIGHT; D/E2/CN |

## Prihvaćeni MNE/AKSCG profili bez povezivanja s trkom

| Lokator | Ime | Predloženi archive_key | Broj / klub / vozilo / kontekst |
| --- | --- | --- | --- |
| P str. 1, R.B. 47 | PERICA MARĐONOVIĆ | driver-perica-mardonovic | 21; AMSK SUTORMAN; CITROEN AX; 1 H H 1600 AKSCG KUP KUP |
| P str. 1, R.B. 58 | RADOVAN RADANOVIĆ | driver-radovan-radanovic | 930; AMSK HERCEG NOVI; OPEL ASTRA; 1 A A 2000 AKSCG KUP KUP |
| P str. 1, R.B. 60 | MILAN JAKŠIĆ | driver-milan-jaksic | 44; AMSK KOTOR; HONDA TYPER; 1 H H 2000 AKSCG KUP KUP |
| P str. 1, R.B. 62 | SEAD SPAHIĆ | driver-sead-spahic | 137; AMSK CRMNICA; VW GOLF II; 1 H H 2000 AKSCG KUP KUP |
| P str. 1, R.B. 64 | VLADO BURIĆ | driver-vlado-buric | 150; AMSK KOTOR; HONDA CIVIC; 1 H H 2000 AKSCG KUP KUP |
| P str. 1, R.B. 65 | ZORAN RAJHEL | driver-zoran-rajhel | NULL; AMSK KOTOR; VW GOLF II; 1 H H 2000 AKSCG KUP KUP |

Ovi redovi dokazuju prijavu, ne start, DNS ili konačnu klasu. Njihove klubove i vozila kao predloge takođe obuhvataju donje tabele.

## Ostalih 59 prihvaćenih profila bez povezivanja s trkom

| Lokator | Ime | Predloženi archive_key | Nac / broj / klub / vozilo / izvorni kontekst |
| --- | --- | --- | --- |
| P str. 2, R.B. 75 | ZLATKO BROZOVIĆ | driver-zlatko-brozovic | HR; 466; AK BELSON NOSTALGIJA; OPEL MANTA; PV A 3 BPPA |
| P str. 2, R.B. 76 | NENAD GRŽANČIĆ | driver-nenad-grzancic | HR; 432; AKK ROGOZNICA; ZASTAVA 750; PV A 1 BPPA |
| P str. 2, R.B. 77 | ANTE GAĆINA | driver-ante-gacina | HR; 409; AKK ROGOZNICA; VW GOLF1; PV A 3 BPPA |
| P str. 2, R.B. 78 | ŠIME ŠIMUNOVIĆ | driver-sime-simunovic | HR; 412; AK RAGUSA RACING; VW GOLF1; PV A 3 BPPA |
| P str. 2, R.B. 79 | MARIJAN HRGOVIĆ | driver-marijan-hrgovic | HR; 470; AK BELSON NOSTALGIJA; ŠKODA FAVORIT; PV B 1 BPPA |
| P str. 2, R.B. 80 | JURICA IVANOVIĆ | driver-jurica-ivanovic | HR; 431; AK SKRADIN; OPEL CORSA; PV B 2 BPPA |
| P str. 2, R.B. 81 | SANDRO POLDRUGOVAC | driver-sandro-poldrugovac | HR; 414; AK BUZET AUTOSPORT; FIAT RITMO 130TC; PV B 3 BPPA |
| P str. 2, R.B. 82 | MARO SLADE | driver-maro-slade | HR; 10; AK DUBROVNIK RACING; MITSUBISHI EVO X; 1 1 PH |
| P str. 2, R.B. 83 | DEJAN DIMITRIJEVIĆ | driver-dejan-dimitrijevic | HR; 1; AK FLY WITH KOLMAR; SEAT LEON; 1 1 1 28 PH |
| P str. 2, R.B. 84 | VILIAM PRODAN | driver-viliam-prodan | HR; 17; AK OPATIJA MOTORSPORT; PORSCHE; 1 2 PH |
| P str. 2, R.B. 85 | NIKO PULIĆ | driver-niko-pulic | HR; 44; AK KONAVLE; LAMBORGINI; 1 2 66 PH |
| P str. 2, R.B. 86 | SIMERON JARGIĆ | driver-simeron-jargic | HR; NULL; AK KONAVLE; TOYOTA YARIS; 1 2 61 PH J |
| P str. 2, R.B. 87 | FRANO DUBRETA | driver-frano-dubreta | HR; NULL; AK DUBROVNIK RACING; CUPRA COMPETITION; 1 2 PH |
| P str. 2, R.B. 88 | LUKA DUBRETA | driver-luka-dubreta | HR; NULL; AK DUBROVNIK RACING; BMW M3 E90; 1 2 PH |
| P str. 2, R.B. 89 | TAMAS TOMCSANYI | driver-tamas-tomcsanyi | HR; 5; AK DUBROVNIK RACING; LADA VAZ 2101; 1 2 PH |
| P str. 2, R.B. 90 | PETER JORDAN | driver-peter-jordan | HR; 59; AK PELJEŠAC RACING; MAZDA MX5 turbo; 1 2 79 PH |
| P str. 2, R.B. 91 | JOSIP PAVIĆ | driver-josip-pavic | HR; 132; AK ONEUM; SUZUKI SWIFT; 1 3 120 PH |
| P str. 2, R.B. 92 | ANTE BARIĆ | driver-ante-baric | HR; 93; AKK ROGOZNICA; BMW; 1 3 101 PH |
| P str. 2, R.B. 93 | IVAN BATALIĆ | driver-ivan-batalic | HR; 26; SPLITSKI AUTO KLUB; HONDA CIVIC Type R; 1 3 110 PH |
| P str. 2, R.B. 94 | STIJEPO MANENICA | driver-stijepo-manenica | HR; NULL; AK DUBROVNIK RACING; PEUGEUOT 308; 1 3 PH |
| P str. 2, R.B. 95 | MLADEN ĐAJIĆ | driver-mladen-dajic | HR; 21; AK ŽUPA DUBROVAČKA; DACIA SANDERO; 1 3 107 PH |
| P str. 2, R.B. 96 | FRAN KRANJAC | driver-fran-kranjac | HR; NULL; AK KONAVLE; FORD FIETS; 1 4 152 PH |
| P str. 2, R.B. 97 | ANĐELKO VRKIĆ | driver-andelko-vrkic | HR; 131; AK ONEUM; RENAULT CLIO; 1 4 142 PH |
| P str. 2, R.B. 98 | ANTONIO TOMIĆ | driver-antonio-tomic | HR; 140; AK ONEUM; PEUGEUOT 106; 1 4 148 PH |
| P str. 2, R.B. 99 | BEGO ŠAKANOVIĆ | driver-bego-sakanovic | HR; 80; AK SKRADIN; RENAULT CLIO; 1 4 128 PH |
| P str. 2, R.B. 100 | IVICA DRAGOŠEVIĆ | driver-ivica-dragosevic | HR; 122; AK SPLIT MOTORSPORT; PEUGEUOT 205 GTI; 1 4 156 PH |
| P str. 2, R.B. 101 | BORIS KATIĆ | driver-boris-katic | HR; 7; AK ŽUPA DUBROVAČKA; CITROEN SAXO; 1 4 128 PH |
| P str. 2, R.B. 102 | GORAN SIKIRICA | driver-goran-sikirica | HR; 166; AK ŽUPA DUBROVAČKA; Renault Clio; 1 4 128 PH |
| P str. 2, R.B. 103 | ANDREJ BULE | driver-andrej-bule | HR; 171; AK BIOKOVO; GOLF MK2; 1 5 5A 176 PH |
| P str. 2, R.B. 104 | KORNEL NYEKI | driver-kornel-nyeki | HR; 141; AK PELJEŠAC RACING; OPEL CORSA GSI 1.6 - 16V; 1 5 176 PH |
| P str. 2, R.B. 105 | DINO DOMINIKOVIĆ | driver-dino-dominikovic | HR; 149; AK PELJEŠAC RACING; ZASTAVA YUGO 1,6; 1 5 164 PH |
| P str. 2, R.B. 106 | MARIJO BAROVIĆ | driver-marijo-barovic | HR; NULL; AK ŽUPA DUBROVAČKA; PEUGEUOT 106; 1 5 165 PH |
| P str. 2, R.B. 107 | NINO JELIČIĆ | driver-nino-jelicic | HR; 190; AK SKRADIN; HONDA CIVIC; 1 6 194 PH |
| P str. 2, R.B. 108 | IVAN PEJKOVIĆ | driver-ivan-pejkovic | HR; 65; AKK ROGOZNICA; HONDA CIVIC; 1 6 197 PH |
| P str. 2, R.B. 109 | MATO MILKOVIĆ | driver-mato-milkovic | HR; 13; AK DUBROVNIK RACING; HONDA CIVIC; 1 6 PH |
| P str. 2, R.B. 110 | MILAN TATALIĆ | driver-milan-tatalic | HR; NULL; AK DUBROVNIK RACING; CITROEN SAXO; 1 6 PH |
| P str. 2, R.B. 111 | MARO FRANIĆ | driver-maro-franic | HR; 30; AK DUBROVNIK RACING; HONDA TYPE R; 1 6 PH |
| P str. 2, R.B. 112 | NIKOLA RADNJIĆ | driver-nikola-radnjic | HR; 15; AK DUBROVNIK RACING; HONDA TYPE R; 1 6 PH |
| P str. 2, R.B. 113 | IGOR NIKOLAUS | driver-igor-nikolaus | HR; 208; AK OZALJ; PEUGEUOT 106; 1 6 192 PH |
| P str. 2, R.B. 114 | DENI KMETOVIĆ | driver-deni-kmetovic | HR; NULL; AK ŽUPA DUBROVAČKA; ŠKODA OKTAVIA; 1 6 209 PH |
| P str. 2, R.B. 115 | STIPE BEKAVAC | driver-stipe-bekavac | HR; NULL; AK SOLIN; ŠKODA OCTAVIA; 1 6 191 PH |
| P str. 2, R.B. 116 | TONI BEBIĆ | driver-toni-bebic | HR; 258; AK METKOVIĆ RACING; HONDA CIVIC; 1 7 257 PH |
| P str. 2, R.B. 117 | IVAN GROZDANIĆ | driver-ivan-grozdanic | HR; 116; AKK ROGOZNICA; VW POLO; 1 7 231 PH |
| P str. 2, R.B. 118 | IVAN ŽIVKOVIĆ | driver-ivan-zivkovic | HR; 270; AKK ROGOZNICA; ŠKODA FABIA; 1 7 231 PH |
| P str. 2, R.B. 119 | DINO ANTIČEVIĆ | driver-dino-anticevic | HR; 231; SPLITSKI AUTO KLUB; HONDA CIVIC 1,6; 1 7 231 PH |
| P str. 2, R.B. 120 | MARIN GRLJUŠIĆ | driver-marin-grljusic | HR; 267; AK PELJEŠAC RACING; CITROEN SAXO 1.6 VTS; 1 7 232 PH |
| P str. 2, R.B. 121 | JURE MORNAR | driver-jure-mornar | HR; NULL; AK SOLIN; HONDA CIVIC; 1 7 231 PH |
| P str. 2, R.B. 122 | IVAN BEKAVAC | driver-ivan-bekavac | HR; NULL; AK SOLIN; CITROEN C2; E1-Moto PH |
| P str. 2, R.B. 123 | PETAR DAJAK | driver-petar-dajak | HR; 36; AK SPLIT MOTORSPORT; RENAULT TATUUS; 2 Proto 19 PH |
| P str. 2, R.B. 124 | FILIP MAGDIĆ | driver-filip-magdic | HR; 33; AK FLY WITH KOLMAR; GIBSON; 2 Proto 20 PH |
| P str. 2, R.B. 125 | ALEKSANDAR VIDOSAVLJEVIĆ | driver-aleksandar-vidosavljevic | SRB; 135; ASK RID SPORT; MITSUBISHI EVO IX; 1 2000+ KUP KUP |
| P str. 2, R.B. 126 | MATIJA BRUSNSKOLE | driver-matija-brusnskole | SLO; NULL; ŠD SIGMA MOTORSPORT; ZASTAVA YUGO; 1 1400 KUP KUP |
| P str. 2, R.B. 127 | HYSEIN ISENI | driver-hysein-iseni | KOS; NULL; AK FERIZAJ; PEUGEUOT 106; 1 1400 KUP KUP |
| P str. 2, R.B. 128 | BURIM ASLLANI | driver-burim-asllani | KOS; NULL; AK FERIZAJ; VOLVO 850; 1 2000+ KUP KUP |
| P str. 2, R.B. 129 | LEUTRIM BYTYQI | driver-leutrim-bytyqi | KOS; NULL; AK FERIZAJ; PEUGEUOT 106; 1 1400 KUP KUP |
| P str. 2, R.B. 130 | VALON REXHEPI | driver-valon-rexhepi | KOS; NULL; NULL; OPEL ASTRA; 1 2000 KUP KUP |
| P str. 2, R.B. 131 | SMAIL SMANI | driver-smail-smani | KOS; NULL; NULL; OPEL ASTRA; 1 2000 KUP KUP |
| P str. 2, R.B. 132 | BRADHIL CANOLLI | driver-bradhil-canolli | KOS; NULL; NULL; RADICAL; 2 Formula KUP KUP |
| P str. 2, R.B. 133 | GENTJAM SHAQIRI | driver-gentjam-shaqiri | ALB; NULL; NACUT RACING; TATUUS MASTER; 2 Formula KUP KUP |

Nac je izvorna oznaka iz P; nije zaključak o državljanstvu osobe ili državi kluba. KOS, SRB, SLO i ALB ne pretvarati automatski u drugačije šifre bez potvrde. Za VALON REXHEPI, SMAIL SMANI i BRADHIL CANOLLI klub nije naveden: ostaje NULL. Numeričke klase/istorijske grupe su sačuvane u kontekstu; bez uspostavljene pripadnosti prvenstvu ne kreirati generičke klase 1/2/3/1400/2000 koje bi spojile različite sisteme.

## Popis izvornih klubova i ranijih predloga — u migraciju ulazi 12 klubova iz R

| Tačan zapis / predloženi naziv | Predloženi archive_key | Obim / svi lokatori |
| --- | --- | --- |
| AMK MONTERACING → AMK MONTERACING | club-amk-monteracing | Konačni rezultat; P str. 1, R.B. 41; R str. 2, kat. 1, #48 |
| AMK PODGORICA RT → AMK PODGORICA RT | club-amk-podgorica-rt | Konačni rezultat; P str. 1, R.B. 22; P str. 1, R.B. 70; R str. 1, kat. 1, #231; R str. 3, kat. 1, #237 |
| AMKK LOVĆEN R.T. → AMKK LOVĆEN R.T. | club-amkk-lovcen-r-t | Konačni rezultat; P str. 1, R.B. 4; P str. 1, R.B. 5; P str. 1, R.B. 14; R str. 1, kat. 1, #318; R str. 2, kat. 1, #153; R str. 3, kat. 1, #511 |
| AMSK AP SPORT / AMSK APSPORT → AMSK AP SPORT | club-amsk-ap-sport | Konačni rezultat; P str. 1, R.B. 2; P str. 1, R.B. 3; P str. 1, R.B. 11; P str. 1, R.B. 12; P str. 1, R.B. 19; P str. 1, R.B. 37; P str. 1, R.B. 39; P str. 1, R.B. 40; P str. 1, R.B. 59; P str. 2, R.B. 74; R str. 1, kat. 1, #29; R str. 2, kat. 1, #121; R str. 2, kat. 1, #506; R str. 2, kat. 1, #200; R str. 2, kat. 1, #510; R str. 2, kat. 1, #500; R str. 2, kat. 1, #78; R str. 2, kat. 1, #152; R str. 3, kat. 1, #454; R str. 3, kat. 2, #21 |
| AMSK CRMNICA / AMSKCRMNICA → AMSK CRMNICA | club-amsk-crmnica | Konačni rezultat; P str. 1, R.B. 21; P str. 1, R.B. 28; P str. 1, R.B. 29; P str. 1, R.B. 42; P str. 1, R.B. 43; P str. 1, R.B. 48; P str. 1, R.B. 51; P str. 1, R.B. 57; P str. 1, R.B. 62; P str. 1, R.B. 69; R str. 1, kat. 1, #88; R str. 1, kat. 1, #7; R str. 1, kat. 1, #217; R str. 1, kat. 1, #43; R str. 2, kat. 1, #95; R str. 2, kat. 1, #119; R str. 2, kat. 1, #96; R str. 2, kat. 1, #89; R str. 2, kat. 1, #94 |
| AMSK GORŠTAK → AMSK GORŠTAK | club-amsk-gorstak | Konačni rezultat; P str. 1, R.B. 13; P str. 1, R.B. 46; P str. 1, R.B. 53; P str. 1, R.B. 55; P str. 1, R.B. 72; R str. 1, kat. 1, #31; R str. 1, kat. 1, #262; R str. 2, kat. 1, #80; R str. 3, kat. 1, #197; R str. 3, kat. 2, #14 |
| AMSK HERCEG NOVI → AMSK HERCEG NOVI | club-amsk-herceg-novi | Konačni rezultat; P str. 1, R.B. 1; P str. 1, R.B. 9; P str. 1, R.B. 10; P str. 1, R.B. 23; P str. 1, R.B. 24; P str. 1, R.B. 25; P str. 1, R.B. 26; P str. 1, R.B. 30; P str. 1, R.B. 44; P str. 1, R.B. 49; P str. 1, R.B. 58; P str. 1, R.B. 63; R str. 1, kat. 1, #115; R str. 1, kat. 1, #13; R str. 1, kat. 1, #19; R str. 1, kat. 1, #104; R str. 1, kat. 1, #17; R str. 1, kat. 1, #151; R str. 2, kat. 1, #76; R str. 2, kat. 1, #155; R str. 2, kat. 1, #81; R str. 2, kat. 1, #18; R str. 3, kat. 1, #220 |
| AMSK KOTOR → AMSK KOTOR | club-amsk-kotor | Konačni rezultat; P str. 1, R.B. 32; P str. 1, R.B. 45; P str. 1, R.B. 50; P str. 1, R.B. 54; P str. 1, R.B. 60; P str. 1, R.B. 61; P str. 1, R.B. 64; P str. 1, R.B. 65; P str. 1, R.B. 71; P str. 2, R.B. 73; R str. 1, kat. 1, #16; R str. 1, kat. 1, #12; R str. 1, kat. 1, #26; R str. 1, kat. 1, #416; R str. 1, kat. 1, #91; R str. 2, kat. 1, #25; R str. 3, kat. 2, #52 |
| AMSK NILOVIĆ RACING → AMSK NILOVIĆ RACING | club-amsk-nilovic-racing | Konačni rezultat; P str. 1, R.B. 8; P str. 1, R.B. 20; P str. 1, R.B. 34; P str. 1, R.B. 68; R str. 3, kat. 1, #208; R str. 3, kat. 1, #224; R str. 3, kat. 1, #230; R str. 3, kat. 1, #333 |
| AMSK PODI → AMSK PODI | club-amsk-podi | Konačni rezultat; P str. 1, R.B. 15; P str. 1, R.B. 16; P str. 1, R.B. 17; P str. 1, R.B. 18; P str. 1, R.B. 31; P str. 1, R.B. 35; P str. 1, R.B. 36; P str. 1, R.B. 28 (38. fizički red; štampano 28); P str. 1, R.B. 52; P str. 1, R.B. 56; P str. 1, R.B. 66; P str. 1, R.B. 67; R str. 1, kat. 1, #36; R str. 1, kat. 1, #68; R str. 1, kat. 1, #71; R str. 1, kat. 1, #23; R str. 1, kat. 1, #222; R str. 1, kat. 1, #30; R str. 2, kat. 1, #113; R str. 2, kat. 1, #33; R str. 2, kat. 1, #53; R str. 2, kat. 1, #92; R str. 3, kat. 1, #236; R str. 3, kat. 1, #442 |
| AMSK TIVAT / AMSKTIVAT → AMSK TIVAT | club-amsk-tivat | Konačni rezultat; P str. 1, R.B. 6; P str. 1, R.B. 7; P str. 1, R.B. 33; R str. 2, kat. 1, #160; R str. 2, kat. 1, #86; R str. 3, kat. 1, #287 |
| AMSK VLAHOVIĆ → AMSK VLAHOVIĆ | club-amsk-vlahovic | Konačni rezultat; P str. 1, R.B. 27; R str. 2, kat. 1, #75 |
| AK BELSON NOSTALGIJA → AK BELSON NOSTALGIJA | club-ak-belson-nostalgija | Samo prijave; obim nije odlučen; P str. 2, R.B. 75; P str. 2, R.B. 79 |
| AK BIOKOVO → AK BIOKOVO | club-ak-biokovo | Samo prijave; obim nije odlučen; P str. 2, R.B. 103 |
| AK BUZET AUTOSPORT → AK BUZET AUTOSPORT | club-ak-buzet-autosport | Samo prijave; obim nije odlučen; P str. 2, R.B. 81 |
| AK DUBROVNIK RACING → AK DUBROVNIK RACING | club-ak-dubrovnik-racing | Samo prijave; obim nije odlučen; P str. 2, R.B. 82; P str. 2, R.B. 87; P str. 2, R.B. 88; P str. 2, R.B. 89; P str. 2, R.B. 94; P str. 2, R.B. 109; P str. 2, R.B. 110; P str. 2, R.B. 111; P str. 2, R.B. 112 |
| AK FERIZAJ → AK FERIZAJ | club-ak-ferizaj | Samo prijave; obim nije odlučen; P str. 2, R.B. 127; P str. 2, R.B. 128; P str. 2, R.B. 129 |
| AK FLY WITH KOLMAR → AK FLY WITH KOLMAR | club-ak-fly-with-kolmar | Samo prijave; obim nije odlučen; P str. 2, R.B. 83; P str. 2, R.B. 124 |
| AK KONAVLE → AK KONAVLE | club-ak-konavle | Samo prijave; obim nije odlučen; P str. 2, R.B. 85; P str. 2, R.B. 86; P str. 2, R.B. 96 |
| AK METKOVIĆ RACING → AK METKOVIĆ RACING | club-ak-metkovic-racing | Samo prijave; obim nije odlučen; P str. 2, R.B. 116 |
| AK ONEUM → AK ONEUM | club-ak-oneum | Samo prijave; obim nije odlučen; P str. 2, R.B. 91; P str. 2, R.B. 97; P str. 2, R.B. 98 |
| AK OPATIJA MOTORSPORT → AK OPATIJA MOTORSPORT | club-ak-opatija-motorsport | Samo prijave; obim nije odlučen; P str. 2, R.B. 84 |
| AK OZALJ → AK OZALJ | club-ak-ozalj | Samo prijave; obim nije odlučen; P str. 2, R.B. 113 |
| AK PELJEŠAC RACING → AK PELJEŠAC RACING | club-ak-peljesac-racing | Samo prijave; obim nije odlučen; P str. 2, R.B. 90; P str. 2, R.B. 104; P str. 2, R.B. 105; P str. 2, R.B. 120 |
| AK RAGUSA RACING → AK RAGUSA RACING | club-ak-ragusa-racing | Samo prijave; obim nije odlučen; P str. 2, R.B. 78 |
| AK SKRADIN → AK SKRADIN | club-ak-skradin | Samo prijave; obim nije odlučen; P str. 2, R.B. 80; P str. 2, R.B. 99; P str. 2, R.B. 107 |
| AK SOLIN → AK SOLIN | club-ak-solin | Samo prijave; obim nije odlučen; P str. 2, R.B. 115; P str. 2, R.B. 121; P str. 2, R.B. 122 |
| AK SPLIT MOTORSPORT → AK SPLIT MOTORSPORT | club-ak-split-motorsport | Samo prijave; obim nije odlučen; P str. 2, R.B. 100; P str. 2, R.B. 123 |
| AK ŽUPA DUBROVAČKA → AK ŽUPA DUBROVAČKA | club-ak-zupa-dubrovacka | Samo prijave; obim nije odlučen; P str. 2, R.B. 95; P str. 2, R.B. 101; P str. 2, R.B. 102; P str. 2, R.B. 106; P str. 2, R.B. 114 |
| AKK ROGOZNICA → AKK ROGOZNICA | club-akk-rogoznica | Samo prijave; obim nije odlučen; P str. 2, R.B. 76; P str. 2, R.B. 77; P str. 2, R.B. 92; P str. 2, R.B. 108; P str. 2, R.B. 117; P str. 2, R.B. 118 |
| AMSK SUTORMAN → AMSK SUTORMAN | club-amsk-sutorman | Samo prijave; obim nije odlučen; P str. 1, R.B. 47 |
| ASK RID SPORT → ASK RID SPORT | club-ask-rid-sport | Samo prijave; obim nije odlučen; P str. 2, R.B. 125 |
| NACUT RACING → NACUT RACING | club-nacut-racing | Samo prijave; obim nije odlučen; P str. 2, R.B. 133 |
| ŠD SIGMA MOTORSPORT → ŠD SIGMA MOTORSPORT | club-sd-sigma-motorsport | Samo prijave; obim nije odlučen; P str. 2, R.B. 126 |
| SPLITSKI AUTO KLUB → SPLITSKI AUTO KLUB | club-splitski-auto-klub | Samo prijave; obim nije odlučen; P str. 2, R.B. 93; P str. 2, R.B. 119 |

Predlozi aliasa za AMSKTIVAT → AMSK TIVAT, AMSKCRMNICA → AMSK CRMNICA i AMSK APSPORT → AMSK AP SPORT su potkrijepljeni istim prijavnim redom, klubom u R i K, te čekaju odobrenje. Državu/federaciju stranih klubova ne izvoditi iz oznake Nac vozača.

## Popis izvornih opisa vozila i ranijih predloga — konačne reference su iznad

| Tačni zapisi → predloženi naziv | Predloženi archive_key | Obim / svi lokatori |
| --- | --- | --- |
| BMW → BMW | vehicle-bmw | Konačni rezultat; P str. 1, R.B. 68; P str. 2, R.B. 92; R str. 3, kat. 1, #333 |
| BMW E46 → BMW E46 | vehicle-bmw-e46 | Konačni rezultat; P str. 1, R.B. 66; R str. 2, kat. 1, #53 |
| BMW M3 → BMW M3 | vehicle-bmw-m3 | Konačni rezultat; P str. 1, R.B. 69; R str. 1, kat. 1, #217 |
| CITROEN C2 → CITROEN C2 | vehicle-citroen-c2 | Konačni rezultat; P str. 1, R.B. 43; P str. 1, R.B. 44; P str. 2, R.B. 122; R str. 1, kat. 1, #7; R str. 3, kat. 1, #220 |
| CITROEN SAXO → CITROEN SAXO | vehicle-citroen-saxo | Konačni rezultat; P str. 1, R.B. 28 (38. fizički red; štampano 28); P str. 1, R.B. 39; P str. 1, R.B. 41; P str. 1, R.B. 42; P str. 2, R.B. 101; P str. 2, R.B. 110; R str. 1, kat. 1, #29; R str. 2, kat. 1, #48; R str. 2, kat. 1, #33; R str. 2, kat. 1, #96 |
| FIAT 500 → FIAT 500 | vehicle-fiat-500 | Konačni rezultat; P str. 1, R.B. 51; R str. 1, kat. 1, #88 |
| FIAT 750 → FIAT 750 | vehicle-fiat-750 | Konačni rezultat; P str. 1, R.B. 52; R str. 1, kat. 1, #36 |
| FIAT SIECENTO → FIAT SIECENTO | vehicle-fiat-siecento | Konačni rezultat; P str. 1, R.B. 19; R str. 2, kat. 1, #78 |
| GLOBAL → GLOBAL | vehicle-global | Konačni rezultat; P str. 2, R.B. 74; R str. 3, kat. 2, #21 |
| GLOBAL LIGHT → GLOBAL LIGHT | vehicle-global-light | Konačni rezultat; P str. 2, R.B. 73; R str. 3, kat. 2, #52 |
| HONDA CIVIC → HONDA CIVIC | vehicle-honda-civic | Konačni rezultat; P str. 1, R.B. 45; P str. 1, R.B. 64; P str. 2, R.B. 107; P str. 2, R.B. 108; P str. 2, R.B. 109; P str. 2, R.B. 116; P str. 2, R.B. 121; R str. 1, kat. 1, #416 |
| HONDA CIVIC TYPER / HONDA  CIVIC TYPER → HONDA CIVIC TYPER | vehicle-honda-civic-typer | Konačni rezultat; P str. 1, R.B. 56; P str. 1, R.B. 57; R str. 1, kat. 1, #68; R str. 1, kat. 1, #43 |
| HONDA TYPER / HONDA  TYPER → HONDA TYPER | vehicle-honda-typer | Konačni rezultat; P str. 1, R.B. 54; P str. 1, R.B. 55; P str. 1, R.B. 60; P str. 1, R.B. 67; R str. 1, kat. 1, #26; R str. 2, kat. 1, #113; R str. 2, kat. 1, #80 |
| NISAN MICRA → NISAN MICRA | vehicle-nisan-micra | Konačni rezultat; P str. 1, R.B. 2; R str. 2, kat. 1, #500 |
| NORMA N202B → NORMA N202B | vehicle-norma-n202b | Konačni rezultat; P str. 1, R.B. 72; R str. 3, kat. 2, #14 |
| OPEL ASTRA → OPEL ASTRA | vehicle-opel-astra | Konačni rezultat; P str. 1, R.B. 58; P str. 1, R.B. 59; P str. 2, R.B. 130; P str. 2, R.B. 131; R str. 2, kat. 1, #506 |
| OPEL CORSA → OPEL CORSA | vehicle-opel-corsa | Konačni rezultat; P str. 1, R.B. 49; P str. 2, R.B. 80; R str. 1, kat. 1, #151 |
| PEUGEOT 106 / PEUGEUOT 106 → PEUGEOT 106 | vehicle-peugeot-106 | Konačni rezultat; P str. 1, R.B. 1; P str. 1, R.B. 20; P str. 1, R.B. 21; P str. 1, R.B. 22; P str. 1, R.B. 23; P str. 1, R.B. 25; P str. 1, R.B. 26; P str. 1, R.B. 27; P str. 1, R.B. 30; P str. 1, R.B. 32; P str. 1, R.B. 33; P str. 1, R.B. 34; P str. 1, R.B. 37; P str. 1, R.B. 40; P str. 1, R.B. 46; P str. 2, R.B. 98; P str. 2, R.B. 106; P str. 2, R.B. 113; P str. 2, R.B. 127; P str. 2, R.B. 129; R str. 1, kat. 1, #31; R str. 1, kat. 1, #115; R str. 1, kat. 1, #91; R str. 1, kat. 1, #13; R str. 1, kat. 1, #231; R str. 1, kat. 1, #17; R str. 2, kat. 1, #81; R str. 2, kat. 1, #18; R str. 2, kat. 1, #75; R str. 2, kat. 1, #86; R str. 2, kat. 1, #89; R str. 2, kat. 1, #152; R str. 3, kat. 1, #224; R str. 3, kat. 1, #230; R str. 3, kat. 1, #454 |
| PEUGEOT 306 → PEUGEOT 306 | vehicle-peugeot-306 | Konačni rezultat; P str. 1, R.B. 61; R str. 1, kat. 1, #12 |
| ROVER → ROVER | vehicle-rover | Konačni rezultat; P str. 1, R.B. 29; R str. 2, kat. 1, #119 |
| ROVER MG → ROVER MG | vehicle-rover-mg | Konačni rezultat; P str. 1, R.B. 31; R str. 1, kat. 1, #23 |
| SEAT LEON → SEAT LEON | vehicle-seat-leon | Konačni rezultat; P str. 1, R.B. 70; P str. 2, R.B. 83; R str. 3, kat. 1, #237 |
| ŠKODA FELICIA → ŠKODA FELICIA | vehicle-skoda-felicia | Konačni rezultat; P str. 1, R.B. 53; R str. 1, kat. 1, #262 |
| SUZUKI SWIFT → SUZUKI SWIFT | vehicle-suzuki-swift | Konačni rezultat; P str. 1, R.B. 35; P str. 2, R.B. 91; R str. 1, kat. 1, #71 |
| SUZUKI SWIFT GTI → SUZUKI SWIFT GTI | vehicle-suzuki-swift-gti | Konačni rezultat; P str. 1, R.B. 36; R str. 1, kat. 1, #222 |
| VW GOLF I → VW GOLF I | vehicle-vw-golf-i | Konačni rezultat; P str. 1, R.B. 63; R str. 1, kat. 1, #104 |
| VW GOLF V → VW GOLF V | vehicle-vw-golf-v | Konačni rezultat; P str. 1, R.B. 71; R str. 1, kat. 1, #16 |
| VW POLO → VW POLO | vehicle-vw-polo | Konačni rezultat; P str. 1, R.B. 28; P str. 2, R.B. 117; R str. 2, kat. 1, #95 |
| WV POLO → WV POLO | vehicle-wv-polo | Konačni rezultat; P str. 1, R.B. 24; R str. 1, kat. 1, #19 |
| YUGO → YUGO | vehicle-yugo | Konačni rezultat; P str. 1, R.B. 4; P str. 1, R.B. 5; P str. 1, R.B. 6; P str. 1, R.B. 7; P str. 1, R.B. 9; P str. 1, R.B. 10; P str. 1, R.B. 14; P str. 1, R.B. 16; P str. 1, R.B. 50; R str. 1, kat. 1, #318; R str. 2, kat. 1, #160; R str. 2, kat. 1, #76; R str. 2, kat. 1, #155; R str. 2, kat. 1, #25; R str. 2, kat. 1, #92; R str. 2, kat. 1, #153; R str. 3, kat. 1, #287; R str. 3, kat. 1, #511 |
| YUGO 55 → YUGO 55 | vehicle-yugo-55 | Konačni rezultat; P str. 1, R.B. 3; P str. 1, R.B. 11; P str. 1, R.B. 12; P str. 1, R.B. 13; R str. 2, kat. 1, #121; R str. 2, kat. 1, #200; R str. 2, kat. 1, #510; R str. 3, kat. 1, #197 |
| ZASTAVA YUGO → ZASTAVA YUGO | vehicle-zastava-yugo | Konačni rezultat; P str. 1, R.B. 8; P str. 1, R.B. 15; P str. 1, R.B. 17; P str. 1, R.B. 18; P str. 1, R.B. 48; P str. 2, R.B. 126; R str. 1, kat. 1, #30; R str. 2, kat. 1, #94; R str. 3, kat. 1, #208; R str. 3, kat. 1, #236; R str. 3, kat. 1, #442 |
| BMW M3 E90 → BMW M3 E90 | vehicle-bmw-m3-e90 | Samo prijave; obim nije odlučen; P str. 2, R.B. 88 |
| CITROEN AX → CITROEN AX | vehicle-citroen-ax | Samo prijave; obim nije odlučen; P str. 1, R.B. 47 |
| CITROEN SAXO 1.6 VTS → CITROEN SAXO 1.6 VTS | vehicle-citroen-saxo-1-6-vts | Samo prijave; obim nije odlučen; P str. 2, R.B. 120 |
| CUPRA COMPETITION → CUPRA COMPETITION | vehicle-cupra-competition | Samo prijave; obim nije odlučen; P str. 2, R.B. 87 |
| DACIA SANDERO → DACIA SANDERO | vehicle-dacia-sandero | Samo prijave; obim nije odlučen; P str. 2, R.B. 95 |
| FIAT RITMO 130TC → FIAT RITMO 130TC | vehicle-fiat-ritmo-130tc | Samo prijave; obim nije odlučen; P str. 2, R.B. 81 |
| FORD FIETS → FORD FIETS | vehicle-ford-fiets | Samo prijave; obim nije odlučen; P str. 2, R.B. 96 |
| GIBSON → GIBSON | vehicle-gibson | Samo prijave; obim nije odlučen; P str. 2, R.B. 124 |
| GOLF MK2 → GOLF MK2 | vehicle-golf-mk2 | Samo prijave; obim nije odlučen; P str. 2, R.B. 103 |
| HONDA CIVIC 1,6 → HONDA CIVIC 1,6 | vehicle-honda-civic-1-6 | Samo prijave; obim nije odlučen; P str. 2, R.B. 119 |
| HONDA CIVIC Type R → HONDA CIVIC TYPE R | vehicle-honda-civic-type-r | Samo prijave; obim nije odlučen; P str. 2, R.B. 93 |
| HONDA TYPE R → HONDA TYPE R | vehicle-honda-type-r | Samo prijave; obim nije odlučen; P str. 2, R.B. 111; P str. 2, R.B. 112 |
| LADA VAZ 2101 → LADA VAZ 2101 | vehicle-lada-vaz-2101 | Samo prijave; obim nije odlučen; P str. 2, R.B. 89 |
| LAMBORGINI → LAMBORGINI | vehicle-lamborgini | Samo prijave; obim nije odlučen; P str. 2, R.B. 85 |
| MAZDA MX5 turbo → MAZDA MX5 TURBO | vehicle-mazda-mx5-turbo | Samo prijave; obim nije odlučen; P str. 2, R.B. 90 |
| MITSUBISHI EVO IX → MITSUBISHI EVO IX | vehicle-mitsubishi-evo-ix | Samo prijave; obim nije odlučen; P str. 2, R.B. 125 |
| MITSUBISHI EVO X → MITSUBISHI EVO X | vehicle-mitsubishi-evo-x | Samo prijave; obim nije odlučen; P str. 2, R.B. 82 |
| OPEL CORSA GSI 1.6 - 16V → OPEL CORSA GSI 1.6 - 16V | vehicle-opel-corsa-gsi-1-6-16v | Samo prijave; obim nije odlučen; P str. 2, R.B. 104 |
| OPEL MANTA → OPEL MANTA | vehicle-opel-manta | Samo prijave; obim nije odlučen; P str. 2, R.B. 75 |
| PEUGEUOT 205 GTI → PEUGEOT 205 GTI | vehicle-peugeot-205-gti | Samo prijave; obim nije odlučen; P str. 2, R.B. 100 |
| PEUGEUOT 308 → PEUGEOT 308 | vehicle-peugeot-308 | Samo prijave; obim nije odlučen; P str. 2, R.B. 94 |
| PORSCHE → PORSCHE | vehicle-porsche | Samo prijave; obim nije odlučen; P str. 2, R.B. 84 |
| RADICAL → RADICAL | vehicle-radical | Samo prijave; obim nije odlučen; P str. 2, R.B. 132 |
| RENAULT CLIO / Renault Clio → RENAULT CLIO | vehicle-renault-clio | Samo prijave; obim nije odlučen; P str. 2, R.B. 97; P str. 2, R.B. 99; P str. 2, R.B. 102 |
| RENAULT TATUUS → RENAULT TATUUS | vehicle-renault-tatuus | Samo prijave; obim nije odlučen; P str. 2, R.B. 123 |
| ŠKODA FABIA → ŠKODA FABIA | vehicle-skoda-fabia | Samo prijave; obim nije odlučen; P str. 2, R.B. 118 |
| ŠKODA FAVORIT → ŠKODA FAVORIT | vehicle-skoda-favorit | Samo prijave; obim nije odlučen; P str. 2, R.B. 79 |
| ŠKODA OCTAVIA → ŠKODA OCTAVIA | vehicle-skoda-octavia | Samo prijave; obim nije odlučen; P str. 2, R.B. 115 |
| ŠKODA OKTAVIA → ŠKODA OKTAVIA | vehicle-skoda-oktavia | Samo prijave; obim nije odlučen; P str. 2, R.B. 114 |
| TATUUS MASTER → TATUUS MASTER | vehicle-tatuus-master | Samo prijave; obim nije odlučen; P str. 2, R.B. 133 |
| TOYOTA YARIS → TOYOTA YARIS | vehicle-toyota-yaris | Samo prijave; obim nije odlučen; P str. 2, R.B. 86 |
| VOLVO 850 → VOLVO 850 | vehicle-volvo-850 | Samo prijave; obim nije odlučen; P str. 2, R.B. 128 |
| VW GOLF II → VW GOLF II | vehicle-vw-golf-ii | Samo prijave; obim nije odlučen; P str. 1, R.B. 62; P str. 1, R.B. 65 |
| VW GOLF1 → VW GOLF1 | vehicle-vw-golf1 | Samo prijave; obim nije odlučen; P str. 2, R.B. 77; P str. 2, R.B. 78 |
| ZASTAVA 750 → ZASTAVA 750 | vehicle-zastava-750 | Samo prijave; obim nije odlučen; P str. 2, R.B. 76 |
| ZASTAVA YUGO 1,6 → ZASTAVA YUGO 1,6 | vehicle-zastava-yugo-1-6 | Samo prijave; obim nije odlučen; P str. 2, R.B. 105 |

Ključevi opisuju ponovljivo vozilo/model, ne fizičku šasiju. Skraćeni BMW, GLOBAL, ROVER, PORSCHE, GIBSON i RADICAL ne dopunjavaju se izmišljenim modelom. WV POLO i VW POLO su za sada dva zasebna kandidata do odluke; isto vrijedi za druge skraćene opise. Normalizacija razmaka i veličine slova ne dodaje tehničke specifikacije. PEUGEUOT je predlog aliasa zasnovan na istim redovima vozača u P/R, ne konačna odluka. Nepotvrđene greške iz prijava (npr. FORD FIETS, LAMBORGINI, ŠKODA OKTAVIA) ostaju doslovno zapisane i čekaju dodatnu provjeru ako uđu u obim.

## Prihvaćene klase AKSCG

| Klasa | Predloženi archive_key | Broj nastupa / lokatori |
| --- | --- | --- |
| A 1150 | class-akscg-a-1150 | 7; R str. 4, A 1150, #30; R str. 4, A 1150, #318; R str. 4, A 1150, #78; R str. 4, A 1150, #92; R str. 4, A 1150, #197; R str. 4, A 1150, #236; R str. 4, A 1150, #442 |
| A 1400 | class-akscg-a-1400 | 4; R str. 5, A 1400, #115; R str. 5, A 1400, #23; R str. 5, A 1400, #95; R str. 5, A 1400, #119 |
| A 1600 | class-akscg-a-1600 | 4; R str. 6, A 1600, #7; R str. 6, A 1600, #48; R str. 6, A 1600, #96; R str. 6, A 1600, #220 |
| A 2000 | class-akscg-a-2000 | 2; R str. 7, A 2000, #43; R str. 7, A 2000, #506 |
| D/E2/CN | class-akscg-d-e2-cn | 3; R str. 7, D/E2/CN, #21; R str. 7, D/E2/CN, #14; R str. 7, D/E2/CN, #52 |
| E1 1200 | class-akscg-e1-1200 | 2; R str. 5, E1 1200, #71; R str. 5, E1 1200, #262 |
| E2SH | class-akscg-e2sh | 3; R str. 5, E2SH, #36; R str. 5, E2SH, #88; R str. 6, E2SH, #25 |
| H 1000 | class-akscg-h-1000 | 3; R str. 3, H 1000, #121; R str. 3, H 1000, #500; R str. 3, H 1000, #81 |
| H 1400 | class-akscg-h-1400 | 5; R str. 5, H 1400, #91; R str. 5, H 1400, #222; R str. 5, H 1400, #86; R str. 5, H 1400, #152; R str. 5, H 1400, #230 |
| H 1600 | class-akscg-h-1600 | 4; R str. 6, H 1600, #31; R str. 6, H 1600, #416; R str. 6, H 1600, #151; R str. 6, H 1600, #94 |
| H 2000 | class-akscg-h-2000 | 4; R str. 7, H 2000, #12; R str. 7, H 2000, #104; R str. 7, H 2000, #113; R str. 7, H 2000, #53 |
| H 2000+ | class-akscg-h-2000-plus | 4; R str. 7, H 2000+, #16; R str. 7, H 2000+, #217; R str. 7, H 2000+, #237; R str. 7, H 2000+, #333 |
| N 1150 | class-akscg-n-1150 | 9; R str. 3, N 1150, #160; R str. 4, N 1150, #76; R str. 4, N 1150, #200; R str. 4, N 1150, #155; R str. 4, N 1150, #510; R str. 4, N 1150, #153; R str. 4, N 1150, #208; R str. 4, N 1150, #287; R str. 4, N 1150, #511 |
| N 1400 | class-akscg-n-1400 | 8; R str. 4, N 1400, #13; R str. 4, N 1400, #231; R str. 4, N 1400, #19; R str. 4, N 1400, #17; R str. 4, N 1400, #18; R str. 4, N 1400, #75; R str. 5, N 1400, #89; R str. 5, N 1400, #224 |
| N 1600 | class-akscg-n-1600 | 3; R str. 6, N 1600, #29; R str. 6, N 1600, #33; R str. 6, N 1600, #454 |
| N 2000 | class-akscg-n-2000 | 3; R str. 6, N 2000, #26; R str. 6, N 2000, #68; R str. 6, N 2000, #80 |

D/E2/CN je potvrđen čitljivim naslovom tabele R str. 7 i pojedinačnim redovima K. Sitni preklopljeni tekst kolona Klasa/Grupa u R str. 3/7 je u ekstrakciji „D / E2 / DC N/ E2 / CN“; taj artefakt nije novi naziv klase. Formula iz P je širi izvorni opis; ne stvarati iz njega zamjensku konačnu klasu. H 2000 + i H2000+ predlažu se kao potvrđene varijante H 2000+ nakon odluke.

## Razlike startnih brojeva — konačni R/K su predloženi autoritet

| Vozač | P lokator / broj | R/K konačni broj / lokatori |
| --- | --- | --- |
| DEJAN BULATOVIĆ | P str. 1, R.B. 61; 46 | 12; R str. 1, kat. 1, #12; K str. 1, AMSK KOTOR, #12 |
| MARKO PEJOVIĆ | P str. 1, R.B. 22; prazno | 231; R str. 1, kat. 1, #231; K str. 3, AMK PODGORICA RT, #231 |
| ILIJA ŠAPIĆ | P str. 1, R.B. 26; prazno | 17; R str. 1, kat. 1, #17; K str. 2, AMSK HERCEG NOVI, #17 |
| DARKO KOKOTOVIĆ | P str. 1, R.B. 49; 31 | 151; R str. 1, kat. 1, #151; K str. 2, AMSK HERCEG NOVI, #151 |
| ANDRIJA ĐURIŠIĆ | P str. 1, R.B. 3; 12 | 121; R str. 2, kat. 1, #121; K str. 1, AMSK AP SPORT, #121 |
| STEFAN MILINIĆ | P str. 1, R.B. 67; 13 | 113; R str. 2, kat. 1, #113; K str. 1, AMSK PODI, #113 |
| ALMIN ĆOROVIĆ | P str. 1, R.B. 59; prazno | 506; R str. 2, kat. 1, #506; K str. 1, AMSK AP SPORT, #506 |
| LUKA BRKAN | P str. 1, R.B. 6; prazno | 160; R str. 2, kat. 1, #160; K str. 3, AMSK TIVAT, #160 |
| IGOR RADMILOVIĆ | P str. 1, R.B. 9; prazno | 155; R str. 2, kat. 1, #155; K str. 2, AMSK HERCEG NOVI, #155 |
| DARKO IVANOVIĆ | P str. 1, R.B. 33; prazno | 86; R str. 2, kat. 1, #86; K str. 3, AMSK TIVAT, #86 |
| MILAN BRACANOVIĆ | P str. 1, R.B. 37; 75 | 152; R str. 2, kat. 1, #152; K str. 1, AMSK AP SPORT, #152 |
| MATIJA MARKOVIĆ | P str. 1, R.B. 4; prazno | 153; R str. 2, kat. 1, #153; K str. 3, AMKK LOVĆEN R.T., #153 |
| MILE MALOVIĆ | P str. 1, R.B. 70; prazno | 237; R str. 3, kat. 1, #237; K str. 3, AMK PODGORICA RT, #237 |
| DEJAN ŠUŠKAVČEVIĆ | P str. 1, R.B. 68; 33 | 333; R str. 3, kat. 1, #333; K str. 4, AMSK NILOVIĆ RACING, #333 |
| BAĆKO VUJOVIĆ | P str. 1, R.B. 72; 24 | 14; R str. 3, kat. 2, #14; K str. 3, AMSK GORŠTAK, #14 |

Nijedan startni broj nije globalni identitet vozača. P sadrži duplikate 12/13/21/31/33/75, različita prvenstva i kasnije promjene; broj mora ostati vezan za konkretni nastup.

## Rezultati prve i druge vožnje i zvanične klasifikacije — za provjeru

| Lokator R / vozač | 1. vožnja | 2. vožnja | Ukupno Time | Kategorija/mjesto; klasa/mjesto | Bodovi GENERALKA / KLASA |
| --- | --- | --- | --- | --- | --- |
| R str. 1, kat. 1, #36; JOVAN PERUNOVIĆ | 2:27.192 | 2:30.347 | 4:57.539 | 1/1; E2SH/1 | 25 / 25,00 |
| R str. 1, kat. 1, #16; GORAN VUČKOVIĆ | 2:30.065 | 2:33.457 | 5:03.522 | 1/2; H 2000+/1 | 18 / 25,00 |
| R str. 1, kat. 1, #12; DEJAN BULATOVIĆ | 2:32.198 | 2:32.539 | 5:04.737 | 1/3; H 2000/1 | 15 / 25,00 |
| R str. 1, kat. 1, #88; TRIPO PINJATIĆ | 2:31.805 | 2:33.096 | 5:04.901 | 1/4; E2SH/2 | 12 / 18,00 |
| R str. 1, kat. 1, #7; MIRKO VUJAČIĆ | 2:33.726 | 2:37.702 | 5:11.428 | 1/5; A 1600/1 | 10 / 25,00 |
| R str. 1, kat. 1, #26; DUŠAN TUJKOVIĆ | 2:38.098 | 2:39.511 | 5:17.609 | 1/6; N 2000/1 | 8 / 25,00 |
| R str. 1, kat. 1, #31; MILOŠ BOJANOVIĆ | 2:39.986 | 2:38.566 | 5:18.552 | 1/7; H 1600/1 | 6 / 25,00 |
| R str. 1, kat. 1, #68; GORAN VUČETIĆ | 2:38.095 | 2:41.806 | 5:19.901 | 1/8; N 2000/2 | 4 / 18,00 |
| R str. 1, kat. 1, #416; DAMJAN PEROVIĆ | 2:39.445 | 2:41.879 | 5:21.324 | 1/9; H 1600/2 | 2 / 18,00 |
| R str. 1, kat. 1, #71; ĐURO POPOVIĆ | 2:39.954 | 2:42.579 | 5:22.533 | 1/10; E1 1200/1 | 1 / 12,50 |
| R str. 1, kat. 1, #217; EFIMIN DMITRI | 2:42.513 | 2:42.515 | 5:25.028 | 1/11; H 2000+/2 | prazno — nije automatska nula / 18,00 |
| R str. 1, kat. 1, #262; BALŠA ĐURIČKOVIĆ | 2:43.521 | 2:42.327 | 5:25.848 | 1/12; E1 1200/2 | prazno — nije automatska nula / 9,00 |
| R str. 1, kat. 1, #43; RADOVAN MASONIČIĆ | 2:41.739 | 2:45.908 | 5:27.647 | 1/13; A 2000/1 | prazno — nije automatska nula / 12,50 |
| R str. 1, kat. 1, #115; MILUTIN KRIVOKAPIĆ | 2:51.133 | 2:46.316 | 5:37.449 | 1/14; A 1400/1 | prazno — nije automatska nula / 25,00 |
| R str. 1, kat. 1, #23; MARKO ADŽIĆ | 2:51.177 | 2:50.963 | 5:42.140 | 1/15; A 1400/2 | prazno — nije automatska nula / 18,00 |
| R str. 1, kat. 1, #91; MILO ĐUKANOVIĆ | 2:52.247 | 2:50.020 | 5:42.267 | 1/16; H 1400/1 | prazno — nije automatska nula / 25,00 |
| R str. 1, kat. 1, #13; MILOŠ VUJNOVIĆ | 2:52.706 | 2:52.202 | 5:44.908 | 1/17; N 1400/1 | prazno — nije automatska nula / 25,00 |
| R str. 1, kat. 1, #231; MARKO PEJOVIĆ | 2:53.193 | 2:52.596 | 5:45.789 | 1/18; N 1400/2 | prazno — nije automatska nula / 18,00 |
| R str. 1, kat. 1, #29; SLOBODAN MUGOŠA | 2:53.856 | 2:54.345 | 5:48.201 | 1/19; N 1600/1 | prazno — nije automatska nula / 25,00 |
| R str. 1, kat. 1, #19; DEJAN KOVAČEVIĆ | 2:55.170 | 2:53.967 | 5:49.137 | 1/20; N 1400/3 | prazno — nije automatska nula / 15,00 |
| R str. 1, kat. 1, #104; GORAN MILOŠEV | 2:56.140 | 2:55.367 | 5:51.507 | 1/21; H 2000/2 | prazno — nije automatska nula / 18,00 |
| R str. 1, kat. 1, #17; ILIJA ŠAPIĆ | 2:56.538 | 2:56.514 | 5:53.052 | 1/22; N 1400/4 | prazno — nije automatska nula / 12,00 |
| R str. 1, kat. 1, #222; LUKA ŠUĆUR | 2:54.844 | 3:00.297 | 5:55.141 | 1/23; H 1400/2 | prazno — nije automatska nula / 18,00 |
| R str. 1, kat. 1, #151; DARKO KOKOTOVIĆ | 2:59.316 | 2:58.426 | 5:57.742 | 1/24; H 1600/3 | prazno — nije automatska nula / 15,00 |
| R str. 1, kat. 1, #30; PETAR JOVANOVIĆ | 2:59.005 | 2:58.871 | 5:57.876 | 1/25; A 1150/1 | prazno — nije automatska nula / 25,00 |
| R str. 1, kat. 1, #318; ŽARKO LAGATOR | 3:00.260 | 2:58.586 | 5:58.846 | 1/26; A 1150/2 | prazno — nije automatska nula / 18,00 |
| R str. 2, kat. 1, #95; STEFAN BOKOVAC | 2:51.122 | 3:09.592 | 6:00.714 | 1/27; A 1400/3 | prazno — nije automatska nula / 15,00 |
| R str. 2, kat. 1, #121; ANDRIJA ĐURIŠIĆ | 3:00.649 | 3:00.788 | 6:01.437 | 1/28; H 1000/1 | prazno — nije automatska nula / 25,00 |
| R str. 2, kat. 1, #113; STEFAN MILINIĆ | 3:02.526 | 2:59.796 | 6:02.322 | 1/29; H 2000/3 | prazno — nije automatska nula / 15,00 |
| R str. 2, kat. 1, #506; ALMIN ĆOROVIĆ | 3:01.520 | 3:01.351 | 6:02.871 | 1/30; A 2000/2 | prazno — nije automatska nula / 9,00 |
| R str. 2, kat. 1, #48; MARIJA IVANOVIĆ | 3:00.487 | 3:03.936 | 6:04.423 | 1/31; A 1600/2 | prazno — nije automatska nula / 18,00 |
| R str. 2, kat. 1, #119; SAKIB DURAKOVIĆ | 3:02.616 | 3:02.649 | 6:05.265 | 1/32; A 1400/4 | prazno — nije automatska nula / 12,00 |
| R str. 2, kat. 1, #33; GLIGORIJE ŠUŠIĆ | 2:58.132 | 3:08.849 | 6:06.981 | 1/33; N 1600/2 | prazno — nije automatska nula / 18,00 |
| R str. 2, kat. 1, #96; MARGARITA MALTSEVA | 3:10.223 | 3:06.912 | 6:17.135 | 1/34; A 1600/3 | prazno — nije automatska nula / 15,00 |
| R str. 2, kat. 1, #160; LUKA BRKAN | 3:09.420 | 3:08.707 | 6:18.127 | 1/35; N 1150/1 | prazno — nije automatska nula / 25,00 |
| R str. 2, kat. 1, #76; STEVAN MILOŠEV | 3:10.638 | 3:10.049 | 6:20.687 | 1/36; N 1150/2 | prazno — nije automatska nula / 18,00 |
| R str. 2, kat. 1, #200; DRAGAN DŽOGAZ | 3:12.193 | 3:09.059 | 6:21.252 | 1/37; N 1150/3 | prazno — nije automatska nula / 15,00 |
| R str. 2, kat. 1, #155; IGOR RADMILOVIĆ | 3:13.348 | 3:09.456 | 6:22.804 | 1/38; N 1150/4 | prazno — nije automatska nula / 12,00 |
| R str. 2, kat. 1, #510; STEVAN MILIČKOVIĆ | 3:14.660 | 3:12.018 | 6:26.678 | 1/39; N 1150/5 | prazno — nije automatska nula / 10,00 |
| R str. 2, kat. 1, #500; NIKOLA TOŠKOVIĆ | 3:14.063 | 3:13.739 | 6:27.802 | 1/40; H 1000/2 | prazno — nije automatska nula / 18,00 |
| R str. 2, kat. 1, #81; VOJO RADANOVIĆ | 3:16.183 | 3:13.257 | 6:29.440 | 1/41; H 1000/3 | prazno — nije automatska nula / 15,00 |
| R str. 2, kat. 1, #18; ĐORĐE KOVAČEVIĆ | 3:01.620 | DNS | prazna ćelija — odluka o ukupnom statusu | 1/bez plasmana; N 1400/bez plasmana | prazno — nije automatska nula / 0,00 |
| R str. 2, kat. 1, #25; ŽELJKO RADONJIĆ | 2:40.090 | DNS | prazna ćelija — odluka o ukupnom statusu | 1/bez plasmana; E2SH/bez plasmana | prazno — nije automatska nula / 0,00 |
| R str. 2, kat. 1, #53; LAZAR MUJOVIĆ | DNS | DNS | DNS | 1/bez plasmana; H 2000/bez plasmana | prazno — nije automatska nula / 0,00 |
| R str. 2, kat. 1, #75; NIKOLA VLAHOVIĆ | DISQ | DISQ | DISQ | 1/bez plasmana; N 1400/bez plasmana | prazno — nije automatska nula / 0,00 |
| R str. 2, kat. 1, #78; MILAN ROGANOVIĆ | 3:10.689 | DNF | prazna ćelija — odluka o ukupnom statusu | 1/bez plasmana; A 1150/bez plasmana | prazno — nije automatska nula / 0,00 |
| R str. 2, kat. 1, #80; MARKO JANKOVIĆ | DNF | DNS | DNF | 1/bez plasmana; N 2000/bez plasmana | prazno — nije automatska nula / 0,00 |
| R str. 2, kat. 1, #86; DARKO IVANOVIĆ | DNF | DNS | DNF | 1/bez plasmana; H 1400/bez plasmana | prazno — nije automatska nula / 0,00 |
| R str. 2, kat. 1, #89; DEJAN ĐONOVIĆ | 2:50.580 | DNF | prazna ćelija — odluka o ukupnom statusu | 1/bez plasmana; N 1400/bez plasmana | prazno — nije automatska nula / 0,00 |
| R str. 2, kat. 1, #92; BOŽIDAR DODEROVIĆ | DNS | DNS | DNS | 1/bez plasmana; A 1150/bez plasmana | prazno — nije automatska nula / 0,00 |
| R str. 2, kat. 1, #94; ZLATKO SINDIK | 3:08.001 | DNS | prazna ćelija — odluka o ukupnom statusu | 1/bez plasmana; H 1600/bez plasmana | prazno — nije automatska nula / 0,00 |
| R str. 2, kat. 1, #152; MILAN BRACANOVIĆ | DNS | DNS | DNS | 1/bez plasmana; H 1400/bez plasmana | prazno — nije automatska nula / 0,00 |
| R str. 2, kat. 1, #153; MATIJA MARKOVIĆ | DISQ | DISQ | DISQ | 1/bez plasmana; N 1150/bez plasmana | prazno — nije automatska nula / 0,00 |
| R str. 3, kat. 1, #197; SAVA NIKČEVIĆ | DISQ | DISQ | DISQ | 1/bez plasmana; A 1150/bez plasmana | prazno — nije automatska nula / 0,00 |
| R str. 3, kat. 1, #208; PETAR ZUROVAC | DISQ | DISQ | DISQ | 1/bez plasmana; N 1150/bez plasmana | prazno — nije automatska nula / 0,00 |
| R str. 3, kat. 1, #220; KRSTO RADOVIĆ | DNS | DNS | DNS | 1/bez plasmana; A 1600/bez plasmana | prazno — nije automatska nula / 0,00 |
| R str. 3, kat. 1, #224; MILJAN NIKOLIĆ | 2:51.638 | DNF | prazna ćelija — odluka o ukupnom statusu | 1/bez plasmana; N 1400/bez plasmana | prazno — nije automatska nula / 0,00 |
| R str. 3, kat. 1, #230; LAZAR KOSTIĆ | 2:53.004 | DNS | prazna ćelija — odluka o ukupnom statusu | 1/bez plasmana; H 1400/bez plasmana | prazno — nije automatska nula / 0,00 |
| R str. 3, kat. 1, #236; DRAGAN MUDREŠA | DNF | DNS | DNF | 1/bez plasmana; A 1150/bez plasmana | prazno — nije automatska nula / 0,00 |
| R str. 3, kat. 1, #237; MILE MALOVIĆ | DNS | DNS | DNS | 1/bez plasmana; H 2000+/bez plasmana | prazno — nije automatska nula / 0,00 |
| R str. 3, kat. 1, #287; MILORAD RAŽNATOVIĆ | DISQ | DISQ | DISQ | 1/bez plasmana; N 1150/bez plasmana | prazno — nije automatska nula / 0,00 |
| R str. 3, kat. 1, #333; DEJAN ŠUŠKAVČEVIĆ | DNS | DNS | DNS | 1/bez plasmana; H 2000+/bez plasmana | prazno — nije automatska nula / 0,00 |
| R str. 3, kat. 1, #442; SPASOJE NIKOLIĆ | DNS | DNS | DNS | 1/bez plasmana; A 1150/bez plasmana | prazno — nije automatska nula / 0,00 |
| R str. 3, kat. 1, #454; RADIVOJE JANJUŠEVIĆ | 3:11.042 | DNF | prazna ćelija — odluka o ukupnom statusu | 1/bez plasmana; N 1600/bez plasmana | prazno — nije automatska nula / 0,00 |
| R str. 3, kat. 1, #511; NIKOLA LAGATOR | DISQ | DISQ | DISQ | 1/bez plasmana; N 1150/bez plasmana | prazno — nije automatska nula / 0,00 |
| R str. 3, kat. 2, #21; ALDIN ĆOROVIĆ | 2:18.661 | 2:23.613 | 4:42.274 | 2/1; D/E2/CN/1 | 25 / 25,00 |
| R str. 3, kat. 2, #14; BAĆKO VUJOVIĆ | 2:25.918 | 2:20.730 | 4:46.648 | 2/2; D/E2/CN/2 | 18 / 18,00 |
| R str. 3, kat. 2, #52; BOŽIDAR JABLAN | DNF | DNS | DNF | 2/bez plasmana; D/E2/CN/bez plasmana | prazno — nije automatska nula / 0,00 |

FINISHED se odnosi samo na stvarno objavljeno vrijeme vožnje; DISQ se prenosi kao status DSQ, uz izvorni zapis u dokazima. Za DNS/DNF/DISQ nema izmišljenog vremena. Objavljeni kategorijski/klasni zbir i bodovi ne postaju treća trkačka vožnja. Ukupna vremena su sačuvana u reconciliation.json; postojeći model ClassificationResult nema polje za zbir vremena. Buduća implementacija ne smije ga prikazati kao vrijeme pojedinačne sesije. Klasifikacije različitih kategorija ne spajati u nepostojeću zajedničku generalku.

## Objavljeni klupski poredak

| Rang | Klub | Bodovi | Lokator |
| --- | --- | --- | --- |
| 1 | AMSK KOTOR | 75 | K str. 1, zaglavlje kluba |
| 2 | AMSK AP SPORT | 75 | K str. 1, zaglavlje kluba |
| 3 | AMSK PODI | 68 | K str. 1, zaglavlje kluba |
| 4 | AMSK HERCEG NOVI | 68 | K str. 2, zaglavlje kluba |
| 5 | AMSK CRMNICA | 61 | K str. 2, zaglavlje kluba |
| 6 | AMSK GORŠTAK | 52 | K str. 2, zaglavlje kluba |
| 7 | AMSK TIVAT | 25 | K str. 3, zaglavlje kluba |
| 8 | AMKK LOVĆEN R.T. | 18 | K str. 3, zaglavlje kluba |
| 9 | AMK PODGORICA RT | 18 | K str. 3, zaglavlje kluba |
| 10 | AMK MONTERACING | 18 | K str. 3, zaglavlje kluba |
| 11 | AMSK VLAHOVIĆ | 0 | K str. 3, zaglavlje kluba |
| 12 | AMSK NILOVIĆ RACING | 0 | K str. 3, zaglavlje kluba |

K vizuelno podebljava tri uračunata vozača kod vodećih klubova. Objavljene sume su sačuvane; iz njih se ne proglašavaju kompletna sezonska pravila ili pravilo izjednačenja. Za sada domen nema tabelu za klupski rezultat događaja, a javni dashboard ne čita zvanične ClassificationResult bodove. Dokument ostaje dokaz; ne zamijeniti ove sume postojećim izračunom iz posljednje vožnje. Ako je javni prikaz zvaničnog poretka dio odobrenog obima, potrebna je odgovarajuća podrška bez fabrikovanja sesije.

## Nedostajući podaci

Rezultati i tačni čas treninga, tačni časovi starta vožnji, izvorni URL-ovi, kompletna pravila sezone i rezultati drugih prvenstava još nijesu dostavljeni. Nema otvorenih odluka koje blokiraju ovu migraciju. Za KOS oznaku iz P tri slova ne staju u nationality_code CHAR(2): tačno KOS je sačuvano u javnoj identity_notes, a strukturisana vrijednost ostaje NULL; nije izmišljena druga nacionalnost. MNE/HR/SRB/SLO/ALB su preneseni u ME/HR/RS/SI/AL kao normalizovane oznake iz prijave, bez tvrdnje o dokumentima državljanstva.

## Migracije i verifikacija

- `backend/src/main/resources/db/changelog/014-official-event-results.yaml`
- `backend/src/main/resources/db/changelog/015-kotor-trojica-2026.yaml`
- `backend/src/main/resources/db/changelog/015-kotor-trojica-identities.sql`
- `backend/src/main/resources/db/changelog/015-kotor-trojica-event.sql`
- `backend/src/main/resources/db/changelog/015-kotor-trojica-sources.sql`

014 dodaje zbirno vrijeme zvaničnoj klasifikaciji i tabelu objavljenih klupskih rezultata. 015 kreira identitete, roditelje, događaj, sesije, nastupe, rezultate, izvore i alias dokaze. SQL koristi stabilne ključeve i eksplicitne provjere sa HALT; podudaranje s nepregledanim postojećim zapisom zaustavlja migraciju, bez brisanja ili automatskog spajanja.

Sva vremena i statusi prvih/drugih vožnji su preneseni doslovno; pojedinačni plasmani ostaju NULL jer nijesu objavljeni. Zbirne klasifikacije sadrže objavljeno vrijeme, plasman i bodove. Trening je potvrđena sesija bez rezultata, scheduled_at je NULL jer čas nije poznat. Zvanični bodovi su dostupni na klasifikacijama i klupskom poretku; dashboard ne računa sezonske bodove bez pravila.

Frontend build i lint su prošli. Backend: 15 testova, 0 grešaka, 0 neuspjeha i 0 preskočenih testova uz podešene izolovane MySQL baze. Čist replay na MySQL 8.4.11 i nadogradnja sa 013 su prošli; nepovezani stari profil je sačuvan. Nadogradnja je koristila generisane ID-jeve od 10000, a ponovljeni Liquibase update nije kreirao duplikate.

Pokrenuta je prava izolovana Spring Boot aplikacija sa Hibernate validacijom i nasumičnim HTTP portom. Provjereni su dashboard bez pretpostavljenih sezonskih bodova, sve 3 sesije (trening 0, svaka vožnja 68 rezultata), svih 18 klasifikacija, klupski poredak i prazan profil vozača bez nastupa. Svih 136 pojedinačnih ishoda i 68 klasnih rezultata poređeni su s reconciliation.json. Završno read-only poređenje baze je dodatno potvrdilo svih 133 imena, svih 136 kategorijskih/klasnih redova (vrijeme, plasman, status i bodovi) i svih 12 klupskih plasmana/bodova bez razlika.

Sva 3 registrovana originalna PDF-a su preuzeta kroz javni HTTP endpoint sa application/pdf tipom i potvrđenim SHA-256. Originalni bajtovi su sačuvani.

Testne baze: racing_archive_verify_kotor_20261020_clean i racing_archive_verify_kotor_20261020_upgrade. Sadrže samo izolovane testne podatke i nijesu baza aplikacije. Migracije nijesu primijenjene na bazu aplikacije; nema objave ili deploy-a.

## Naknadna odluka o bodovanju — 20.10.2026.

Programer je izričito zatražio da se CG sezona 2026. poveže sa sačuvanim
konfiguracionim zapisom `Modern hillclimb scoring system`. Ova odluka zamjenjuje
raniju odluku da `scoring_system_id` ostane NULL. Osnov je instrukcija programera,
a ne novi dostavljeni pravilnik.

Nova migracija `016-cg-2026-scoring-system.yaml` povezuje samo CG hillclimb sezonu
2026. Koristi provjerene prirodne ključeve umjesto lokalnih numeričkih ID-jeva i
zaustavlja se ako sezona ili sistem nijesu jednoznačni ili nedostaje raspored bodova.
Postojeća konfiguracija, zvanični bodovi klasifikacija, pojedinačni rezultati i
originalni dokumenti ostaju neizmijenjeni. Ranije primijenjene migracije nijesu mijenjane.

Verifikacija 016: svih 15 backend testova je prošlo bez preskakanja, uključujući
čist MySQL replay, nadogradnju sa pomjerenim generisanim ID-jevima, poređenje
zvaničnih rezultata i originalna preuzimanja. Backend je ponovo izgrađen i pokrenut;
migracija 016 je EXECUTED u bazi aplikacije, a javni dashboard vraća scoringKnown=true.

Uočeno ograničenje postojećeg obračuna: sezonski dashboard još čita plasman
posljednje vožnje, a izvori tih pojedinačnih plasmana nemaju (ostaju NULL).
Zbog toga trenutno svih 68 učesnika ima nule u sezonskom prikazu. Ovo povezivanje
ne mijenja taj obračun niti zvanične bodove u classification_results. Potrebna je
zasebna izmjena da sezonski obračun koristi odgovarajuće zvanične klasifikacije.

## Ispravka sezonskog prikaza — 20.10.2026.

Na zahtjev programera prilagođen je prikaz glavne stranice i izvor njegovih
podataka u API-ju. Ranije opisano ograničenje sa nulama je riješeno:
sezonski poredak sabira upisane bodove iz konačnih klasnih klasifikacija, zatim
primjenjuje podešena pravila odbacivanja rezultata. Kategorijske klasifikacije
se ne uračunavaju još jednom, a zvanični umanjeni bodovi ne umanjuju se ponovo.
Nedostajući zvanični bodovi daju nedostupan zbir, dok stvarna nula ostaje nula.
Nijedan izvorni rezultat, dokument ni primijenjena migracija nijesu mijenjani.

Verifikacija: svih 20 backend testova je prošlo bez preskakanja, uključujući
poređenje svih 68 sezonskih zbirova sa zvaničnim klasnim bodovima kroz izolovanu
MySQL/Spring Boot aplikaciju, testove sezonskog odbacivanja, polovičnih bodova,
DSQ pravila i nepoznatih bodova. Frontend build i lint su prošli. Backend je
ponovo pokrenut; javni API aplikacije prikazuje 16 klasa i 68 vozača, od kojih
43 imaju pozitivne bodove, uključujući dva rezultata od 12,5. Frontend prikazuje
kolone ukupnih bodova i bodova nakon odbacivanja.
