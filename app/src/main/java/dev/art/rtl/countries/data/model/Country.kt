package dev.art.rtl.countries.data.model

import com.google.gson.annotations.SerializedName

/*data class Country(
    val capital: List<String>,
    val region: String,
    val subregion: String,
    val flags: CoatOfArms
)*/

data class Country (
    val name: Name,
    val tld: List<String>,
    val cca2: String,
    val ccn3: String,
    val cca3: String,
    val cioc: String,
    val independent: Boolean,
    val status: String,
    val unMember: Boolean,
    //val currencies: Currencies,
    val idd: Idd,
    val capital: List<String>,
    val altSpellings: List<String>,
    val region: String,
    val subregion: String,
    val languages: Languages,
    val translations: Map<String, Translation>,
    val latlng: List<Double>,
    val landlocked: Boolean,
    val borders: List<String>,
    val area: Double,
    val demonyms: Demonyms,
    val flag: String,
    val maps: Maps,
    val population: Long,
    val fifa: String,
    val car: Car,
    val timezones: List<String>,
    val continents: List<String>,
    val flags: CoatOfArms,
    val coatOfArms: CoatOfArms,
    val startOfWeek: String,
    val capitalInfo: CapitalInfo
)

data class CapitalInfo (
    val latlng: List<Double>
)

data class Car (
    val signs: List<String>,
    val side: String
)

data class CoatOfArms (
    val png: String,
    val svg: String
)

/*data class Currencies (
    @Json(name = "QAR")
    val qar: Qar
)

data class Qar (
    val name: String,
    val symbol: String
)*/

data class Demonyms (
    val eng: Eng,
    val fra: Eng
)

data class Eng (
    val f: String,
    val m: String
)

data class Idd (
    val root: String,
    val suffixes: List<String>
)

data class Languages (
    val ara: String
)

data class Maps (
    val googleMaps: String,
    val openStreetMaps: String
)

data class Name (
    val common: String,
    val official: String,
    val nativeName: NativeName
)

data class NativeName (
    val ara: Translation
)

data class Translation (
    val official: String,
    val common: String
)


//data class CountriesResponse(val countries: List<Country>)

/*
data class Country(
    val name: CountryName,
    val capital: List<String>,
    val region: String,
    val subregion: String,
    val flags: Flags
    )

data class CountryName(
    @SerializedName("common") val commonName: String,
    @SerializedName("official") val officialName: String
    )

data class Flags(
    @SerializedName("png") val pngFlag: String,
    @SerializedName("svg") val svgFlag: String
)

 */