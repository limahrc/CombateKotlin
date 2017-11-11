package equipamentos

import personagens.Personagem

class Armadura(dono: Personagem) : Equipamento() {

    override val DESGASTE = 15
    private var DEFESA = 0

    init {
        dono.poderDefesa = DEFESA
    }

}