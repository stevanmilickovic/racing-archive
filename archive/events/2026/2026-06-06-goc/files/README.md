# Goč 2026 — Arhiva rezultata SCG

Nagrada Grada Kraljeva — Goč 2026 · 6–7. jun 2026.

**Sadržaj:** 46 evidentiranih takmičara Šampionata Crne Gore, 11 klubova, 15 klasa i 7 sesija sa izvorno evidentiranim statusima DNS/DNF.

## Fajlovi
- `01_svi_rezultati_SCG.csv`: svih 291 zapisa za SCG, dugačak format (vozač × sesija).
- `02_treninzi_za_import.csv`: 46 vozača sa kolonama za trening 1 i 2.
- `03_trke_za_import.csv`: 46 vozača sa kolonama za trku 1 i 2.
- `04_evidentirani_ucesnici_SCG.csv`: 46 vozača, klub, klasa, vozilo i broj.
- `05_klubovi_SCG.csv`: agregacija članstva po klubu.
- `06_klase_SCG.csv`: broj vozača po klasi.
- `07_prvenstva_i_izvori.csv`: prvenstva sa linkovima do njihovih rezultata.
- `08_sesije.csv`: katalog sesija i direktni URL-ovi.
- `09_podaci_o_trci.json`: podaci o stazi, datumi, izvori i metodologija.
- `placeni_trening_1.csv`, `placeni_trening_2.csv`, `trening_1.csv`, `trening_2.csv`, `trka_1.csv`, `trka_2.csv`, `generalni_plasman.csv`: odvojene tabele.

## Izvori
- <https://timing.gaga.co.rs/event.php?slug=goc-2026>
- <https://timing.gaga.co.rs/uploads/doc_10_1780671204_MBAT_CEZ-_Nagrada_Grada_Kraljeva_-_GOC_2026_-_Pravilnik-pp.pdf>
- <https://timing.gaga.co.rs/uploads/doc_10_1780671250_BAT_Nagrada_Grada_Kraljeva_-_Goc_2026_-_Bilten_br__1.pdf>

## Važne napomene
- **SCG**, ne sve kategorije: detaljni CSV podaci sadrže 46 vozača iz filtera `SCG`. NSS, OPS, CEZ i OPRS navedeni su sa direktnim linkovima, ali njihovi zasebni rezultati nisu prepisani u CSV.
- **Nije zvanična prijavna lista**: mjerilac vremena prikazuje tekst „Startna lista još nije objavljena”. Spisak evidentiranih učesnika rekonstruisan je iz sesijskih rezultata, a ne iz formalne prijavne liste.
- **Statusi**: `FIN` = ostvaren rezultat; `DNS` = nije startovao; `DNF` = nije završio. Prazno vrijeme nije nula. Za plaćene treninge tabela sadrži samo vozače prisutne u objavljenom prikazu te sesije, a ne imputirana `DNS` za ostale.
- **Generalni plasman**: zbir dvije trke za 39 takmičara sa završenim vožnjama; zbir je provjeren naspram objavljenih vodećih i zapisa #510. Za diskvalifikacije/povlačenja nema dodijeljenih vremena.
- **Normalizacija**: `E2 SH` → `E2SH`; `Moto 1200` → `E1 1200`; #510 `MILIĆEVIĆ Stevan` u izvoru je ispravljeno u `MILIČKOVIĆ Stevan`. Izvorne klase su očuvane i u koloni `klasa_izvor`.
- **CSV**: UTF-8 s BOM-om (kompatibilno sa Excel-om), separator `,`, decimalna tačka u polju `vrijeme_ms` koja je zapravo cijeli broj milisekundi, prikaz vremena `m:ss.SSS`.
- **Import**: CSV sa dva treninga/trke je univerzalna tabela; možda zahtijeva mapiranje zaglavlja prema konkretnoj verziji importera u tvom backendu.
- Pravilnik i bilten nisu uključeni kao PDF bajtovi, ali su navedeni kao linkovi u katalogu izvora.
