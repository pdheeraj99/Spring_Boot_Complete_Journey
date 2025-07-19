# Lesson 2: The Stream Pipeline Anatomy

## Core Concepts

Oka Stream Pipeline lo fundamentally 3 stages untayi:

1.  **Source (మూలం):** Stream ki data ekkadi nunchi vastundo adi. (e.g., `List`, `Array`).
2.  **Intermediate Operations (మధ్యంతర ఆపరేషన్స్):** Ivvi stream meeda transformations perform chestayi.
    * Output malli inkoka Stream la vastundi, so manam chain cheyochu (e.g., `filter().map()`).
    * Examples: `filter()`, `map()`, `sorted()`.
3.  **Terminal Operation (తుది ఆపరేషన్):** Idi pipeline ni "kick-start" chestundi and final result istundi.
    * Terminal operation call chesaka, stream ni malli vaadalemu.
    * Examples: `forEach()`, `collect()`, `count()`.

## Key Concept 1: Lazy Evaluation (సోమరిపోతు మదింపు)

Idi streams lo **most important** concept.

* `StreamPipeline.java` code lo, manam `filter()` and `map()` lo `println` pettam.
* Kani nuvvu code run chesinappudu, `forEach()` (terminal operation) call chese varaku aa `println` statements execute avvavu.
* Ante, intermediate operations anevi kevalam oka "plan" laantivi. Terminal operation call chesinappude aa plan execute avutundi. Daanine **Lazy Evaluation** antaru. Data element by element pipeline lo travel chestundi, antha okesari kadu.

## Key Concept 2: Streams are Consumable (స్ట్రీమ్‌లను ఒకసారే వాడగలం)

* Oka stream meeda terminal operation call chesaka, adi "consumed" or "closed" aipotundi.
* Aa tarvata, ade stream variable ni malli vaadali anukunte `IllegalStateException` vastundi.
* Anduke, prathi sari kotha operation cheyalante, source nunchi kotha stream create cheskovali (`names.stream()`).

## Code Explanation

1.  **`.stream()`**: `names` list nunchi stream create chesindi.
2.  **`.filter()`**: 5 characters kanna ekkuva unna names ni matrame munduku pampistundi.
3.  **`.map()`**: Filter chesina names ni `toUpperCase()` loకి marchindi.
4.  **`.forEach()`**: Final ga vachina prathi name ni console lo print chesindi. Ee line execute ayinappude, paina unna filter and map logic run avutundi.