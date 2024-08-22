package ge.mk.sharedelementtransition.data

data class ContentModel(
    val id: Int,
    val imageUrl: String,
    val title: String,
    val description: String,
)


val contentList = listOf(
    ContentModel(
        id = 1,
        imageUrl = "https://images.moviesanywhere.com/198e228b071c60f5ad57e5f62fe60029/ff22cad6-2218-414d-b853-3f95d76905c7.jpg",
        title = "The Lord of the Rings: The Fellowship of the Ring",
        description = "A meek Hobbit from the Shire and eight companions set out on a journey to destroy the powerful One Ring and save Middle-earth from the Dark Lord Sauron. 1"
    ),
    ContentModel(
        id = 2,
        imageUrl = "https://m.media-amazon.com/images/I/61yfrZ2fQ1L._AC_UF894,1000_QL80_.jpg",
        title = "The Lord of the Rings: The Fellowship of the Ring 2",
        description = "A meek Hobbit from the Shire and eight companions set out on a journey to destroy the powerful One Ring and save Middle-earth from the Dark Lord Sauron. 2"
    ),
    ContentModel(
        id = 3,
        imageUrl = "https://resizing.flixster.com/Ud9K2Pmc_MLxDoGwQv5HPeHP_o8=/ems.cHJkLWVtcy1hc3NldHMvdHZzZWFzb24vZjJkNDFmYWQtYTJmMS00ZWRhLTg1ZWEtNzhhNjdiY2VjNWRiLmpwZw==",
        title = "The Lord of the Rings: The Rings of Power",
        description = "A meek Hobbit from the Shire and eight companions set out on a journey to destroy the powerful One Ring and save Middle-earth from the Dark Lord Sauron. 3"
    ),
)
