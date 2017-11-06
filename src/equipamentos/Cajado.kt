package equipamentos

import personagens.Personagem

data class Cajado(private val dono: Personagem) : Arma(dono) {
    override val tipo = "cajado"
    init {
        dano = 20f
    }
}