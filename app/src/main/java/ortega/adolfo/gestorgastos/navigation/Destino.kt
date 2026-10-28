package ortega.adolfo.gestorgastos.navigation

enum class Destino(val etiqueta: String, val ruta: Ruta) {
    GRUPOS("Grupos", Ruta.Grupos),
    GRAFICAS("Gráficas", Ruta.Graficas),
    INICIO("Inicio", Ruta.Inicio),
    PRESUPUESTO("Presupuesto", Ruta.Presupuesto),
    PERFIL("Perfil", Ruta.Perfil)
}
