package equipamentos

import personagens.Personagem

data class Espada(private val dono: Personagem) : Arma(dono) {
    override val tipo = "espada"
    init {
        dano = 20f
    }
}