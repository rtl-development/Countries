package dev.art.rtl.countries.data.model

data class Country(
    val name: Name,
    val capital: List<String>,
    val region: String,
    val subregion: String,
    val languages: Map<String, String>,
    val borders: List<String>,
    val flags: Images,
    val coatOfArms: Images
)

data class Images(
    val png: String,
    val svg: String
)

/*
data class Languages(
    val ara: String,
    val ces: String,
    val cym: String,
    val deu: String,
    val est: String,
    val fin: String,
    val fra: String,
    val hrv: String,
    val hun: String,
    val ita: String,
    val jpn: String,
    val kor: String,
    val nld: String,
    val per: String,
    val pol: String,
    val por: String,
    val rus: String,
    val slk: String,
    val spa: String,
    val swe: String,
    val urd: String,
    val zho: String
)
*/

data class Name(
    val common: String,
    val official: String,
)